package scenes;

import java.util.ArrayList;
import java.util.List;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.PinchZoomDetector;
import org.andengine.input.touch.detector.SurfaceScrollDetector;
import org.andengine.ui.activity.BaseGameActivity;

import Game.Director;
import Game.RtsGame;
import Game.UpdateTMXManager;
import SpriteManager.creerObjet;
import android.util.Log;

import projet.daywalker.MainActivity;

/**
 * Scène du jeu en cours
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public class RTSscene extends Scene{
	
  MainActivity act;
  RtsGame theGame;
  creerObjet creationBarricade;
    
  /**
   * Constructeur
   * @param activity d'android
   * @param gm Jeu en cours
   */
  public RTSscene(MainActivity activity, RtsGame gm)
  {
	act = activity;
	theGame = gm;
  }
  
  /**
   * Initialise la scène
   */
  public void iniScene()
  {
	    act.survivor = new ArrayList<Sprite>();
	    act.selection = new ArrayList<Sprite>();
	    
	    act.sprite_hudBase = new Sprite[4];
	    act.sprite_hudToujours = new Sprite[1];
	    Sprite creation[] = new Sprite[3];
	    
	    act.sprite_hudBase[0] = new Sprite(520, 640, act.add_survivorTextureRegion, act.getVertexBufferObjectManager()){
		    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
			    if(pSceneTouchEvent.isActionUp()){
				    act.creationUnit = true;
			    }
			    return true;
		    }
	    };
	    act.sprite_hudBase[0].setSize(80, 80);
	    act.sprite_hudBase[1] = new Sprite(600, 640, act.add_scavengerTextureRegion, act.getVertexBufferObjectManager());
	    act.sprite_hudBase[1].setSize(80, 80);
	    act.sprite_hudBase[2] = new Sprite(1120, 640, act.pauseTextureRegion, act.getVertexBufferObjectManager()){
		    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	    		if(pSceneTouchEvent.isActionUp()){
	    			act.mZoomCamera.setZoomFactor(1);
	    			act.mZoomCamera.setCenter(0, 0);
	    			act.getEngine().setScene(act.menu);
	    		}
	    		return true;
	    	}
	    };
	    act.sprite_hudBase[3] = new Sprite(680, 640, act.add_barricadeTextureRegion, act.getVertexBufferObjectManager()){
	    	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	    		if(pSceneTouchEvent.isActionUp()){
	    			creationBarricade = new creerObjet(act, theGame, new Sprite(30, 30, act.barricadeTextureRegion, act.getVertexBufferObjectManager()));
	    		}
	    		return true;
	    	}
	    };
	    act.sprite_hudBase[3].setSize(80, 80);
	    
	    act.sprite_hudToujours[0] = new Sprite(1120, 640, act.pauseTextureRegion, act.getVertexBufferObjectManager()){
		    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	    		if(pSceneTouchEvent.isActionUp()){
	    			act.mZoomCamera.setZoomFactor(1);
	    			act.mZoomCamera.setCenter(0, 0);
	    			act.getEngine().setScene(act.menu);
	    		}
	    		return true;
	    	}
	    };
		
		creation[0] = new Sprite(520, 640, act.acceptTextureRegion, act.getVertexBufferObjectManager()){
	    	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	    		if(pSceneTouchEvent.isActionUp()){
	    			act.mZoomCamera.setHUD(act.hudToujours);
	    			UpdateTMXManager tempTile = new UpdateTMXManager(act, theGame.getMap());
	    			for(int i=0;i<=30;i+=30)
	    			{
	    				for(int j=0;j<=30;j+=30)
		    			{
		    				theGame.addTile(tempTile.getTileWhere(creationBarricade.getSprite().getX()+i+30, creationBarricade.getSprite().getY()+j+30));
		    				Log.i("Joe", "points : "+(creationBarricade.getSprite().getX()+i)+" "+(creationBarricade.getSprite().getY()+j));
		    			}
	    			}
	    			Log.i("Joe", "size : "+theGame.getTiles().size());
	    		}
	    		return true;
	    	}
	    };
	    creation[1] = new Sprite(600, 640, act.cancelTextureRegion, act.getVertexBufferObjectManager()){
	    	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	    		if(pSceneTouchEvent.isActionUp()){
	    			creationBarricade.getSprite().detachSelf();
	    			act.mZoomCamera.setHUD(act.hudToujours);
	    		}
	    		return true;
	    	}
	    };
		creation[2] = new Sprite(1120, 640, act.pauseTextureRegion, act.getVertexBufferObjectManager()){
		    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	    		if(pSceneTouchEvent.isActionUp()){
	    			act.mZoomCamera.setZoomFactor(1);
	    			act.mZoomCamera.setCenter(0, 0);
	    			act.getEngine().setScene(act.menu);
	    		}
	    		return true;
	    	}
	    };
	    
		act.base = new Sprite(128, 216, act.baseTextureRegion, act.getVertexBufferObjectManager()){
	    	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
	    		if(pSceneTouchEvent.isActionUp()){
	    			if(act.mZoomCamera.getHUD() != act.hudBase && act.mZoomCamera.getHUD() != act.hudCreation)
	    				act.mZoomCamera.setHUD(act.hudBase);	    			
	    		}
	    		
	    		return true;
	    	}
	    };
	    act.base.setSize(60, 60);
	    
	    UpdateTMXManager tempTile = new UpdateTMXManager(act, theGame.getMap());
		
	    for(int i=0;i<=30;i+=30)
		{
			for(int j=0;j<=30;j+=30)
			{
				theGame.addTile(tempTile.getTileWhere(act.base.getX()+i+30, act.base.getY()+j+30));
			}
		}
	    
	    act.mPinchZoomDetector = new PinchZoomDetector(act);
	    act.mScrollDetector = new SurfaceScrollDetector(act);
	    
	    this.setOnSceneTouchListener(act);
	    this.setTouchAreaBindingOnActionDownEnabled(true);

		this.registerTouchArea(act.base);
	    this.attachChild(act.base);
	    
	    this.registerUpdateHandler(new IUpdateHandler() {                    
	        public void reset() {        
	        }             
	        public void onUpdate(float pSecondsElapsed) {
	        	if(act.creationUnit == true)
	        	{
	        		theGame.addSurvivor();
	        		act.creationUnit = false;
	        	}
	        }
	    });
	    
	    act.hudToujours = new HUDisplay(act.sprite_hudToujours);	    
	    act.hudBase = new HUDisplay(act.sprite_hudBase);
	    act.hudCreation = new HUDisplay(creation);
	    act.mZoomCamera.setHUD(act.hudToujours);
  }
  
  /**
   * Retourne l'objet qui permet de creer des barricades
   * @return l'objet qui permet de creer des barricades
   */
  public creerObjet getCreerBarricade()
  {
	  return creationBarricade;
  }
}
