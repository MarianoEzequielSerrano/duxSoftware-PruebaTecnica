package com.software.dux.challenge.service;

import com.software.dux.challenge.model.Equipo;
import java.util.List;

public interface iEquipoService {
    public List<Equipo> getAll();
    public Equipo getEquipoById(Long id);
    public List<Equipo> getEquiposByName(String nombre);
    public Equipo saveEquipo(Equipo nuevoEquipo);
    public Equipo editEquipo(Long id, Equipo equipoEditado);
    public void deleteEquipo(Long id);
}
