class FancyVisitor implements Visitor{
	FancyVisitor(){}

	public void visit(Node n)
	{
		//n.setString(n.getString() + "\n");
	}
	public void visit(Header n)
	{
		n.setString("<font color=\"red\">" + n.getString() + "</font>\n");
	}
	public void visit(CodeBlock n)
	{
		n.setString("<font color=\"blue\">" + n.getString() + "</font>\n");
	}
	public void visit(List n)
	{
		n.setString("<font color=\"green\">" + n.getString() + "</font>\n");
	}
	public void visit(OList n)
	{
		n.setString("<font color=\"gray\">" + n.getString() + "</font>\n");
	}
	public void visit(BlockQuote n)
	{
		n.setString("<font color=\"yellow\">" + n.getString() + "</font>\n");
	}

}