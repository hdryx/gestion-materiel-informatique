/*
 * Dialog_chioxLieu.java
 *
 * Created on __DATE__, __TIME__
 */

package forms;

import gestion_materiel.*;

import java.sql.ResultSet;

import javax.swing.table.TableModel;

/**
 *
 * @author  __USER__
 */
public class Dialog_choixLieu extends javax.swing.JDialog {

    
    MainForm m;
    Object [][] data=null;
    String [] titre={"Id","Type","Lieu"};
    int code ;
    //J ai ajouter tout simplement un code puis lors de l appel chaque panel appel avec un code different et en teste ce code dans le boutton ajouter 
    
    /** Creates new form Dialog_chioxLieu */
    public Dialog_choixLieu(MainForm parent, boolean modal,int codepanel) {
        super(parent, modal);
        initComponents();
        
        this.code = codepanel;
        
        
        jButton1.addActionListener(new java.awt.event.ActionListener(){
        
        	public void actionPerformed(java.awt.event.ActionEvent e)
        	{
        		listenerJButton1();
        	}
        
        });
        
        
        jButton2.addActionListener(new java.awt.event.ActionListener(){
        
        	public void actionPerformed(java.awt.event.ActionEvent e)
        	{
        		listenerJButton2();
        	}
        
        });
        
        
        this.m = parent;
        
        
                
        String requete = "SELECT id_lieu FROM lieu";
        Gbd gbd = new Gbd();
        gbd.connecter();
        ResultSet r = gbd.execQuery(requete);
        int nombre = gbd.count();
        int i=0;
        data = new Object[nombre][3];
        
        if (nombre!=0)
        {
        	try
        	{
        		while(r.next())
        		{
        	
	        		Lieu l = new Lieu();
	        		l.construireLieu(r.getInt("id_lieu"));
        			
        			data[i][0] = l.getId_lieu();
        			data[i][1] = l.getTypeLieu().getNom_type();
        			data[i][2] = l.getNom();
        			
        			i++;
        		}
        		gbd.deconnecter();
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        
        	this.jTable1.setModel(getModel());
        
        }
        
        
        
        
    }

    
    
    public void listenerJButton1(){
    
    	if (jTable1.getSelectedRowCount()!=0)
		{
    		int i= jTable1.getSelectedRow();
			
    		if(this.code==1)
			{	
    			Panel_peripherique p = (Panel_peripherique)this.m.jPanel1.getComponent(0);
				
    			p.getTxtTypeLieu().setText(jTable1.getModel().getValueAt(i,1).toString());
    			p.getTxtLieu().setText(jTable1.getModel().getValueAt(i,2).toString());
    			p.getCmbIdLieu().removeAllItems();
    			p.getCmbIdLieu().addItem(" ");
    			p.getCmbIdLieu().addItem("Choisir un Lieu");
    			p.getCmbIdLieu().addItem(jTable1.getModel().getValueAt(i,0).toString());
    			p.getCmbIdLieu().setSelectedItem(jTable1.getModel().getValueAt(i,0).toString());
    			
			}
			else if(this.code==2)
			{
				panelPc p = (panelPc)this.m.jPanel1.getComponent(0);
				System.out.println(this.m.getName());
//				
				p.getJTextTypelieu().setText(jTable1.getModel().getValueAt(i,1).toString());
				p.getJTextLieu().setText(jTable1.getModel().getValueAt(i,2).toString());
				p.getCmbIdLieu().removeAllItems();
				p.getCmbIdLieu().addItem(" ");
				p.getCmbIdLieu().addItem("Choisir un Lieu");
				p.getCmbIdLieu().addItem(jTable1.getModel().getValueAt(i,0).toString());
				p.getCmbIdLieu().setSelectedItem(jTable1.getModel().getValueAt(i,0).toString());
			
			}		
						
			
						
			this.setVisible(false);
			
		}
    
    }
    
    
    
    public void listenerJButton2(){
    
    	this.setVisible(false);
    
    }
    
    
    
	public TableModel getModel()
	{
	   
	   return new ModelJTable(data,titre);
   
	}
	
	private void initComponents() {
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null } },
				new String[] { "Id", "Type", "Lieu" }));
		jScrollPane1.setViewportView(jTable1);

		jButton1.setText("Ajouter");

		jButton2.setText("Annuler");

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
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
																				122,
																				122,
																				122)
																		.add(
																				jScrollPane1,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				452,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				212,
																				212,
																				212)
																		.add(
																				jButton1)
																		.add(
																				93,
																				93,
																				93)
																		.add(
																				jButton2)))
										.addContainerGap(124, Short.MAX_VALUE)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												jScrollPane1,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												140,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jButton1).add(
																jButton2))
										.addContainerGap(29, Short.MAX_VALUE)));
		pack();
	}// </editor-fold>//GEN-END:initComponents


	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;

	private javax.swing.JButton jButton2;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JTable jTable1;
	// End of variables declaration//GEN-END:variables

}
