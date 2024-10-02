import dao.BD;
import dao.impl.DentistDaoH2;
import model.Dentist;
import service.DentistService;

public class Main {
    public static void main(String[] args) {

        DentistService dentistService = new DentistService(new DentistDaoH2());

//        Create tables
        BD.createTables();

//        Create new dentist
        Dentist dentist1 = new Dentist(1334455, "Jorge", "Perez");
        Dentist dentist2 = new Dentist(1886544, "Pedro", "Pereira");
        Dentist dentist3 = new Dentist(1545757, "Luis", "Prado");

//        Save dentist DB
        dentistService.save(dentist1);
        dentistService.save(dentist2);
        dentistService.save(dentist3);

//        Find dentist by id
        dentistService.findById(1);
        dentistService.findById(2);
        dentistService.findById(3);

//        Update dentist
        dentist1.setRegistration(4332222);
        dentist1.setName("Andres");
        dentist1.setLastname("Rojas");
        dentistService.update(dentist1);
        System.out.println("User Id: " + dentist1.getId());
        System.out.println("Registration Update: " + dentist1.getRegistration());
        System.out.println("Name Update: " + dentist1.getName());
        System.out.println("Lastname Update: " + dentist1.getLastname());

//        Delete dentist

        dentistService.delete(2);


//        List Dentist
        dentistService.findAll();


    }
}
