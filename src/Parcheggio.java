public class Parcheggio {

    private volatile int  nroPosti;

    public Parcheggio(int _nroPosti){
        nroPosti = _nroPosti;
    }

    //N.B: nel caso in cui ci sia soltanto un posto libero, ci sono dei problemi di sincronizzazione:
    /*  Se thread i-esimo deve entrare in CS, se nel frattempo arriva un altro thread i+1, gli frega il posto e i deve ancora aspettare...
    *   Interessante notare però che ogni volta che si usa la wait, il thread viene mandato in fondo
    *  */
    public synchronized void entrata(String ThreadName){
        while(PostiLiberi()<= 0){
            try {
                System.out.println("Non ci sono posti disponibili (" + nroPosti + ")... "+ ThreadName +"  Aspetta  un sec");
                this.wait(); //mando in attesa il thread che ha chiamato questa funzione

                System.out.println("\t**** Ripartenza: "+ ThreadName + "  nro posti: " + nroPosti);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        decrementPlaces();
    }

    public synchronized void uscita(String s){
//        nroPosti++;
        System.out.println(" \t\t### esco dal parcheggio... " + s + " nroPosti: " + nroPosti +"###");
        incrementPlaces();
        notify(); //"sblocco" momentaneamente il cliente (thread)
    }

    public synchronized int PostiLiberi(){
        return nroPosti;
    }
    private synchronized void decrementPlaces(){
        --nroPosti;
    }
    private synchronized void incrementPlaces(){
        ++nroPosti;
    }

    public synchronized void chiusura(){
        notifyAll();    //sblocco tutti i thtead in attesa
    }

}
