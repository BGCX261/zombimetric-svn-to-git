package projet.daywalker;

import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Contient les informations d'une barricade
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public class Barricade extends Building {

	public Barricade(int haut, int larg, TextureRegion texture,VertexBufferObjectManager vertexManager) {
		super("image/barricade.png", 500, haut, larg, texture, vertexManager);		
	}
	
	

}
