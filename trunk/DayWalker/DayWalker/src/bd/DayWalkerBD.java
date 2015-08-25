package bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Créer la bd ainsi que la table map
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public class DayWalkerBD extends SQLiteOpenHelper{
	
	/**
	 * @param context le Context d'android
	 * @param name le nom de la BD
	 * @param factory permet de retourner des sous-classes du curseur lors de requete
	 * @param version version de la BD
	 */
	public DayWalkerBD(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//db.execSQL("DROP TABLE map");
		db.execSQL(	"CREATE TABLE map (map_no INTEGER PRIMARY KEY AUTOINCREMENT, "+ 
                 	"map_nom VARCHAR(32), map_chemin VARCHAR(32))");
		Log.i("Info", "Database created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("Info", "Database upgraded");
	}
}
