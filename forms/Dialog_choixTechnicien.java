/*
 * Dialog_choixTechnicien.java
 *
 * Created on __DATE__, __TIME__
 */

package forms;

import gestion_materiel.Gbd;
import gestion_materiel.Technicien;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.AlternateRowHighlighter;
import org.jdesktop.swingx.decorator.Highlighter;
import org.jdesktop.swingx.decorator.HighlighterPipeline;
import org.jdesktop.swingx.decorator.RolloverHighlighter;

/**
 *
 * @author  __USER__
 */
public class Dialog_choixTechnicien extends javax.swing.JDialog {




    /** Creates new form Dialog_choixTechnicien */
 
	MainForm m;
	Vector v;
	DefaultTableModel model;
	JXTable table;
	JPanel panel;
	
    public Dialog_choixTechnicien(MainForm parent, boolean modal,JPanel panel) {
        super(parent, modal);
        
        this.panel = panel;
        this.m = parent;
        
        
        
        
        v = new Vector();
        
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nom");
        model.addColumn("Prenom");
        model.addColumn("CIN");
        
        
        table = new JXTable();
		table.setHighlighters(
        new HighlighterPipeline(new Highlighter[] {AlternateRowHighlighter.quickSilver}
));
		table.setRolloverEnabled(true);
		table.getHighlighters().addHighlighter(new RolloverHighlighter(Color.GRAY, Color.WHITE ));
		table.setColumnControlVisible(true);
	
        
		initComponents();
		
        Gbd gbd = new Gbd();
        gbd.connecter();
        String requete = "SELECT id_technicien FROM technicien";
        
        ResultSet r = gbd.execQuery(requete);
        
        try {
			while (r.next())
			{
				Technicien t = new Technicien();
				t.construireTechnicien(r.getInt("id_technicien"));
				v.add(t);
				String [] ligne = {
						t.getId_technicien().toString(),
						t.getNom_technicien(),
						t.getPrenom_technicien(),
						t.getCin_technicien()
						
					};
				
				model.addRow(ligne);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		table.setModel(model);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
	private void initComponents() {
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jbtAjouter = new javax.swing.JButton();
		jbtFermer = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		table.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null },
						{ null, null, null, null } }, new String[] {
						"id_technicien", "Nom", "Prenom", "Cin" }));
		jScrollPane1.setViewportView(table);

		jbtAjouter.setText("Ajouter");
		jbtAjouter.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtAjouterActionPerformed(evt);
			}
		});

		jbtFermer.setText("Fermer");
		jbtFermer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbtFermerActionPerformed(evt);
			}
		});

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				org.jdesktop.layout.GroupLayout.TRAILING,
				layout.createSequentialGroup().addContainerGap(115,
						Short.MAX_VALUE).add(jScrollPane1,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 546,
						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE).add(
						104, 104, 104)).add(
				layout.createSequentialGroup().add(250, 250, 250).add(
						jbtAjouter).add(128, 128, 128).add(jbtFermer)
						.addContainerGap(251, Short.MAX_VALUE)));
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
												141,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jbtAjouter).add(
																jbtFermer))
										.add(29, 29, 29)));
		pack();
	}// </editor-fold>//GEN-END:initComponents

	
	
	
	
	private void jbtAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAjouterActionPerformed
	
		int ligne = table.getSelectedRow();
		
		if (table.getSelectedRowCount()!=0)
		{
			if (panel instanceof Panel_intervention==true)
			{
				Panel_intervention intervention = (Panel_intervention)this.panel;
	        	intervention.getCmbId().removeAllItems();
	        	intervention.getCmbId().addItem(" ");
	        	intervention.getCmbId().addItem("Choisir un Intervention");
	        	intervention.getCmbId().addItem(table.getModel().getValueAt(ligne,0).toString());
	        	intervention.getCmbId().setSelectedItem(table.getModel().getValueAt(ligne,0).toString());
	        	intervention.getTxtNom().setText(table.getModel().getValueAt(ligne,1).toString());
	        	intervention.getTxtPrenom().setText(table.getModel().getValueAt(ligne,2).toString());
	        	intervention.getTxtCin().setText(table.getModel().getValueAt(ligne,3).toString());
	        	
	        	this.dispose();
	        	
	        	
		
			}
		}
		
	}//GEN-LAST:event_jbtAjouterActionPerformed

	private void jbtFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFermerActionPerformed
	 this.dispose();
	}//GEN-LAST:event_jbtFermerActionPerformed


// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JTable jTable1;

	private javax.swing.JButton jbtAjouter;

	private javax.swing.JButton jbtFermer;
	// End of variables declaration//GEN-END:variables

}
