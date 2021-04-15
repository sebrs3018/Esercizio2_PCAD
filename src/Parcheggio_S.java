public class Parcheggio_S {

    private volatile int  nroPosti;
    private boolean disponibile = true;
    private boolean isWaiting = true;
    private volatile boolean chiusura = false;

    public Parcheggio_S(int _nroPosti){
        nroPosti = _nroPosti;
    }


    public void entrata(String ThreadName){

        while(PostiLiberi() <= 0 && !chiusura) {
            synchronized (this) {
                try {
//                    System.out.println("Waiting " + ThreadName);
                    wait();
                    System.out.println("Riparto " + ThreadName + " " + getClosed());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if(getClosed() == true){
            System.out.println("E' chiuso, mi tocca uscire:  " + ThreadName);
//            Thread.currentThread().stop();
            return;
        }

        decrementPlaces();
    }

    public synchronized void uscita(String s){
//        nroPosti++;
//        disponibile = true;
        incrementPlaces();
        System.out.println(" \t\t### esco dal parcheggio... " + s + " nroPosti: " + nroPosti +"###");
//        notifyAll(); //"sblocco" momentaneamente il cliente (thread)
    }

    public synchronized int PostiLiberi(){
        return nroPosti;
    }

    public synchronized void itsClosed(){
        chiusura = true;
    }

    public synchronized boolean getClosed(){
        return  chiusura;
    }

    private synchronized void decrementPlaces(){
        --nroPosti;
        if(nroPosti <= 0)
            disponibile = false;

//        notifyAll();
    }
    private synchronized void incrementPlaces(){
        if(nroPosti <= 0){
            ++nroPosti;
            notify();
        }
    }

    public synchronized void chiusura(){
        System.out.print("\t******* Chiusura ********!");
        itsClosed();
        System.out.println("is it closed?  " + chiusura);
        notifyAll();    //sblocco tutti i thtead in attesa
    }

}
