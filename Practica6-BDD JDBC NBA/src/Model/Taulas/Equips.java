package Model.Taulas;
import Model.DAO.EquipsDAODB;

public class Equips extends EquipsDAODB {
    int equip_id;
    String nom;
    String acronim;
    String ciutat;
    String estat;
    String divisio;
    int guanyades;
    int perdudes;


    public Equips(int equip_id, String nom, String acronim, String ciutat, String estat, String divisio, int guanyades, int perdudes) {
        this.equip_id = equip_id;
        this.nom = nom;
        this.acronim = acronim;
        this.ciutat = ciutat;
        this.estat = estat;
        this.divisio = divisio;
        this.guanyades = guanyades;
        this.perdudes = perdudes;
    }

    public int getEquip_id() {
        return equip_id;
    }

    public String getNom() {
        return nom;
    }

    public String getAcronim() {
        return acronim;
    }

    public String getCiutat() {
        return ciutat;
    }

    public String getEstat() {
        return estat;
    }

    public String getDivisio() {
        return divisio;
    }

    public int getGuanyades() {
        return guanyades;
    }

    public int getPerdudes() {
        return perdudes;
    }

    public void set(String nom, String acronim, String ciutat, String estat, String divisio,int perdudes, int guanyades){
        this.nom = nom;
        this.acronim = acronim;
        this.ciutat = ciutat;
        this.estat = estat;
        this.perdudes = perdudes;
        this.divisio = divisio;
        this.guanyades = guanyades;
    }

    public String toString() {
        return "\nEquip: " +
                "\n\tID: " + equip_id +
                "\n\tNom: " + nom +
                "\n\tAcronim: " + acronim  +
                "\n\tCiutat: " + ciutat +
                "\n\tEstat: " + estat +
                "\n\tDivisio: " + divisio +
                "\n\tPerdues: " + perdudes +
                "\n\tGuanyades: " + guanyades;
    }
}
