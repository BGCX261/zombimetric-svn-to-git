package Game;

import org.andengine.input.touch.TouchEvent;

import PathFinding.AStar;
import android.util.Log;
import projet.daywalker.MainActivity;

/**
 * Permet de gérer toute l'activité qui se déroulera (les maps, les touch, le pause, etc)
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public class Director {
	
   RtsGame currentGame;
   MainActivity act;
   UpdateTMXManager update;
   
   /**
    * Constructeur
    * @param activity d'android
    */
   public Director(MainActivity activity)
   {
	 act = activity;
   }
   
   /**
    * Permet d'initialiser le jeu ainsi que le UpdateTMXManager
    * @param map nom de la map
    */
   public void loadRTSTilledGame(String map)
   {
	   currentGame = new RtsGame(map, this);
	   update = new UpdateTMXManager(act, currentGame.getMap());
   }
   
   /**
    * Permet d'intialiser le jeu
    * @param map nom de la map
 	*/
   public void loadTilledGame(String map)
   {
	   currentGame = new RtsGame(map, this);	   
   }
   
   /**
    * Demarre le jeu
 	*/
   public void startGame()
   {
	   currentGame.initGame();
   }
   
   /**
    * Retourne l'activité
    * @return l'activité d'android
 	*/
   public MainActivity getActivity()
   {
	   return act;
   }
   
   /**
    * Retourne le jeu en cours
    * @return le jeu
 	*/
   public RtsGame getGame()
   {
	   return currentGame;
   }
   
   /**
    * Permet de reprendre le jeu lorsqu'il a été mis en pause
 	*/
   public void ContinueGame()
   {
	   currentGame.unPause();
   }
   
   /**
    * Permet de faire des actions lorsque la scene est touché
    * @param touch evenement capturé sur la scene
 	*/
   public void UpdateOnTouchCurrentGame(TouchEvent touch)
   {
	  Log.i("Bob", "TouchScreen"); 
	  
	   if(touch.isActionDown()){
			if(act.mZoomCamera.getHUD() != act.hudToujours && act.mZoomCamera.getHUD() != act.hudCreation)
			{
				act.mZoomCamera.setHUD(act.hudToujours);	
			}
			if(act.mZoomCamera.getHUD() == act.hudCreation)
			{
				currentGame.getScene().getCreerBarricade().placerObjet(touch);
			}
	   }
	   if(update.containPropertyAt(touch.getX(), touch.getY(), "block", "true"))
		  Log.i("Bob", "block");
	  
	  if(currentGame.getSpriteManager().isSomeoneSelected() && touch.isActionUp())
	  {
		  AStar pathfinding = new AStar(currentGame.getMap(), currentGame.getProperty());
		  pathfinding.getPath(currentGame.getMap().getBaseLayer().getTMXTileAt(currentGame.getSpriteManager().selectedSprite().getX(), currentGame.getSpriteManager().selectedSprite().getY()),
				              currentGame.getMap().getBaseLayer().getTMXTileAt(touch.getX(), touch.getY()), touch);
	  }
   }
}
