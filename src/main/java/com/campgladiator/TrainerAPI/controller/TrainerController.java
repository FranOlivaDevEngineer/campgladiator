package com.campgladiator.TrainerAPI.controller;

import com.campgladiator.TrainerAPI.dto.TrainerDto;
import com.campgladiator.TrainerAPI.exception.EntityNotFoundException;
import com.campgladiator.TrainerAPI.service.TrainerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    TrainerService trainerService;

    @GetMapping("/{trainerId}")
    public ResponseEntity getTrainer(@PathVariable Long trainerId) throws EntityNotFoundException {
        log.info("get Trainer request trainer id= " + trainerId);
        TrainerDto trainerResponse =  trainerService.getTrainerById(trainerId);
        return new ResponseEntity(trainerResponse, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity createTrainer(@RequestBody TrainerDto trainerBody){
        log.info("post Trainer body request = " + trainerBody.toString());
        TrainerDto trainerResponse =  trainerService.createTrainer(trainerBody);
        return new ResponseEntity(trainerResponse, HttpStatus.CREATED);

    }

}
