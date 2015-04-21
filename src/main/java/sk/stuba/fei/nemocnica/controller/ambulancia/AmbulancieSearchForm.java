package sk.stuba.fei.nemocnica.controller.ambulancia;

/**
 * Created by jakubrehak on 20/04/15.
 */
public class AmbulancieSearchForm extends sk.stuba.fei.nemocnica.search.GenericSearchForm{

    private String meno;
    private int zariadenie;

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public int getZariadenie() {
        return zariadenie;
    }

    public void setZariadenie(int zariadenie) {
        this.zariadenie = zariadenie;
    }


}
