package PathFinding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.andengine.extension.tmx.TMXTile;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.algorithm.path.Path;

import android.util.Log;

import map.TilledMap;

public class AStar {
	
	private int DIRECT_COST_DIAG = 14;
	private int DIRECT_COST_LINE = 10;
	
	private List<Node> openList;
	private List<Node> closeList;
	private List<TMXTile> openTMXTile;
	private List<TMXTile> closeTMXTile;
	private List<TMXTile> tempList;
	
	private List<List<String>> blockProperty;
	
	private ManathanEurestic mHeurestic;
	
	private TilledMap theMap;
	private TMXTile begin, finish;
	
	private boolean endFound;
	
	
	public AStar(TilledMap map, List<List<String>> property)
	{
		Log.i("path", "LoadPathFinding...");
		theMap = map;
		blockProperty = property;
		
		openList = new ArrayList<Node>();
		closeList = new ArrayList<Node>();
		
		tempList = new ArrayList<TMXTile>();
		openTMXTile = new ArrayList<TMXTile>();
		closeTMXTile = new ArrayList<TMXTile>();
		
		mHeurestic = new ManathanEurestic(theMap);
		
	}
	
	public Path getPath(TMXTile start, TMXTile end, TouchEvent touch)
	{
		//Debut PathFinding
		Log.i("path", "Start PathFinding...");
		
		//Ajouter Tile de Debut, Tile de fin (la ou le Path va mener)
		begin = start;
		finish = end;
		
		//creer une liste temporaire: La liste des voisin directe
		List<Node> neighborhood;
		
		//Creer le Node courrant, il est positioner au Debut (Parent == Null)
		Node current = new Node(null, mHeurestic.count(begin, finish), 0, begin);
		
		//Ajoute le noeud courrant a la liste ouverte
		openList.add(current);
		
		//Ajoute tout les voisins a la liste ouverte
		openList.addAll(getNeighbor(current));
		Log.i("path", "List Opened " + (openList.size()));
		
		//Ajoute les voisin sous forme de Tiles
		openTMXTile.add(current.getTile());
		openTMXTile.addAll(tempList);
		
        Log.i("path", "MARACAS wqdw" + closeList.size() + " " + openList.size());
		
		//Ajoute les noeuds courants aux liste Fermer
		toCloseList(current);
		toCloseTMXList(current.getTile());
		
		Log.i("path", "MARACAS wqdw" + closeList.size() + " " + openList.size());
		
		//Tan que le liste Noeud final n'est pas trouver
		while(!closeTMXTile.contains(finish))
		{
			Log.i("path", "Start EPIC TOSTY");
			if(closeTMXTile.contains(finish))
			{
				Log.i("path", "FINI!");
			}
			
		   //Pour la tout les elements de la liste ouverte
		   for(int i=0; i<openList.size(); i++)
		   {
			  
			   Log.i("path", "MARACAS 4 THE " + openList.get(i).getCost() + " " + current.getCost() + " Cout H " + mHeurestic.count(begin, finish));
			  //si un des elements de cette liste a un Cout plus
			  //petit que le noeud Courrant, il devient le nouveau
			  //courant
			  if(openList.get(i).getCost() <= current.getCost())
			  {
				  Log.i("path", "A Trouver un Node Plus Petit!" + openList.get(i).getCost() + " " + current.getCost());
				  current = openList.get(i);
			  }
		   }
		   
		   Log.i("path", "List Opened InStart Loop" + (openList.size()));
		   
		   
		   //Ajoute le noeud courant a la liste des Noeuds Fermer
		   toCloseList(current);
		   toCloseTMXList(current.getTile());
		   
		   if(current.getTile().equals(finish))
			     break;
		   
		   Log.i("path", "MARACAS AWSDA " +  openList.size()+ " " + closeList.size() + " Tile: " + openTMXTile.size() + " " + closeTMXTile.size());
		   
		   //Obtien la liste des voisin du noeud courant
		   neighborhood = getNeighbor(current);
		   
		   //Pour tout les voisins
		   for(Iterator<Node> ls = neighborhood.iterator(); ls.hasNext(); ) {
			   Log.i("path", "MARACAS");
			  // Log.i("path", "List Opened in For Each" + (openList.size()) + " NeighBoor Size: " + (neighborhood.size()));
			   Node temp = ls.next();
			   
			   if(openTMXTile.contains(temp.getTile()))
				   Log.i("path", "MARACAS SAMBOUCCA");
			   
			   //Si ce voisin n'est pas dans la liste ouverte et n'est pas dans la liste fermer
			   if(!openTMXTile.contains(temp.getTile()) && !closeTMXTile.contains(temp.getTile()))
			   {
				 //il n'existe donc dans aucune liste et est ajouter a la liste ouverte
				// Log.i("path", "Existe Pas");
			     openList.add(temp);
			     openTMXTile.add(temp.getTile());
			     
			     //sinon, si le voisin est deja dans la liste ouverte mais n'est pas dans
			     //la liste fermer
			   }
			   else if(openTMXTile.contains(temp.getTile()))
			   {
				//   Log.i("path", "Existe Deja");
				   int indexOfTheNode = openTMXTile.indexOf(temp.getTile());
				   Node checkNode = openList.get(indexOfTheNode);
				   Log.i("path", "MARACAS " + indexOfTheNode);
				   
				   
				   Log.i("path", "MARACAS" + checkNode.getAssociateCost() + " " + (temp.getAssociateCost() + getCostTo(temp, checkNode)));
				   
				   //Si ce voisin a un cout plus petit que le cout total pour ce rendre
				   //a ce dernier en passant par le noeud courrant, cela veut dire que 
				   //passer directement par ce noeud est donc une meilleur option
				   if(checkNode.getAssociateCost() < (temp.getAssociateCost() + getCostTo(temp, checkNode)))
				   {
				//	  Log.i("path", "CheckNode : "+ checkNode.getAssociateCost() + "CostTo" + getCostTo(temp, checkNode));
					  
					  int indice = closeList.indexOf(checkNode);
					  Log.i("path", "MARACAS " + indice);
					   
					  //met le noeud courrant dans la liste ouverte
					  toOpenList(closeList.get(indice));
					  toOpenTMXList(closeTMXTile.get(indice));
					  //met le noeud le plus petit dans la liste fermer
					  toCloseList(checkNode);
					  //change le parent pour l'ancien parent de l'ancien noeud courrant (oui oui je sais...)
					  closeList.get(closeList.size()-1).setParent(temp.getParent());
					  toCloseTMXList(checkNode.getTile());
					  current = checkNode;
					  
				   }
			   }
			   
		   }
		   
		   
		}
		
		Log.i("path", "Sortie du Path");
		Log.i("path", "Path OpenList Taille " + openList.size());
		Log.i("path", "asdasdasd Path");
		if(openList.isEmpty())
		{
			Log.i("path", "Wut? Path Vide!");
			return null;
			
		}
		
		/* Calcule la longeur tu chemin pour initialiser le Path */
		int length = 1;
		
		Node tmp = closeList.get(closeList.size()-1);
		
		while(!tmp.equals(start)) {
			Log.i("path", "Longueur du Path " + length);
			tmp = tmp.getParent();
			length++;
		}

		/* Creer le Path en parcouran les noeuds parents */
		final Path path = new Path(length);
		int index = length - 1;
		tmp = closeList.get(closeList.size()-1);;
		while(!tmp.equals(start)) {
			path.set(index, ((tmp.getTile().getTileColumn())*30)-15, ((tmp.getTile().getTileHeight())*30)-15);
			tmp = tmp.getParent();
			index--;
		}
		path.set(0, (int)touch.getX(), (int)touch.getY());
		
		Log.i("path", "Path Trouver");
		
		return path;
	}
	
