package com.lancer.compcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lancer.compcon.models.ActiveSynergy;

// --- Repositório para a entidade ActiveSynergy ---
// ActiveSynergy.java (a classe que definimos, se marcada com @Entity e com seu próprio @Id)

/**
 * Repositório para a entidade ActiveSynergy.
 * Relevante apenas se 'ActiveSynergy' for gerenciada como uma entidade separada com seu próprio @Id.
 * JpaRepository<ActiveSynergy, Long> assume que 'ActiveSynergy' tem uma chave primária do tipo Long.
 */
@Repository
public interface ActiveSynergyRepository extends JpaRepository<ActiveSynergy, Long> {

    // Encontra ActiveSynergies pelo detalhe
    List<ActiveSynergy> findByDetailContaining(String detailKeyword);

    // Se você tivesse uma relação bidirecional de ActiveSynergy para CoreSystem (e CoreSystem fosse uma entidade):
    // List<ActiveSynergy> findByCoreSystem_Id(Long coreSystemId); // Exemplo
}