package Models;

/**
 * Created by jhona on 07/01/2017.
 */

public class Linhas {

    private int id_linha;
    private String linha_nome;

    public Linhas(int id_linha, String linha_nome) {
        this.id_linha = id_linha;
        this.linha_nome = linha_nome;
    }

    public String getLinha_nome() {
        return linha_nome;
    }

    public void setLinha_nome(String linha_nome) {
        this.linha_nome = linha_nome;
    }

    public int getId_linha() {
        return id_linha;
    }

    public void setId_linha(int id_linha) {
        this.id_linha = id_linha;
    }
}
