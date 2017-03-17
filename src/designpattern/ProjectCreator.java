package designpattern;

import main.MyMain;
import utils.Separator;

import java.io.*;

/**
 * Created by Matthieu CROUZET on 06/03/2017.
 */
public class ProjectCreator extends FilesCreator {

    private String packagename = MyMain.userData.getProjectName().toLowerCase().replaceAll(Separator.SEPARATOR + "s+", "");
    private String path = MyMain.userData.getProjectFolder() + Separator.SEPARATOR + "src" + Separator.SEPARATOR + packagename + Separator.SEPARATOR;

    public ProjectCreator() {
        super();
        int nbFiles = 1;
        File f = new File(path);
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles" + Separator.SEPARATOR + "project.bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData main = createFileData(path + "Main.java",
                "package " + packagename + "; \n\n" +
                        "public class Main {\n\n" +
                        "\tpublic static void main(String[] args) {\n" +
                        "\n\t}\n");
        this.fileDataTab[0] = main;
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
