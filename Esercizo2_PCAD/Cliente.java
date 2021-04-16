public class Cliente extends Thread {

    private String name;
    private final Parcheggio parcheggio;
    private final int NUM_REP = 1000000000;

    public Cliente(Parcheggio _parcheggio, String _name){
        name = _name;
        parcheggio = _parcheggio;
    }

    public String getNomeCliente(){
        return name;
    }

    public void run(){

            int counter = 0;
            while(!parcheggio.isItClosed() && counter < NUM_REP) {
                parcheggio.entrata(name);
                    if(parcheggio.isItClosed())  //Questo controllo interno mi permette di non proseguire nel caso in cui il parcheggio abbia chiuso al momento dell'entrata
                        return;
                try { // simuliamo un delay
                    System.out.println(" \t\t### sono nel parcheggio... " + name + " ### ");
                    Thread.sleep ((int) Math.random() * 20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                parcheggio.uscita(name);
                counter++;
            }
        System.out.println( name+ " ha finito correttamente");
    }
}
