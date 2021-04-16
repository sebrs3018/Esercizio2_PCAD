import java.util.Timer;
import java.util.TimerTask;

public class Main {

    static int counter = 0;
    static Timer timer;

    public static void main(String[] args) throws
            InterruptedException {

        Parcheggio p = new Parcheggio(1);
        Cliente c1 = new Cliente(p, "Tread_1");
        Cliente c2 = new Cliente(p, "Thread_2");
        Cliente c3 = new Cliente(p, "Thread_3");
        Cliente c4 = new Cliente(p, "Thread_4");
        Cliente c5 = new Cliente(p, "Thread_5");
        Cliente c6 = new Cliente(p, "Thread_6");
        Cliente c7 = new Cliente(p, "Thread_7");

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(counter == 1){
                    p.chiusura();   //è tempo di chiusura
                    timer.cancel();
                    System.out.println("\n***********************************\n" +
                            "****  IL PARCHEGGIO HA CHIUSO! ****" +
                            "\n***********************************");
                }
                counter++;//increments the counter
            }
        };

        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();
        c7.start();

        timer = new Timer("MyTimer");//create a new Timer
        timer.scheduleAtFixedRate(timerTask, 200, 1);//this line starts the timer at the same time its executed
    }
}

