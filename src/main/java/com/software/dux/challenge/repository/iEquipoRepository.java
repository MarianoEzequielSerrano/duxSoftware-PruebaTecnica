package com.software.dux.challenge.repository;

import com.software.dux.challenge.model.Equipo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iEquipoRepository extends JpaRepository<Equipo, Long>{
    
    List<Equipo> findByNombreContainingIgnoreCase(String nombre);
}
