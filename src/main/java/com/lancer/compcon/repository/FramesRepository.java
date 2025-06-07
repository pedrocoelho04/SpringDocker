package com.lancer.compcon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lancer.compcon.models.Frames;

import java.util.List; 
@Repository
public interface FramesRepository extends JpaRepository<Frames, String> {

    List<Frames> findByFramesName(String framesName);
    List<Frames> findByLicensedLevel(Integer licensedLevel);
    List<Frames> findByMechTypeContaining(String mechType);
}