package services;

import entities.Machine;
import entities.Salle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MachineServiceTest {

    private final SalleService salleService = new SalleService();
    private final MachineService machineService = new MachineService();

    @Test
    public void testCrudMachine() {
        Salle salle = new Salle("S-M", "Salle Machines");
        Assertions.assertTrue(salleService.create(salle));

        Machine m = new Machine("M-TEST", "HP", salle);
        Assertions.assertTrue(machineService.create(m));
        int id = m.getId();

        Machine lue = machineService.getById(id);
        Assertions.assertEquals("HP", lue.getMarque());

        lue.setMarque("Lenovo");
        Assertions.assertTrue(machineService.update(lue));
        Assertions.assertEquals("Lenovo", machineService.getById(id).getMarque());

        Assertions.assertTrue(machineService.delete(lue));
        Assertions.assertNull(machineService.getById(id));

        salleService.delete(salle);
    }

    @Test
    public void testFindByMarqueHQL() {
        Salle salle = new Salle("S-HQL", "Salle HQL");
        salleService.create(salle);
        Machine m = new Machine("M-HQL", "MarqueUniqueHQL", salle);
        machineService.create(m);

        List<Machine> res = machineService.findByMarque("MarqueUniqueHQL");
        Assertions.assertEquals(1, res.size());
        Assertions.assertEquals("M-HQL", res.get(0).getRef());

        Assertions.assertEquals(1, machineService.findBySalleCode("S-HQL").size());

        machineService.delete(m);
        salleService.delete(salle);
    }

    @Test
    public void testTransactionRollback() {
        // Salle nulle -> session.save(null) échoue -> rollback + RuntimeException
        Assertions.assertThrows(RuntimeException.class,
                () -> salleService.createSalleAvecMachines(null, List.of(new Machine())));
    }
}
