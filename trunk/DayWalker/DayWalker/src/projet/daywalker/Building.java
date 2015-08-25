package projet.daywalker;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Contient les informations des buildings
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public class Building extends Sprite{
	
	private int hp;
	
	String img;
	
	/**
	 * Constructeur qui definit par defaut de la vie et l'image de base
	 * @param haut hauteur de l'image
	 * @param larg largeur de l'image
	 * @param texture texture de l'image
	 * @param vertexManager VertexManager de l'activité
	 */
	public Building(int haut, int larg, TextureRegion texture,VertexBufferObjectManager vertexManager){
		super(haut, larg, texture,	vertexManager);
		hp = 1000;
		img = "image/base.png";
	}
	
	/**
	 * Constructeur qui recoit tout en parametres
	 * @param path chemin de l'image
	 * @param hitPoint point de vie de la batisse
	 * @param haut hauteur de l'image
	 * @param larg largeur de l'image
	 * @param texture texture de l'image
	 * @param vertexManager VertexManager de l'activité
	 */
	public Building(String path, int hitPoint, int haut, int larg, TextureRegion texture,VertexBufferObjectManager vertexManager)
	{
		super(haut, larg, texture,	vertexManager);
		hp = hitPoint;
		img = path;
	}
	
	/**
	 * Retourne les points de vie
	 * @return les points de vie
	 */
	public int getHp(){
		return hp;
	}
	
	/**
	 * Permet de changer les points de vie
	 * @param health les points de vie a setter
	 */
	public void setHp(int health){
		hp = health;
	}
	
	/**
	 * reduit la vie du nombre de l'attaque de l'attaquant
	 * @param att attaque de l'attaquant
	 */
	public void receiveDamage(int att){
		hp -= att;
	}
	
	/**
	 * Retourne le chemin de l'image du batiment
	 * @return le chemin de l'image du batiment
	 */
	public String getImg(){
		return img;
	}
}
