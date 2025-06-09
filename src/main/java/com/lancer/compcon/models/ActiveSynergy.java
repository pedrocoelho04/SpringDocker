package com.lancer.compcon.models;

// Importe apenas estas classes. List e ElementCollection não são mais necessários aqui.
import javax.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VERSÃO FINAL E CORRIGIDA
 * Esta classe representa uma sinergia ativa.
 * Foi alterada para usar uma String para 'locations' para resolver a limitação do JPA
 * que proíbe coleções aninhadas em componentes embutidos.
 */
@Data
@NoArgsConstructor
@Embeddable // A classe é um componente, não uma entidade principal.
public class ActiveSynergy {
    
    /**
     * O campo 'locations' armazena a lista de locais como um texto simples.
     * Exemplo: "Ataque, Arma de Calor, Explosão 1"
     * ANTERIORMENTE: @ElementCollection private List<String> locations; <-- Isto causava o erro.
     */
    private String locations; 
    
    private String detail;

    /**
     * Construtor para ser usado no DataSeeder.
     * @param locations Uma String contendo os locais, separados por vírgula.
     * @param detail A descrição detalhada da sinergia.
     */
    public ActiveSynergy(String locations, String detail) {
        this.locations = locations;
        this.detail = detail;
    }
}
