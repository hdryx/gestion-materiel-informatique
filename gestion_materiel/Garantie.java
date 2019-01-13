/**************************************************************************
* Source File	:  Garantie.java
* Author                   :  abcdef  
* Project name         :  Espace de travail* Created                 :  28/03/2007
* Modified   	:  28/03/2007
* Description	:  Definition of the class Garantie
**************************************************************************/


package gestion_materiel;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;



public  class Garantie  
{ 
	//Inners Classifiers
	

	//Attributes
		

	
		private 
	 java.lang.Integer id_garantie;
		
		// il manque une reference au garantie par exp le nom de la societe ?? et une reference ...
		private 
		Date date_debut;
		private 
	 java.lang.String duree;
	
	//Attributes Association
	
		Pc Rel_1[];
	 
		Peripherique Rel_2[];
/**
 * Constructors :
 * @param idG
 * @param Date_debut
 * @param Duree
 */
		
		
		public Garantie(Integer idG, Date Date_debut, String Duree) {
			//super();
			this.id_garantie = idG;
			this.date_debut = Date_debut;
			this.duree = Duree;
		}
		
		public Garantie()
		{
			
			
		}
/**
 * Getters & Setters
 * 
 */		
		public Date getDate_debut() {
			return date_debut;
		}

		public void setDate_debut(Date date_debut) {
			this.date_debut = date_debut;
		}

		public java.lang.String getDuree() {
			return duree;
		}

		public void setDuree(java.lang.String duree) {
			this.duree = duree;
		}

		public java.lang.Integer getId_garantie() {
			return id_garantie;
		}

		public void setId_garantie(java.lang.Integer id_garantie) {
			this.id_garantie = id_garantie;
		}

		public Pc[] getRel_1() {
			return Rel_1;
		}

		public void setRel_1(Pc[] rel_1) {
			Rel_1 = rel_1;
		}

		public Peripherique[] getRel_2() {
			return Rel_2;
		}

		public void setRel_2(Peripherique[] rel_2) {
			Rel_2 = rel_2;
		}

//		Operations
		
	
		 /**
		  *   public int construireGarantie(int idG)
		  *   methode qui permet de restituer un objet a partir de la  base de donné on donnant l id du Garantie
		  * 
		  */
	        public int construireGarantie(int idG)
	        {
	            this.id_garantie = idG;
	            Gbd gbd = new Gbd();
	            gbd.connecter();
	            String requete = "SELECT * FROM garantie WHERE id_garantie="+idG;
	            ResultSet r = gbd.execQuery(requete);
	            int nombre = gbd.count();
	            if (nombre!=0)
	            {
	                try {
	                    r.next();
	     // a verifier si sa marche getDate(string) ...
	                    this.date_debut= r.getDate("date_debut");
	                    this.duree= r.getString("duree");
	                    gbd.deconnecter();
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                    }
	                }
	            
	            return nombre;   
	        }
	        
	        
	        
	        /**
	         * Methode qui permet d'insrer un Garantie dans la base
	         * 
	         * @return res : int : Inserer ou non
	         */
		public int insererGarantie()
        {
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "INSERT INTO garantie (date_debut,duree) VALUES('"+this.getDate_debut()+"','"+this.getDuree()+"')";
            int res = gbd.execUpdate(requete);
            gbd.deconnecter();
            return res;
      
      
        }
		/**
		 * modifierGarantie
		 * 
		 * fonction qui permet de modifier un modifierGarantie a la base
		 * 
		 * @return 1:si modification reussie 0:si echec de modification
		 */	
		
		public int modifierGarantie()
		{
			Gbd gbd = new Gbd();
	        gbd.connecter();
	        String requete = "UPDATE garantie SET date_debut='"+this.date_debut+"' , duree='"+this.duree+"' WHERE id_garantie=" +
	        		""+this.id_garantie;
	        int res = gbd.execUpdate(requete);
	        gbd.deconnecter();
	        return res;
		}
	

		
		/**
		 * supprimerGarantie
		 * 
		 * fonction qui permet de supprimer un supprimerGarantie de la base
		 * 
		 * @return 1:si suppression reussie 0:si echec de suppression
		 */
	    
		public int supprimerGarantie()
		{
			Gbd gbd = new Gbd();
	        gbd.connecter();
	        String requete = "DELETE FROM garantie WHERE id_garantie="+this.id_garantie;
	        int res = gbd.execUpdate(requete);
	        gbd.deconnecter();
	        return res;
//	   
		}

	
} //End Class Garantie


