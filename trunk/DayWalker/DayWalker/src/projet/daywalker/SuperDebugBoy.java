package projet.daywalker;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.algorithm.path.Path;

import android.util.Log;

/**
 * Personnage permettant de debugg (faire tout les tests)
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public class SuperDebugBoy extends Personnage implements Perso{
	int hauteur,
		largeur,
		hp,
		dmg,
		distAtt,
		aps,
		ressource;
	
	boolean selected;
	
	Path path;

    String img;
    
	/**
	 * Constructeur
	 * @param haut Hauteur du personnage
	 * @param larg Largeur du personnage
	 * @param health Vie du personnage
	 * @param damage Attaque du personnage
	 * @param distance Distance d'attaque du personnage
	 * @param apersecond Attaque par seconde du personnage
	 * @param texture Texture du personnage
	 * @param vertexManager VertexManager d'android
	 */
	public SuperDebugBoy(int haut, int larg, int health, int damage,
			int distance, int apersecond, TextureRegion texture,VertexBufferObjectManager vertexManager) {
		
		super(haut, larg, health, damage, distance, apersecond, texture, vertexManager);
		selected = false;
	}
	
	/**
	 * Selectionne le personnage
	 */
	public void select()
	{
		selected = true;
	}
	
	/**
	 * Deselectionne le personnage
	 */
	public void unSelect()
	{
		selected = false;
	}
	
	/**
	 * Retourne si le personnage est selectionné
	 * @return si le personnage est selectionné
	 */
	public boolean isSelected()
	{
		return selected;
	}
	
	/**
	 * Permet de setter le chemin a suivre
	 * @param patrick chemin a suivre
	 */
	public void setPath(Path patrick)
	{
		path = patrick;
	}
	
	/**
	 * Fait bouger le personnage selon un chemin
	 */
	public void move()
	{
		//Supposé avoir du pathfinding T.T
	}

}
