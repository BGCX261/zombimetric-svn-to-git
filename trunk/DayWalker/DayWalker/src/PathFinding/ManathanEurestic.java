package PathFinding;

import map.TilledMap;

import org.andengine.extension.tmx.TMXTile;
import org.andengine.extension.tmx.TMXTiledMap;

public class ManathanEurestic {
	
	private TMXTile begin, finish;
	private TilledMap theMap;
	
	private int price;
	
	public ManathanEurestic(TilledMap map)
	{
		theMap = map;
	}
	
	public int count(TMXTile start, TMXTile end)
	{
		TMXTile current = start;
		begin = start;
		finish = end;
		
		price = 0;
		
		if(current.getTileHeight() > finish.getTileHeight())
		{
			//searchDown
			while(current.getTileHeight() != finish.getTileHeight())
			{
				current = theMap.getBaseLayer().getTMXTile(current.getTileColumn(), current.getTileHeight() - 1);
				price += 10;
			}
			searchInRow(current);
			return price;
		}
		else if(current.getTileHeight() < finish.getTileHeight())
		{
			//search up
			while(current.getTileHeight() != finish.getTileHeight())
			{
				current = theMap.getBaseLayer().getTMXTile(current.getTileColumn(), current.getTileHeight() + 1);
				price += 10;
			}
			searchInRow(current);
			return price;
		}
		else if(current.getTileHeight() == finish.getTileHeight())
		{
			//its the same row
			searchInRow(current);
			return price;
		}
		//its already this...
		return 0;
	}
	
	//Going to search in a row
	private void searchInRow(TMXTile now)
	{
	    if(now.getTileColumn() < finish.getTileColumn())
	    {
	    	while(now.getTileColumn() != finish.getTileColumn())
	    	{
	    		now = theMap.getBaseLayer().getTMXTile(now.getTileColumn() + 1, now.getTileHeight());
	    		price += 10;
	    	}
	    }
	    else
	    {
	    	while(now.getTileColumn() != finish.getTileColumn())
	    	{
	    		now = theMap.getBaseLayer().getTMXTile(now.getTileColumn() - 1, now.getTileHeight());
	    		price += 10;
	    	}
	    }
	}
}
