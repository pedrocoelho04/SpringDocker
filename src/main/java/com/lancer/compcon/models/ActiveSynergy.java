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

    @ElementCollection
    private List<String> locations;
    private String detail;

    //Relação com Core Sytems
    @ManyToOne
    @JoinColumn(name = "core_system_id_fk", insertable = false, updatable = false)
    private CoreSystem coreSystem;

    public ActiveSynergy(List<String> locations, String detail) {
        this.locations = locations;
        this.detail = detail;
    }
}