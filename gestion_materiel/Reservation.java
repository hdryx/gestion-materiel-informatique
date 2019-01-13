package gestion_materiel;





import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.*;



public  class Reservation  
{ 
	//Inners Classifiers
	

	//Attributes
		
		private 
	 java.lang.Integer id_reservation;
		private 
	 Date date_reservation;
		private 
	 Date date_retour;
		private
		Time heure_reservation ;
		private 
		Time heure_retour ;
	
	//Attributes Association
	
		//Peripherique est_reserve[];
	Vector peripheriques_reserve ; 
	Enseignant Reserveur;
/**
 * Constructors
 * @param id_reservation
 * @param date_reservation
 * @param date_retour
 * @param est_reserve
 * @param reserver
 */
		public Reservation(Integer id_reservation, Date date_reservation, Date date_retour, Enseignant reserver) {
			super();
			this.id_reservation = id_reservation;
			this.date_reservation = date_reservation;
			this.date_retour = date_retour;
			Reserveur = reserver;
		}

		
		public Reservation(Integer id_reservation, Date date_reservation, Date date_retour, Time heure_reservation, Time heure_retour, Vector peripheriques_reserve, Enseignant reserveur) {
	super();
	this.id_reservation = id_reservation;
	this.date_reservation = date_reservation;
	this.date_retour = date_retour;
	this.heure_reservation = heure_reservation;
	this.heure_retour = heure_retour;
	this.peripheriques_reserve = peripheriques_reserve;
	Reserveur = reserveur;
}


		public Reservation() {
			super();
		}
		
/**
 * Getters & Setters
 * @return
 */
		

		public Date getDate_reservation() {
			return date_reservation;
		}


		public void setDate_reservation(Date date_reservation) {
			this.date_reservation = date_reservation;
		}


		public Date getDate_retour() {
			return date_retour;
		}


		public void setDate_retour(Date date_retour) {
			this.date_retour = date_retour;
		}


		public Time getHeure_reservation() {
			return heure_reservation;
		}


		public void setHeure_reservation(Time heure_reservation) {
			this.heure_reservation = heure_reservation;
		}


		public Time getHeure_retour() {
			return heure_retour;
		}


		public void setHeure_retour(Time heure_retour) {
			this.heure_retour = heure_retour;
		}


		public java.lang.Integer getId_reservation() {
			return id_reservation;
		}


		public void setId_reservation(java.lang.Integer id_reservation) {
			this.id_reservation = id_reservation;
		}


		public Vector getPeripheriques_reserve() {
			return peripheriques_reserve;
		}


		public void setPeripheriques_reserve(Vector peripheriques_reserve) {
			this.peripheriques_reserve = peripheriques_reserve;
		}


		public Enseignant getReserveur() {
			return Reserveur;
		}


		public void setReserveur(Enseignant reserveur) {
			Reserveur = reserveur;
		}		
		
		
	
	//Operations
		
	
		
		
		/**
         *   
	     * public int construireReservation(int  id)
	     *    
	     * @param id
         * @return nombre : int : objet trouvé ou pas 
         */
		public int construireReservation(int id)
        {
            this.setId_reservation(id);
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "SELECT * FROM reservation WHERE id_reservation="+getId_reservation()+"";
            ResultSet r = gbd.execQuery(requete);
            int nombre = gbd.count();
            if (nombre !=0)
            {
                try {
                    r.next();
                    this.setDate_reservation((r.getDate("date_reservation")));
                    this.setDate_retour(((r.getDate("date_retour"))));
                    this.setHeure_reservation(r.getTime("heure_reservation"));
                    this.setHeure_retour(r.getTime("heure_retour"));
                    this.Reserveur = new Enseignant();
                    this.getReserveur().construireEnseignant(r.getInt("id_enseignant"));
                    gbd.deconnecter();    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            
            }
            
            return nombre;
            
        }	
		
		
		 /**
         *  insererReservation()
         *  Methode qui permet d'insrer une Reservation dans la base
         *
         * @return res : int : Inserer ou non
         **/
        
        
        public int insererReservation()
        {
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "INSERT INTO reservation (id_enseignant,date_reservation,date_retour,heure_reservation,heure_retour) VALUES("+this.getReserveur().getId_enseignant()+",'"+this.getDate_reservation()+"','"+this.getDate_retour()+"','"+this.getHeure_reservation()+"','"+this.getHeure_retour()+"')";
            int res = gbd.execUpdate(requete);
            gbd.deconnecter();
            return res;
      
        }

   	 /**
	 	 * modifierReservation
	 	 * 
	 	 * fonction qui permet de modifier une Reservation a la base
	 	 * 
	 	 * @return 1:si modification reussie 0:si echec de modification
	 	 */	
	 	 	public int modifierReservation()
	 	{
	 		Gbd gbd = new Gbd();
	         gbd.connecter();
	         String requete = "UPDATE  reservation SET id_enseignant="+this.getReserveur().getId_enseignant()+", date_reservation='"+this.getDate_reservation()+"', date_retour='"+this.date_retour+"' WHERE id_reservation="+this.id_reservation+"" ;
	         int res = gbd.execUpdate(requete);
	         gbd.deconnecter();
	         return res;
	 	}
	     
	 	/**
	 	 * supprimerReservation
	 	 * 
	 	 * fonction qui permet de supprimer une Reservation de la base
	 	 * 
	 	 * @return 1:si suppression reussie 0:si echec de suppression
	 	 */

	 	public int supprimerReservation()
	 	{
	 		Gbd gbd = new Gbd();
	         gbd.connecter();
	         String requete = "DELETE FROM reservation WHERE id_reservation="+this.id_reservation;
	         int res = gbd.execUpdate(requete);
	         gbd.deconnecter();
	         return res;
	 	}

		public String toString()
		{String s="";
		s+="id reser  ="+this.id_reservation+"\n id ense ="+this.getReserveur().getId_enseignant()+"\n date resrvation ="+getDate_reservation()+"heure reservation ="+getHeure_reservation()+"\ndate retour ="+this.getDate_retour()+" heure retour ="+this.getHeure_retour();
		return s ;
		}
	
	

} //End Class Reservation


