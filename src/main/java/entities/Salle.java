package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "salles")
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String libelle;

    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Machine> machines = new ArrayList<>();

    public Salle() {}

    public Salle(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public List<Machine> getMachines() { return machines; }
    public void setMachines(List<Machine> machines) { this.machines = machines; }

    @Override
    public String toString() {
        return "Salle{id=" + id + ", code='" + code + "', libelle='" + libelle + "'}";
    }
}
