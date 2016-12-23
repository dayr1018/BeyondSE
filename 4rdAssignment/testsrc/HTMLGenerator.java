import java.io.*;
import java.util.*;

class HTMLGenerator{
	ArrayList<Node> nodes = new ArrayList<Node>();

	HTMLGenerator(){};

	void run(Document doc)
	{
		try{
			String htmlFile = doc.getHtmlFileName();
			String path = System.getProperty("user.dif");

			checkFileDup(doc.getHtmlFileName() + ".html"); //checkFileDup("test.html");

			File file = new File(doc.getHtmlFileName() + ".html"); //File file = new File("test.html"); // htmlFile
			FileWriter fw = new FileWriter(file, true);

			fw.write("<!DOCTYPE html>\n");
			fw.write("<html>\n");
			fw.write("<body>\n\n");
			for(int i = 0; i < doc.numOfNodes(); i++)
			{
				fw.write(doc.getNode(i).getString());
				fw.flush();
			}
			fw.write("\n\n</body>");
			fw.write("\n</html>");

			fw.close();

		}catch(Exception e){}

	}


	void checkFileDup(String fileName)
	{

		String s = System.getProperty("user.dir");
		File f= new File(s);
		if(!f.exists())
		{
		   System.out.println("Directory doesn't exist.");
		   return;
		}

		File[] allFiles= f.listFiles();
		for(File file : allFiles)
		{
		   if(file.getName().equals(fileName))
		   {
				file.delete();
			}
		}
	}


}
