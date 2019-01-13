package forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXComboBox;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.autocomplete.Configurator;


public class GUILivre2 extends JFrame {
	String [] noms = {"Ahmed","Mehdi","Houssem","Becim"};
	//private JXComboBox a = new JXComboBox(noms);
//	private JTextField saisieAuteur = new JTextField(15);
	//JTextField a = new JTextField();
	//JXList a = new JXList();
	
	
	JTextField textField = new JTextField(" ");
	JComboBox jcb;//=new JXComboBox();	
	
	
	
	private JTextField saisietitre = new JTextField(20);
	
public GUILivre2()
{
	super("Afficher les informations sur un livre");
	
	String[] data = {"Français","Anglais","Italien","Espagnol","Portugais","Allemand","Hollandais","Russe"};
	JList list = new JList(data);
//	textField.setColumns(10);
//	textField.setEditable(true);
//	Configurator.enableAutoCompletion(list, textField);
//	textField.setEditable(true);
//	textField.setEnabled(true);
	jcb=new JXComboBox(data);
	//final JComboBox jcb=new JComboBox(data);
	jcb.setEditable(true);
	
	Configurator.enableAutoCompletion(jcb);
	//Configurator.
	
	addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		}	);
	
//jpanel pour la recherche avec flow layout 
	
	JPanel panelRecherche = new JPanel(new FlowLayout());
	Box boiteAuteur = Box.createVerticalBox() ;
	boiteAuteur.add(new JLabel("Auteur"));
	//boiteAuteur.add(saisieAuteur);
	//boiteAuteur.add(textField);
	boiteAuteur.add(jcb);
	Box boiteTitre = Box.createVerticalBox() ;
	boiteTitre.add(new JLabel("Titre"));
	boiteTitre.add(saisietitre);
	 
	Box principale = Box.createVerticalBox();
	
	
	
	
	panelRecherche.add(boiteAuteur);
	JButton chercher = new JButton("Chercher");
	chercher.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("boutton rechercher appuié "+jcb.getSelectedItem().toString());
			
		}
		
	});
	
	panelRecherche.add(chercher);
	panelRecherche.setBorder(BorderFactory.createTitledBorder("Saisie des informations sur le livre "));
	this.add(panelRecherche,BorderLayout.NORTH);
	
	
	//boutton pour quitter en bas !
	
	JButton quitter = new JButton("Quitter");
	
	
	JPanel panel = new JPanel(new FlowLayout());
	JButton afficher = new JButton("Afficher");
	
	afficher.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e ){
			//String s="";
			System.out.println("boutton appuier "+(String)jcb.getSelectedItem());
			//s+=saisieAuteur.getText();
			//s+=a.getToolTipText();System.out.print(s);
		}
	});
	
	/////////////////////////////////////// a emporter pour les dates 
	JPanel cal = new JPanel();
	JXDatePicker c = new JXDatePicker();
	
	Date a = Date.valueOf("2007-12-20");
	//a.valueOf();
	c.setDate(a);
	//c.setFocusable(true);
	c.setEnabled(false);
	//c.setFormats("dd MM yyyy");
	cal.add(c);
//////////////////////////////////////////////////	
	panel.add(afficher);
	panel.add(quitter);
	panel.add(cal);
	
	//this.add(panel);
	
	principale.add(panelRecherche);
	principale.add(boiteTitre);
	principale.add(new TextArea(20,30));
	principale.add(panel);
	
	this.add(principale);
	
	quitter.addActionListener(new MybouttonListner());
	
	
	
}

class MybouttonListner implements ActionListener
{
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		System.out.print(jcb.getToolTipText());	
		System.exit(0);
		
		
	}
}

public static void main(String []arg )
{
	GUILivre2 fenetre = new GUILivre2();
	fenetre.setVisible(true );
	fenetre.pack();
	



}


}
