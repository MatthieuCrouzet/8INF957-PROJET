package designpattern;

import java.io.*;

/**
 * Created by Matthieu CROUZET on 13/03/2017.
 */
public class SingletonPatternCreator extends FilesCreator {

    String packageName = "singleton";

    String path = MAIN_PATH + "\\" + packageName + "\\";

    public SingletonPatternCreator(){
        super();
        int nbFiles = 1;
        File f = new File(path + "\\example\\");
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles\\" + packageName + ".bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData singleton = createFileData(path + "Singleton.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public class Singleton {\n\n" +
                        "\tprivate Singleton;\n\n" +
                        "\tprivate static Singleton INSTANCE = new Singleton();\n\n" +
                        "\tpublic static Singleton getInstance(){ return INSTANCE; }\n\n" +
                        "}\n");
        this.fileDataTab[0] = singleton;
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
