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
    public Equipo editEquipo(Long id, Equipo equipoEditado) {
        Equipo equipoUpdate = this.getEquipoById(id);
        if(equipoUpdate != null){
            equipoUpdate.setNombre(equipoEditado.getNombre());
            equipoUpdate.setLiga(equipoEditado.getLiga());
            equipoUpdate.setPais(equipoEditado.getPais());
            return this.saveEquipo(equipoUpdate);
        }else{
            return equipoUpdate;
        }
    }

    @Override
    public Equipo deleteEquipo(Long id) {
        Equipo equipoDelete = this.getEquipoById(id);
        if(equipoDelete != null){
            equipoRepo.deleteById(id);
        }

        return equipoDelete;
    }

}
