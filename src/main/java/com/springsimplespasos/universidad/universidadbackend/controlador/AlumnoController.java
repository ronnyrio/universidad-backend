package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exception.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Alumno;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController extends PersonaController {

    private final CarreraDAO carreraDAO;

    @Autowired
    public AlumnoController(@Qualifier("alumnoDAOImpl") PersonaDAO alumnoDao, CarreraDAO carreraDAO) {
        super(alumnoDao);
        nombreEntidad = "Alumno";
        this.carreraDAO = carreraDAO;
    }

    /* @GetMapping
     public List<Persona> obtenerTodos() {
         List<Persona> alumnos = (List<Persona>) alumnoDao.findAll();
         if (alumnos.isEmpty()) {
             throw new BadRequestException("No existen Alumnos");
         }
         return alumnos;
     }

     @GetMapping("/{id}")
     public Persona obtenerAlumnoPorId(@PathVariable(required = false) Integer id) {
         Optional<Persona> oAlumno = alumnoDao.findById(id);
         if (!oAlumno.isPresent()) {
             throw new BadRequestException(String.format("Alumno con ID %id no existe finu", id));
         }
         return oAlumno.get();
     }

     @PostMapping
     public Persona altaAlumno(@RequestBody Persona alumno) {
         return alumnoDao.save(alumno);
     }
 */
    @PutMapping("/{id}")
    public Persona actualizarAlumno(@PathVariable Integer id, @RequestBody Persona alumno) {
        Persona alumnoUpdate = null;
        Optional<Persona> oAlumno = service.findById(id);
        if (!oAlumno.isPresent()) {
            throw new BadRequestException(String.format("no existe ningún ID del alumno finu", id));
        }
        alumnoUpdate = oAlumno.get();
        alumnoUpdate.setNombre(alumno.getNombre());
        alumnoUpdate.setApellido(alumno.getApellido());
        alumnoUpdate.setDireccion(alumno.getDireccion());
        return service.save(alumnoUpdate);
    }

    /* @DeleteMapping("/{id}")
     public void eliminarAlumno(@PathVariable Integer id) {
         alumnoDao.deletedById(id);
     }*/

    @PutMapping("/{idAlumno}/carrera/{idCarrera}")

    public Persona asignarCarreraAlumno(@PathVariable Integer idAlumno, @PathVariable Integer idCarrera) {
        Optional<Persona> oAlumno = service.findById(idAlumno);
        if (!oAlumno.isPresent()) {
            throw new BadRequestException(String.format("no existe ningún ID del alumno finu", idAlumno));
        }

        Optional<Carrera> oCarrera = carreraDAO.findById(idCarrera);
        if (!oCarrera.isPresent()) {
            throw new BadRequestException(String.format("no existe ningún ID de la carrera finu", idCarrera));
        }
        Persona alumno = oAlumno.get();
        Carrera carrera = oCarrera.get();
        ((Alumno) alumno).setCarrera(carrera);
        return service.save(alumno);
    }

}
