***How to use this converter program.***

There are 4 command lines, which are execute, ls, --help, and exit.

1. You can run program using 'execute' command. The basic format is, 

	 execute *.md -type *.html

'*.md' and '*.html' mean markdown and html file names. A word 'type' can be changed p, f, s, which mean plain, fancy and slide.
Default value of type is plain. So, if you don't describe what type is, program converts md file to html file automatically in a plain format.

On other hands, there are some usages that you can use 'execute' command more powerful. The things are following statements.

	execute test.md -p test.html & test1.md -s test2.html
	execute test.md test1.md test2.md -p

In order to convert some files simultaneously, you can use these commands. The only things that you watch out are to keep the 2 rules.
The first fule is you must specify 3 arguments(md file, type, html file) when you write the first command line. The second rule is
you can't specify html file name when you use the second command line. It is because converter program automatically determines html
file names using those of md file. In this case, it is okay skipping type. 

Example)
execute test.md -f test.html & test1.md test2.html    :  x   ( Type is needed)
execute test.md test1.md test2.md -f test.html        :  x   ( Html file must be deleted)
execute test.md test1.md test2.md                     :  o   

2. You may need 'ls' command which helps you to check what fils are in current dirtory. This is the exactly same with 'ls' in linux.

3. You can use '--help' command when you want to know how to use the program. It shows command lists and explanation. 

4. When you quit the program, use 'exit'.


**Comment
When it comes to using 'execute' commands, we have considered several situations. First, when md files that user inputs doesn't exist,
program doesn't accecpt it. It shows error message. We have implemented this part. Second, when users take mistakes such as '.htmls' or
'executed', program also doesn't accept it. We have strickly checked it out. It won't work in those case. Lastly, when user inputs several 
md files and specifies html file names, we have thought that html file names can conflict with each other. And we decided that we don't 
accept that situation too. This part have not been developed yet, but we will complete it in next version. 