/**************************************************************************
* Source File	:  Intervention.java
* Author                   :  abcdef  
* Project name         :  Espace de travail* Created                 :  28/03/2007
* Modified   	:  28/03/2007
* Description	:  Definition of the class Intervention
**************************************************************************/


package gestion_materiel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;



public  class Intervention  
{ 
	//Inners Classifiers
	

	//Attributes
		
		// public Date a ;
	
		private 
	 java.lang.Integer id_intervention;
		private ///// ici il ya un problem pour la date !!! type Character ???
	// java.lang.Character 
		Date date_intervention;
		private 
	 java.lang.String resultat_intervention;
		private
		Time heure_intervention ;
	//Attributes Association
		
		Type_intervention type_intervention;
		 
		
		Pc pc;
	 
		
		Technicien technicien;

		
//		Operations
        
        
		 /**
		  * Getters & Setters
		  * @return
		  */       
		        
		        
			
			public Date getDate_intervention() {
				return date_intervention;
			}

			public void setDate_intervention(Date date_intervention) {
				this.date_intervention = date_intervention;
			}

			public java.lang.Integer getId_intervention() {
				return id_intervention;
			}

			public void setId_intervention(java.lang.Integer id_intervention) {
				this.id_intervention = id_intervention;
			}

			public String getResultat_intervention() {
				return resultat_intervention;
			}

			public void setResultat_intervention(String resultat_intervention) {
				this.resultat_intervention = resultat_intervention;
			}

			public Technicien getTechnicien() {
				return this.technicien;
			}

			public void setTechnicien(Technicien technicien) {
				this.technicien = technicien;
			}

			public Pc getPc() {
				return pc;
			}

			public void setPc(Pc a) {
				pc = a;
			}

			public Type_intervention getType_intervention() {
				return type_intervention;
			}

			public void setType_intervention(Type_intervention type_intervention) {
				this.type_intervention = type_intervention;
			}

		

		
		public Time getHeure_intervention() {
				return heure_intervention;
			}

			public void setHeure_intervention(Time heure_intervention) {
				this.heure_intervention = heure_intervention;
			}

		/**
		 * Constructors
		 * 
		 */
   
        
		public Intervention(int id,Date date,String R_intervenition, Type_intervention t)
	        {
	            this.id_intervention = id;
	            this.date_intervention = date;
	            this.type_intervention= t;
	            this.resultat_intervention= R_intervenition;    
	        }
		
	        public Intervention()
	        {
	            this.type_intervention = new Type_intervention();
	        }
	        
	        
	      /**
	       * public int construireIntervention(int idIntervention)
	       *   
	       * 
	       * @param idIntervention
	       * @return
	       */  
	        public int construireIntervention(int idIntervention)
	        {
	            this.id_intervention = idIntervention;
	            Gbd gbd = new Gbd();
	            gbd.connecter();
	            String requete = "SELECT * FROM Intervention WHERE id_intervention="+idIntervention;
	            ResultSet r = gbd.execQuery(requete);
	            int nombre = gbd.count();
	            if (nombre!=0)
	            {
	                try {
	                    r.next();
	    	            this.date_intervention= r.getDate("date_intervention");
	                    this.type_intervention = new Type_intervention();
	                    this.type_intervention.construireType_intervention(r.getInt("id_type_intervention"));
	                    this.technicien = new Technicien();
	                    this.technicien.construireTechnicien(r.getInt("id_type_intervention"));
	                    this.setResultat_intervention(r.getString("resultat_intervention"));
	                    this.heure_intervention =r.getTime("heure_intervention");
	                    this.pc = new Pc();
	                    this.pc.construirePc(r.getInt("id_pc"));
	                    gbd.deconnecter();
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                    }
	                }
	            
	            return nombre;   
	  }
	        
	        
	        
/**
 * public int insererIntervention
 * 
 * @return
 */	        
	        
		public int insererIntervention()
	        {
	            Gbd gbd = new Gbd();
	            gbd.connecter();
	            String requete = "INSERT INTO Intervention (id_type_intervention,id_pc,date_intervention,resultat_intervention,heure_intervention,id_technicien) VALUES ("+this.getType_intervention().getId_type_intervention()+","+this.getPc().getId_pc()+",'"+this.getDate_intervention()+"','"+this.getResultat_intervention()+"','"+this.getHeure_intervention()+"',"+this.technicien.getId_technicien()+")";
	            System.out.println(requete);
	            int res = gbd.execUpdate(requete);
	            gbd.deconnecter();
	            return res;
	         
	            
	        }
	        
	        

		 /**
	 	 * modifierIntervention
	 	 * 
	 	 * fonction qui permet de modifier une Intervention a la base
	 	 * 
	 	 * @return 1:si modification reussie 0:si echec de modification
	 	 */	
	 	// genere des exception
	 	public int modifierIntervention()
	 	{
	 		Gbd gbd = new Gbd();
	         gbd.connecter();
	         String requete = "UPDATE  intervention SET date_intervention='" +
	         		""+this.date_intervention+"' ,heure_intervention='"+this.heure_intervention+"" +
	         				"', resultat_intervention='null', id_type_intervention=" +this.type_intervention.getId_type_intervention()+
	         				", id_technicien="+this.technicien.getId_technicien()+
	         				", id_pc="+this.pc.getId_pc()+
	         				" WHERE id_intervention=" +
	         				""+this.id_intervention ;
	         System.out.println(requete);
	         int res = gbd.execUpdate(requete);
	         gbd.deconnecter();
	         return res;
	 	}
	     
	 	/**
	 	 * supprimerIntervention
	 	 * 
	 	 * fonction qui permet de supprimer une Intervention de la base
	 	 * 
	 	 * @return 1:si suppression reussie 0:si echec de suppression
	 	 */
	     // marche bien
	 	public int supprimerIntervention()
	 	{
	 		Gbd gbd = new Gbd();
	         gbd.connecter();
	         String requete = "DELETE FROM intervention WHERE id_intervention ="+this.id_intervention;
	         int res = gbd.execUpdate(requete);
	         gbd.deconnecter();
	         return res;
	 	}
	     
	     
		
	
	 	 public String toString()
	     {
	     	String s="";
	     	s+="id_intervention="+this.id_intervention+"/n";
	     	s+=" date_intervention ="+this.date_intervention+"/n";
	     	s+="  heure_intervention ="+this.heure_intervention+"/n";
	     	s+="   resultat_intervention ="+this.resultat_intervention+"/n";
	     	s+="    nom type intervention ="+this.type_intervention.getNom_intervention()+"/n";
	     	s+="	 marque pc ="+this.pc.getMarque();	
	     	return s;
	     	
	     }
	 		
		
	
	

} //End Class Intervention


