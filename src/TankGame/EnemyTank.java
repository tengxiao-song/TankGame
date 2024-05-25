package TankGame;

import java.util.Vector;

// Every enemy tank moves by itself, so they should be thread
public class EnemyTank extends Tank implements Runnable {
    // an enemy tank has a collection of shots
    Vector<Shot> shots = new Vector<>();
    // an enemy tank can get all enemy tanks (for collision detection)
    Vector<EnemyTank> enemyTanks = new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    // determine whether this tank is colliding with other tanks
    public boolean isTouchEnemyTank() {
        switch (getDirect()) {
            // this tank is moving up (focus on the top left and top right corner)
            case 0:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        // if the enemy tank is moving up or down
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            // top left corner
                            if (getX() >= enemyTank.getX() && getX() <= enemyTank.getX() + 40
                                    && getY() >= enemyTank.getY() && getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            // top right corner
                            if (getX() + 40 >= enemyTank.getX() && getX() + 40 <= enemyTank.getX() + 40
                                    && getY() >= enemyTank.getY() && getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        // if the enemy tank is moving left or right
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            // top left corner
                            if (getX() >= enemyTank.getX() && getX() <= enemyTank.getX() + 60
                                    && getY() >= enemyTank.getY() && getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            // top right corner
                            if (getX() + 40 >= enemyTank.getX() && getX() + 40 <= enemyTank.getX() + 60
                                    && getY() >= enemyTank.getY() && getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            // this tank is moving right (focus on the top right and bottom right corner)
            case 1:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        // if the enemy tank is moving up or down
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            // top right corner
                            if (getX() + 60 >= enemyTank.getX() && getX() + 60 <= enemyTank.getX() + 40
                                    && getY() >= enemyTank.getY() && getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            // bottom right corner
                            if (getX() + 60 >= enemyTank.getX() && getX() + 60 <= enemyTank.getX() + 40
                                    && getY() + 40 >= enemyTank.getY() && getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        // if the enemy tank is moving left or right
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            // top right corner
                            if (getX() + 60 >= enemyTank.getX() && getX() + 60 <= enemyTank.getX() + 60
                                    && getY() >= enemyTank.getY() && getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            // bottom right corner
                            if (getX() + 60 >= enemyTank.getX() && getX() + 60 <= enemyTank.getX() + 60
                                    && getY() + 40 >= enemyTank.getY() && getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            // this tank is moving down (focus on the bottom left and bottom right corner)
            case 2:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        // if the enemy tank is moving up or down
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            // bottom left corner
                            if (getX() >= enemyTank.getX() && getX() <= enemyTank.getX() + 40
                                    && getY() + 60 >= enemyTank.getY() && getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            // bottom right corner
                            if (getX() + 40 >= enemyTank.getX() && getX() + 40 <= enemyTank.getX() + 40
                                    && getY() + 60 >= enemyTank.getY() && getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        // if the enemy tank is moving left or right
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            // bottom left corner
                            if (getX() >= enemyTank.getX() && getX() <= enemyTank.getX() + 60
                                    && getY() + 60 >= enemyTank.getY() && getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                            // bottom right corner
                            if (getX() + 40 >= enemyTank.getX() && getX() + 40 <= enemyTank.getX() + 60
                                    && getY() + 60 >= enemyTank.getY() && getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            // this tank is moving left (focus on the top left and bottom left corner)
            case 3:
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        // if the enemy tank is moving up or down
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            // top left corner
                            if (getX() >= enemyTank.getX() && getX() <= enemyTank.getX() + 40
                                    && getY() >= enemyTank.getY() && getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            // bottom left corner
                            if (getX() >= enemyTank.getX() && getX() <= enemyTank.getX() + 40
                                    && getY() + 40 >= enemyTank.getY() && getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        // if the enemy tank is moving left or right
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            // top left corner
                            if (getX() >= enemyTank.getX() && getX() <= enemyTank.getX() + 60
                                    && getY() >= enemyTank.getY() && getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            // bottom left corner
                            if (getX() >= enemyTank.getX() && getX() <= enemyTank.getX() + 60
                                    && getY() + 40 >= enemyTank.getY() && getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while (isAlive) {
            if (shots.size() < 1) {
                Shot s = null;
                switch (getDirect()) {
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        s = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                shots.add(s);
                new Thread(s).start();
            }


            // Move depending on the direction
            switch (getDirect()) {
                case 0:
                    for (int i = 0; i < 30; i++) {
                        // keep the tank within the frame
                        if (getY() > 0 && !isTouchEnemyTank()) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if (getX() + 60 < 1000 && !isTouchEnemyTank()) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 60 < 700 && !isTouchEnemyTank()) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0 && !isTouchEnemyTank()) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
            // change direction randomly
            setDirect((int) (Math.random() * 4));
        }
    }
}
