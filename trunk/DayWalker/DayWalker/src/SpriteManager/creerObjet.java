package SpriteManager;

import map.TilledMap;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;

import Game.RtsGame;
import Game.UpdateTMXManager;

import projet.daywalker.MainActivity;

import scenes.HUDisplay;

/**
 * Permet d'entrer en mode création et de créer des objets sur la map
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public class creerObjet {
	
	MainActivity act;
	RtsGame jeu;
	Sprite objCreer;
	
	/**
	 * Constructeur
	 * @param activity d'android
	 * @param game jeu en cours
	 * @param sprite sprite a afficher sur la map
	 */
	public creerObjet(MainActivity activity, RtsGame game, Sprite sprite)
	{
		act = activity;
		jeu = game;
		objCreer = sprite;
		Sprite barricade;
		
	    act.mZoomCamera.setHUD(act.hudCreation);
	    objCreer.setX(0);
	    objCreer.setY(0);
	    jeu.getScene().attachChild(objCreer);
	}
	
	/**
	 * Place un objet sur la map si il y a de la place et qu'il n'y a pas deja un autre objet
	 * @param touch evenement du touché de l'utilisateur
	 * @return vrai si l'objet a pu etre placé
	 */
	public boolean placerObjet(TouchEvent touch)
	{
		TMXTile currentTile;
		UpdateTMXManager tmxManager = new UpdateTMXManager(act, jeu.getMap());
		
		if(touch.getX() < 1170 && touch.getY() < 1170)
		{
			for(int i=0;i<=30;i+=30)
			{
				for(int j=0;j<=30;j+=30)
				{
					for(int k=0;k<jeu.getTiles().size();k++)
					{
						if(jeu.getTiles().get(k).equals(tmxManager.getTileWhere(touch.getX()+i, touch.getY()+j)))
						{
							return false;
						}
					}
					if(tmxManager.containPropertyAt(touch.getX()+i, touch.getY()+j, "block", "true"))
					{
						return false;
					}
				}
			}
			
			currentTile = tmxManager.getTileWhere(touch);
			objCreer.setPosition(currentTile.getTileX(), currentTile.getTileY());
			return true;
		}
		return false;
	}
	
	/**
	 * Retourne le sprite qui est placé sur la map
	 * @return le sprite qui est placé sur la map
	 */
	public Sprite getSprite()
	{
		return objCreer;
	}
}
