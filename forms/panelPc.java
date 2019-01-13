/*
 * panelPc.java
 *
 * Created on __DATE__, __TIME__
 */

package forms;
import java.util.*;

import gestion_materiel.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.sql.*;
import java.sql.Date;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import sun.awt.DefaultMouseInfoPeer;

import org.jdesktop.swingx.JXComboBox;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXDialog;
import org.jdesktop.swingx.JXErrorDialog;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.AlternateRowHighlighter;
import org.jdesktop.swingx.decorator.Highlighter;
import org.jdesktop.swingx.decorator.HighlighterPipeline;
import org.jdesktop.swingx.decorator.RolloverHighlighter;

import com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultXMLDocumentHandler;

/*
 *
 * @author  __USER__
 */
public class panelPc extends javax.swing.JPanel {


	Vector v = new Vector();
	Object [][] data=null;
	Object [][] test =null;
	JXTable jTablePc;
	
	DefaultTableModel model;
	
	JXDialog periphdialog ;
	
	String [] titre = {"Type peripherique","Nom","Numéro de série","Slot","Pilote"};
	String [] titrePc = {"ID Pc","Marque","Modèle","Numéro de série","Nb slot PCI","Nb slot AGP","Nb port USB"};
	ListIterator lst;
	Pc p1 ;
	
	boolean a=true,ajout=false,modif=false;
	MainForm m;

	String [] tl ;
	String [] lieu ;
	
	//JXDatePicker jDatedebut;
	
	JTextField txtIdLieu = new JTextField("");
	JComboBox cmbIdLieu =  new JComboBox(titre);
	
	
	
	String s1 = "card1_text";
    String s2 = "card1_box";
    
    String s3 = "card2_text";	
    String s4 = "card2_box";
	
	String s5 ="card3_Date";
	
	int code=-1;
	
    /** Creates new form panelPc */
    public panelPc(MainForm m,int code) 
    {
    	model = new DefaultTableModel();
    	
    	this.code = code;
    	
    //	jDatedebut= new JXDatePicker();
    	JTextField essaie = new JTextField("C est un essaie ");
//    	String [] titrePc = {"ID Pc","Marque","Modèle","Numéro de série","Nb slot PCI","Nb slot AGP","Nb port USB"};
    	model.addColumn("Id Pc");
    	model.addColumn("Marque");
    	model.addColumn("Modèle");
    	
    	model.addColumn("Numéro de série");
    	model.addColumn("Nb slot PCI");
    	model.addColumn("Nb slot AGP");
    	model.addColumn("Nb slot USB");
    	
    	this.m=m;
    	jTablePc = new JXTable();
		jTablePc.setHighlighters(
        new HighlighterPipeline(new Highlighter[] {AlternateRowHighlighter.quickSilver}
));
		jTablePc.setRolloverEnabled(true);
		jTablePc.getHighlighters().addHighlighter(new RolloverHighlighter(Color.GRAY, Color.WHITE ));
		jTablePc.setColumnControlVisible(true);
		
		
		
		
    	initComponents(); 
    	if(this.code!=-2)jBRechercher.setVisible(false);
    	// jDatepanel.add(jDatedebut); 	
	jDatedebut.setEnabled(false);
	String[] format= new String []{"dd/MM/yyyy"};
	jDatedebut.setFormats(format);
    	txtIdLieu.setEnabled(false);
    	
    	jcardid_lieu.setLayout(new CardLayout());
    	//http://www.comelec.enst.fr/~ciblat/docs_cours/dvb.pdf
    	
    	
    	 cmbIdLieu.addItemListener(new java.awt.event.ItemListener() {
 			public void itemStateChanged(java.awt.event.ItemEvent evt) {
 				cmbIdLieuItemStateChanged(evt);
 			}
 		});
    	
    	
    	
    	
    	jcardid_lieu.add(s1,txtIdLieu);
    	jcardid_lieu.add(s2,cmbIdLieu);

    	      
        ((CardLayout) jcardid_lieu.getLayout()).show(jcardid_lieu,s1);
        if(code==-1 || code==0)//0 pour l inventaire le truc de flag =1
        recupererPc();
        if(code==-2){
        	
    		
			cmbIdLieu.removeAllItems();
			cmbIdLieu.addItem("");
			cmbIdLieu.addItem("Choisir un Lieu");
			cmbIdLieu.setEnabled(true);
									
			((CardLayout) jcardid_lieu.getLayout()).show(jcardid_lieu,s2);
			
			jDatedebut.setEnabled(true);
			jTextDurée.setEnabled(true);
			jTextmarque.setEnabled(true);
			jTextmodèle.setEnabled(true);
			jTextN_serie.setEnabled(true);
			jTextnb_AGP.setEnabled(true);
			jTextnb_PCI.setEnabled(true);
			jTextnb_USB.setEnabled(true);
        	
        }
        //remplirChamps(p1);
     // jDatepanel.add(jDatedebut);
        
        jTablePc.getSelectionModel().addListSelectionListener(new ListSelectionListener()
    	{
    			public void valueChanged(ListSelectionEvent lse)
    			{
    				if (jTablePc.getSelectedRow()!=-1 && modif==false && ajout == false)
    				{
    					
    					int ligne = jTablePc.getSelectedRow();
    					p1 =(Pc)v.elementAt(ligne);
    					
    					
    					remplirChamps(p1);
    					/*jTextid_pc.setText(p1.getId_pc().toString());
    					jTextmarque.setText(p1.getMarque());
    					jTextmodèle.setText(p1.getModele());
    					jTextN_serie.setText(p1.getNumero_serie());
    					jTextnb_PCI.setText(p1.getNb_pci().toString());
    					jTextnb_AGP.setText(p1.getNb_agp().toString());
    					jTextnb_USB.setText(p1.getNb_usb().toString());
    					jTextTypelieu.setText(p1.getLieu().getTypeLieu().getNom_type());
    					jTextLieu.setText(p1.getLieu().getNom());
    					jDatedebut.setDate(p1.getGarantie().getDate_debut());
    					jTextDurée.setText(p1.getGarantie().getDuree());
    				*/
    				}
    	
    			}
    	
    	});
    	
    	
              
        
    }

        
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
	 */
	  public void recupererPc()
    {String requete="";
    	Gbd gbd = new Gbd();
    	gbd.connecter();
    	
    	if (this.code==-1)
    		requete  =  "SELECT id_pc FROM pc";
    	else if (this.code==0)
    		requete  =  "SELECT id_pc FROM pc WHERE flag=1";
    	
    	ResultSet r = gbd.execQuery(requete);
    	
    	try{
    	
    		while (r.next())
    		{
    			Pc p = new Pc();
    			p.construirePc(r.getInt("id_pc"));
    			v.add(p);
    		}
    	}catch (SQLException e)
    	{
    		e.printStackTrace();
    	}
    	
    	
    	
    	Object [][] dataPC = new Object[v.size()][7];
    	
    	
    	for (int i=0;i<v.size();i++)
    	{
    		Pc p =(Pc)v.elementAt(i);
    		dataPC[i][0] = p.getId_pc().toString();
    		dataPC[i][1] = p.getMarque();
    		dataPC[i][2] = p.getModele();
    		dataPC[i][3] = p.getNumero_serie();      
    		dataPC[i][4] = p.getNb_pci().toString();
    		dataPC[i][5] = p.getNb_agp().toString();
    	    dataPC[i][6] = p.getNb_usb().toString();
    	    
    	    String ligne[]={p.getId_pc().toString(),
    	    		p.getMarque(),
    	    		p.getModele(),
    	    		p.getNumero_serie(),
    	    		p.getNb_pci().toString(),
    	    		p.getNb_agp().toString(),
    	    		p.getNb_usb().toString()	
    	    };
    	    System.out.println(p.getNb_usb().toString());
    	    
    	    model.addRow(ligne);
    	 }
    	jTablePc.setModel(model);
    	
    	//data=dataPC;
    	//jTablePc.setModel(getModelPc());
    	
    	
    	
    }
	 
	 
	 
