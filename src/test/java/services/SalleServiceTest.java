package services;

import entities.Salle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SalleServiceTest {

    private final SalleService service = new SalleService();

    @Test
    public void testCreateSalle() {
        Salle salle = new Salle();
        salle.setCode("S2");
        salle.setLibelle("Salle Réseau");

        boolean result = service.create(salle);
        Assertions.assertTrue(result, "La création de la salle doit réussir");
    }

    @Test
    public void testCrudSalle() {
        // Create
        Salle salle = new Salle("S-CRUD", "Salle CRUD");
        Assertions.assertTrue(service.create(salle));
        int id = salle.getId();
        Assertions.assertTrue(id > 0, "L'id doit être généré");

        // Read
        Salle lue = service.getById(id);
        Assertions.assertNotNull(lue);
        Assertions.assertEquals("S-CRUD", lue.getCode());

        // Update
        lue.setLibelle("Salle CRUD modifiée");
        Assertions.assertTrue(service.update(lue));
        Assertions.assertEquals("Salle CRUD modifiée", service.getById(id).getLibelle());

        // GetAll
        Assertions.assertFalse(service.getAll().isEmpty());

        // Delete
        Assertions.assertTrue(service.delete(lue));
        Assertions.assertNull(service.getById(id));
    }
}
