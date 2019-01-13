package gestion_materiel;

/**************************************************************************
* Source File	:  OS.java
* Author                   :  Kara  
* Project name         :  Non enregistré* Created                 :  11/04/2007
* Modified   	:  11/04/2007
* Description	:  Definition of the class OS
**************************************************************************/



import java.sql.ResultSet;
import java.sql.SQLException;



public  class OS  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private 
	 java.lang.Integer id_os;
		private 
	 String nom_os;
	
	//Attributes Association
	
		Pc marche_sous[];
		 
		Logiciel compatible[];
/**
 * Geters & Setters
 * @return
 */
		public java.lang.Integer getId_os() {
			return id_os;
		}

		public void setId_os(java.lang.Integer id_os) {
			this.id_os = id_os;
		}

	

		public String getNom_os() {
			return nom_os;
		}

		public Logiciel[] getCompatible() {
			return compatible;
		}

		public void setCompatible(Logiciel[] compatible) {
			this.compatible = compatible;
		}

		public Pc[] getMarche_sous() {
			return marche_sous;
		}

		public void setMarche_sous(Pc[] marche_sous) {
			this.marche_sous = marche_sous;
		}

		public void setNom_os(String string) {
			this.nom_os = string;
		}
/**
 * Constructors
 * 
 * @param id_os
 * @param nom_os
 */
		public OS(Integer id_os, String nom_os) {
			super();
			this.id_os = id_os;
			this.nom_os = nom_os;
		}

		public OS() {
			super();
		}
		

		/**
         *   
	     * public int construireOS(int  id)
	     *    a verifer si sa marche 
	     * @param id
         * @return nombre : int : objet trouvé ou pas 
         */
		public int construireOS(int id)
        {
            this.setId_os(id);
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "SELECT * FROM os WHERE id_os="+this.getId_os()+"";
            ResultSet r = gbd.execQuery(requete);
            int nombre = gbd.count();
            if (nombre !=0)
            {
                try {
                    r.next();
                    this.setNom_os(r.getString("nom_os"));
                    gbd.deconnecter();    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            
            }
            
            return nombre;
            
        }	
		public int construireOS(String os)
        {   int id=0;
			
			Gbd gbd = new Gbd();
        	gbd.connecter();
        	String req="SELECT id_os FROM os WHERE nom_os  LIKE '"+os+"'";
		  
        	ResultSet r1= gbd.execQuery(req);
        	try {
				r1.next();
				id= r1.getInt("id_os");
				System.out.println(r1.getInt("id_os"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  if (id==0)
			  System.out.println("Ce os n existe pas ");
		  else
			  System.out.println(id);
            this.setId_os(id);
          
            String requete = "SELECT * FROM os WHERE id_os="+this.getId_os()+"";
            ResultSet r = gbd.execQuery(requete);
            int nombre = gbd.count();
            if (nombre !=0)
            {
                try {
                    r.next();
                    this.setNom_os(r.getString("nom_os"));
                    gbd.deconnecter();    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            
            }
            
            return nombre;
            
        }	
		
		 /**
         *  insererOS()
         *  Methode qui permet d'insrer un OS dans la base
         *
         * @return res : int : Inserer ou non
         **/
        
        
        public int insererOS()
        {
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "INSERT INTO logiciel (nom_os) VALUES('"+this.getNom_os()+"')";
            int res = gbd.execUpdate(requete);
            gbd.deconnecter();
            return res;
      
        }

   	 /**
	 	 * modifierOS
	 	 * 
	 	 * fonction qui permet de modifier un  OS a la base
	 	 * 
	 	 * @return 1:si modification reussie 0:si echec de modification
	 	 */	
	 	
	 	public int modifierOS()
	 	{
	 		Gbd gbd = new Gbd();
	         gbd.connecter();
	         String requete = "UPDATE  os SET nom_os='"+this.nom_os+"' WHERE id_os ="+id_os ;
	         int res = gbd.execUpdate(requete);
	         gbd.deconnecter();
	         return res;
	 	}
	     
	 	/**
	 	 * supprimerOS
	 	 * 
	 	 * fonction qui permet de supprimer un OS de la base
	 	 * 
	 	 * @return 1:si suppression reussie 0:si echec de suppression
	 	 */
	     // marche bien
	 	public int supprimerOS()
	 	{
	 		Gbd gbd = new Gbd();
	         gbd.connecter();
	         String requete = "DELETE FROM os WHERE id_os="+this.id_os;
	         int res = gbd.execUpdate(requete);
	         gbd.deconnecter();
	         return res;
	 	}		
		
		
		
		
	 
	
	
	
	

	
	//Operations
		
		
	
	

} //End Class OS


