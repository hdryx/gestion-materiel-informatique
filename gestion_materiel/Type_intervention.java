/**************************************************************************
* Source File	:  Type_intervention.java
* Author                   :  abcdef  
* Project name         :  Espace de travail* Created                 :  28/03/2007
* Modified   	:  28/03/2007
* Description	:  Definition of the class Type_intervention
**************************************************************************/


package gestion_materiel;
import java.sql.ResultSet;
import java.sql.SQLException;


public  class Type_intervention  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private 
	 java.lang.Integer id_type_intervention;
		private 
	 java.lang.String nom_intervention;
	
	//Attributes Association
	
		Intervention Rel_5[];

		
		
//		Operations
		
/**
 * Getters & Setters 
 */
		
		public java.lang.Integer getId_type_intervention() {
			return id_type_intervention;
		}

		public void setId_type_intervention(java.lang.Integer id_type_intervention) {
			this.id_type_intervention = id_type_intervention;
		}

		public java.lang.String getNom_intervention() {
			return nom_intervention;
		}

		public void setNom_intervention(java.lang.String nom_intervention) {
			this.nom_intervention = nom_intervention;
		}

		public Intervention[] getRel_5() {
			return Rel_5;
		}

		public void setRel_5(Intervention[] rel_5) {
			Rel_5 = rel_5;
		}
	 
	
	
	
		
		
		/**
		 * 
		 * Constructors
		 * 
		 */
		 
		public Type_intervention(int id, String nom)
	        {
	            this.setId_type_intervention(id);
	            this.setNom_intervention(nom);
	        }
		 
	       public Type_intervention() 
	        {
				// TODO Auto-generated constructor stub
			}
	      
	       
	       /**
	       *  public int construireType_intervention(int id)
	       *  
	       *  methode qui permet de restituer un objet a partir de la  base de donné on donnant l id de lintervention  
	       * 
	       * @param id
	       * @return nombre : int : objet trouvé ou pas 
	       */        
	       
	       
	       public int construireType_intervention(int id)
	        {
	            this.setId_type_intervention(id);
	            Gbd gbd = new Gbd();
	            gbd.connecter();
	            String requete = "SELECT * FROM Type_intervention WHERE id_type_intervention="+id+"";
	            ResultSet r = gbd.execQuery(requete);
	            int nombre = gbd.count();
	            if (nombre !=0)
	            {
	                try {
	                    r.next();
	                    this.setNom_intervention(r.getString("nom_intervention"));
	                    gbd.deconnecter();    
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            
	            }
	            
	            return nombre;
	            
	        }
	       
	        
		
		
	       public int construireType_intervention(String nom)
	        {
	            this.nom_intervention = nom;
	            Gbd gbd = new Gbd();
	            gbd.connecter();
	            String requete = "SELECT * FROM Type_intervention WHERE nom_intervention='"+nom+"'";
	            ResultSet r = gbd.execQuery(requete);
	            int nombre = gbd.count();
	            if (nombre !=0)
	            {
	                try {
	                    r.next();
	                    this.id_type_intervention = r.getInt("id_type_intervention");
	                    gbd.deconnecter();    
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            
	            }
	            
	            return nombre;
	            
	        }
	       
	       
		
		  /**
         *  insererType_intervention()
         *  Methode qui permet d'insrer un type_intervention dans la base
         *
         * @return res : int : Inserer ou non
         **/
		  
		public int insererType_intervention()
	        {
	            Gbd gbd = new Gbd();
	            gbd.connecter();
	            String requete = "INSERT INTO type_intervention (nom_intervention) VALUES('"+this.getNom_intervention()+"')";
	            int res = gbd.execUpdate(requete);
	            gbd.deconnecter();
	            return res;
	      
	        }
		
		

		 /**
	 	 * modifierType_intervention
	 	 * 
	 	 * fonction qui permet de modifier un Type_intervention a la base
	 	 * 
	 	 * @return 1:si modification reussie 0:si echec de modification
	 	 */	
	 	// pas encore tester il faut des parametres 
	 	public int modifierType_intervention()
	 	{
	 		Gbd gbd = new Gbd();
	         gbd.connecter();
	         String requete = "UPDATE  type_intervention SET nom_intervention='"+this.nom_intervention+"' WHERE id_type_intervention="+this.id_type_intervention;
	         int res = gbd.execUpdate(requete);
	         gbd.deconnecter();
	         return res;
	 	}
	     
	 	/**
	 	 * supprimerType_intervention
	 	 * 
	 	 * fonction qui permet de supprimer un Type_intervention de la base
	 	 * 
	 	 * @return 1:si suppression reussie 0:si echec de suppression
	 	 */
	    
	 	/* ne marche pas , genere des exceptionjava.sql.SQLException: [Microsoft][Pilote ODBC Microsoft Access] Type de données incompatible dans l'expression du critère.
		at sun.jdbc.odbc.JdbcOdbc.createSQLException(Unknown Source) */
	 	
	 	public int supprimerType_intervention()
	 	{
	 		Gbd gbd = new Gbd();
	         gbd.connecter();
	         String requete = "DELETE FROM type_intervention WHERE id_type_intervention = "+this.id_type_intervention+"";
	         int res = gbd.execUpdate(requete);
	         gbd.deconnecter();
	         return res;
	 	}
		   
		   
		   
		
	
	

	
	
		
		
	
	

} //End Class Type_intervention


