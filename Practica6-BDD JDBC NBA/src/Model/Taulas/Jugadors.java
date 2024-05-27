package Model.Taulas;

import Model.DAO.JugadorsDAODB;

public class Jugadors extends JugadorsDAODB {
    int jugador_id;
    String nom;
    String cognom;
    String dataNaixement;
    float alcada;
    float pes;
    String dorsal;
    String posicio;
    int equip_id;

    public Jugadors(int id, String nom, String cognom, String data_naixement, float altura, float pes, String dorsal, String posicio, int equip_id) {
        this.jugador_id = id;
        this.nom = nom;
        this.cognom = cognom;
        this.dataNaixement = data_naixement;
        this.alcada = altura;
        this.pes = pes;
        this.dorsal = dorsal;
        this.posicio = posicio;
        this.equip_id = equip_id;
    }

    public int getJugador_id() {
        return jugador_id;
    }

    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return cognom;
    }

    public String getDataNaixement() {
        return dataNaixement;
    }

    public float getAlcada() {
        return alcada;
    }

    public float getPes() {
        return pes;
    }

    public String getDorsal() {
        return dorsal;
    }

    public String getPosicio() {
        return posicio;
    }

    public int getEquip_id() {
        return equip_id;
    }

    public void set(String nom, String cognom, String data_naixement, float altura, float pes, String dorsal, String posicio, int equip_id){
        this.nom = nom;
        this.cognom = cognom;
        this.dataNaixement = data_naixement;
        this.alcada = altura;
        this.pes = pes;
        this.dorsal = dorsal;
        this.posicio = posicio;
        this.equip_id = equip_id;
    }

    @Override
    public String toString() {
        return "\nJugador: " +
                "\n\tID: " + jugador_id +
                "\n\tNom: " + nom +
                "\n\tData_naixement: " + dataNaixement +
                "\n\tAltura: " + alcada +
                "\n\tPes: " + pes +
                "\n\tDorsal: " + dorsal +
                "\n\tPosicio: " + posicio+
                "\n\tEquip_id: " + equip_id;
    }
}
