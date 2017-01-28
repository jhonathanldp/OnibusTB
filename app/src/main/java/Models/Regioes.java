package Models;

/**
 * Created by jhona on 30/12/2016.
 */

public class Regioes {
    private int id;
    private String regiao;

    public Regioes(int id, String regiao) {
        this.id = id;
        this.regiao = regiao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }
}
