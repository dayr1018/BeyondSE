import java.io.*;
import java.util.*;

class CommandReader {

	String command="";
	StringTokenizer tokens;
	String[] commandType={""};
	String[] commandArr={""};
	String[] sCommandArr={""};
	String mdFileName="";
	String typeName="plain";
	String htmlFileName="";

	Scanner scanner = new Scanner(System.in);

	final String[] CMD_Explanation ={   "Execute converting :    execute *.md -type *.html\n",
										"-type:p,f,s           || execute *.md -type *.html & *.md -type *.html ...\n",
										"(default type:p)      || execute *.md *.md ... *.md -type\n",
										"MD files in directory:  ls\n",
										"Help:		       --help\n",
										"Terminate Program:      exit\n"};

	CommandReader(){};

	void run()
	{
		while(!commandType[0].equals("exit"))
		{
			System.out.print("$");

			command = scanner.nextLine();
			tokens = new StringTokenizer(command);

			commandType = command.split(" ");

			switch(commandType[0])
			{
				case "execute":
					command = command.substring(8, command.length());
					FuncExecute();
					break;
				case "ls":
					if(commandType.length > 1)
						System.out.println("Invalid Command line. Command line is too long.");
					else
						FuncLs();
					break;
				case "--help":
					if(commandType.length > 1)
						System.out.println("Invalid Command line. Command line is too long.");
					else
						FuncHelp();
					break;
				case "exit":
					if(commandType.length > 1)
						System.out.println("Invalid Command line. Command line is too long.");
					else
						FuncExit();
					break;
				default:
					System.out.println("Invalid Command line. Try again!");
			}

		}
	}

	boolean canExecute()
	{
		int numOfMd = 0;
		if(command.split("&").length==1)  // & 없는 경우이므로 execute a.md b.md c.md -f 에 해당하는 경우 체크
		{
			sCommandArr = command.split(" ");

			for(int i = 0; i < sCommandArr.length; i++)
			{
				if(sCommandArr[i].endsWith(".md"))
					numOfMd++;
			}

			if(numOfMd ==1) // md 파일 1개일 때
			{
				for(int i = 0; i < sCommandArr.length; i++)
				{
					if(sCommandArr[i].endsWith(".md"))
					{
						if(!isThereFile(sCommandArr[i]))
							return false;
					}
					else if(sCommandArr[i].startsWith("-"))
					{
						// type 지정
					}
					else if(sCommandArr[i].endsWith(".html"))
					{
					}
					else
					{
						System.out.println("'Execute' failed. Check Command line.");
						return false;
					}
				}
			}
			else //md file이 여러개인지
			{
				for(int i = 0; i < sCommandArr.length; i++)
				{
					if(sCommandArr[i].endsWith(".md"))
					{
						if(!isThereFile(sCommandArr[i]))
							return false;
					}
					else if(sCommandArr[i].startsWith("-"))
					{
						// type 지정
					}
					else
					{
						System.out.println("'Execute' failed. Check Command line.");
						return false;
					}
				}
			}

		}
		else    // & 있는 경우이므로 execute a.md -f a.html & b.md -p b.html 에 해당하는 경우 체크
		{
			for(int i=0; i < commandArr.length; i++)
			{
				if(commandArr[i].startsWith(" "))
					commandArr[i] = commandArr[i].substring(1, commandArr[i].length());

				sCommandArr = commandArr[i].split(" ");
				for(int j = 0; j < sCommandArr.length; j++)
				{
					if(sCommandArr[j].endsWith(".md"))
					{
						if(!isThereFile(sCommandArr[j]))
							return false;
					}

					if(sCommandArr[j].endsWith(".html"))
					{
						String str = sCommandArr[j].substring(0, sCommandArr[j].length()-5);

						if(htmlFileName.indexOf(str) > 0)
						{
							System.out.println("HTML file names conflict.");
							return false;
						}
						htmlFileName.concat(str);
						htmlFileName.concat(" ");
					}
				}
			}
		}

		return true;
	}

	void FuncExecute()
	{
		commandArr = command.split("&");

		if(!canExecute())
			return;

		if(commandArr.length==1) // & 없는 경우이므로 execute a.md b.md c.md -f 혹은 execute a.md -f a.html 실행
		{
			sCommandArr = commandArr[0].split(" ");
			for(int i = 0; i < sCommandArr.length; i++)
			{
				if(sCommandArr[i].endsWith(".md"))
					System.out.println("Success");
			}
		}
		else // & 있는 경우이므로, execute a.md -f a.html & b.md -p b.html 실행
		{
			for(int i=0; i < commandArr.length; i++)
			{
				if(commandArr[i].startsWith(" "))
					commandArr[i] = commandArr[i].substring(1, commandArr[i].length());

				sCommandArr = commandArr[i].split(" ");

				for(int j = 0; j < sCommandArr.length; j++)
				{
					if(sCommandArr[j].startsWith("-"))
					{
						typeName = sCommandArr[j].substring(1, sCommandArr[j].length()); //type 지정
					}
					else if(sCommandArr[j].endsWith(".md"))
					{
						mdFileName = sCommandArr[j].substring(0, sCommandArr[j].length()-3);
					}
					else if(sCommandArr[j].endsWith(".html"))
					{
						htmlFileName = sCommandArr[j].substring(0, sCommandArr[j].length()-5);
					}
					else
					{
						System.out.println("Invalid Command line. Try again!");
						return;
					}

				}

				System.out.println("Success");
				//System.out.println("Input File: " + mdFileName + ".md");
				//System.out.println("Type: " + typeName);
				//System.out.println("Output File: " + htmlFileName + ".html");
			}
		}
	}

	boolean isThereFile(String fileName)
	{

		String s = System.getProperty("user.dir");
		File f= new File(s);
		if(!f.exists())
		{
		   System.out.println("Directory doesn't exist.");
		   return false;
		}

		File[] allFiles= f.listFiles();
		for(File file : allFiles)
		{
		   if(file.getName().equals(fileName))
		   		return true;
		}

		System.out.println("'" +fileName + "'" + " file doesn't exist.");
		return false;
	}

	void FuncLs()
	{

		ArrayList<File> mdFiles= new ArrayList<File>();
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
		   if(file.getName().endsWith(".md"))
		   {
			   mdFiles.add(file);
		   }
	    }

		System.out.println(s);
	    System.out.println("-------Markdown Files in the directory-------");


		System.out.print("");
	    for(File file : mdFiles)
	    {
		    System.out.print("\t" + file.getName());
	    }
	    System.out.print("\n");
	}

	void FuncHelp()
	{
		System.out.println("-----------Command Lists-----------");

  	 	for(int i = 0; i < CMD_Explanation.length; i++)
			 System.out.print(CMD_Explanation[i]);
	}

	void FuncExit()
	{
		//System.out.println("exit");
	}
}



public class ExecuteCommandReader{
	public static void main(String args[]){
		CommandReader reader = new CommandReader();

		reader.run();
	}
}