	 public TableModel getModelPc()
	 {
		 return new ModelJTable(data,titrePc);
	 
	 };
	 
	 
	 
// <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
	private void initComponents() {
		jLabel1 = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jTextid_pc = new javax.swing.JTextField();
		jTextN_serie = new javax.swing.JTextField();
		jTextmarque = new javax.swing.JTextField();
		jTextmodèle = new javax.swing.JTextField();
		jLabel10 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jTextnb_PCI = new javax.swing.JTextField();
		jTextnb_AGP = new javax.swing.JTextField();
		jTextnb_USB = new javax.swing.JTextField();
		jPanel9 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jTextTypelieu = new javax.swing.JTextField();
		jTextLieu = new javax.swing.JTextField();
		jLabel13 = new javax.swing.JLabel();
		jcardid_lieu = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jTextDurée = new javax.swing.JTextField();
		jPanel6 = new javax.swing.JPanel();
		jDatedebut = new JXDatePicker();
		jPanel4 = new javax.swing.JPanel();
		jbtSupprimer = new javax.swing.JButton();
		jbtModifier = new javax.swing.JButton();
		jbtAjouter = new javax.swing.JButton();
		Peripherique = new javax.swing.JButton();
		jButtonAnnuler = new javax.swing.JButton();
		jButtonLogiciels = new javax.swing.JButton();
		jBRechercher = new javax.swing.JButton();
		jBIntervention = new javax.swing.JButton();
		jPanel5 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
//		jTablePc = new javax.swing.JTable();

		setMaximumSize(new java.awt.Dimension(20000, 20000));
		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("PC");

		jPanel1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Propri\u00e9t\u00e9s"));
		jLabel2.setText("id Pc");

		jLabel3.setText("Marque");

		jLabel4.setText("Mod\u00e8le");

		jLabel5.setText("Num\u00e9ro de s\u00e9rie");

		jTextid_pc.setEnabled(false);

		jTextN_serie.setEnabled(false);

		jTextmarque.setEnabled(false);

		jTextmodèle.setEnabled(false);

		jLabel10.setText("Nombre de slot PCI");

		jLabel11.setText("Nombre de slot AGP");

		jLabel12.setText("Nombre de port USB");

		jTextnb_PCI.setEnabled(false);

		jTextnb_AGP.setEnabled(false);

		jTextnb_USB.setEnabled(false);

		jPanel2
				.setBorder(javax.swing.BorderFactory
						.createTitledBorder("Local"));
		jLabel6.setText("Type lieu");

		jLabel7.setText("Lieu");

		jTextTypelieu.setEnabled(false);

		jTextLieu.setEnabled(false);

		jLabel13.setText("id Lieu");

		org.jdesktop.layout.GroupLayout jcardid_lieuLayout = new org.jdesktop.layout.GroupLayout(
				jcardid_lieu);
		jcardid_lieu.setLayout(jcardid_lieuLayout);
		jcardid_lieuLayout.setHorizontalGroup(jcardid_lieuLayout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(0, 144, Short.MAX_VALUE));
		jcardid_lieuLayout.setVerticalGroup(jcardid_lieuLayout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(0, 18, Short.MAX_VALUE));

		org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel2Layout
										.createSequentialGroup()
										.add(
												jPanel2Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING,
																false)
														.add(
																jLabel6,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.add(jLabel13)
														.add(
																jLabel7,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.add(60, 60, 60)
										.add(
												jPanel2Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING,
																false)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																jTextTypelieu)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																jTextLieu,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																144,
																Short.MAX_VALUE)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																jcardid_lieu,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jPanel2Layout.linkSize(new java.awt.Component[] { jTextLieu,
				jTextTypelieu, jcardid_lieu },
				org.jdesktop.layout.GroupLayout.HORIZONTAL);

		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												jPanel2Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING)
														.add(jLabel13)
														.add(
																jcardid_lieu,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel2Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jLabel6)
														.add(
																jTextTypelieu,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel2Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jLabel7)
														.add(
																jTextLieu,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))));

		jPanel3.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Garantie"));
		jLabel8.setText("Date d\u00e9but");

		jLabel9.setText("Dur\u00e9e (mois)");

		jTextDurée.setEnabled(false);

		org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(
				jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				jPanel6Layout.createSequentialGroup().add(jDatedebut,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 118,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(26, Short.MAX_VALUE)));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				jPanel6Layout.createSequentialGroup().addContainerGap().add(
						jDatedebut, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						19, Short.MAX_VALUE)));

		org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				org.jdesktop.layout.GroupLayout.TRAILING,
				jPanel3Layout.createSequentialGroup().add(
						jPanel3Layout.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING).add(
								jLabel9).add(jLabel8)).add(42, 42, 42).add(
						jPanel3Layout.createParallelGroup(
								org.jdesktop.layout.GroupLayout.TRAILING).add(
								jPanel6,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).add(jTextDurée,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								145, Short.MAX_VALUE)).addContainerGap()));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel3Layout
										.createSequentialGroup()
										.add(
												jPanel3Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING)
														.add(
																jPanel3Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.add(
																				jLabel8,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				19,
																				Short.MAX_VALUE))
														.add(
																jPanel6,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel3Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(
																jLabel9,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																25,
																Short.MAX_VALUE)
														.add(
																jTextDurée,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.add(15, 15, 15)));

		org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(
				jPanel9);
		jPanel9.setLayout(jPanel9Layout);
		jPanel9Layout
				.setHorizontalGroup(jPanel9Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel9Layout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												jPanel9Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING,
																false)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																jPanel3, 0,
																273,
																Short.MAX_VALUE)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																jPanel2,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.add(42, 42, 42)));
		jPanel9Layout.setVerticalGroup(jPanel9Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				jPanel9Layout.createSequentialGroup().add(jPanel2,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jPanel3,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								92,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(jLabel5).add(
																jLabel4).add(
																jLabel3).add(
																jLabel2).add(
																jLabel12).add(
																jLabel11).add(
																jLabel10))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING,
																false)
														.add(jTextnb_USB)
														.add(jTextnb_AGP)
														.add(jTextnb_PCI)
														.add(
																jTextid_pc,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																122,
																Short.MAX_VALUE)
														.add(
																jTextmarque,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																122,
																Short.MAX_VALUE)
														.add(jTextN_serie)
														.add(
																jTextmodèle,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																122,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED,
												302, Short.MAX_VALUE)
										.add(
												jPanel9,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												287,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.add(21, 21, 21)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																jPanel9,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																jPanel1Layout
																		.createSequentialGroup()
																		.add(
																				jPanel1Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.BASELINE)
																						.add(
																								jTextid_pc,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																						.add(
																								jLabel2))
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jPanel1Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.BASELINE)
																						.add(
																								jLabel3)
																						.add(
																								jTextmarque,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jPanel1Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.BASELINE)
																						.add(
																								jLabel4)
																						.add(
																								jTextmodèle,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jPanel1Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.BASELINE)
																						.add(
																								jLabel5)
																						.add(
																								jTextN_serie,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jPanel1Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.BASELINE)
																						.add(
																								jLabel10)
																						.add(
																								jTextnb_PCI,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jPanel1Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.BASELINE)
																						.add(
																								jLabel11)
																						.add(
																								jTextnb_AGP,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jPanel1Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.BASELINE)
																						.add(
																								jLabel12)
																						.add(
																								jTextnb_USB,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
										.addContainerGap()));

		jbtSupprimer.setText("Supprimer");
		jbtSupprimer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtSupprimerActionPerformed(evt);
			}
		});

		jbtModifier.setText("Modifier");
		jbtModifier.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtModifierActionPerformed(evt);
			}
		});

		jbtAjouter.setText("Ajouter");
		jbtAjouter.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtAjouterActionPerformed(evt);
			}
		});

		Peripherique.setText("Peripheriques");
		Peripherique.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				PeripheriqueActionPerformed(evt);
			}
		});

		jButtonAnnuler.setText("Annuler");
		jButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonAnnulerActionPerformed(evt);
			}
		});

		jButtonLogiciels.setText("Logiciels");
		jButtonLogiciels.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonLogicielsActionPerformed(evt);
			}
		});

		jBRechercher.setText("Rechrcher");
		jBRechercher.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBRechercherActionPerformed(evt);
			}
		});

		jBIntervention.setText("Interventions");
		jBIntervention.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBInterventionActionPerformed(evt);
			}
		});

		org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(
				jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				jPanel4Layout.createSequentialGroup().add(jbtSupprimer,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 81,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jbtModifier,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								71,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jbtAjouter).addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jButtonAnnuler).addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jBRechercher,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								94,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED, 37,
								Short.MAX_VALUE).add(jBIntervention)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jButtonLogiciels).addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								Peripherique).addContainerGap()));

		jPanel4Layout.linkSize(new java.awt.Component[] { Peripherique,
				jBIntervention, jBRechercher, jButtonAnnuler, jButtonLogiciels,
				jbtAjouter, jbtModifier, jbtSupprimer },
				org.jdesktop.layout.GroupLayout.HORIZONTAL);

		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				jPanel4Layout.createParallelGroup(
						org.jdesktop.layout.GroupLayout.BASELINE).add(
						jbtModifier).add(jbtSupprimer).add(jbtAjouter).add(
						Peripherique).add(jButtonAnnuler).add(jButtonLogiciels)
						.add(jBRechercher).add(jBIntervention)));

		jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("PC"));
		jTablePc.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null } },
				new String[] { "ID Pc", "Marque", "Modèle", "Numéro de série",
						"Nb slot PCI", "Nb slot AGP", "Nb slot USB" }));
		jScrollPane1.setViewportView(jTablePc);

		org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(
				jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jScrollPane1,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 898,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				jPanel5Layout.createSequentialGroup().add(jScrollPane1,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 157,
						Short.MAX_VALUE).addContainerGap()));

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING,
																false)
														.add(
																jLabel1,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																jPanel1,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																jPanel4,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																jPanel5,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																859,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup().addContainerGap().add(jLabel1,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 22,
						Short.MAX_VALUE).add(57, 57, 57).add(jPanel5,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jPanel4,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jPanel1,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
	}// </editor-fold>//GEN-END:initComponents

	private void jBInterventionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBInterventionActionPerformed
	// TODO add your handling code here:
	}//GEN-LAST:event_jBInterventionActionPerformed

	private void jBRechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRechercherActionPerformed
		String requete ="SELECT id_pc FROM pc WHERE ";
	int cmparg=0;
			
			if(jTextmarque.getText().toString().length()>0)	
				{	if(cmparg!=0)requete+=" AND ";
					requete+=" marque LIKE '%"+jTextmarque.getText().toString()+"%'";
					cmparg++;
					
				}
			if(jTextmodèle.getText().toString().length()>0)	
				{	if(cmparg!=0)requete+=" AND ";
					requete+=" modele LIKE '%"+jTextmodèle.getText().toString()+"%'";
					cmparg++;

				}
			if(jTextN_serie.getText().toString().length()>0)	
				{	if(cmparg!=0)requete+=" AND ";
					requete+=" numero_serie LIKE '%"+jTextN_serie.getText().toString()+"%'";
					cmparg++;

				}
			
			try{if(jTextnb_PCI.getText().toString().length()>0)	
				{	System.out.println("onvatriuvé"+Integer.parseInt(jTextnb_PCI.getText().toString())+"lerreur"+cmparg+"\n");	
					
					if(cmparg!=0)requete+=" AND ";
				
					
					requete+=" nb_pci ="+Integer.parseInt(jTextnb_PCI.getText().toString());
					cmparg++;
					}}catch(NumberFormatException e)
						{e.printStackTrace();
						JXErrorDialog.showDialog(null,"Veuillez remplir les champs correctements !!!", "Erreur lors de saisie des données  !!!", "\n\n\tLes champs nb de slot agp,pci et usb ainsi que Durée du garantie  prennent des valeurs numeriques \n et non pas des chaines de caractéres !!!\n\n  ");	
						jTextnb_PCI.setText(null);
						}
			
			try{if(jTextnb_AGP.getText().toString().length()>0)	
			{	System.out.println("onvatriuvé"+Integer.parseInt(jTextnb_AGP.getText().toString())+"lerreur"+cmparg+"\n");
				if(cmparg!=0)requete+=" AND ";
			
				
					requete+=" nb_agp ="+Integer.parseInt(jTextnb_AGP.getText().toString())+"";
					cmparg++;
				}}catch(Exception e)
						{e.printStackTrace();
						JXErrorDialog.showDialog(null,"Veuillez remplir les champs correctements !!!", "Erreur lors de saisie des données  !!!", "\n\n\tLes champs nb de slot agp,pci et usb ainsi que Durée du garantie  prennent des valeurs numeriques \n et non pas des chaines de caractéres !!!\n\n  ");	
						jTextnb_AGP.setText(null);
						}
			
			try{if(jTextnb_USB.getText().toString().length()>0)	
			{	System.out.println("onvatriuvé"+Integer.parseInt(jTextnb_USB.getText().toString())+"lerreur"+cmparg+"\n");	
				if(cmparg!=0)requete+=" AND ";
			
			
					requete+=" nb_usb ="+Integer.parseInt(jTextnb_USB.getText().toString())+"";
					cmparg++;
			}	}catch(Exception e)
						{
						JXErrorDialog.showDialog(null,"Veuillez remplir les champs correctements !!!", "Erreur lors de saisie des données  !!!", "\n\n\tLes champs nb de slot agp,pci et usb ainsi que Durée du garantie  prennent des valeurs numeriques \n et non pas des chaines de caractéres !!!\n\n  ");	
						jTextnb_USB.setText(null);
						}
			
			try{
			if(jTextDurée.getText().toString().length()>0)	
			{	System.out.println("onvatriuvé"+Integer.parseInt(jTextnb_PCI.getText().toString())+"lerreur"+cmparg+"\n");
				if(cmparg!=0)requete+=" AND ";
				
			
					requete+=" duree ="+Integer.parseInt(jTextDurée.getText().toString())+"";
					cmparg++;
			}}catch(Exception e)
						{System.out.println("Le problem vient de jTextDurée");
						JXErrorDialog.showDialog(null,"Veuillez remplir les champs correctements !!!", "Erreur lors de saisie des données  !!!", "\n\n\tLes champs nb de slot agp,pci et usb ainsi que Durée du garantie  prennent des valeurs numeriques \n et non pas des chaines de caractéres !!!\n\n  ");	
						jTextDurée.setText(null);
						}
