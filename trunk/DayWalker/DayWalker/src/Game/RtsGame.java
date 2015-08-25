package Game;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

import map.TilledMap;

import org.andengine.engine.Engine;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXProperties;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.extension.tmx.TMXTileProperty;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import SpriteManager.AnimatedSpriteManager;
import SpriteManager.SelectManager;
import android.util.DisplayMetrics;
import android.util.Log;

import projet.daywalker.MainActivity;
import projet.daywalker.SuperDebugBoy;
import scenes.RTSscene;

/**
 * Permet de gérer toute la partie qui se déroulera (les maps, le pause, etc)
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public class RtsGame{
	
	private Director lead;
	
	//used for util purpose
	private DisplayMetrics metrics;
	
	//new Scene
	RTSscene thisScene;
	ArrayList<TMXTile> tileUtilise = new ArrayList<TMXTile>();
	
	//personages
	AnimatedSpriteManager personages;
	SelectManager selection;
	int spriteId;
	int spriteIdEnSelection;
	
	
	//the TilledMap attribut
	private TilledMap thatMap;
	
	private List<List<String>> property;
	//private TMXTiledMap mTMXTiledMap;
	//private TMXLayer tmxLayer;
	//private TMXProperties<TMXTileProperty> tmxTileProperties;
	private String tmxMapLocation;
	
	/**
	 * 
	 * @param map nom de la map a utilisé
	 * @param director Directeur s'occupant du jeu
	 */
	public RtsGame(String map, Director director)
	{
		Log.i("Bob", "Create RTS game");
		personages = new AnimatedSpriteManager();
		selection = new SelectManager();
		tmxMapLocation = map;
		lead = director;
		thatMap = new TilledMap(lead.getActivity());
		thatMap.loadMap(tmxMapLocation);
		metrics = new DisplayMetrics();
		spriteId = 0;
		Log.i("Bob", "Create RTS game1");
		setPropertyList();
		Log.i("Bob", "Create RTS game2");
	}
	
	/**
	 * Initialise le jeu avec une scène
	 * @return une confirmation que ça c'est bien passé
	 */
	public boolean initGame()
	{	
		thisScene = new RTSscene(lead.getActivity(), this);
		lead.getActivity().getEngine().setScene(thisScene);
		lead.getActivity().getEngine().getScene().attachChild(thatMap.getBaseLayer());
		thisScene.iniScene();
	    thatMap.creatingRectangle(this.getScene());
		return true;
	}
	
	/**
	 * Retourne la tilled map utilisé
	 * @return une tilledmap
	 */
	public TilledMap getMap()
	{
		return thatMap;
	}
	
	/**
	 * Retourne la scene utilisé
	 * @return une scene
	 */
	public RTSscene getScene()
	{
		return thisScene;
	}
	
	/**
	 * Remet le jeu en marche
	 */
	public void unPause()
	{
		lead.getActivity().getEngine().setScene(thisScene);
	}
	
	/**
	 * Permet d'ajouter des tmxTile a un tableau de tmxTile deja utilisé
	 * @param utilise un tableau de tmxTile
	 */
	public void addTile(TMXTile utilise[])
	{
		for(int i=0;i<utilise.length;i++)
		{
			tileUtilise.add(utilise[i]);
		}		
	}
	
	/**
	 * Permet d'ajouter une tmxTile a un tableau de tmxTile deja utilisé
	 * @param utilise une tmxTile 
	 */
	public void addTile(TMXTile utilise)
	{
		tileUtilise.add(utilise);	
	}
	
	/**
	 * Retourne les tiles utilise
	 * @return une ArrayListe de toute les tilesDeja utilise
	 */
	public ArrayList<TMXTile> getTiles()
	{
		return tileUtilise;
	}
	
	/**
	 * Retourne la liste des personnage selectionner
	 * @return les personnages selectionner
	 */
	public AnimatedSpriteManager getSpriteManager()
	{
		return personages;
	}

	/**
	 * Ajoute un individu sur la map
	 */
	public void addSurvivor()
	{
		TextureRegion bo = lead.getActivity().yourTextureRegion;
		VertexBufferObjectManager bob = lead.getActivity().getVertexBufferObjectManager();
		
		Log.i("Bob","In creation Creates!");
		personages.addSprite(new SuperDebugBoy(230, 250, 0, 0, 0, 0, bo, bob)
		{
	    	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY)
	    	{
	    		Log.i("Info","Is touched!");
	    		//selection.add(this, spriteIdEnSelection++);
	    		personages.select(this);
	    		this.select();
	    		return true;
	    	}
	    	
	    },  spriteId++);
		Log.i("Info", "Creation d'un unit!!!!!!");
		personages.getLastSprite().setSize(25, 20);
	    lead.getActivity().getEngine().getScene().registerTouchArea(personages.getLastSprite());
	    lead.getActivity().getEngine().getScene().attachChild(personages.getLastSprite());
		//lead.getActivity().createSurvivor();
	}
	
	/**
	 * Crée la liste des propriétés de base du jeu (map)
	 */
	private void setPropertyList()
	{
		Log.i("Bob", "Creating Properties...");
		property = new ArrayList<List<String>>();
		List<String> littleString = new ArrayList<String>();
		
		littleString.add("block");
		littleString.add("true");
	
		property.add(littleString);
	}
	
	/**
	 * Retourne la liste des propriétés
	 * @return la liste des propriétés
	 */
	public List<List<String>> getProperty()
	{
		return property;
	}

}
