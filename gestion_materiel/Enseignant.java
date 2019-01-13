package gestion_materiel;




import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;



public  class Enseignant  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private 
	 java.lang.Integer id_enseignant;
		private 
	 java.lang.String cin_enseignant;
		private 
	 java.lang.String nom_enseignant;
		private 
	 java.lang.String prenom_enseignant;
	
	//Attributes Association
	
		Reservation Reserver[];

		public Enseignant(Integer id_enseignant, String cin_enseignant, String nom_enseignant, String prenom_enseignant) {
			super();
			this.id_enseignant = id_enseignant;
			this.cin_enseignant = cin_enseignant;
			this.nom_enseignant = nom_enseignant;
			this.prenom_enseignant = prenom_enseignant;
			
		}

		public Enseignant() {
			super();
		}

		public java.lang.String getCin_enseignant() {
			return cin_enseignant;
		}

		public void setCin_enseignant(java.lang.String cin_enseignant) {
			this.cin_enseignant = cin_enseignant;
		}

		public java.lang.Integer getId_enseignant() {
			return id_enseignant;
		}

		public void setId_enseignant(java.lang.Integer id_enseignant) {
			this.id_enseignant = id_enseignant;
		}

		public java.lang.String getNom_enseignant() {
			return nom_enseignant;
		}

		public void setNom_enseignant(java.lang.String nom_enseignant) {
			this.nom_enseignant = nom_enseignant;
		}

		public java.lang.String getPrenom_enseignant() {
			return prenom_enseignant;
		}

		public void setPrenom_enseignant(java.lang.String prenom_enseignant) {
			this.prenom_enseignant = prenom_enseignant;
		}

		public Reservation[] getReserver() {
			return Reserver;
		}

		public void setReserver(Reservation[] reserver) {
			Reserver = reserver;
		}
	 
		
		
	//Operations

		  /**
	       * 	public int construireEnseignant(int id)
	       *  
	       *  methode qui permet de restituer un objet a partir de la  base de donné on donnant l id du l Enseignant 
	       * 
	       * @param id
	       * @return nombre : int : objet trouvé ou pas */
		
		public int construireEnseignant(int id)
	        {
	            this.setId_enseignant(id);
	            Gbd gbd = new Gbd();
	            gbd.connecter();
	            String requete = "SELECT * FROM enseignant WHERE id_enseignant="+id+"";
	            ResultSet r = gbd.execQuery(requete);
	            int nombre = gbd.count();
	            if (nombre !=0)
	            {
	                try {
	                    r.next();
	                    this.setCin_enseignant(r.getString("cin_enseignant"));
	                    this.setNom_enseignant(r.getString("nom_enseignant"));
	                    this.setPrenom_enseignant(r.getString("prenom_enseignant"));
	                    gbd.deconnecter();    
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            
	            }
	            
	            return nombre;
	            
	        }
	       
	        /**
	         * insererEnseignant
	         * Methode qui permet d'insrer un Enseignant dans la base
	         * 
	         * @return res : int : Inserer ou non
	         */
		public int insererEnseignant()
      {
          Gbd gbd = new Gbd();
          gbd.connecter();
          String requete = "INSERT INTO enseignant (cin_enseignant,nom_enseignant,prenom_enseignant) VALUES('"+this.getCin_enseignant()+"','"+ this.getPrenom_enseignant()+"','"+this.getNom_enseignant()+"')";
          int res = gbd.execUpdate(requete);
          gbd.deconnecter();
          return res;
    
      }
	
		/**
		 * modifierEnseignant
		 * 
		 * fonction qui permet de modifier un Enseignant a la base
		 * 
		 * @return 1:si modification reussie 0:si echec de modification
		 */	
		
		public int modifierEnseignant()
		{
			Gbd gbd = new Gbd();
	        gbd.connecter();
	        String requete = "UPDATE enseignant SET cin_enseignant='"+this.cin_enseignant+"' ,nom_enseignant='"+this.nom_enseignant+"', prenom_enseignant='"+this.prenom_enseignant+"' WHERE id_enseignant="+this.id_enseignant;
	        int res = gbd.execUpdate(requete);
	        gbd.deconnecter();
	        return res;
		}
	

		
		/**
		 * supprimerEnseignant
		 * 
		 * fonction qui permet de supprimer un Enseignant de la base
		 * 
		 * @return 1:si suppression reussie 0:si echec de suppression
		 */
	    
		public int supprimerEnseignant()
		{
			Gbd gbd = new Gbd();
	        gbd.connecter();
	        String requete = "DELETE FROM enseignant WHERE id_enseignant="+this.id_enseignant;
	        int res = gbd.execUpdate(requete);
	        gbd.deconnecter();
	        return res;
		}
		
		
	
		
	
	

} //End Class Enseignant


