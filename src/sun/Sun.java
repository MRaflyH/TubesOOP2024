package sun;
import java.io.*;

public class Sun implements Serializable {
    private static Sun instance;
    private int totalsun;

    // Private constructor to prevent instantiation
    private Sun() {
        totalsun = 0;
    }

    // Public method to provide access to the singleton instance
    public static Sun getInstance() {
        if (instance == null) {
            instance = new Sun();
        }
        return instance;
    }

    public void generateSun() {
        totalsun += 25;
    }

    public int getTotalSun() {
        return totalsun;
    }

    public void reduceSun(int plantcost) {
        System.out.println("berkurang sebanyak : " + plantcost);
        totalsun -= plantcost;
    }

    public void initializeSun(){
        totalsun = 50;
    }
}
