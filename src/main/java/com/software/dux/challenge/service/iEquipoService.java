package com.software.dux.challenge.service;

import com.software.dux.challenge.model.Equipo;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface iEquipoService {
    public List<Equipo> getAll();
    public ResponseEntity<?> getEquipoById(Long id);
    public List<Equipo> getEquiposByName(String nombre);
    public Equipo saveEquipo(Equipo nuevoEquipo);
    public Equipo editEquipo(Long id, String nuevoNombre, String nuevaLiga, String nuevoPais);
    public void deleteEquipo(Long id);
}
