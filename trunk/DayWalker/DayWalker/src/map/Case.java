package map;

//N'EST PAS BON, CLASS DEBUG A NE PAS CORRIGÉ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class Case {
	final int xSize = 10;
	final int ySize = 10;
	String id;
	boolean usable;
	
	public Case(String _id)
	{
		id = _id;
	}
	
	public String getId()
	{
		return id;
	}
	
	
	
	public boolean isSameAs(Case leCase)
	{
		return this.id==leCase.getId();
	}
	
	public void isActive(boolean answer)
	{
		this.usable = answer;
	}

}
