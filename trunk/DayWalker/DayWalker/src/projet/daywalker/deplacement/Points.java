package projet.daywalker.deplacement;

//N'EST PAS UTILISÉ !!! PEUT ETRE UTILE PLUS TARD !!!!!!!!!!!!!!!!!!!!!

public class Points {
	int x,y;
	
	public Points(int X, int Y)
	{
		x = X;
		y = Y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public Points setX(int leX)
	{
		return new Points(leX, this.getY());
	}
	
	public Points setY(int leY)
	{
		return new Points(this.getX(), leY);
	}
	
	public Points setPoints(int x, int y)
	{
		return new Points(x,y);
	}
	
	public boolean isEqualTo(Points lePoint)
	{
		return lePoint.getX()==this.getX()&&lePoint.getY()==this.getY();
	}
	
	public String toString()
	{
		return this.getX()+","+getY();
	}

}
