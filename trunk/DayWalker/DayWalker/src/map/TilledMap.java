package map;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXProperties;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.extension.tmx.TMXTileProperty;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.TMXLoader.ITMXTilePropertiesListener;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.util.debug.Debug;

import projet.daywalker.MainActivity;

import android.content.res.AssetManager;
import android.util.Log;

/**
 * Contien la map sur laquelle le jeu se déroule
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public class TilledMap {
	
	boolean fail;
	Engine mEngine;
	//AssetManager mAssets;
	MainActivity act;
	
	Scene sc;
	
	private TMXTiledMap theMap;
	private TMXProperties<TMXTileProperty> tmxTileProperties;
	
	//util
    private Rectangle currentTileRectangle;
	
	/**
	 * Constructeur
	 * @param Activity d'android
	 */
	public TilledMap(MainActivity Activity)
	{
	  fail = false;
	  act = Activity;
	  mEngine = act.getEngine();
	}
    
    
	/**
	 * Permet de mettre la map en mémoire
	 * @param map nom de la map
	 */
	public void loadMap(String map){
	    try{
	    	final TMXLoader tmxLoader = new TMXLoader(act.getAssets(), mEngine.getTextureManager(), TextureOptions.BILINEAR_PREMULTIPLYALPHA, mEngine.getVertexBufferObjectManager(), new ITMXTilePropertiesListener(){
	            
				@Override
				public void onTMXTileWithPropertiesCreated(
						final TMXTiledMap pTMXTiledMap, final TMXLayer pTMXLayer,
						final TMXTile pTMXTile,
						final TMXProperties<TMXTileProperty> pTMXTileProperties) {
				}
	    		
	    	});
	    	
	    	theMap = tmxLoader.loadFromAsset(map);
	    	
	    }catch(final TMXLoadException e) {
			Debug.e(e);
			fail = true;
	    }
	}
	
	/**
	 * DEBUG dessine rectangle mauve sur la map
	 * @param thisScene scene sur laquelle les rectangles vont apparaitre
	 */
	public void creatingRectangle(Scene thisScene)
	{
		sc = thisScene;
		currentTileRectangle = new Rectangle(0, 0, theMap.getTileWidth(), theMap.getTileHeight(), act.getVertexBufferObjectManager());
		currentTileRectangle.setColor(1, 255, 0, 0.25f);
		thisScene.attachChild(currentTileRectangle);
	}
	
	/**
	 * DEBUG dessine un rectangle mauve a l'endroit que l'on touche
	 * @param thisScene scene sur laquelle les rectangles vont apparaitre
	 * @param thisSceneTouchEvent evenement du touché de l'utilisateur
	 * @param tmxLayer1 un layer de la map
	 * @param act activité d'android
	 */
	public void setRectangleOnTouch(Scene thisScene, final TouchEvent thisSceneTouchEvent, final TMXLayer tmxLayer1, MainActivity act)
	  {
		thisScene.registerUpdateHandler(new IUpdateHandler() {
			public void reset() { }

			public void onUpdate(final float pSecondsElapsed) {
				

				/* Get the tile where the dude touch on. */
				final TMXTile tmxTile = tmxLayer1.getTMXTileAt(thisSceneTouchEvent.getX(), thisSceneTouchEvent.getY());
				if(tmxTile != null) {
					// tmxTile.setTextureRegion(null); <-- Rubber-style removing of tiles =D
					currentTileRectangle.setPosition(tmxTile.getTileX(), tmxTile.getTileY());
				}
			}
		});
	  }
	
	/**
	 * Retourne le premier layer de la map
	 * @return le premier layer de la map
	 */

	public void setRectangleAt(TMXTile bob)
	{
	    Log.i("path", "DU BONHEUR");
		Rectangle bobtator = new Rectangle(70, 70, theMap.getTileWidth(), theMap.getTileHeight(), act.getVertexBufferObjectManager());
		bobtator.setColor(0.10f, 0.15f, 0.25f);
		sc.attachChild(bobtator);
	}
	

	public TMXLayer getBaseLayer()
	{
		return theMap.getTMXLayers().get(0);
	}
	
	/**
	 * Retourne la map
	 * @return la map
	 */
	public TMXTiledMap getMap()
	{
		return theMap;
	}
}
