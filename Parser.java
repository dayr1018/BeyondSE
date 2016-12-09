import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MDParser{

	ArrayList<String> buf = new ArrayList<String>();
	Pattern pHeaderA = Pattern.compile("^[#]+[ A-Za-z#-]*");
	Pattern pHeaderB = Pattern.compile("[=]+[=]+[=]+|[-]+[-]+[-]+");
	Pattern pDoubleSpace = Pattern.compile("^[ A-Za-z.*]*[ ][ ]$");
	Pattern pCodeBlock = Pattern.compile("^[~]+[~]+[~]+$");
	Pattern pBlockQuote = Pattern.compile("^[>][A-Za-z0-9 .*]*");
	Pattern pList = Pattern.compile("^[*][A-Za-z0-9 .*]*|^[+][A-Za-z0-9 .*]*|^[-][A-Za-z0-9 .*]*");
	Pattern pImage = Pattern.compile("!\\[[A-Za-z0-9.*]*\\]\\([A-Za-z0-9/:.]*\\)");

	Matcher mHeaderA, mHeaderB, mDoubleSpace, mCodeBlock, mBlockQuote, mList, mImage;

	int mode;

	private final static int BASIC = 0;
	private final static int C_BLOCK = 1;
	private final static int BLOCK_Q = 2;
	private final static int LIST = 3;

	Document doc;

	MDParser(){mode = BASIC;}

	Document run(String fileName)
	{
		try
		{
			doc = new Document(fileName);

			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);

			String line = "";
			for(int i = 0; (line = br.readLine())!=null; i++)
			{
				mHeaderA = pHeaderA.matcher(line);
				mHeaderB = pHeaderB.matcher(line);
				mDoubleSpace = pDoubleSpace.matcher(line);
				mCodeBlock = pCodeBlock.matcher(line);
				mBlockQuote = pBlockQuote.matcher(line);
				mList = pList.matcher(line);
				mImage = pImage.matcher(line);

				switch(mode)
				{
					case BASIC:
						if(mHeaderA.find())
						{
							/*
							for(int j = 0; j < buf.size(); j++)                    // 일반 문자열 노드 생성되는 곳
								System.out.println(buf.get(j));
							*/

							String str="";
							for(int j = 0; j < buf.size(); j++)
								str = str.concat(buf.get(j));
							System.out.println(str);
							Node node = new Node(str);
							doc.addNode(node);

							System.out.println(mHeaderA.group() + "\t\tmHeaderA");          // 헤더 노드 생성되는 곳
							Header header = new Header(mHeaderA.group(), 2);
							doc.addNode(header);

							buf.clear();
						}
						else if(mHeaderB.find())
						{
							/*
							for(int j = 0; j < buf.size()-1; j++)                          // 일반 문자열 노드 생성되는 곳
								System.out.println(buf.get(j));
							*/

							String str="";
							for(int j = 0; j < buf.size()-1; j++)
								str = str.concat(buf.get(j));
							System.out.println(str);
							Node node = new Node(str);
							doc.addNode(node);

							System.out.println(buf.get(buf.size()-1) + "\t\tmHeaderB");      // 헤더 노드 생성되는 곳
							Header header = new Header(buf.get(buf.size()-1), 2);
							doc.addNode(header);

							buf.clear();
						}
						else if(mImage.find())
						{
							String str="";                                           // 일반 문자열 생성
							for(int j = 0; j < buf.size()-1; j++)
								str = str.concat(buf.get(j));
							System.out.println(str);
							Node node = new Node(str);
							doc.addNode(node);

							System.out.println(line + "\t\tmImage");
							Image image = new Image(line);
							doc.addNode(image);

							buf.clear();
						}
						else if(mDoubleSpace.find())
						{
							buf.add(line + "\n");
						}
						else if(line.length() == 0)
						{
							buf.add(line + "\n\n");
						}
						else if(mCodeBlock.find())
						{
							/*
							for(int j = 0; j < buf.size(); j++)                    // 일반 문자열 노드 생성되는 곳
								System.out.println(buf.get(j));
							*/

							String str="";
							for(int j = 0; j < buf.size(); j++)
								str = str.concat(buf.get(j));
							System.out.println(str);
							Node node = new Node(str);
							doc.addNode(node);

							buf.clear();
							mode = C_BLOCK;
							//System.out.println(mCodeBlock.group() + "\t\tmCodeBlock");
						}
						else if(mBlockQuote.find())
						{
							/*
							for(int j = 0; j < buf.size(); j++)                 //  일반 문자열 노드 생성 되는 곳
								System.out.println(buf.get(j));
							*/

							String str="";
							for(int j = 0; j < buf.size(); j++)
								str = str.concat(buf.get(j));
							System.out.println(str);
							Node node = new Node(str);
							doc.addNode(node);

							buf.clear();
							mode = BLOCK_Q;
							//System.out.println(mBlockQuote.group() + "\t\tmBlockQuote");
						}
						else if(mList.find())
						{

							/*
							for(int j = 0; j < buf.size(); j++)         // 일반 문자열 노드 생성 되는 곳
								System.out.println(buf.get(j));
							*/

							String str="";
							for(int j = 0; j < buf.size(); j++)
								str = str.concat(buf.get(j));
							System.out.println(str);
							Node node = new Node(str);
							doc.addNode(node);

							buf.clear();

							buf.add(line);
							mode = LIST;
						}
						else
						{
							buf.add(line);
						}

						break;
					case C_BLOCK:
						if(mCodeBlock.find())
						{
							String str="";
							/*
							for(int j = 0; j < buf.size(); j++)    // Code Block 노드 생성 되는 곳
								System.out.println(buf.get(j));
							*/
							for(int j = 0; j < buf.size(); j++)    // Code Block 노드 생성 되는 곳
								str = str.concat("\n" +buf.get(j));  //
							System.out.println(str);
							CodeBlock codeBlock = new CodeBlock(str);
							doc.addNode(codeBlock);


							buf.clear();
							mode = BASIC;
							break;
						}

						buf.add(line);
						break;
					case BLOCK_Q:
						break;
					case LIST:
					  if(!mList.find() || (line.length() == 0) )
					  {
							 String str="";
							 String tmpstr = "";

							 for(int j = 0; j < buf.size(); j++) {   // list 노드 생성 되는 곳
								tmpstr = buf.get(j);
								tmpstr = tmpstr.substring(1);
								str = str.concat("\n" + "<li>" + tmpstr + "</li>");
							 }

							 System.out.println(str);
							 List list = new List(str);
							 doc.addNode(list);

							 buf.clear();

							 buf.add("\n\t"+line);
							 mode = BASIC;
							 break;
				  		}

						buf.add(line);
						break;

					///////////
					/*
						if(!mList.find() || (line.length() == 0) )
						{
							String str="";

							for(int j = 0; j < buf.size(); j++)    // list 노드 생성 되는 곳						{
								str = str.concat("\n" +buf.get(j)); //
							System.out.println(str);
							List list = new List(str);
							doc.addNode(list);

							buf.clear();

							buf.add(line);
							mode = BASIC;
							break;
						}

						buf.add(line);
						break;
					*/

				}

			}

			String str="";
			for(int j = 0; j < buf.size(); j++)
				str = str.concat("\n"+ buf.get(j));  //
			System.out.println(str);
			Node node = new Node(str);
			doc.addNode(node);

			br.close();
		}catch(IOException e){}

		return doc;
	}

}
