package designpattern;

import java.io.*;
import java.util.WeakHashMap;

import utils.Separator;

/**
 * Created by Matthieu CROUZET on 13/03/2017.
 */
public class MediatorPatternCreator extends FilesCreator {

    String packageName = "mediator";

    String path = MAIN_PATH + Separator.SEPARATOR + packageName + Separator.SEPARATOR;

    public MediatorPatternCreator(){
        super();
        int nbFiles = 5;
        File f = new File(path + Separator.SEPARATOR + "example" + Separator.SEPARATOR);
        f.mkdirs(); //Create all directories
        this.fileDataTab = new FileData[nbFiles];
        this.binaryFileName = "binaryfiles" + Separator.SEPARATOR + packageName + ".bin";
    }

    @Override
    protected void createBinaryFile() {
        FileData mediatorAspect = createFileData(path + "MediatorAspect.aj",
                "package designpattern." + packageName + "; \n\n" +
                        "public abstract aspect MediatorAspect {\n\n" +
                        "\tprotected interface Colleague {}\n" +
                        "\tprotected interface Mediator {}\n\n" +
                        "\tprivate WeakHashMap mappingColleagueToMediator = new WeakHashMap( );\n" +
                        "\tprivate Mediator getMediator(Colleague colleague){\n" +
                        "\t\tMediator mediator = (Mediator) mappingColleagueToMediator.get(colleague);" +
                        "\t\treturn mediator;\n\t}\n\n" +
                        "\tpublic void setMediator(Colleague c, Mediator m){\n\t\tmappingColleagueToMediator.put(c, m);\n\t}\n\n" +
                        "\tprotected abstract pointcut change(Colleague c);\n\n" +
                        "\tafter(Colleague c) : change(c) {\n\t\tnotifyMediator(c, getMediator(c));\n\t}\n\n" +
                        "\tprotected abstract void notifyMediator(Colleague c, Mediator m);\n\n" +
                        "}\n");
        String examplePath = path  + Separator.SEPARATOR + "example" + Separator.SEPARATOR;
        FileData chatMediatorAspect = createFileData(examplePath + "ChatMediator.aj",
                "package designpattern." + packageName + "; \n\n" +
                        "public aspect ChatMediatorAspect extends MediatorAspect {\n\n" +
                        "\tdeclare parents : ChatUser implements Colleague;\n" +
                        "\tdeclare parents : ChatMediator implements Mediator;\n\n" +
                        "\tprotected pointcut change(Colleague c) : \n" +
                        "\t\t(execution(void ChatUser.sendMessage(String)) && this(c));\n\n" +
                        "\tprotected void notifyMediator(Colleague c, Mediator m) {\n" +
                        "\t\tSystem.out.println(((ChatUser) c).getName() + \" sends \" + ((ChatUser) c).getLastMessageSend());\n" +
                        "\t}\n\n" +
                        "}\n");
        FileData chatUser = createFileData(examplePath + "ChatUser.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public class ChatUser {\n\n" +
                        "\tprivate static int nextID = 1;" +
                        "\tprivate String name;\n\n" +
                        "\tprivate String lastMessageSend;\n\n" +
                        "\tpublic ChatUser(String name){\n\t\tthis.name = name;\n\t\tnextID++;\n\t}\n\n" +
                        "\tpublic ChatUser(){\n\t\tthis.name = \"anonymous\" + nextID;\n\t\tnextID++\n\t}\n\n" +
                        "\tpublic String getLastMessageSend() {\n\t\treturn lastMessageSend;\n\t}\n\n" +
                        "\tpublic String getName() {\n\t\treturn name;\n\t}\n\n" +
                        "\tpublic void setName(String newName) {\n\t\tname = newName;\n\t}\n\n" +
                        "\tpublic void sendMessage(String message) {\n\t\tlastMessageSend = message;\n\t}\n\n" +
                        "}\n");
        FileData chatMediator = createFileData(examplePath + "ChatMediator.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public class ChatMediator {\n\n}\n");
        FileData main = createFileData(examplePath + "Main.java",
                "package designpattern." + packageName + "; \n\n" +
                        "public class Main {\n\n" +
                        "\tpublic static void main(String[] args) {\n" +
                        "\t\tChatMediator med = new ChatMediator();\n" +
                        "\t\tChatUser john = new Client(\"John\");\n" +
                        "\t\tChatUser doe = new Client(\"Doe\");\n\n" +
                        "\t\tChatMediatorAspect.aspectOf().setMediator(john, med);\n" +
                        "\t\tChatMediatorAspect.aspectOf().setMediator(doe, med);\n" +
                        "\t\tjohn.sendMessage(\"Hello world !\");\n\n;" +
                        "}\n");
        this.fileDataTab[0] = mediatorAspect;
        this.fileDataTab[1] = chatMediatorAspect;
        this.fileDataTab[2] = chatUser;
        this.fileDataTab[3] = chatMediator;
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
