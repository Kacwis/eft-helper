package com.example.efthelper.controller;

import com.example.efthelper.model.HideoutStation;
import com.example.efthelper.model.projection.myHideoutProjection.HideoutStationDetailsDTO;
import com.example.efthelper.model.projection.myHideoutProjection.quest.MyHideoutQuestDTO;
import com.example.efthelper.model.projection.myHideoutProjection.quest.QuestDetailsDTO;
import com.example.efthelper.model.projection.myHideoutProjection.quest.QuestDetailsObjectiveDTO;
import com.example.efthelper.service.HideoutService;
import com.example.efthelper.service.QuestService;
import com.example.efthelper.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/hideout/my")
public class MyHideoutController {

    final private JwtUtil jwtUtil;

    final private HideoutService hideoutService;

    final private QuestService questService;

    public MyHideoutController(final JwtUtil jwtUtil,
                               final HideoutService hideoutService,
                               final QuestService questService) {
        this.jwtUtil = jwtUtil;
        this.hideoutService = hideoutService;
        this.questService = questService;
    }

    @Secured("ROLE_USER")
    @GetMapping("/stations")
    public ResponseEntity<Set<HideoutStation>> readALlStationsForUser(HttpServletRequest request) {
        var username = getUsernameFromToken(request);
        if(username.equals("")){
            return ResponseEntity.badRequest().build();
        }
        Set<HideoutStation> result = null;
        try {
            result = hideoutService.readAllStationsForUser(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @Secured("ROLE_USER")
    @GetMapping("stations/details/{stationId}")
    public ResponseEntity<HideoutStationDetailsDTO> readStationDetailsById(@PathVariable Integer stationId, HttpServletRequest request){
        var username = getUsernameFromToken(request);
        if(username.equals("")){
            return ResponseEntity.badRequest().build();
        }
        HideoutStationDetailsDTO result = null;
        try {
            result = hideoutService.readStationDetailsByIdForUser(stationId, username);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @Secured("ROLE_USER")
    @GetMapping("/quests/details/{questId}")
    public ResponseEntity<QuestDetailsDTO> readQuestDetailsById(@PathVariable Integer questId, HttpServletRequest request){
        var questDetails = questService.getQuestDetailsForUser(questId);
        return ResponseEntity.ok(questDetails);
    }

    @Secured("ROLE_USER")
    @GetMapping("/quests")
    public ResponseEntity<Set<MyHideoutQuestDTO>> readAllQuestsForUser(HttpServletRequest request){
        var username = getUsernameFromToken(request);
        if(username.equals("")){
            return ResponseEntity.badRequest().build();
        }
        Set<MyHideoutQuestDTO> questDTOSet = null;
        try{
            questDTOSet = questService.getAllQuestsForUser(username);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(questDTOSet);
    }   

    private String getUsernameFromToken(HttpServletRequest request){
        var jwt =  jwtUtil.parseJwt(request);
        String username = "";
        if(jwt != null && jwtUtil.validateJwtToken(jwt)){
            username = jwtUtil.getUserNameFromJwtToken(jwt);
        }
        return username;
    }
}
