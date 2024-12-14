package telran.producer.consumer;

public class Receiver extends Thread {
    private MessageBox messageBox;
    private boolean isEven;

    public Receiver(MessageBox messageBox, boolean isEven) {
        this.messageBox = messageBox;
        this.isEven = isEven;
    }

    public void setMessageBox(MessageBox messageBox) {
        this.messageBox = messageBox;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String message = messageBox.take();
                if (isEven && Integer.parseInt(message.replaceAll("\\D", "")) % 2 == 0 ||
                        !isEven && Integer.parseInt(message.replaceAll("\\D", "")) % 2 != 0) {
                    System.out.printf("%s, %s\n", getName(), message);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.printf("Receiver %s stopped.\n", getName());
    }
}
