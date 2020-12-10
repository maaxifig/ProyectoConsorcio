package vistaPck;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import modeloPck.Usuario;

public class UsuarioTableModel extends AbstractTableModel 
{
	private List<Usuario> lista;
	private String[] UsuarioTabla = {"usuario", "pass", "permiso"};
	private final static int USUARIO_COL = 0;
	private final static int PASS_COL = 1;
	private final static int PERMISO_COL = 2;
	
	public UsuarioTableModel(List<Usuario> lista)
	{
		this.lista = lista;
	}
	
	public String getColumnName(int col)
	{
		return UsuarioTabla[col];
	}
	
	@Override
	public int getRowCount() 
	{
		return lista.size();
	}
	
	public int getColumnCount()
	{
		return UsuarioTabla.length;
	}
	
	//Metodo para llenar la tabla con datos en la fila y columna especificada
	public Object getValueAt(int row, int col)
	{
		Usuario usuario = lista.get(row);
		switch(col)
		{
			case USUARIO_COL:
				return usuario.getUsuario();
			case PASS_COL:
				return usuario.getPass();
			case PERMISO_COL:
				return usuario.getPermiso();
		}
		return null;
	}
}
