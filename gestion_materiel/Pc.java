/**************************************************************************
* Source File	:  Pc.java
* Author                   :  abcdef  
* Project name         :  Espace de travail* Created                 :  28/03/2007
* Modified   	:  28/03/2007
* Description	:  Definition of the class Pc
**************************************************************************/



package gestion_materiel;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public  class Pc  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private 
	 java.lang.Integer id_pc;
		private 
	 java.lang.String marque;
		private 
	 java.lang.String modele;
		private 
	 java.lang.String numero_serie;
		private 
	 java.lang.Integer nb_pci;
		private 
	 java.lang.Integer nb_agp;
		private 
	 java.lang.Integer nb_usb;
		private 
	 java.lang.String nom_modele;
		private 
	 java.lang.String est_modele;
		private
	 java.sql.Date	date_debut ;
		private
		int duree ;
		
	
	//Attributes Association
	
		Vector listePeripherique;
		
		Vector LogicielsInstallés;
		
		Vector lesOS ;
		
		Vector listeIntervention;
	 
		Garantie garantie;
	 
		Lieu lieu;
	 
		
/**
 * Constructors
 * 
 * 
 * @param id_pc
 * @param marque
 * @param modele
 * @param numero_serie
 * @param nb_pci
 * @param nb_agp
 * @param nb_usb
 * @param nom_modele
 * @param est_modele
 */
	public Pc(Integer id_pc, String marque, String modele, String numero_serie, Integer nb_pci, Integer nb_agp, Integer nb_usb, String nom_modele, String est_modele) {
			super();
			this.id_pc = id_pc;
			this.marque = marque;
			this.modele = modele;
			this.numero_serie = numero_serie;
			this.nb_pci = nb_pci;
			this.nb_agp = nb_agp;
			this.nb_usb = nb_usb;
			this.nom_modele = nom_modele;
			this.est_modele = est_modele;
				}
		
		

	public Pc(Integer id_pc, String marque, String modele, String numero_serie, Integer nb_pci, Integer nb_agp, Integer nb_usb, String nom_modele, String est_modele, Vector listePeripherique, Vector logicielsInstallés, Vector lesOS, Vector listeIntervention, Garantie garantie, Lieu lieu) {
	super();
	this.id_pc = id_pc;
	this.marque = marque;
	this.modele = modele;
	this.numero_serie = numero_serie;
	this.nb_pci = nb_pci;
	this.nb_agp = nb_agp;
	this.nb_usb = nb_usb;
	this.nom_modele = nom_modele;
	this.est_modele = est_modele;
	this.listePeripherique = listePeripherique;
	LogicielsInstallés = logicielsInstallés;
	this.lesOS = lesOS;
	this.listeIntervention = listeIntervention;
	this.garantie = garantie;
	this.lieu = lieu;
}



		public Pc()
		{
			this.listeIntervention = new Vector();
			this.listePeripherique = new Vector();
			this.lesOS = new Vector();
		}

