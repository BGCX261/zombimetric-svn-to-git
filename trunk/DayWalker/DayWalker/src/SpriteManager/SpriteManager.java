package SpriteManager;

//N'EST PAS UTILISER !!!! POURRA SERVIR DANS UN AVENIR PROCHAIN !!!!!!!!!!!!!!!!!!!

import java.util.List;

import org.andengine.entity.sprite.Sprite;

public class SpriteManager {
	
	private List<Sprite> spriteList;
	private List<Integer> spriteId;
	
	public SpriteManager(){
		
	}
	
	public void addSprite(Sprite sp, int id){
		spriteList.add(sp);
		spriteId.add(id);
	}
	
	public void removeLastSprite()
	{
		spriteList.remove(spriteList.size());
		spriteId.remove(spriteList.size());
	}
	
	public void removeSprite(int id)
	{
		spriteList.remove(id);
		spriteId.remove(id);
	}
	
	public Sprite spriteAt(int id)
	{
		return spriteList.get(id);
	}
	
	public int spriteIdAt(int id)
	{
		return spriteId.get(id).intValue();
	}

}
