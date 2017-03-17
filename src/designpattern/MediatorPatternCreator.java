package designpattern;

import java.io.*;

import utils.Separator;

/**
 * Created by Matthieu CROUZET on 13/03/2017.
 */
public class MediatorPatternCreator extends FilesCreator {

    String packageName = "mediator";

    String path = MAIN_PATH + Separator.SEPARATOR + packageName + Separator.SEPARATOR;

    public MediatorPatternCreator(){
        super();
        int nbFiles = 3;
        File f = new File(path + Separator.SEPARATOR + "example" + Separator.SEPARATOR);
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles" + Separator.SEPARATOR + packageName + ".bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData mediator = createFileData(path + "Mediator.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public interface Mediator {\n\n" +
                        "\tpublic void send(Message message, Colleague colleague);\n\n" +
                        "}\n");
        FileData colleague = createFileData(path + "Colleague.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public abstract class Colleague {\n\n" +
                        "\tprivate Mediator mediator;\n\n" +
                        "\tpublic Colleague(Mediator mediator) { this.mediator = mediator; }\n\n" +
                        "\tpublic void send(Message message) { mediator.send(message); }\n\n" +
                        "\tpublic Mediator getMediator() { return mediator; }\n\n" +
                        "\tpublic abstract void receive(Message message);\n\n" +
                        "}\n");
        FileData message = createFileData(path + "Message.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public interface Message {\n\n" +
                        "\tpublic String toString();\n\n" +
                        "}\n");
        this.fileDataTab[0] = mediator;
        this.fileDataTab[1] = colleague;
        this.fileDataTab[2] = message;
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
