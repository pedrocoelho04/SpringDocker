
package com.lancer.compcon.models;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable 
public class CoreSystem {
    private String core_systems_name;
    private String active_name;
    private String active_effect;
    private String use;
    private String activation;

    @ElementCollection 
    private List<ActiveSynergy> active_synergies;
    
    @ElementCollection
    private List<String> integrated;
}
