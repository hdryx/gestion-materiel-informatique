package forms;

import gestion_materiel.Gbd;
import gestion_materiel.Peripherique;

import java.util.StringTokenizer;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "Processeur==>name::        Intel(R) Pentium(R) M processor 1.60GHz::" +
				"cache::2048::frequence::1596::serial::AFE9FBFF000006D8==>" +
				"Graphique==>name::ATI MOBILITY RADEON X700::memoire::128::mode::" +
				"1280 x 800 x 4294967296 couleurs==>Son==>name::SoundMAX Integrated Digital " +
				"Audio::fabriquant::Analog Devices, Inc.==>Pc==>Marque::Hewlett-Packard" +
				"::Modele::Pavilion dv4000 (EN417EA#ABF)::Serial::2CE54500LF==>" +
				"Ram==>Emplacement::Bank 0::Bank 1::Taille::256::256";
		
		StringTokenizer t = new StringTokenizer(s,"==>");
		
				
		t.nextToken();
		/* Processeur*/
		String processeur = t.nextToken();
			StringTokenizer p = new StringTokenizer(processeur,"::");
			p.nextToken();
			String name = p.nextToken();;
			p.nextToken();
			String cache = p.nextToken();;
			p.nextToken();
			String frequence = p.nextToken();;
			p.nextToken();
			String serial = p.nextToken();
		/* Fin Processeur */
			
			
			
			/* Graphique */	
			t.nextToken();
			String graphique = t.nextToken();
				StringTokenizer p2 = new StringTokenizer(graphique,"::");
				p2.nextToken();
				String name_graphique =p2.nextToken() ;
				p2.nextToken();
				String memoire_graphique =p2.nextToken() ;
				p2.nextToken();
				String mode_graphique =p2.nextToken() ;
			/* Fin Graphique */
				
			/* Son */	
			t.nextToken();
			String son = t.nextToken();
				StringTokenizer p3 = new StringTokenizer(son,"::");
				p3.nextToken();
				String name_son =p3.nextToken() ;
				p3.nextToken();
				String fabricant_son =p3.nextToken() ;
			/*Fin Son */
			
			/* PC */
			t.nextToken();
			String pc  = t.nextToken();
				StringTokenizer p4 = new StringTokenizer(pc,"::");
				p4.nextToken();
				String marque_pc =p4.nextToken() ;
				p4.nextToken();
				String modele_pc =p4.nextToken() ;
				p4.nextToken();
				String serial_pc =p4.nextToken() ;
			/* Fin PC */	
			
			/* RAM */
			t.nextToken();
			String ram = t.nextToken();
			
			System.out.println(ram);
			Gbd gbd = new Gbd();
			
			
			gbd.connecter();
			String requete = "INSERT INTO peripherique (id_type_peripherique,id_pc,id_lieu,id_slot,nom,numero_serie,pilote,flag)" +
					" Values (2,null,null,null,'"+name+"','"+serial+"','','1')";
			//System.out.println(requete);
			String requete2 = "SELECT max(id_peripherique) AS id FROM perihperique"; 
			String requete3 = "INSERT INTO peripherique_propriete VALUES(id,1,'"+frequence+"')";
			requete3 = "INSERT INTO peripherique_propriete VALUES(id,5,'"+cache+"')";
			
			/*Fin RAM */
		
	}

}
