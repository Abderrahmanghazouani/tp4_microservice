package services;

import dao.IDao;
import dao.MachineDao;
import entities.Machine;

import java.util.List;

public class MachineService implements IDao<Machine> {
    private final MachineDao dao = new MachineDao();

    @Override
    public boolean create(Machine o) {
        return dao.create(o);
    }

    @Override
    public boolean delete(Machine o) {
        return dao.delete(o);
    }

    @Override
    public boolean update(Machine o) {
        return dao.update(o);
    }

    @Override
    public Machine getById(int id) {
        return dao.getById(id);
    }

    @Override
    public List<Machine> getAll() {
        return dao.getAll();
    }

    public List<Machine> findByMarque(String marque) {
        return dao.findByMarque(marque);
    }

    public List<Machine> findBySalleCode(String code) {
        return dao.findBySalleCode(code);
    }
}