	private List<Node> getNeighbor(Node currentNode)
	{
		if(!tempList.isEmpty())
		{
		  tempList.clear();
		  Log.i("path", "Clear tempList");
		}
		
		Log.i("path", "Get Neighbor");
		
		TMXTile temp;
		List<Node> neighbor = new ArrayList<Node>();
		Node tmpNode;
		
		temp = theMap.getBaseLayer().getTMXTile(currentNode.getTile().getTileColumn() - 1, currentNode.getTile().getTileHeight());
		if(!isBlockable(temp))
		{ 
			tmpNode = new Node(currentNode, mHeurestic.count(temp, finish), currentNode.getAssociateCost() + DIRECT_COST_LINE, temp);
			tempList.add(tmpNode.getTile());
			neighbor.add(tmpNode);
		}
		
		temp = theMap.getBaseLayer().getTMXTile(currentNode.getTile().getTileColumn() - 1, currentNode.getTile().getTileHeight() + 1);
		if(!isBlockable(temp))
		{
			tmpNode = new Node(currentNode, mHeurestic.count(temp, finish), currentNode.getAssociateCost() + DIRECT_COST_DIAG, temp);
			tempList.add(tmpNode.getTile());
			neighbor.add(tmpNode);
		}
		
		temp = theMap.getBaseLayer().getTMXTile(currentNode.getTile().getTileColumn(), currentNode.getTile().getTileHeight() + 1);
		if(!isBlockable(temp))
		{
			tmpNode = new Node(currentNode, mHeurestic.count(temp, finish), currentNode.getAssociateCost() + DIRECT_COST_LINE, temp);
			tempList.add(tmpNode.getTile());
			neighbor.add(tmpNode);
		}
		
		temp = theMap.getBaseLayer().getTMXTile(currentNode.getTile().getTileColumn() + 1, currentNode.getTile().getTileHeight() + 1);
		if(!isBlockable(temp))
		{
			tmpNode = new Node(currentNode, mHeurestic.count(temp, finish), currentNode.getAssociateCost() + DIRECT_COST_DIAG, temp);
			tempList.add(tmpNode.getTile());
			neighbor.add(tmpNode);
		}
		
		temp = theMap.getBaseLayer().getTMXTile(currentNode.getTile().getTileColumn() + 1, currentNode.getTile().getTileHeight());
		if(!isBlockable(temp))
		{
			tmpNode = new Node(currentNode, mHeurestic.count(temp, finish), currentNode.getAssociateCost() + DIRECT_COST_LINE, temp);
			tempList.add(tmpNode.getTile());
			neighbor.add(tmpNode);
			
		}
		
		temp = theMap.getBaseLayer().getTMXTile(currentNode.getTile().getTileColumn() + 1, currentNode.getTile().getTileHeight() - 1);
		if(!isBlockable(temp))
		{
			tmpNode = new Node(currentNode, mHeurestic.count(temp, finish), currentNode.getAssociateCost() + DIRECT_COST_DIAG, temp);
			tempList.add(tmpNode.getTile());
			neighbor.add(tmpNode);
		}
		
		temp = theMap.getBaseLayer().getTMXTile(currentNode.getTile().getTileColumn(), currentNode.getTile().getTileHeight() - 1);
		if(!isBlockable(temp))
		{
			tmpNode = new Node(currentNode, mHeurestic.count(temp, finish), currentNode.getAssociateCost() + DIRECT_COST_LINE, temp);
			tempList.add(tmpNode.getTile());
			neighbor.add(tmpNode);
		}
		
		temp = theMap.getBaseLayer().getTMXTile(currentNode.getTile().getTileColumn() - 1, currentNode.getTile().getTileHeight() - 1);
		if(!isBlockable(temp))
		{
			tmpNode = new Node(currentNode, mHeurestic.count(temp, finish), currentNode.getAssociateCost() + DIRECT_COST_DIAG, temp);
			tempList.add(tmpNode.getTile());
			neighbor.add(tmpNode);
		}
		
		Log.i("path", "Neighbor count " + (neighbor.size()-1));
		
		return neighbor;
	}
	
