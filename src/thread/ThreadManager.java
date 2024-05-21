package thread;

import java.util.ArrayList;

public class ThreadManager {
    private static ThreadManager instance;
    private ArrayList<Runnable> threadList;

    // Private constructor to prevent instantiation
    private ThreadManager() {
        threadList = new ArrayList<>();
    }

    // Public method to provide access to the singleton instance
    public static synchronized ThreadManager getInstance() {
        if (instance == null) {
            instance = new ThreadManager();
        }
        return instance;
    }

    public synchronized ArrayList<Runnable> getList() {
        return threadList;
    }

    public void addThread(Runnable thread) {
        System.out.println("addThread called with thread " + thread.getClass().getName());
        threadList.add(thread);
    }

    public void removeThread(int i) {
        threadList.remove(i);
    }

    public synchronized void startAllThreads() {
        for (Runnable run : threadList) {
            Thread t = new Thread(run);
            t.start();
        }
    }

    public synchronized void stopAllThreads() {
        for (Runnable run : threadList) {
            if (run instanceof RunnableGameTimer) {
                ((RunnableGameTimer) run).endCurrentGameTime();
            } else if (run instanceof RunnableGenerateSun) {
                ((RunnableGenerateSun) run).endCurrentSundrop();
            } else if (run instanceof RunnableZombieSpawn) {
                ((RunnableZombieSpawn) run).endCurrentZombieSpawn();
            }
        }
        threadList.clear();
    }

    public RunnableGameTimer getRunnableGameTimer() {
        for (Runnable run : threadList) {
            if (run instanceof RunnableGameTimer) {
                return (RunnableGameTimer) run;
            }
        }
        return null;
    }
}
