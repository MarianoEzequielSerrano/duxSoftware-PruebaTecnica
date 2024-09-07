package com.software.dux.challenge.service;

import com.software.dux.challenge.model.Equipo;
import com.software.dux.challenge.repository.iEquipoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class EquipoServiceTest {
    
    @Autowired
    private iEquipoService equipoServ;
    
    @MockBean
    private iEquipoRepository equipoRepo;
    
    @Test
    public void getAllEquiposShouldFoundNotEmptyList(){
        List<Equipo> equipos = List.of( new Equipo (1L, "Barcelona", "La Liga", "España"), 
                                        new Equipo (2L, "Real Madrid", "La Liga", "España"),
                                        new Equipo (3L, "Manchester City", "Premier League", "Inglaterra"));
        
        when(equipoRepo.findAll()).thenReturn(equipos);
        List<Equipo> result = equipoServ.getAll();
        
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
    }
   
    @Test
    public void getAllEquiposShouldFoundEmptyList(){
        List<Equipo> equipos = new ArrayList<>();
        
        when(equipoRepo.findAll()).thenReturn(equipos);
        List<Equipo> result = equipoServ.getAll();
        
        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.size());
    }
    
    @Test
    @DisplayName("Test para obtener un empleado con ID existente")
    public void getEquipoByIdShouldFound(){
        Long id = 1L;
        Equipo equipo = new Equipo (1L, "Barcelona", "La Liga", "España");
        
        when(equipoRepo.findById(id)).thenReturn(Optional.of(equipo));
        Equipo result = equipoServ.getEquipoById(id);
        
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Barcelona", result.getNombre());
        assertEquals("La Liga", result.getLiga());
        assertEquals("España", result.getPais());
    }
    
    @Test
    public void getEquiposByNameShouldFoundNotEmptyList(){
        String filtro = "madrid";
        Equipo equipo1 = new Equipo (1L, "Real Madrid", "La Liga", "España");
        Equipo equipo3 = new Equipo (3L, "Atletico Madrid", "La Liga", "España");
        List<Equipo> equiposMadrid = new ArrayList<>();
        equiposMadrid.add(equipo1);
        equiposMadrid.add(equipo3);
        
        
        when(equipoRepo.findByNombreContainingIgnoreCase(filtro)).thenReturn(equiposMadrid);
        List<Equipo> result = equipoServ.getEquiposByName(filtro);
        
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertTrue(result.get(0).getNombre().toUpperCase().contains(filtro.toUpperCase()));
        assertTrue(result.get(1).getNombre().toUpperCase().contains(filtro.toUpperCase()));
    }
    
    @Test
    public void getEquiposByNameShouldFoundEmptyList(){
        String filtro = "manchester";
        List<Equipo> equiposManchester = new ArrayList<>();
        
        when(equipoRepo.findByNombreContainingIgnoreCase(filtro)).thenReturn(equiposManchester);
        List<Equipo> result = equipoServ.getEquiposByName(filtro);
        
        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.size());
    }
    
    @Test
    public void saveEquipo(){
        Equipo equipo = new Equipo(1L, "Juventus FC", "Serie A", "Italia");
        
        when(equipoRepo.save(equipo)).thenReturn(equipo);
        Equipo result = equipoServ.saveEquipo(equipo);
        
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Juventus FC", result.getNombre());
        assertEquals("Serie A", result.getLiga());
        assertEquals("Italia", result.getPais());
    }
    
    @Test
    public void editEquipoExistente(){
        Long id = 1L;
        Equipo equipo = new Equipo(id, "Borussia Dortmund", "Bundesliga", "Germany");
        Equipo equipoEditado = new Equipo(id, "Borussia Dortmund", "Bundesliga", "Alemania");
        
        when(equipoRepo.findById(id)).thenReturn(Optional.of(equipo));
        when(equipoRepo.save(equipoEditado)).thenReturn(equipoEditado);
        Equipo equipoNotUpdated = equipoServ.getEquipoById(id);
        Equipo equipoUpdated = equipoServ.editEquipo(equipoEditado);
        
        assertNotNull(equipoNotUpdated);
        assertNotNull(equipoUpdated);
        assertEquals(equipoNotUpdated.getId(), equipoUpdated.getId());
        assertEquals(equipoNotUpdated.getNombre(), equipoUpdated.getNombre());
        assertEquals(equipoNotUpdated.getLiga(), equipoUpdated.getLiga());
        assertNotEquals(equipoNotUpdated.getPais(), equipoUpdated.getPais());
        assertEquals("Alemania", equipoUpdated.getPais());
    }
    
    @Test
    public void deleteEquipoExistente(){
        Long id = 1L;
        
        equipoServ.deleteEquipo(id);
        
        ArgumentCaptor<Long> longArgCaptor = ArgumentCaptor.forClass(Long.class);
        verify(equipoRepo).deleteById(anyLong());
        verify(equipoRepo).deleteById(longArgCaptor.capture());
        assertEquals(1L, longArgCaptor.getValue());
    }
  

}
