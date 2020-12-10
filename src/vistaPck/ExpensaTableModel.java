package vistaPck;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import modeloPck.Expensa;
import modeloPck.Gasto;

public class ExpensaTableModel extends AbstractTableModel 
{
	private List<Expensa> lista;
	private String[] ExpensaTabla = {"id_edificio", "nUnidad", "m2","expensaOrd","expensaExt","año","mes"};
	private final static int ID_EDIF_COL = 0;
	private final static int UNIDAD_COL= 1;
	private final static int M2_COL = 2;
	private final static int EXP_ORD_COL = 3;
	private final static int EXP_EXT_COL = 4;
	private final static int AÑO = 5;
	private final static int MES = 6;
	
	public ExpensaTableModel(List<Expensa> lista)
	{
		this.lista = lista;
	}
	
	public String getColumnName(int col)
	{
		return ExpensaTabla[col];
	}
	
	@Override
	public int getRowCount() 
	{
		return lista.size();
	}
	
	public int getColumnCount()
	{
		return ExpensaTabla.length;
	}
	
	//Metodo para llenar la tabla con datos en la fila y columna especificada
	public Object getValueAt(int row, int col)
	{
		Expensa expensa = lista.get(row);
		switch(col)
		{
			case ID_EDIF_COL:
				return expensa.getIdEdif();
			case UNIDAD_COL:
				return expensa.getnUnidad();
			case M2_COL:
				return expensa.getm2();
			case EXP_ORD_COL:
				return expensa.getExpensaOrd();
			case EXP_EXT_COL:
				return expensa.getExpensaExt();
			case AÑO:
				return expensa.getAño();
			case MES:
				return expensa.getMes();
		}
		return null;
	}
}
