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
    int perdues;


    public Equips(int equip_id, String nom, String acronim, String ciutat, String estat, String divisio, int guanyades, int perdues) {
        this.equip_id = equip_id;
        this.nom = nom;
        this.acronim = acronim;
        this.ciutat = ciutat;
        this.estat = estat;
        this.divisio = divisio;
        this.guanyades = guanyades;
        this.perdues = perdues;
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

    public int getPerdues() {
        return perdues;
    }

    public void set(String nom, String acronim, String ciutat, String estat, String divisio,int perdues, int guanyades){
        this.nom = nom;
        this.acronim = acronim;
        this.ciutat = ciutat;
        this.estat = estat;
        this.perdues = perdues;
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
                "\n\tPerdues: " + perdues +
                "\n\tGuanyades: " + guanyades;
    }
}
