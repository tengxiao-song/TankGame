package TankGame;

// shot moves by itself, should be thread
public class Shot implements Runnable {
    int x;
    int y;
    int direct = 0;
    int speed = 20;
    boolean isAlive = true;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        // bullet updates its x,y coordinate every 500ms, terminate when out of bounds or hit tank
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direct) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isAlive)) {
                isAlive = false;
                break;
            }
        }
    }
}
