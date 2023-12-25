import java.util.concurrent.Semaphore;

public class FourPlayerCardGame {

    private static final int NUM_PLAYER = 6;
    private static final Semaphore sem = new Semaphore(1);

    public static void main(String[] args) {
        for(int i=1; i<NUM_PLAYER; i++) {
            new Thread(new Player(i, sem)).start();
        }
    }

}

class Player implements Runnable {
    private final int playerNumber;
    private final Semaphore semaphore;

    Player(int playerNumber, Semaphore semaphore) {
        this.playerNumber = playerNumber;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
            try {
                semaphore.acquire();
                playTurn();
                Thread.sleep(2000);
                semaphore.release();
                
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    private void playTurn() {
        System.out.println("Player " + playerNumber + " is playing their turn.");
    }
}