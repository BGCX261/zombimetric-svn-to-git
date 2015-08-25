package scenes;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.sprite.Sprite;

import android.util.Log;

/**
 * Contient les sprites et les actions d'un hud
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public class HUDisplay extends HUD {
	
	/**
	 * Constructeur
	 * @param sprite les sprites à ajouter au hud
	 */
	public HUDisplay(Sprite sprite[])
	{
		super();
		for(int i=0;i<sprite.length;i++)
		{
			registerTouchArea(sprite[i]);
			attachChild(sprite[i]);
		}
	}
	
	/**
	 * Enleve le sprite du hud
	 * @param num numero du sprite a enlever
	 */
	public void enleveSprite(int num)
	{
		detachChild(num);
	}
	
	/**
	 * Ajoute un/des sprite au hud
	 * @param sprite Les sprites a ajouter
	 */
	public void ajoutSprite(Sprite sprite[])
	{
		for(int i=0;i<sprite.length;i++)
		{
			registerTouchArea(sprite[i]);
			attachChild(sprite[i]);
		}
	}
	
	/**
	 * Retourne une liste des sprites qui sont dans le hud
	 * @return une liste des sprites présent
	 */
	public Sprite[] getSprite()
	{
		Sprite allChild[] = new Sprite[getChildCount()];
		Sprite temp;
		Log.i("Info", "getSprite error 1 !");
		for(int i=0;i<getChildCount();i++)
		{
			temp = (Sprite)getChildByIndex(i);
			allChild[i] = temp;
			Log.i("Info", "getSprite error 2 !");
		}
		return allChild;
	}
}
