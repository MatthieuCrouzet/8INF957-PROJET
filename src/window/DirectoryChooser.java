package window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Matthieu CROUZET on 04/03/2017.
 */
public class DirectoryChooser extends JPanel
            implements ActionListener {
    JButton go;
    JFrame jFrame;
    JFileChooser chooser;
    String choosertitle = "Choose your project directory";

    public DirectoryChooser(JFrame jFrame) {
        go = new JButton(" . . . ");
        go.addActionListener(this);
        add(go);
        this.jFrame = jFrame;
    }

    public void actionPerformed(ActionEvent e) {
        int result;

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(""));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    +  chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : "
                    +  chooser.getSelectedFile());
            if(jFrame instanceof ProjectWindow){
                ProjectWindow pw = (ProjectWindow) jFrame;
                if(chooser.getSelectedFile().getName() == pw.getProjectNameInput().getText()){
                    pw.getProjectDirectoryInput().setText(chooser.getSelectedFile().getAbsolutePath());
                } else {
                    String s = chooser.getSelectedFile().getAbsolutePath()
                            + "\\" + pw.getProjectNameInput().getText();
                    pw.getProjectDirectoryInput().setText(s);
                }
            }
        }
        else {
            System.out.println("No Selection ");
        }
    }

}
