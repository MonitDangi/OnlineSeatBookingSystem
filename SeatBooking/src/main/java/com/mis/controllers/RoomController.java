package com.mis.controllers;

import com.mis.customclasses.Custom;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {
    @PostMapping("/addNewRoom")
    public ResponseEntity<String> addNewRoom(Custom custom){

    }
}
