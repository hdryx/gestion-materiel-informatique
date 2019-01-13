/*
 * Main.java
 *
 * Created on 28 mars 2007, 13:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gestion_materiel;

import java.util.Vector;



/**
 *
 * @author haffouff
 */
public class Main {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        
       /* 
    	Peripherique p = new Peripherique();
    	p.construirePeripherique(1);
    	Peripherique_propriete pp = (Peripherique_propriete)(p.getLesPeripherique_propriete().elementAt(1));
    	//Propriete t = (Propriete)(p.getType_peripherique().getLesProprietes().elementAt(0));
    	Propriete t = new Propriete();
    	t.construirePropriete(pp.getId_propriete());
    	System.out.println(t.getPropriete()+"==>"+pp.getValeur());
    	*/
    	Enseignant e = new Enseignant();
    	e.construireEnseignant(1);
    	e.setNom_enseignant("ali");
    	System.out.println(e.modifierEnseignant());
    	
    }       
}
