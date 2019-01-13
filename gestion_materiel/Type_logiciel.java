/**************************************************************************
* Source File	:  Type_logiciel.java
* Author                   :  abcdef  
* Project name         :  Espace de travail* Created                 :  28/03/2007
* Modified   	:  28/03/2007
* Description	:  Definition of the class Type_logiciel
**************************************************************************/


package gestion_materiel;
import java.sql.ResultSet;
import java.sql.SQLException;



public  class Type_logiciel  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private 
	 java.lang.Integer id_type_logiciel;
		private 
	 java.lang.String type;
	
	//Attributes Association
	
		Logiciel avoir_type[];

		
		
		public Type_logiciel(Integer id_type_logiciel, String type, Logiciel[] avoir_type) {
			super();
			this.id_type_logiciel = id_type_logiciel;
			this.type = type;
			this.avoir_type = avoir_type;
		}
		
		public Type_logiciel()
		{
			
		}

		public Logiciel[] getAvoir_type() {
			return avoir_type;
		}

		public void setAvoir_type(Logiciel[] avoir_type) {
			this.avoir_type = avoir_type;
		}

		public java.lang.Integer getId_type_logiciel() {
			return id_type_logiciel;
		}

		public void setId_type_logiciel(java.lang.Integer id_type_logiciel) {
			this.id_type_logiciel = id_type_logiciel;
		}

		public java.lang.String getType() {
			return type;
		}

		public void setType(java.lang.String type) {
			this.type = type;
		}
	 
	
	
	
	

	
	//Operations
		 /**
         *   
	     * public int construireType_logiciel(int  id)
	     *    a verifer si sa marche 
	     * @param id
         * @return nombre : int : objet trouvé ou pas 
         */
              
		  public int construireType_logiciel(int id)
	        {
	            this.setId_type_logiciel(id);
	            Gbd gbd = new Gbd();
	            gbd.connecter();
	            String requete = "SELECT * FROM type_logiciel WHERE id_type_logiciel="+id+"";
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
	
		  public int construireType_logiciel(String type)
	        { int id=0;
	        	Gbd gbd = new Gbd();
	        	gbd.connecter();
	        	String req="SELECT id_type_logiciel FROM type_logiciel WHERE TYPE  LIKE '"+type+"'";
			  
	        	ResultSet r1= gbd.execQuery(req);
	        	try {
					r1.next();
					id= r1.getInt("id_type_logiciel");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  if (id==0)
				  System.out.println("Ce type n existe pas ");
			  
			  
	            this.setId_type_logiciel(id);
	           
	            String requete = "SELECT * FROM type_logiciel WHERE id_type_logiciel="+id+"";
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
	         *  insererType_logiciel()
	         *  Methode qui permet d'insrer un Type_logiciel dans la base
	         *
	         * @return res : int : Inserer ou non
	         **/
	        
	        
	        public int insererType_logiciel()
	        {
	            Gbd gbd = new Gbd();
	            gbd.connecter();
	            String requete = "INSERT INTO type_logiciel (type) VALUES('"+this.getType()+"')";
	            int res = gbd.execUpdate(requete);
	            gbd.deconnecter();
	            return res;
	      
	        }
	
	      	 /**
		 	 * modifierType_logiciel
		 	 * 
		 	 * fonction qui permet de modifier un Type_logiciel a la base
		 	 * 
		 	 * @return 1:si modification reussie 0:si echec de modification
		 	 */	
		 	// genere des exception
		 	public int modifierType_logiciel()
		 	{
		 		Gbd gbd = new Gbd();
		         gbd.connecter();
		         String requete = "UPDATE  type_logiciel SET type='"+this.type+"'" ;
		         int res = gbd.execUpdate(requete);
		         gbd.deconnecter();
		         return res;
		 	}
		     
		 	/**
		 	 * supprimerType_logiciel
		 	 * 
		 	 * fonction qui permet de supprimer un Type_logiciel de la base
		 	 * 
		 	 * @return 1:si suppression reussie 0:si echec de suppression
		 	 */
		     // marche bien
		 	public int supprimerType_logiciel()
		 	{
		 		Gbd gbd = new Gbd();
		         gbd.connecter();
		         String requete = "DELETE FROM type_logiciel WHERE id_type_logiciel ="+this.id_type_logiciel;
		         int res = gbd.execUpdate(requete);
		         gbd.deconnecter();
		         return res;
		 	}
		     
} //End Class Type_logiciel


