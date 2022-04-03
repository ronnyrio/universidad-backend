package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exception.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    private final CarreraDAO carreraDAO;

    @Autowired
    public CarreraController(CarreraDAO carreraDAO) {
        this.carreraDAO = carreraDAO;
    }

    @GetMapping
    public List<Carrera> obtenerTodos() {
        List<Carrera> carreras = (List<Carrera>) carreraDAO.findAll();
        if (carreras.isEmpty()) {
            throw new BadRequestException("no existe ninguna carrera");
        }
        return carreras;
    }

    @GetMapping("/{codigo}")
    public Carrera obtenerPorId(@PathVariable(value = "codigo", required = false) Integer id) {
        Optional<Carrera> oCarrera = carreraDAO.findById(id);
        if (!oCarrera.isPresent()) {
            throw new BadRequestException(String.format("no existe ningún ID de la carrera finu", id));
        }
        return oCarrera.get();
    }

    @PostMapping
    public Carrera altaCarrera(@RequestBody Carrera carrera) {
        if (carrera.getCantidadAnios() < 0) {
            throw new BadRequestException("El campo cantidad de años no puede ser negativo finu");
        }
        if (carrera.getCantidaMaterias() < 0) {
            throw new BadRequestException("El campo cantidad de materias no puede ser negativo");
        }
        return carreraDAO.save(carrera);

    }

    @PutMapping("/{id}")
    public Carrera actualizarCarrera(@PathVariable Integer id, @RequestBody Carrera carrera) {
        Carrera carreraUpdate = null;
        Optional<Carrera> oCarrera = carreraDAO.findById(id);
        if (!oCarrera.isPresent()) {
            throw new BadRequestException(String.format("no existe ningún ID de la carrera finu", id));
        }
        carreraUpdate = oCarrera.get();
        carreraUpdate.setCantidadAnios(carrera.getCantidadAnios());
        carreraUpdate.setCantidaMaterias(carreraUpdate.getCantidaMaterias());
        return carreraDAO.save(carreraUpdate);
    }

    @DeleteMapping("/{id}")
    public void eliminarCarrera(@PathVariable Integer id){
        carreraDAO.deleteById(id);
    }
}