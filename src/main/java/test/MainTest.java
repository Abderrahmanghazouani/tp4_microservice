package test;

import entities.Machine;
import entities.Salle;
import services.MachineService;
import services.SalleService;

public class MainTest {
    public static void main(String[] args) {
        SalleService salleService = new SalleService();
        MachineService machineService = new MachineService();

        Salle salleA = new Salle();
        salleA.setCode("S1");
        salleA.setLibelle("Salle Informatique");

        salleService.create(salleA);

        Machine machine = new Machine();
        machine.setRef("M-101");
        machine.setMarque("Dell");
        machine.setSalle(salleA);

        machineService.create(machine);

        System.out.println("Données insérées avec succès !");

        // Étape 10 : requête HQL
        System.out.println("Machines Dell : " + machineService.findByMarque("Dell"));

        util.HibernateUtil.getSessionFactory().close();
    }
}
