package bd;

import java.io.IOException;

import projet.daywalker.MainActivity;
import android.content.res.AssetManager;
import android.util.Log;


/**
 * Permet d'explorer le dossier asset
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public class AssetExplorer {
	
	MainActivity act;
	
	/**
	 * Constructeur de l'objet qui reçoit l'activité android en paramètre
	 * @param activity
	 */
	public AssetExplorer(MainActivity activity)
	{
		act = activity;
	}
	
	/**
	 * Crée un tableau de caractère contenant les nom des fichiers compris dans le dossier tmx et le retourne
	 * @return un tableau contenant les noms des fichiers du dossier tmx
	 */
	public String[] getTmx()
	{
		AssetManager assetManager = act.getAssets();
		
		// To get names of all files inside the "Files" folder
		try {
			return assetManager.list("tmx");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * Crée un tableau de caractère contenant les nom des fichiers compris dans le dossier gfx et le retourne
	 * @return un tableau contenant les noms des fichiers du dossier gfx
	 */
	public String[] getGfx()
	{
		AssetManager assetManager = act.getAssets();
		
		// To get names of all files inside the "Files" folder
		try {
			return assetManager.list("gfx");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * Crée un tableau de caractère contenant les nom des fichiers compris dans le dossier image et le retourne
	 * @return un tableau contenant les noms des fichiers du dossier image
	 */
	public String[] getImage()
	{
		AssetManager assetManager = act.getAssets();
		
		// To get names of all files inside the "Files" folder
		try {
			return assetManager.list("image");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * Crée un tableau de caractère contenant les nom des fichiers compris dans le dossier map et le retourne
	 * @return un tableau contenant les noms des fichiers du dossier map
	 */
	public String[] getMap()
	{
		AssetManager assetManager = act.getAssets();
		
		// To get names of all files inside the "Files" folder
		try {
			return assetManager.list("map");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return null;
		
	}
}
