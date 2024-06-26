package TankGame;

public class Tank {
    private int x;
    private int y;
    private int direct; // 0 for up, 1 for right, 2 for down, 3 for left
    private int speed = 1;
    boolean isAlive = true;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveUp() {
        y -= speed;
    }
    public void moveDown() {
        y += speed;
    }
    public void moveRight() {
        x += speed;
    }
    public void moveLeft() {
        x -= speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
