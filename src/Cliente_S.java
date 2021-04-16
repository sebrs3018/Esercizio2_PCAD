public class Cliente_S extends Thread {

    private String name;
    private final Parcheggio_S parcheggio;
    private final int NUM_REP = 1;

    public Cliente_S(Parcheggio_S _parcheggio, String _name){
        name = _name;
        parcheggio = _parcheggio;
    }

    public String getNomeCliente(){
        return name;
    }

    public void run(){

            int counter = 0;
            while(!parcheggio.isItClosed() && counter < NUM_REP) {
                parcheggio.entrata(name);        //consuma
                    if(parcheggio.isItClosed())  //Questo controllo interno mi permette di non proseguire nel caso in cui il parcheggio sia chiuso
                        return;
                try { // simuliamo un delay
                    System.out.println(" \t\t### sono nel parcheggio... " + name + " ### " + parcheggio.isItClosed());
                    Thread.sleep ((int) Math.random() * 20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                parcheggio.uscita(name);        //produce
                counter++;
            }
/*            //da notare che ci arriva a questo punto, è perchè quando il parcheggio viene chiuso, lui era già dentro
            if(parcheggio.isItClosed()){
                System.out.println("\t/// Il parcheggio è chiuso, finisco esecuzione: " + name +"///");
            }*/
    }
}
