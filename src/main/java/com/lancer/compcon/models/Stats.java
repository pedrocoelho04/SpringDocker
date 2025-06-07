package com.lancer.compcon.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable 
public class Stats {

    //Status do Frame
    private Double size;
    private Integer structured;
    private Integer stress;
    private Integer armor;
    private Integer hp;
    private Integer evasion;
    private Integer edef;
    private Integer heatcap;
    private Integer repcap;
    private Integer sensor_range;
    private Integer tech_attack;
    private Integer save;
    private Integer speed;
    private Integer sp;
}
