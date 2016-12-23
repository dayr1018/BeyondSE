public interface Visitor{
	public void visit(Node n);
	public void visit(Header n);
	public void visit(CodeBlock n);
	public void visit(List n);
	public void visit(OList n);
	public void visit(BlockQuote n);
}