package designpattern;

import java.io.*;

import utils.Separator;

/**
 * Created by Matthieu CROUZET on 20/03/2017.
 */
public class StatePatternCreator extends FilesCreator {

    String packageName = "state";

    String path = MAIN_PATH + Separator.SEPARATOR + packageName + Separator.SEPARATOR;

    public StatePatternCreator(){
        super();
        int nbFiles = 2;
        File f = new File(path + Separator.SEPARATOR + "example" + Separator.SEPARATOR);
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles" + Separator.SEPARATOR + packageName + ".bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData state = createFileData(path + "State.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public interface State {\n\n" +
                        "\tpublic void doAction(Context context);\n\n" +
                        "}\n");
        FileData context = createFileData(path + "Context.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public class Context {\n\n" +
                        "\tprivate State state;\n\n" +
                        "\tpublic Context(){ state = null; }\n\n" +
                        "\tpublic void setState(State state){ this.state = state; }\n\n" +
                        "\tpublic State getState(){ return state; }\n\n" +
                        "}\n");
        this.fileDataTab[0] = state;
        this.fileDataTab[1] = context;
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
