package com.lancer.compcon.models;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Frames {

    @Id // Exemplo se frames_id for a chave primária no JPA
    private String frames_id; // Corresponde a frames_id: String

    private Integer licensed_level; // Corresponde a licensed_level: Number
    private String source; // Corresponde a source: String
    private String frames_name; // Corresponde a frames_name: String

    @ElementCollection // Exemplo se for usar JPA para uma coleção de tipos básicos
    private List<String> mech_type; // Corresponde a mech_type: [String]

    private Integer y_pos; // Corresponde a y_pos: Number (assumindo inteiro)
    private String description; // Corresponde a description: String

    @ElementCollection // Exemplo JPA
    private List<String> mounts; // Corresponde a mounts: [String]

    @Embedded // Exemplo JPA para um objeto embutido
    private Stats stats; // Corresponde a stats: { ... }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // Exemplo JPA para uma relação
    @JoinColumn(name = "frames_id") // Necessário se Traits for uma entidade separada
    private List<Trait> traits; // Corresponde a traits: [{ ... }]

    @Embedded // Exemplo JPA
    private CoreSystem core_systems; // Corresponde a core_systems: { ... }

    private String image_url; // Corresponde a image_url: String
    private String licensed_id; // Corresponde a licensed_id: String
}