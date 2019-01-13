/*
 * Panel_logicielle.java
 *
 * Created on __DATE__, __TIME__
 */

package forms;

import java.awt.CardLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXComboBox;
import org.jdesktop.swingx.JXErrorDialog;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.AlternateRowHighlighter;
import org.jdesktop.swingx.decorator.Highlighter;
import org.jdesktop.swingx.decorator.HighlighterPipeline;
import org.jdesktop.swingx.decorator.RolloverHighlighter;

import gestion_materiel.Gbd;
import gestion_materiel.Logiciel;
import gestion_materiel.OS;
import gestion_materiel.Type_logiciel;

/**
 *
 * @author  __USER__
 */
public class Panel_logicielle extends javax.swing.JPanel {

	JXTable jTableL;
	MainForm m;
	int id_pc=-1;
	DefaultTableModel model;
	boolean ajout=false,modif=false;//a=true,
	Vector v = new Vector();
	JComboBox cmbType;
	JComboBox cmbOS;
	Logiciel l ;
	  
	JTextField jTextType = new JTextField("             ");
	JTextField jTextOS = new JTextField("             ");
//    	cmbType = new JComboBox( recupererTypes());
//    	cmbOS = new JComboBox(recupererOS());
	public  String [] a ;//=recupererTypes();
    public String [] b ;//=recupererOS();
     
	String s1 = "cardType_text";
    String s2 = "cardType_cb";
    
    String s3 = "cardOS_text";	
    String s4 = "cardOS_cb";
    
    JList types;
    JXList OSs;
    
    /** Creates new form Panel_logicielle */
    public Panel_logicielle(MainForm m,int id_pc) {
    	this.id_pc=id_pc;
    	
    	
    
    	model = new DefaultTableModel();
    	
    	
    	

    	model.addColumn("Id Logiciel");
    	model.addColumn("Nom ");
    	model.addColumn("Type");
    	
    	model.addColumn("System d exploitation");
    	model.addColumn("Memoire Ram Min");
    	model.addColumn("Memoire Carte Graphique Min");
    	model.addColumn("Espace Disque Min");
    	model.addColumn("Note Config Min");
    	
    	this.m=m;
    	jTableL = new JXTable();
		jTableL.setHighlighters(
        new HighlighterPipeline(new Highlighter[] {AlternateRowHighlighter.quickSilver}));
		jTableL.setRolloverEnabled(true);
		jTableL.getHighlighters().addHighlighter(new RolloverHighlighter(Color.GRAY, Color.WHITE ));
		jTableL.setColumnControlVisible(true);
		//jTableL.setModel(model);
    	
		 
    	
        initComponents();
     //////////////////// ::::::::::::::::::::::::::::::::::::::::::::  
       
        String [] a =recupererTypes();
        String [] b =recupererOS();
        
          if(this.id_pc<0)jbLExistant.setVisible(false);  
          if(id_pc!=-2)jBRechrcher.setVisible(false);
          
        jTextAreaDescription.setEnabled(false);
        jTextNomL.setEnabled(false);
        jTextType.setEnabled(false);
        jTextOS.setEnabled(false);
        jTextespace_d_min.setEnabled(false);
        jTextm_ram_min.setEnabled(false);
        jTextm_g_min.setEnabled(false);
        jTextNote_config.setEnabled(false);
        jTextEditeur.setEnabled(false);
        
        JList types = new JList(a);
        JList OSs = new JList(b);
        jTextType.setColumns(10);
        jTextOS.setColumns(10);
//        Configurator.enableAutoCompletion(types,jTextType);
//	    Configurator.enableAutoCompletion(OSs,jTextOS);
	    
	    
        ////////////////////////////////////////////////////////////////////////////////////
//        recupererTypes();
//        recupererOS();
	      
	   
//	    	cmbType = new JXComboBox();
//	    	cmbOS = new JXComboBox();  
//	    	cmbType.addItem("");
//	      Configurator.enableAutoCompletion(cmbType);
//	      Configurator.enableAutoCompletion(cmbOS);
        CardType.setLayout(new CardLayout());
        CardOS.setLayout(new CardLayout());
        cmbType = new JXComboBox(a);
    	cmbOS = new JXComboBox(b);    
//    	cmbType.setEditable(true);
//		cmbOS.setEditable(true);  
//    	
    	
        CardType.add(s1,jTextType);
        CardType.add(s2,cmbType);
        
        CardOS.add(s3,jTextOS);
        CardOS.add(s4,cmbOS);
        
        
        ((CardLayout) CardType.getLayout()).show(CardType,s1);
        ((CardLayout) CardOS.getLayout()).show(CardOS,s3);
        
        
      if(id_pc!=-2)
        recupererLogiciels();  
      if(id_pc ==-2){
    	  jTextAreaDescription.setEnabled(true);
          jTextNomL.setEnabled(true);
          jTextType.setEnabled(true);
          jTextOS.setEnabled(true);
          jTextespace_d_min.setEnabled(true);
          jTextm_ram_min.setEnabled(true);
          jTextm_g_min.setEnabled(true);
          jTextNote_config.setEnabled(true);
          jTextEditeur.setEnabled(true);
          
          jTextAreaDescription.setEditable(true);
          jTextNomL.setEditable(true);
          jTextType.setEditable(true);
          jTextOS.setEditable(true);
          jTextespace_d_min.setEditable(true);
          jTextm_ram_min.setEditable(true);
          jTextm_g_min.setEditable(true);
          jTextNote_config.setEditable(true);
          jTextEditeur.setEditable(true);
          
          
          ((CardLayout) CardType.getLayout()).show(CardType,s2);
          ((CardLayout) CardOS.getLayout()).show(CardOS,s4);
      }
        
        jTableL.getSelectionModel().addListSelectionListener(new ListSelectionListener()
    	{
    			public void valueChanged(ListSelectionEvent lse)
    			{
    				if (jTableL.getSelectedRow()!=-1 && ajout==false  && modif==false)
    				{
    					Logiciel l1 = new Logiciel();
    					int ligne = jTableL.getSelectedRow();
    					l1 =(Logiciel)v.elementAt(ligne);
    					   					
    					remplirChamps(l1);

    				}
    		    	
    			}
    	}    	);
          
       
}

    
  
    
    
    
    
