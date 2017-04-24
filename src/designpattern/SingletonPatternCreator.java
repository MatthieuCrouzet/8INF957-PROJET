package designpattern;

import java.io.*;
import java.util.Observer;

import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
import utils.Separator;

/**
 * Created by Matthieu CROUZET on 13/03/2017.
 */
public class SingletonPatternCreator extends FilesCreator {

    String packageName = "singleton";

    String path = MAIN_PATH + Separator.SEPARATOR + packageName + Separator.SEPARATOR;

    public SingletonPatternCreator(){
        super();
        int nbFiles = 4;
        File f = new File(path + Separator.SEPARATOR + "example" + Separator.SEPARATOR);
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles" + Separator.SEPARATOR + packageName + ".bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData singleton = createFileData(path + "SingletonProtocol.aj",
                "package designpattern." + packageName + "; \n\n" +
                		"\t/**\n\t * Represents a Singleton pattern in AspectJ */\n" +
                        "public abstract aspect SingletonProtocol {\n\n" +
                        "\tprivate Hashable singletons = new Hashable();\n\n" +
                		"\t/**\n\t * Interface that defines a Singleton\n\t */\n" +
                        "\tpublic interface Singleton {};\n\n" +
                		"\t/**\n\t * A pointcut called on exclusions\n\t */\n" +
                        "\tprotected pointcut protectionExclusions();\n\n" +
                        "\tObject around() : call((Singleton+).new(..)) && ! protectionExclusions(){\n" +
                        "\t\tClass singleton = thisJoinPoint.getSignature().getDeclaringType();\n" +
                        "\t\tif (singletons.get(singleton) == null)  { singletons.put(singleton, proceed()); }\n" +
                        "\t\treturn singletons.get(singleton);\n}\n\n" +
                        "}\n");
        String examplePath = path  + Separator.SEPARATOR + "example" + Separator.SEPARATOR;
        FileData screenSingleton = createFileData(examplePath + "ScreenSingleton.aj",
                "package designpattern." + packageName + "example; \n\n" +
                        "public aspect ScreenSingleton extends SingletonProtocol {\n\n" +
                        "\tdeclare parents: \n" +
                        "\t\tScreen implements Singleton;\n\n" +
                        "\tprotected pointcut protectionExclusions(): \n" +
                        "\t\tcall((Screen+).new(..));\n\n" +
                        "}\n");
        FileData screen = createFileData(examplePath + "Screen.java",
                "package designpattern." + packageName + "example; \n\n" +
                        "public class Screen {\n\n" +
                        "\tprivate int width, height;" +
                        "\tpublic Screen() { width = 100; heigth = 100; }\n\n" +
                        "\tpublic void setWidth(int w) { width = w; } \n\n" +
                        "\tpublic void setHeight(int h) { heigth = h; } \n\n" +
                        "\tpublic int getWidth() { return width; } \n\n" +
                        "\tpublic int getHeight() { return height; } \n\n" +
                        "}\n");
        FileData main = createFileData(examplePath + "Main.java",
                "package designpattern." + packageName + "example; \n\n" +
                        "public class Main {\n\n" +
                        "\tpublic void main() {" +
                        "\t\tScreen s1 = new Screen();\n" +
                        "\t\tScreen s2 = new Screen();\n" +
                        "\t\ts1.setWidth(50);\n" +
                        "\t\tSystem.out.println(\"s1 width = \" + s1.getWidth() + \"\ns2 width = \" + s2.getWidth());\n" +
                        "\t}\n\n" +
                        "}\n");
        this.fileDataTab[0] = singleton;
        this.fileDataTab[1] = screenSingleton;
        this.fileDataTab[2] = screen;
        this.fileDataTab[3] = main;
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
