package forms;

import javax.swing.table.AbstractTableModel;

public class ModelJTable extends AbstractTableModel{
	
	Object [][]data;
	String [] titre;
	boolean modifier=false;
	
	
	
	 public ModelJTable(Object data[][], String titre[]) {

	      this.data = data;

	      this.titre = titre;

	   }

	   public int getColumnCount() {

	       return data[0].length;

	   }

	   public Object getValueAt(int parm1, int parm2) {

	       return data[parm1][parm2];

	   }

	   public int getRowCount() {

	       return data.length;

	   }

	   public String getColumnName(int col){

	     return titre[col];

	   }
	   
	   public boolean isCellEditable(int row,int col)
	   {
		   if (modifier!=false)
			   return (col>0);
		   return false;
	   }
	   
	   public void setValueAt(Object value,int row,int col)
	   {
		   data[row][col]=value;
		   fireTableCellUpdated(row, col);
	   }

	   public void addRow(Object data2)
	   {
		   Object data3 [][] = new Object[data.length+2][data[0].length];
			
			
			for (int i=0;i<data.length;i++)
				for (int j=0;j<data[i].length;j++)
					data3[i][j] = data[i][j];
					
			
			data3[data.length+1][0] = "55";
			data3[data.length+1][1] = "55";
			data3[data.length+1][2] = "55";
			data3[data.length+1][3] = "55";
			data3[data.length+1][4] = "55";
			data3[data.length+1][5] = "55";
			data3[data.length+1][6] = "55";
			data3[data.length+1][7] = "55";
			
			for (int i=0;i<data3.length;i++)
				for (int j=0;j<data3[i].length;j++)
					System.out.println(data3[i][j]);
	   }
}
