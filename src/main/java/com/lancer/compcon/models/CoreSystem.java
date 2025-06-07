package com.lancer.compcon.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CoreSystem {
    //Dados
    private String core_systems_name;
    private String active_name;
    private String active_effect;
    //Tipo de Uso
    private String use;
    //Caso de Uso
    private String activation;

    //Relação com Active Synergies, possui varias relações
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) 
    @JoinColumn(name = "core_system_id_fk")
    private List<ActiveSynergy> active_synergies;

    @ElementCollection
    private List<String> integrated;
}