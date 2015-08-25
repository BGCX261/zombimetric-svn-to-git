package projet.daywalker;

/**
 * Permet de gérer la table map dans la BD map.db
 * @author Jonathan Guertin et Thibault Podevin
 *
 */
public interface Perso{
	
	/**
	 * Retourne l'attaque du personnage
	 * @return l'attaque du personnage
	 */
	public double Attack();
	
	/**
	 * Rajoute de la vie au personnage
	 * @param health vie a rajouter
	 */
	public void UpgrageHealth(int health);
	
	/**
	 * Rajoute des points d'attaque au personnage
	 * @param damage point d'attaque a rajouter
	 */
	public void UpgrageDamage(int damage);
	
	/**
	 * Rajoute de la vitesse au personnage
	 * @param speed vitesse a rajouter
	 */
	public void UpgradeSpeed(int speed);
	
	/**
	 * Retourne la hauteur du personnage
	 * @return la hauteur du personnage
	 */
	public int getHauteur();
	
	/**
	 * Permet de changer la hauteur du personnage
	 * @param hauteur du personnage a changer
	 */
	public void setHauteur(int hauteur);
	
	/**
	 * Retourne la largeur du personnage
	 * @return la largeur du personnage
	 */
	public int getLargeur();
	
	/**
	 * Permet de changer la largeur du personnage
	 * @param largeur du personnage a changer
	 */
	public void setLargeur(int largeur);
	
	/**
	 * Retourne la vie du personnage
	 * @return la vie du personnage
	 */
	public int getHp();
	
	/**
	 * Permet de changer la vie du perso
	 * @param hp la vie du perso que le perso aura
	 */
	public void setHp(int hp);
	
	/**
	 * Retourne l'attaque du personnage
	 * @return l'attaque du personnage
	 */
	public int getDmg();
	
	/**
	 * Permet de changer l'attaque d'un personnage
	 * @param dmg l'attaque que le personnage aura
	 */
	public void setDmg(int dmg);
	
	/**
	 * Retourne l'attaque par seconde du personnage
	 * @return l'attaque par seconde du personnage
	 */
	public double getAps();
	
	/**
	 * Change l'attaque par seconde du personnage
	 * @param aps l'attaque par seconde du personnage
	 */
	public void setAps(int aps);
}
