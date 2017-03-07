package designpattern;

import java.io.File;
import java.io.Serializable;

/**
 * Created by Matthieu CROUZET on 06/03/2017.
 */
public class FileData implements Serializable{

    public String filename;
    public String content;

    public FileData(String filename, String content){
        this.filename = filename;
        this.content = content;
    }

}
