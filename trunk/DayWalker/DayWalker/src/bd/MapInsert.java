package bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Permet de gérer la table map dans la BD map.db
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public class MapInsert {
	private static final String BD = "map.db";
	private static final String TABLE = "map";
	private static final String NO = "map_no";
	private static final String NOM = "map_nom";
	private static final String	CHEMIN = "map_chemin";
	private SQLiteDatabase bdd;
	private DayWalkerBD bd;
	
	/**
	 * Constructeur permettant d'initialiser la BD
	 * @param context Context d'android
	 */
	public MapInsert(Context context){
		bd = new DayWalkerBD(context, BD, null, 1);
	}
	
	/**
	 * Ouvre une connexion à la BD
	 */
	public void open(){
		bdd = bd.getWritableDatabase();
	}
	
	/**
	 * Ferme une connexion à la BD
	 */
	public void close(){
		bdd.close();
	}
	
	/**
	 * Permet de retourner la BD
	 * @return la BD
	 */
	public SQLiteDatabase getBDD(){
		return bdd;
	}
	
	/**
	 * Permet d'ajouter et de supprimer des entrées dans la table table selon ce qui se trouve dans le dossier asset
	 * @param asset objet servant a acceder au dossier asset
	 */
	public void gererMap(AssetExplorer asset)
	{
		String fichier[] = asset.getTmx();
		String baseDonnee[] = getListeMap();
		boolean verification = false;
		
		if(baseDonnee.length == 0)
		{
			for(int i=0;i<fichier.length;i++)
			{
				insert(fichier[i]);
			}
		}
		else if(fichier.length >= baseDonnee.length)
		{
			for(int i=0;i<fichier.length;i++)
			{
				for(int j=0;j<baseDonnee.length;j++)
				{
					if(fichier[i].equals(baseDonnee[j]))
					{
						verification = true;
					}
				}
				if(verification != true)
				{
					insert(fichier[i]);
				}
				verification = false;
			}
		}
		else
		{
			for(int i=0;i<baseDonnee.length;i++)
			{
				for(int j=0;j<fichier.length;j++)
				{
					if(fichier[j].equals(baseDonnee[i]))
					{
						verification = true;
					}
				}
				if(verification != true)
				{
					bdd.delete(TABLE, NOM+"='"+fichier[i]+"'", null);
					verification = false;
				}
			}
			
		}
	}
	
	/**
	 * Insere une ligne dans la table map
	 * @param nom est le nom de la map
	 */
	public void insert(String nom){		
		ContentValues value = new ContentValues();

		value.put(NOM, nom);
		value.put(CHEMIN, "tmx/"+nom);
		
		bdd.insert(TABLE, null, value);
	}

	/**
	 * Permet de retourner un tableau de toutes les map se trouvant dans la bd
	 * @return tableau de nom de map
	 */
	public String[] getListeMap(){

		Cursor cursor = bdd.rawQuery("SELECT map_nom FROM map", null);
		String[] liste = new String[cursor.getCount()];
		if(cursor!=null)
		{
			cursor.moveToNext();
			for(int i=0;i<cursor.getCount();i++){
				liste[i] = cursor.getString(0);
				cursor.moveToNext();
			}
		}
		return liste;		
	}
}
