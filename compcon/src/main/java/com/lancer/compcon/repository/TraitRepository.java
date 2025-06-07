package com.lancer.compcon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lancer.compcon.models.Trait;

// --- Repositório para a entidade Trait ---
// Trait.java (a classe que definimos, se marcada com @Entity e com seu próprio @Id)

/**
 * Repositório para a entidade Trait.
 * Relevante apenas se 'Trait' for gerenciada como uma entidade separada com seu próprio @Id.
 * JpaRepository<Trait, Long> assume que 'Trait' tem uma chave primária do tipo Long.
 * (Como o 'id' gerado automaticamente que comentamos na classe Trait).
 */
@Repository
public interface TraitRepository extends JpaRepository<Trait, Long> {

    // Encontra Traits pelo nome
    List<Trait> findByTraitsName(String traitsName);

    // Se você tivesse uma relação bidirecional de Trait para Frames:
    // List<Trait> findByFrame_FramesId(String framesId);
}