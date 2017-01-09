package Models;

/**
 * Created by jhona on 07/01/2017.
 */

public class TipoHorario {

    private int id_tipoHorario;
    private String tipoHorario;
    private String tipoData;

    public TipoHorario(int id_tipoHorario, String tipoHorario, String tipoData) {
        this.id_tipoHorario = id_tipoHorario;
        this.tipoHorario = tipoHorario;
        this.tipoData = tipoData;
    }

    public int getId_tipoHorario() {
        return id_tipoHorario;
    }

    public void setId_tipoHorario(int id_tipoHorario) {
        this.id_tipoHorario = id_tipoHorario;
    }

    public String getTipoHorario() {
        return tipoHorario;
    }

    public void setTipoHorario(String tipoHorario) {
        this.tipoHorario = tipoHorario;
    }

    public String getTipoData() {
        return tipoData;
    }

    public void setTipoData(String tipoData) {
        this.tipoData = tipoData;
    }
}
