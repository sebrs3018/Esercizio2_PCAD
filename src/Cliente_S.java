public class Cliente_S extends Thread {

    private String name;
    private final Parcheggio_S parcheggio;

    public Cliente_S(Parcheggio_S _parcheggio, String _name){
        name = _name;
        parcheggio = _parcheggio;
    }

    public String getNomeCliente(){
        return name;
    }

    public void run(){

        //entro nel parcheggio sse ci sono dei posti liberi
//            System.out.println("entro " + name);

//        for (int i = 0; i< 10; i++){
//            System.out.println("*******" +this.getState().name() + "*******");
            while(parcheggio.getClosed() == false){
                parcheggio.entrata(name);       //consuma
                try { // simuliamo un delay
                    System.out.println(" \t\t### sono nel parcheggio... " + name + " ###");
                    Thread.sleep ((int) Math.random() * 20000);
                } catch (InterruptedException e) { }
                parcheggio.uscita(name);        //produce
            }

//        }


    }

}
