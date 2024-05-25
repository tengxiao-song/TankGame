package TankGame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

// Recorder class is a tool class used to record the game using file I/O
public class Recorder {
    private static int allEnemyTankNum = 0;
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static FileReader fr = null;
    private static BufferedReader br = null;
    private static String recordFile = "src/TankGame/myRecord.txt";
    private static Vector<EnemyTank> enemyTanks = new Vector<>();
    private static Vector<Node> nodes = new Vector<>();
    // read the record from the file
    public static Vector<Node> getNodesAndEnemyTankRec() {
        try {
            fr = new FileReader(recordFile);
            br = new BufferedReader(fr);
            allEnemyTankNum = Integer.parseInt(br.readLine());
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] xyz = line.split(" ");
                Node node = new Node(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2]));
                nodes.add(node);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nodes;
    }

    // record the number of enemy tanks hit by our tank and the location of the enemy tanks alive
    public static void keepRecord() {
        try {
            fw = new FileWriter(recordFile);
            bw = new BufferedWriter(fw);
            bw.write(allEnemyTankNum + "\r\n");
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isAlive) {
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                    bw.write(record + "\r\n");
                }
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // when our tank destroys an enemy tank, increase allEnemyTankNum
    public static void addAllEnemyTankNum() {
        allEnemyTankNum++;
    }
    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    public static void setEts(Vector<EnemyTank> ets) {
        Recorder.enemyTanks = ets;
    }

    public static String getRecordFile() {
        return recordFile;
    }
}
