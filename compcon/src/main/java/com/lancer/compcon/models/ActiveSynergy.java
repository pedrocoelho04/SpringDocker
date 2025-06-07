package com.lancer.compcon.models;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity 
public class ActiveSynergy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection // Exemplo de anotação JPA
    private List<String> locations; // Corresponde a locations: [String]
    private String detail; // Corresponde a detail: String

    // Se estiver usando JPA e ActiveSynergy for uma entidade relacionada a CoreSystem:
    @ManyToOne
    @JoinColumn(name = "core_system_id_fk", insertable = false, updatable = false)
    private CoreSystem coreSystem;
}