/*
 * Panel_skins.java
 *
 * Created on __DATE__, __TIME__
 */

package forms;

import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;

import com.l2fprod.gui.plaf.skin.Skin;
import com.l2fprod.gui.plaf.skin.SkinLookAndFeel;
import com.l2fprod.util.OS;
import com.l2fprod.gui.plaf.skin.Skin;
import com.l2fprod.gui.plaf.skin.SkinLookAndFeel;
import com.l2fprod.util.OS;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import sun.security.krb5.internal.tools.Kinit;



/**
 *
 * @author  __USER__
 */
public class Panel_skins extends javax.swing.JPanel {


    /** Creates new form Panel_skins */
    
    MainForm m;
    public Panel_skins(MainForm m) {
        
        this.m = m;
        initComponents();
        
        jComboBox1.removeAllItems();
        jComboBox1.addItem("amarachthemepack.zip");
        jComboBox1.addItem("architectBluethemepack.zip");
        jComboBox1.addItem("architectOlivethemepack.zip");
        jComboBox1.addItem("b0sumiErgothempack.zip");
        jComboBox1.addItem("blueMetalthemepack.zip");
        jComboBox1.addItem("BeOSthemepack.zip");
        jComboBox1.addItem("bbjthemepack.zip");
        jComboBox1.addItem("b0sumithemepack.zip");
        jComboBox1.addItem("blueTurquesathemepack.zip");
        jComboBox1.addItem("cellshadedthemepack.zip");
        jComboBox1.addItem("chaNinja-Bluethemepack.zip");
        jComboBox1.addItem("coronaHthemepack.zip");
        jComboBox1.addItem("gfxOasisthemepack.zip");
        jComboBox1.addItem("fatalEthemepack.zip");
        jComboBox1.addItem("crystal2themepack.zip");
        jComboBox1.addItem("cougarthemepack.zip");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
	private void initComponents() {
		jButton1 = new javax.swing.JButton();
		jComboBox1 = new javax.swing.JComboBox();

		setLayout(null);

		jButton1.setText("Appliquer");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		add(jButton1);
		jButton1.setBounds(140, 110, 100, 23);

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));
		add(jComboBox1);
		jComboBox1.setBounds(130, 70, 120, 22);

	}// </editor-fold>//GEN-END:initComponents

	
	
	
	
	
	
	
	
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
	
	
	 String themepack = "theme\\"+jComboBox1.getSelectedItem().toString();
      
      try
      {
      if (themepack.endsWith(".xml")) {
        SkinLookAndFeel.setSkin(
          SkinLookAndFeel.loadThemePackDefinition(new File(themepack).toURL()));
        UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
       
      } else if (themepack.startsWith("class:")) {
        String classname = themepack.substring("class:".length());
        SkinLookAndFeel.setSkin((Skin)Class.forName(classname).newInstance());
        UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
      } else if (themepack.startsWith("theme:")) {
        String classname = themepack.substring("theme:".length());
        MetalTheme theme = (MetalTheme)Class.forName(classname).newInstance();
        MetalLookAndFeel metal = new MetalLookAndFeel();
        MetalLookAndFeel.setCurrentTheme(theme);
        UIManager.setLookAndFeel(metal);
      } else {
        SkinLookAndFeel.setSkin(SkinLookAndFeel.loadThemePack(themepack));
        UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
      }
        
      if (OS.isOneDotFourOrMore()) {
      java.lang.reflect.Method method =
        JFrame.class.getMethod(
          "setDefaultLookAndFeelDecorated",
          new Class[] { boolean.class });
      method.invoke(null, new Object[] { Boolean.TRUE });

      method =
        JDialog.class.getMethod(
          "setDefaultLookAndFeelDecorated",
          new Class[] { boolean.class });
      method.invoke(null, new Object[] { Boolean.TRUE });
      }
      }catch (Exception e)
      {
    	  e.printStackTrace();
      }
    
   
	
	}//GEN-LAST:event_jButton1ActionPerformed


// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;

	private javax.swing.JComboBox jComboBox1;
	// End of variables declaration//GEN-END:variables

}