    public void recupererLogiciels()
{
    	String requete ="";
    	
    	Gbd gbd = new Gbd();
    	gbd.connecter();
    	if(this.id_pc==-1)
    	 requete  =  "SELECT id_logiciel FROM logiciel";
    	
    	else
    	 requete ="SELECT id_logiciel FROM installe_dans WHERE id_pc="+this.id_pc;
    	
    	ResultSet r = gbd.execQuery(requete);
    	
    	try{
    	
    		while (r.next())
    		{
    			Logiciel l = new Logiciel();
    			l.construireLogiciel((r.getInt("id_logiciel")));
    			v.add(l);
    		}
    	}catch (SQLException e)
    	{
    		e.printStackTrace();
    		JXErrorDialog.showDialog(null,"DataBase ERROR !!!",e);
    	}
    	
Object [][] dataL = new Object[v.size()][8];
    	
    	
    	for (int i=0;i<v.size();i++)
    	{
    		Logiciel l =(Logiciel)v.elementAt(i);
    		dataL[i][0] = l.getId_logiciel().toString();
    		dataL[i][1] = l.getNom();
    		dataL[i][2] = l.getTypeLogicielle().getType();
    		dataL[i][3] = l.getCompatible().getNom_os();  
    		dataL[i][4] =  String.valueOf(l.getM_ram_min());
    		dataL[i][5] =  String.valueOf(l.getM_g_min());
    	    dataL[i][6] =  String.valueOf(l.getEspace_d_min());
    	    dataL[i][7] =  String.valueOf(l.getNote_config());
    	    	
    	    String ligne[]={l.getId_logiciel().toString(),
    	    		l.getNom(),
    	    		l.getTypeLogicielle().getType(),
    	    		l.getCompatible().getNom_os(),
    	    		String.valueOf(l.getM_ram_min()),
    	    		String.valueOf(l.getM_g_min()),
    	    		String.valueOf(l.getEspace_d_min()),
    	    		String.valueOf(l.getNote_config())
    	    };
    	    System.out.println(l.getId_logiciel().toString());
    	    
    	    model.addRow(ligne);
    	 }
    	jTableL.setModel(model);
    	
}
    
    public void remplirChamps(Logiciel l)
    {
    	
    	this.jTextNomL.setText(l.getNom());
    	this.jTextEditeur.setText(l.getEditeur());
    	this.jTextOS.setText(l.getCompatible().getNom_os());
    	this.jTextm_ram_min.setText(l.getM_ram_min().toString());
    	this.jTextm_g_min.setText(l.getM_g_min().toString());
    	this.jTextNote_config.setText(l.getNote_config().toString());
    	this.jTextespace_d_min.setText(l.getEspace_d_min().toString());
    	this.jTextAreaDescription.setText(l.getDescription());
    	this.jTextType.setText(l.getTypeLogicielle().getType());
        	
    	
    }
    
