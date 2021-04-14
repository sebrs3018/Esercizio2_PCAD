public class Main {
    public static void main(String[] args) throws
            InterruptedException {

        Parcheggio p = new Parcheggio(5);
        Cliente c1 = new Cliente(p, "Tread1");
        Cliente c2 = new Cliente(p, "Thread2");
        Cliente c3 = new Cliente(p, "Thread3");
        Cliente c4 = new Cliente(p, "Thread4");
        Cliente c5 = new Cliente(p, "Thread5");
        Cliente c6 = new Cliente(p, "Thread6");
        Cliente c7 = new Cliente(p, "Thread7");

        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();
        c7.start();
    }
}
