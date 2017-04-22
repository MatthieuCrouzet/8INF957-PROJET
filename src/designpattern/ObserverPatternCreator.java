package designpattern;

import utils.Separator;

import java.io.*;
import java.util.Observer;

/**
 * Created by Baptiste BURON on 19/03/2017.
 */
public class ObserverPatternCreator extends FilesCreator {

    String packageName = "observer";

    String path = MAIN_PATH + Separator.SEPARATOR + packageName + Separator.SEPARATOR;

    public ObserverPatternCreator(){
        super();
        int nbFiles = 5;
        File f = new File(path + Separator.SEPARATOR + "example" + Separator.SEPARATOR);
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles" + Separator.SEPARATOR + packageName + ".bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData observerProtocol = createFileData(path + "ObserverProtocol.aj",
                "package designpattern." + packageName + "; \n\n" +
                        "public abstract aspect ObserverProtocol {\n\n" +
                        "\tprotected interface Subject { }\n\n" +
                        "\tprotected interface Observer { }\n\n" +
                        "\tprivate WeakHashMap perSubjectObservers;\n\n" +
                        "\tprotected List getObservers(Subject s) {\n" +
                        "\t\tif (perSubjectObservers == null) {\n\t\t\tperSubjectObservers = new WeakHashMap();\n\t\t}\n" +
                        "\t\tList observers = (List)perSubjectObservers.get(s);\n" +
                        "\t\tif ( observers == null ) {\n\t\t\tobservers = new LinkedList();\n\t\t\tperSubjectObservers.put(s, observers);\n\t\t}\n" +
                        "\t\treturn observers;\n\t}\n\n" +
                        "\tpublic void addObserver(Subject s,Observer o){\n\t\tgetObservers(s).add(o);\n\t}\n\n" +
                        "\tpublic void removeObserver(Subject s,Observer o){\n\t\tgetObservers(s).remove(o);\n\t}\n\n" +
                        "\tabstract protected pointcut subjectChange(Subject s);\n" +
                        "\tabstract protected void updateObserver(Subject s, Observer o);\n" +
                        "\tafter(Subject s): subjectChange(s) {\n" +
                        "\t\tIterator iter = getObservers(s).iterator();\n" +
                        "\t\twhile ( iter.hasNext() ) {\n\t\t\tupdateObserver(s, ((Observer)iter.next()));\n\t\t}\n\t}" +
                        "}\n");

        String examplePath = path  + Separator.SEPARATOR + "example" + Separator.SEPARATOR;
        FileData client = createFileData(examplePath + "Client.java",
                "package designpattern." + packageName + "example; \n\n" +
                        "public class Client {\n\n" +
                        "\tprivate static int nextID = 1;" +
                        "\tprivate String name;\n\n" +
                        "\tpublic Client(String name){\n\t\tthis.name = name;\n\t\tnextID++;\n\t}\n\n" +
                        "\tpublic Client(){\n\t\tthis.name = \"anonymous\" + nextID;\n\t\tnextID++\n\t}\n\n" +
                        "\tpublic String getName() {\n\t\treturn name;\n\t}\n\n" +
                        "\tpublic void setName(String newName) {\n\t\tname = newName;\n\t}\n\n" +
                        "}\n");
        FileData product = createFileData(examplePath + "Product.java",
                "package designpattern." + packageName + "example; \n\n" +
                        "public class Product {\n\n" +
                        "\tprivate float price;\n\n" +
                        "\tprivate String name;\n\n" +
                        "\tpublic Product(String name, float price) {\n\t\tthis.name = name;\n\t\tthis.price = price; \n\t}" +
                        "\tpublic float getPrice() {\n\t\treturn price;\n\t}\n\n" +
                        "\tpublic void setPrice(float newPrice) {\n\t\tprice = newPrice;\n\t}\n\n" +
                        "\tpublic String getName() {\n\t\treturn name;\n\t}\n\n" +
                        "\tpublic void setName(String newName) {\n\t\tname = newName;\n\t}\n\n" +
                        "}\n");
        FileData productObserver = createFileData(examplePath + "ProductObserver.aj",
                "package designpattern." + packageName + "example; \n\n" +
                        "public aspect ProductObserver extends ObserverProtocol {\n\n" +
                        "\tdeclare parents: Product implements Subject;\n\n"+
                        "\tdeclare parents: Client implements Observer;\n\n" +
                        "\tprotected pointcut subjectChange(Subject s):\n" +
                        "\t\t(call(void Product.setPrice(float)) && target(s); \n\n" +
                        "\tprotected void updateObserver(Subject s, Observer o) { \n" +
                        "\t\tSystem.out.println(\"Hello \" + ((Client) o).getName() + \",\nthe price of \" + ((Product) s).getName() + \" changes to \" + ((Product) s).getPrice());\n\t}\n\n" +
                        "}\n");
        FileData main = createFileData(examplePath + "Main.java",
                "package designpattern." + packageName + "example; \n\n" +
                        "public class Main {" +
                        "\tpublic static void main(String[] args) {\n\n" +
                        "\t\tProduct a = new Product(\"A\", 15.99f);\n" +
                        "\t\tClient john = new Client(\"John\");\n" +
                        "\t\tClient doe = new Client(\"Doe\");\n\n" +
                        "\t\tProductObserver.aspectOf().addObserver(a, john);\n" +
                        "\t\ta.setPrice(13.0f);\n" +
                        "\t\tProductObserver.aspectOf().addObserver(a, doe);\n" +
                        "\t\ta.setPrice(12.0f)\n\n;" +
                        "}\n");
        this.fileDataTab[0] = observerProtocol;
        this.fileDataTab[1] = client;
        this.fileDataTab[2] = product;
        this.fileDataTab[3] = productObserver;
        this.fileDataTab[4] = main;
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