/**
 * Getters & Setters
 * 
 * @return
 */		public java.lang.String getEst_modele() {
			return est_modele;
		}

		public void setEst_modele(java.lang.String est_modele) {
			this.est_modele = est_modele;
		}

		public Garantie getGarantie() {
			return garantie;
		}

		public void setGarantie(Garantie garantie) {
			this.garantie = garantie;
		}

		public java.lang.Integer getId_pc() {
			return id_pc;
		}

		public void setId_pc(java.lang.Integer id_pc) {
			this.id_pc = id_pc;
		}

		public Lieu getLieu() {
			return lieu;
		}

		public void setLieu(Lieu lieu) {
			this.lieu = lieu;
		}

		

		
	

		public Vector getListePeripherique() {
			this.construieListePeripherique();
			return listePeripherique;
		}

		public void setListePeripherique(Vector listePeripherique) {
			this.listePeripherique = listePeripherique;
		}

		public Vector getLogicielsInstallés() {
			return LogicielsInstallés;
		}

		public void setLogicielsInstallés(Vector logicielsInstallés) {
			LogicielsInstallés = logicielsInstallés;
		}

		
		
		public Vector getLesOS() {
			return lesOS;
		}

		public void setLesOS(Vector lesOS) {
			this.lesOS = lesOS;
		}

		public Vector getListeIntervention() {
			
			return listeIntervention;
		}

		public void setListeIntervention(Vector listeIntervention) {
			this.listeIntervention = listeIntervention;
		}

		public java.lang.String getMarque() {
			return marque;
		}

		public void setMarque(java.lang.String marque) {
			this.marque = marque;
		}

		public java.lang.String getModele() {
			return modele;
		}

		public void setModele(java.lang.String modele) {
			this.modele = modele;
		}

		public java.lang.Integer getNb_agp() {
			return nb_agp;
		}

		public void setNb_agp(java.lang.Integer nb_agp) {
			this.nb_agp = nb_agp;
		}

		public java.lang.Integer getNb_pci() {
			return nb_pci;
		}

		public void setNb_pci(java.lang.Integer nb_pci) {
			this.nb_pci = nb_pci;
		}

		public java.lang.Integer getNb_usb() {
			return nb_usb;
		}

		public void setNb_usb(java.lang.Integer nb_usb) {
			this.nb_usb = nb_usb;
		}

		public java.lang.String getNom_modele() {
			return nom_modele;
		}

		public void setNom_modele(java.lang.String nom_modele) {
			this.nom_modele = nom_modele;
		}

		public java.lang.String getNumero_serie() {
			return numero_serie;
		}

		public void setNumero_serie(java.lang.String numero_serie) {
			this.numero_serie = numero_serie;
		}
	 
		
		public java.util.Date getDate_debut() {
			return date_debut;
		}



		public void setDate_debut(Date date) {
			this.date_debut =date;
		}



		public int getDuree() {
			return duree;
		}



		public void setDuree(int duree) {
			this.duree = duree;
		}
