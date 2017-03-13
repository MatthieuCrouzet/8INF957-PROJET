package designpattern;

import java.io.*;

/**
 * Created by Matthieu CROUZET on 13/03/2017.
 */
public class IteratorPatternCreator extends FilesCreator {

    String packageName = "iterator";

    String path = MAIN_PATH + "\\" + packageName + "\\";

    public IteratorPatternCreator(){
        super();
        int nbFiles = 2;
        File f = new File(path + "\\example\\");
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles\\iterator.bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData iterator = createFileData(path + "Iterator.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public interface Iterator {\n\n" +
                        "\tpublic boolean hasNext();\n\n" +
                        "\tpublic Object next();\n\n" +
                        "}\n");
        FileData container = createFileData(path + "Container.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public interface Container {\n\n" +
                        "\tpublic Iterator getIterator();\n\n" +
                        "}\n");
        this.fileDataTab[0] = container;
        this.fileDataTab[1] = iterator;
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
