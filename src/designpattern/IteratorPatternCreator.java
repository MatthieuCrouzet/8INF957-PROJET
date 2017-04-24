package designpattern;

import java.io.*;

import utils.Separator;

/**
 * Created by Matthieu CROUZET on 13/03/2017.
 */
public class IteratorPatternCreator extends FilesCreator {

    String packageName = "iterator";

    String path = MAIN_PATH + Separator.SEPARATOR + packageName + Separator.SEPARATOR;

    public IteratorPatternCreator(){
        super();
        int nbFiles = 6;
        File f = new File(path + Separator.SEPARATOR + "example" + Separator.SEPARATOR);
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles" + Separator.SEPARATOR + "iterator.bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData iterator = createFileData(path + "IteratorAspect.aj",
                "package designpattern." + packageName + "; \n\n" +
                		"/**\n * Represents an Iterator pattern in AspectJ\n */\n" +
                        "public abstract aspect IteratorAspect {\n\n" +
                		"\t/**\n\t * Interface that defines operations to create an iterator\n\t */\n" +
                        "\tpublic interface Aggregate {\n\n" +
                		"\t\t/**\n\t\t * Method which creates an iterator and return it \n\t\t * @return Iterator the iterator which is created\n\t\t */ \n" +
                        "\t\tpublic Iterator createIterator();\n" +
                		"\t\t/**\n\t\t * Method which creates a reverse iterator and return it \n\t\t * @return Iterator the reverse iterator which is created\n\t\t */ \n" +
                        "\t\tpublic Iterator createReverseIterator();\n\n" +
                        "\t}\n\n" +
                        "}\n");
        FileData collection = createFileData(path + "Collection.java",
                "package designpattern." + packageName + "; \n\n" +
                		"/**\n * A collection used by an iterator\n */\n" +
                        "public interface Collection {\n\n" +
                		"\t/**\n\t * Counts the size of the collection\n\t * @return the size of the collection */\n" +
                        "\tpublic int count();\n" +
                		"\t/**\n\t * Adds an object to the collection\n\t * @param o an object to append to the collection \n\t * @return true if an object is added */\n" +
                        "\tpublic boolean append(Object o);\n" +
                		"\t/**\n\t * Removes an object to the collection\n\t * @param o an object to remove to the collection \n\t * @return true if an object is removed */\n" +
                        "\tpublic boolean remove(Object o);\n" +
                		"\t/**\n\t * Gets an object located at an index of the collection\n\t * @param index an integer that allows to get the object located in the collection \n\t * @return the object that is searched at the index */\n" +
                        "\tpublic Object get(int index);\n" +
                        "}\n");
        String examplePath = path  + Separator.SEPARATOR + "example" + Separator.SEPARATOR;
        FileData employeeCollection = createFileData(examplePath + "EmployeeCollection.java",
                "package designpattern." + packageName + ".example; \n\n" +
                        "import java.util.*;\n\n" +
                        "public class EmployeeCollection implements Collection {\n\n" +
                        "\tprivate List<Employee> employees;\n\n" +
                        "\tpublic EmployeeCollection() {\n\t\temployees = new ArrayList<Employee>();\n\t}\n\n" +
                        "\tpublic int count(){\n\t\temployees.size();\t}\n\n" +
                        "\tpublic boolean append(Object o){\n\t\temployees.add(o);\n\t}\n\n" +
                        "\tpublic boolean remove(Object o){\n\t\temployees.remove(o);\n\t}\n\n" +
                        "\tpublic Object get(int index){\n\t\temployees.get(index);\n\t}\n\n" +
                        "}\n");
        FileData employeeIterator = createFileData(examplePath + "EmployeeIterator.aj",
                "package designpattern." + packageName + ".example; \n\n" +
                        "public aspect EmployeeIterator extends IteratorAspect {\n\n" +
                        "\tdeclare parents : EmployeeCollection implements Aggregate;\n\n" +
                        "\tpublic Iterator EmployeeCollection.createIterator(){\n\t\treturn new EmployeeIterator(this, true);\n\t}\n\n" +
                        "\tpublic Iterator EmployeeCollection.createReverseIterator(){\n\t\treturn new EmployeeIterator(this, false);\n\t}\n\n" +
                        "}\n");
        FileData employee = createFileData(examplePath + "Employee.java",
                "package designpattern." + packageName + "example; \n\n" +
                        "public class Employee {\n\n" +
                        "\tprivate static int nextID = 1;" +
                        "\tprivate String name;\n\n" +
                        "\tpublic Employee(String name){\n\t\tthis.name = name;\n\t\tnextID++;\n\t}\n\n" +
                        "\tpublic Employee(){\n\t\tthis.name = \"anonymous\" + nextID;\n\t\tnextID++\n\t}\n\n" +
                        "\tpublic String getName() {\n\t\treturn name;\n\t}\n\n" +
                        "\tpublic void setName(String newName) {\n\t\tname = newName;\n\t}\n\n" +
                        "}\n");
        FileData main = createFileData(examplePath + "Main.java",
                "package designpattern." + packageName + "example; \n\n" +
                        "public class Main {" +
                        "\tpublic static void main(String[] args) {\n\n" +
                        "\t\tEmployeeCollection ec = new EmployeeCollection();\n" +
                        "\t\tec.append(new Employee(\"John Doe\"));\n" +
                        "\t\tec.append(new Employee(\"Jane Doe\"));\n" +
                        "\t\tec.append(new Employee(\"Foo foo\"));\n\n" +
                        "\t\tIterator it = ec.createIterator();\n" +
                        "\t\tIterator reverseIt = ec.createReverseIterator();\n" +
                        "\t\twhile(it.hasNext()) {\n\t\t\tSystem.out.println((Employee) it).getName());\n\t\t}\n" +
                        "\t\twhile(reverseIt.hasNext()) {\n\t\t\tSystem.out.println((Employee) reverseIt).getName());\n\t\t}\n\n" +
                        "\t}\n\n" +
                        "}\n");
        this.fileDataTab[0] = iterator;
        this.fileDataTab[1] = collection;
        this.fileDataTab[2] = employeeCollection;
        this.fileDataTab[3] = employeeIterator;
        this.fileDataTab[4] = employee;
        this.fileDataTab[5] = main;
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