//			jDatedebut.is
			
	java.util.Date a = new java.util.Date();
	System.out.print("le contenu de jdatedebut est == "+jDatedebut.getDate().toString());
	
	System.out.print("\n\n\n jDatedebut.getDate().getYear()="+jDatedebut.getDate().getYear()+"		a.getYear()  = "+a.getYear());
	System.out.print("\n jDatedebut.getDate().getMonth()="+jDatedebut.getDate().getMonth()+"		a.getMonth()  = "+a.getMonth());
	System.out.println("\n jDatedebut.getDate().getDay()="+jDatedebut.getDate().getDay()+"		a.getDay()  = "+a.getDay());
	
	if(jDatedebut.getDate().getYear()!= a.getYear() || jDatedebut.getDate().getMonth()!= a.getMonth() || jDatedebut.getDate().getDay()!= a.getDay())	
			{	System.out.print("SA passe le test ");
				if(cmparg!=0)requete+=" AND ";
				Date b = new Date(jDatedebut.getDateInMillis());
					requete+=" date_debut =#"+b.toString()+"#";
				System.out.println(requete);
					cmparg++;
			}	
	
			try{
			if(cmbIdLieu.getSelectedItem().toString().length()>0 )	
			{System.out.println("onvatriuvé"+jTextnb_PCI.getText().toString()+"lerreur"+cmparg+"\n");
				if(cmparg!=0)requete+=" AND ";
			
			  requete+=" id_lieu = "+Integer.parseInt(cmbIdLieu.getSelectedItem().toString());
		      cmparg++;
			
			}
			}catch(Exception e)
				{System.out.println("Le problem vient de combobox id lieu son contenu est :"+cmbIdLieu.getSelectedItem().toString()+"___");
				JXErrorDialog.showDialog(null,"Veuillez remplir les champs correctements !!!", "Erreur lors de saisie des données  !!!", "\n\n\tLes champs nb de slot agp,pci et usb ainsi que Durée du garantie  prennent des valeurs numeriques \n et non pas des chaines de caractéres !!!\n\n  ");	
				cmbIdLieu.setSelectedItem("");
				}
				
				
				System.out.println("\n\n"+requete);
				System.out.print("Execution de la requette a "+cmparg+" Critéres ");
				if(cmparg>0)
				{System.out.println("----------->"+cmparg);
					int c=0;
	
					while(c<v.size())
						{model.removeRow(0);c++;}

					v.removeAllElements();
	
					Gbd gbd = new Gbd();
					gbd.connecter();

					try{
						ResultSet r =gbd.execQuery(requete);
						System.out.println("le nombre de resultat ===> "+gbd.count());
						if(gbd.count()==0)JOptionPane.showConfirmDialog(this,"Pas de resultat pour cette recherche  !!!","Rechrche PC",JOptionPane.DEFAULT_OPTION);
							
						while (r.next())
							{
							Pc p = new Pc();
							p.construirePc((r.getInt("id_pc")));
							v.add(p);
							}
						}catch (SQLException e)
						{
							e.printStackTrace();
							if(gbd.count()==0)JOptionPane.showConfirmDialog(this,"Pas de Resultat pour cette rechrche !!!","Rechrche PC",JOptionPane.DEFAULT_OPTION);
							System.out.println(gbd.count());
						}
	
						

				    	
				    	Object [][] dataPC = new Object[v.size()][7];
				    	
				    	
				    	for (int i=0;i<v.size();i++)
				    	{
				    		Pc p =(Pc)v.elementAt(i);
				    		dataPC[i][0] = p.getId_pc().toString();
				    		dataPC[i][1] = p.getMarque();
				    		dataPC[i][2] = p.getModele();
				    		dataPC[i][3] = p.getNumero_serie();      
				    		dataPC[i][4] = p.getNb_pci().toString();
				    		dataPC[i][5] = p.getNb_agp().toString();
				    	    dataPC[i][6] = p.getNb_usb().toString();
				    	    
				    	    String ligne[]={p.getId_pc().toString(),
				    	    		p.getMarque(),
				    	    		p.getModele(),
				    	    		p.getNumero_serie(),
				    	    		p.getNb_pci().toString(),
				    	    		p.getNb_agp().toString(),
				    	    		p.getNb_usb().toString()	
				    	    };
				    	    System.out.println(p.getNb_usb().toString());
				    	    
				    	    model.addRow(ligne);
				    	 }
				    	jTablePc.setModel(model);	
				}
				else
				{
					JOptionPane.showConfirmDialog(this,"Il faut donner des arguments pour cette recherche  !!!","Rechrche PC ",JOptionPane.DEFAULT_OPTION);
				}	
						
						
					
	
	}//GEN-LAST:event_jBRechercherActionPerformed

	private void jButtonLogicielsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogicielsActionPerformed
	// TODO add your handling code here:
		if(ajout == false && modif == false)
		{
				int ligne = jTablePc.getSelectedRow();
				if ( ligne >= 0)
				{p1 =(Pc)v.elementAt(ligne);
				System.out.print(ligne);

				
				Panel_logicielle p = new Panel_logicielle(m , p1.getId_pc() );
								
				
				periphdialog = new JXDialog(m,p);
				
				
				periphdialog.setTitle("Les Logiciels du Pc ");
//				JDialog periphdialog = new JDialog(m,"Peripheriques pour ce Pc",true);
//			periphdialog.add(p);
				periphdialog.setVisible(true);
				periphdialog.pack();
				}
				else 
				{
					JOptionPane.showConfirmDialog(null, "Selectionner un Pc pour voir ces logiciels ","Logiciels", JOptionPane.DEFAULT_OPTION);
					
				}
		}
		else if(ajout==true)
		{JOptionPane.showConfirmDialog(null, " Veuillez completer le processus de l ajout !!!","Logiciels", JOptionPane.DEFAULT_OPTION);
			}
		else if(modif==true)
			JOptionPane.showConfirmDialog(null, " Veuillez completer le processus de modification !!!","Logiciels", JOptionPane.DEFAULT_OPTION);
	
	}//GEN-LAST:event_jButtonLogicielsActionPerformed

	private void jButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnnulerActionPerformed
	// TODO add your handling code here:
