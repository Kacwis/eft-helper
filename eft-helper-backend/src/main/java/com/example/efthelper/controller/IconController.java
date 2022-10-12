package com.example.efthelper.controller;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/icons")
public class IconController {

    @GetMapping("/{iconId}")
    public ResponseEntity<InputStreamResource> getIconById(@PathVariable String iconId){
        var contentType = MediaType.IMAGE_PNG;
        var resourcePath = "/static/images/" + iconId + ".png";
        var in = getClass().getResourceAsStream(resourcePath);
        return ResponseEntity.ok()
                .contentType(contentType)
                .body(new InputStreamResource(in));
    }
}
