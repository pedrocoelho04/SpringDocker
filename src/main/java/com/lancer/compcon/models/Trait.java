package com.lancer.compcon.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@NoArgsConstructor
@Entity
public class Trait {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Nome do Traço
    private String traits_name;
    //Descrição
    private String description;

    //Relação com o frame
    @ManyToOne
    @JoinColumn(name = "frames_id_fk", insertable = false, updatable = false) 
    private Frames frame;

    //Criado pro DataSeeder
    public Trait(String traits_name, String description) {
        this.traits_name = traits_name;
        this.description = description;
    }
}
