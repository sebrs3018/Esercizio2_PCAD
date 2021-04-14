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
        parcheggio.entrata(name);
        try { // simuliamo un delay
            Thread.sleep ((int) Math.random() * 2000000);
            System.out.println(" sono nel parcheggio... " + name);
        } catch (InterruptedException e) { }
        parcheggio.uscita(name);
//        }
    }

}