///	jPanel7.add(jDatedebut);
		modif = false;
		jbtModifier.setText("Modifier");
		ajout=false;
		jbtAjouter.setText("Ajouter");
		
		jTextid_pc.setText("");
    	txtIdLieu.setText("");
    	jTextmarque.setText("");
    	jTextmodèle.setText("");
    	jTextN_serie.setText("");
    	jTextTypelieu.setText("");
    	jTextLieu.setText("");
    	jDatedebut.setDate(new java.util.Date());
    	jTextDurée.setText("");
    	jTextnb_PCI.setText("");
		jTextnb_AGP.setText("");
		jTextnb_USB.setText("");
		cmbIdLieu.setSelectedItem("");
		
		jDatedebut.setEnabled(false);
		jTextDurée.setEnabled(false);
		jTextmarque.setEnabled(false);
		jTextmodèle.setEnabled(false);
		jTextN_serie.setEnabled(false);
		jTextnb_AGP.setEnabled(false);
		jTextnb_PCI.setEnabled(false);
		jTextnb_USB.setEnabled(false);	

		((CardLayout) jcardid_lieu.getLayout()).show(jcardid_lieu,s1);

		jTablePc.clearSelection();
			if(code==-2){
								int c=0;
								
								while(c<v.size())
								{model.removeRow(0);c++;}
								v.removeAllElements();


								cmbIdLieu.removeAllItems();
								cmbIdLieu.addItem("");
								cmbIdLieu.addItem("Choisir un Lieu");
								cmbIdLieu.setEnabled(true);
														
								((CardLayout) jcardid_lieu.getLayout()).show(jcardid_lieu,s2);
								
								jDatedebut.setEnabled(true);
								jTextDurée.setEnabled(true);
								jTextmarque.setEnabled(true);
								jTextmodèle.setEnabled(true);
								jTextN_serie.setEnabled(true);
								jTextnb_AGP.setEnabled(true);
								jTextnb_PCI.setEnabled(true);
								jTextnb_USB.setEnabled(true);
			
			
			
						}
	}//GEN-LAST:event_jButtonAnnulerActionPerformed

	private void jbtModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtModifierActionPerformed
	// TODO add your handling code here:
		if(jTablePc.getSelectedRowCount()!=0 && ajout== false)	
		{
			if(modif == false)
			{
				modif=true;
				jbtModifier.setText("Valider");
				//				
				jDatedebut.setEnabled(true);
				jTextDurée.setEnabled(true);
				jTextmarque.setEnabled(true);
				jTextmodèle.setEnabled(true);
				jTextN_serie.setEnabled(true);
				jTextnb_AGP.setEnabled(true);
				jTextnb_PCI.setEnabled(true);
				jTextnb_USB.setEnabled(true);
				cmbIdLieu.removeAllItems();
				cmbIdLieu.addItem(txtIdLieu.getText());
				cmbIdLieu.addItem("Choisir un Lieu");
				//cmbIdLieu.setSelectedItem(txtIdLieu.getText());
				System.out.println("Le contenu de la txtIDLieu est "+txtIdLieu.getText());
				cmbIdLieu.setEnabled(true);
				((CardLayout) jcardid_lieu.getLayout()).show(jcardid_lieu,s2);
				//jTablePc.disable();
				
			}else 
			{
				modif = false;
				jbtModifier.setText("Modifier");
				p1 =new Pc();
				
				int indice =jTablePc.getSelectedRow();
				p1 = (Pc) v.elementAt(indice);
				
				try{	  
////////////////////////////////////////	/			
				//importChamp( p1);

					p1.setMarque(jTextmarque.getText());
					p1.setModele(jTextmodèle.getText());
					p1.setNumero_serie(jTextN_serie.getText());
			try{		p1.setNb_agp(Integer.parseInt(jTextnb_AGP.getText()));
			}catch(NumberFormatException e){
				p1.setNb_agp(null) ;}
			try{
			p1.setNb_pci(Integer.parseInt(jTextnb_PCI.getText()));
			}catch(NumberFormatException e){
				p1.setNb_pci(null) ;}
			try{
			p1.setNb_usb(Integer.parseInt(jTextnb_USB.getText()));
			}catch(NumberFormatException e){
				p1.setNb_usb(null) ;}
							
				Date temp = new Date(jDatedebut.getDateInMillis());
				
				
				p1.setDate_debut(temp);
				try{	p1.setDuree(Integer.parseInt(jTextDurée.getText().toString()));
				
					}catch(NumberFormatException e){
					p1.setNb_usb(null) ;}	
				
				Lieu l = new Lieu();
				l.construireLieu(Integer.parseInt(cmbIdLieu.getSelectedItem().toString()));			
				p1.setLieu(l);
				
				
				jDatedebut.setEnabled(false);
				jTextDurée.setEnabled(false);
				jTextmarque.setEnabled(false);
				jTextmodèle.setEnabled(false);
				jTextN_serie.setEnabled(false);
				jTextnb_AGP.setEnabled(false);
				jTextnb_PCI.setEnabled(false);
				jTextnb_USB.setEnabled(false);
				
				((CardLayout) jcardid_lieu.getLayout()).show(jcardid_lieu,s1);
//				p1.insererPc();System.out.print("Inserer Pc ");
				
			
					
					int 	reponse = JOptionPane.showConfirmDialog(this,"Etes vous sur de vouloir modifier ce PC  ?","Modification",JOptionPane.YES_NO_OPTION);
						if(reponse==0)
							{	
							int nb = p1.modifierPc();
							System.out.println("le resultat de la imodif est ="+nb);
						
						
							if (nb==1)
							{v.setElementAt(p1, indice);
							
							String ligne2[]={p1.getId_pc().toString(),
									p1.getMarque(),
				    	    		p1.getModele(),
				    	    		p1.getNumero_serie(),
				    	    		p1.getNb_agp().toString(),
				    	    		p1.getNb_agp().toString(),
				    	    		p1.getNb_usb().toString()	
								};
					
							model.setValueAt(p1.getId_pc().toString(), indice, 0);
							model.setValueAt(p1.getMarque(), indice, 1);
							model.setValueAt(p1.getModele(), indice, 2);
							model.setValueAt(p1.getNumero_serie(), indice, 3);
							model.setValueAt(p1.getNb_agp().toString(), indice, 4);
							model.setValueAt(p1.getNb_agp().toString(), indice, 5);
							model.setValueAt(p1.getNb_usb().toString(), indice, 6);
						
						
							JOptionPane.showConfirmDialog(this, "Le PC a été Modifier avec succès!","Ajout",JOptionPane.DEFAULT_OPTION);
							//model.addRow(ligne);
							//	jTablePc.enable();
							}}
							else
							{
									JOptionPane.showConfirmDialog(null, "Le Logiciel n'a pas été modifier !!","Ajout",JOptionPane.WARNING_MESSAGE);
									//jTablePc.enable();
							}
					
					}catch (Exception e)
					{
						e.printStackTrace();
						//JXErrorDialog.showDialog(null,"DataBase ERROR !!!",e);
						JXErrorDialog.showDialog(null,"Veuillez remplir les champs correctements !!!", "Erreur lors de saisie des données  !!!", "Les champs doivent être saisies avec soin  !!!\n  ");
					}
			}	}	
			else if(ajout==true)
				JOptionPane.showConfirmDialog(this, "Veuillez completer le processus de l ajout !!!", "Modification",JOptionPane.DEFAULT_OPTION);
			
			
			else
				JOptionPane.showConfirmDialog(this, "Il faut selectionner un PC afin de le modifier", "Modification",JOptionPane.DEFAULT_OPTION);
				

			
	
	}//GEN-LAST:event_jbtModifierActionPerformed

	private void PeripheriqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PeripheriqueActionPerformed
	// TODO add your handling code here:
