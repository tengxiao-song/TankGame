package TankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

// Panel need to capture user key type and continue to repaint by itself
public class MyPanel extends JPanel implements KeyListener, Runnable {
    MyTank myTank = null;
    // use Vector for thread safe
    Vector<EnemyTank> enemyTanks = new Vector<>();
    // define a node vector to restore the location of the enemy tanks
    Vector<Node> nodes = new Vector<>();
    // Add a bomb obj to the bombs if a tank has been shot
    Vector<Bomb> bombs = new Vector<>();
    int enemyTankSize = 3;
    Image img1 = null;
    Image img2 = null;
    Image img3 = null;
    public MyPanel(String key) {
        // load nodes to restore the enemy tank location
        nodes = Recorder.getNodesAndEnemyTankRec();
        // check if the record file exists, if not start a new game
        File file = new File(Recorder.getRecordFile());
        if (!file.exists()) {
            key = "1";
        }
        // initialize player tank
        myTank = new MyTank(100, 100);
        myTank.setSpeed(20);
        // init or load enemy tanks based on user input
        switch (key) {
            case "1":
                // initialize enemy tank
                for (int i = 0; i < enemyTankSize; i++) {
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
                    // set all the enemy tanks to the current tank
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirect(2);
                    // start enemy tank thread
                    new Thread(enemyTank).start();
                    enemyTanks.add(enemyTank);
                    // initialize bullets of enemy tank and start the bullet thread
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    new Thread(shot).start();
                    enemyTank.shots.add(shot);
                }
                break;
            case "2":
                // load the enemyTank from the file, from the nodes
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    enemyTank.setDirect(node.getDirect());
                    enemyTank.setEnemyTanks(enemyTanks);
                    new Thread(enemyTank).start();
                    enemyTanks.add(enemyTank);
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
                    new Thread(shot).start();
                    enemyTank.shots.add(shot);
                }
                break;
            default:
                System.out.println("Invalid input, please restart the game.");
                System.exit(0);
        }
        // load the imgs for showing explosion
        img1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb1.png"));
        img2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb2.png"));
        img3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb3.png"));
        Recorder.setEts(enemyTanks);
    }
    // display records of the game
    public void showInfo(Graphics g) {
        Font font = new Font("宋体", Font.BOLD, 20);
        g.setFont(font);
        g.drawString("您累计击毁敌方坦克", 1020, 40);
        drawTank(1020, 60, g, 0, 0);
        g.setColor(Color.black);
        g.drawString(Recorder.getAllEnemyTankNum() + "", 1080, 100);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        showInfo(g);
        // draw the background
        g.fillRect(0, 0, 1000, 750);
        // draw player tank
        if (myTank != null && myTank.isAlive) {
            drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(), 1);
        }
        // draw the bomb
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.life > 6) {
                g.drawImage(img1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(img2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(img3, bomb.x, bomb.y, 60, 60, this);
            }
            bomb.lifeDown();
            if (bomb.life == 0) {
                bombs.remove(bomb);
                bomb.isAlive = false;
            }
        }
        // draw enemy tank
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isAlive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
                // draw the bullets of enemy tank, remove if bullet is dead
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    // draw shots
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.isAlive) {
                        g.fill3DRect(shot.x, shot.y, 3, 3, false);
                    } else {
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }
        // draw player bullet
        for (int i = 0; i < myTank.shots.size(); i++) {
            Shot shot = myTank.shots.get(i);
            if (shot.isAlive) {
                g.fill3DRect(shot.x,shot.y, 3, 3, false);
            } else {
                myTank.shots.remove(shot);
            }
        }
    }

    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        // type: 1 for player, 0 for enemy
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        // direct: 0 for up, 1 for right, 2 for down, 3 for left
        switch (direct) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1:
                // for right direction, x,y is still the top-left corner
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 60, x + 20, y + 30);
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
        }
    }

    public void hitTank(Shot s, Tank tank) {
        // depending on the direction of the tank, test whether the bullet is in tank region
        switch (tank.getDirect()) {
            // tank is placed face up or down, width = 40, height = 60
            case 0:
            case 2:
                if (s.x > tank.getX() && s.x < tank.getX() + 40
                && s.y > tank.getY() && s.y < tank.getY() + 60) {
                    s.isAlive = false;
                    tank.isAlive = false;
                    System.out.println("????");
                    enemyTanks.remove(tank);
                    bombs.add(new Bomb(tank.getX(), tank.getY()));
                    if (tank instanceof EnemyTank) {
                        Recorder.addAllEnemyTankNum();
                    }
                }
                break;
            // tank is placed face left or right width = 60, height = 40
            case 1:
            case 3:
                if (s.x > tank.getX() && s.x < tank.getX() + 60
                && s.y > tank.getY() && s.y < tank.getY() + 40) {
                    s.isAlive = false;
                    tank.isAlive = false;
                    enemyTanks.remove(tank);
                    bombs.add(new Bomb(tank.getX(), tank.getY()));
                    if (tank instanceof EnemyTank) {
                        Recorder.addAllEnemyTankNum();
                    }
                }
        }
    }

    public void hitEnemyTank() {
        for (int j = 0; j < myTank.shots.size();j++) {
            Shot shot = myTank.shots.get(j);
            if (shot != null && shot.isAlive) {
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);
                }
            }
        }
    }

    public void hitMyTank() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                Shot shot = enemyTank.shots.get(j);
                if (shot.isAlive && myTank.isAlive) {
                    hitTank(shot, myTank);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // move tank based on wasd
        if (e.getKeyCode() == KeyEvent.VK_W) {
            myTank.setDirect(0);
            // check bounds
            if (myTank.getY() > 0) {
                myTank.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            myTank.setDirect(1);
            // check bounds also takes account of the size of the tank
            if (myTank.getX() + 60 < 1000) {
                myTank.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            myTank.setDirect(2);
            if (myTank.getY() + 60 < 750) {
                myTank.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            myTank.setDirect(3);
            if (myTank.getX() > 0) {
                myTank.moveLeft();
            }
        }
        // fire shots based on j
        if (e.getKeyCode() == KeyEvent.VK_J) {
            myTank.shotEnemyTank();
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    // MyPanel is also a thread, repaint every 100ms
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Determine whether bullets hit tanks
            hitEnemyTank();
            hitMyTank();
            repaint();
        }
    }
}
