/**************************************************************************
* Source File	:  Peripherique.java
* Author                   :  abcdef  
* Project name         :  Espace de travail* Created                 :  28/03/2007
* Modified   	:  28/03/2007
* Description	:  Definition of the class Peripherique
**************************************************************************/


package gestion_materiel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;




public  class Peripherique  
{ 

	

	//Attributes
		
		private 
	 int id_peripherique;
		private 
	 String nom;
		private 
	 String numero_serie;
		private 
	 String pilote;
	
	//Attributes Association
	
		private 
	Type_peripherique type_peripherique;
		private
	Pc pc;
		private
	Slot slot;
		private
	Garantie garantie;
		private
	Lieu lieu;
		private 
	Vector lesPeripherique_propriete;
		
	
	
	

	/** Constructeurs*/
	
	public Peripherique(int id_peripherique, String nom, String numero_serie, String pilote, Type_peripherique type_peripherique,Vector lesPeripherique_propriete) 
	{
		super();
		this.id_peripherique = id_peripherique;
		this.nom = nom;
		this.numero_serie = numero_serie;
		this.pilote = pilote;
		this.type_peripherique = type_peripherique;
		this.lesPeripherique_propriete = lesPeripherique_propriete;
	}
		
	public Peripherique()
	{
		this.lesPeripherique_propriete = new Vector();
	}

	

	/** Guetteurs et setteurs*/
	
	public int getId_peripherique() {
		return id_peripherique;
	}

	public void setId_peripherique(int id_peripherique) {
		this.id_peripherique = id_peripherique;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNumero_serie() {
		return numero_serie;
	}

	public void setNumero_serie(String numero_serie) {
		this.numero_serie = numero_serie;
	}

	public String getPilote() {
		return pilote;
	}

	public void setPilote(String pilote) {
		this.pilote = pilote;
	}

	public Type_peripherique getType_peripherique() {
		return type_peripherique;
	}

	public void setType_peripherique(Type_peripherique type_peripherique) {
		this.type_peripherique = type_peripherique;
	}
	
	public Garantie getGarantie() {
		return garantie;
	}

	public void setGarantie(Garantie garantie) {
		this.garantie = garantie;
	}

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}

	public Pc getPc() {
		return pc;
	}

	public void setPc(Pc pc) {
		this.pc = pc;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}
	
	public Vector getLesPeripherique_propriete() {
		return lesPeripherique_propriete;
	}

	public void setLesPeripherique_propriete(Vector lesPeripherique_propriete) {
		this.lesPeripherique_propriete = lesPeripherique_propriete;
	}

	/**
	 * insererPeripherique
	 * 
	 * fonction qui permet d'ajouter un Peripherique a la base
	 * 
	 * @return 1:si insertion reussie 0:si echec d'insertion
	 */
	
	public int insererPeripherique()
	{
		
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "INSERT INTO peripherique (id_garantie,id_type_peripherique,id_pc,id_lieu,id_slot,nom,numero_serie,pilote) " +
        		"VALUES("+this.garantie.getId_garantie()+"," +
        				""+this.type_peripherique.getId_type_peripherique()+"," +
        				""+this.pc.getId_pc()+","+
        				""+this.lieu.getId_lieu()+","+
        				""+this.slot.getId_slot()+","+
        				"'"+this.nom+"','"+this.numero_serie+"','"+this.pilote+"')";
        System.out.println(requete);
        int res = gbd.execUpdate(requete);
        gbd.deconnecter();
        return res;
	
	}
	
	
	/**
	 * modifierPeripherique
	 * 
	 * fonction qui permet de modifier un Peripherique a la base
	 * 
	 * @return 1:si modification reussie 0:si echec de modification
	 */	
	
	public int modifierPeripherique()
	{
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "UPDATE peripherique SET" +
        		" id_garantie="+this.garantie.getId_garantie()+
        		", id_type_peripherique="+this.type_peripherique.getId_type_peripherique()+
        		", id_pc="+this.pc.getId_pc()+
        		", id_lieu="+this.lieu.getId_lieu()+
        		", id_slot="+this.slot.getId_slot()+
        		", nom='"+this.nom+"'"+
        		", numero_serie='"+this.numero_serie+"'"+
        		", pilote='"+this.pilote+"'"+
        		" WHERE id_peripherique="+this.id_peripherique;
        System.out.println(requete);
        int res = gbd.execUpdate(requete);
        gbd.deconnecter();
        return res;
	}
	
	/**
	 * supprimerPeripherique
	 * 
	 * fonction qui permet de supprimer un Peripherique de la base
	 * 
	 * @return 1:si suppression reussie 0:si echec de suppression
	 */
    
	public int supprimerPeripherique()
	{
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "DELETE FROM peripherique WHERE id_peripherique="+this.id_peripherique;
        int res = gbd.execUpdate(requete);
        gbd.deconnecter();
        return res;
	}


	/**
	 * construirePeripherique
	 * 
	 * fonction qui permet de construire un Peripherique a partir de son id
	 * 
	 * @param int	id_peripherique	l'id du peripherique
	 * @return 1:si id existe 0:si id inexistant
	 */
	public int construirePeripherique(int id_peripherique)
	{
		
		this.setId_peripherique(id_peripherique);
		Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "SELECT * FROM peripherique WHERE id_peripherique="+id_peripherique;
        
        ResultSet r = gbd.execQuery(requete);
        int nombre = gbd.count();
        if (nombre !=0)
        {
            try {
                r.next();
                this.garantie = new Garantie();
                this.garantie.construireGarantie(r.getInt("id_garantie"));
                this.type_peripherique = new Type_peripherique();
                this.type_peripherique.construireTypePeripherique(r.getInt("id_type_peripherique"));
                this.pc = new Pc();
                this.pc.construirePc(r.getInt("id_pc"));
                this.lieu = new Lieu();
                this.lieu.construireLieu(r.getInt("id_lieu"));
                this.slot = new Slot();
                this.slot.construireSlot(r.getInt("id_slot"));
                this.nom = r.getString("nom");
                this.numero_serie = r.getString("numero_serie");
                this.pilote = r.getString("pilote");
                
                //Recuperer toutes les proprietes du peripherique avec leurs valeurs
                String requete2 = "SELECT * FROM peripherique_propriete WHERE id_peripherique="+id_peripherique;
                ResultSet r2 = gbd.execQuery(requete2);
                if (gbd.count()!=0)
                {
                	while (r2.next())
                	{
                		Peripherique_propriete p = new Peripherique_propriete(r2.getInt("id_propriete"),id_peripherique,r2.getString("valeur"));
                		this.lesPeripherique_propriete.add(p);
                	}
                }
                gbd.deconnecter();    
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        
        }
        
        return nombre;
        
				
	}
	
	
	public String toString()
	{
		String a="";
		a+="id="+this.id_peripherique;
		a+="\nid_garantie="+this.garantie.getId_garantie();
		a+="\nid_type_peripherique="+this.type_peripherique.getId_type_peripherique();
		a+="\nid_pc="+this.pc.getId_pc();
		a+="\nlieu="+this.lieu.getId_lieu();
		a+="\nid_slot="+this.slot.getId_slot();
		a+="\nnom="+this.nom;
		a+="\nnumero_serie="+this.numero_serie;
		a+="\npilote="+this.pilote;
		
		return a;
	}

	

} //End Class Peripherique


