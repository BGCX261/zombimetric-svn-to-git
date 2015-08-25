package projet.daywalker;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Contient les informations d'une barricade
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public class Personnage extends Sprite implements Perso{
	
	int hauteur,
		largeur,
		hp,
		dmg,
		distAtt,
		aps;
	
	
	/**
	 * @param haut
	 * @param larg
	 * @param health
	 * @param damage
	 * @param distance
	 * @param apersecond
	 * @param texture
	 * @param vertexManager
	 */
	public Personnage(int haut, int larg, int health, int damage, int distance, int apersecond, TextureRegion texture, VertexBufferObjectManager vertexManager) {
		super(haut, larg, texture,	vertexManager);
		hauteur = haut;
		largeur = larg;
		hp = health;
		dmg = damage;
		distAtt = distance;
		aps = apersecond;
	}
	public double Attack(){
		return dmg*aps;
	}
	
	public void UpgrageHealth(int health){
		hp += health;
	}
	
	public void UpgrageDamage(int damage){
		dmg += damage;
	}
	
	public void UpgradeSpeed(int speed){
		aps += speed;
	}
	
	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public double getAps() {
		return aps;
	}

	public void setAps(int aps) {
		this.aps = aps;
	}
	
	
}
