package com.lancer.compcon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lancer.compcon.models.Frames;

import java.util.List; // Exemplo para métodos de consulta personalizados

// --- Repositório para a entidade Frames ---
// Frames.java (a classe que definimos anteriormente, marcada com @Entity)

/**
 * Repositório para a entidade Frames.
 * JpaRepository<Frames, String> significa que este repositório gerencia
 * entidades 'Frames' e o tipo da chave primária (@Id) de 'Frames' é String.
 * (Assumindo que 'frames_id' é a chave primária e é do tipo String).
 * Se o ID fosse Long, seria JpaRepository<Frames, Long>.
 */
@Repository
public interface FramesRepository extends JpaRepository<Frames, String> {

    // Spring Data JPA cria automaticamente implementações para métodos CRUD básicos
    // como save(), findById(), findAll(), deleteById(), etc.

    // Você pode adicionar métodos de consulta personalizados aqui.
    // Spring Data JPA os implementará automaticamente com base no nome do método.
    // Exemplos:

    // Encontra Frames pelo nome do frame
    List<Frames> findByFramesName(String framesName);

    // Encontra Frames por um nível de licença específico
    List<Frames> findByLicensedLevel(Integer licensedLevel);

    // Encontra Frames que contêm um determinado tipo de 'mech_type'
    // (Requereria uma consulta mais complexa ou uma modelagem diferente se 'mech_type'
    // for uma coleção de elementos e você quiser pesquisar dentro dela de forma eficiente
    // com JPQL ou Criteria API, mas para um simples 'findBy' isso é um exemplo)
    List<Frames> findByMechTypeContaining(String mechType); // Nome pode variar dependendo da estratégia

    // Você também pode usar @Query para consultas JPQL ou SQL nativas mais complexas:
    // @Query("SELECT f FROM Frames f WHERE f.stats.hp > :minHp")
    // List<Frames> findFramesWithHpGreaterThan(@Param("minHp") Integer minHp);
}