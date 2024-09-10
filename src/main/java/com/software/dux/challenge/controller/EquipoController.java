package com.software.dux.challenge.controller;

import com.software.dux.challenge.model.Equipo;
import com.software.dux.challenge.model.ErrorMensaje;
import com.software.dux.challenge.service.iEquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Equipos", description = "API para la gestión de equipos.")

@RestController
public class EquipoController {
    
    @Autowired  
    private iEquipoService equipoServ;
    
    @Operation(summary = "Lista todos los equipos.", description = "Lista todos los equipos.")
    @ApiResponse(responseCode = "200", description = "Equipos listados.")
    @GetMapping("/equipos")
    public List<Equipo> getEquipos(){
        return equipoServ.getAll();
    }
    
    @Operation(summary = "Obtiene equipo por su ID.", description = "Obtiene equipo por su ID.")
    @ApiResponse(responseCode = "200", description = "Equipo recuperado por su ID.")
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
    
    @Operation(summary = "Lista equipos filtrados por nombre.", description = "Lista equipos filtrados por nombre.")
    @ApiResponse(responseCode = "200", description = "Equipos filtrados por nombre.")
    @GetMapping("/equipos/buscar")
    public List<Equipo> getEquiposByName(@RequestParam String nombre){
        return equipoServ.getEquiposByName(nombre);
    }
    
    @Operation(summary = "Crea un equipo.", description = "Crea un equipo.")
    @ApiResponse(responseCode = "200", description = "Equipos creado.")
    @PostMapping("/equipos")
    public ResponseEntity<?> createEquipo(@Valid @RequestBody Equipo nuevoEquipo){
        try{
            Equipo equipoGuardado = equipoServ.saveEquipo(nuevoEquipo);
            return new ResponseEntity(equipoGuardado, HttpStatus.CREATED);
        }catch(RuntimeException e){
            throw e;
        }
    }
    
    @Operation(summary = "Modifica un equipo.", description = "Modifica un equipo.")
    @ApiResponse(responseCode = "200", description = "Equipos modificado.")
    @PutMapping("/equipos/{id}")
    public ResponseEntity<?> editEquipo(@PathVariable Long id, @Valid @RequestBody Equipo equipoModificado){
        try{
            Equipo equipo = equipoServ.getEquipoById(id);
            if(equipo == null){
                ErrorMensaje error = new ErrorMensaje("Equipo no encontrado", 404);
                return new ResponseEntity(error, HttpStatus.NOT_FOUND);
            }else{
                equipo.setNombre(equipoModificado.getNombre());
                equipo.setLiga(equipoModificado.getLiga());
                equipo.setPais(equipoModificado.getPais());
                Equipo equipoActualizado = equipoServ.editEquipo(equipo);
   
                return new ResponseEntity(equipoActualizado, HttpStatus.OK);
            }
        }catch(RuntimeException e){
            throw e;
        }
    }
    
    @Operation(summary = "Elimina un equipo.", description = "Elimina un equipo.")
    @ApiResponse(responseCode = "200", description = "Equipos eliminado.")
    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<?> deleteEquipo(@PathVariable Long id){
        Equipo equipoDelete = equipoServ.getEquipoById(id);
        if(equipoDelete == null){
            ErrorMensaje error = new ErrorMensaje("Equipo no encontrado", 404);
            return new ResponseEntity(error, HttpStatus.NOT_FOUND);
        }else{
            equipoServ.deleteEquipo(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
