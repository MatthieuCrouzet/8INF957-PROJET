package designpattern;

import utils.Separator;

import java.io.*;
/**
 * Created by Baptiste BURON on 19/03/2017.
 */
public class CompositePatternCreator extends FilesCreator{
	
	String packageName = "composite";

    String path = MAIN_PATH + Separator.SEPARATOR + packageName + Separator.SEPARATOR;

    public CompositePatternCreator(){
        super();
        int nbFiles = 4;
        File f = new File(path + Separator.SEPARATOR + "example" + Separator.SEPARATOR);
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles" + Separator.SEPARATOR + packageName + ".bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData component = createFileData(path + "Component.java",
                "package designpattern." + packageName + "; \n\n" +
                		"/**\n * Defines all components, even those which are composed\n * Declares an interface for the handle by default\n */\n" +
                        "public interface Component {\n\n" +
                		"\t/**\n\t * Declares an operation\n\t */\n" +
                        "\tpublic void operation();\n\n" +
                        "}\n");
        FileData composite = createFileData(path + "Composite.java",
                "package designpattern." + packageName + "; \n\n" +
                		"import java.util.List; \n" +
                		"import java.util.ArrayList; \n" +
                		"/**\n * Represents a component that can have subelements\n * Stores child components and allows to acceed to it \n * Implements a behavior by using children\n */\n" +
                        "public class Composite implements Component {\n\n" +
                        "\tprivate List<Component> childComponents = new ArrayList<Component>();\n\n" +
                		"\t/**\n\t * Implements an operation\n\t */\n" +
                        "\tpublic void operation(){ \n\t\tfor(Component component : childComponents) {\n\t\t\t component.operation(); \n\t\t}\n\t}\n\n" +
                		"\t/**\n\t * Adds a component instance \n\t * @param component an instance which implements Component interface\n\t */\n" +
                        "\tpublic void add(Component component) { \n\t\tchildComponents.add(component); \n\t}\n\n" +
                		"\t/**\n\t * Removes a component instance \n\t * @param component an instance which implements Component interface\n\t */\n" +
                        "\tpublic void remove(Component component) { \n\t\tchildComponents.remove(component); \n\t}\n\n" +
                        "}\n");
        FileData leaf = createFileData(path + "Leaf.java",
                "package designpattern." + packageName + "; \n\n" +
                		"/**\n * Represents components that do not have child\n * Implements the behavior by default\n */\n" +
                        "public class Leaf implements Component {\n\n" +
                		"\t/**\n\t * Implements an operation\n\t */\n" +
                        "\tpublic void operation(){\n\t\t//Operation to manage \n\t}\n\n" +
                        "}\n");
        
        FileData compositeProtocol = createFileData(path + "CompositeProtocol.aj",
                "package designpattern." + packageName + "; \n\n" +
                		"import java.util.List; \n" +
                		"import java.util.ArrayList; \n" +
                		"/**\n * Represents a Composite pattern in AspectJ\n */\n" +
                        "public abstract aspect CompositeProtocol {\n\n" +
                        "\t// Centralized structure \n" +
                        "\tprivate Map perComponentChildren = new WeakHashMap();\n\n" +
                		"\t/**\n\t * Gets child vector from Component\n\t * @param s an instance of Component\n\t * @return vector A vector of child components\n\t */\n" +
                        "\tprivate Vector getChildren(Component s){ \n\t\tVector children = (Vector) perComponentChildren.get(s);\n\t\t//... lazy instantiation of children vector if none\n\t\treturn children;\n\t}\n\n" +
                        "\t// Registration interface \n" +
                		"\t/**\n\t * Adds a component instance \n\t * @param cs an instance from Composite\n\t * @param cn an instance which implements Component interface\n\t */\n" +
                        "\tpublic void addChild(Composite cs, Component cn) { \n\t\tgetChildren(cs).add(cn); \n\t}\n\n" +
                		"\t/**\n\t * Removes a component instance \n\t * @param cs an instance from Composite\n\t * @param cn an instance which implements Component interface\n\t */\n" +
                        "\tpublic void removeChild(Composite cs,Component cn) { \n\t\tgetChildren(cs).remove(cn); \n\t}\n\n" +
                		"\t/**\n\t * Gets a vector from a child component iterator\n\t * @param c an instance of Component\n\t * @return vector A vector from child component iterator\n\t */\n" +
                        "\tprivate Vector traverseChildren(Component c){ \n\t\treturn getChildren(c).iterator();\n\t}\n\n" +
                        "\t// Collaboration with Visitor \n" +
                		"\t/**\n\t * Collaborates with Visitor design pattern in AspectJ\n\t */\n" +
                        "\tdeclare parents: Component extends VisitableNode;\n" +
                		"\t/**\n\t * Recurses all child of component with Visitor design pattern \n\t * @param c an instance from Component\n\t * @param visitor an instance which implements Visitor design pattern\n\t */\n" +
                        "\tpublic void recurseAll(Component c, Visitor visitor){ \n\t\tvisitor.visit(VisitableNode) c);\n\t\tfor (Iterator ite = traverseChildren(c); ite.hasNext();) \n\t\t\trecurseAll((Component) ite.next(), visitor);\n\t}\n\n" +
                        "\t// Other collaborations with Visitor... \n" +
                        "}\n");
        this.fileDataTab[0] = component;
        this.fileDataTab[1] = composite;
        this.fileDataTab[2] = leaf;
        this.fileDataTab[3] = compositeProtocol;
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
