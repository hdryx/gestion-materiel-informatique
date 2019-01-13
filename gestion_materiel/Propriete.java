/**************************************************************************
* Source File	:  Propriete.java
* Author                   :  abcdef  
* Project name         :  Espace de travail* Created                 :  28/03/2007
* Modified   	:  28/03/2007
* Description	:  Definition of the class Propriete
**************************************************************************/


package gestion_materiel;
import java.sql.ResultSet;
import java.sql.SQLException;




public  class Propriete  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private 
	 int id_propriete;
		private 
	 String propriete;
		
	
	//Attributes Association
	
	//	posseder posseder[];
	 
	
	//Operations
	
	/** Guetteurs et setteurs */
	public int getId_propriete() {
		return id_propriete;
	}
	public void setId_propriete(int id_propriete) {
		this.id_propriete = id_propriete;
	}
	public String getPropriete() {
		return propriete;
	}
	public void setPropriete(String propriete) {
		this.propriete = propriete;
	}
	
	
	/** Constructeur */
	public Propriete(int id_propriete, String propriete) {
		this.id_propriete = id_propriete;
		this.propriete = propriete;
	}
	
	public Propriete()
	{
		
	}

	/**
	 * insererPropriete
	 * 
	 * fonction qui permet d'ajouter une propriete a la base
	 * 
	 * @return 1:si insertion reussie 0:si echec d'insertion
	 */
	
	public int insererPropriete()
	{
		
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "INSERT INTO propriete (propriete) VALUES('"+this.propriete+"')";
        int res = gbd.execUpdate(requete);
        gbd.deconnecter();
        gbd.connecter();
        String requete2 = "SELECT id_propriete FROM propriete WHERE propriete='"+this.propriete+"'";
        ResultSet r = gbd.execQuery(requete2);
        try {
			r.next();
			this.id_propriete = r.getInt("id_propriete");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        return res;
	
	}
	
	/**
	 * modifierPropriete
	 * 
	 * fonction qui permet de modifier une Propriete a la base
	 * 
	 * @return 1:si modification reussie 0:si echec de modification
	 */	
	
	public int modifierPropriete()
	{
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "UPDATE propriete SET propriete='"+this.propriete+"' WHERE id_propriete=" +
        		""+this.id_propriete;
        int res = gbd.execUpdate(requete);
        gbd.deconnecter();
        return res;
	}
    
	/**
	 * supprimerPropriete
	 * 
	 * fonction qui permet de supprimer une Propriete de la base
	 * 
	 * @return 1:si suppression reussie 0:si echec de suppression
	 */
    
	public int supprimerPropriete()
	{
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "DELETE FROM propriete WHERE id_propriete="+this.id_propriete;
        int res = gbd.execUpdate(requete);
        gbd.deconnecter();
        return res;
	}

	
	/**
	 * construirePropriete
	 * 
	 * fonction qui permet de construire une Propriete a partir de son id
	 * 
	 * @param int	id_propriete	l'id de la propriete
	 * @return 1:si id existe 0:si id inexistant
	 */
	public int construirePropriete(int id_propriete)
	{
		
		this.id_propriete = id_propriete;
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "SELECT propriete FROM propriete WHERE id_propriete="+id_propriete;
        
        ResultSet r = gbd.execQuery(requete);
        int nombre = gbd.count();
        if (nombre !=0)
        {
            try {
                r.next();
                this.setPropriete(r.getString("propriete"));
                gbd.deconnecter();    
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        
        }
        
        return nombre;
        
				
	}
	
	
	/**
	 * construirePropriete
	 * 
	 * fonction qui permet de construire une Propriete a partir de son id
	 * 
	 * @param int	id_propriete	l'id de la propriete
	 * @return 1:si id existe 0:si id inexistant
	 */
	public int construirePropriete(String propriete)
	{
		
		this.propriete = propriete;
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "SELECT id_propriete FROM propriete WHERE propriete='"+propriete+"'";
        
        ResultSet r = gbd.execQuery(requete);
        int nombre = gbd.count();
        if (nombre !=0)
        {
            try {
                r.next();
                this.setId_propriete(r.getInt("id_propriete"));
                gbd.deconnecter();    
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        
        }
        
        return nombre;
        
				
	}
	
} //End Class Propriete


