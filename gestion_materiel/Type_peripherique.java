/*
 * Type_peripherique.java
 *
 * Created on 8 avril 2007, 16:46
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gestion_materiel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author haffouff
 */
public class Type_peripherique {
    
	
	private int id_type_peripherique;
	private String type;
	private Vector lesProprietes;
	
	
	
    /** Constructeurs */
    public Type_peripherique() {
    	this.lesProprietes = new Vector();
    }
    
    public Type_peripherique(int id_type_peripherique,String type,Vector lesProprietes)
    {
    	this.id_type_peripherique = id_type_peripherique;
    	this.type = type;
    	this.lesProprietes = lesProprietes;
    }

    
    /** Guetteurs et setteurs*/
	public int getId_type_peripherique() {
		return id_type_peripherique;
	}

	public void setId_type_peripherique(int id_type_peripherique) {
		this.id_type_peripherique = id_type_peripherique;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
	
	
	public Vector getLesProprietes() {
		return lesProprietes;
	}

	public void setLesProprietes(Vector lesProprietes) {
		this.lesProprietes = lesProprietes;
	}

	/**
	 * insererTypePeripherique
	 * 
	 * fonction qui permet d'ajouter un Type_peripherique a la base
	 * 
	 * @return 1:si insertion reussie 0:si echec d'insertion
	 */
	
	public int insererTypePeripherique()
	{
		
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "INSERT INTO type_peripherique (type) VALUES('"+this.type+"')";
        int res = gbd.execUpdate(requete);
        gbd.deconnecter();
        return res;
	
	}
	
	/**
	 * modifierTypePeripherique
	 * 
	 * fonction qui permet de modifier un Type_peripherique a la base
	 * 
	 * @return 1:si modification reussie 0:si echec de modification
	 */	
	
	public int modifierTypePeripherique()
	{
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "UPDATE type_peripherique SET type='"+this.type+"' WHERE id_type_peripherique=" +
        		""+this.id_type_peripherique;
        int res = gbd.execUpdate(requete);
        gbd.deconnecter();
        return res;
	}
    
	/**
	 * supprimerTypePeripherique
	 * 
	 * fonction qui permet de supprimer un Type_peripherique de la base
	 * 
	 * @return 1:si suppression reussie 0:si echec de suppression
	 */
    
	public int supprimerTypePeripherique()
	{
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "DELETE FROM type_peripherique WHERE id_type_peripherique="+this.id_type_peripherique;
        int res = gbd.execUpdate(requete);
        gbd.deconnecter();
        return res;
	}
	
	/**
	 * construireTypePeripherique
	 * 
	 * fonction qui permet de construire un Type_peripherique a partir de son id
	 * 
	 * @param int	id_type_peripherique	l'id du type du peripherique
	 * @return 1:si id existe 0:si id inexistant
	 */
	public int construireTypePeripherique(int id_type_peripherique)
	{
		
		this.setId_type_peripherique(id_type_peripherique);
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "SELECT type FROM type_peripherique WHERE id_type_peripherique="+id_type_peripherique;
        
        ResultSet r = gbd.execQuery(requete);
        int nombre = gbd.count();
        if (nombre !=0)
        {
            try {
                r.next();
                this.setType(r.getString("type"));
                gbd.deconnecter();    
                
                String requete2 = "SELECT id_propriete FROM posseder WHERE id_type_peripherique="+id_type_peripherique;
                gbd.connecter();
                ResultSet r2 = gbd.execQuery(requete2);
                int nombre2 = gbd.count();
                if (nombre2 !=0)
                {
                	
                	while(r2.next())
                	{
                		Propriete p = new Propriete();
                		p.construirePropriete(r2.getInt("id_propriete"));
                		this.lesProprietes.add(p);
                	}
                }
                gbd.deconnecter();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        
        }
        
        return nombre;
        
				
	}
    
	
	/**
	 * construireTypePeripherique
	 * 
	 * fonction qui permet de construire un Type_peripherique a partir de son id
	 * 
	 * @param int	id_type_peripherique	l'id du type du peripherique
	 * @return 1:si id existe 0:si id inexistant
	 */
	public int construireTypePeripherique(String type)
	{
		
		this.setType(type);
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "SELECT id_type_peripherique FROM type_peripherique WHERE type='"+type+"'";
        
        ResultSet r = gbd.execQuery(requete);
        int nombre = gbd.count();
        if (nombre !=0)
        {
            try {
                r.next();
                this.setId_type_peripherique(r.getInt("id_type_peripherique"));
                gbd.deconnecter();    
                
                String requete2 = "SELECT id_propriete FROM posseder WHERE id_type_peripherique="+id_type_peripherique;
        
                gbd.connecter();
                ResultSet r2 = gbd.execQuery(requete2);
                int nombre2 = gbd.count();
                if (nombre2 !=0)
                {
                	
                	while(r2.next())
                	{
                		Propriete p = new Propriete();
                		p.construirePropriete(r2.getInt("id_propriete"));
                		this.lesProprietes.add(p);
                	}
                }
                gbd.deconnecter();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        
        }
        
        return nombre;
        
				
	}
	
	
	
    /**
     * 
     * ajouterPropriete
     * 
     * fonction qui permet d'ajouter une propriete 
     * 
     * @param	prop	Propriete
     * @return	1:ajout avec succès ou 0
     * 
     * 
     */

	public int ajouterPropriete(Propriete propriete)
	{
		Gbd gbd = new Gbd();
		gbd.connecter();
		String requete = "INSERT INTO posseder VALUES("+propriete.getId_propriete()+","
			+this.id_type_peripherique+")";
		int res=gbd.execUpdate(requete);
		gbd.deconnecter();
		return res;
		
	}
    
    
    /**
     * 
     * supprimerPropriete
     * 
     * fonction qui permet de supprimer une propriete 
     * 
     * @param	prop	Propriete
     * @return	1:suppression avec succès ou 0
     * 
     * 
     */

	public int supprimerPropriete(Propriete propriete)
	{
		Gbd gbd = new Gbd();
		gbd.connecter();
		String requete = "DELETE FROM posseder WHERE id_propriete="+propriete.getId_propriete()+"" +
				" AND id_type_peripherique="+this.id_type_peripherique;
		int res=gbd.execUpdate(requete);
		gbd.deconnecter();
		return res;
		
	}
    
    
    
}
