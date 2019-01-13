/**************************************************************************
* Source File	:  Type_lieu.java
* Author                   :  abcdef  
* Project name         :  Espace de travail* Created                 :  28/03/2007
* Modified   	:  28/03/2007
* Description	:  Definition of the class Type_lieu
**************************************************************************/


package gestion_materiel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;



public  class Type_lieu  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
        private 
	int id_type_lieu;
	private 
	java.lang.String nom_type;
	
	//Attributes Association
	
        private Vector lesLieu;
        
       
		
	//Operations
		
         public Type_lieu(int id, String nom)
        {
            this.setId_type_lieu(id);
            this.setNom_type(nom);
        }
	 
        public Type_lieu()
        {
            lesLieu = new Vector();
        }
        
              
        public int construireType_lieu(int id)
        {
            this.setId_type_lieu(id);
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "SELECT * FROM type_lieu WHERE id_type_lieu="+id+"";
            ResultSet r = gbd.execQuery(requete);
            int nombre = gbd.count();
            if (nombre !=0)
            {
                try {
                    r.next();
                    this.setNom_type(r.getString("nom_type"));
                    gbd.deconnecter();    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            
            }
            
            return nombre;
            
        }
        
        
        
        public int construireType_lieu(String nom)
        {
            this.setNom_type(nom);
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "SELECT * FROM type_lieu WHERE nom_type='"+nom+"'";
            ResultSet r = gbd.execQuery(requete);
            int nombre = gbd.count();
            if (nombre !=0)
            {
                try {
                    r.next();
                    this.setId_type_lieu(r.getInt("id_type_lieu"));
                    gbd.deconnecter();    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            
            }
            
            return nombre;
            
        }
        
        
        /*
        public Type_lieu(String nom)
        {
            this.setNom_type(nom);
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "SELECT * FROM type_lieu WHERE nom_type='"+nom+"'";
            ResultSet r = gbd.execQuery(requete);
            
            try {
                r.next();
                this.setId_type_lieu(r.getInt("id_type_lieu"));
                gbd.deconnecter();    
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
                    
        }
         
        */
        
        
        /**
         *  insererType_lieu()
         *  Methode qui permet d'insrer un type_lieu dans la base
         *
         * @return res : int : Inserer ou non
         **/
        
        
        public int insererType_lieu()
        {
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "INSERT INTO type_lieu (nom_type) VALUES('"+this.getNom_type()+"')";
            int res = gbd.execUpdate(requete);
            gbd.deconnecter();
            return res;
      
        }
        
        
        
        
        public int modifierType_lieu()
        {
            
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "UPDATE type_lieu SET nom_type='"+this.getNom_type()
                +"' WHERE id_type_lieu="+this.getId_type_lieu();
            int res = gbd.execUpdate(requete);
            
            gbd.deconnecter();
            
            return res;
            
        }

    public int getId_type_lieu() {
        return id_type_lieu;
    }

    public void setId_type_lieu(int id) {
        this.id_type_lieu = id;
    }

    public java.lang.String getNom_type() {
        return nom_type;
    }

    public void setNom_type(java.lang.String nom_type) {
        this.nom_type = nom_type;
    }

    

    
	
	

} //End Class Type_lieu


