package SpriteManager;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;

import android.util.Log;

import projet.daywalker.Perso;
import projet.daywalker.Personnage;
import projet.daywalker.SuperDebugBoy;

/**
 * Gère une liste de sprite et leurs id
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public class AnimatedSpriteManager {
	
	private List<Sprite> spriteList;
	private List<Integer> spriteId;
	
	Sprite spriteSelected;
	
	boolean someoneSelected;
	
	/**
	 * Constructeur
	 */
	public AnimatedSpriteManager()
	{
		spriteList = new ArrayList<Sprite>();
		spriteId = new ArrayList<Integer>();
		
		someoneSelected = false;
	}
	
	/**
	 * Ajoute un sprite a la liste
	 * @param sp sprite a ajouter
	 * @param id id du sprite a ajouter
	 */
	public void addSprite(Sprite sp, int id){
		spriteList.add(sp);
		spriteId.add(id);
	}
	
	/**
	 * Enleve le dernier sprite
	 */
	public void removeLastSprite()
	{
		spriteList.remove(spriteList.size());
		spriteId.remove(spriteList.size());
	}
	
	/**
	 * Enleve le sprite dont l'indice est pareille à celui en paramètre
	 * @param id du sprite à enlever
	 */
	public void removeSprite(int id)
	{
		spriteList.remove(id);
		spriteId.remove(id);
	}
	
	/**
	 * Retourne le sprite dont l'id est le meme que celui en parametre
	 * @param id du sprite a retourner
	 * @return sprite dont l'id correspond
	 */
	public Sprite spriteAt(int id)
	{
		return spriteList.get(id);
	}
	
	/**
	 * Retourne l'id a un indice donné
	 * @param id indice donné
	 * @return un id
	 */
	public int spriteIdAt(int id)
	{
		return spriteId.get(id).intValue();
	}
	
	/**
	 * Retourne le dernier sprite de la liste
	 * @return Retourne le dernier sprite
	 */
	public Sprite getLastSprite()
	{
		return spriteList.get(spriteList.size()-1);
	}
	
//=====================POUR TEST DU PATHFINDING, PAS DES FONCTIONS OFFICIELS===========================
	public void select(Personnage pers)
	{
		spriteSelected = spriteList.get(spriteList.indexOf(pers));
		someoneSelected = true;
	}
	
	/**
	 * @return
	 */
	public Sprite selectedSprite()
	{
		return spriteSelected;
	}
	
	/**
	 * @return
	 */
	public boolean isSomeoneSelected()
	{
		return someoneSelected;
	}
}
