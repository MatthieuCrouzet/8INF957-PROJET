package designpattern;

import utils.Separator;

import java.io.*;
/**
 * Created by Baptiste BURON on 19/03/2017.
 */
public class BuilderPatternCreator extends FilesCreator{
	
	String packageName = "builder";

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
                		"/**\n * Represents a complex object under construction\n */\n" +
                        "public class Product {\n\n" +
                		"\t/**\n\t * A method to be used by a Builder class\n\t */\n" +
                        "\tpublic void doSomething();{  }\n\n"+
                		"\t/**\n\t * Another method to be used by a Builder class\n\t */\n" +
                        "\tpublic void doSomethingElse();{ // }\n\n"+
                        "}\n");
        FileData builder = createFileData(path + "Builder.java",
                "package designpattern." + packageName + "; \n\n" +
                		"/**\n * An abstract class that contains a product\n */\n" +
                        "public abstract class Builder{\n\n" +
                		"\tprivate Product product;\n\n"+
                		"\t/**\n\t * Gets a product instance \n\t * @return product An instance from the Product class\n\t */\n" +
                        "\tpublic Product getProduct(){ \n\t\treturn product;\n\t}\n\n" +
                		"\t/**\n\t * Creates a new Product\n\t */\n" +
                        "\tpublic void createNewProduct(){ \n\t\tthis.product = new Product();\n\t}\n\n" +
                		"\t/**\n\t * An abstract method to build something with one of the product methods\n\t */\n" +
                        "\tpublic abstract void buildSomething();\n\n" +
                		"\t/**\n\t * Another abstract method to build something else with one of the product methods\n\t */\n" +
                        "\tpublic abstract void buildSomethingElse();\n\n" +
                        "}\n");
        FileData concreteBuilder = createFileData(path + "ConcreteBuilder.java",
                "package designpattern." + packageName + "; \n\n" +
                		"/**\n * Provides an implementation of the Builder class\n * Builds and matches up different parts of the product\n */\n" +
                        "public class ConcreteBuilder extends Builder{\n\n" +
                		"\t/**\n\t * Builds something with one of the product methods\n\t */\n" +
                		"\tpublic void buildSomething(){\n\t\tthis.product.doSomething(); \n\t}\n\n" +
                		"\t/**\n\t * Builds something else with one of the product methods\n\t */\n" +
                		"\tpublic void buildSomethingElse(){\n\t\tthis.product.doSomethingElse(); \n\t}\n\n" + 
                        "}\n");
        FileData director = createFileData(path + "Director.java",
                "package designpattern." + packageName + "; \n\n" +
                		"/**\n * Builds an object using the method provided by Builder\n */\n" +
                        "public class Director{ \n\n" +
                		"\tprivate Builder builder; \n\n"+
                		"\t/**\n\t * Sets a builder instance \n\t * @param b an instance from Builder class\n\t */\n" +
                        "\tpublic void setBuilder(Builder b) { \n\t\tbuilder = b;\n\t}\n\n" +
                		"\t/**\n\t * Gets a product instance \n\t * @return product An instance from the Product class\n\t */\n" +
                        "\tpublic Product getProduct(){ \n\t\treturn product;\n\t}\n\n" +
                		"\t/**\n\t * Builds a product with methods from the Builder class\n\t */\n" +
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
