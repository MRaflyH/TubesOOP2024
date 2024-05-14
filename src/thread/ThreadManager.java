package thread;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ThreadManager {
    private static ArrayList<Runnable> ThreadList = new  ArrayList<Runnable>();

    public synchronized static ArrayList<Runnable> getList(){
        return ThreadList;
    }

    public static void addThread(Runnable thread) {
        ThreadList.add(thread);
    }

    public static void removeThread(int i) {
        ThreadList.remove(i);
    }

    public synchronized static void startAllThreads() {
        for (Runnable run : ThreadList) {
            Thread t = new Thread(run);
            t.start();
        }
    }

    public synchronized static void stopAllThreads(){
        for(Runnable run : ThreadList){
            if(run instanceof RunnableGameTimer){
                ((RunnableGameTimer)run).endCurrentGameTime();
            } else if(run instanceof RunnableGenerateSun){
                ((RunnableGenerateSun)run).endCurrentSundrop();
            }
        }
        for(int i = 0; i < ThreadList.size(); i++){
            ThreadManager.removeThread(i);
        }
    }

    public static void notifyAllThreads() {
        for (Runnable run : ThreadList) {
            {
                synchronized (run) {
                    run.notify();
                }
            }
        }
    }
}
