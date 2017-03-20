package designpattern;

import java.io.*;

import utils.Separator;

/**
 * Created by Matthieu CROUZET on 20/03/2017.
 */
public class InterpreterPatternCreator extends FilesCreator {

    String packageName = "interpreter";

    String path = MAIN_PATH + Separator.SEPARATOR + packageName + Separator.SEPARATOR;

    public InterpreterPatternCreator(){
        super();
        int nbFiles = 1;
        File f = new File(path + Separator.SEPARATOR + "example" + Separator.SEPARATOR);
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles" + Separator.SEPARATOR + packageName + ".bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData expression = createFileData(path + "Expression.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public interface Expression {\n\n" +
                        "\tpublic boolean interpret(String context);\n\n" +
                        "}\n");
        this.fileDataTab[0] = expression;
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
