package com.test.testsprite;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.ZoomCamera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.PinchZoomDetector;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.SurfaceScrollDetector;
import org.andengine.input.touch.detector.PinchZoomDetector.IPinchZoomDetectorListener;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

import android.os.SystemClock;
import android.widget.Toast;



public class MainActivity extends SimpleBaseGameActivity implements IOnSceneTouchListener, IScrollDetectorListener, IPinchZoomDetectorListener{

	private ZoomCamera mZoomCamera;
	private SurfaceScrollDetector mScrollDetector;
	private PinchZoomDetector mPinchZoomDetector;
	private float mPinchZoomStartedCameraZoomFactor;
	
	private Scene scene;
	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;
	
	private BitmapTextureAtlas yourTexture;
	private ITextureRegion yourTextureRegion;
	private BitmapTextureAtlas mapTexture;
	private ITextureRegion mapTextureRegion;
	
	private Sprite face;
	
	private boolean premier = true;
	private long temps1,
				 temps2;
	
	@Override
	public EngineOptions onCreateEngineOptions() {
	    mZoomCamera = new ZoomCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
	    mZoomCamera.setBounds(0, 0, 844, 844);
	    mZoomCamera.setBoundsEnabled(true);
	    EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, 
	    new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mZoomCamera);
	    return engineOptions;
	}

	@Override
	protected void onCreateResources() {
	   BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("image/");
	   yourTexture = new BitmapTextureAtlas(getTextureManager(), 450, 300, TextureOptions.NEAREST);
	   yourTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(yourTexture, this, "smb.jpg", 0, 0);
	   yourTexture.load();
	   mapTexture = new BitmapTextureAtlas(getTextureManager(), 844, 844, TextureOptions.NEAREST);
	   mapTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mapTexture, this, "belder.jpg", 0, 0);
	   mapTexture.load();
		
	}

	@Override
	protected Scene onCreateScene() {
		scene = new Scene();
//		this.scene.setOnAreaTouchTraversalFrontToBack();
	    scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
	    
	    Sprite background = new Sprite(0, 0, this.mapTextureRegion, this.getVertexBufferObjectManager());
	    face = new Sprite(0, 0, this.yourTextureRegion, this.getVertexBufferObjectManager());
	    face.setSize(25, 20);
	    this.mPinchZoomDetector = new PinchZoomDetector(this);
	    this.mScrollDetector = new SurfaceScrollDetector(this);
	    
	    this.scene.setOnSceneTouchListener(this);
		this.scene.setTouchAreaBindingOnActionDownEnabled(true);
	    
		scene.registerTouchArea(face);
	    scene.attachChild(background);
	    scene.attachChild(face);
	    
	    return scene;
	}

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
			if(pSceneTouchEvent.isActionUp())
			{
				if(SystemClock.uptimeMillis() - temps1 >=500){
					premier = true;
				}
				
				if(premier){					
					premier = false;
				}
				else{
					MoveModifier modifier = new MoveModifier(5, face.getX(), pSceneTouchEvent.getX(), face.getY(), pSceneTouchEvent.getY());			               
					face.registerEntityModifier(modifier);
					
					premier = true;				
				}
				temps1 = SystemClock.uptimeMillis();
			}
		}

		return true;
	}



}
