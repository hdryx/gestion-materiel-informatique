/*
 * Gbd.java
 *
 * Created on 28 mars 2007, 14:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gestion_materiel;
import java.sql.* ;
import org.jdesktop.swingx.JXDialog;
import org.jdesktop.swingx.JXErrorDialog;

/**
 *
 * @author haffouff
 */
public class Gbd {
    
    
    String nomBase = "gestion_materiel";
    ResultSet resultat;
    Connection connexion;
    Statement stmt;
    PreparedStatement pStmt;
    
    /** Creates a new instance of Gbd */
    public Gbd() 
    {
         try {
            
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JXErrorDialog.showDialog(null,"DataBase ERROR !!!",ex);
            
        }
         
    }
    
    public void connecter()
    {
        String url="jdbc:odbc:"+nomBase;
        try {
            connexion = DriverManager.getConnection(url);
            stmt = connexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            } catch (SQLException ex) {
            	ex.printStackTrace();
            	JXErrorDialog.showDialog(null,"DataBase ERROR !!!",ex);
        }
        
     }
    
    
    public void deconnecter()
    {
        try {
            connexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JXErrorDialog.showDialog(null,"DataBase ERROR !!!",ex);
        }
    }
    
    
    public ResultSet execQuery(String requete)
    {
        ResultSet r = null;
        try {
            r = stmt.executeQuery(requete);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JXErrorDialog.showDialog(null,"DataBase ERROR !!!",ex);
        }
        this.resultat = r;
        return r;
    }
    
    
    public int execUpdate(String requete)
    {
        int res =0;
        try {
           
             res = stmt.executeUpdate(requete);
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            JXErrorDialog.showDialog(null,"DataBase ERROR !!!",ex);
        }
        
       return res;
    }
    
    
    public int execUpdate()
    {
        int res =0;
        try {
           
             res = pStmt.executeUpdate();
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            JXErrorDialog.showDialog(null,"DataBase ERROR !!!",ex);
        }
        
       return res;
    }
    
    
    public void preparerStatement(String requete)
    {
    	try {
			pStmt = connexion.prepareStatement(requete);
		} catch (SQLException e) {
			 
			e.printStackTrace();
			JXErrorDialog.showDialog(null,"DataBase ERROR !!!",e);
		}
    	

    }
    
    
    
    public int count()
    {
        int nombreLignes = 0;
        try {
            this.resultat.last();
             nombreLignes = this.resultat.getRow();
            this.resultat.beforeFirst();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JXErrorDialog.showDialog(null,"DataBase ERROR !!!",ex);
        }
        
        return nombreLignes;
    }
    
    
}
