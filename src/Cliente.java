public class Cliente extends Thread {

    private String name;
    private final Parcheggio parcheggio;

    public Cliente(Parcheggio _parcheggio, String _name){
        name = _name;
        parcheggio = _parcheggio;
    }

    public void run(){


        synchronized (parcheggio){
            while(parcheggio.PostiLiberi() <= 0){
                try {
                    parcheggio.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }

            //entro nel parcheggio sse ci sono dei posti liberi
            System.out.println("entro " + name);
            parcheggio.entrata(this.name);
            System.out.println("posti liberi " + parcheggio.PostiLiberi());

            try { // simuliamo un delay
                Thread.sleep ((int) Math.random() * 2000);
                System.out.println(" sono nel parcheggio... " + name);
            } catch (InterruptedException e) { }

            parcheggio.uscita();
            System.out.println("esco "+ name + ", nro posti: " + parcheggio.PostiLiberi()) ;

//            parcheggio.notify();

        }

    }

}
