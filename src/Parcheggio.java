public class Parcheggio {

    private volatile int  nroPosti;

    public Parcheggio(int _nroPosti){
        nroPosti = _nroPosti;
    }


    public synchronized void entrata(String ThreadName){

        if(nroPosti <= 0){
            try {
                System.out.println("Non ci sono posti disponibili (" + nroPosti + ")... "+ ThreadName +"  Aspetta  un sec");
                wait(); //mando in attesa il thread che ha chiamato questa funzione
                System.out.println("\t**** Ripartenza: "+ ThreadName + "  nro posti: " + nroPosti);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            nroPosti--;
    }

    public synchronized void uscita(String s){
        nroPosti++;
        System.out.println("esco "+ s + ", nro posti: " + nroPosti) ;
        notify(); //"sblocco" momentaneamente il cliente (thread)
    }

    public int PostiLiberi(){
        return nroPosti;
    }


    public synchronized void chiusura(){
        notifyAll();    //sblocco tutti i thtead in attesa
    }

}
