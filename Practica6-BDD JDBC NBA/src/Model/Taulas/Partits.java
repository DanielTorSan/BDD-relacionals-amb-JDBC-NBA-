package Model.Taulas;

import Model.DAO.PartitsDAODB;

public class Partits extends PartitsDAODB {
    int partit_id;
    int equip_id;
    String data_partit;
    String matx;
    String resultat;

    public Partits(int partit_id, int equip_id, String data_partit, String matx, String resultat) {
        this.partit_id = partit_id;
        this.equip_id = equip_id;
        this.data_partit = data_partit;
        this.matx = matx;
        this.resultat = resultat;
    }

    public int getPartit_id() {
        return partit_id;
    }

    public int getEquip_id() {
        return equip_id;
    }

    public String getData_partit() {
        return data_partit;
    }

    public String getMatx() {
        return matx;
    }

    public String getResultat() {
        return resultat;
    }
    public void set(int equip_id, String data_partit, String matx, String resultat){
        this.equip_id = equip_id;
        this.data_partit = data_partit;
        this.matx = matx;
        this.resultat = resultat;
    }

    @Override
    public String toString() {
        return "\nJugador: " +
                "\n\tID: " + partit_id +
                "\n\tEquip_id: " + equip_id +
                "\n\tData_partit: " + data_partit  +
                "\n\tMatx: " + matx +
                "\n\tResultat: " + resultat;
    }
}