//		Operations


		/**
         *   
	     * public int construirePc(int  id)
	     *    a verifer si sa marche 
	     * @param id
         * @return nombre : int : objet trouvé ou pas 
         */
		
		public int construirePc(int id)
        {
            this.setId_pc(id);
            Gbd gbd = new Gbd();
            gbd.connecter();
            String requete = "SELECT * FROM pc WHERE id_pc="+id+"";
            ResultSet r = gbd.execQuery(requete);
            int nombre = gbd.count();
            if (nombre !=0)
            {
                try {
                    r.next();
                    this.setNom_modele(r.getString("nom_modele"));
                    this.setEst_modele(r.getString("est_modele"));
                    this.setMarque(r.getString("marque"));
                    this.setModele(r.getString("modele"));
                    this.setNumero_serie(r.getString("numero_serie"));
                    this.setNb_pci(r.getInt("nb_pci"));
                    this.setNb_agp(r.getInt("nb_agp"));
                    this.setNb_usb(r.getInt("nb_usb"));
                    this.lieu =new Lieu();
                    this.lieu.construireLieu(r.getInt("id_lieu"));
                    this.setDate_debut(r.getDate("date_debut"));
                    this.setDuree(r.getInt("duree"));
                    
                    
                    /*  this.garantie = new Garantie();
                    this.garantie.construireGarantie(r.getInt("id_garantie"));
                    
                    */
                    
                    gbd.deconnecter();    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            
            }
            
            return nombre;
            
        }	
		
		
		
/**
 * construieLogicielsInstallés
 * 
 *  
 *
 */		
		
	public void	construieLogicielsInstallés()
		{Gbd gbd = new Gbd();
		 gbd.connecter();
		 
		 String req ="select id_logiciel  from installe_dans where id_pc="+this.id_pc;
		 ResultSet r =gbd.execQuery(req);
		 try {
			while (r.next())
			 {Logiciel l = new Logiciel();
			  l.construireLogiciel(r.getInt("id_logiciel"));
			  LogicielsInstallés.add(l);			 
			 }
			gbd.deconnecter();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
		}
		
	
	/**
	 * construieListePeripherique
	 * 
	 *  
	 *
	 */		
			
		public void	construieListePeripherique()
			{Gbd gbd = new Gbd();
			 gbd.connecter();
			 
			 String req ="select id_peripherique  from peripherique where id_pc="+this.id_pc;
			 ResultSet r =gbd.execQuery(req);
			 try {
				while (r.next())
				 {Peripherique p = new Peripherique();
				  p.construirePeripherique(r.getInt("id_peripherique"));
				  listePeripherique.add(p);			 
				 }
				gbd.deconnecter();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				
			}
/**
 * construirelesOS
 *
 */		
		public void construirelesOS()
		{	Gbd gbd = new Gbd();
			gbd.connecter();
			
			String req ="SELECT  id_os FROM marche_sous WHERE id_pc ="+this.getId_pc();
			ResultSet r =gbd.execQuery(req);
			
			try {
				while (r.next())
				 {OS p = new OS();
				  p.construireOS(r.getInt("id_os"));
				  lesOS.add(p);			 
				 }
				gbd.deconnecter();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
/**
 * 
 *		
 *		listeIntervention
 **/		

		public void construirelisteIntervention()
		{	Gbd gbd = new Gbd();
			gbd.connecter();
			
			String req ="SELECT  id_intervention FROM intervention  WHERE id_pc ="+this.getId_pc();
			ResultSet r =gbd.execQuery(req);
			
			try {
				while (r.next())
				 {Intervention i = new Intervention();
				  i.construireIntervention(r.getInt("id_intervention"));
				  listeIntervention.add(i);			 
				 }
				gbd.deconnecter();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
	
		
		
		
		
	/**
      *  insererPc()
      *  Methode qui permet d'insrer un Pc dans la base
      *
      * @return res : int : Inserer ou non
      **/
     
     
     public int insererPc()
     {
         Gbd gbd = new Gbd();
         gbd.connecter();
         //String requete = "INSERT INTO pc (id_garantie,id_lieu,marque,modele,numero_serie,nom_modele,est_modele,nb_pci,nb_agp,nb_usb,date_debut,duree) VALUES("+this.getGarantie().getId_garantie()+","+this.getLieu().getId_lieu()+",'"+this.getMarque()+"','"+ this.getModele()+"','"+this.getNumero_serie()+"','"+this.getNom_modele()+"','"+ this.getEst_modele()+"',"+ this.getNb_pci()+","+ this.getNb_agp()+","+ this.getNb_usb()+",'"+this.getDate_debut()+"','"+this.getDuree()+"')";
         
         String requete = "INSERT INTO pc (id_lieu,marque,modele,numero_serie,nom_modele,est_modele,nb_pci,nb_agp,nb_usb,date_debut,duree) VALUES("+this.getLieu().getId_lieu()+",'"+this.getMarque()+"','"+ this.getModele()+"','"+this.getNumero_serie()+"','"+this.getNom_modele()+"','"+ this.getEst_modele()+"',"+ this.getNb_pci()+","+ this.getNb_agp()+","+ this.getNb_usb()+",'"+this.getDate_debut()+"',"+this.getDuree()+")";
         int res = gbd.execUpdate(requete);
         gbd.deconnecter();
         return res;
   
     }

     /**
 	 * modifierPc
 	 * 
 	 * fonction qui permet de modifier un Pc a la base
 	 * 
 	 * @return 1:si modification reussie 0:si echec de modification
 	 */	
 	
 	public int modifierPc()
 	{
 		Gbd gbd = new Gbd();
         gbd.connecter();
        // String requete = "UPDATE pc SET id_garantie="+this.getGarantie().getId_garantie()+", id_lieu="+this.getLieu().getId_lieu()+" , marque='"+this.marque+"' , modele='"+this.modele+"' ,  numero_serie='"+this.numero_serie+"' ,  nom_modele='"+this.nom_modele+"' ,  est_modele='"+this.est_modele+"' ,  nb_pci="+this.nb_pci+" ,  nb_agp="+this.nb_agp+"  ,  nb_usb="+this.nb_usb+" ,date_debut='"+this.getDate_debut()+"',duree='"+this.getDuree()+"' WHERE id_pc="+this.id_pc;
         
         String requete = "UPDATE pc SET  id_lieu="+this.getLieu().getId_lieu()+" , marque='"+this.marque+"' , modele='"+this.modele+"' ,  numero_serie='"+this.numero_serie+"' ,  nom_modele='"+this.nom_modele+"' ,  est_modele='"+this.est_modele+"' ,  nb_pci="+this.nb_pci+" ,  nb_agp="+this.nb_agp+"  ,  nb_usb="+this.nb_usb+" ,date_debut='"+this.getDate_debut()+"',duree="+this.getDuree()+" WHERE id_pc="+this.id_pc;
         int res = gbd.execUpdate(requete);
         gbd.deconnecter();
         return res;
 	}
     
 	/**
 	 * supprimerPc
 	 * 
 	 * fonction qui permet de supprimer un Pc de la base
 	 * 
 	 * @return 1:si suppression reussie 0:si echec de suppression
 	 */
     
 	public int supprimerPc()
 	{
 		Gbd gbd = new Gbd();
         gbd.connecter();
         //Liberation des peripherique
         String requete = "UPDATE peripherique SET id_pc=null WHERE id_pc="+this.id_pc;
         int res = gbd.execUpdate(requete);
         //Suppression du Pc
         requete = "DELETE FROM pc WHERE id_pc="+this.id_pc;
         res = gbd.execUpdate(requete);
         gbd.deconnecter();
         return res;
 	}
 	
 	
 	public int supprimerPcEtPeripherique()
 	{
 		Gbd gbd = new Gbd();
         gbd.connecter();
         //Suppression des peripherique
         String requete = "DELETE FROM peripherique WHERE id_pc="+this.id_pc;
         int res = gbd.execUpdate(requete);
         //Spression du Pc
         requete = "DELETE FROM pc WHERE id_pc="+this.id_pc;
         res = gbd.execUpdate(requete);
         gbd.deconnecter();
         return res;
 	}
 	
 	
 	
 	public String toString()
     {
     	String s="";
     	s+="id pc ="+this.id_pc+"/n";
     	s+=" Marque ="+this.marque+"/n";
     	s+="  Modele ="+this.modele+"/n";
     	s+="   numero_serie ="+this.numero_serie+"/n";
     	s+="    id du garantie ="+this.getGarantie().getId_garantie()+"/n";
     	s+="	 id du lieu  ="+this.lieu.getId_lieu()+"/n";	
     	return s;
     	
     }



	

     
	

} //End Class Pc

/*
 * test main de pc 
 * 
 Pc p = new Pc();
p.construirePc(1);
p.setEst_modele("non");
p.setNom_modele("non montionné");
p.setNumero_serie("DNWJGHQD");
p.modifierPc();
Pc p2 = new Pc();
p2.construirePc(1);
String s="";
s+="marque='"+p2.getMarque()+"' and modele='"+p2.getModele()+"' and  numero_serie='";
s+=""+p2.getNumero_serie()+"' and  nom_modele='"+p2.getNom_modele()+"' and  est_modele='"+p2.getEst_modele()+"' and  nb_pci="+p2.getNb_pci()+" and  nb_agp="+p2.getNb_agp()+"  and  nb_usb="+p2.getNb_usb()+"";

System.out.println(s);
//Pc p1 = new Pc();

//p.insererPc();
/*p.modifierPc();
Pc p1 = new Pc();
p1.construirePc(2);
System.out.println("marque='"+p1.getMarque()+"' and modele='"+p1.getModele()+"' and  numero_serie='"+p1.getNumero_serie()+"' and  nom_modele='"+p1.getNom_modele()+"' and  est_modele='"+p1.getEst_modele()+"' and  nb_pci="+p1.getNb_pci()+" and  nb_agp="+p1.getNb_agp()+"  and  nb_usb="+p1.getNb_usb());
p1.supprimerPc();*/
