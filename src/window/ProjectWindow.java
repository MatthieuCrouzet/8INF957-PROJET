package window;

import main.MyMain;
import utils.*;
import utils.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * Created by Matthieu CROUZET on 04/03/2017.
 */
public class ProjectWindow extends JFrame {

    private JTextField projectDirectoryInput;
    private JTextField projectNameInput;
    private JFrame nextJFrame;
    private JFrame previousJFrame;

    public ProjectWindow(){
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

    private JPanel buildContentPane(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.WHITE);


        JLabel projectNameLabel = new JLabel("Project name : ");
        panel.add(projectNameLabel);

        projectNameInput = new JTextField();
        projectNameInput.setColumns(30);
        projectNameInput.setText("New Project");
        panel.add(projectNameInput);

        JLabel projectDirectoryLabel = new JLabel("Project directory : ");
        panel.add(projectDirectoryLabel);

        projectDirectoryInput = new JTextField();
        projectDirectoryInput.setColumns(30);
        projectDirectoryInput.setText(new File("").getAbsolutePath() + "\\" + getProjectNameInput().getText());
        panel.add(projectDirectoryInput);

        DirectoryChooser dc = new DirectoryChooser(this);
        panel.add(dc);

        JButton button = new JButton(new AbstractAction("Previous") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Previous");
                previousJFrame.setVisible(true);
                setVisible(false);
            }
        });
        panel.add(button);

        button = new JButton(new AbstractAction("Next") {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuffer folder = new StringBuffer(projectDirectoryInput.getText());
                String name = projectNameInput.getText();
                //System.out.println(projectNameInput.getText());
                //System.out.println(projectDirectoryInput.getText());
                if(folder.substring(0).endsWith("\\")){
                    folder.deleteCharAt(folder.length()-1);
                }
                if(!folder.substring(0).endsWith(name)){
                    folder.append("\\" + name);
                }
                MyMain.userData.setProjectName(name);
                MyMain.userData.setProjectFolder(folder.substring(0));
                //System.out.println("Next");
                nextJFrame.setVisible(true);
                setVisible(false);
            }
        });
        panel.add(button);

        return panel;
    }

    public JTextField getProjectDirectoryInput() {
        return projectDirectoryInput;
    }

    public JTextField getProjectNameInput() {
        return projectNameInput;
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
