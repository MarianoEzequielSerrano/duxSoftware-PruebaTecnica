package com.software.dux.challenge.controller;

import com.software.dux.challenge.model.Equipo;
import com.software.dux.challenge.service.iEquipoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquipoController {
    
    @Autowired  
    private iEquipoService equipoServ;
    
    @GetMapping("/equipos")
    public List<Equipo> getEquipos(){
        return equipoServ.getAll();
    }
    
    @GetMapping("/equipos/{id}")
    public ResponseEntity<?> getEquipoById(@PathVariable Long id){
        return equipoServ.getEquipoById(id);
    }
    
    @GetMapping("/equipos/buscar")
    public List<Equipo> getEquiposByName(@RequestParam String nombre){
        return equipoServ.getEquiposByName(nombre);
    }
}
