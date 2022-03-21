package com.springsimplespasos.universidad.universidadbackend;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Alumno;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Dirección;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AlumnoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UniversidadBackendApplication {

    @Autowired
    private AlumnoDao servicio;


    public static void main(String[] args) {
        String[] BeanDefinitionNames = SpringApplication.run(UniversidadBackendApplication.class, args).getBeanDefinitionNames();

     /*for (String str : BeanDefinitionNames) {
            System.out.println(str);
        }*/
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            Persona alumno = new Alumno(null, "Raul", "Perez", "09419736",
                    new Dirección("calle falete", "123", "33997", "2", "2º", "Oviedo"));
            Persona save = servicio.save(alumno);
            System.out.println(save.toString());
        };
    }
}
