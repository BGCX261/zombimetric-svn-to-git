package projet.daywalker;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

//A DEFINIR PLUS TARD !! N'EST PAS UTILISÉ MAIS POURRA ETRE UTILE !!!!!!!!!!!!!!!

public class Scavenger extends Personnage {
	public Scavenger(int haut, int larg, int health, int damage, int distance,
			int apersecond, TextureRegion texture, VertexBufferObjectManager vertexManager) {
		super(haut, larg, health, damage, distance, apersecond, texture, vertexManager);
		// TODO Auto-generated constructor stub
	}

	int hauteur,
		largeur,
		hp,
		dmg,
		distAtt,
		aps,
		ressource;

	String img;

	/*public Scavenger(int haut, int larg, int health, int damage, int distance, int apersecond) {
		super(25, 20, 50, 3, 15, 1500, "image/Scavenger.png");
		ressource = 0;
	}*/

}