/////************************************////////////////////////	
if(ajout == false && modif == false)
{
		int ligne = jTablePc.getSelectedRow();
		if ( ligne >= 0)
		{p1 =(Pc)v.elementAt(ligne);
		System.out.print(ligne);
		Panel_peripherique p = new Panel_peripherique(m,p1.getId_pc());
		
		JXDialog periphdialog = new JXDialog(m,p);
		periphdialog.setTitle("Les Peripheriques du Pc ");
//		JDialog periphdialog = new JDialog(m,"Peripheriques pour ce Pc",true);
//	periphdialog.add(p);
		periphdialog.setVisible(true);
		periphdialog.pack();
		}
		else 
		{
			JOptionPane.showConfirmDialog(null, "Selectionner un Pc pour voir ces peripheriques ","Peripheriques", JOptionPane.DEFAULT_OPTION);
			
		}
}
else if(ajout==true)
{JOptionPane.showConfirmDialog(null, " Veuillez completer le processus de l ajout !!!","Peripheriques", JOptionPane.DEFAULT_OPTION);
	}
else if(modif==true)
	JOptionPane.showConfirmDialog(null, " Veuillez completer le processus de modification !!!","Peripheriques", JOptionPane.DEFAULT_OPTION);
	}//GEN-LAST:event_PeripheriqueActionPerformed


	
	void importChamp(Pc p1)
	{
	
//	p1.setMarque(jTextmarque.getText());
//	p1.setModele(jTextmodèle.getText());
//	p1.setNumero_serie(jTextN_serie.getText());
//	p1.setNb_agp(Integer.parseInt(jTextnb_AGP.getText()));
//	p1.setNb_pci(Integer.parseInt(jTextnb_PCI.getText()));
//	p1.setNb_usb(Integer.parseInt(jTextnb_USB.getText()));
//	
//	java.util.Date date = new java.util.Date();
//	java.sql.Date dateSQL = new java.sql.Date(date.getTime());
//	
//	p1.setDate_debut((Date) jDatedebut.getDate());
//	p1.setDuree(jTextDurée.getText());
										
	
	}	
	
	
	
	
	
	public void remplirChamps(Pc p1)
   {
	   	
    	jTextid_pc.setText(String.valueOf(p1.getId_pc()));
    	jTextmarque.setText(p1.getMarque());
    	jTextmodèle.setText(p1.getModele());
    	jTextN_serie.setText(p1.getNumero_serie());
    	txtIdLieu.setText(String.valueOf(p1.getLieu().getId_lieu()));
    	
    	jTextTypelieu.setText(p1.getLieu().getTypeLieu().getNom_type());
    	jTextLieu.setText(p1.getLieu().getNom());
    	jDatedebut.setDate(p1.getDate_debut());
    	jTextDurée.setText(String.valueOf(p1.getDuree()));
    	jTextnb_PCI.setText(p1.getNb_pci().toString());
		jTextnb_AGP.setText(p1.getNb_agp().toString());
		jTextnb_USB.setText(p1.getNb_usb().toString());

   } 

   public TableModel getModel()
   {
	   
	   return new ModelJTable(test,titre);
   
   }	
   
   
   
	private void cmbIdLieuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPcItemStateChanged
		
		
		if (cmbIdLieu.getItemCount()!=0)
			if (evt.getStateChange()==1)
				if (cmbIdLieu.getSelectedItem().toString()=="Choisir un Lieu")
				{	
					
					Dialog_choixLieu d = new Dialog_choixLieu(m,true,2);
					d.pack();
					d.setVisible(true);
					d.setLocationRelativeTo(this);
					
				}
	}

	private void jbtSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSupprimerActionPerformed
	
		int reponse=-1;
		if (jTablePc.getSelectedRowCount()!=0)
		{	// 0 : OUI
			// 1 : NON
			reponse = JOptionPane.showConfirmDialog(this,"Etes vous sur de vouloir supprimer ce Pc ?","Suppression",JOptionPane.YES_NO_OPTION);
			if (reponse==0)
			{
				int id = Integer.parseInt(jTablePc.getValueAt(jTablePc.getSelectedRow(), 0).toString());
				
				Pc p = new Pc();
				
				p.setId_pc(id);
				int res = p.supprimerPc();
				System.out.println("id = "+id+"  res="+res);
				//int res=0;
				if (res==1)
				{
					v.remove(jTablePc.getSelectedRow());
					model.removeRow(jTablePc.getSelectedRow());
					JOptionPane.showConfirmDialog(this, "Pc supprimé avec succès","Suppression",JOptionPane.DEFAULT_OPTION);
					//jTable1.setModel(new DefaultTableModel());
					
					//Vider la JTable des proptietes
					
					
						
					//Restaurer les combobox a la place des TextFields
//					((CardLayout) jPanel7.getLayout()).show(jPanel7,s1);
//					((CardLayout) cardType.getLayout()).show(cardType,s3);
//					((CardLayout) cardSlot.getLayout()).show(cardSlot,s5);
//					((CardLayout) cardIdPc.getLayout()).show(cardIdPc,s7);
//					
					//Effacer le contenu des TextFields
		jTextid_pc.setText("");
    	txtIdLieu.setText("");
    	jTextmarque.setText("");
    	jTextmodèle.setText("");
    	jTextN_serie.setText("");
    	jTextTypelieu.setText("");
    	jTextLieu.setText("");
    	jDatedebut.setDate(new java.util.Date());
    	jTextDurée.setText("");
    	jTextnb_PCI.setText("");
		jTextnb_AGP.setText("");
		jTextnb_USB.setText("");
					
				}}
				else
				{
					JOptionPane.showConfirmDialog(null, "Le Pc n'a pas été supprimé !!","Suppression",JOptionPane.DEFAULT_OPTION);
				}
				
			
		}
		else
		{
			JOptionPane.showConfirmDialog(null, "Veuillez selectionner un PC !! ","Suppression",JOptionPane.DEFAULT_OPTION);
		}
	
	
	}//GEN-LAST:event_jbtSupprimerActionPerformed

	private void jbtAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAjouterActionPerformed
	//GEN-FIRST:event_jbtAjouterActionPerformed
	/////////////////////////////////////////////
		if(modif==false)
		{
			if (ajout==false)
		
		{
			ajout=true;
			jbtAjouter.setText("Valider");			
					
			cmbIdLieu.removeAllItems();
			cmbIdLieu.addItem("");
			cmbIdLieu.addItem("Choisir un Lieu");
			cmbIdLieu.setEnabled(true);
									
			((CardLayout) jcardid_lieu.getLayout()).show(jcardid_lieu,s2);
			
			jDatedebut.setEnabled(true);
			jTextDurée.setEnabled(true);
			jTextmarque.setEnabled(true);
			jTextmodèle.setEnabled(true);
			jTextN_serie.setEnabled(true);
			jTextnb_AGP.setEnabled(true);
			jTextnb_PCI.setEnabled(true);
			jTextnb_USB.setEnabled(true);
			
			//System.out.println(jDatedebut.getDate().toString());
//::::::::::::::::::::::::::::::::::::::::::************************::::::::::::::::::::::::::::://////////	
		///Collecte des different Marques de pc 
//			Gbd gbd = new Gbd();
//			gbd.connecter();
//			String req ="SELECT DISTINCT marque FROM PC";
//			ResultSet r =gbd.execQuery(req);
////			String req2 ="SELECT count(DISTINCT) marque FROM PC";
////			ResultSet r2 =gbd.execQuery(req2);
//			int taille=0;
	//////////////////////////////////////////////////pour la combo box des noms ! a voir
			
//			Vector<String> v = new Vector();
//			//int i =0;
//			try {
//				while(r.next())
//				{
//					v.add(r.getString("marque"));
//					taille++;
//				}
//				gbd.deconnecter();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			System.out.println(taille);
//			String [] nom =new String [taille];
//			for(int i=0 ;i<taille ;i++)
//				{nom[i]=(String) v.elementAt(i);
//				System.out.print(nom[i]);
//				}
			
//		ModelJTable m = new ModelJTable(data,titre);
//		m.modifier=true;
//		jTable1.setModel(m);
//		b=true;
		
		
		}
		else
		{
			ajout=false;
			jbtAjouter.setText("Ajouter");
			
			p1 =new Pc();
			
	  
	  
/////////////////////////////////////////			
			//importChamp( p1);
		
			p1.setMarque(jTextmarque.getText());
			p1.setModele(jTextmodèle.getText());
			p1.setNumero_serie(jTextN_serie.getText());
	try{		p1.setNb_agp(Integer.parseInt(jTextnb_AGP.getText()));
	}catch(NumberFormatException e){
		p1.setNb_agp(null) ;}
	try{
	p1.setNb_pci(Integer.parseInt(jTextnb_PCI.getText()));
	}catch(NumberFormatException e){
		p1.setNb_pci(null) ;}
	try{
	p1.setNb_usb(Integer.parseInt(jTextnb_USB.getText()));
	}catch(NumberFormatException e){
		p1.setNb_usb(null) ;}
			
	
	Date temp = new Date(jDatedebut.getDateInMillis());
			
			
			p1.setDate_debut(temp);
			try{		p1.setDuree(Integer.parseInt(jTextDurée.getText().toString()));
			}catch(NumberFormatException e){
				p1.setNb_usb(null) ;}
			
			try{			
			
			
			
			Lieu l = new Lieu();
			l.construireLieu(Integer.parseInt(cmbIdLieu.getSelectedItem().toString()));			
			p1.setLieu(l);
}catch(Exception e )
{JXErrorDialog.showDialog(null,"Veuillez remplir les champs correctements !!!", "Erreur lors de saisie des données  !!!", "\n\n Il ne faut pas oublier de choisir un  Lieu pour ce Pc   !!! ");

jDatedebut.setEnabled(false);
jTextDurée.setEnabled(false);
jTextmarque.setEnabled(false);
jTextmodèle.setEnabled(false);
jTextN_serie.setEnabled(false);
jTextnb_AGP.setEnabled(false);
jTextnb_PCI.setEnabled(false);
jTextnb_USB.setEnabled(false);	

((CardLayout) jcardid_lieu.getLayout()).show(jcardid_lieu,s1);

}
			
			int nb=p1.insererPc();System.out.print("Inserer Pc ");
			v.add(p1);
			
			
			
			if (nb==1)
			{
				String requete = "SELECT max(id_pc) AS id FROM pc";
				Gbd gbd = new Gbd();
				gbd.connecter();
				ResultSet r = gbd.execQuery(requete);
			
			int id_pc=0;
			try
			{
				r.next();
				id_pc = r.getInt("id");
				
				gbd.deconnecter();
			}catch(Exception e)
			{
				System.out.println(e.toString());
			}
			
			String [] ligne={String.valueOf(id_pc),
					p1.getMarque(),
    	    		p1.getModele(),
    	    		p1.getNumero_serie(),
    	    		p1.getNb_pci().toString(),
    	    		p1.getNb_agp().toString(),
    	    		p1.getNb_usb().toString()	
    	    };
			
			JOptionPane.showConfirmDialog(this, "Le Pc a été ajouté avec succès!","Ajout",JOptionPane.DEFAULT_OPTION);
			model.addRow(ligne);
			jTablePc.setRowSelectionInterval(jTablePc.getRowCount()-1, jTablePc.getRowCount()-1);
			
			
			//p.setLieu(l);
		
			jDatedebut.setEnabled(false);
			jTextDurée.setEnabled(false);
			jTextmarque.setEnabled(false);
			jTextmodèle.setEnabled(false);
			jTextN_serie.setEnabled(false);
			jTextnb_AGP.setEnabled(false);
			jTextnb_PCI.setEnabled(false);
			jTextnb_USB.setEnabled(false);
			////REQUETTE POUR LE LIEU DU PC §§!!!
			
			((CardLayout) jcardid_lieu.getLayout()).show(jcardid_lieu,s1);
			}
		}	}
	
	else{
				JOptionPane.showConfirmDialog(this, "Veuillez completer le processus de modification !!!", "Ajout",JOptionPane.DEFAULT_OPTION);
		}
		
