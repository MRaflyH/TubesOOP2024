package sun;

public class Sun {
    private static int totalsun;
    public Sun(){
        totalsun = 25;
    }

    public static void generateSun(){
        totalsun += 25;
    }

    public static int getTotalSun(){
        return totalsun;
    }
}