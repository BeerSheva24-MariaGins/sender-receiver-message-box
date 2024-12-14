package telran.producer.consumer;

public class Main {
    private static final int N_MESSAGES = 20;
    static final int N_RECEIVERS = 10;

    public static void main(String[] args) throws InterruptedException {
        MessageBox evenMessageBox = new SimpleMessageBox();
        MessageBox oddMessageBox = new SimpleMessageBox();
        Sender sender = new Sender(N_MESSAGES, evenMessageBox, oddMessageBox);
        Receiver[] receivers = new Receiver[N_RECEIVERS];
        for (int i = 0; i < N_RECEIVERS; i++) {
            receivers[i] = new Receiver((i % 2 != 0) ? evenMessageBox : oddMessageBox, i % 2 != 0);
            receivers[i].start();
        }
        sender.start();
        sender.join();
        for (Receiver receiver : receivers) {
            receiver.interrupt();
            receiver.join();
        }
    }
}