//////////////////////////////////////////////////	
		// TODO add your handling code here:
}//GEN-LAST:event_jbtAjouterActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton Peripherique;

	private javax.swing.JButton jBIntervention;

	private javax.swing.JButton jBRechercher;

	private javax.swing.JButton jButtonAnnuler;

	private javax.swing.JButton jButtonLogiciels;

	private JXDatePicker jDatedebut;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel10;

	private javax.swing.JLabel jLabel11;

	private javax.swing.JLabel jLabel12;

	private javax.swing.JLabel jLabel13;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JLabel jLabel3;

	private javax.swing.JLabel jLabel4;

	private javax.swing.JLabel jLabel5;

	private javax.swing.JLabel jLabel6;

	private javax.swing.JLabel jLabel7;

	private javax.swing.JLabel jLabel8;

	private javax.swing.JLabel jLabel9;

	private javax.swing.JPanel jPanel1;

	private javax.swing.JPanel jPanel2;

	private javax.swing.JPanel jPanel3;

	private javax.swing.JPanel jPanel4;

	private javax.swing.JPanel jPanel5;

	private javax.swing.JPanel jPanel6;

	private javax.swing.JPanel jPanel9;

	private javax.swing.JScrollPane jScrollPane1;

//	private javax.swing.JTable jTablePc;

	private javax.swing.JTextField jTextDurée;

	private javax.swing.JTextField jTextLieu;

	private javax.swing.JTextField jTextN_serie;

	private javax.swing.JTextField jTextTypelieu;

	private javax.swing.JTextField jTextid_pc;

	private javax.swing.JTextField jTextmarque;

	private javax.swing.JTextField jTextmodèle;

	private javax.swing.JTextField jTextnb_AGP;

	private javax.swing.JTextField jTextnb_PCI;

	private javax.swing.JTextField jTextnb_USB;

	private javax.swing.JButton jbtAjouter;

	private javax.swing.JButton jbtModifier;

	private javax.swing.JButton jbtSupprimer;

	private javax.swing.JPanel jcardid_lieu;

	// End of variables declaration//GEN-END:variables





	public JComboBox getCmbIdLieu() {
		return cmbIdLieu;
	}




	public void setCmbIdLieu(JComboBox cmbIdLieu) {
		this.cmbIdLieu = cmbIdLieu;
	}




	public javax.swing.JTextField getJTextLieu() {
		return jTextLieu;
	}




	public void setJTextLieu(javax.swing.JTextField textLieu) {
		jTextLieu = textLieu;
	}




	public javax.swing.JTextField getJTextTypelieu() {
		return jTextTypelieu;
	}




	public void setJTextTypelieu(javax.swing.JTextField textTypelieu) {
		jTextTypelieu = textTypelieu;
	}




	public JXDialog getPeriphdialog() {
		return periphdialog;
	}




	public void setPeriphdialog(JXDialog periphdialog) {
		this.periphdialog = periphdialog;
	}

}
