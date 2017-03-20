package designpattern;

import utils.Separator;

import java.io.*;
/**
 * Created by Baptiste BURON on 19/03/2017.
 */
public class AdapterPatternCreator extends FilesCreator{
	
	String packageName = "adapter";

    String path = MAIN_PATH + Separator.SEPARATOR + packageName + Separator.SEPARATOR;

    public AdapterPatternCreator(){
        super();
        int nbFiles = 2;
        File f = new File(path + Separator.SEPARATOR + "example" + Separator.SEPARATOR);
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles" + Separator.SEPARATOR + packageName + ".bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData adaptee = createFileData(path + "Adaptee.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public interface Adaptee {\n\n" +
                        "\tpublic void methodB();//method to be adapt by Adapter class\n\n" +
                        "}\n");
        FileData adapter = createFileData(path + "Adapter.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public class Adapter{\n\n" +
                		"\tprivate Adaptee adaptee;"+
                        "\tpublic Adapter(Adaptee instance){ \n\t\tthis.adaptee = instance;\n\t}\n\n" +
                        "\tpublic void methodA() { \n\t\t//adapt methodB utilisation \n\t\tthis.adaptee.methodB(); \n\t}\n\n" +
                        "}\n");
        this.fileDataTab[0] = adaptee;
        this.fileDataTab[1] = adapter;
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
