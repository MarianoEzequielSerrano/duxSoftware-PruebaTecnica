package com.software.dux.challenge.service;

import com.software.dux.challenge.model.Equipo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.software.dux.challenge.repository.iEquipoRepository;

@Service
public class EquipoService implements iEquipoService{
    
    @Autowired
    private iEquipoRepository equipoRepo;

    @Override
    public List<Equipo> getAll() {
        return equipoRepo.findAll();
    }

    @Override
    public Equipo getEquipoById(Long id) {
        return equipoRepo.findById(id).orElse(null);
    }

    @Override
    public List<Equipo> getEquiposByName(String nombre) {
        return equipoRepo.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Equipo saveEquipo(Equipo nuevoEquipo) {
        return equipoRepo.save(nuevoEquipo);
    }

    @Override
    public Equipo editEquipo(Equipo equipoEditado) {
        return this.saveEquipo(equipoEditado);
    }

    @Override
    public void deleteEquipo(Long id) {
        equipoRepo.deleteById(id);
    }
}
