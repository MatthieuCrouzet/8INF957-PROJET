package utils;

/**
 * Created by Baptiste BURON on 16/03/2017.
 */
public final class Separator {

	public static String SEPARATOR;
	
	public Separator(){
		String OS = System.getProperty("os.name").toLowerCase();
		
		//Define the separator to use to create files at the right place depending on current OS.
		if(OS.indexOf("win")>0){
			SEPARATOR = "\\";
		}
		else
		{
			SEPARATOR = "//";
		}
	}
}
