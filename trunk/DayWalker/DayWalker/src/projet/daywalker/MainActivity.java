package projet.daywalker;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import map.Map;
import map.TilledMap;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.ZoomCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXLoader;
import org.andengine.extension.tmx.TMXProperties;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.extension.tmx.TMXTileProperty;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.extension.tmx.TMXLoader.ITMXTilePropertiesListener;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.PinchZoomDetector;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.SurfaceScrollDetector;
import org.andengine.input.touch.detector.PinchZoomDetector.IPinchZoomDetectorListener;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.Constants;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

import bd.AssetExplorer;
import bd.MapInsert;

import projet.daywalker.deplacement.Points;
import scenes.HUDisplay;
import scenes.RTSscene;

import Game.Director;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

/**
 * Main du programme. C'est la dedans que la premiere scene s'initialise et que le scroll, le zoom, etc se produisent
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public class MainActivity extends SimpleBaseGameActivity implements IOnSceneTouchListener, IScrollDetectorListener, IPinchZoomDetectorListener{

	private static final int   CAMERA_WIDTH = 1200,
							   CAMERA_HEIGHT = 720,
							   CAMERA_BOUND_WIDTH = 1200,
							   CAMERA_BOUND_HEIGHT = 1200;
	
	public ZoomCamera 		   mZoomCamera;
	public SurfaceScrollDetector mScrollDetector;
	public PinchZoomDetector   mPinchZoomDetector;
	private float 			   mPinchZoomStartedCameraZoomFactor;
	
	public HUDisplay 		   hudBase,
					 		   hudToujours,
					 		   hudCreation;
	
	
	public Scene 			   menu;
	
	
	
	/**************************Texture**************************/
	private Font 			   font,
				 			   fontReprendre;
	
	private BitmapTextureAtlas yourTexture,
							   mapTexture,
							   baseTexture,
							   add_survivorTexture,
							   add_scavengerTexture,
							   add_barricadeTexture,
							   pauseTexture,
							   jouerTexture,
							   optionsTexture,
							   reprendreTexture,
							   barricadeTexture,
							   cancelTexture,
							   acceptTexture;
	
	public TextureRegion 	   yourTextureRegion,
						 	   mapTextureRegion,
						 	   baseTextureRegion,
						 	   add_survivorTextureRegion,
						 	   add_scavengerTextureRegion,
						 	   add_barricadeTextureRegion,
						 	   pauseTextureRegion,
						 	   jouerTextureRegion,
						 	   optionsTextureRegion,
						 	   reprendreTextureRegion,
						 	   barricadeTextureRegion,
						 	   cancelTextureRegion,
						 	   acceptTextureRegion;
	
	/****************TESTMAP***************************/
	private TilledMap 		   thatMap;
	private TMXTiledMap 	   mTMXTiledMap;
	private TMXLayer 		   tmxLayer;
	private TMXProperties<TMXTileProperty> tmxTileProperties;
	public Director 		   directeur;
	/****************FINTEST***************************/
	
	public Sprite 			   sprite_hudBase[],
				  			   sprite_hudToujours[],
				  			   sprite_menu[],
				  			   base;
	
	public List<Sprite> 	   survivor,
							   selection;
	
	private Text 			   txt_jouer,
				 			   txt_reprendre,
				 			   txt_option;
	
	private boolean 		   premier = true;
	
	public boolean 			   creationUnit = false;
	
	private long 			   temps1,
				 			   temps2;
	
	private boolean 		   loaded = false;
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		
		mZoomCamera = new ZoomCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
	    mZoomCamera.setBounds(0, 0, CAMERA_BOUND_WIDTH, CAMERA_BOUND_HEIGHT);
	    mZoomCamera.setBoundsEnabled(true);
	    
	    EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, 
	    new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mZoomCamera);

		directeur = new Director(this);
		
		AssetExplorer asset = new AssetExplorer(this);
		MapInsert accesBD = new MapInsert(this);
		accesBD.open();
		accesBD.gererMap(asset);
		accesBD.close();
	    
		return engineOptions;
	}

	@Override
	protected void onCreateResources() {
	   
		loadFonts();
		
		loadGraphics();
	   
	}
	
	/**
	 * Stock en mémoire les fonts d'écriture à utiliser
	 */
	public void loadFonts()
	{
		FontFactory.setAssetBasePath("font/");
		
		final ITexture fontTexture = new BitmapTextureAtlas(getTextureManager(), 328, 328, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		font = FontFactory.createFromAsset(getFontManager(), fontTexture, this.getAssets(), "font1.ttf", 70, true, Color.BLACK);
	    font.load();
		
		final ITexture fontTextReprendre = new BitmapTextureAtlas(getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		fontReprendre = FontFactory.createFromAsset(getFontManager(), fontTextReprendre, this.getAssets(), "font1.ttf", 55, true, Color.BLACK);
		fontReprendre.load();
	}
	
	/**
	 * Stock en mémoire les images à utiliser
	 */
	public void loadGraphics()
	{
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("image/");
		   
		yourTexture = new BitmapTextureAtlas(getTextureManager(), 450, 300, TextureOptions.NEAREST);
		yourTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(yourTexture, this, "smb.jpg", 0, 0);
		yourTexture.load();
		
		barricadeTexture = new BitmapTextureAtlas(getTextureManager(), 400, 400, TextureOptions.NEAREST);
		barricadeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(barricadeTexture, this, "barrier1.png", 0, 0);
		barricadeTexture.load();
   
		baseTexture = new BitmapTextureAtlas(getTextureManager(), 574, 574, TextureOptions.NEAREST);
		baseTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(baseTexture, this, "base.jpg", 0, 0);
		baseTexture.load();
   
		add_survivorTexture = new BitmapTextureAtlas(getTextureManager(), 50, 50, TextureOptions.NEAREST);
		add_survivorTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(add_survivorTexture, this, "add_survivor.png", 0, 0);
		add_survivorTexture.load();
   
		add_scavengerTexture = new BitmapTextureAtlas(getTextureManager(), 50, 50, TextureOptions.NEAREST);
		add_scavengerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(add_scavengerTexture, this, "add_scavenger.png", 0, 0);
		add_scavengerTexture.load();
		
		add_barricadeTexture = new BitmapTextureAtlas(getTextureManager(), 80, 80, TextureOptions.NEAREST);
		add_barricadeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(add_barricadeTexture, this, "add_barrier.png", 0, 0);
		add_barricadeTexture.load();
   
		pauseTexture = new BitmapTextureAtlas(getTextureManager(), 80, 80, TextureOptions.NEAREST);
		pauseTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(pauseTexture, this, "pause.jpg", 0, 0);
		pauseTexture.load();   
   
		jouerTexture = new BitmapTextureAtlas(getTextureManager(), 400, 100, TextureOptions.NEAREST);
		jouerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(jouerTexture, this, "jouer1.png", 0, 0);
		jouerTexture.load();
   
		optionsTexture = new BitmapTextureAtlas(getTextureManager(), 400, 100, TextureOptions.NEAREST);
		optionsTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(optionsTexture, this, "jouer1.png", 0, 0);
		optionsTexture.load();
   
		reprendreTexture = new BitmapTextureAtlas(getTextureManager(), 400, 100, TextureOptions.NEAREST);
		reprendreTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(reprendreTexture, this, "reprendre.png", 0, 0);
		reprendreTexture.load();
		
		acceptTexture = new BitmapTextureAtlas(getTextureManager(), 80, 80, TextureOptions.NEAREST);
		acceptTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(acceptTexture, this, "Accept.png", 0, 0);
		acceptTexture.load();
		
		cancelTexture = new BitmapTextureAtlas(getTextureManager(), 80, 80, TextureOptions.NEAREST);
		cancelTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cancelTexture, this, "Cancel.png", 0, 0);
		cancelTexture.load();
	}
	
	@Override
	protected Scene onCreateScene() {
		
		menu = new Scene();		
		sprite_menu = new Sprite[2];
		
		sprite_menu[0] = new Sprite(400, 150, jouerTextureRegion, getVertexBufferObjectManager()){
	    	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	    		if(pSceneTouchEvent.isActionUp() && !txt_reprendre.hasParent()){
	    			Log.i("Bob", "StartGame");
	    			menu.attachChild(txt_reprendre);
	    			directeur.loadRTSTilledGame("tmx/bob.tmx");
	    			directeur.startGame();
	    			loaded = true;
	    			menu.detachChild(txt_jouer);
	    		}
	    		else if(pSceneTouchEvent.isActionUp() && txt_reprendre.hasParent())
	    		{
	    			directeur.ContinueGame();
	    		}	
	    		return true;
	    	}
	    };
	    sprite_menu[1] = new Sprite(400, 300, optionsTextureRegion, getVertexBufferObjectManager());
		
	    txt_jouer = new Text(440, 150, font, getString(R.string.jouer), getVertexBufferObjectManager());
		txt_reprendre = new Text(420, 150, fontReprendre, getString(R.string.reprendre), getVertexBufferObjectManager());
		txt_option = new Text(410, 300, font, getString(R.string.opt), getVertexBufferObjectManager());

		menu.registerTouchArea(sprite_menu[0]);
		menu.attachChild(sprite_menu[0]);
		menu.attachChild(sprite_menu[1]);
		menu.attachChild(txt_jouer);
		menu.attachChild(txt_option);
	    
		return menu;
	}
	
	/*********************************MOUVEMENT CAMERA*************************************************/

	@Override
	public void onPinchZoomStarted(PinchZoomDetector pPinchZoomDetector, TouchEvent pSceneTouchEvent) {
		this.mPinchZoomStartedCameraZoomFactor = this.mZoomCamera.getZoomFactor();
		
	}

	@Override
	public void onPinchZoom(PinchZoomDetector pPinchZoomDetector, TouchEvent pTouchEvent, float pZoomFactor) {
		this.mZoomCamera.setZoomFactor(this.mPinchZoomStartedCameraZoomFactor * pZoomFactor);
		if(mZoomCamera.getZoomFactor() < 1)
		{
			mZoomCamera.setZoomFactor(1);
		}		
	}

	@Override
	public void onPinchZoomFinished(PinchZoomDetector pPinchZoomDetector, TouchEvent pTouchEvent, float pZoomFactor) {
		this.mZoomCamera.setZoomFactor(this.mPinchZoomStartedCameraZoomFactor * pZoomFactor);
		if(mZoomCamera.getZoomFactor() < 1)
		{
			mZoomCamera.setZoomFactor(1);
		}
	}

	@Override
	public void onScrollStarted(ScrollDetector pScollDetector, int pPointerID, float pDistanceX, float pDistanceY) {
		final float zoomFactor = this.mZoomCamera.getZoomFactor();
		this.mZoomCamera.offsetCenter(-pDistanceX / zoomFactor, -pDistanceY / zoomFactor);		
	}

	@Override
	public void onScroll(ScrollDetector pScollDetector, int pPointerID, float pDistanceX, float pDistanceY) {
		final float zoomFactor = this.mZoomCamera.getZoomFactor();
		this.mZoomCamera.offsetCenter(-pDistanceX / zoomFactor, -pDistanceY / zoomFactor);
	}

	@Override
	public void onScrollFinished(ScrollDetector pScollDetector, int pPointerID, float pDistanceX, float pDistanceY) {
		final float zoomFactor = this.mZoomCamera.getZoomFactor();
		this.mZoomCamera.offsetCenter(-pDistanceX / zoomFactor, -pDistanceY / zoomFactor);
	}
	/************************************FIN MOUVEMENT CAMERA**********************************************/
	/******************************************************************************************************/

	
	
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {

		this.mPinchZoomDetector.onTouchEvent(pSceneTouchEvent);
		
		if(this.mPinchZoomDetector.isZooming()) {
			this.mScrollDetector.setEnabled(false);
		} 
		else{
			if(pSceneTouchEvent.isActionDown()){
				this.mScrollDetector.setEnabled(true);
			}
			this.mScrollDetector.onTouchEvent(pSceneTouchEvent);
		}
		
		directeur.UpdateOnTouchCurrentGame(pSceneTouchEvent);

		return true;
	}

  /**
   * Retourne le PinchZoomDetector
   * @return le PinchZoomDetector
   */
  public PinchZoomDetector getPinchZoomDetector()
  {
	  return mPinchZoomDetector;
  }

  /**
   * Retourne le SurfaceScrollDetector
   * @return le SurfaceScrollDetector
   */
  public SurfaceScrollDetector getScrollDetector()
  {
	  return mScrollDetector;
  }

}
