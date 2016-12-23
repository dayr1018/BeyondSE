import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MDParser{

	ArrayList<String> buf = new ArrayList<String>();
	Pattern pHeaderA = Pattern.compile("^[#]+[ A-Za-z#-*]*");
	Pattern pHeaderB = Pattern.compile("[=]+[=]+[=]+|[-]+[-]+[-]+");
	Pattern pDoubleSpace = Pattern.compile("^[ A-Za-z.*]*[ ][ ]$");
	Pattern pCodeBlock = Pattern.compile("^[~]+[~]+[~]+$");
	Pattern pBlockQuote = Pattern.compile("^[>][A-Za-z0-9 .*]*");
	Pattern pList = Pattern.compile("^[*][A-Za-z0-9 .]*|^[+][A-Za-z0-9 .]*|^[-][A-Za-z0-9 .]*");
	Pattern pImage = Pattern.compile("!\\[[A-Za-z0-9.*]*\\]\\([A-Za-z0-9/:.*]*\\)");
	Pattern pOList = Pattern.compile("^[0-9][.][ ][A-Za-z0-9 ]*");

	Matcher mHeaderA, mHeaderB, mDoubleSpace, mCodeBlock, mBlockQuote, mList, mImage, mOList;

	int mode;

	private final static int BASIC = 0;
	private final static int C_BLOCK = 1;
	private final static int BLOCK_Q = 2;
	private final static int LIST = 3;
	private final static int OLIST = 4;

	Document doc;
	Singleton s = Singleton.getInstance();

	MDParser(){mode = BASIC;}

	Document run(String fileName)
	{
		try
		{
			doc = new Document(fileName);

			/*file start*/
			if(s.getType().equals("p"))
			{
				Node node = new Node("<!DOCTYPE html>\n<html>\n<body>\n\n");
				doc.addNode(node);
			}
			if(s.getType().equals("f"))
			{
				Node node = new Node("<!DOCTYPE html>\n<html>\n<body bgcolor=\"black\">\n<font color=\"white\">\n\n");
				doc.addNode(node);
			}
			if(s.getType().equals("s"))
			{
				Node node = new Node("<!DOCTYPE html>\n<html>\n<body bgcolor=\"skyblue\">\n\n");
				doc.addNode(node);
			}


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
				//mOList = pOList.matcher(line);

				switch(mode)
				{
					case BASIC:
						if(mHeaderA.find())
						{
							String str="";                   					// 일반 문자열 노드 생성되는 곳
							for(int j = 0; j < buf.size(); j++)
								str = str.concat(buf.get(j));
							//System.out.println(str);

							Node node = new Node(str);
							doc.addNode(node);


							//System.out.println(mHeaderA.group() + "\t\tmHeaderA");


							Header header = new Header(mHeaderA.group());          // 헤더 노드 생성되는 곳
							doc.addNode(header);

							buf.clear();
						}
						else if(mHeaderB.find())
						{

							String str="";                       				   // 일반 문자열 노드 생성되는 곳
							for(int j = 0; j < buf.size()-1; j++)
								str = str.concat(buf.get(j));
							//System.out.println(str);

							Node node = new Node(str);
							doc.addNode(node);


							//System.out.println(buf.get(buf.size()-1) + "\t\tmHeaderB");
							Header header = new Header(buf.get(buf.size()-1));        // 헤더 노드 생성되는 곳
							doc.addNode(header);

							buf.clear();
						}
						else if(mImage.find())
						{
							String str="";                                           // 일반 문자열 생성
							for(int j = 0; j < buf.size()-1; j++)
								str = str.concat(buf.get(j));
							//System.out.println(str);

							Node node = new Node(str);
							doc.addNode(node);


							//System.out.println(line + "\t\tmImage");
							Image image = new Image(line);                     // 이미지 노드 생성
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
							String str="";             				       // 일반 문자열 노드 생성되는 곳
							for(int j = 0; j < buf.size(); j++)
								str = str.concat(buf.get(j));
							//System.out.println(str);

							Node node = new Node(str);
							doc.addNode(node);


							buf.clear();
							mode = C_BLOCK;
							//System.out.println(mCodeBlock.group() + "\t\tmCodeBlock");
						}
						else if(mBlockQuote.find())
						{
							String str="";                					 //  일반 문자열 노드 생성 되는 곳
							for(int j = 0; j < buf.size(); j++)
								str = str.concat(buf.get(j));
							//System.out.println(str);

							Node node = new Node(str);
							doc.addNode(node);

							buf.clear();
							mode = BLOCK_Q;
							//System.out.println(mBlockQuote.group() + "\t\tmBlockQuote");
						}
						else if(mList.find())
						{
							String str="";       							  // 일반 문자열 노드 생성 되는 곳
							for(int j = 0; j < buf.size(); j++)
								str = str.concat(buf.get(j));
							//System.out.println(str);

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

							for(int j = 0; j < buf.size(); j++)  				  // Code Block 노드 생성 되는 곳
								str = str.concat("\n" + buf.get(j));
							//System.out.println(str);
							CodeBlock codeBlock = new CodeBlock(str);
							doc.addNode(codeBlock);


							buf.clear();
							mode = BASIC;
							break;
						}

						buf.add(line);
						break;
					case BLOCK_Q:
					  if(!mBlockQuote.find() || (line.length() == 0) )
					  {
							 String str="";
							 String tmpstr = "";

							 for(int j = 0; j < buf.size(); j++) {  					 // block quote 노드 생성 되는 곳
								tmpstr = buf.get(j);
								tmpstr = tmpstr.substring(1);
								str = str.concat("<blockquote>" + tmpstr + "</blockquote>\n");
							 }

							 //System.out.println(str);
							 BlockQuote blockquote = new BlockQuote(str);
							 doc.addNode(blockquote);

							 buf.clear();

							 buf.add("\n\t"+line);
							 mode = BASIC;
							 break;
				  		}

						buf.add(line);
						break;
					case LIST:
					  if(!mList.find() || (line.length() == 0) )
					  {
							 String str="";
							 String tmpstr = "";

							 for(int j = 0; j < buf.size(); j++) {  					 // list 노드 생성 되는 곳
								tmpstr = buf.get(j);
								tmpstr = tmpstr.substring(1);
								str = str.concat("\n" + "<li>" + tmpstr + "</li>");
							 }

							 //System.out.println(str);
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

					case OLIST:
						break;

				}
			}

			String str="";
			for(int j = 0; j < buf.size(); j++)
				str = str.concat("\n"+ buf.get(j));
			//System.out.println(str);

			Node node = new Node(str);
			doc.addNode(node);

			buf.clear();

			br.close();


			/*file end*/
			if(s.getType().equals("p"))
			{
				node = new Node("\n\n</body>\n</html>");
				doc.addNode(node);
			}
			if(s.getType().equals("f"))
			{
				node = new Node("\n\n</font>\n</body>\n</html>");
				doc.addNode(node);
			}
			if(s.getType().equals("s"))
			{
				node = new Node("\n\n</body>\n</html>");
				doc.addNode(node);
			}

		}catch(IOException e){}

		return doc;
	}

}
