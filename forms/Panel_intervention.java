/*
 * Panel_intervention.java
 *
 * Created on __DATE__, __TIME__
 */

package forms;

import gestion_materiel.Gbd;
import gestion_materiel.Intervention;
import gestion_materiel.Pc;
import gestion_materiel.Technicien;
import gestion_materiel.Type_intervention;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXEditorPane;
import org.jdesktop.swingx.JXErrorDialog;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.AlternateRowHighlighter;
import org.jdesktop.swingx.decorator.Highlighter;
import org.jdesktop.swingx.decorator.HighlighterPipeline;
import org.jdesktop.swingx.decorator.RolloverHighlighter;

/**
 *
 * @author  __USER__
 */
public class Panel_intervention extends javax.swing.JPanel {

    /** Creates new form Panel_intervention */
    
    JXTable jTableIntervention;
    DefaultTableModel model;
    Vector v = new Vector();;
    
    JTextField txtType = new JTextField(20);
    JComboBox cmbType = new JComboBox();
    
    JTextField txtId_technicien = new JTextField(20);
    JComboBox cmbId  = new JComboBox();
    
    JTextField txtIdPc = new JTextField(20);
    JComboBox cmbIdPc  = new JComboBox();
    
    MainForm m;
    
    boolean ajout=false,modif=false;
    
     
        String s1 = "card1_text";
        String s2 = "card1_box";
        
        String s3 = "card2_text";	
        String s4 = "card2_box";
        
