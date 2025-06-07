package com.lancer.compcon.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
