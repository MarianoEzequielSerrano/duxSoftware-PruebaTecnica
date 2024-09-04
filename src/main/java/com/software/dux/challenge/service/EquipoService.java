package com.software.dux.challenge.service;

import com.software.dux.challenge.model.Equipo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getEquipoById(Long id) {
        Equipo e = equipoRepo.findById(id).orElse(null);
        if(e == null){
            ErrorMensaje error = new ErrorMensaje("Equipo no encontrado", 404);
            return new ResponseEntity(error, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(e, HttpStatus.ACCEPTED);
        }
    }

    @Override
    public List<Equipo> getEquiposByName(String nombre) {
        return equipoRepo.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Equipo saveEquipo(Equipo nuevoEquipo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Equipo editEquipo(Long id, String nuevoNombre, String nuevaLiga, String nuevoPais) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteEquipo(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static class ErrorMensaje{
        private String mensaje;
        private int codigo;
        
        public ErrorMensaje(String mensaje, int codigo){
            this.mensaje = mensaje;
            this.codigo = codigo;
        }
        
        public String getMensaje(){
            return mensaje;
        }
        public void setMensaje(String mensaje){
            this.mensaje = mensaje;
        }
        
        public int getCodigo(){
            return codigo;
        }
        
        public void setCodigo(int codigo){
            this.codigo = codigo;
        }
    }
   
}
