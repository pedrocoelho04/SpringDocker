package com.lancer.compcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lancer.compcon.models.Trait;

@Repository
public interface TraitRepository extends JpaRepository<Trait, Long> {
    List<Trait> findByTraitsName(String traitsName);
    List<Trait> findByFrame_FramesId(String framesId);
}