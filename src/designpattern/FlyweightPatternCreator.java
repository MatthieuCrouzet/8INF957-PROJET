package designpattern;

import java.io.*;

import utils.Separator;

/**
 * Created by Matthieu CROUZET on 13/03/2017.
 */
public class FlyweightPatternCreator extends FilesCreator {

    String packageName = "flyweight";

    String path = MAIN_PATH + Separator.SEPARATOR + packageName + Separator.SEPARATOR;

    public FlyweightPatternCreator(){
        super();
        int nbFiles = 2;
        File f = new File(path + Separator.SEPARATOR + "example" + Separator.SEPARATOR);
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles" + Separator.SEPARATOR + packageName + ".bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData flyweight = createFileData(path + "Flyweight.java",
                "package designpattern." + packageName + "; \n\n" +
                		"/**\n * An interface that represents datas \n */\n" +
                        "public interface Flyweight {\n\n" +
                        "\tpublic String key;\n\n" +
                		"\t/**\n\t * Defines a method to be used by a static instance from FlyweightFactory\n\t * @param args Represents any parameters in an method\n\t */\n" +
                        "\tpublic void do(Object... args);\n\n" +
                        "}\n");
        FileData flyweightFactory = createFileData(path + "FlyweightFactory.java",
                "package designpattern." + packageName + "; \n\n" +
                        "import java.util.HashMap;\n" +
                        "import java.util.Map;\n\n" +
                		"/**\n * Creates Flyweight itself \n */\n" +
                        "public class FlyweightFactory {\n\n" +
                        "\tprivate static FlyweightFactory INSTANCE  = new FlyweightFactory();\n\n" +
                        "\tprivate Map<String, Flyweight> flyweightPool;\n\n" +
                		"\t/**\n\t * Creates an HashMap of Flyweight\n\t */\n" +
                        "\tprivate FlyweightFactory() { flyweightPool = new HashMap<String, Flyweight>(); }\n\n" +
                		"\t/**\n\t * Gets itself an instance of FlyweightFactory \n\t * @return INSTANCE The static instance of FlyweightFactory\n\t */\n" +
                        "\tpublic static FlyweightFactory getInstance() { return INSTANCE; }\n\n" +
                		"\t/**\n\t * Gets an instance of Flyweight from an an Hashmap \n\t * @param key A string associated to an instance of Flyweight class\n\t * @return an instance of Flyweight\n\t */\n" +
                        "\tpublic Flyweight getFlyweight(String key) { return flyweightPool.get(key); }\n\n" +
                		"\t/**\n\t * Adds an instance of Flyweight \n\t * @param f An instance of Flyweight class to be added\n\t */\n" +
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
