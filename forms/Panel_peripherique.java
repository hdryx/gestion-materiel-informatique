/*
 * Panel_peripherique.java
 *
 * Created on __DATE__, __TIME__
 */


package forms;
import gestion_materiel.*;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.*;

import java.util.*;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.event.TableModelListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.*;



import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Window;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.*;



import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.DefaultTableModel;

import javax.swing.*;
import java.awt.*;
import org.jdesktop.swingx.autocomplete.Configurator;
import org.jdesktop.swingx.border.DropShadowBorder;
import org.jdesktop.swingx.calendar.*;
import org.jdesktop.swingx.decorator.*;
import org.jdesktop.swingx.event.ProgressEvent;
import org.jdesktop.swingx.tips.*;
import org.jdesktop.swingx.treetable.*;
import org.jdesktop.swingx.tips.TipOfTheDayModel.*;
import org.jdesktop.swingx.decorator.Highlighter;





/**
 *
 * @author  __USER__
 */
public class Panel_peripherique extends javax.swing.JPanel{

	
	
	int id_pc=-1 ; 
	
	Vector v = new Vector();
	JXTable jTable1;
	JXTable jTable2;
	
	
	Object [][] test =null;
	Object [][] data2=null;
	String [] titre = {"Proprieté","Valeur"};
	String [] titre2 = {"Id","Type","Nom","Slot","Num Série","Pilote","Id_pc","Id_lieu"};
	
	ListIterator lst;
	ModelJTable mm;
	Peripherique p1;
	boolean a=true,ajout=false,modif=false,b=false,var=false;
	MainForm m;
	Type_lieu t;
	private DefaultTableModel model;
	private DefaultTableModel model2;
		
		
	JTextField txtIdLieu = new JTextField("             ");
	JTextField txtType = new JTextField("             ");
	JTextField txtIdSlot = new JTextField("             ");
	JTextField txtIdPc = new JTextField("             ");
	
	JComboBox cmbIdLieu = new JComboBox(titre);
    JComboBox cmbType = new JComboBox(titre);
    JComboBox cmbIdSlot = new JComboBox(titre);
    JComboBox cmbIdPc = new JComboBox(titre);
	
	String s1 = "card1_text";
    String s2 = "card1_box";
    
    String s3 = "card2_text";	
    String s4 = "card2_box";
    
    String s5 = "card3_text";
    String s6 = "card3_box";
    	
