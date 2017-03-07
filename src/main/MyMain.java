package main;

import utils.Pattern;
import window.*;

import javax.swing.*;

/**
 * Created by Matthieu CROUZET on 04/03/2017.
 */
public class MyMain {

    public static UserData userData = new UserData();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                WelcomeWindow welcomeWindow = new WelcomeWindow();
                welcomeWindow.setVisible(true);

                ProjectWindow projectWindow = new ProjectWindow();
                projectWindow.setVisible(false);


                PatternChoices patternChoices = new PatternChoices();
                patternChoices.setVisible(false);

                CreationWindow creationWindow = new CreationWindow();
                creationWindow.setVisible(false);

                EndWindow endWindow = new EndWindow();
                endWindow.setVisible(false);


                welcomeWindow.setNextJFrame(projectWindow);
                projectWindow.setPreviousJFrame(welcomeWindow);
                projectWindow.setNextJFrame(patternChoices);
                patternChoices.setPreviousJFrame(projectWindow);
                patternChoices.setNextJFrame(creationWindow);
                creationWindow.setPreviousJFrame(patternChoices);
                creationWindow.setNextJFrame(endWindow);
            }
        });

    }

    public static class UserData {

        private String projectName;
        private String projectFolder;
        private Pattern[] choices;


        public UserData() {
            this.projectName = "New Project";
            this.projectFolder = "C:/";
            this.choices = new Pattern[Pattern.values().length];
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectFolder() {
            return projectFolder;
        }

        public void setProjectFolder(String projectFolder) {
            this.projectFolder = projectFolder;
        }

        public Pattern[] getChoices() {
            return choices;
        }

        public void setChoices(Pattern[] choices) {
            this.choices = choices;
        }

        public Pattern getChoice(int i){
            return this.choices[i];
        }
    }
}
