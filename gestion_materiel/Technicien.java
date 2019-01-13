/**************************************************************************
* Source File	:  Technicien.java
* Author                   :  abcdef  
* Project name         :  Espace de travail* Created                 :  28/03/2007
* Modified   	:  28/03/2007
* Description	:  Definition of the class Technicien
**************************************************************************/


package gestion_materiel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;



public  class Technicien  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private 
	 java.lang.Integer id_technicien;
		private 
	 java.lang.String cin_technicien;
		private 
	 java.lang.String nom_technicien;
		private 
	 java.lang.String prenom_technicien;
	
	//Attributes Association
	
		Intervention Effectuer[];
		
/**
 * Getters & Setters 
 * @return
 */		
		
		public java.lang.String getCin_technicien() {
			return cin_technicien;
		}

		public void setCin_technicien(java.lang.String cin_technicien) {
			this.cin_technicien = cin_technicien;
		}

		public Intervention[] getEffectuer() {
			return Effectuer;
		}

		public void setEffectuer(Intervention[] effectuer) {
			Effectuer = effectuer;
		}

		public java.lang.Integer getId_technicien() {
			return id_technicien;
		}

		public void setId_technicien(java.lang.Integer id_technicien) {
			this.id_technicien = id_technicien;
		}

		public java.lang.String getNom_technicien() {
			return nom_technicien;
		}

		public void setNom_technicien(java.lang.String nom_technicien) {
			this.nom_technicien = nom_technicien;
		}

		
		public java.lang.String getPrenom_technicien() {
			return prenom_technicien;
		}

		public void setPrenom_technicien(java.lang.String prenom_technicien) {
			this.prenom_technicien = prenom_technicien;
		}
	 
		
/**
 * Constructors
 * 
 * @param id_technicien
 * @param cin_technicien
 * @param nom_technicien
 * @param prenom_technicien
 */		
		
		public Technicien(Integer id_technicien, String cin_technicien, String nom_technicien, String prenom_technicien) {
			super();
			this.id_technicien = id_technicien;
			this.cin_technicien = cin_technicien;
			this.nom_technicien = nom_technicien;
			this.prenom_technicien = prenom_technicien;
		}

		
		public Technicien() 
		{
			
		}


		
	
	
	
	

	
	//Operations
	
		  /**
	       * 	public int construireTechnicien(int id)
	       *  
	       *  methode qui permet de restituer un objet a partir de la  base de donné on donnant l id du Technicien
	       * 
	       * @param id
	       * @return nombre : int : objet trouvé ou pas */
		
		public int construireTechnicien(int id)
	        {
	            this.setId_technicien(id);
	            Gbd gbd = new Gbd();
	            gbd.connecter();
	            String requete = "SELECT * FROM technicien WHERE id_technicien="+id+"";
	            ResultSet r = gbd.execQuery(requete);
	            int nombre = gbd.count();
	            if (nombre !=0)
	            {
	                try {
	                    r.next();
	                    this.setCin_technicien(r.getString("cin_technicien"));
	                    this.setNom_technicien(r.getString("nom_technicien"));
	                    this.setPrenom_technicien(r.getString("prenom_technicien"));
	                    gbd.deconnecter();    
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            
	            }
	            
	            return nombre;
	            
	        }
	       
	        /**
	         * Methode qui permet d'insrer un Tchnicien dans la base
	         * 
	         * @return res : int : Inserer ou non
	         */
		public int insererTchnicien()
        {
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "INSERT INTO technicien (cin_technicien,prenom_technicien,nom_technicien) VALUES('"+this.getCin_technicien()+"','"+ this.getPrenom_technicien()+"','"+this.getNom_technicien()+"')";
            int res = gbd.execUpdate(requete);
            gbd.deconnecter();
            return res;
      
        }
		
		
	
		 /**
	 	 * modifierTechnicien
	 	 * 
	 	 * fonction qui permet de modifier un Technicien a la base
	 	 * 
	 	 * @return 1:si modification reussie 0:si echec de modification
	 	 */	
	 	// pas encore tester il faut des parametres 
	 	public int modifierTechnicien()
	 	{
	 		Gbd gbd = new Gbd();
	         gbd.connecter();
	         String requete = "UPDATE  technicien SET cin_technicien='"+this.cin_technicien+"' , nom_technicien ='"+this.nom_technicien +"' ,  prenom_technicien = '"+this.prenom_technicien+"'  WHERE id_technicien="+this.id_technicien;
	         int res = gbd.execUpdate(requete);
	         gbd.deconnecter();
	         return res;
	 	}
	     
	 	/**
	 	 * supprimerTechnicien
	 	 * 
	 	 * fonction qui permet de supprimer un Technicien de la base
	 	 * 
	 	 * @return 1:si suppression reussie 0:si echec de suppression
	 	 */
	    
	 	
	 	public int supprimerTechnicien()
	 	{
	 		Gbd gbd = new Gbd();
	         gbd.connecter();
	         String requete = "DELETE FROM technicien WHERE id_technicien = "+this.id_technicien+"";
	         int res = gbd.execUpdate(requete);
	         gbd.deconnecter();
	         return res;
	 	}	
		
		
	
	

} //End Class Technicien


