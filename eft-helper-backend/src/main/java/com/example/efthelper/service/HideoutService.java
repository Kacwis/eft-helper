package com.example.efthelper.service;

import com.example.efthelper.model.HideoutModule;
import com.example.efthelper.model.HideoutStation;
import com.example.efthelper.model.ModuleRequirement;
import com.example.efthelper.model.projection.*;
import com.example.efthelper.model.projection.myHideoutProjection.HideoutStationDetailsDTO;
import com.example.efthelper.model.projection.myHideoutProjection.RequiredItemDTO;
import com.example.efthelper.model.projection.myHideoutProjection.StationLevelDTO;
import com.example.efthelper.model.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HideoutService {

    final private HideoutModuleRepository moduleRepository;

    final private HideoutStationRepository stationRepository;

    final private ModuleRequirementRepository requirementRepository;

    final private TraderRepository traderRepository;

    final private ItemRepository itemRepository;

    final private UserRepository userRepository;

    public HideoutService(final HideoutModuleRepository moduleRepository,
                          final HideoutStationRepository stationRepository,
                          final ModuleRequirementRepository requirementRepository,
                          final TraderRepository traderRepository,
                          final ItemRepository itemRepository,
                          UserRepository userRepository) {
        this.moduleRepository = moduleRepository;
        this.stationRepository = stationRepository;
        this.requirementRepository = requirementRepository;
        this.traderRepository = traderRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public List<HideoutStation> readAllStations(){
        return stationRepository.findAll();
    }

    public Optional<HideoutStation> readStationById(Integer stationId) {
        return stationRepository.findById(stationId);
    }

    public Set<HideoutStation> readAllStationsForUser(String username) throws Exception {
        var userResult = userRepository.findByUsername(username);
        if(userResult.isEmpty()){
            throw new Exception("User Not found");
        }
        return userResult.get().getHideoutStations();
    }


    public HideoutStationDetailsDTO readStationDetailsByIdForUser(Integer id, String username) throws Exception {
        var userResult = userRepository.findByUsername(username);
        if(userResult.isEmpty()){
            throw new Exception("User not found");
        }
        var stationResult = userResult.get()
                .getHideoutStations().stream()
                .filter(s -> s.getId().equals(id)).findFirst();
        if(stationResult.isEmpty()){
            throw new Exception("Station not found");
        }
        var station = stationResult.get();
        var stationDetailsDTO = new HideoutStationDetailsDTO(station.getId(), station.getName(), station.getFunction());
        stationDetailsDTO.setStationLevels(getStationLevelsForStation(station.getModules()));
        return stationDetailsDTO;
    }

    public void deleteStationForUserById(Integer stationId, String username) throws Exception {
        var userResult = userRepository.findByUsername(username);
        if(userResult.isEmpty()){
            throw new Exception("User not found");
        }
        var user = userResult.get();
        var stationToDelete = user.getHideoutStations().stream().filter(station -> station.getId().equals(stationId)).findFirst();
        if(stationToDelete.isPresent()) {
            var stationLevelTodDelete = stationToDelete.get().getModules()
                    .stream()
                    .sorted((module1, module2) -> module1.getLevel() < module2.getLevel() ? 1 : -1)
                    .findFirst();
            if (stationLevelTodDelete.isPresent()) {
                moduleRepository.save(stationLevelTodDelete.get());
                stationRepository.save(stationToDelete.get());
            }
        }
        userRepository.save(user);
    }

    private Set<StationLevelDTO> getStationLevelsForStation(Set<HideoutModule> modules){
        var stationLevelsDTOs = new HashSet<StationLevelDTO>();
        modules.forEach(module -> {
            var currentModule = new StationLevelDTO(module.getId(), module.getLevel());
            currentModule.setRequiredItems(getRequiredItemsForStationLevel(module.getRequirements()));
            currentModule.setRequiredStations(getRequiredStationsForStationLevel(module.getRequirements()));
            stationLevelsDTOs.add(currentModule);
        });
        return stationLevelsDTOs;
    }

    private Set<RequiredItemDTO> getRequiredStationsForStationLevel(Set <ModuleRequirement> moduleRequirements){
        var requiredStations = new HashSet<RequiredItemDTO>();
        moduleRequirements.stream()
                .filter(mr -> mr.getType().equals("module"))
                .forEach(smr -> {
                    var stationResult = stationRepository.findByName(smr.getName());
                    if(stationResult.isPresent()){
                        var station = stationResult.get();
                        var currentRequiredStation = new RequiredItemDTO(station.getId().toString(), station.getName(), smr.getQuantity());
                        requiredStations.add(currentRequiredStation);
                    }
                });
        return requiredStations;
    }


    private Set<RequiredItemDTO> getRequiredItemsForStationLevel(Set<ModuleRequirement> moduleRequirements){
        var requiredItemsDTOs = new HashSet<RequiredItemDTO>();
        moduleRequirements.stream()
                .filter(mr -> mr.getType().equals("item"))
                .forEach(imr -> {
                    var itemResult = itemRepository.findById(imr.getName());
                    if(itemResult.isPresent()){
                        var currentItem = itemResult.get();
                        requiredItemsDTOs.add(
                                new RequiredItemDTO(currentItem.getId(), currentItem.getName(), imr.getQuantity())
                        );
                    }
                });

        return requiredItemsDTOs;
    }

    public HideoutStation saveHideoutStation(HideoutStationDTO hideoutStation){
        var station = new HideoutStation();
        station.setId(hideoutStation.getId());
        station.setImgSource(hideoutStation.getImgSource());
        station.setFunction(hideoutStation.getFunction());
        station.setName(hideoutStation.getName());
        var result = stationRepository.save(station);
        return result;
    }

    public ModuleRequirement saveModuleRequirement(ModuleRequirementDTO moduleRequirementDTO){
        var moduleRequirement = new ModuleRequirement();
        var traderId = moduleRequirementDTO.getTraderId();
        if(traderId != null){
            if(traderRepository.existsById(traderId)){
                moduleRequirement.setTrader(traderRepository.findById(traderId).get());
            }
        }
        moduleRequirement.setId(moduleRequirementDTO.getId());
        moduleRequirement.setType(moduleRequirementDTO.getType());
        if(moduleRequirementDTO.getName() == null){
            moduleRequirement.setName(moduleRequirement.getTrader().getName());
        }
        else {
            moduleRequirement.setName(moduleRequirementDTO.getName());
        }
        moduleRequirement.setQuantity(moduleRequirementDTO.getQuantity());
        var result = requirementRepository.save(moduleRequirement);
        return result;
    }


    public List<HideoutModule> readAllModules() {
        return moduleRepository.findAll();
    }

    public HideoutModule saveHideoutModule(HideoutModuleDTO hideoutModuleDTO) {
        System.out.println("-----------------------------" + hideoutModuleDTO.getId());
        var hideoutModule = new HideoutModule();
//        hideoutModule.setId(hideoutModuleDTO.getId());
        hideoutModule.setName(hideoutModuleDTO.getModule());
        hideoutModule.setLevel(hideoutModuleDTO.getLevel());
        HideoutStation hideoutStation = null;
        var requirementList = new HashSet<ModuleRequirement>();
        if(stationRepository.existsById(hideoutModuleDTO.getStationId())){
            hideoutStation = stationRepository.findById(hideoutModuleDTO.getStationId()).get();
            hideoutModule.setStation(hideoutStation);
        }
        for (var id : hideoutModuleDTO.getRequirementsIds()){
            if(requirementRepository.existsById(id))
                requirementList.add(requirementRepository.findById(id).get());
        }
        hideoutModule.setRequirements(requirementList);
        var result = moduleRepository.save(hideoutModule);
        hideoutStation.getModules().add(result);
        stationRepository.save(hideoutStation);
        for(var requirement : requirementList){
            var currentRequirement = new ModuleRequirement();
            currentRequirement.setName(requirement.getName());
            currentRequirement.setId(requirement.getId());
            currentRequirement.setQuantity(requirement.getQuantity());
            currentRequirement.setTrader(requirement.getTrader());
            currentRequirement.setType(requirement.getType());
            currentRequirement.setModule(result);
            requirementRepository.save(currentRequirement);
        }
        return result;
    }


}
