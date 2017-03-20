package designpattern;

import utils.Separator;

import java.io.*;
/**
 * Created by Baptiste BURON on 19/03/2017.
 */
public class ObserverPatternCreator extends FilesCreator{

    String packageName = "observer";

    String path = MAIN_PATH + Separator.SEPARATOR + packageName + Separator.SEPARATOR;

    public ObserverPatternCreator(){
        super();
        int nbFiles = 3;
        File f = new File(path + Separator.SEPARATOR + "example" + Separator.SEPARATOR);
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles" + Separator.SEPARATOR + packageName + ".bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData observer = createFileData(path + "Observer.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public interface Observer {\n\n" +
                        "\tvoid setSubject(Subject s);\n\n" +
                        "\tSubject getSubject();\n\n" +
                        "\tvoid update();\n\n" +
                        "}\n");
        FileData subject = createFileData(path + "Subject.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public interface Subject {\n\n" +
                        "\tpublic void addObserver(Observer o);\n\n" +
                        "\tpublic void removeObserver(Observer o);\n\n" +
                        "\tCollection<Observer> getObservers();" +
                        "}\n");
        FileData protocol = createFileData(path + "SubjectObserverProtocol.aj",
                "package designpattern." + packageName + "; \n\n" +
                        "public abstract aspect SubjectObserverProtocol {\n\n" +
                        "\tabstract pointcut stateChanges(Subject s);\n\n" +
                        "\tprivate ArrayList<Observer> Subject.observers = new ArrayList<Observer>();\n\n" +
                        "\tafter(Subject s): stateChanges(s) {\n\t\tfor (Observer observer : s.getObservers()) {\n\t\t\tobserver.update();\n\t\t}\n\t}\n\n" +
                        "\tpublic void Subject.addObserver(Observer obs) {\n\tobservers.add(obs);\n\tobs.setSubject(this);\n\t}\n\n" +
                        "\tpublic void Subject.removeObserver(Observer obs) {\n\tobservers.remove(obs);\n\tobs.setSubject(null);\n\t}\n\n" +
                        "\tpublic ArrayList<Observer> Subject.getObservers() { return observers; }\n\n" +
                        "\tprivate Subject Observer.subject = null;\n\n" +
                        "\tpublic void Observer.setSubject(Subject s) { subject = s; }\n\n" +
                        "\tpublic Subject Observer.getSubject() { return subject; }\n\n" +
                        "}\n");
        this.fileDataTab[0] = observer;
        this.fileDataTab[1] = subject;
        this.fileDataTab[2] = protocol;
        try {
            FileOutputStream file = new FileOutputStream(binaryFileName);
            ObjectOutputStream os = new ObjectOutputStream(file);
            os.writeObject(this.fileDataTab);
            os.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


