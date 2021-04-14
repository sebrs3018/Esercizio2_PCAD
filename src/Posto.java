public class Posto {

    private int nro_posti;

    public Posto(){
        nro_posti = 1;
    }


    public boolean isEmpty(){
        return nro_posti <=0;
    }

    public int getNro_posti() {
        return nro_posti;
    }

    public void setNro_posti(int nro_posti) {
        this.nro_posti = nro_posti;
    }




}
