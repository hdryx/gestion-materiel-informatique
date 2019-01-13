/**************************************************************************
* Source File	:  Logiciel.java
* Author                   :  abcdef  
* Project name         :  Espace de travail* Created                 :  28/03/2007
* Modified   	:  28/03/2007
* Description	:  Definition of the class Logiciel
**************************************************************************/


package gestion_materiel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;



public  class Logiciel  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private 
	 java.lang.Integer id_logiciel;
		private 
	 java.lang.String nom;
		private
		String description ;
		private
		String editeur ;
		private
	java.lang.Integer	m_g_min ;
		private 
	java.lang.Integer	m_ram_min;
		private 
	java.lang.Integer	espace_d_min;
		private 
	java.lang.Integer	note_config;
	
	//Attributes Association
		public
		Type_logiciel typeLogicielle ;
		
		//Type_logiciel avoir_type[];
		public Vector installe_dans_pc ;
		//Pc Installe_dans[];
		public 
		OS compatible;
		
		
		
		/**
		 *  constructors
		 *  
		 *  
		 * @param id_logiciel
		 * @param nom
		 */	 
				public Logiciel(Integer id_logiciel, String nom) {
					super();
					this.id_logiciel = id_logiciel;
					this.nom = nom;
				}
				
				public Logiciel() {
					super();
				}
		
		
/**
 * 
 * Getters & Setters
 * 
 * 
 */		
		/*public Type_logiciel[] getAvoir_type() {
			return avoir_type;
		}
		
		public void setAvoir_type(Type_logiciel[] avoir_type) {
			this.avoir_type = avoir_type;
		}
*/
		public java.lang.Integer getId_logiciel() {
			return id_logiciel;
		}

		public void setId_logiciel(java.lang.Integer id_logiciel) {
			this.id_logiciel = id_logiciel;
		}

		/*public Pc[] getInstalle_dans() {
			return Installe_dans;
		}

		public void setInstalle_dans(Pc[] installe_dans) {
			Installe_dans = installe_dans;
		}
*/
		public java.lang.String getNom() {
			return nom;
		}

		public void setNom(java.lang.String nom) {
			this.nom = nom;
		}
				
//		public Vector getAvoir_type_logiciel() {
//			return avoir_type_logiciel;
//		}
//
//		public void setAvoir_type_logiciel(Vector avoir_type_logiciel) {
//			this.avoir_type_logiciel = avoir_type_logiciel;
//		}

		public OS getCompatible() {
			return compatible;
		}

		public void setCompatible(OS compatible) {
			this.compatible = compatible;
		}

		public Vector getInstalle_dans_pc() {
			return installe_dans_pc;
		}

		public void setInstalle_dans_pc(Vector installe_dans_pc) {
			this.installe_dans_pc = installe_dans_pc;
		}

	
		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Type_logiciel getTypeLogicielle() {
			return typeLogicielle;
		}

		public void setTypeLogicielle(Type_logiciel typeLogicielle) {
			this.typeLogicielle = typeLogicielle;
		}
		
		public String getEditeur() {
			return editeur;
		}

		public void setEditeur(String editeur) {
			this.editeur = editeur;
		}
		
			
		public java.lang.Integer getEspace_d_min() {
			return espace_d_min;
		}

		public void setEspace_d_min(java.lang.Integer espace_d_min) {
			this.espace_d_min = espace_d_min;
		}

		public java.lang.Integer getM_g_min() {
			return m_g_min;
		}

		public void setM_g_min(java.lang.Integer m_g_min) {
			this.m_g_min = m_g_min;
		}

		public java.lang.Integer getM_ram_min() {
			return m_ram_min;
		}

		public void setM_ram_min(java.lang.Integer m_ram_min) {
			this.m_ram_min = m_ram_min;
		}

		public java.lang.Integer getNote_config() {
			return note_config;
		}

		public void setNote_config(java.lang.Integer note_config) {
			this.note_config = note_config;
		}

//		Operations
		
		/******************************  a voir la composition des type et a implementer !!!!

		/**
         *   
	     * public int construireLogiciel(int  id)
	     *    a verifer si sa marche 
	     * @param id
         * @return nombre : int : objet trouvé ou pas 
         */
		public int construireLogiciel(int id)
        {
            this.setId_logiciel(id);
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "SELECT * FROM logiciel WHERE id_logiciel="+id+"";
            ResultSet r = gbd.execQuery(requete);
            int nombre = gbd.count();
            if (nombre !=0)
            {
                try {
                    r.next();
                    this.setNom(r.getString("nom"));
                    this.setM_g_min(r.getInt("m_g_min"));
                    this.setM_ram_min(r.getInt("m_ram_min"));
                    this.setEspace_d_min(r.getInt("espace_d_min"));
                    this.setNote_config(r.getInt("note_config"));
                    this.setDescription(r.getString("description"));                    
                    this.setEditeur(r.getString("editeur"));
                    
                    this.compatible = new OS();
                    this.compatible.construireOS(r.getInt("id_os"));
                                                   
                 
                    this.typeLogicielle = new Type_logiciel();
                    this.typeLogicielle.construireType_logiciel((r.getInt("id_type_logiciel")));
                  
                  gbd.deconnecter();
                
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            
            }
            
            return nombre;
            
        }	
		
		
		public void construireLesPc()
		{
			Gbd gbd = new Gbd();  
			
            String requete3 ="SELECT id_pc FROM installe_dans  WHERE id_logiciel="+this.getId_logiciel()+"";
            gbd.connecter();
            ResultSet r3 = gbd.execQuery(requete3);
            int nombre3 = gbd.count();
            if (nombre3 !=0)
            {
            	
            	try {
					while(r3.next())
					{
						Pc i = new Pc();
						i.construirePc((r3.getInt("id_pc")));
						this.installe_dans_pc.add(i);
					}
					gbd.deconnecter();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            
		}
		
		   /**
         *  insererLogiciel()
         *  Methode qui permet d'insrer un Logiciel dans la base
         *
         * @return res : int : Inserer ou non
         **/
        
        
        public int insererLogiciel()
        {
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "INSERT INTO logiciel (id_os,id_type_logiciel,editeur,nom,description,m_g_min,m_ram_min,espace_d_min,note_config) VALUES("+this.compatible.getId_os()+","+this.typeLogicielle.getId_type_logiciel()+",'"+this.getEditeur()+"','"+this.getNom()+"','"+this.getDescription()+"',"+this.getM_g_min()+","+this.getM_ram_min()+","+this.getEspace_d_min()+","+this.getNote_config()+")";
            int res = gbd.execUpdate(requete);
            gbd.deconnecter();
            return res;
      
        }

   	    /**
	 	 * modifierLogiciel
	 	 * 
	 	 * fonction qui permet de modifier une Logiciel a la base
	 	 * 
	 	 * @return 1:si modification reussie 0:si echec de modification
	 	 */	
	 	
	 	public int modifierLogiciel()
	 	{
	 		Gbd gbd = new Gbd();
	         gbd.connecter();
	         String requete = "UPDATE  logiciel SET nom='"+this.nom+"',id_os="+this.compatible.getId_os()+",id_type_logiciel="+this.getTypeLogicielle().getId_type_logiciel()+",editeur='"+this.getEditeur()+"',description='"+this.getDescription()+"',m_g_min="+this.getM_g_min()+",m_ram_min="+this.getM_ram_min()+",espace_d_min="+this.getEspace_d_min()+",note_config="+this.getNote_config()+" WHERE id_logiciel="+this.id_logiciel ;
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

		

} //End Class Logiciel


