package Game;

import map.TilledMap;

import org.andengine.extension.tmx.TMXTile;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.input.touch.TouchEvent;

import projet.daywalker.MainActivity;

/**
 * Permet d'acceder au tiles et a leur propriétés
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public class UpdateTMXManager {
	
	MainActivity act;
	TilledMap map;
	TMXTile currentTile;
	
	/**
	 * Constructeur
	 * @param activity d'android
	 * @param currentmap tilledMap utilisé
	 */
	public UpdateTMXManager(MainActivity activity, TilledMap currentmap)
	{
		act = activity;
		map = currentmap;
	}
	
	/**
	 * Retourne la tile ou l'utilisateur a touché
	 * @param touch evenement produit
	 * @return tile associé a l'endroit du touché
	 */
	public TMXTile getTileWhere(TouchEvent touch)
	{
		currentTile = map.getBaseLayer().getTMXTileAt(touch.getX(), touch.getY());
		return currentTile;
	}
	
	/**
	 * Retourne la tile ou l'utilisateur a touché
	 * @param posX points x
	 * @param posY points y
	 * @return tile associé au pts en parametre
	 */
	public TMXTile getTileWhere(float posX, float posY)
	{
		currentTile = map.getBaseLayer().getTMXTileAt(posX, posY);
		return currentTile;
	}
	
	/**
	 * Verifie si la propriété passé en parametre a l'endroit désiré est là
	 * @param posX point x
	 * @param posY point y
	 * @param property propriétés a verifier
	 * @param value valeur de la propriété
	 * @return vrai si la propriété est belle et bien là et qu'elle possède la bonne valeur
	 */
	public boolean containPropertyAt(float posX, float posY, String property, String value)
	{
		currentTile = map.getBaseLayer().getTMXTileAt(posX, posY);
		if(currentTile.getTMXTileProperties(map.getMap()).containsTMXProperty(property, value)) return true;
		return false;
	}
	
	/**
	  * Verifie si la propriété passé en parametre a l'endroit désiré est là
	 * @param tile a examiné
	 * @param property propriétés a verifier
	 * @param value valeur de la propriété
	 * @return vrai si la propriété est belle et bien là et qu'elle possède la bonne valeur
	 */
	public boolean containPropertyFrom(TMXTile tile, String property, String value)
	{
		if(tile.getTMXTileProperties(map.getMap()).containsTMXProperty(property, value)) return true;
		return false;
	}
	
	/**
	  * Verifie si la propriété passé en parametre a l'endroit désiré est là
	 * @param property propriétés a verifier
	 * @param value valeur de la propriété
	 * @return vrai si la propriété est belle et bien là et qu'elle possède la bonne valeur
	 */
	public boolean containProperty(String property, String value)
	{
		if(currentTile.getTMXTileProperties(map.getMap()).containsTMXProperty(property, value)) return true;
		return false;
	}

}