    String[] recupererTypes()
    {Gbd gbd = new Gbd();
    	gbd.connecter();
    	String requete ="SELECT DISTINCT 	type from type_logiciel";
    	ResultSet r =gbd.execQuery(requete);
    	Vector<String> v = new Vector<String> ();
    	v.add("");
    	try {
				while (r.next())
				{
					v.add(r.getString("type"));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
    	
		String[] retour = new  String [v.size()];
		v.toArray(retour);
//		System .out.println(v.size());
//		
//		for(int i=0;i<v.size();i++)
//		System .out.println(retour[i]);
		 return retour ;
		
    }
    
    
    String[] recupererOS()
    {Gbd gbd = new Gbd();
    	gbd.connecter();
    	String requete ="SELECT DISTINCT 	nom_os from os ";
    	ResultSet r =gbd.execQuery(requete);
    	Vector<String> v = new Vector<String> ();
    	v.add("");
    	try {
				while (r.next())
				{
					v.add(r.getString("nom_os"));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
    	
		String[] retour = new  String [v.size()];
		v.toArray(retour);
//		System .out.println(v.size());
//		
//		for(int i=0;i<v.size();i++)
//		System .out.println(retour[i]);
		 return retour ;
		
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
	private void initComponents() {
		jPanel3 = new javax.swing.JPanel();
		jPanelTableau = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
//		jTable1 = new javax.swing.JTable();
		jPanel5 = new javax.swing.JPanel();
		jbtSupprimer = new javax.swing.JButton();
		jbtModifier = new javax.swing.JButton();
		jbtAjouter = new javax.swing.JButton();
		jbLExistant = new javax.swing.JButton();
		jButtonAnnuler = new javax.swing.JButton();
		jBRechrcher = new javax.swing.JButton();
		jPanel2Prop = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jTextNomL = new javax.swing.JTextField();
		jLabel9 = new javax.swing.JLabel();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTextAreaDescription = new javax.swing.JTextArea();
		CardType = new javax.swing.JPanel();
		CardOS = new javax.swing.JPanel();
		jLabel10 = new javax.swing.JLabel();
		jTextEditeur = new javax.swing.JTextField();
		jPanel2 = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jTextm_ram_min = new javax.swing.JTextField();
		jTextm_g_min = new javax.swing.JTextField();
		jTextespace_d_min = new javax.swing.JTextField();
		jTextNote_config = new javax.swing.JTextField();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();

		jPanelTableau.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Logiciels"));
		jTableL.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null } },
				new String[] { "Nom", "Type", "System d exploitation",
						"Memoire Ram Min", "Memoire Carte Graphique Min",
						"Espace Disque Min", "Note Config Min" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false,
					false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane1.setViewportView(jTableL);

		org.jdesktop.layout.GroupLayout jPanelTableauLayout = new org.jdesktop.layout.GroupLayout(
				jPanelTableau);
		jPanelTableau.setLayout(jPanelTableauLayout);
		jPanelTableauLayout.setHorizontalGroup(jPanelTableauLayout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(jScrollPane1,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 937,
						Short.MAX_VALUE));
		jPanelTableauLayout.setVerticalGroup(jPanelTableauLayout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(jScrollPane1,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 162,
						Short.MAX_VALUE));

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

		jbLExistant.setText("Logiciels Existants");
		jbLExistant.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbLExistantActionPerformed(evt);
			}
		});

		jButtonAnnuler.setText("Annuler");
		jButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonAnnulerActionPerformed(evt);
			}
		});

		jBRechrcher.setText("Rechrcher");
		jBRechrcher.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBRechrcherActionPerformed(evt);
			}
		});

		org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(
				jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				jPanel5Layout.createSequentialGroup().add(jbtSupprimer,
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
								jButtonAnnuler,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								86,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED, 370,
								Short.MAX_VALUE).add(jBRechrcher)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jbLExistant).addContainerGap()));

		jPanel5Layout.linkSize(new java.awt.Component[] { jBRechrcher,
				jButtonAnnuler, jbtAjouter, jbtModifier, jbtSupprimer },
				org.jdesktop.layout.GroupLayout.HORIZONTAL);

		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				jPanel5Layout.createParallelGroup(
						org.jdesktop.layout.GroupLayout.BASELINE).add(
						jbtModifier).add(jbtSupprimer).add(jbtAjouter).add(
						jbLExistant).add(jButtonAnnuler).add(jBRechrcher)));

		jPanel2Prop.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Propri\u00e9t\u00e9s"));
		jLabel2.setText("Type du Logiciel");

		jLabel3.setText("Nom du Logiciel");

		jLabel4.setText("System d 'exploitation compatible");

		jLabel9.setText("Description ");

		jTextAreaDescription.setColumns(20);
		jTextAreaDescription.setRows(5);
		jScrollPane2.setViewportView(jTextAreaDescription);

		org.jdesktop.layout.GroupLayout CardTypeLayout = new org.jdesktop.layout.GroupLayout(
				CardType);
		CardType.setLayout(CardTypeLayout);
		CardTypeLayout.setHorizontalGroup(CardTypeLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 213,
				Short.MAX_VALUE));
		CardTypeLayout.setVerticalGroup(CardTypeLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 22,
				Short.MAX_VALUE));

		org.jdesktop.layout.GroupLayout CardOSLayout = new org.jdesktop.layout.GroupLayout(
				CardOS);
		CardOS.setLayout(CardOSLayout);
		CardOSLayout.setHorizontalGroup(CardOSLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 213,
				Short.MAX_VALUE));
		CardOSLayout.setVerticalGroup(CardOSLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 22,
				Short.MAX_VALUE));

		jLabel10.setText("Editeur");

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
														.add(jLabel9)
														.add(
																org.jdesktop.layout.GroupLayout.TRAILING,
																jPanel1Layout
																		.createSequentialGroup()
																		.add(
																				jPanel1Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.TRAILING)
																						.add(
																								org.jdesktop.layout.GroupLayout.LEADING,
																								jScrollPane2,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								376,
																								Short.MAX_VALUE)
																						.add(
																								jPanel1Layout
																										.createSequentialGroup()
																										.add(
																												jPanel1Layout
																														.createParallelGroup(
																																org.jdesktop.layout.GroupLayout.LEADING)
																														.add(
																																jLabel4,
																																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)
																														.add(
																																jLabel3,
																																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																																159,
																																Short.MAX_VALUE)
																														.add(
																																jLabel2,
																																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																																159,
																																Short.MAX_VALUE)
																														.add(
																																jLabel10,
																																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																																159,
																																Short.MAX_VALUE))
																										.addPreferredGap(
																												org.jdesktop.layout.LayoutStyle.RELATED)
																										.add(
																												jPanel1Layout
																														.createParallelGroup(
																																org.jdesktop.layout.GroupLayout.LEADING)
																														.add(
																																CardOS,
																																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																														.add(
																																jPanel1Layout
																																		.createParallelGroup(
																																				org.jdesktop.layout.GroupLayout.LEADING)
																																		.add(
																																				CardType,
																																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)
																																		.add(
																																				jTextEditeur,
																																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																																				213,
																																				Short.MAX_VALUE)
																																		.add(
																																				jTextNomL,
																																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																																				213,
																																				Short.MAX_VALUE)))))
																		.add(
																				46,
																				46,
																				46)))));

		jPanel1Layout.linkSize(
				new java.awt.Component[] { CardOS, jTextEditeur },
				org.jdesktop.layout.GroupLayout.HORIZONTAL);

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
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																jPanel1Layout
																		.createSequentialGroup()
																		.add(
																				jLabel2,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				13,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jLabel3)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jLabel4,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				22,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jLabel10)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jLabel9))
														.add(
																jPanel1Layout
																		.createSequentialGroup()
																		.add(
																				CardType,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jTextNomL,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				CardOS,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jTextEditeur,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
										.add(14, 14, 14)
										.add(
												jScrollPane2,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(19, Short.MAX_VALUE)));

		jPanel1Layout.linkSize(new java.awt.Component[] { jLabel10, jLabel2,
				jLabel3, jLabel4, jTextNomL },
				org.jdesktop.layout.GroupLayout.VERTICAL);

		jPanel1Layout.linkSize(
				new java.awt.Component[] { CardOS, jTextEditeur },
				org.jdesktop.layout.GroupLayout.VERTICAL);

		jPanel2.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Configuration Minimal"));
		jLabel5.setText("M\u00e9moire RAM ");

		jLabel6.setText("M\u00e9moire Carte Graphique ");

		jLabel7.setText("Espace Disque Dur");

		jLabel8.setText("Note de la configuration minimal ");

		jLabel11.setText("Mo");

		jLabel12.setText("Mo");

		jLabel13.setText("Mo");

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
										.addContainerGap()
										.add(
												jPanel2Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(jLabel8).add(
																jLabel6).add(
																jLabel7).add(
																jLabel5))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel2Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																jTextNote_config,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(
																jPanel2Layout
																		.createSequentialGroup()
																		.add(
																				jTextm_g_min,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jLabel12))
														.add(
																jPanel2Layout
																		.createSequentialGroup()
																		.add(
																				jTextespace_d_min,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jLabel13))
														.add(
																jPanel2Layout
																		.createSequentialGroup()
																		.add(
																				jTextm_ram_min,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				155,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jLabel11)))
										.addContainerGap(34, Short.MAX_VALUE)));

		jPanel2Layout.linkSize(new java.awt.Component[] { jLabel5, jLabel6,
				jLabel7, jLabel8, jTextNote_config, jTextespace_d_min,
				jTextm_g_min }, org.jdesktop.layout.GroupLayout.HORIZONTAL);

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
														.add(
																jPanel2Layout
																		.createSequentialGroup()
																		.add(
																				jPanel2Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.BASELINE)
																						.add(
																								jTextm_ram_min,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																						.add(
																								jLabel11))
																		.add(
																				25,
																				25,
																				25)
																		.add(
																				jPanel2Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.BASELINE)
																						.add(
																								jTextm_g_min,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								25,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																						.add(
																								jLabel12))
																		.add(
																				30,
																				30,
																				30)
																		.add(
																				jPanel2Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.BASELINE)
																						.add(
																								jTextespace_d_min,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																						.add(
																								jLabel13))
																		.add(
																				23,
																				23,
																				23)
																		.add(
																				jTextNote_config,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
														.add(
																jPanel2Layout
																		.createSequentialGroup()
																		.add(
																				jLabel5)
																		.add(
																				25,
																				25,
																				25)
																		.add(
																				jLabel6)
																		.add(
																				30,
																				30,
																				30)
																		.add(
																				jLabel7)
																		.add(
																				23,
																				23,
																				23)
																		.add(
																				jLabel8)))
										.addContainerGap(
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jPanel2Layout.linkSize(new java.awt.Component[] { jLabel5, jLabel6,
				jLabel7, jLabel8, jTextNote_config, jTextespace_d_min,
				jTextm_g_min, jTextm_ram_min },
				org.jdesktop.layout.GroupLayout.VERTICAL);

		org.jdesktop.layout.GroupLayout jPanel2PropLayout = new org.jdesktop.layout.GroupLayout(
				jPanel2Prop);
		jPanel2Prop.setLayout(jPanel2PropLayout);
		jPanel2PropLayout
				.setHorizontalGroup(jPanel2PropLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel2PropLayout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												jPanel1,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												400,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED,
												125, Short.MAX_VALUE)
										.add(
												jPanel2,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		jPanel2PropLayout
				.setVerticalGroup(jPanel2PropLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel2PropLayout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												jPanel2PropLayout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																jPanel2,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(
																jPanel1,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Logiciels");

		org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel3Layout
										.createSequentialGroup()
										.add(
												jPanel3Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING,
																false)
														.add(
																jPanel3Layout
																		.createSequentialGroup()
																		.add(
																				10,
																				10,
																				10)
																		.add(
																				jLabel1,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																jPanel2Prop,
																0,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																jPanelTableau,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																jPanel5,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap(
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				org.jdesktop.layout.GroupLayout.TRAILING,
				jPanel3Layout.createSequentialGroup().addContainerGap().add(
						jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						23, Short.MAX_VALUE).add(21, 21, 21).add(jPanelTableau,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jPanel5,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jPanel2Prop,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup().addContainerGap().add(jPanel3,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup().add(jPanel3,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(30, Short.MAX_VALUE)));
	}// </editor-fold>//GEN-END:initComponents

	private void jBRechrcherActionPerformed(java.awt.event.ActionEvent evt) 
{//GEN-FIRST:event_jBRechrcherActionPerformed
	//GEN-FIRST:event_jBRechrcherActionPerformed
    // TODO add your handling code here:
		

		String requete ="SELECT id_logiciel FROM logiciel WHERE ";
	int cmparg=0;
		if(cmbType.getSelectedItem().toString().length()>0)	
										{ if(cmparg!=0)requete+=" AND ";
										  Type_logiciel t = new Type_logiciel();
										  t.construireType_logiciel((String)cmbType.getSelectedItem());	
									      requete+=" id_type_logiciel = "+t.getId_type_logiciel();
									      cmparg++;
										
										}
		if(cmbOS.getSelectedItem().toString().length()>0)	
										{	if(cmparg!=0)requete+=" AND ";
											OS o = new OS();
											o.construireOS((String)cmbOS.getSelectedItem());	
											requete+=" id_os = "+o.getId_os();
											cmparg++;
		
										}
		if(jTextNomL.getText().toString().length()>0)	
		{	if(cmparg!=0)requete+=" AND ";
			requete+=" nom LIKE '%"+jTextNomL.getText().toString()+"%'";
			cmparg++;

		}
		if(jTextEditeur.getText().toString().length()>0)	
		{	if(cmparg!=0)requete+=" AND ";
			requete+=" editeur LIKE '%"+jTextEditeur.getText().toString()+"%'";
			cmparg++;

		}
		if(jTextAreaDescription.getText().toString().length()>0)	
		{	if(cmparg!=0)requete+=" AND ";
			requete+=" description = '%"+jTextAreaDescription.getText().toString()+"%'";
			cmparg++;

		}
		
		try{
			if(jTextm_ram_min.getText().toString().length()>0)	
		{	System.out.println("onvatriuvé"+Integer.parseInt(jTextm_ram_min.getText().toString())+"lerreur"+cmparg+"\n");
			if(cmparg!=0)requete+=" AND ";
			
				requete+=" m_ram_min ="+Integer.parseInt(jTextm_ram_min.getText().toString());
				cmparg++;
		}}catch(Exception e)
			{
				JXErrorDialog.showDialog(null,"Veuillez remplir les champs correctements !!!", "Erreur lors de saisie des données  !!!", "\n\n\tLes champs de configuration minimal prennent des valeurs numeriques \n et non pas des chaines de caractéres !!!\n\n  ");	
				jTextm_ram_min.setText(null);
				
			}

		
		try{if(jTextm_g_min.getText().toString().length()>0)	
		{	System.out.println("onvatriuvé"+Integer.parseInt(jTextm_g_min.getText().toString())+"lerreur"+cmparg+"\n");
			if(cmparg!=0)requete+=" AND ";
			
				requete+=" m_g_min ="+Integer.parseInt(jTextm_g_min.getText().toString());
				cmparg++;
			}}catch(Exception e)
			{
				JXErrorDialog.showDialog(null,"Veuillez remplir les champs correctements !!!", "Erreur lors de saisie des données  !!!", "\n\n\tLes champs de configuration minimal prennent des valeurs numeriques \n et non pas des chaines de caractéres !!!\n\n  ");	
				jTextm_g_min.setText(null);
				
			}

		
		try{
			if(jTextespace_d_min.getText().toString().length()>0)	
		{		System.out.println("onvatriuvé"+Integer.parseInt(jTextespace_d_min.getText().toString())+"lerreur"+cmparg+"\n");
				if(cmparg!=0)requete+=" AND ";
			
				requete+=" espace_d_min ="+Integer.parseInt(jTextespace_d_min.getText().toString());
				cmparg++;
			}}catch(Exception e)
			{
				JXErrorDialog.showDialog(null,"Veuillez remplir les champs correctements !!!", "Erreur lors de saisie des données  !!!", "\n\n\tLes champs de configuration minimal prennent des valeurs numeriques \n et non pas des chaines de caractéres !!!\n\n  ");	
				jTextespace_d_min.setText(null);
				
			}

			try{
				if(jTextNote_config.getText().toString().length()>0)	
			{		System.out.println("onvatriuvé"+Integer.parseInt(jTextNote_config.getText().toString())+"lerreur"+cmparg+"\n");
			if(cmparg!=0)requete+=" AND ";
			
				requete+=" Note_config ="+Integer.parseInt(jTextNote_config.getText().toString())+"";
				cmparg++;
				
			}}catch(Exception e)
			{
				JXErrorDialog.showDialog(null,"Veuillez remplir les champs correctements !!!", "Erreur lors de saisie des données  !!!", "\n\n\tLes champs de configuration minimal prennent des valeurs numeriques \n et non pas des chaines de caractéres !!!\n\n  ");	
				jTextNote_config.setText(null);
				
			}

		
		System.out.println("\n\n"+requete);
		System.out.print("Execution de la requette a "+cmparg+" Critéres ");
	if(cmparg>0){System.out.println("----------->"+cmparg);
		int c=0;
		
		while(c<v.size())
		{model.removeRow(0);c++;}

		v.removeAllElements();
		
	Gbd gbd = new Gbd();
	gbd.connecter();
	
	try{
		ResultSet r =gbd.execQuery(requete);
		System.out.println("le nombre de resultat ===> "+gbd.count());
		if(gbd.count()==0)JOptionPane.showConfirmDialog(this,"Pas de resultat pour cette recherche  !!!","Rechrche Logiciel",JOptionPane.DEFAULT_OPTION);
		while (r.next())
			{
				Logiciel l = new Logiciel();
				l.construireLogiciel((r.getInt("id_logiciel")));
				v.add(l);
			}
		}catch (SQLException e)
		{
			e.printStackTrace();
			if(gbd.count()==0)JOptionPane.showConfirmDialog(this,"Pas de Resultat pour cette rechrche !!!","Rechrche Logiciel",JOptionPane.DEFAULT_OPTION);
			System.out.println(gbd.count());
		}
	
		gbd.deconnecter();		
		
		Object [][] dataL = new Object[v.size()][8];
	
	
				for (int i=0;i<v.size();i++)
				{
						Logiciel l =(Logiciel)v.elementAt(i);
						dataL[i][0] = l.getId_logiciel().toString();
						dataL[i][1] = l.getNom();
						dataL[i][2] = l.getTypeLogicielle().getType();
						dataL[i][3] = l.getCompatible().getNom_os();  
						dataL[i][4] =  String.valueOf(l.getM_ram_min());
						dataL[i][5] =  String.valueOf(l.getM_g_min());
						dataL[i][6] =  String.valueOf(l.getEspace_d_min());
	    				dataL[i][7] =  String.valueOf(l.getNote_config());
	    	
	    			String ligne[]={l.getId_logiciel().toString(),
	    				l.getNom(),
	    				l.getTypeLogicielle().getType(),
	    				l.getCompatible().getNom_os(),
	    				String.valueOf(l.getM_ram_min()),
	    				String.valueOf(l.getM_g_min()),
	    				String.valueOf(l.getEspace_d_min()),
	    				String.valueOf(l.getNote_config())
	    				};
	    		System.out.println(l.getId_logiciel().toString());
	    
	    		model.addRow(ligne);
	 			}
			jTableL.setModel(model);
	}
	else
	{
		JOptionPane.showConfirmDialog(this,"Il faut donner des arguments pour cette recherche  !!!","Rechrche Logiciel",JOptionPane.DEFAULT_OPTION);
	}
}
	
	
	
	private void jButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnnulerActionPerformed
	//GEN-FIRST:event_jButtonAnnulerActionPerformed
		// TODO add your handling code here:
		///	jPanel7.add(jDatedebut);
		modif = false;
		jbtModifier.setText("Modifier");
		ajout = false;
		jbtAjouter.setText("Ajouter");
	
		
		jTextAreaDescription.setEnabled(false);
		
		jTextNomL.setEnabled(false);
 //cmbType.setEnabled(false);
 //cmbOS.setEnabled(false);
 jTextType.setEnabled(false);
 jTextOS.setEnabled(false);
 jTextespace_d_min.setEnabled(false);
 jTextm_ram_min.setEnabled(false);
 jTextm_g_min.setEnabled(false);
 jTextNote_config.setEnabled(false);
 jTextEditeur.setEnabled(false);
 ((CardLayout) CardType.getLayout()).show(CardType,s1);
 ((CardLayout) CardOS.getLayout()).show(CardOS,s3);			
	
 	this.jTextNomL.setText("");
	this.jTextEditeur.setText("");
	this.jTextOS.setText("");
	this.jTextm_ram_min.setText("");
	this.jTextm_g_min.setText("");
	this.jTextNote_config.setText("");
	this.jTextespace_d_min.setText("");
	this.jTextAreaDescription.setText("");
	this.jTextType.setText("");	
		
		jTableL.clearSelection();
	if(id_pc==-2){int c=0;
			
					while(c<v.size())
					{model.removeRow(0);c++;}
	
					v.removeAllElements();
			
			
			
		
					cmbType.setSelectedItem("");
					cmbOS.setSelectedItem("");
			  jTextAreaDescription.setEnabled(true);
	          jTextNomL.setEnabled(true);
	          jTextType.setEnabled(true);
	          jTextOS.setEnabled(true);
	          jTextespace_d_min.setEnabled(true);
	          jTextm_ram_min.setEnabled(true);
	          jTextm_g_min.setEnabled(true);
	          jTextNote_config.setEnabled(true);
	          jTextEditeur.setEnabled(true);
	          
	          jTextAreaDescription.setEditable(true);
	          jTextNomL.setEditable(true);
	          jTextType.setEditable(true);
	          jTextOS.setEditable(true);
	          jTextespace_d_min.setEditable(true);
	          jTextm_ram_min.setEditable(true);
	          jTextm_g_min.setEditable(true);
	          jTextNote_config.setEditable(true);
	          jTextEditeur.setEditable(true);
	          
			
	          ((CardLayout) CardType.getLayout()).show(CardType,s2);
	          ((CardLayout) CardOS.getLayout()).show(CardOS,s4);			
			
			
				}	
	}//GEN-LAST:event_jButtonAnnulerActionPerformed

	private void jbLExistantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLExistantActionPerformed
	// TODO add your handling code here:
		
	new	 Dialog_choixLogiciel(m, true,this,this.id_pc).setVisible(true);
		
		
	}//GEN-LAST:event_jbLExistantActionPerformed

	private void jbtModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtModifierActionPerformed
	
	if(jTableL.getSelectedRowCount()!=0 && ajout== false)	
{
	if(modif == false)
	{
		modif=true;
		jbtModifier.setText("Valider");
		  ((CardLayout) CardType.getLayout()).show(CardType,s2);
	      ((CardLayout) CardOS.getLayout()).show(CardOS,s4);
	      jTextAreaDescription.setEnabled(true);
	        jTextNomL.setEnabled(true);
	     //   jTextType.setEnabled(true);
	      //  jTextOS.setEnabled(true);
	        jTextespace_d_min.setEnabled(true);
	        jTextm_ram_min.setEnabled(true);
	        jTextm_g_min.setEnabled(true);
	        jTextNote_config.setEnabled(true);
	        jTextEditeur.setEnabled(true);
    cmbOS.setSelectedItem((String)jTextOS.getText());
    cmbType.setSelectedItem((String)jTextType.getText());
	//	jTableL.disable();
		
	}else 
	{
		modif = false;
		jbtModifier.setText("Modifier");
		Logiciel l = new Logiciel();
		
		int indice =jTableL.getSelectedRow();
		l = (Logiciel) v.elementAt(indice);
		
		try{ 
			
			l.setDescription(jTextAreaDescription.getText());
			l.setEditeur(jTextEditeur.getText());
			l.setM_g_min(Integer.parseInt(jTextm_g_min.getText()));
			l.setM_ram_min(Integer.parseInt(jTextm_ram_min.getText()));
			l.setNom(jTextNomL.getText());
			l.setEspace_d_min(Integer.parseInt(jTextespace_d_min.getText()));
			l.setNote_config(Integer.parseInt(jTextNote_config.getText()));
			
			Type_logiciel T = new Type_logiciel();
			T.construireType_logiciel((String)cmbType.getSelectedItem());
			
			gestion_materiel.OS o =  new gestion_materiel.OS();
			o.construireOS((String)cmbOS.getSelectedItem());
			
			l.setTypeLogicielle(T);
			l.setCompatible(o);
			// 0 : OUI
			// 1 : NON
		
						
			
			int 	reponse = JOptionPane.showConfirmDialog(this,"Etes vous sur de vouloir modifier ce logiciel  ?","Modification",JOptionPane.YES_NO_OPTION);
			if(reponse==0)
			{	
				int nb = l.modifierLogiciel();
				System.out.println("le resultat de la imodif est ="+nb);
				
				
				if (nb==1)
				{v.setElementAt(l, indice);
	
				  String ligne[]={l.getId_logiciel().toString(),
		    	    		l.getNom(),
		    	    		l.getTypeLogicielle().getType(),
		    	    		l.getCompatible().getNom_os(),
		    	    		String.valueOf(l.getM_ram_min()),
		    	    		String.valueOf(l.getM_g_min()),
		    	    		String.valueOf(l.getEspace_d_min()),
		    	    		String.valueOf(l.getNote_config())
		    	    
					};
			
				model.setValueAt(l.getId_logiciel().toString(), indice, 0);
				model.setValueAt(l.getNom(), indice, 1);
				model.setValueAt(l.getTypeLogicielle().getType(), indice, 2);
				model.setValueAt(l.getCompatible().getNom_os(), indice, 3);
				model.setValueAt(String.valueOf(l.getM_ram_min()), indice, 4);
				model.setValueAt(String.valueOf(l.getM_g_min()), indice, 5);
				model.setValueAt(String.valueOf(l.getEspace_d_min()), indice, 6);
				model.setValueAt(String.valueOf(l.getNote_config()), indice, 7);
				
				JOptionPane.showConfirmDialog(this, "Le Logiciel a été Modifier avec succès!","Modification",JOptionPane.DEFAULT_OPTION);
			//	model.addRow(ligne);
		//		jTableL.enable();
			}}
			else
			{
				JOptionPane.showConfirmDialog(null, "Le Logiciel n'a pas été modifier !!","Modification",JOptionPane.DEFAULT_OPTION);
			//	jTableL.enable();
			}
			
		}catch (Exception e)
{
	e.printStackTrace();
	//JXErrorDialog.showDialog(null,"DataBase ERROR !!!",e);
	JXErrorDialog.showDialog(null,"Veuillez remplir les champs correctements !!!", "Erreur lors de saisie des données  !!!", "\n\n\tLes champs de configuration minimal prennent des valeurs numeriques \n et non pas des chaines de caractéres !!!\n\n  ");
}

 
 jTextAreaDescription.setEnabled(false);
 jTextNomL.setEnabled(false);
 //cmbType.setEnabled(false);
 //cmbOS.setEnabled(false);
 jTextType.setEnabled(false);
 jTextOS.setEnabled(false);
 jTextespace_d_min.setEnabled(false);
 jTextm_ram_min.setEnabled(false);
 jTextm_g_min.setEnabled(false);
 jTextNote_config.setEnabled(false);
 jTextEditeur.setEnabled(false);
 ((CardLayout) CardType.getLayout()).show(CardType,s1);
 ((CardLayout) CardOS.getLayout()).show(CardOS,s3);			
	
// 	this.jTextNomL.setText("");
//	this.jTextEditeur.setText("");
//	this.jTextOS.setText("");
//	this.jTextm_ram_min.setText("");
//	this.jTextm_g_min.setText("");
//	this.jTextNote_config.setText("");
//	this.jTextespace_d_min.setText("");
//	this.jTextAreaDescription.setText("");
//	this.jTextType.setText("");	
		
	}}
	else if(ajout==true)
	{JOptionPane.showConfirmDialog(this, "Veuillez completer le processus de l ajout !!!", "Modification",JOptionPane.DEFAULT_OPTION);
	}
	else
	{	JOptionPane.showConfirmDialog(this, "Il faut selectionner un Logiciel afin de le modifier", "Modification",JOptionPane.DEFAULT_OPTION);
	}	

//jTableL.enable();
		// TODO add your handling code here:
}//GEN-LAST:event_jbtModifierActionPerformed

	private void jbtAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAjouterActionPerformed
	//GEN-FIRST:event_jbtAjouterActionPerformed
		//GEN-FIRST:event_jbtAjouterActionPerformed
		/////////////////////////////////////////////
		if(modif==false)
		{
		
		if (ajout == false) {
			ajout = true;
			jbtAjouter.setText("Valider");

			
			
			  ((CardLayout) CardType.getLayout()).show(CardType,s2);
		      ((CardLayout) CardOS.getLayout()).show(CardOS,s4);
		   
		      cmbType.setSelectedItem("  ");
		      cmbOS.setSelectedItem("  ");
		     
		      jTextAreaDescription.setEnabled(true);
		        jTextNomL.setEnabled(true);
		     //   jTextType.setEnabled(true);
		      //  jTextOS.setEnabled(true);
		        jTextespace_d_min.setEnabled(true);
		        jTextm_ram_min.setEnabled(true);
		        jTextm_g_min.setEnabled(true);
		        jTextNote_config.setEnabled(true);
		        jTextEditeur.setEnabled(true);

		} else {
			ajout = false;
			jbtAjouter.setText("Ajouter");
			l = new Logiciel();
		
try{ 
			
			l.setDescription(jTextAreaDescription.getText());
			l.setEditeur(jTextEditeur.getText());
			l.setM_g_min(Integer.parseInt(jTextm_g_min.getText()));
			l.setM_ram_min(Integer.parseInt(jTextm_ram_min.getText()));
			l.setNom(jTextNomL.getText());
			l.setEspace_d_min(Integer.parseInt(jTextespace_d_min.getText()));
			l.setNote_config(Integer.parseInt(jTextNote_config.getText()));
		try{
			Type_logiciel T = new Type_logiciel();
			T.construireType_logiciel((String)cmbType.getSelectedItem());
			
			gestion_materiel.OS o =  new gestion_materiel.OS();
			o.construireOS((String)cmbOS.getSelectedItem());
			
			l.setTypeLogicielle(T);
			l.setCompatible(o);
			// 0 : OUI
			
			
			int 	reponse = JOptionPane.showConfirmDialog(this,"Etes vous sur de vouloir ajouter ce logiciel  ?","Ajout",JOptionPane.YES_NO_OPTION);
			if(reponse==0)
			{	
				int nb = l.insererLogiciel();
				System.out.println("le resultat de l insertion est ="+nb);
				if (nb==1)
				{
					String requete = "SELECT max(id_logiciel) AS id FROM logiciel";
					Gbd gbd = new Gbd();
					gbd.connecter();
					ResultSet r = gbd.execQuery(requete);
				
				int id_logiciel=0;
					try
					{
						r.next();
						id_logiciel= r.getInt("id");
					
						gbd.deconnecter();
					}catch(Exception e)
					{
						System.out.println(e.toString());
					}
				l.construireLogiciel(id_logiciel);
				v.add(l);
				}						
				  String ligne[]={l.getId_logiciel().toString(),
		    	    		l.getNom(),
		    	    		l.getTypeLogicielle().getType(),
		    	    		l.getCompatible().getNom_os(),
		    	    		String.valueOf(l.getM_ram_min()),
		    	    		String.valueOf(l.getM_g_min()),
		    	    		String.valueOf(l.getEspace_d_min()),
		    	    		String.valueOf(l.getNote_config())    };
				  				
				  					if(id_pc>0)
				  							{
				  								Gbd gbd = new Gbd();
				  								String  requete="INSERT INTO installe_dans VALUES("+id_pc+","+l.getId_logiciel()+")";
				  								gbd.connecter();
				  								int res = gbd.execUpdate(requete);
				  								if(res==1)System.out.println("le resultat de l ajout dans le local panel s est effectué avec succé ");
				  							}
				JOptionPane.showConfirmDialog(this, "Le Logiciel a été ajouté avec succès!","Ajout",JOptionPane.DEFAULT_OPTION);
				model.addRow(ligne);
				jTableL.setRowSelectionInterval(jTableL.getRowCount()-1, jTableL.getRowCount()-1);
		
			
			}
			else
			{
				JOptionPane.showConfirmDialog(null, "Le Logiciel n'a pas été ajouter !!","Ajout",JOptionPane.DEFAULT_OPTION);
			}	
			}catch(NullPointerException e){e.printStackTrace();
			//JXErrorDialog.showDialog(null,"DataBase ERROR !!!",e);
			JXErrorDialog.showDialog(null,"Veuillez remplir les champs correctements !!!", "Erreur lors de saisie des données  !!!", "\n\n\tLes champs de configuration minimal prennent des valeurs numeriques \n et non pas des chaines de caractéres !!!\n\nIl ne faut pas oublier de spécifier le type ainsi que le system d exploitation dans les combo box !!! ");};// 1 : NON
}catch (Exception e)
{
	e.printStackTrace();
	//JXErrorDialog.showDialog(null,"DataBase ERROR !!!",e);
	JXErrorDialog.showDialog(null,"Veuillez remplir les champs correctements !!!", "Erreur lors de saisie des données  !!!", "\n\n\tLes champs de configuration minimal prennent des valeurs numeriques \n et non pas des chaines de caractéres !!!\n\nIl ne faut pas oublier de spécifier le type ainsi que le system d exploitation dans les combo box !!! ");
}

 
 jTextAreaDescription.setEnabled(false);
 jTextNomL.setEnabled(false);
 //cmbType.setEnabled(false);
 //cmbOS.setEnabled(false);
 jTextType.setEnabled(false);
 jTextOS.setEnabled(false);
 jTextespace_d_min.setEnabled(false);
 jTextm_ram_min.setEnabled(false);
 jTextm_g_min.setEnabled(false);
 jTextNote_config.setEnabled(false);
 jTextEditeur.setEnabled(false);
 ((CardLayout) CardType.getLayout()).show(CardType,s1);
 ((CardLayout) CardOS.getLayout()).show(CardOS,s3);			
	
 	this.jTextNomL.setText("");
	this.jTextEditeur.setText("");
	this.jTextOS.setText("");
	this.jTextm_ram_min.setText("");
	this.jTextm_g_min.setText("");
	this.jTextNote_config.setText("");
	this.jTextespace_d_min.setText("");
	this.jTextAreaDescription.setText("");
	this.jTextType.setText("");	
}}
		else{
			JOptionPane.showConfirmDialog(this, "Veuillez completer le processus de modification !!!", "Ajout",JOptionPane.DEFAULT_OPTION);
		}
		

		

	//////////////////////////////////////////////////	
		// TODO add your handling code here:
	}//GEN-LAST:event_jbtAjouterActionPerformed

	private void jbtSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSupprimerActionPerformed
	//GEN-FIRST:event_jbtSupprimerActionPerformed

		int reponse = -1;
		int res=0;
		if (jTableL.getSelectedRowCount() != 0) { // 0 : OUI
			// 1 : NON
			reponse = JOptionPane.showConfirmDialog(this,
					"Etes vous sur de vouloir supprimer ce Logiciel ?",
					"Suppression", JOptionPane.YES_NO_OPTION);
		
				if (reponse == 0) {
					
									int id = Integer.parseInt(jTableL.getValueAt(
									jTableL.getSelectedRow(), 0).toString());
												if(id_pc==-1)
												{
													Logiciel l = new Logiciel();

													l.setId_logiciel(id);
													res = l.supprimerLogiciel();
													System.out.println("id = " + id + "  res=" + res+"de la base de donné");
												}	
												else if(id_pc>0)
													{	Gbd  gbd = new Gbd();
														gbd.connecter();
														String requete="DELETE id_logiciel FROM installe_dans WHERE id_logiciel="+id ;
														res=gbd.execUpdate(requete);
														System.out.println("Resultat de suppression local est = "+res);
														gbd.deconnecter();
													}
													
													//int res=0;
				if (res == 1) {
					v.remove(jTableL.getSelectedRow());
					model.removeRow(jTableL.getSelectedRow());
					JOptionPane.showConfirmDialog(this,
							"Logiciel supprimé avec succès", "Suppression",
							JOptionPane.DEFAULT_OPTION);
					
					
					this.jTextNomL.setText("");
					this.jTextEditeur.setText("");
					this.jTextOS.setText("");
					this.jTextm_ram_min.setText("");
					this.jTextm_g_min.setText("");
					this.jTextNote_config.setText("");
					this.jTextespace_d_min.setText("");
					this.jTextAreaDescription.setText("");
					this.jTextType.setText("");	
				 
				} else {
					JOptionPane.showConfirmDialog(null,
							"Le Logiciel n'a pas été supprimé !!", "Suppression",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		} else {
			JOptionPane.showConfirmDialog(null,
					"Veuillez selectionner un Logiciel !! ", "Suppression",
					JOptionPane.DEFAULT_OPTION);
		}
	}//GEN-LAST:event_jbtSupprimerActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel CardOS;

	private javax.swing.JPanel CardType;

	private javax.swing.JButton jBRechrcher;

	private javax.swing.JButton jButtonAnnuler;

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

	private javax.swing.JPanel jPanel2Prop;

	private javax.swing.JPanel jPanel3;

	private javax.swing.JPanel jPanel5;

	private javax.swing.JPanel jPanelTableau;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JScrollPane jScrollPane2;

//	private javax.swing.JTable jTable1;

	private javax.swing.JTextArea jTextAreaDescription;

	private javax.swing.JTextField jTextEditeur;

	private javax.swing.JTextField jTextNomL;

	private javax.swing.JTextField jTextNote_config;

	private javax.swing.JTextField jTextespace_d_min;

	private javax.swing.JTextField jTextm_g_min;

	private javax.swing.JTextField jTextm_ram_min;

	private javax.swing.JButton jbLExistant;

	private javax.swing.JButton jbtAjouter;

	private javax.swing.JButton jbtModifier;

	private javax.swing.JButton jbtSupprimer;

	// End of variables declaration//GEN-END:variables
	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public Vector getV() {
		return v;
	}

	public void setV(Vector v) {
		this.v = v;
	}

}
