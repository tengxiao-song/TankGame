package TankGame;

import java.util.Vector;

public class MyTank extends Tank {
    Shot shot = null;

    // my tank can shot multiple times
    Vector<Shot> shots = new Vector<>();

    public MyTank(int x, int y) {
        super(x, y);
    }

    public void shotEnemyTank() {
        // create the bullet based on the direction of tank and position of the cannon
        if (shots.size() == 5) {
            return;
        }
        switch (getDirect()) {
            case 0:
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }
        shots.add(shot);
        // start the bullet thread
        Thread thread = new Thread(shot);
        thread.start();

    }
}
