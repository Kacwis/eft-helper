package com.example.efthelper.controller;

import com.example.efthelper.model.HideoutModule;
import com.example.efthelper.model.HideoutStation;
import com.example.efthelper.model.Item;
import com.example.efthelper.model.ModuleRequirement;
import com.example.efthelper.model.projection.HideoutModuleDTO;
import com.example.efthelper.model.projection.HideoutStationDTO;
import com.example.efthelper.model.projection.HideoutStationDetailsDTO;
import com.example.efthelper.model.projection.ModuleRequirementDTO;
import com.example.efthelper.model.repository.HideoutStationRepository;
import com.example.efthelper.service.HideoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/hideout")
public class HideoutStationController {

    final private HideoutService service;


    public HideoutStationController(final HideoutService service) {
        this.service = service;
    }

    @GetMapping("/stations")
    public ResponseEntity<List<HideoutStation>> readAllStations(){
        return ResponseEntity.ok(service.readAllStations());
    }

    @GetMapping("/stations/{stationId}")
    public ResponseEntity<HideoutStation> readStationById(@PathVariable Integer stationId){
        var result = service.readStationById(stationId);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("/stations/details/{stationId}")
    public ResponseEntity<HideoutStationDetailsDTO> readStationDetailsById(@PathVariable Integer stationId){
        var result = service.readStationDetailsById(stationId);
        if(result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/stations")
    public ResponseEntity<HideoutStation> saveStation(@RequestBody HideoutStationDTO station){
        var result = service.saveHideoutStation(station);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @GetMapping("/modules")
    public ResponseEntity<List<HideoutModule>> readAllModules(){
        return ResponseEntity.ok(service.readAllModules());
    }

    @PostMapping("/modules")
    public ResponseEntity<HideoutModule> saveHideoutModule(@RequestBody HideoutModuleDTO hideoutModuleDTO){
        var result = service.saveHideoutModule(hideoutModuleDTO);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PostMapping("/modules/requirements")
    public ResponseEntity<ModuleRequirement> saveModuleRequirement(@RequestBody ModuleRequirementDTO moduleRequirement){
        var result = service.saveModuleRequirement(moduleRequirement);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
}