	private void toCloseList(Node toClose)
	{
		closeList.add(toClose);
		openList.remove(openList.indexOf(toClose));
	}
	
	private void toCloseTMXList(TMXTile toClose)
	{
		closeTMXTile.add(toClose);
		openTMXTile.remove(openTMXTile.indexOf(toClose));
	}
	
	private void toOpenList(Node toOpen)
	{
		openList.add(toOpen);
		closeList.remove(closeList.indexOf(toOpen));
	}
	
	private void toOpenTMXList(TMXTile toOpen)
	{
		openTMXTile.add(toOpen);
		closeTMXTile.remove(closeTMXTile.indexOf(toOpen));
	}
	
	private boolean isBlockable(Node currentNode)
	{
		for(int i=0; i<blockProperty.size();i++)
		  if(currentNode.getTile().getTMXTileProperties(theMap.getMap()).containsTMXProperty(blockProperty.get(i).get(0), blockProperty.get(i).get(1)))
		  {
			 
	         return true;
		  }
		Log.i("path", "PAS BLOCKER!!!!");
		return false;
	}
	
	private boolean isBlockable(TMXTile til)
	{
		for(int i=0; i<blockProperty.size();i++)
		{
		  Log.i("path", "Property : " + blockProperty.get(i).get(0) + " Value : " +blockProperty.get(i).get(1));
		  if(til.getTMXTileProperties(theMap.getMap()).containsTMXProperty(blockProperty.get(i).get(0), blockProperty.get(i).get(1)))
		  {
			  Log.i("path", "BLOCKER!!!!");
			  return true;
		  }
		}
		Log.i("path", "PAS BLOCKER!!!!");
		return false;
	}
	
	private int getCostTo(Node a, Node b)
	{
		if((a.getTile().getTileHeight() - 1 == b.getTile().getTileHeight() && a.getTile().getTileColumn() - 1 == b.getTile().getTileColumn())||
		   (a.getTile().getTileHeight() + 1 == b.getTile().getTileHeight() && a.getTile().getTileColumn() - 1 == b.getTile().getTileColumn())||
		   (a.getTile().getTileHeight() + 1 == b.getTile().getTileHeight() && a.getTile().getTileColumn() + 1 == b.getTile().getTileColumn())||
		   (a.getTile().getTileHeight() - 1 == b.getTile().getTileHeight() && a.getTile().getTileColumn() + 1 == b.getTile().getTileColumn()))
			return DIRECT_COST_DIAG;
		return DIRECT_COST_LINE;
	}

}
