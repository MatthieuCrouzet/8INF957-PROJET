package designpattern;

import java.io.*;
import main.*;

/**
 * Created by Matthieu CROUZET on 05/03/2017.
 */
public abstract class FilesCreator {

    public final static String MAIN_PATH = MyMain.userData.getProjectFolder() + "\\src\\designpattern";

    public String binaryFileName;
    public FileData[] fileDataTab;

    public FilesCreator(){}

    //TODO Clear these two methods from all FilesCreator when all binary files are created
    //But not for ProjectCreator
    protected abstract void createBinaryFile();
    protected FileData createFileData(String name, String content){
        return new FileData(name, content);
    }

    private void load(){
        try {
            FileInputStream file = new FileInputStream(binaryFileName);
            ObjectInputStream is = new ObjectInputStream(file);
            fileDataTab = (FileData[]) is.readObject();
            is.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void write(FileData fd){
        String filename = fd.filename;
        String content = fd.content;
        try{
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.println(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute(){
        createBinaryFile();
        load();
        for(int i = 0; i < fileDataTab.length; i++) {
            write(fileDataTab[i]);
        }
    }


}
