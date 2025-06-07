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

    private String traits_name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "frames_id_fk", insertable = false, updatable = false) // Para evitar duplicar a coluna se já definida em Frames
    private Frames frame;
}
