public class Cliente extends Thread {

    private String name;
    private final Parcheggio parcheggio;

    public Cliente(Parcheggio _parcheggio, String _name){
        name = _name;
        parcheggio = _parcheggio;
    }

    public String getNomeCliente(){
        return name;
    }

    public void run(){

        //entro nel parcheggio sse ci sono dei posti liberi
//            System.out.println("entro " + name);

            parcheggio.entrata(name);       //consuma
            try { // simuliamo un delay
                System.out.println(" \t\t### sono nel parcheggio... " + name + " ###");
                Thread.sleep ((int) Math.random() * 20000);
            } catch (InterruptedException e) { }
            parcheggio.uscita(name);        //produce

    }

}
