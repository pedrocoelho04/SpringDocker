package com.lancer.compcon.models;

import javax.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable // A classe é um componente, não uma entidade principal.
public class ActiveSynergy {
    
    /**
     * O campo 'locations' armazena a lista de locais como um texto simples.
     * Exemplo: "Ataque, Arma de Calor, Explosão 1"
     * Antes nao funcionava com a anotação de @Collections por limitação de dependencia
     */
    private String locations; 
    private String detail;

    // Criado pro DataSeeder
    public ActiveSynergy(String locations, String detail) {
        this.locations = locations;
        this.detail = detail;
    }
}
