public class Parcheggio {

    private int nroPosti;

    public Parcheggio(int _nroPosti){
        nroPosti = _nroPosti;
    }


    public void  entrata(String s){

        if(nroPosti <= 0){
            try {
                System.out.println("Non ci sono posti disponibili... "  + s + " Aspetta  un sec");
                wait(); //mando in attesa il thread che ha chiamato questa funzione
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            nroPosti--;
    }

    public void uscita(){
        nroPosti++;
        notifyAll(); //"sblocco" momentaneamente il cliente (thread)
    }

    public int PostiLiberi(){
        return nroPosti;
    }


    public void chiusura(){
        notifyAll();    //sblocco tutti i thtead in attesa
    }

}
