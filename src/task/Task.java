package task;

import java.util.Random;

/**
 * Created by Matthieu CROUZET on 04/03/2017.
 */
public class Task {
    private boolean done = false;
    private boolean canceled = false;

    public Task() { }


    public void go() {
        final SwingWorker worker = new SwingWorker() {

            public Object construct() {
                done = false;
                canceled = false;
                return new Task.ActualTask();
            }
        };
        worker.start();
    }

    public void stop() {
        canceled = true;
    }

    public boolean isDone() {
        return done;
    }


    /**
     * The actual long running task.  This runs in a task.SwingWorker thread.
     */
    class ActualTask {
        ActualTask() {
            while (!canceled && !done) {
                try {
                    Random random = new Random();
                    Thread.sleep(random.nextInt(10)*1000);
                    //Do the job
                    done = true;
                } catch (InterruptedException e) {
                    System.out.println("ActualTask interrupted");
                }
            }
        }
    }


}