    String s7 = "card4_text";
    String s8 = "card4_box";
    	
    	
    /** Creates new form Panel_peripherique */
    public Panel_peripherique(MainForm m, int id) {
    	this.id_pc=id;
    	this.m = m;
    	jTable2 = new JXTable();
		jTable2.setHighlighters(
		        new HighlighterPipeline(new Highlighter[] {AlternateRowHighlighter.beige}
		));
				jTable2.setRolloverEnabled(true);
				jTable2.getHighlighters().addHighlighter(new RolloverHighlighter(Color.GRAY, Color.WHITE ));
				jTable2.setColumnControlVisible(true);
		
				jTable1 = new JXTable();
				jTable1.setHighlighters(
		        new HighlighterPipeline(new Highlighter[] {AlternateRowHighlighter.quickSilver}
		));
				jTable1.setRolloverEnabled(true);
				jTable1.getHighlighters().addHighlighter(new RolloverHighlighter(Color.GRAY, Color.WHITE ));
				jTable1.setColumnControlVisible(true);
    	
    	initComponents();
    	
    	checkbox1.setEnabled(false);
    	
    	model = new DefaultTableModel();
    	model2 = new DefaultTableModel();
    	
    	model.addColumn("Id");
   		model.addColumn("Type");
   		model.addColumn("Nom");
   		model.addColumn("Slot");
   		model.addColumn("Num Série");
   		model.addColumn("Pilote");
   		model.addColumn("Id_pc");
   		model.addColumn("Id_lieu");
    	
    	
    	
    	model2.addColumn("Proptiété");
    	model2.addColumn("Valeur");
    	
    	txtIdLieu.setEditable(false);
    	txtType.setEditable(false);
    	txtIdSlot.setEditable(false);
    	txtIdPc.setEditable(false);    	   	
    	txtTypeLieu.setEditable(false);
    	txtLieu.setEditable(false);
    	   	
    	
    	cmbIdLieu.removeAllItems();
    	cmbIdLieu.addItem("Choisir un Lieu");
    	cmbIdLieu.addItem("");
    	cmbIdLieu.setSelectedItem("");
    	
    	cmbIdPc.removeAllItems();
    	cmbIdPc.addItem("Choisir un PC");
    	cmbIdPc.addItem("");
    	cmbIdPc.setSelectedItem("");    	
    	
    	
    	   	
    	cmbIdLieu.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				cmbIdLieuItemStateChanged(evt);
			}
		});
		
		cmbIdPc.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				cmbIdPcItemStateChanged(evt);
			}
		});
    	
    	jPanel7.setLayout(new CardLayout());
    	cardType.setLayout(new CardLayout());
    	cardSlot.setLayout(new CardLayout());
    	cardIdPc.setLayout(new CardLayout());
    	
    	jPanel7.add(s1,txtIdLieu);
    	jPanel7.add(s2,cmbIdLieu);
    	
    	cardType.add(s3,txtType);
    	cardType.add(s4,cmbType);
    	
    	cardSlot.add(s5,txtIdSlot);
    	cardSlot.add(s6,cmbIdSlot);
    	
    	cardIdPc.add(s7,txtIdPc);
    	cardIdPc.add(s8,cmbIdPc);
    	
    	
    	((CardLayout) jPanel7.getLayout()).show(jPanel7,s1);
    	((CardLayout) cardType.getLayout()).show(cardType,s3);
    	((CardLayout) cardSlot.getLayout()).show(cardSlot,s5);
    	((CardLayout) cardIdPc.getLayout()).show(cardIdPc,s7);
    	
    	cmbType.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				cmbTypePeripheriqueItemStateChanged(evt);
			}
		});
		
    	
    	
    	
    	
    	
    	//Bouton modifier 
    	jbtModifier.addActionListener(new java.awt.event.ActionListener(){
    	
    		public void actionPerformed(java.awt.event.ActionEvent e)
    		{
    			jbtModifierActionPerformed();
    		}
    		
    			
    });
    	
    	
    
    	
    	
    	
    	jTable2.getSelectionModel().addListSelectionListener(new ListSelectionListener()
    	{
   			public void valueChanged(ListSelectionEvent lse)
   			{
   				
   				if (jTable2.getSelectedRow()!=-1 && ajout==false && modif==false)
   				{
   					
   					int ligne = jTable2.getSelectedRow();
   					Peripherique pp = (Peripherique)v.elementAt(ligne);
   					txtIdPeripherique.setText(String.valueOf(pp.getId_peripherique()));
   					txtType.setText(pp.getType_peripherique().getType());
   					txtNomPeripherique.setText(pp.getNom());
   					txtSerial.setText(pp.getNumero_serie());
   					txtPilote.setText(pp.getPilote());
   					txtIdLieu.setText(String.valueOf(pp.getLieu().getId_lieu()));
   					txtTypeLieu.setText(pp.getLieu().getTypeLieu().getNom_type());
   					txtLieu.setText(pp.getLieu().getNom());
   					
   					if (pp.getPc().getId_pc()!=0)
   					{
   						checkbox1.setState(true);
   						txtIdPc.setText(String.valueOf(pp.getPc().getId_pc()));
   						txtPc.setText(pp.getPc().getMarque()+" "+pp.getPc().getModele());
   					}
   					else
   					{
   						checkbox1.setState(false);
   						txtIdPc.setText("");
   						txtPc.setText("");
   					}
   					
   					txtIdSlot.setText(String.valueOf(pp.getSlot().getType()));
   					
   					Vector v2 = pp.getLesPeripherique_propriete();
   					Vector v3 = pp.getType_peripherique().getLesProprietes();
   					
   					System.out.println(jTable1.getRowCount());
   					
   					jTable1.setModel(model2);
   					
   					
   					while (jTable1.getRowCount()!=0)
   						model2.removeRow(0);
   						
   						
   					
   					if (v2.size()!=0)
   					{
   						Object [][] data = new Object[v2.size()][2];
   					
   						for (int i=0;i<v2.size();i++)
   						{
   							Peripherique_propriete pProp = (Peripherique_propriete)v2.elementAt(i);
   							Propriete prop = (Propriete)v3.elementAt(i);
   							data[i][0] = prop.getPropriete();
   							data[i][1] = pProp.getValeur();
   							System.out.println("prop.getpropriete="+prop.getPropriete()+"--prop.getValeur="+pProp.getValeur());
   							
   							String []s = {prop.getPropriete(),pProp.getValeur()};
   							model2.addRow(s);
   						}
    	
//   						test = data;
//   					jTable1.setModel(getModel());
   						
   						
   						
   						   						
   					}else
   					{
   						jTable1.setModel(new DefaultTableModel(titre,0));
   					}
   				
   				
   				}
 	
   			}
    	
    	});
    	
    	recupererPeripheriques();
       
    }
    
    
    
    
    public void jbtModifierActionPerformed()
    {
    	if (jTable2.getSelectedRowCount()!=0)
		{
			
			int ligne = jTable2.getSelectedRow();
			System.out.println("LIGNE="+ligne);
			
			if (modif==false)
			{
				modif=true;
				jbtModifier.setText("Valider");
				
				((CardLayout) jPanel7.getLayout()).show(jPanel7,s2);
				((CardLayout) cardType.getLayout()).show(cardType,s4);
				((CardLayout) cardSlot.getLayout()).show(cardSlot,s6);
				((CardLayout) cardIdPc.getLayout()).show(cardIdPc,s8);
				checkbox1.setEnabled(true);
				txtNomPeripherique.setEnabled(true);
				txtSerial.setEnabled(true);
				txtPilote.setEnabled(true);
				
			
				cmbIdSlot.removeAllItems();
				Gbd gbd = new Gbd();	
				gbd.connecter();
				String requete2 = "SELECT id_slot FROM slot";
				ResultSet r2 = gbd.execQuery(requete2);
				try
				{
					while (r2.next())
					{
						Slot s = new Slot();
						s.construireSlot(r2.getInt("id_slot"));
						cmbIdSlot.addItem(s.getType());
					}
					gbd.deconnecter();
				}catch(Exception ex)
				{
					System.out.println(ex.toString());
				}
				
				cmbIdSlot.setSelectedItem(txtIdSlot.getText());
				
				
				
				
				
				cmbType.removeAllItems();
				gbd.connecter();
				
				String requete = "SELECT id_type_peripherique FROM type_peripherique";
				ResultSet r = gbd.execQuery(requete);
				try
				{
					while (r.next())
					{
						Type_peripherique tp = new Type_peripherique();
						tp.construireTypePeripherique(r.getInt("id_type_peripherique"));
						cmbType.addItem(tp.getType());
						System.out.println(tp.getType());
					}
					gbd.deconnecter();
				}catch(Exception ex)
				{
					System.out.println(ex.toString());
					JXErrorDialog.showDialog(null,"DataBase ERROR !!!",ex);
				}
				
				cmbType.setSelectedItem(txtType.getText());
				
		  					
				
				for (int i=0; i<cmbIdLieu.getItemCount();i++)
				{
					if (cmbIdLieu.getItemAt(i)!="Choisir un Lieu")
						cmbIdLieu.removeItem(i);
				}
				
				cmbIdLieu.addItem(txtIdLieu.getText());
				cmbIdLieu.setSelectedItem(txtIdLieu.getText());
				
				
				var=true;
				for (int i=0; i<cmbIdPc.getItemCount();i++)
				{
					if (cmbIdPc.getItemAt(i)!="Choisir un PC")
						cmbIdPc.removeItemAt(i);
				}
				
				cmbIdPc.addItem(txtIdPc.getText());
				cmbIdPc.setSelectedItem(txtIdPc.getText());
				var=false;
				
				
				
			}
			else	//Modif=true
			{	
				modif=false;
				jbtModifier.setText("Modifier");
				int reponse = JOptionPane.showConfirmDialog(this,"Etes vous sur de vouloir modifier ce péiphérique ?","Modification",JOptionPane.YES_NO_OPTION);
				if (reponse==0)
				{
					
						
									
						Peripherique p1 = (Peripherique)v.elementAt(ligne);
								
						Type_peripherique t = new Type_peripherique();
						t.construireTypePeripherique(cmbType.getSelectedItem().toString());
						
						p1.setType_peripherique(t);
						p1.setNom(txtNomPeripherique.getText());
						p1.setNumero_serie(txtSerial.getText());
						p1.setPilote(txtPilote.getText());
						
						Slot s = new Slot();
						s.construireSlot(cmbIdSlot.getSelectedItem().toString());
						p1.setSlot(s);
						
						Lieu l = new Lieu();
						l.construireLieu(Integer.parseInt(cmbIdLieu.getSelectedItem().toString()));
						p1.setLieu(l);
						
						Pc pc = new Pc();
						try
						{
							pc.construirePc(Integer.parseInt(cmbIdPc.getSelectedItem().toString()));
						}catch (NumberFormatException e)
						{
							pc.setId_pc(null);
						}
						p1.setPc(pc);
						
						Garantie g = new Garantie();
						g.setId_garantie(1);
						p1.setGarantie(g);
						
						
						//Modification des caracteristiques
						for (int i=0; i< jTable1.getRowCount();i++)
						{
						
							Propriete p = new Propriete();
							p.construirePropriete(jTable1.getValueAt(i, 0).toString());
							System.out.println(p.getId_propriete());
							Gbd gbd2 = new Gbd();
							gbd2.connecter();
							String requete = "UPDATE peripherique_propriete SET valeur='"+
									jTable1.getValueAt(i, 1).toString()+
									"' WHERE id_peripherique="+p1.getId_peripherique()+
									" AND id_propriete="+p.getId_propriete();
							System.out.println(requete);
							gbd2.execUpdate(requete);
							
							
							
							
						}
						
						
					
						int i = p1.modifierPeripherique();
						
						if (i==1)
						{
							v.set(ligne, p1);
							
							model.setValueAt(p1.getId_peripherique(), ligne, 0);
							model.setValueAt(p1.getType_peripherique().getType(), ligne, 1);
							model.setValueAt(p1.getNom(), ligne, 2);
							model.setValueAt(p1.getSlot().getType(), ligne, 3);
							model.setValueAt(p1.getNumero_serie(), ligne, 4);
							model.setValueAt(p1.getPilote(), ligne, 5);
							model.setValueAt(p1.getPc().getId_pc(), ligne, 6);
							model.setValueAt(p1.getLieu().getId_lieu(), ligne, 7);
							JOptionPane.showConfirmDialog(this, "Le périphérique a été modifié avec succès!","Modification",JOptionPane.DEFAULT_OPTION);
							
						}
						else
						{
							JOptionPane.showConfirmDialog(null, "Le périphérique n'a pas été modifié !!","Modification",JOptionPane.WARNING_MESSAGE);
						}
						
						

			
				}
				else //Reponse de l'utilisateur = non
				{
					
					JOptionPane.showConfirmDialog(this, "Le périphérique n'a pas été modifié !","Modification",JOptionPane.DEFAULT_OPTION);
				}
				
				
				((CardLayout) jPanel7.getLayout()).show(jPanel7,s1);
				((CardLayout) cardType.getLayout()).show(cardType,s3);
				((CardLayout) cardSlot.getLayout()).show(cardSlot,s5);
				((CardLayout) cardIdPc.getLayout()).show(cardIdPc,s7);
				
				
				txtIdLieu.setEnabled(false);
				txtIdPc.setEnabled(false);
				txtIdSlot.setEnabled(false);
				txtNomPeripherique.setEnabled(false);
				txtPc.setEnabled(false);
				txtPilote.setEnabled(false);
				txtSerial.setEnabled(false);
				checkbox1.setEnabled(false);
			}	
				
		}
				
				
}  			
			
			
    
    	
    

    
    
    
    
    
    
    public void recupererPeripheriques()
    {
    	String requete="";
    	
    	Gbd gbd = new Gbd();
    	gbd.connecter();
    	if(this.id_pc==-1)
    	 requete  =  "SELECT id_peripherique FROM peripherique";
    	
    	else
    		requete ="SELECT id_peripherique FROM peripherique WHERE id_pc="+id_pc;
    	
    	ResultSet r = gbd.execQuery(requete);
    	
    	try{
    	
    		while (r.next())
    		{
    			Peripherique p = new Peripherique();
    			p.construirePeripherique(r.getInt("id_peripherique"));
    			v.add(p);
    		}
    	}catch (SQLException e)
    	{
    		e.printStackTrace();
    		JXErrorDialog.showDialog(null,"DataBase ERROR !!!",e);
    	}
    	
    	lst = v.listIterator();
    	System.out.println("SIZE="+v.size());
    	
    	
    	Object [][]data3 = new Object[v.size()][8];
    	
    	for (int i=0;i<v.size();i++)
    	{
    		p1 = (Peripherique)lst.next();
    		data3[i][0] = p1.getId_peripherique();
    		data3[i][1] = p1.getType_peripherique().getType();
    		data3[i][2] = p1.getNom();
    		data3[i][3] = p1.getSlot().getType();
    		data3[i][4] = p1.getNumero_serie();
    		data3[i][5] = p1.getPilote();
    		data3[i][6] = p1.getLieu().getId_lieu();
    		data3[i][7] = p1.getPc().getId_pc();
    		    	
    		String [] s = {String.valueOf(p1.getId_peripherique()),
    						p1.getType_peripherique().getType(),
    						p1.getNom(),
    						p1.getSlot().getType(),
    						p1.getNumero_serie(),
    						p1.getPilote(),
    						String.valueOf(p1.getPc().getId_pc()),
    						String.valueOf(p1.getLieu().getId_lieu())
    					};
    		model.addRow(s);
    	}
    	
    	data2 = data3;
    	

//    	jTable2.setModel(getModel2());
    	jTable2.setModel(model);
    	jTable1.setModel(new DefaultTableModel(titre,5));
    	
    	
//    	
    }
    
    
    
    
   public TableModel getModel()
   {
	   
	   return new ModelJTable(test,titre);
   
   }
   
   
   
   public TableModel getModel2()
   {
	   
	   return new ModelJTable(data2,titre2);
   
   }
