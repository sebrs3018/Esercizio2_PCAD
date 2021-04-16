import java.util.Timer;
import java.util.TimerTask;

public class Main_S {

    static int counter = 0;
    static Timer timer;

    public static void main(String[] args) throws
            InterruptedException {

        Parcheggio_S p = new Parcheggio_S(1);
        Cliente_S c1 = new Cliente_S(p, "Tread_1");
        Cliente_S c2 = new Cliente_S(p, "Thread_2");
        Cliente_S c3 = new Cliente_S(p, "Thread_3");
        Cliente_S c4 = new Cliente_S(p, "Thread_4");
        Cliente_S c5 = new Cliente_S(p, "Thread_5");
        Cliente_S c6 = new Cliente_S(p, "Thread_6");
        Cliente_S c7 = new Cliente_S(p, "Thread_7");

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
//                System.out.println("TimerTask executing counter is: " + counter);
                if(counter == 1){
                    p.chiusura();   //è tempo di chiusura
                    timer.cancel();
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

