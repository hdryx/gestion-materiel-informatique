package gestion_materiel;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Peripherique_propriete {

	
	//Attributs
	
	private
		int id_propriete;
	private
		int id_peripherique;
	private
		String valeur;
	
	
	//Operations
	
	/**Constructeurs */
	public Peripherique_propriete(int id_propriete, int id_peripherique, String valeur) {
		super();
		this.id_propriete = id_propriete;
		this.id_peripherique = id_peripherique;
		this.valeur = valeur;
	}
	
	public Peripherique_propriete()
	{
		
	}
	
	/** Guetteurs et setteurs */
	public int getId_peripherique() {
		return id_peripherique;
	}

	public void setId_peripherique(int id_peripherique) {
		this.id_peripherique = id_peripherique;
	}

	public int getId_propriete() {
		return id_propriete;
	}

	public void setId_propriete(int id_propriete) {
		this.id_propriete = id_propriete;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	
	
	
	/**
	 * construirePeripheriquePropriete
	 * 
	 * fonction qui permet de construire un objet peripherique_propriete a partir de l'id de la propriete et du peripherique
	 * 
	 * @param int	id_peripherique	l'id du peripherique
	 * @param int	id_propriete	l'id de la propriete
	 * @return 1:si id existe 0:si id inexistant
	 */
	
	public int construirePeripheriquePropriete(int id_peripherique,int id_propriete)
	{
		Gbd gbd = new Gbd();
		gbd.connecter();
		String requete = "SELECT valeur FROM peripherique_proptiete WHERE id_peripherique="+
				id_peripherique+" AND id_propriete="+id_propriete;
		ResultSet r = gbd.execQuery(requete);
		int nombre = gbd.count();
		if (nombre !=0)
		
		{
			try {
				r.next();
				this.valeur = r.getString("valeur");
			} catch (SQLException e) {

				e.printStackTrace();
			}
		
		
		}
		return nombre;
	}
	
	
	/**
	 * insererPeripherique_propriete
	 * 
	 * fonction qui permet d'ajouter un Peripherique_propriete a la base
	 * 
	 * @return 1:si insertion reussie 0:si echec d'insertion
	 */
	
	public int insererPeripherique_propriete()
	{
		Gbd gbd = new Gbd();
		gbd.connecter();
		String requete = "INSERT INTO peripherique_propriete VALUES("+id_peripherique+
			","+id_propriete+",'"+valeur+"')";
		int res= gbd.execUpdate(requete);
		return res;	
		
	}
	
	
}
