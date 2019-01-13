/*
 * Panel_Iad.java
 *
 * Created on __DATE__, __TIME__
 */

package forms;

import gestion_materiel.Gbd;

import java.net.*;
import org.jdesktop.swingx.*;
import java.lang.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.*;
import javax.swing.SwingUtilities;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.sql.ResultSet;
import java.util.*;
/**
 *
 * @author  __USER__
 */
public class Panel_Iad extends javax.swing.JPanel {


	DefaultTableModel model;
	
	
	
    /** Creates new form Panel_Iad */
    public Panel_Iad() {
        initComponents();
        model = new DefaultTableModel();
        
        model.addColumn("Adresse IP");
        model.addColumn("Port");
        
        
        jTable2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        
        
        
        jTable2.setModel(model);
        
    }

   
   
   
   
   
   
   
	private void initComponents() {
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jLabel1 = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		txtIpS = new javax.swing.JTextField();
		txtIpE = new javax.swing.JTextField();
		jbtCopy = new javax.swing.JButton();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTable2 = new javax.swing.JTable();
		jButton3 = new javax.swing.JButton();
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});
		
		


		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null },
						{ null, null, null, null } }, new String[] { "Title 1",
						"Title 2", "Title 3", "Title 4" }));
		jScrollPane1.setViewportView(jTable1);

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
		jLabel1.setText("Inventaire \u00e0 distance");

		jPanel1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Param\u00e8tre Ip"));
		jLabel2.setText("Adresse IP start");

		jLabel3.setText("Adresse IP end");

		txtIpS.setText("127.0.0.1");

		txtIpE.setText("127.0.0.1");

		jbtCopy.setFont(new java.awt.Font("Tahoma", 0, 3));
		jbtCopy.setText("=");
		jbtCopy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtCopyActionPerformed(evt);
			}
		});

		jButton1.setText("Analyser");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("Annuler");

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
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																jPanel1Layout
																		.createSequentialGroup()
																		.add(
																				jPanel1Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING)
																						.add(
																								jLabel2)
																						.add(
																								jLabel3))
																		.add(
																				23,
																				23,
																				23)
																		.add(
																				jPanel1Layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING,
																								false)
																						.add(
																								txtIpE)
																						.add(
																								txtIpS,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								123,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jbtCopy,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				24,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
														.add(
																jPanel1Layout
																		.createSequentialGroup()
																		.add(
																				42,
																				42,
																				42)
																		.add(
																				jButton1)
																		.add(
																				46,
																				46,
																				46)
																		.add(
																				jButton2)))
										.addContainerGap(34, Short.MAX_VALUE)));
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
																txtIpS,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jLabel3)
														.add(
																txtIpE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(jbtCopy))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED,
												26, Short.MAX_VALUE)
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jButton1).add(
																jButton2))));

		jTable2.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null } },
				new String[] { "Adresse IP", "Port", "Inventaire ?" }));
		jScrollPane2.setViewportView(jTable2);

		jButton3.setText("Lancer l'inventaire");

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
																				155,
																				155,
																				155)
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING)
																						.add(
																								layout
																										.createSequentialGroup()
																										.add(
																												167,
																												167,
																												167)
																										.add(
																												jLabel1))
																						.add(
																								jScrollPane2,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								452,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																						.add(
																								layout
																										.createSequentialGroup()
																										.add(
																												76,
																												76,
																												76)
																										.add(
																												jPanel1,
																												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				321,
																				321,
																				321)
																		.add(
																				jButton3)))
										.addContainerGap(158, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup().addContainerGap().add(jLabel1)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jPanel1,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.add(24, 24, 24).add(jScrollPane2,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								150,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jButton3).addContainerGap(
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
	}// </editor-fold>//GEN-END:initComponents

	
	
	
	
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		
	try {
	           
	            //InetAddress addr = InetAddress.getByName(txtIpS.getText());
	            	           
	            int i=1311;
	            
	            
	            Socket s = null;
	               try {
	                   
	            	   String addr = txtIpS.getText();
	                   s = new Socket(addr,i);
	                   
	                   System.out.println("Port: " + i + " open on " + txtIpS.getText());
	                   Object [] ligne = {addr.toString(),String.valueOf(i)};
	                   model.addRow(ligne);
	                   
	                    }
	                catch (IOException ex) {
	                	//ex.printStackTrace();
	                }      
	                finally {
	                    try {
	                        if (s != null) s.close();
	                    }
	                    catch (IOException ex) {}
	                }
	           
	            
	        } //try
	        catch (Exception ex) {
	            System.err.println(ex); 
	        }

	}

	private void jbtCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCopyActionPerformed
	
	
		txtIpE.setText(txtIpS.getText());
		
		
	}
	
	
	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCopyActionPerformed
	
		int selected = jTable2.getSelectedRowCount();
		
		
		int lesLignes[] = jTable2.getSelectedRows();
		
		
		if (selected==0)
			
			JOptionPane.showConfirmDialog(this,"Veuillez selectionner une ou plusieurs adresse à inventorier!", "Inventaire",JOptionPane.ERROR_MESSAGE);
		else
			
		for (int i=0; i<selected ; i++)
		{
		
			String adresse = jTable2.getValueAt(lesLignes[i],0).toString();
			System.out.println(adresse);
		
		
		try
		{
			final Socket client=new Socket(adresse,1311);
			final PrintWriter out = new PrintWriter(client.getOutputStream(),true);
			final BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			
			new Thread(new Runnable() 
			{
        	public void run() 
        	{
        		
        		out.println("haloooooooo");
				out.flush();
				try
				{
				String reponse = in.readLine();
				System.out.println("le serveur dit : "+reponse);
				StringTokenizer t = new StringTokenizer(reponse,"==>");
				
				
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
				
				
				Gbd gbd = new Gbd();
				gbd.connecter();
				//Requete d'insertion du PC
				String requete = "INSERT INTO pc (id_lieu,marque,modele,numero_serie" +
						",flag) VALUES(1,'"+marque_pc+"','"+modele_pc+"','" +
							serial_pc+"',1)";
				
				gbd.execUpdate(requete);
				
				requete = "SELECT max(id_pc) AS id FROM pc";
				ResultSet r = gbd.execQuery(requete);
				r.next();
				int id_pc = r.getInt("id");
				
				
				//Requete d'insertion du Processeur
				requete = "INSERT INTO peripherique (id_type_peripherique,id_pc,id_lieu" +
						",id_slot,nom,numero_serie,flag) VALUES(2,"+id_pc+",1,1,'" +
						name+"','"+serial+"',1)";
				
				gbd.execUpdate(requete);
				
				requete = "SELECT max(id_peripherique) AS id FROM peripherique";
				r = gbd.execQuery(requete);
				r.next();
				int id_peripherique = r.getInt("id");
				
				//Insertion de la frequence du processeur
				requete = "INSERT INTO peripherique_propriete VALUES("+id_peripherique+",1"+
						",'"+frequence+"')";
				gbd.execUpdate(requete);
				
				//Insertion du cache du processeur
				requete = "INSERT INTO peripherique_propriete VALUES("+id_peripherique+",5"+
				",'"+cache+"')";
				
				gbd.execUpdate(requete);
				
				//Requete d'insertion de la Carte graphique
				requete = "INSERT INTO peripherique (id_type_peripherique,id_pc,id_lieu" +
						",id_slot,nom,numero_serie,flag) VALUES(1,"+id_pc+",1,1,'" +
						name_graphique+"','',1)";
				
				gbd.execUpdate(requete);
				
				requete = "SELECT max(id_peripherique) AS id FROM peripherique";
				r = gbd.execQuery(requete);
				r.next();
				id_peripherique = r.getInt("id");
				
				//Insertion de la memoire de la carte graphique
				requete = "INSERT INTO peripherique_propriete VALUES("+id_peripherique+",2"+
				",'"+memoire_graphique+"')";
				
				gbd.execUpdate(requete);
				
				
//				Insertion de la mode de la carte graphique
				requete = "INSERT INTO peripherique_propriete VALUES("+id_peripherique+",6"+
				",'"+mode_graphique+"')";
				
				gbd.execUpdate(requete);
				
				
				
				
//				Requete d'insertion de la Carte son
				requete = "INSERT INTO peripherique (id_type_peripherique,id_pc,id_lieu" +
						",id_slot,nom,numero_serie,flag) VALUES(4,"+id_pc+",1,1,'" +
						name_son+"','',1)";
				
				gbd.execUpdate(requete);
				
				requete = "SELECT max(id_peripherique) AS id FROM peripherique";
				r = gbd.execQuery(requete);
				r.next();
				id_peripherique = r.getInt("id");
				
				//Insertion du fabricant de la carte son
				requete = "INSERT INTO peripherique_propriete VALUES("+id_peripherique+",10"+
				",'"+fabricant_son+"')";
				
				gbd.execUpdate(requete);
				
				        				
				client.close();
				}catch(Exception e){e.printStackTrace();}
        		
        		SwingUtilities.invokeLater(new Runnable() 
        		{
        			
        			
        			public void run() 
        			{
            
        				try
        				{
        								
        					panelPc panel = new panelPc(null,0);
        					final JDialog j = new JDialog();
        					j.add(panel);
        					j.addWindowListener(new WindowAdapter() {
        						public void windowClosing(WindowEvent evt)
        						{
        							int reponse = JOptionPane.showConfirmDialog(null,"En fermant cette fenêtre, les PC inventoriés seront sauvegardés!\nEtes vous sûr de vouloir continuer ?", "Fermeture",JOptionPane.YES_NO_OPTION);
        							System.out.println("reponse de la frame = "+reponse);
        							
        							if (reponse==0)
        							{
        								String requete = "UPDATE pc set flag=0";
        								Gbd gbd = new Gbd();
        								gbd.connecter();
        								gbd.execUpdate(requete);
        								gbd.deconnecter();
        								j.dispose();
        							}
        							else
        							{
        								
        								j.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        								
        							}
        								
        								
        						}
        					});
        					
        					j.pack();
        					j.setVisible(true);
        				}catch (Exception e){e.printStackTrace();}
	      	  
        			}
        		});
        	}
        }
        ).start();
			
			
			
		}catch(UnknownHostException e) {
            
			System.err.println("Don't know about host: taranis.");
            
        } catch (IOException e) {
//            System.err.println("Couldn't get I/O for the connection to: taranis.");
            
        }
        
	}//Fin pour
		
  }
        
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;

	private javax.swing.JButton jButton2;

	private javax.swing.JButton jButton3;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JLabel jLabel3;

	private javax.swing.JPanel jPanel1;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JScrollPane jScrollPane2;

	private javax.swing.JTable jTable1;

	private javax.swing.JTable jTable2;

	private javax.swing.JButton jbtCopy;

	private javax.swing.JTextField txtIpE;

	private javax.swing.JTextField txtIpS;
	// End of variables declaration//GEN-END:variables

}
