package Models;

/**
 * Created by Jhonathan on 07/01/2017.
 */

public class Horario {

    private int id_horario;
    private boolean isFavorite;
    private String horario;
    private int id_regiao;
    private int id_tipoHorario;
    private int id_linha;
    private String regiao_nome;
    private String  infoAdicional;

    public Horario(int id_horario, boolean isFavorite, String horario, int id_regiao, int id_tipoHorario, int id_linha, String regiao_nome, String infoAdicional) {
        this.id_horario = id_horario;
        this.isFavorite = isFavorite;
        this.horario = horario;
        this.id_regiao = id_regiao;
        this.id_tipoHorario = id_tipoHorario;
        this.id_linha = id_linha;
        this.regiao_nome = regiao_nome;
        this.infoAdicional = infoAdicional;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getId_regiao() {
        return id_regiao;
    }

    public void setId_regiao(int id_regiao) {
        this.id_regiao = id_regiao;
    }

    public int getId_tipoHorario() {
        return id_tipoHorario;
    }

    public void setId_tipoHorario(int id_tipoHorario) {
        this.id_tipoHorario = id_tipoHorario;
    }

    public int getId_linha() {
        return id_linha;
    }

    public void setId_linha(int id_linha) {
        this.id_linha = id_linha;
    }

    public String getRegiao_nome() {
        return regiao_nome;
    }

    public void setRegiao_nome(String regiao_nome) {
        this.regiao_nome = regiao_nome;
    }

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }
}
