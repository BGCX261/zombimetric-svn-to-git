package map;

import java.io.InputStream;

import android.content.res.AssetManager;

//N'EST PAS BON, CLASS DEBUG A NE PAS CORRIGÉ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

/*
 * Cette classe contien pour l'instant une liste de Case, la map sera en faite une simple grille.
 * Cela sert a la premiere implementation du Pathfinding A*, C'est aussi ici que possiblement sera
 * implementer un QuadTree.
 */

public class Map {
	String background;
	int gridX, gridY;
	Case[][] listeCase;
	
	
	public Map()
	{
		background = "";
		gridX = 0;
		gridY = 0;
		listeCase = setTab(gridX, gridY);
	}
	
	/*
	 * Ce constructeur tiendra surement a changer, ou bien je vais
	 * en rajouter un nouveau qui prendra juste le chemin de la map.
	 * Elle sera surement un XML, je sais pas trop encore.
	 */
	public Map(String _background, int _gridX, int _gridY)
	{
		background = _background;
		gridX = _gridX;
		gridY = _gridY;
		listeCase = setTab(gridX, gridY);
	}
	
	public String getBackGroundPath()
	{
		return background;
	}
	
	
	/*
	 *Methode qui creer un tableau et set les id dans chaques cases
	 */
	public Case[][] setTab(int x, int y)
	{
		String id;
		Case generateTab[][] = new Case[x][y];
		for(int i=0; i<x; i++)
		{
			id = i+"_";
			for(int j=0; j<y; j++)
			{
				id += j;
				generateTab[i][j] = new Case(id);
			}
		}
		return generateTab;
	}
	
	public Case getCase(int x, int y)
	{
		return listeCase[x][y];
	}
	
	public void readInFile(String file)
	{
		
	}
}
