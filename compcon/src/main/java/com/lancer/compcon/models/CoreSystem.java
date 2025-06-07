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
    private String core_systems_name; // Corresponde a core_systems_name: String
    private String active_name;
    private String active_effect;
    private String use;
    private String activation;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // Exemplo de relação JPA
    @JoinColumn(name = "core_system_id_fk") // Chave estrangeira na tabela ActiveSynergy
    private List<ActiveSynergy> active_synergies; // Corresponde ao array de objetos 'active_synergies'

    @ElementCollection // Exemplo de anotação JPA
    private List<String> integrated; // Corresponde a integrated: [String]
}