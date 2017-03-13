package designpattern;

import java.io.*;

/**
 * Created by Matthieu CROUZET on 13/03/2017.
 */
public class FlyweightPatternCreator extends FilesCreator {

    String packageName = "flyweight";

    String path = MAIN_PATH + "\\" + packageName + "\\";

    public FlyweightPatternCreator(){
        super();
        int nbFiles = 2;
        File f = new File(path + "\\example\\");
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles\\" + packageName + ".bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData flyweight = createFileData(path + "Flyweight.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public interface Flyweight {\n\n" +
                        "\tpublic String key;\n\n" +
                        "\tpublic void do(Object... args);\n\n" +
                        "}\n");
        FileData flyweightFactory = createFileData(path + "FlyweightFactory.java",
                "package designpattern." + packageName + "; \n\n" +
                        "import java.util.HashMap;\n" +
                        "import java.util.Map;\n\n" +
                        "public class FlyweightFactory {\n\n" +
                        "\tprivate static FlyweightFactory INSTANCE  = new FlyweightFactory();\n\n" +
                        "\tprivate Map<String, Flyweight> flyweightPool;\n\n" +
                        "\tprivate FlyweightFactory() { flyweightPool = new HashMap<String, Flyweight>(); }\n\n" +
                        "\tpublic static FlyweightFactory getInstance() { return INSTANCE; }\n\n" +
                        "\tpublic Flyweight getFlyweight(String key) { return flyweightPool.get(key); }\n\n" +
                        "\tpublic void addFlyweight(Flyweight f) { flyweightPool.put(f.key, f); }\n\n" +
                        "}\n");
        this.fileDataTab[0] = flyweight;
        this.fileDataTab[1] = flyweightFactory;
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