        String s5 = "card2_text";	
        String s6 = "card2_box";
        
    
    public Panel_intervention(MainForm m) {
       
        this.m = m;
    	jTableIntervention = new JXTable();
    	  	
        model  = new DefaultTableModel();
        
        model.addColumn("id_intervention");
        model.addColumn("Type");
        model.addColumn("Date");
        model.addColumn("Heure");
        model.addColumn("Nom technicien");
        model.addColumn("Prenom technicien");
        model.addColumn("Id_pc");
       
        
        jTableIntervention = new JXTable();
		jTableIntervention.setHighlighters(
        new HighlighterPipeline(new Highlighter[] {AlternateRowHighlighter.quickSilver}
));
		jTableIntervention.setRolloverEnabled(true);
		jTableIntervention.getHighlighters().addHighlighter(new RolloverHighlighter(Color.GRAY, Color.WHITE ));
		jTableIntervention.setColumnControlVisible(true);
		
		initComponents();
		
		
		cardType.setLayout(new CardLayout());
		cardIdTechnicien.setLayout(new CardLayout());
		cardPc.setLayout(new CardLayout());
		
		cardType.add(s1,txtType);
		cardType.add(s2,cmbType);
		
		cardIdTechnicien.add(s3,txtId_technicien);
		cardIdTechnicien.add(s4,cmbId);
		
		cardPc.add(s5,txtIdPc);
		cardPc.add(s6,cmbIdPc);
		
		
		((CardLayout) cardType.getLayout()).show(cardType,s1);
		((CardLayout) cardIdTechnicien.getLayout()).show(cardIdTechnicien,s3);
		((CardLayout) cardPc.getLayout()).show(cardPc,s5);
		
		
		recupererIntervention();
		
		
		txtIdIntervention.setEnabled(false);
		txtType.setEnabled(false);
		txtDate.setEnabled(false);
		txtHeure.setEnabled(false);
		txtMinutes.setEnabled(false);
		txtId_technicien.setEnabled(false);
		txtNom.setEnabled(false);
		txtPrenom.setEnabled(false);
		txtCin.setEnabled(false);
		txtId_technicien.setEnabled(false);
		txtNomPc.setEnabled(false);
		txtIdPc.setEnabled(false);
		
		
		cmbId.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				cmbIdItemStateChanged(evt);
			}

		});
		
		cmbIdPc.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				cmbIdPcItemStateChanged(evt);
			}

			
		});
		
		
	     jTableIntervention.getSelectionModel().addListSelectionListener(new ListSelectionListener()
	    	{
	    			public void valueChanged(ListSelectionEvent lse)
	    			{
	    				if (jTableIntervention.getSelectedRow()!=-1 && modif==false && ajout == false)
	    				{
	    					
	    					int ligne = jTableIntervention.getSelectedRow();
	    					Intervention in = (Intervention)v.elementAt(ligne);
	    					
	    					txtIdIntervention.setText(in.getId_intervention().toString());
	    					txtType.setText(in.getType_intervention().getNom_intervention());
	    					txtDate.setDate(in.getDate_intervention());
	    					String heure = in.getHeure_intervention().toString();
	    					StringTokenizer dateHeure = new StringTokenizer(heure,":");
	    					txtHeure.setText(dateHeure.nextToken());
	    					txtMinutes.setText(dateHeure.nextToken());
	    					txtId_technicien.setText(in.getTechnicien().getId_technicien().toString());
	    					txtNom.setText(in.getTechnicien().getNom_technicien());
	    					txtPrenom.setText(in.getTechnicien().getPrenom_technicien());
	    					txtCin.setText(in.getTechnicien().getCin_technicien());
	    					txtIdPc.setText(in.getPc().getId_pc().toString());
	    					txtNomPc.setText(in.getPc().getMarque()+" "+in.getPc().getModele());
	    					
	    					
	    				}
	    	
	    			}
	    	
	    	});
        
    }

    
    

	private void cmbIdItemStateChanged(ItemEvent evt) {
	
		if (cmbId.getItemCount()!=0)
			if (evt.getStateChange()==1)
				if (cmbId.getSelectedItem().toString()=="Choisir un Technicien")
				{	
					Dialog_choixTechnicien d = new Dialog_choixTechnicien(m,true,this);
					d.setVisible(true);
					d.setLocationRelativeTo(this);
					
				}
		
		
	}
	
	
	private void cmbIdPcItemStateChanged(ItemEvent evt) {
	
		
		if (cmbIdPc.getItemCount()!=0)
			if (evt.getStateChange()==1)
				if (cmbIdPc.getSelectedItem().toString()=="Choisir un PC")
				{	
					Dialog_choixPc d = new Dialog_choixPc(m,true,this);
					d.setVisible(true);
					d.setLocationRelativeTo(this);
					
				}
		
		
	}
    
    public void recupererIntervention()
    {
    	Gbd gbd = new Gbd();
    	gbd.connecter();
    	
    	String requete="" ;
    	
    	requete  =  "SELECT id_intervention FROM intervention";
    	
    	ResultSet r = gbd.execQuery(requete);
    	
    	try{
    	
    		while (r.next())
    		{
    			Intervention i = new Intervention();
    			i.construireIntervention(r.getInt("id_intervention"));
    			
    			v.add(i);
    		}
    	}catch (SQLException e)
    	{
    		e.printStackTrace();
    	}
    	
    	
    	
    	for (int i=0;i<v.size();i++)
    	{
    		Intervention in = (Intervention)v.elementAt(i);
    		
    	    
    	    String ligne[]={
    	    		in.getId_intervention().toString(),
    	    		in.getType_intervention().getNom_intervention(),
    	    		in.getDate_intervention().toString(),
    	    		in.getHeure_intervention().toString(),
    	    		in.getTechnicien().getNom_technicien(),
    	    		in.getTechnicien().getPrenom_technicien(),
    	    		in.getPc().getId_pc().toString()
    	    };
    	     
    	   model.addRow(ligne);
    	 }
    	
    	jTableIntervention.setModel(model);
    	
    	    	
    	
    	
    }
    
    
    
    
    
    
    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
	private void initComponents() {
		jLabel1 = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jPanel2 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		txtIdIntervention = new javax.swing.JTextField();
		txtDate = new JXDatePicker();
		String format[]={"dd/MM/yyyy"};
		txtDate.setFormats(format);
		
		cardType = new javax.swing.JPanel();
		jLabel9 = new javax.swing.JLabel();
		txtHeure = new javax.swing.JTextField();
		jLabel12 = new javax.swing.JLabel();
		txtMinutes = new javax.swing.JTextField();
		jPanel3 = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		cardIdTechnicien = new javax.swing.JPanel();
		txtCin = new javax.swing.JTextField();
		txtNom = new javax.swing.JTextField();
		txtPrenom = new javax.swing.JTextField();
		jPanel4 = new javax.swing.JPanel();
		jLabel10 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		cardPc = new javax.swing.JPanel();
		txtNomPc = new javax.swing.JTextField();
		jPanel5 = new javax.swing.JPanel();
		jbtSupprimer = new javax.swing.JButton();
		jbtModifier = new javax.swing.JButton();
		jbtAjouter = new javax.swing.JButton();
		jButtonAnnuler = new javax.swing.JButton();

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
		jLabel1.setText("Interventions");

		jPanel1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Interventions"));
		jTableIntervention.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null },
						{ null, null, null, null, null, null },
						{ null, null, null, null, null, null },
						{ null, null, null, null, null, null } }, new String[] {
						"id_intervention", "Type", "Date", "Heure",
						"Nom technicien", "Prenom technicien" }));
		jScrollPane1.setViewportView(jTableIntervention);

		org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				jPanel1Layout.createSequentialGroup().addContainerGap().add(
						jScrollPane1,
						org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 652,
						Short.MAX_VALUE).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				jPanel1Layout.createSequentialGroup().add(jScrollPane1,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 114,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(24, Short.MAX_VALUE)));

		jPanel2.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Propri\u00e9t\u00e9s"));
		jLabel2.setText("Id_intervention");

		jLabel3.setText("Type de l'intervention");

		jLabel4.setText("Date de l'intervention");

		org.jdesktop.layout.GroupLayout cardTypeLayout = new org.jdesktop.layout.GroupLayout(
				cardType);
		cardType.setLayout(cardTypeLayout);
		cardTypeLayout.setHorizontalGroup(cardTypeLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 135,
				Short.MAX_VALUE));
		cardTypeLayout.setVerticalGroup(cardTypeLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 18,
				Short.MAX_VALUE));

		jLabel9.setText("Heure de l'intervention");

		jLabel12.setText(":");

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
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(jLabel2).add(
																jLabel4).add(
																jLabel3).add(
																jLabel9))
										.add(44, 44, 44)
										.add(
												jPanel2Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING,
																false)
														.add(
																cardType,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.add(
																txtIdIntervention,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																135,
																Short.MAX_VALUE)
														.add(txtDate)
														.add(
																jPanel2Layout
																		.createSequentialGroup()
																		.add(
																				txtHeure,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				30,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jLabel12)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				txtMinutes,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				35,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(39, Short.MAX_VALUE)));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel2Layout
										.createSequentialGroup()
										.add(
												jPanel2Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(
																jPanel2Layout
																		.createSequentialGroup()
																		.add(
																				5,
																				5,
																				5)
																		.add(
																				jLabel2,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.add(
																txtIdIntervention,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel2Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING)
														.add(
																cardType,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(
																jLabel3,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																18,
																Short.MAX_VALUE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel2Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(
																jPanel2Layout
																		.createSequentialGroup()
																		.add(
																				5,
																				5,
																				5)
																		.add(
																				jLabel4,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.add(
																txtDate,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel2Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jLabel9)
														.add(
																txtHeure,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(jLabel12)
														.add(
																txtMinutes,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.add(16, 16, 16)));

		jPanel3.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Technicien"));
		jLabel5.setText("id_technicien");

		jLabel6.setText("CIN");

		jLabel7.setText("Nom");

		jLabel8.setText("Prenom");

		org.jdesktop.layout.GroupLayout cardIdTechnicienLayout = new org.jdesktop.layout.GroupLayout(
				cardIdTechnicien);
		cardIdTechnicien.setLayout(cardIdTechnicienLayout);
		cardIdTechnicienLayout.setHorizontalGroup(cardIdTechnicienLayout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(0, 124, Short.MAX_VALUE));
		cardIdTechnicienLayout.setVerticalGroup(cardIdTechnicienLayout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(0, 18, Short.MAX_VALUE));

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
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(jLabel5).add(
																jLabel6).add(
																jLabel7).add(
																jLabel8))
										.add(38, 38, 38)
										.add(
												jPanel3Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																cardIdTechnicien,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.add(
																txtCin,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																124,
																Short.MAX_VALUE)
														.add(
																txtNom,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																124,
																Short.MAX_VALUE)
														.add(
																txtPrenom,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																124,
																Short.MAX_VALUE))
										.addContainerGap()));
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
														.add(jLabel5)
														.add(
																cardIdTechnicien,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel3Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jLabel6)
														.add(
																txtCin,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel3Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(jLabel7)
														.add(
																txtNom,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel3Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(jLabel8)
														.add(
																txtPrenom,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(16, Short.MAX_VALUE)));

		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("PC"));
		jLabel10.setText("Id PC");

		jLabel11.setText("Nom");

		org.jdesktop.layout.GroupLayout cardPcLayout = new org.jdesktop.layout.GroupLayout(
				cardPc);
		cardPc.setLayout(cardPcLayout);
		cardPcLayout.setHorizontalGroup(cardPcLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 124,
				Short.MAX_VALUE));
		cardPcLayout.setVerticalGroup(cardPcLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 18,
				Short.MAX_VALUE));

		org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(
				jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout
				.setHorizontalGroup(jPanel4Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								org.jdesktop.layout.GroupLayout.TRAILING,
								jPanel4Layout
										.createSequentialGroup()
										.add(
												jPanel4Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(jLabel10).add(
																jLabel11))
										.add(85, 85, 85)
										.add(
												jPanel4Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING)
														.add(
																jPanel4Layout
																		.createSequentialGroup()
																		.add(
																				cardPc,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap())
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																jPanel4Layout
																		.createSequentialGroup()
																		.add(
																				txtNomPc,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				124,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap()))));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel4Layout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												jPanel4Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING)
														.add(
																cardPc,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(jLabel10))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.add(
												jPanel4Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jLabel11)
														.add(
																txtNomPc,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
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

		jButtonAnnuler.setText("Annuler");
		jButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonAnnulerActionPerformed(evt);
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
								jButtonAnnuler).addContainerGap(
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				jPanel5Layout.createParallelGroup(
						org.jdesktop.layout.GroupLayout.BASELINE).add(
						jbtModifier).add(jbtSupprimer).add(jbtAjouter).add(
						jButtonAnnuler)));

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
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				346,
																				346,
																				346)
																		.add(
																				jLabel1,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				139,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
														.add(
																layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING)
																						.add(
																								jPanel1,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																						.add(
																								jPanel5,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																						.add(
																								layout
																										.createSequentialGroup()
																										.add(
																												jPanel2,
																												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																										.add(
																												112,
																												112,
																												112)
																										.add(
																												layout
																														.createParallelGroup(
																																org.jdesktop.layout.GroupLayout.LEADING,
																																false)
																														.add(
																																jPanel4,
																																0,
																																250,
																																Short.MAX_VALUE)
																														.add(
																																jPanel3,
																																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE))))
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.add(jLabel1)
										.add(28, 28, 28)
										.add(
												jPanel1,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.add(19, 19, 19)
										.add(
												jPanel5,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING,
																false)
														.add(
																jPanel3,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.add(
																jPanel2,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel4,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(52, Short.MAX_VALUE)));
	}// </editor-fold>//GEN-END:initComponents

	private void jButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnnulerActionPerformed
	//GEN-FIRST:event_jButtonAnnulerActionPerformed
		// TODO add your handling code here:
		///	jPanel7.add(jDatedebut);
		modif = false;
		jbtModifier.setText("Modifier");
		ajout = false;
		jbtAjouter.setText("Ajouter");

		txtIdIntervention.setText("");
	    txtType.setText("");
	    txtHeure.setText("");
	    txtId_technicien.setText("");
	    txtNom.setText("");
	    txtPrenom.setText("");
	    txtCin.setText("");
	    txtId_technicien.setText("");
	    txtNomPc.setText("");

	    txtIdIntervention.setEnabled(false);
		txtType.setEnabled(false);
		txtDate.setEnabled(false);
		txtHeure.setEnabled(false);
		txtId_technicien.setEnabled(false);
		txtNom.setEnabled(false);
		txtPrenom.setEnabled(false);
		txtCin.setEnabled(false);
		txtId_technicien.setEnabled(false);
		txtNomPc.setEnabled(false);
		txtIdPc.setEnabled(false);
		txtMinutes.setEnabled(false);
	    
		((CardLayout) cardType.getLayout()).show(cardType,s1);
		((CardLayout) cardIdTechnicien.getLayout()).show(cardIdTechnicien,s3);
		((CardLayout) cardPc.getLayout()).show(cardPc,s5);
		
		jTableIntervention.clearSelection();
	}//GEN-LAST:event_jButtonAnnulerActionPerformed

	private void jbtAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAjouterActionPerformed
	//GEN-FIRST:event_jbtAjouterActionPerformed
		//GEN-FIRST:event_jbtAjouterActionPerformed
		/////////////////////////////////////////////
		if (modif == false) {
			if (ajout == false)

			{
				ajout = true;
				jbtAjouter.setText("Valider");

				cmbId.removeAllItems();
				cmbId.addItem(" ");
				cmbId.addItem("Choisir un Technicien");
				cmbId.setEnabled(true);

				cmbType.removeAllItems();
				Gbd gbd = new Gbd();
				gbd.connecter();
				String requete = "SELECT nom_intervention FROM type_intervention";
				ResultSet r = gbd.execQuery(requete);
				try {
					while(r.next())
					{
						cmbType.addItem(r.getString("nom_intervention"));
						
					}
					gbd.deconnecter();
				} catch (SQLException e) {
					 
					e.printStackTrace();
				}
				
				
				cmbIdPc.removeAllItems();
				cmbIdPc.addItem(" ");
				cmbIdPc.addItem("Choisir un PC");
				cmbIdPc.setEnabled(true);
				
				((CardLayout) cardType.getLayout()).show(cardType,s2);
				((CardLayout) cardIdTechnicien.getLayout()).show(cardIdTechnicien,s4);
				((CardLayout) cardPc.getLayout()).show(cardPc,s6);
						
				txtType.setEnabled(true);
				txtDate.setEnabled(true);
				txtHeure.setEnabled(true);
				txtId_technicien.setEnabled(true);
				txtNom.setEnabled(false);
				txtPrenom.setEnabled(false);
				txtCin.setEnabled(false);
				txtId_technicien.setEnabled(true);
				txtNomPc.setEnabled(false);
				txtMinutes.setEnabled(true);
				

				

			} else {
				ajout = false;
				jbtAjouter.setText("Ajouter");

				Intervention in = new Intervention();
				
				Type_intervention t = new Type_intervention();
				t.construireType_intervention(cmbType.getSelectedItem().toString());
				
				in.setType_intervention(t);
				
				in.setDate_intervention(new Date(txtDate.getDateInMillis()));
				
				Technicien tec = new Technicien();
				tec.construireTechnicien(Integer.parseInt(cmbId.getSelectedItem().toString()));
				in.setTechnicien(tec);
				
				
				Pc p = new Pc();
				p.construirePc(Integer.parseInt(cmbIdPc.getSelectedItem().toString()));
				
				in.setPc(p);
				
				
				try
				{
					int heure = Integer.parseInt(txtHeure.getText());
					int minutes = Integer.parseInt(txtMinutes.getText());
					Time time = new Time(heure,minutes,0);
					in.setHeure_intervention(time);					
						
					
				}catch(NumberFormatException e)
				{
					JXErrorDialog.showDialog(null, "Ajout" ,"Veuillez vérifier l'heure de l'intervension!!", "");
					
					
				}
				
				int res = in.insererIntervention();

				if (res==1)
				{
					Gbd gbd = new Gbd();
					gbd.connecter();
					String requete = "SELECT max(id_intervention) AS id FROM intervention";
					ResultSet r = gbd.execQuery(requete);
					try {
						r.next();
						in.setId_intervention(r.getInt("id"));
						v.add(in);
					} catch (SQLException e) {
					// 	TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
				
				
				
					String [] ligne = {
							in.getId_intervention().toString(),
							in.getType_intervention().getNom_intervention(),
							in.getDate_intervention().toString(),
							in.getHeure_intervention().toString(),
							in.getTechnicien().getNom_technicien(),
							in.getTechnicien().getPrenom_technicien()
							
					};
					
					model.addRow(ligne);
					JOptionPane.showConfirmDialog(this, "L'intervention a été ajouté avec succès!","Ajout",JOptionPane.DEFAULT_OPTION);
					
					jTableIntervention.setRowSelectionInterval(jTableIntervention.getRowCount()-1, jTableIntervention.getRowCount()-1);
					
					
				}
				else
				{
					JOptionPane.showConfirmDialog(this, "L'intervention n'a pas été ajouté!","Ajout",JOptionPane.ERROR);
				}
					
				
		}
	}		

}

	private void jbtModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtModifierActionPerformed
	//GEN-FIRST:event_jbtModifierActionPerformed
		// TODO add your handling code here:
		if (jTableIntervention.getSelectedRowCount() != 0 && ajout == false) {
			if (modif == false) {
				modif = true;
				jbtModifier.setText("Valider");
				//				
				cmbId.removeAllItems();
				cmbId.addItem(" ");
				cmbId.addItem("Choisir un Technicien");
				cmbId.setEnabled(true);
				cmbId.addItem(txtId_technicien.getText());
				cmbId.setSelectedItem(txtId_technicien.getText());
				
				
				cmbType.removeAllItems();
				Gbd gbd = new Gbd();
				gbd.connecter();
				String requete = "SELECT nom_intervention FROM type_intervention";
				ResultSet r = gbd.execQuery(requete);
				try {
					while(r.next())
					{
						cmbType.addItem(r.getString("nom_intervention"));
						
					}
					gbd.deconnecter();
				} catch (SQLException e) {
					 
					e.printStackTrace();
				}
				cmbType.setSelectedItem(txtType.getText());
				
				
				cmbIdPc.removeAllItems();
				cmbIdPc.addItem(" ");
				cmbIdPc.addItem("Choisir un PC");
				cmbIdPc.setEnabled(true);
				cmbIdPc.addItem(txtIdPc.getText());
				cmbIdPc.setSelectedItem(txtIdPc.getText());
				
				
				((CardLayout) cardType.getLayout()).show(cardType,s2);
				((CardLayout) cardIdTechnicien.getLayout()).show(cardIdTechnicien,s4);
				((CardLayout) cardPc.getLayout()).show(cardPc,s6);

				txtIdIntervention.setEnabled(true);
				txtType.setEnabled(true);
				txtDate.setEnabled(true);
				txtHeure.setEnabled(true);
				txtId_technicien.setEnabled(true);
				txtNom.setEnabled(true);
				txtPrenom.setEnabled(true);
				txtCin.setEnabled(true);
				txtId_technicien.setEnabled(true);
				txtNomPc.setEnabled(true);

			} 
			else {
				modif = false;
				jbtModifier.setText("Modifier");
				
				int reponse = JOptionPane.showConfirmDialog(this,"Etes vous sur de vouloir modifier cette intervention ?","Modification",JOptionPane.YES_NO_OPTION);
				if (reponse==0)
				{
					Intervention in = new Intervention();

					int indice = jTableIntervention.getSelectedRow();
					in = (Intervention) v.elementAt(indice);
					
					Type_intervention t = new Type_intervention();
					t.construireType_intervention(cmbType.getSelectedItem().toString());
					
					Pc p = new Pc();
					p.construirePc(Integer.parseInt(cmbIdPc.getSelectedItem().toString()));
					
					Technicien tech = new Technicien();
					tech.construireTechnicien(Integer.parseInt(cmbId.getSelectedItem().toString()));
					
					in.setType_intervention(t);
					in.setPc(p);
					in.setTechnicien(tech);
					in.setDate_intervention(new Date(txtDate.getDateInMillis()));
					in.setHeure_intervention(new Time(Integer.parseInt(txtHeure.getText()),Integer.parseInt(txtMinutes.getText()),0));
					int res = in.modifierIntervention();
					if (res==1)
					{
						JOptionPane.showConfirmDialog(this, "L'intervention a été modifié avec succès!","Modification",JOptionPane.DEFAULT_OPTION);
						
						v.set(indice,in);
						
						String [] ligne = {
								in.getId_intervention().toString(),
								in.getType_intervention().getNom_intervention(),
								in.getDate_intervention().toString(),
								in.getHeure_intervention().toString(),
								in.getTechnicien().getNom_technicien(),
								in.getTechnicien().getPrenom_technicien()
								
						};
						
						model.setValueAt(in.getId_intervention().toString(), indice, 0);
						model.setValueAt(in.getType_intervention().getNom_intervention(), indice, 1);
						model.setValueAt(in.getDate_intervention().toString(), indice, 2);
						model.setValueAt(in.getHeure_intervention().toString(), indice, 3);
						model.setValueAt(in.getTechnicien().getNom_technicien(), indice, 4);
						model.setValueAt(in.getTechnicien().getPrenom_technicien(), indice, 5);
						
					}
					else
					{
						JOptionPane.showConfirmDialog(null, "L'intervention n'a pas été modifié !!","Modification",JOptionPane.WARNING_MESSAGE);
						
					}
					
				}
				else
				{
					JOptionPane.showConfirmDialog(this, "L'intervention n'a pas été modifiée !","Modification",JOptionPane.WARNING_MESSAGE);
				}
					
					
				txtIdIntervention.setEnabled(false);
				txtType.setEnabled(false);
				txtDate.setEnabled(false);
				txtHeure.setEnabled(false);
				txtId_technicien.setEnabled(false);
				txtNom.setEnabled(false);
				txtPrenom.setEnabled(false);
				txtCin.setEnabled(false);
				txtId_technicien.setEnabled(false);
				txtNomPc.setEnabled(false);
				txtIdPc.setEnabled(false);
				txtMinutes.setEnabled(false);
				   
				((CardLayout) cardType.getLayout()).show(cardType,s1);
				((CardLayout) cardIdTechnicien.getLayout()).show(cardIdTechnicien,s3);
				((CardLayout) cardPc.getLayout()).show(cardPc,s5);
				
			}
		}
		else
		{
			JOptionPane.showConfirmDialog(null, "Veuillez sélectionnez une intervention à modifier!!","Modification",JOptionPane.DEFAULT_OPTION);
			
		}
	}//GEN-LAST:event_jbtModifierActionPerformed

	private void jbtSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSupprimerActionPerformed
	//GEN-FIRST:event_jbtSupprimerActionPerformed

		int reponse = -1;
		if (jTableIntervention.getSelectedRowCount() != 0) { // 0 : OUI
			// 1 : NON
			reponse = JOptionPane.showConfirmDialog(this,
					"Etes vous sur de vouloir supprimer cette intervention ?",
					"Suppression", JOptionPane.YES_NO_OPTION);
			if (reponse == 0) {
				int id = Integer.parseInt(jTableIntervention.getValueAt(
						jTableIntervention.getSelectedRow(), 0).toString());

				Intervention in = new Intervention();
				in.setId_intervention(id);
				
				int res = in.supprimerIntervention();
				
				if (res == 1) {
					v.remove(jTableIntervention.getSelectedRow());
					model.removeRow(jTableIntervention.getSelectedRow());
					JOptionPane.showConfirmDialog(this,
							"Intervention supprimée avec succès", "Suppression",
							JOptionPane.DEFAULT_OPTION);
					jTableIntervention.clearSelection();
				
				}
			} else {
				JOptionPane.showConfirmDialog(null,
						"L'intervention n'a pas été supprimée !!", "Suppression",
						JOptionPane.DEFAULT_OPTION);
			}

		} else {
			JOptionPane.showConfirmDialog(null,
					"Veuillez selectionner une intervention à supprimer!! ", "Suppression",
					JOptionPane.DEFAULT_OPTION);
		}
	}


// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel cardIdTechnicien;

	private javax.swing.JPanel cardPc;

	private javax.swing.JPanel cardType;

	private javax.swing.JButton jButtonAnnuler;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel10;

	private javax.swing.JLabel jLabel11;

	private javax.swing.JLabel jLabel12;

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

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JTable jTable1;

	private javax.swing.JButton jbtAjouter;

	private javax.swing.JButton jbtModifier;

	private javax.swing.JButton jbtSupprimer;

	private javax.swing.JTextField txtCin;

	private JXDatePicker txtDate;

	private javax.swing.JTextField txtHeure;

	private javax.swing.JTextField txtIdIntervention;

	private javax.swing.JTextField txtMinutes;

	private javax.swing.JTextField txtNom;

	private javax.swing.JTextField txtNomPc;

	private javax.swing.JTextField txtPrenom;
	// End of variables declaration//GEN-END:variables


	public JComboBox getCmbId() {
		return cmbId;
	}




	public void setCmbId(JComboBox cmbId) {
		this.cmbId = cmbId;
	}




	public JComboBox getCmbIdPc() {
		return cmbIdPc;
	}




	public void setCmbIdPc(JComboBox cmbIdPc) {
		this.cmbIdPc = cmbIdPc;
	}




	public javax.swing.JTextField getTxtNomPc() {
		return txtNomPc;
	}




	public void setTxtNomPc(javax.swing.JTextField txtNomPc) {
		this.txtNomPc = txtNomPc;
	}




	public javax.swing.JTextField getTxtCin() {
		return txtCin;
	}




	public void setTxtCin(javax.swing.JTextField txtCin) {
		this.txtCin = txtCin;
	}




	public javax.swing.JTextField getTxtNom() {
		return txtNom;
	}




	public void setTxtNom(javax.swing.JTextField txtNom) {
		this.txtNom = txtNom;
	}




	public javax.swing.JTextField getTxtPrenom() {
		return txtPrenom;
	}




	public void setTxtPrenom(javax.swing.JTextField txtPrenom) {
		this.txtPrenom = txtPrenom;
	}

}
