import java.io.*;
import java.util.*;

class HTMLGenerator{
	ArrayList<Node> nodes = new ArrayList<Node>();
	Singleton s = Singleton.getInstance();

	HTMLGenerator(){};

	void run(Document doc)
	{
		try{
			String htmlFile = doc.getHtmlFileName();
			String path = System.getProperty("user.dif");

			checkFileDup(doc.getHtmlFileName());

			File file = new File(doc.getHtmlFileName());
			FileWriter fw = new FileWriter(file, true);

			if(s.getType().equals("p"))
				doc.accept(new PlainVisitor());
			else if(s.getType().equals("f"))
				doc.accept(new FancyVisitor());
			else // slide
				doc.accept(new SlideVisitor());

			for(int i = 0; i < doc.numOfNodes(); i++)
			{
				fw.write(doc.getNode(i).getString());
				fw.flush();
			}

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
