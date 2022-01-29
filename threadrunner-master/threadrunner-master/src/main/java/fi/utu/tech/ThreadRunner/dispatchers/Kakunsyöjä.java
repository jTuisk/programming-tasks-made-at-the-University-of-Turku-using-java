package fi.utu.tech.ThreadRunner.dispatchers;

import fi.utu.tech.ThreadRunner.workers.Worker;
import fi.utu.tech.ThreadRunner.workers.WorkerFactory;
import java.util.ArrayList;

public class Kakunsyöjä implements Runnable{

    private ArrayList<Integer> tasks;
    private Worker worker;

    public ArrayList<Integer> getTasks() {return this.tasks; }
    public void setTasks(ArrayList<Integer> tasks){ this.tasks = tasks;}

    public Kakunsyöjä(ArrayList<Integer> tasks, String worker){
        try {
            this.worker = WorkerFactory.createWorker(worker);
            this.tasks = tasks;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int time : this.tasks) {
            try {
                this.worker.count(time);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
