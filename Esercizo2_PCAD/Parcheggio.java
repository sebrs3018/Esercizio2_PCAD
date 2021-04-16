public class Parcheggio {

    private volatile int  nroPosti;
    private volatile boolean chiusura = false;

    public Parcheggio(int _nroPosti){
        nroPosti = _nroPosti;
    }

    public synchronized void entrata(String ThreadName){

        while(PostiLiberi() <= 0 && !isItClosed()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }

        if(isItClosed()){
            System.out.println("\tE' chiuso, non posso entrare:  " + ThreadName);
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
          System.out.println(" \t\t\t### esco dal parcheggio... " + s + " ###");
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
        CloseParking(); //setto variabile chiusura a true
        notifyAll();    //sblocco tutti i thtead in attesa
    }

}
