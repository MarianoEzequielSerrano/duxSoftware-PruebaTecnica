package com.software.dux.challenge.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Equipo {
     @Id
     @GeneratedValue(strategy=GenerationType.SEQUENCE)
     private long id;
     private String nombre;
     private String liga;
     private String pais;

    public Equipo() {
    }

    public Equipo(long id, String nombre, String liga, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.liga = liga;
        this.pais = pais;
    }

}
