package sun;

public class Sun {
    private static int totalsun;
    public Sun(){
        totalsun = 50;
    }

    public static void generateSun(){
        totalsun += 25;
    }

    public static int getTotalSun(){
        return totalsun;
    }

    public static void reduceSun(int plantcost){
        System.out.println("berkurang sebanyak : " + plantcost);
        totalsun -= plantcost;
    }
}