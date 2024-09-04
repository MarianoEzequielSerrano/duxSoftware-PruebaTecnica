package com.software.dux.challenge.controller;

import com.software.dux.challenge.model.Equipo;
import com.software.dux.challenge.model.ErrorMensaje;
import com.software.dux.challenge.service.iEquipoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        Equipo e = equipoServ.getEquipoById(id);
        if(e == null){
            ErrorMensaje error = new ErrorMensaje("Equipo no encontrado", 404);
            return new ResponseEntity(error, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(e, HttpStatus.ACCEPTED);
        }
    }
    
    @GetMapping("/equipos/buscar")
    public List<Equipo> getEquiposByName(@RequestParam String nombre){
        return equipoServ.getEquiposByName(nombre);
    }
    
    @PostMapping("/equipos")
    public ResponseEntity<?> createEquipo(@Valid @RequestBody Equipo nuevoEquipo){
        try{
            Equipo equipoGuardado = equipoServ.saveEquipo(nuevoEquipo);
            return new ResponseEntity(equipoGuardado, HttpStatus.CREATED);
        }catch(RuntimeException e){
            throw e;
        }
    }
    
    @PutMapping("/equipos/{id}")
    public ResponseEntity<?> editEquipo(@PathVariable Long id, @Valid @RequestBody Equipo equipoModificado){
        try{
            Equipo equipoUpdate = equipoServ.editEquipo(id, equipoModificado);
            if(equipoUpdate == null){
                ErrorMensaje error = new ErrorMensaje("Equipo no encontrado", 404);
                return new ResponseEntity(error, HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity(equipoUpdate, HttpStatus.OK);
            }
        }catch(RuntimeException e){
            throw e;
        }
    }
    
    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<?> deleteEquipo(@PathVariable Long id){
        Equipo equipoDelete = equipoServ.deleteEquipo(id);
        if(equipoDelete == null){
            ErrorMensaje error = new ErrorMensaje("Equipo no encontrado", 404);
            return new ResponseEntity(error, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
