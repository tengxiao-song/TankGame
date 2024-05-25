package TankGame;

public class Bomb {
    int x;
    int y;
    // Bomb's life cycle, provide different img based on life
    int life = 9;
    boolean isAlive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void lifeDown() {
        if (life > 0) {
            life--;
        } else {
            isAlive = false;
        }
    }
}
