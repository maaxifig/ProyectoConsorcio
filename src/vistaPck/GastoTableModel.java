package vistaPck;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import modeloPck.Gasto;

public class GastoTableModel extends AbstractTableModel 
{
	private List<Gasto> lista;
	private String[] GastoTabla = {"id_edificio", "id_gasto", "gasto_ord","gasto_ext","año","mes"};
	private final static int ID_EDIF_COL = 0;
	private final static int ID_GASTO_COL = 1;
	private final static int GASTO_ORD_COL = 2;
	private final static int GASTO_EXT_COL = 3;
	private final static int AÑO = 4;
	private final static int MES = 5;
	
	public GastoTableModel(List<Gasto> lista)
	{
		this.lista = lista;
	}
	
	public String getColumnName(int col)
	{
		return GastoTabla[col];
	}
	
	@Override
	public int getRowCount() 
	{
		return lista.size();
	}
	
	public int getColumnCount()
	{
		return GastoTabla.length;
	}
	
	//Metodo para llenar la tabla con datos en la fila y columna especificada
	public Object getValueAt(int row, int col)
	{
		Gasto gasto = lista.get(row);
		switch(col)
		{
			case ID_EDIF_COL:
				return gasto.getIdEdif();
			case ID_GASTO_COL:
				return gasto.getIdGasto();
			case GASTO_ORD_COL:
				return gasto.getGastoOrd();
			case GASTO_EXT_COL:
				return gasto.getGastoExt();
			case AÑO:
				return gasto.getAño();
			case MES:
				return gasto.getMes();
		}
		return null;
	}
}
