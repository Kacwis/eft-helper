package com.example.efthelper.service;

import com.example.efthelper.model.HideoutModule;
import com.example.efthelper.model.HideoutStation;
import com.example.efthelper.model.ModuleRequirement;
import com.example.efthelper.model.projection.*;
import com.example.efthelper.model.repository.*;
import com.example.efthelper.model.repository.jpa.JpaModuleRequirementRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.internal.SessionFactoryBuilderImpl;
import org.springframework.stereotype.Service;

import javax.persistence.Persistence;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HideoutService {

    final private HideoutModuleRepository moduleRepository;

    final private HideoutStationRepository stationRepository;

    final private ModuleRequirementRepository requirementRepository;

    final private TraderRepository traderRepository;

    final private ItemRepository itemRepository;

    public HideoutService(final HideoutModuleRepository moduleRepository,
                          final HideoutStationRepository stationRepository,
                          final ModuleRequirementRepository requirementRepository,
                          final TraderRepository traderRepository,
                          final ItemRepository itemRepository) {
        this.moduleRepository = moduleRepository;
        this.stationRepository = stationRepository;
        this.requirementRepository = requirementRepository;
        this.traderRepository = traderRepository;
        this.itemRepository = itemRepository;
    }

    public List<HideoutStation> readAllStations(){
        return stationRepository.findAll();
    }

    public Optional<HideoutStation> readStationById(Integer stationId) {
        return stationRepository.findById(stationId);
    }

    public HideoutStationDetailsDTO readStationDetailsById(Integer id) {
        var result = stationRepository.findById(id);
        if(result.isEmpty()) {
            return null;
        }
        var station = result.get();
        var requiredItems =
                station.getModules().stream()
                .filter(module -> module.getName().length() == 24)
                .collect(Collectors.toList());
        var requiredItemsDtoSet = new HashSet<RequiredItemDTO>();
        for(var rItem : requiredItems){
            var currentItemOpt = itemRepository.findById(rItem.getName());
            if(currentItemOpt.isPresent()){
                System.out.println(currentItemOpt.get());
                var currentItem = currentItemOpt.get();
                requiredItemsDtoSet.add(new RequiredItemDTO(currentItem.getName(), currentItem.getId()));
            }
        }
        var stationDetailsDTO = new HideoutStationDetailsDTO(station.getId(), station.getName(), station.getFunction());
        requiredItemsDtoSet.forEach(rIDto -> stationDetailsDTO.getRequiredItems().add(rIDto));
        return stationDetailsDTO;
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
