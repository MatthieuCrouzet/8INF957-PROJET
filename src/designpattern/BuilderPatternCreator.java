package designpattern;

import utils.Separator;

import java.io.*;
/**
 * Created by Baptiste BURON on 19/03/2017.
 */
public class BuilderPatternCreator extends FilesCreator{
	
	String packageName = "adapter";

    String path = MAIN_PATH + Separator.SEPARATOR + packageName + Separator.SEPARATOR;

    public BuilderPatternCreator(){
        super();
        int nbFiles = 4;
        File f = new File(path + Separator.SEPARATOR + "example" + Separator.SEPARATOR);
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles" + Separator.SEPARATOR + packageName + ".bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData product= createFileData(path + "Product.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public class Product {\n\n" +
                        "\tpublic void doSomething();{ //method to be used by builder }\n\n"+
                        "\tpublic void doSomethingElse();{ //Other method to be used by builder }\n\n"+
                        "}\n");
        FileData builder = createFileData(path + "Builder.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public abstract class Builder{\n\n" +
                		"\tprivate Product product;"+
                        "\tpublic Product getProduct(){ \n\t\treturn product;\n\t}\n\n" +
                        "\tpublic void createNewProduct(){ \n\t\tthis.product = new Product();\n\t}\n\n" +
                        "\tpublic abstract void buildSomething();\n\n" +
                        "\tpublic abstract void buildSomethingElse();\n\n" +
                        "}\n");
        FileData concreteBuilder = createFileData(path + "ConcreteBuilder.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public class ConcreteBuilder extends Builder{\n\n" +
                		"\tpublic void buildSomething(){\n\t\tthis.product.doSomething(); \n\t}\n\n" +
                		"\tpublic void buildSomethingElse(){\n\t\tthis.product.doSomethingElse(); \n\t}\n\n" + 
                        "}\n");
        FileData director = createFileData(path + "Director.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public class Director{ \n\n" +
                		"\tprivate Builder builder; \n\n"+
                        "\tpublic void setBuilder(Builder b) { \n\t\tbuilder = b;\n\t}\n\n" +
                        "\tpublic Product getProduct(){ \n\t\treturn product;\n\t}\n\n" +
                		"\tpublic void buildProduct(){\n\t\tthis.builder.doSomething();\n\t\tthis.builder.doSomethingElse(); \n\t}\n\n" + 
                        "}\n");
        
        this.fileDataTab[0] = product;
        this.fileDataTab[1] = builder;
        this.fileDataTab[2] = concreteBuilder;
        this.fileDataTab[3] = director;

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
