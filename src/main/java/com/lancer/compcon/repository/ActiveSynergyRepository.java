package com.lancer.compcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lancer.compcon.models.ActiveSynergy;

@Repository
public interface ActiveSynergyRepository extends JpaRepository<ActiveSynergy, Long> {
    List<ActiveSynergy> findByDetailContaining(String detailKeyword);
    List<ActiveSynergy> findByCoreSystem_Id(Long coreSystemId);
}