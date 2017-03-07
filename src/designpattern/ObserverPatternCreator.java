package designpattern;

import designpattern.FileData;
import designpattern.FilesCreator;

import java.io.*;

/**
 * Created by Matthieu CROUZET on 05/03/2017.
 */
public class ObserverPatternCreator extends FilesCreator {

    String path = MAIN_PATH + "\\observer\\";

    public ObserverPatternCreator(){
        super();
        int nbFiles = 2;
        File f = new File(path + "\\example\\");
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles\\observer.bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData observer = createFileData(path + "Observer.java",
                "package designpattern.observer; \n\n" +
                "public interface Observer {\n\n" +
                "\tpublic void update(Observable o);\n\n" +
                "}\n");
        FileData observable = createFileData(path + "Observable.java",
                "package designpattern.observer; \n\n" +
                "public interface Observable {\n\n" +
                "\tpublic void addObserver(Observer o);\n\n" +
                "\tpublic void removeObserver(Observer o);\n\n" +
                "\tpublic void notifyObservers();\n\n" +
                "}\n");
        this.fileDataTab[0] = observable;
        this.fileDataTab[1] = observer;
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
