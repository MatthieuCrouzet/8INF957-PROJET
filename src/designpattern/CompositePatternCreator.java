package designpattern;

import main.MyMain;
import utils.Separator;

import java.io.*;
/**
 * Created by Baptiste BURON on 19/03/2017.
 */
public class CompositePatternCreator extends FilesCreator{
	
	String packageName = "composite";

    String path = MAIN_PATH + Separator.SEPARATOR + packageName + Separator.SEPARATOR;

    public CompositePatternCreator(){
        super();
        int nbFiles = 3;
        File f = new File(path + Separator.SEPARATOR + "example" + Separator.SEPARATOR);
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles" + Separator.SEPARATOR + packageName + ".bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData component = createFileData(path + "Component.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public interface Component {\n\n" +
                        "\tpublic void operation();\n\n" +
                        "}\n");
        FileData composite = createFileData(path + "Composite.java",
                "package designpattern." + packageName + "; \n\n" +
                		"import java.util.List; \n" +
                		"import java.util.ArrayList; \n" +
                        "public class Composite implements Component {\n\n" +
                        "\tprivate List<Component> childComponents = new ArrayList<Component>();\n\n" +
                        "\tpublic void operation(){ \n\t\tfor(Component component : childComponents) {\n\t\t\t component.operation(); \n\t\t}\n\t}\n\n" +
                        "\tpublic void add(Component component) { \n\t\tchildComponents.add(component); \n\t}\n\n" +
                        "\tpublic void remove(Component component) { \n\t\tchildComponents.remove(component); \n\t}\n\n" +
                        "}\n");
        FileData leaf = createFileData(path + "Leaf.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public class Leaf implements Component {\n\n" +
                        "\tpublic void operation(){\n\t\t//Operation to manage \n\t}\n\n" +
                        "}\n");
        this.fileDataTab[0] = component;
        this.fileDataTab[1] = composite;
        this.fileDataTab[2] = leaf;
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
