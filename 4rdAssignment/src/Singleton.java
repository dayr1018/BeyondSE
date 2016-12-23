public class Singleton{
	private static Singleton singleton = new Singleton();
	private String type="";

	private Singleton(){}

	public static Singleton getInstance()
	{
		return singleton;
	}
	public String getType()
	{
		return type;
	}

	public void setType(String str)
	{
		type = str;
	}
}