package window;

import main.*;
import utils.Pattern;
import utils.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Matthieu CROUZET on 04/03/2017.
 */
public class PatternChoices extends JFrame{

    private JFrame nextJFrame;
    private JFrame previousJFrame;
    private JCheckBox[] patterns;

    public PatternChoices(){
        super();
        nextJFrame = new JFrame();
        previousJFrame = new JFrame();
        init();
    }

    private void init(){
        setTitle("Application Pattern Creator");
        setSize(Window.WIDTH, Window.HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.WHITE);

        patterns = new JCheckBox[Pattern.values().length];


        for (int i = 0; i < Pattern.values().length; i++) {
            String NAME = Pattern.values()[i].name();
            String Name = NAME.charAt(0) + NAME.substring(1, NAME.length()).toLowerCase();
            patterns[i] =  new JCheckBox(Name);
            panel.add(patterns[i]);
        }

        JButton button = new JButton(new AbstractAction("Previous") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Previous");
                setVisible(false);
                previousJFrame.setVisible(true);
            }
        });
        panel.add(button);

        button = new JButton(new AbstractAction("Finish") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pattern[] choices = new Pattern[Pattern.values().length];
                int j = 0;
                for (int i = 0; i < Pattern.values().length; i++) {
                    //System.out.println(patterns[i].getText() + " : " + patterns[i].isSelected());
                    if (patterns[i].isSelected()) {
                        choices[j] = Pattern.values()[i];
                        j++;
                    }
                }
                MyMain.userData.setChoices(choices);
                nextJFrame.setVisible(true);
                setVisible(false);
                if(nextJFrame instanceof CreationWindow){
                    ((CreationWindow) nextJFrame).tasksGo();
                    ((CreationWindow) nextJFrame).getTimer().start();
                }
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
}
