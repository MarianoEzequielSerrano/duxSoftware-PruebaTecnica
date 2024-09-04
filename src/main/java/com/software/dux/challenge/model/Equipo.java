package com.software.dux.challenge.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Equipo {
     @Id
     @GeneratedValue(strategy=GenerationType.SEQUENCE)
     private long id;
     @NotNull @NotBlank
     private String nombre;
     @NotNull @NotBlank
     private String liga;
     @NotNull @NotBlank
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
