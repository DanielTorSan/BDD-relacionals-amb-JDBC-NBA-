package Model.Taulas;

public class Estadistiques_jugadors {
    int jugador_id;
    int partit_id;
    float minuts_jugats;
    int punts;
    int tirs_anotats;
    int tirs_tirats;
    int tirs_triples_anotats;
    int tirs_triples_tirats;
    int tirs_lliures_anotats;
    int tirs_lliures_tirats;
    int rebots_ofensius;
    int assistencies;
    int robades;
    int bloqueigs;

    public Estadistiques_jugadors(int jugador_id, int partit_id, float minuts_jugats, int punts, int tirs_anotats,
                                  int tirs_tirats, int tirs_triples_anotats, int tirs_triples_tirats, int tirs_lliures_anotats,
                                  int tirs_lliures_tirats, int rebots_ofensius, int assistencies, int robades, int bloqueigs) {
        this.jugador_id = jugador_id;
        this.partit_id = partit_id;
        this.minuts_jugats = minuts_jugats;
        this.punts = punts;
        this.tirs_anotats = tirs_anotats;
        this.tirs_tirats = tirs_tirats;
        this.tirs_triples_anotats = tirs_triples_anotats;
        this.tirs_triples_tirats = tirs_triples_tirats;
        this.tirs_lliures_anotats = tirs_lliures_anotats;
        this.tirs_lliures_tirats = tirs_lliures_tirats;
        this.rebots_ofensius = rebots_ofensius;
        this.assistencies = assistencies;
        this.robades = robades;
        this.bloqueigs = bloqueigs;
    }

    public int getJugador_id() {
        return jugador_id;
    }

    public int getPartit_id() {
        return partit_id;
    }

    public float getMinuts_jugats() {
        return minuts_jugats;
    }

    public int getPunts() {
        return punts;
    }

    public int getTirs_anotats() {
        return tirs_anotats;
    }

    public int getTirs_tirats() {
        return tirs_tirats;
    }

    public int getTirs_triples_anotats() {
        return tirs_triples_anotats;
    }

    public int getTirs_triples_tirats() {
        return tirs_triples_tirats;
    }

    public int getTirs_lliures_anotats() {
        return tirs_lliures_anotats;
    }

    public int getTirs_lliures_tirats() {
        return tirs_lliures_tirats;
    }

    public int getRebots_ofensius() {
        return rebots_ofensius;
    }

    public int getAssistencies() {
        return assistencies;
    }

    public int getRobades() {
        return robades;
    }

    public int getBloqueigs() {
        return bloqueigs;
    }
    public void set(int partit_id, float minuts_jugats, int punts, int tirs_anotats,
                    int tirs_tirats, int tirs_triples_anotats, int tirs_triples_tirats, int tirs_lliures_anotats,
                    int tirs_lliures_tirats, int rebots_ofensius, int assistencies, int robades, int bloqueigs){
        this.partit_id = partit_id;
        this.minuts_jugats = minuts_jugats;
        this.punts = punts;
        this.tirs_anotats = tirs_anotats;
        this.tirs_tirats = tirs_tirats;
        this.tirs_triples_anotats = tirs_triples_anotats;
        this.tirs_triples_tirats = tirs_triples_tirats;
        this.tirs_lliures_anotats = tirs_lliures_anotats;
        this.tirs_lliures_tirats = tirs_lliures_tirats;
        this.rebots_ofensius = rebots_ofensius;
        this.assistencies = assistencies;
        this.robades = robades;
        this.bloqueigs = bloqueigs;
    }
    public String toString() {
        return "\nJugador: " +
                "\n\tID: " + partit_id +
                "\n\tMinuts_jugats: " + minuts_jugats +
                "\n\tPunts: " + punts  +
                "\n\tTirs_anotats: " + tirs_anotats +
                "\n\tTirs_tirats: " + tirs_tirats +
                "\n\tTirs_triples_anotats: " + tirs_triples_anotats +
                "\n\tTirs_triples_tirats: " + tirs_triples_tirats +
                "\n\tTirs_lliures_anotats: " + tirs_lliures_anotats +
                "\n\tTirs_lliures_tirats: " + tirs_lliures_tirats +
                "\n\tRebots_ofensius: " + rebots_ofensius +
                "\n\tAssistencies: " + assistencies +
                "\n\tRobades: " + robades +
                "\n\tBloqueig: " + bloqueigs;
    }
}
