class PlainVisitor implements Visitor{

	PlainVisitor(){}

	public void visit(Node n)
	{
		//n.setString(n.getString() + "\n");
	}
	public void visit(Header n)
	{
		n.setString(n.getString() + "\n");
	}
	public void visit(CodeBlock n)
	{
		n.setString(n.getString() + "\n");
	}
	public void visit(List n)
	{
		n.setString(n.getString() + "\n");
	}
	public void visit(OList n)
	{
		n.setString(n.getString() + "\n");
	}
	public void visit(BlockQuote n)
	{
		n.setString(n.getString() + "\n");
	}
}



