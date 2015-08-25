package projet.daywalker;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

//A DEFINIR PLUS TARD !! N'EST PAS UTILISÉ MAIS POURRA ETRE UTILE !!!!!!!!!!!!!!!

public class Monster extends Personnage {
	
	public Monster(int haut, int larg, int health, int damage, int distance,
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
		recherche;
	
	String img;
	
	/*public Monster() {
		super(25, 20, 50, 3, 15, 1500, );
		recherche = 100;
	}*/

}
