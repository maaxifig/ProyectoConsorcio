package vistaPck;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import modeloPck.Consorcio;

public class ConsorcioTableModel extends AbstractTableModel 
{
	private List<Consorcio> lista;
	private String[] ConsorcioTabla = {"ID", "direccion", "unidades"};
	private final static int ID_COL = 0;
	private final static int DIRECCION_COL = 1;
	private final static int UNIDADES_COL = 2;
	
	public ConsorcioTableModel(List<Consorcio> lista)
	{
		this.lista = lista;
	}
	
	public String getColumnName(int col)
	{
		return ConsorcioTabla[col];
	}
	
	@Override
	public int getRowCount() 
	{
		return lista.size();
	}
	
	public int getColumnCount()
	{
		return ConsorcioTabla.length;
	}
	
	//Metodo para llenar la tabla con datos en la fila y columna especificada
	public Object getValueAt(int row, int col)
	{
		Consorcio consorcio = lista.get(row);
		switch(col)
		{
			case ID_COL:
				return consorcio.getID();
			case DIRECCION_COL:
				return consorcio.getDireccion();
			case UNIDADES_COL:
				return consorcio.getUnidades();
		}
		return null;
	}
}
