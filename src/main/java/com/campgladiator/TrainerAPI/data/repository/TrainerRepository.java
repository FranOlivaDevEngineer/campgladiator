package com.campgladiator.TrainerAPI.data.repository;

import com.campgladiator.TrainerAPI.data.model.Trainer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TrainerRepository extends CrudRepository<Trainer,Long>
{
    Optional<Trainer> findById(@Param("id") String id);
}
