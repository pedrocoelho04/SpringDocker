package com.lancer.compcon.models;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Data
@NoArgsConstructor
@Entity
    //Classe princial
public class Frames {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String frames_id;

    private Integer licensed_level;

    //Nome da Fabricadora
    private String source;
    private String frames_name;

    //Tipo de Mecha
    @ElementCollection
    private List<String> mech_type;

    private Integer y_pos;
    private String description;

    //Encaixes
    @ElementCollection
    private List<String> mounts;

    //Classe com dados de Status do Frame
    @Embedded
    private Stats stats;
    
    //Relação com Traits, possui varios Traits
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "frames_id")
    private List<Trait> traits;

    //Classe com dados do Core System do Frame
    @Embedded
    private CoreSystem core_systems;

    private String image_url; 
    private String licensed_id; 
}