/**************************************************************************
* Source File	:  Slot.java
* Author                   :  abcdef  
* Project name         :  Espace de travail* Created                 :  28/03/2007
* Modified   	:  28/03/2007
* Description	:  Definition of the class Slot
**************************************************************************/


package gestion_materiel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;



public  class Slot  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private 
	 int id_slot;
		private 
	 String type;
		
	
	//Attributes Association
	
		
	
	
	/** Guetteurs et setteurs */
		
	public int getId_slot() {
		return id_slot;
	}
	public void setId_slot(int id_slot) {
		this.id_slot = id_slot;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	/** Constructeurs */

	public Slot(int id_slot, String type) {
		super();
		this.id_slot = id_slot;
		this.type = type;
	}
	
	public Slot()
	{
		
	}
	
	
	
	/**
	 * insererSlot
	 * 
	 * fonction qui permet d'ajouter un slot a la base
	 * 
	 * @return 1:si insertion reussie 0:si echec d'insertion
	 */
	
	public int insererSlot()
	{
		
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "INSERT INTO slot (type) VALUES('"+this.type+"')";
        int res = gbd.execUpdate(requete);
        gbd.deconnecter();
        return res;
	
	}
	
	/**
	 * modifierSlot
	 * 
	 * fonction qui permet de modifier un slot a la base
	 * 
	 * @return 1:si modification reussie 0:si echec de modification
	 */	
	
	public int modifierTypePeripherique()
	{
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "UPDATE slot SET type='"+this.type+"' WHERE id_slot=" +
        		""+this.id_slot;
        int res = gbd.execUpdate(requete);
        gbd.deconnecter();
        return res;
	}
	
	
	/**
	 * supprimerSlot
	 * 
	 * fonction qui permet de supprimer un slot de la base
	 * 
	 * @return 1:si suppression reussie 0:si echec de suppression
	 */
    
	public int supprimerSlot()
	{
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "DELETE FROM slot WHERE id_slot="+this.id_slot;
        int res = gbd.execUpdate(requete);
        gbd.deconnecter();
        return res;
	}
	
	
	
	/**
	 * construireSlot
	 * 
	 * fonction qui permet de construire un Slot a partir de son id
	 * 
	 * @param int	id_slot	l'id du slot
	 * @return 1:si id existe 0:si id inexistant
	 */
	public int construireSlot(int id_slot)
	{
		
		this.id_slot = id_slot;
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "SELECT type FROM slot WHERE id_slot="+id_slot;
        
        ResultSet r = gbd.execQuery(requete);
        int nombre = gbd.count();
        if (nombre !=0)
        {
            try {
                r.next();
                this.setType(r.getString("type"));
                gbd.deconnecter();    
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        
        }
        
        return nombre;
	
	}
				
	
	/**
	 * construireSlot
	 * 
	 * fonction qui permet de construire un Slot a partir de son id
	 * 
	 * @param int	id_slot	l'id du slot
	 * @return 1:si id existe 0:si id inexistant
	 */
	public int construireSlot(String type)
	{
		
		this.type = type;
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "SELECT id_slot FROM slot WHERE type='"+type+"'";
        
        ResultSet r = gbd.execQuery(requete);
        int nombre = gbd.count();
        if (nombre !=0)
        {
            try {
                r.next();
                this.setId_slot(r.getInt("id_slot"));
                gbd.deconnecter();    
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        
        }
        
        return nombre;
	
	}
	

} //End Class Slot


