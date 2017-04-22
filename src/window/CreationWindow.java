package window;

import designpattern.ProjectCreator;
import main.MyMain;
import designpattern.AdapterPatternCreator;
import designpattern.MediatorPatternCreator;
import designpattern.ObserverPatternCreator;
import task.Task;
import utils.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Matthieu CROUZET on 04/03/2017.
 */
public class CreationWindow extends JFrame {

    private JFrame nextJFrame;
    private JFrame previousJFrame;
    private JProgressBar jProgressBar;
    private Timer timer;
    private Task[] tasks;

    public CreationWindow(){
        super();
        nextJFrame = new JFrame();
        previousJFrame = new JFrame();
        tasks = new Task[100];
        for(int i = 0; i < tasks.length; i++){
            tasks[i] = new Task();
        }
        init();
    }

    private void init(){
        setTitle("Application Pattern Creator");
        setSize(Window.WIDTH, Window.HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(buildContentPane());

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int cpt = 0;
                for(int i = 0; i < tasks.length; i++){
                    if(tasks[i].isDone()){
                        cpt++;
                    }
                }
                jProgressBar.setValue(cpt);
                if (cpt == tasks.length) {
                    Toolkit.getDefaultToolkit().beep();
                    timer.stop();
                    nextJFrame.setVisible(true);
                    setVisible(false);
                }
            }
        });
    }

    private JPanel buildContentPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.WHITE);

        jProgressBar = new JProgressBar(0, 100);
        panel.add(jProgressBar);
        jProgressBar.setMinimum(0);
        jProgressBar.setMaximum(100);
        jProgressBar.setStringPainted(true);
        jProgressBar.setValue(0);

        JButton button = new JButton(new AbstractAction("Cancel") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Cancel");
                setVisible(false);
                previousJFrame.setVisible(true);
            }
        });
        panel.add(button);

        return panel;
    }


    public JFrame getNextJFrame() {
        return nextJFrame;
    }

    public void setNextJFrame(JFrame nextJFrame) {
        this.nextJFrame = nextJFrame;
    }

    public JFrame getPreviousJFrame() {
        return previousJFrame;
    }

    public void setPreviousJFrame(JFrame previousJFrame) {
        this.previousJFrame = previousJFrame;
    }

    public JProgressBar getjProgressBar() {
        return jProgressBar;
    }

    public void setjProgressBar(JProgressBar jProgressBar) {
        this.jProgressBar = jProgressBar;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void tasksGo(){
        //Fake tasks for the progress bar
        for(int i = 0; i < tasks.length; i++){
            tasks[i].go();
        }
        ProjectCreator projectCreator = new ProjectCreator();
        projectCreator.execute();
        for(int i = 0; i < MyMain.userData.getChoices().length; i++){
            if(MyMain.userData.getChoice(i) != null) {
                switch (MyMain.userData.getChoice(i)) {
                    case OBSERVER:
                        ObserverPatternCreator observerPatternCreator = new ObserverPatternCreator();
                        observerPatternCreator.execute();
                        break;
                    case MEDIATOR:
                    	MediatorPatternCreator mediatorPatternCreator = new MediatorPatternCreator();
                        mediatorPatternCreator.execute();
                        break;
                    case ADAPTER:
                    	AdapterPatternCreator adapterPatternCreator = new AdapterPatternCreator();
                    	adapterPatternCreator.execute();
                    	break;
                     
                }
            }
        }

    }
}
