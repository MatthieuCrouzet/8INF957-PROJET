package window;

import utils.*;
import utils.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Matthieu CROUZET on 04/03/2017.
 */
public class EndWindow extends JFrame{

    private JFrame nextJFrame;
    private JFrame previousJFrame;

    public EndWindow(){
        super();
        nextJFrame = new JFrame();
        previousJFrame = new JFrame();
        init();
    }

    private void init(){
        setTitle("Application utils.FilesCreator Creator");
        setSize(Window.WIDTH, Window.HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.WHITE);


        JLabel label = new JLabel("Finish !");
        panel.add(label);

        JButton button = new JButton(new AbstractAction("Quit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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