//   

	// <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
	private void initComponents() {
		jLabel1 = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		txtIdPeripherique = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		txtNomPeripherique = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		txtSerial = new javax.swing.JTextField();
		jLabel6 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		txtPilote = new javax.swing.JTextField();
		cardType = new javax.swing.JPanel();
		cardSlot = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
//		jTable1 = new javax.swing.JTable();
		jPanel4 = new javax.swing.JPanel();
		jbtSupprimer = new javax.swing.JButton();
		jbtModifier = new javax.swing.JButton();
		jbtAjouter = new javax.swing.JButton();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		jLabel7 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		txtPc = new javax.swing.JTextField();
		cardIdPc = new javax.swing.JPanel();
		checkbox1 = new java.awt.Checkbox();
		jPanel6 = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
//		jTable2 = new javax.swing.JTable();
		jPanel8 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		jLabel12 = new javax.swing.JLabel();
		txtTypeLieu = new javax.swing.JTextField();
		jLabel11 = new javax.swing.JLabel();
		txtLieu = new javax.swing.JTextField();
		jLabel8 = new javax.swing.JLabel();
		jPanel7 = new javax.swing.JPanel();

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("P\u00e9riph\u00e9riques");

		jPanel1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Propri\u00e9t\u00e9s"));
		jLabel2.setText("Id p\u00e9riph\u00e9rique");

		txtIdPeripherique.setEnabled(false);
		txtIdPeripherique
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						txtIdPeripheriqueActionPerformed(evt);
					}
				});

		jLabel3.setText("Type p\u00e9riph\u00e9rique");

		txtNomPeripherique.setEnabled(false);

		jLabel4.setText("Nom");

		jLabel5.setText("Num\u00e9ro de s\u00e9rie");

		txtSerial.setEnabled(false);

		jLabel6.setText("Slot");

		jLabel9.setText("Chemin du pilote");

		txtPilote.setEnabled(false);

		org.jdesktop.layout.GroupLayout cardTypeLayout = new org.jdesktop.layout.GroupLayout(
				cardType);
		cardType.setLayout(cardTypeLayout);
		cardTypeLayout.setHorizontalGroup(cardTypeLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 201,
				Short.MAX_VALUE));
		cardTypeLayout.setVerticalGroup(cardTypeLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 20,
				Short.MAX_VALUE));

		org.jdesktop.layout.GroupLayout cardSlotLayout = new org.jdesktop.layout.GroupLayout(
				cardSlot);
		cardSlot.setLayout(cardSlotLayout);
		cardSlotLayout.setHorizontalGroup(cardSlotLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 201,
				Short.MAX_VALUE));
		cardSlotLayout.setVerticalGroup(cardSlotLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 19,
				Short.MAX_VALUE));

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
																jLabel6).add(
																jLabel4).add(
																jLabel9).add(
																jLabel2).add(
																jLabel3))
										.add(18, 18, 18)
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																cardSlot,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.add(
																cardType,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.add(
																txtIdPeripherique,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																201,
																Short.MAX_VALUE)
														.add(
																txtSerial,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																201,
																Short.MAX_VALUE)
														.add(
																org.jdesktop.layout.GroupLayout.TRAILING,
																txtNomPeripherique,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																201,
																Short.MAX_VALUE)
														.add(
																txtPilote,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																201,
																Short.MAX_VALUE))
										.add(20, 20, 20)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel1Layout
										.createSequentialGroup()
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jLabel2)
														.add(
																txtIdPeripherique,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																cardType,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(jLabel3))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jLabel4)
														.add(
																txtNomPeripherique,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jLabel5)
														.add(
																txtSerial,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(jLabel6)
														.add(
																cardSlot,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jLabel9)
														.add(
																txtPilote,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.add(138, 138, 138)));

		jPanel2.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Caract\u00e9ristiques"));
		jTable1
				.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
					public void propertyChange(
							java.beans.PropertyChangeEvent evt) {
						jTable1PropertyChange(evt);
					}
				});

		jScrollPane1.setViewportView(jTable1);

		org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jScrollPane1,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 213,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jScrollPane1,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 214,
				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE));

		jbtSupprimer.setText("Supprimer");
		jbtSupprimer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtSupprimerActionPerformed(evt);
			}
		});

		jbtModifier.setText("Modifier");
		jbtModifier.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				jbtModifierActionPerformed(evt);
			}
		});

		jbtAjouter.setText("Nouveau");
		jbtAjouter.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtAjouterActionPerformed(evt);
			}
		});

		jButton1.setText("Intervention");

		jButton2.setText("Rechercher");

		org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(
				jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				jPanel4Layout.createSequentialGroup().add(jbtSupprimer)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jbtModifier).addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jbtAjouter).addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jButton1).addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jButton2)));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				jPanel4Layout.createParallelGroup(
						org.jdesktop.layout.GroupLayout.BASELINE).add(
						jbtSupprimer).add(jbtModifier).add(jbtAjouter).add(
						jButton1).add(jButton2)));

		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Pc"));
		jLabel7.setText("Id PC");

		jLabel10.setText("Nom");

		txtPc.setEnabled(false);
		txtPc.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtPcActionPerformed(evt);
			}
		});

		org.jdesktop.layout.GroupLayout cardIdPcLayout = new org.jdesktop.layout.GroupLayout(
				cardIdPc);
		cardIdPc.setLayout(cardIdPcLayout);
		cardIdPcLayout.setHorizontalGroup(cardIdPcLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 171,
				Short.MAX_VALUE));
		cardIdPcLayout.setVerticalGroup(cardIdPcLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 16,
				Short.MAX_VALUE));

		checkbox1.setFont(new java.awt.Font("Tahoma", 0, 11));
		checkbox1
				.setLabel("    Ce p\u00e9riph\u00e9rique apparient \u00e0 un Pc");
		checkbox1.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				checkbox1ItemStateChanged(evt);
			}
		});

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
										.addContainerGap()
										.add(
												jPanel3Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING,
																false)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																checkbox1,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																jPanel3Layout
																		.createSequentialGroup()
																		.add(
																				jPanel3Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING)
																						.add(
																								jLabel10)
																						.add(
																								jLabel7))
																		.add(
																				13,
																				13,
																				13)
																		.add(
																				jPanel3Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING,
																								false)
																						.add(
																								txtPc)
																						.add(
																								cardIdPc,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
										.addContainerGap(58, Short.MAX_VALUE)));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								org.jdesktop.layout.GroupLayout.TRAILING,
								jPanel3Layout
										.createSequentialGroup()
										.add(
												checkbox1,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.add(
												jPanel3Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING)
														.add(jLabel7)
														.add(
																cardIdPc,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel3Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jLabel10)
														.add(
																txtPc,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.add(126, 126, 126)));

		jPanel6.setBorder(javax.swing.BorderFactory
				.createTitledBorder("P\u00e9riph\u00e9riques"));
		jTable2.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {
						{ null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null } },
				new String[] { "ID", "Type", "Nom", "Slot", "Numéro série",
						"Pilote", "Id_Pc", "Id_lieu" }));
		jScrollPane2.setViewportView(jTable2);

		org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(
				jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jScrollPane2,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 608,
				Short.MAX_VALUE));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jScrollPane2,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 214,
				Short.MAX_VALUE));

		org.jdesktop.layout.GroupLayout jPanel8Layout = new org.jdesktop.layout.GroupLayout(
				jPanel8);
		jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 0,
				Short.MAX_VALUE));
		jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 0,
				Short.MAX_VALUE));

		jPanel5
				.setBorder(javax.swing.BorderFactory
						.createTitledBorder("Local"));
		jLabel12.setText("Id Lieu");

		jLabel11.setText("Type lieu");

		jLabel8.setText("Lieu");

		org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(
				jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 152,
				Short.MAX_VALUE));
		jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 18,
				Short.MAX_VALUE));

		org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(
				jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout
				.setHorizontalGroup(jPanel5Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel5Layout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												jPanel5Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																jPanel5Layout
																		.createSequentialGroup()
																		.add(
																				jLabel8)
																		.addContainerGap(
																				249,
																				Short.MAX_VALUE))
														.add(
																jPanel5Layout
																		.createSequentialGroup()
																		.add(
																				jPanel5Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING)
																						.add(
																								jLabel11)
																						.add(
																								jLabel12))
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jPanel5Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING)
																						.add(
																								jPanel7,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.add(
																								txtLieu,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								152,
																								Short.MAX_VALUE)
																						.add(
																								txtTypeLieu,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								152,
																								Short.MAX_VALUE))
																		.add(
																				69,
																				69,
																				69)))));
		jPanel5Layout
				.setVerticalGroup(jPanel5Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel5Layout
										.createSequentialGroup()
										.add(
												jPanel5Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(jLabel12)
														.add(
																jPanel7,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel5Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(jLabel11)
														.add(
																txtTypeLieu,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel5Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(jLabel8)
														.add(
																txtLieu,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));

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
																				407,
																				407,
																				407)
																		.add(
																				jLabel1))
														.add(
																layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.add(
																				jPanel6,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jPanel2,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
														.add(
																layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.add(
																				jPanel1,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.add(
																				59,
																				59,
																				59)
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING,
																								false)
																						.add(
																								jPanel5,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.add(
																								jPanel3,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																		.add(
																				132,
																				132,
																				132)
																		.add(
																				jPanel8,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
														.add(
																layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.add(
																				jPanel4,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(216, Short.MAX_VALUE)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.addContainerGap(
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.add(jLabel1)
										.add(65, 65, 65)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING,
																false)
														.add(
																org.jdesktop.layout.GroupLayout.TRAILING,
																jPanel6,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.add(
																org.jdesktop.layout.GroupLayout.TRAILING,
																jPanel2,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				129,
																				129,
																				129)
																		.add(
																				jPanel8,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				6,
																				6,
																				6)
																		.add(
																				jPanel4,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.add(
																				26,
																				26,
																				26)
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING)
																						.add(
																								layout
																										.createSequentialGroup()
																										.add(
																												jPanel3,
																												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																												117,
																												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												org.jdesktop.layout.LayoutStyle.RELATED)
																										.add(
																												jPanel5,
																												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
																						.add(
																								jPanel1,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								197,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))));
	}// </editor-fold>//GEN-END:initComponents

	private void checkbox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkbox1ItemStateChanged
	 
	 if (checkbox1.getState()==true)
	 {
	 
		 cmbIdLieu.setEnabled(false);
		 cmbIdLieu.addItem("");
		 cmbIdLieu.setSelectedItem("");
		 txtTypeLieu.setText("");
		 txtLieu.setText("");
		 cmbIdPc.setEnabled(true);
	 }
	 else
	 {
		 
		 cmbIdLieu.setEnabled(true);
		 txtTypeLieu.setText("");
		 cmbIdPc.setEnabled(false);
		 cmbIdPc.addItem("");
		 cmbIdPc.setSelectedItem("");
		 txtPc.setText("");
	 }
	 
	}//GEN-LAST:event_checkbox1ItemStateChanged


	

	private void txtPcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPcActionPerformed
	// TODO add your handling code here:
	}//GEN-LAST:event_txtPcActionPerformed


	public void cmbPcActionPerformed(java.awt.event.ActionEvent evt){
	}
	public void jTable1PropertyChange(java.beans.PropertyChangeEvent evt){
	}
	public void txtIdPeripheriqueActionPerformed(java.awt.event.ActionEvent evt){
	}



	private void cmbIdPcItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPcItemStateChanged
		
		if (var==false)
		if (cmbIdPc.getItemCount()!=0)
			if (evt.getStateChange()==1)
				if (cmbIdPc.getSelectedItem().toString()=="Choisir un PC")
				{	
					Dialog_choixPc d = new Dialog_choixPc(m,true,this);
					d.setVisible(true);
					d.setLocationRelativeTo(this);
					
				}
	}
	
	
	
	
	private void cmbIdLieuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPcItemStateChanged
		
		
		if (cmbIdLieu.getItemCount()!=0)
			if (evt.getStateChange()==1)
				if (cmbIdLieu.getSelectedItem().toString()=="Choisir un Lieu")
				{	
					
					Dialog_choixLieu d = new Dialog_choixLieu(m,true,1);
					d.pack();
					d.setVisible(true);
					d.setLocationRelativeTo(this);
					
				}
	}
	
	
	
	
	
	
	
	
	private void jbtSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSupprimerActionPerformed
		
		int reponse=-1;
		if (jTable2.getSelectedRowCount()!=0)
		{	// 0 : OUI
			// 1 : NON
			reponse = JOptionPane.showConfirmDialog(this,"Etes vous sur de vouloir supprimer ce péiphérique ?","Suppression",JOptionPane.YES_NO_OPTION);
			if (reponse==0)
			{
				int id = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
				
				Peripherique periph = new Peripherique();
				periph.setId_peripherique(id);
				int res = periph.supprimerPeripherique();
				System.out.println("id = "+id+"  res="+res);
				//int res=0;
				if (res==1)
				{
					v.remove(jTable2.getSelectedRow());
					model.removeRow(jTable2.getSelectedRow());
					JOptionPane.showConfirmDialog(this, "Peripherique supprimé avec succès","Suppression",JOptionPane.DEFAULT_OPTION);
					//jTable1.setModel(new DefaultTableModel());
					
					//Vider la JTable des proptietes
					while (jTable1.getRowCount()!=0)
						model2.removeRow(0);
						
					//Restaurer les combobox a la place des TextFields
					((CardLayout) jPanel7.getLayout()).show(jPanel7,s1);
					((CardLayout) cardType.getLayout()).show(cardType,s3);
					((CardLayout) cardSlot.getLayout()).show(cardSlot,s5);
					((CardLayout) cardIdPc.getLayout()).show(cardIdPc,s7);
					
					//Effacer le contenu des TextFields
					txtIdPeripherique.setText("");
					txtType.setText("");
					txtNomPeripherique.setText("");
					txtSerial.setText("");
					txtPilote.setText("");
					txtIdLieu.setText("");
					txtTypeLieu.setText("");
					txtLieu.setText("");
					txtIdPc.setText("");
					txtPc.setText("");
					txtIdSlot.setText("");
					
					
				}
				else
				{
					JOptionPane.showConfirmDialog(null, "Le périphérique n'a pas été supprimé !!","Suppression",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		}
			
			
		
		
	}//GEN-LAST:event_jbtSupprimerActionPerformed




	
	private void cmbTypePeripheriqueItemStateChanged(
			java.awt.event.ItemEvent evt) {
		
		if (evt.getStateChange()==1 && ajout==true)
		{		
			Type_peripherique tp2 = new Type_peripherique();

			tp2.construireTypePeripherique(cmbType.getSelectedItem().toString());
			System.out.println("Type_peripherique = "+tp2.getType());
			
			Vector lesTypes = tp2.getLesProprietes();
			if (lesTypes.size()!=0)
			{
				Object[][] data = new Object[lesTypes.size()][2];
				for (int i=0;i<lesTypes.size();i++)
				{
					Propriete propriete = (Propriete)lesTypes.elementAt(i);
					data[i][0]=propriete.getPropriete();
					System.out.println("Propriete "+i+" "+propriete.getPropriete()); 	
				}
			
				this.test = data;
			
				ModelJTable mt = new ModelJTable(data,titre);
				mt.modifier=true;
				jTable1.setModel(mt);
			}
			else
			{
				jTable1.setModel(new DefaultTableModel(titre,5));
			}
		}
		
					
		
	}





	/**
	*
	* L'ecouteur du bouton jbtAjouter (Ajouter)
	*
	* Il permet d'ajouter un peripherique a la base
	*
	*
	**/
	private void jbtAjouterActionPerformed(java.awt.event.ActionEvent evt) {
	
		if (ajout==false)
		{
		
			ajout=true;
			jbtAjouter.setText("Valider");			
			checkbox1.setEnabled(true);
			txtIdPeripherique.setText("");
			//txtIdPeripherique.setEditable(true);
			//txtIdPeripherique.setEnabled(true);
		
			txtNomPeripherique.setText("");
			txtNomPeripherique.setEditable(true);
			txtNomPeripherique.setEnabled(true);
		
			txtPilote.setText("");
			txtPilote.setEditable(true);
			txtPilote.setEnabled(true);
		
			txtSerial.setText("");
			txtSerial.setEditable(true);
			txtSerial.setEnabled(true);
		
			
			((CardLayout) jPanel7.getLayout()).show(jPanel7,s2);
			((CardLayout) cardType.getLayout()).show(cardType,s4);
			((CardLayout) cardSlot.getLayout()).show(cardSlot,s6);
			((CardLayout) cardIdPc.getLayout()).show(cardIdPc,s8);
			
			
			
			cmbIdLieu.removeAllItems();
			cmbIdLieu.addItem(" ");
			cmbIdLieu.addItem("Choisir un Lieu");
			cmbIdLieu.setEnabled(true);
			
			
			
			
			
			cmbIdPc.removeAllItems();
			cmbIdPc.addItem(" ");
			cmbIdPc.addItem("Choisir un PC");
			cmbIdPc.setEnabled(true);
				
		
			cmbType.setEnabled(true);
			cmbType.removeAllItems();
		
		
			Gbd gbd = new Gbd();
			gbd.connecter();
		
			String requete = "SELECT id_type_peripherique FROM type_peripherique";
			ResultSet r = gbd.execQuery(requete);
			try
			{
				while (r.next())
				{
					Type_peripherique tp = new Type_peripherique();
					tp.construireTypePeripherique(r.getInt("id_type_peripherique"));
					cmbType.addItem(tp.getType());
					System.out.println(tp.getType());
				}
				gbd.deconnecter();
			}catch(Exception e)
			{
				System.out.println(e.toString());
				JXErrorDialog.showDialog(null,"DataBase ERROR !!!",e);
			}
			
			
			
			
			cmbIdSlot.removeAllItems();
						
			gbd.connecter();
			String requete2 = "SELECT id_slot FROM slot";
			ResultSet r2 = gbd.execQuery(requete2);
			try
			{
				while (r2.next())
				{
					Slot s = new Slot();
					s.construireSlot(r2.getInt("id_slot"));
					cmbIdSlot.addItem(s.getType());
				}
				gbd.deconnecter();
			}catch(Exception e)
			{
				System.out.println(e.toString());
			}
			
		Type_peripherique tp2 = new Type_peripherique();
		tp2.construireTypePeripherique(cmbType.getSelectedItem().toString());
		Vector lesTypes = tp2.getLesProprietes();
		Object[][] data = new Object[lesTypes.size()][2];
		for (int i=0;i<lesTypes.size();i++)
		{
					Propriete propriete = (Propriete)lesTypes.elementAt(i);
					data[i][0]=propriete.getPropriete();
					System.out.println(propriete.getPropriete()); 	
		}
		
		
		ModelJTable m = new ModelJTable(data,titre);
		m.modifier=true;
		jTable1.setModel(m);
		b=true;
		
		
		}
		else
		{
			
			
			ajout=false;
			jbtAjouter.setText("Ajouter");
			Peripherique p = new Peripherique();
			
			try
			{
			
			
				p.setNom(txtNomPeripherique.getText());
				p.setNumero_serie(txtSerial.getText());
				p.setPilote(txtPilote.getText());
			
				Lieu l = new Lieu();
				l.setId_lieu(Integer.parseInt(cmbIdLieu.getSelectedItem().toString()));			
				p.setLieu(l);
			
				Slot s = new Slot();
				s.construireSlot(String.valueOf(cmbIdSlot.getSelectedItem()));
				p.setSlot(s);
			
			
					
				Garantie g = new Garantie();
				g.setId_garantie(null);
				p.setGarantie(g);
			
				Type_peripherique t = new Type_peripherique();
				t.construireTypePeripherique(cmbType.getSelectedItem().toString());
			
				p.setType_peripherique(t);

			
			
				Pc pc =new Pc();
			
				if (cmbIdPc.getSelectedItem().toString()!="")
					pc.setId_pc(Integer.parseInt(cmbIdPc.getSelectedItem().toString()));
				else
					pc.setId_pc(null);
				
				

				p.setPc(pc);
				
				}
				catch(Exception e)
				{
					JXErrorDialog.showDialog(null,"Erreur",e);
				}
				int nb = p.insererPeripherique();
				if (nb==1)
				{
					String requete = "SELECT max(id_peripherique) AS id FROM peripherique";
					Gbd gbd = new Gbd();
					gbd.connecter();
					ResultSet r = gbd.execQuery(requete);
				
				int id_peripherique=0;
				try
				{
					r.next();
					id_peripherique = r.getInt("id");
					
					gbd.deconnecter();
				}catch(Exception e)
				{
					System.out.println(e.toString());
				}
				
				
			
				
				for (int i=0;i<jTable1.getModel().getRowCount();i++)
				{
					System.out.println(jTable1.getModel().getValueAt(i,0)+"==>"+jTable1.getModel().getValueAt(i,1));
					Propriete prop = new Propriete();
					prop.construirePropriete(jTable1.getModel().getValueAt(i,0).toString());
					Peripherique_propriete pp = new Peripherique_propriete();
					pp.setId_peripherique(id_peripherique);
					pp.setId_propriete(prop.getId_propriete());
					pp.setValeur(jTable1.getModel().getValueAt(i,1).toString());
					pp.insererPeripherique_propriete();
					
					System.out.println("peripherique_propriete = "+pp.getId_peripherique()+"--"+pp.getId_propriete()+"--"+pp.getValeur());
				}
				
				
		
				
				Peripherique peripherique  = new Peripherique();
				peripherique.construirePeripherique(id_peripherique);
				v.add(peripherique);
				
				
				
				String [] ligne = {
							String.valueOf(peripherique.getId_peripherique()),
    						peripherique.getType_peripherique().getType(),
    						peripherique.getNom(),
    						peripherique.getSlot().getType(),
    						peripherique.getNumero_serie(),
    						peripherique.getPilote(),
    						String.valueOf(peripherique.getLieu().getId_lieu()),
    						String.valueOf(peripherique.getPc().getId_pc())
    					};
    					
    			JOptionPane.showConfirmDialog(this, "Le périphérique a été ajouté avec succès!","Ajout",JOptionPane.DEFAULT_OPTION);
				model.addRow(ligne);
				
				System.out.println("jtable couunt = "+jTable2.getRowCount());
				jTable2.setRowSelectionInterval(jTable2.getRowCount()-1, jTable2.getRowCount()-1);
				
				
				((CardLayout) jPanel7.getLayout()).show(jPanel7,s1);
				((CardLayout) cardType.getLayout()).show(cardType,s3);
				((CardLayout) cardSlot.getLayout()).show(cardSlot,s5);
				((CardLayout) cardIdPc.getLayout()).show(cardIdPc,s7);
				
				
				txtIdLieu.setEnabled(false);
				txtIdPc.setEnabled(false);
				txtIdSlot.setEnabled(false);
				txtNomPeripherique.setEnabled(false);
				txtPc.setEnabled(false);
				txtPilote.setEnabled(false);
				txtSerial.setEnabled(false);
				checkbox1.setEnabled(false);
				ajout=false;
				jbtAjouter.setText("Nouveau");				
				
				
				
				
			}
		}
}





	

	public void actionPerformed(java.awt.event.ActionEvent e)
	{
		if (e.getSource()== jTable1)
		{
			System.out.println("EVENEMENt !!!!");
		}
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel cardIdPc;

	private javax.swing.JPanel cardSlot;

	private javax.swing.JPanel cardType;

	private java.awt.Checkbox checkbox1;

	private javax.swing.JButton jButton1;

	private javax.swing.JButton jButton2;

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

	private javax.swing.JPanel jPanel6;

	private javax.swing.JPanel jPanel7;

	private javax.swing.JPanel jPanel8;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JScrollPane jScrollPane2;

//	private javax.swing.JTable jTable1;
//
//	private javax.swing.JTable jTable2;

	private javax.swing.JButton jbtAjouter;

	private javax.swing.JButton jbtModifier;

	private javax.swing.JButton jbtSupprimer;

	private javax.swing.JTextField txtIdPeripherique;

	private javax.swing.JTextField txtLieu;

	private javax.swing.JTextField txtNomPeripherique;

	private javax.swing.JTextField txtPc;

	private javax.swing.JTextField txtPilote;

	private javax.swing.JTextField txtSerial;

	private javax.swing.JTextField txtTypeLieu;

	// End of variables declaration//GEN-END:variables


	public JComboBox getCmbIdPc() {
		return cmbIdPc;
	}



	public void setCmbIdPc(JComboBox cmbIdPc) {
		this.cmbIdPc = cmbIdPc;
	}



	public javax.swing.JTextField getTxtPc() {
		return txtPc;
	}



	public void setTxtPc(javax.swing.JTextField txtPc) {
		this.txtPc = txtPc;
	}



	public javax.swing.JPanel getCardIdPc() {
		return cardIdPc;
	}



	public void setCardIdPc(javax.swing.JPanel cardIdPc) {
		this.cardIdPc = cardIdPc;
	}



	public JTextField getTxtIdLieu() {
		return txtIdLieu;
	}



	public void setTxtIdLieu(JTextField txtIdLieu) {
		this.txtIdLieu = txtIdLieu;
	}



	public javax.swing.JTextField getTxtLieu() {
		return txtLieu;
	}



	public void setTxtLieu(javax.swing.JTextField txtLieu) {
		this.txtLieu = txtLieu;
	}



	public javax.swing.JTextField getTxtTypeLieu() {
		return txtTypeLieu;
	}



	public void setTxtTypeLieu(javax.swing.JTextField txtTypeLieu) {
		this.txtTypeLieu = txtTypeLieu;
	}



	public JComboBox getCmbIdLieu() {
		return cmbIdLieu;
	}



	public void setCmbIdLieu(JComboBox cmbIdLieu) {
		this.cmbIdLieu = cmbIdLieu;
	}



}
