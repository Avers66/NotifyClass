/**
 * Main
 *
 * @Author Tretyakov Alexandr
 */

public class Main {
    public static void main(String[] args) {
        NotifyClass notifyClass = new NotifyClass();

        Runnable remover = new Runnable() {
            @Override
            public void run() {
                try {
                    notifyClass.removeElement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        };

        Runnable adder = new Runnable() {
            @Override
            public void run() {
            notifyClass.addElement("Hello");
            }
        };


        try {
            Thread remover1 = new Thread(remover, "Remover1");
            remover1.start();
            Thread.sleep(5000);
            Thread remover2 = new Thread(remover, "Remover2");
            remover2.start();
            Thread.sleep(5000);
            Thread adder1 = new Thread(adder, "Adder");
            adder1.start();
            Thread.sleep(5000);
            remover1.interrupt();
            remover2.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
