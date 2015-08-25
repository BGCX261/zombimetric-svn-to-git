package SpriteManager;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;

/**
 * Verifie si le sprite est dans la liste des sprites selectionée
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public class SelectManager {
	
	private boolean enSelection;
	
	private List<Sprite> listSelector;
	private List<Integer> listId;
	
	/**
	 * Constructeur initialisant
	 */
	public SelectManager()
	{
		enSelection = false;
		listSelector = new ArrayList<Sprite>();
		listId = new ArrayList<Integer>();
		
	}
	
	/**
	 * Ajoute un sprite a la liste des sprites selectionné
	 * @param sp sprite a ajouter
	 * @param id indice du sprite a ajouté
	 */
	public void add(Sprite sp, int id)
	{
		if(!enSelection)
		{
			enSelection = true;
		}
		listSelector.add(sp);
		listId.add(id);
	}
	
	/**
	 * Retourne le dernier sprite
	 * @return le dernier sprite
	 */
	public Sprite getLastSprite()
	{
		return listSelector.get(listSelector.size()-1);
	}
	
	/**
	 * Retourne si la liste est en selection
	 * @return si la liste est en selection
	 */
	public boolean isOnSelection()
	{
		return enSelection;
	}
	
	/**
	 * Efface la selection
	 */
	public void clear()
	{
		if(enSelection)
		{
		  listSelector.clear();
		  listId.clear();
		  enSelection = false;
		}
	}
	
	
	

}
