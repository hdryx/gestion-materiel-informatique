/**************************************************************************
* Source File	:  Lieu.java
* Author                   :  abcdef  
* Project name         :  Espace de travail* Created                 :  28/03/2007
* Modified   	:  28/03/2007
* Description	:  Definition of the class Lieu
**************************************************************************/


package gestion_materiel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;



public  class Lieu  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private 
	 int id_lieu;
		private 
	 java.lang.String nom;
	
	//Attributes Association
	
		private Type_lieu typeLieu;
	 
		private Pc listePc[];
	 
		private Peripherique listePeripherique[];
	 
	
                
                
        //Operations
       
    public int getId_lieu() {
        return id_lieu;
    }

    public void setId_lieu(int id_lieu) {
        this.id_lieu = id_lieu;
    }

    public java.lang.String getNom() {
        return nom;
    }

    public void setNom(java.lang.String nom) {
        this.nom = nom;
    }

    public Type_lieu getTypeLieu() {
        return typeLieu;
    }

    public void setTypeLieu(Type_lieu typeLieu) {
        this.typeLieu = typeLieu;
    }

    public Pc[] getListePc() {
        return listePc;
    }

    public void setListePc(Pc[] listePc) {
        this.listePc = listePc;
    }

    public Peripherique[] getListePeripherique() {
        return listePeripherique;
    }

    public void setListePeripherique(Peripherique[] listePeripherique) {
        this.listePeripherique = listePeripherique;
    }

    
    
    
                
	public Lieu(int id,String nom, Type_lieu t)
        {
            this.id_lieu = id;
            this.nom = nom;
            this.typeLieu = t;
                     
        }
	
        public Lieu()
        {
            this.typeLieu = new Type_lieu();
        }
        
        
        public int construireLieu(int idLieu)
        {
            this.id_lieu = idLieu;
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "SELECT * FROM lieu WHERE id_lieu="+idLieu;
            ResultSet r = gbd.execQuery(requete);
            int nombre = gbd.count();
            if (nombre!=0)
            {
                try {
                    r.next();
                    this.nom = r.getString("nom");
                    this.typeLieu = new Type_lieu();
                    this.typeLieu.construireType_lieu(r.getInt("id_type_lieu"));
                    gbd.deconnecter();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    }
                }
            
            return nombre;   
        }
        
        
	public int insererLieu()
        {
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "INSERT INTO LIEU (id_type_lieu,nom) VALUES ("+this.getTypeLieu().getId_type_lieu()+",'"+this.getNom()+"')";
            int res = gbd.execUpdate(requete);
            gbd.deconnecter();
            return res;
         
            
        }
        
        
        
        public int modifierLieu()
        {
            
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "UPDATE lieu SET  nom='"+this.getNom()+"', id_type_lieu="+this.getTypeLieu().getId_type_lieu()+
                        " WHERE id_lieu="+this.getId_lieu();
            System.out.println(requete);
            int res = gbd.execUpdate(requete);
            
            gbd.deconnecter();
            
            
            return res;
            
        }

    
	
		
} //End Class Lieu


