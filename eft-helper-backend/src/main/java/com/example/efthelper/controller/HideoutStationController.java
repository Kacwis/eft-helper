package com.example.efthelper.controller;

import com.example.efthelper.model.HideoutModule;
import com.example.efthelper.model.HideoutStation;
import com.example.efthelper.model.ModuleRequirement;
import com.example.efthelper.model.projection.HideoutModuleDTO;
import com.example.efthelper.model.projection.HideoutStationDTO;
import com.example.efthelper.model.projection.myHideoutProjection.HideoutStationDetailsDTO;
import com.example.efthelper.model.projection.ModuleRequirementDTO;
import com.example.efthelper.service.HideoutService;
import com.example.efthelper.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/hideout")
public class HideoutStationController {

    final private HideoutService service;

    final private JwtUtil jwtUtil;

    public HideoutStationController(final HideoutService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/stations")
    public ResponseEntity<List<HideoutStation>> readAllStations(){
        return ResponseEntity.ok(service.readAllStations());
    }

    @GetMapping("/my/stations")
    public ResponseEntity<Set<HideoutStation>> readALlStationsForUser(HttpServletRequest request) {
        var jwt = jwtUtil.parseJwt(request);
        System.out.println(jwt);
        System.out.println(jwtUtil.getUserNameFromJwtToken(jwt));
        String username = "";
        if (jwt != null && jwtUtil.validateJwtToken(jwt)) {
            username = jwtUtil.getUserNameFromJwtToken(jwt);
        }
        if (username.equals("")) {
            return ResponseEntity.status(423).build();
        }
        Set<HideoutStation> result = null;
        try {
            result = service.readAllStationsForUser(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/stations/{stationId}")
    public ResponseEntity<HideoutStation> readStationById(@PathVariable Integer stationId){
        var result = service.readStationById(stationId);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("/my/stations/details/{stationId}")
    public ResponseEntity<HideoutStationDetailsDTO> readStationDetailsById(@PathVariable Integer stationId, HttpServletRequest request){
        var jwt =  jwtUtil.parseJwt(request);
        String username = "";
        if(jwt != null && jwtUtil.validateJwtToken(jwt)){
            username = jwtUtil.getUserNameFromJwtToken(jwt);
        }
        if(username.equals("")){
            return ResponseEntity.status(423).build();
        }
        HideoutStationDetailsDTO result = null;
        System.out.println(username);
        try {
            result = service.readStationDetailsByIdForUser(stationId, username);
        } catch (Exception e) {
            e.printStackTrace();
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
