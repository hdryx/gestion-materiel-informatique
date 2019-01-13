package gestion_materiel;

/**************************************************************************
* Source File	:  Installe_dans.java
* Author                   :  abcdef  
* Project name         :  Non enregistré* Created                 :  11/04/2007
* Modified   	:  11/04/2007
* Description	:  Definition of the class Installe_dans
**************************************************************************/



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;



public  class Installe_dans  
{ 
	//Inners Classifiers
	

	//Attributes
	/*
	 c moi qui a ajouter sa :
	  private 
	 java.lang.Integer id_pc;
	private 
	 java.lang.Integer id_logiciel;	
	private 
	 java.lang.Integer id_os;	
	
	*/
	//Attributes Association
	
		Pc Installe_dans_Pc;
	 
		Logiciel Logiciel_installer;
	 
		OS Sous_OS;
		
/**
* Getters & Setters
* @return
*/

		public Pc getInstalle_dans_Pc() {
			return Installe_dans_Pc;
		}

		public void setInstalle_dans_Pc(Pc installe_dans_Pc) {
			Installe_dans_Pc = installe_dans_Pc;
		}

		public Logiciel getLogiciel_installer() {
			return Logiciel_installer;
		}

		public void setLogiciel_installer(Logiciel logiciel_installer) {
			Logiciel_installer = logiciel_installer;
		}

		public OS getSous_OS() {
			return Sous_OS;
		}

		public void setSous_OS(OS sous_OS) {
			Sous_OS = sous_OS;
		}
/**
 * Constructors
 * 
 * @param installe_dans_Pc
 * @param logiciel_installer
 * @param sous_OS
 */
		public Installe_dans(Pc installe_dans_Pc, Logiciel logiciel_installer, OS sous_OS) {
			super();
			Installe_dans_Pc = installe_dans_Pc;
			Logiciel_installer = logiciel_installer;
			Sous_OS = sous_OS;
		}

		public Installe_dans() {
			super();
		}
	 
	
	
	
	

	
	//Operations
		
				
		 private 
		 java.lang.Integer id_pc;
		private 
		 java.lang.Integer id_logiciel;	
		private 
		 java.lang.Integer id_os;	
		
		/**
         *   
	     * public int construireInstalle_dans(int  id_pc,int id_logiciel,int id_os)
	     *    a verifer si sa marche 
	     * @param id
         * @return nombre : int : objet trouvé ou pas 
         */
		public int construireInstalle_dans(int  id_pc,int id_logiciel,int id_os)
        {int r=0;
           r+= this.Installe_dans_Pc.construirePc(id_pc);
           r+= this.Logiciel_installer.construireLogiciel(id_logiciel);
           r+= this.Sous_OS.construireOS(id_os);
            
            if(r==3)return (1);
            else
            	return (0);
         }	
		
		
		   /**
         *  insererInstalle_dans()
         *  Methode qui permet d'insrer les trois clé primaire  dans la table Installe_dans de la base
         *
         * @return res : int : Inserer ou non
         **/
        
        
        public int insererInstalle_dans()
        {
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "INSERT INTO installe_dans (id_pc,id_logiciel,id_os) VALUES('"+this.Installe_dans_Pc.getId_pc()+","+this.Logiciel_installer.getId_logiciel()+","+this.Sous_OS.getId_os()+")";
            int res = gbd.execUpdate(requete);
            gbd.deconnecter();
            return res;
      
        }
//////////////////////////////////////////////// jusqu a ici 
   	 /**
	 	 * modifierInstalle_dans
	 	 * 
	 	 * fonction qui permet de modifier un Installe_dans a la base
	 	 * 
	 	 * @return 1:si modification reussie 0:si echec de modification
	 	 */	
	 	// genere des exception
	 	public int modifierInstalle_dans()
	 	{
	 		Gbd gbd = new Gbd();
	         gbd.connecter();
	         String requete = "UPDATE  logiciel SET nom='"+this.Logiciel_installer.getNom()+"'" ;
	         int res = gbd.execUpdate(requete);
	         gbd.deconnecter();
	         return res;
	 	}
	     
	 	/**
	 	 * supprimerLogiciel
	 	 * 
	 	 * fonction qui permet de supprimer une Logiciel de la base
	 	 * 
	 	 * @return 1:si suppression reussie 0:si echec de suppression
	 	 */
	     // marche bien
	 	public int supprimerLogiciel()
	 	{
	 		Gbd gbd = new Gbd();
	         gbd.connecter();
	         String requete = "DELETE FROM logiciel WHERE id_logiciel ="+this.id_logiciel;
	         int res = gbd.execUpdate(requete);
	         gbd.deconnecter();
	         return res;
	 	}
	     

	

} //End Class Installe_dans


