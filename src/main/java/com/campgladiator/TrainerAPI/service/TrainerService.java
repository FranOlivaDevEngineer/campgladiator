package com.campgladiator.TrainerAPI.service;

import com.campgladiator.TrainerAPI.data.model.Trainer;
import com.campgladiator.TrainerAPI.data.repository.TrainerRepository;
import com.campgladiator.TrainerAPI.dto.TrainerDto;
import com.campgladiator.TrainerAPI.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

@Slf4j
@Service
public class TrainerService {

    @Autowired
    TrainerRepository trainerRepository;

    public TrainerDto getTrainerById(Long trainerId) throws EntityNotFoundException {
        log.info("Repository trainer id= " + trainerId);
        Optional<Trainer> trainerFromDB = Optional.ofNullable(trainerRepository.findById(trainerId).orElseThrow(() -> new EntityNotFoundException(HttpStatus.NOT_FOUND, "Get trainer user id not found")));
        return createTrainerDtoBuilder(trainerFromDB.get()).build();
    }

    public TrainerDto createTrainer(TrainerDto trainerBody) {

        final Trainer trainer = createTrainerBuilder(trainerBody).build();
        Trainer trainerEntity = trainerRepository.save(trainer);
        return  createTrainerDtoBuilder(trainerEntity).build();
    }

    private Trainer.TrainerBuilder createTrainerBuilder(TrainerDto trainerDto) {
        return Trainer.builder()
                .id(trainerDto.getId())
                .firstName(trainerDto.getFirstName())
                .lastName(trainerDto.getLastName())
                .email(trainerDto.getEmail())
                .phone(trainerDto.getPhone());

    }
    private TrainerDto.TrainerDtoBuilder createTrainerDtoBuilder(Trainer trainer){
        return TrainerDto.builder()
                .id(trainer.getId())
                .firstName(trainer.getFirstName())
                .lastName(trainer.getLastName())
                .email(trainer.getEmail())
                .phone(trainer.getPhone());
    }
}
