package test;

import dao.BD;
import dao.impl.DentistDaoH2;
import model.Dentist;
import org.junit.jupiter.api.Test;
import service.DentistService;

import static org.junit.jupiter.api.Assertions.*;

class DentistServiceTest {
    //    Instance un Service Dentist From Dentist DAO H2
    DentistService dentistService = new DentistService(new DentistDaoH2());

    //    Test Method Save Dentist DB
    @Test
    void save() {
//        Create Tables
        BD.createTables();
//        Instance Dentist and Set Values
        Dentist dentistInsert = new Dentist();
        dentistInsert.setRegistration(334);
        dentistInsert.setName("Loreto");
        dentistInsert.setLastname("Morales");

//        Call Method Save from dentistService
        dentistService.save(dentistInsert);
//        Test ID Dentist is Not Null
        assertNotNull(dentistInsert.getId());

    }

    @Test
    void findById() {
        Dentist dentistById = dentistService.findById(1);
        assertNotNull(dentistById.getId());
    }

    @Test
    void update() {
        Dentist dentistUpdate = new Dentist();
        dentistUpdate.setRegistration(224);
        dentistUpdate.setName("Isabel");
        dentistUpdate.setLastname("Morales");
        dentistUpdate.setId(1);
        dentistService.update(dentistUpdate);

        assertEquals("Isabel", dentistUpdate.getName());
    }

    @Test
    void findAll() {
        assertFalse(dentistService.findAll().isEmpty());
    }

    @Test
    void delete() {
        dentistService.delete(1);
        assertNull(dentistService.findById(1));
    }

}

