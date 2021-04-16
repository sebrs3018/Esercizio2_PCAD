public class Parcheggio_S {

    private volatile int  nroPosti;
    private volatile boolean chiusura = false;

    public Parcheggio_S(int _nroPosti){
        nroPosti = _nroPosti;
    }

    public synchronized void entrata(String ThreadName){

        while(PostiLiberi() <= 0 && !isItClosed()) {
                try {
                    System.out.println("Waiting " + ThreadName);
                    wait();
                    System.out.println("Riparto " + ThreadName + " " + isItClosed());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }

        if(isItClosed()){
            System.out.println("\tMa... E' chiuso, mi tocca uscire:  " + ThreadName);
            return;
        }
        decrementPlaces();
    }

    public synchronized void uscita(String s){
      if(isItClosed()) {
            System.out.println("\t" + s + " non è uscito perché il parcheggio ha chiuso!");
            notify();
        }
      else {
          incrementPlaces();
          System.out.println(" \t\t### esco dal parcheggio... " + s + " nroPosti: " + nroPosti +"###");
      }
    }

    public synchronized int PostiLiberi(){
        return nroPosti;
    }

    public synchronized void CloseParking(){
        chiusura = true;
    }

    public synchronized boolean isItClosed(){
        return  chiusura;
    }

    private synchronized void decrementPlaces(){
        --nroPosti;
    }
    private synchronized void incrementPlaces(){
        if(nroPosti <= 0){  //Caso particolare: se il numero di posti è esaurito, devo risvegliare il seguente thread in coda!
            ++nroPosti;
            notify();
        }
    }

    public synchronized void chiusura(){
        System.out.println("\t\t*****************************************");
        System.out.println("\t\t*************** Chiusura! ***************");
        System.out.println("\t\t*****************************************");
        CloseParking(); //setto variabile chiusura a true
        notifyAll();    //sblocco tutti i thtead in attesa
    }

}
