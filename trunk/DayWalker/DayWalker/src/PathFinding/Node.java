package PathFinding;

import org.andengine.extension.tmx.TMXTile;

public class Node {
	
	private Node parent;
	private int id;
	private int heurestic;
	private int associateCost;
	private int cost;
	private TMXTile tile;

    
	public Node(Node Parent, int heurestcCost, int directCost, TMXTile associedTile)
	{
		parent = Parent;
		heurestic = heurestcCost;
		associateCost = directCost;
		tile = associedTile;
		cost = heurestic + associateCost;
	}
	
	public int getCost()
	{
		return cost;
	}
	
	public Node getParent()
	{
		return parent;
	}
	
	public int getAssociateCost()
	{
		return associateCost;
	}
	
	public int recalculateAssociateCost()
	{
		int temp = 0;
		if(associateCost != 0)
			temp = parent.recalculateAssociateCost();
		
		return temp;
	}
	
	public void recalculate(int heurestcCost, int directCost)
	{
		heurestic = heurestcCost;
		associateCost = directCost;
		cost = heurestic + associateCost;
	}
	
	public TMXTile getTile()
	{
		return tile;
	}
	
	public void setParent(Node parents)
	{
		parent = parents;
	}
}
