public class Main {
    public static void main(String[] args) throws
            InterruptedException {

        Parcheggio p = new Parcheggio(1);
        Cliente c1 = new Cliente(p, "Tread1");
        Cliente c2 = new Cliente(p, "Thread2");

        c1.start();
        c2.start();
    }
}
