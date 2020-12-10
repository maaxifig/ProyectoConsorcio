package vistaPck;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import modeloPck.Consorcio;
import modeloPck.Usuario;

public abstract class PanelAbstracto extends JPanel
{
	public PanelAbstracto()
	{
	}
	
	public abstract void limpiarCampo(); 
	
	public JScrollPane crearTabla(TableModel  TablaAbstracta) //Prueba con AbstractTableModel
	{
        JTable jTabla = new JTable(TablaAbstracta);//Se pasa el AbstractTableModel al nuevo objeto JTABLE
        JScrollPane scrollPane = new JScrollPane(jTabla);//Se agrega el JTABLE al SCROLLPANE
        jTabla.setFillsViewportHeight(true);//Para no crear ventana gráfica más grande que el tamaño preferido de la tabla
        
        return scrollPane;
	}

	
//	valida si las unidades son mayores a cero
	public static boolean validarUnidades(String cadena)// CAMBIARLO
	{
		try
		{
			if (Integer.parseInt(cadena) > 0)
				return true;
			else 
				return false;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	//************************* VALIDA QUE EL DATO INGRESADO NO SEA BLANCO**************
	public static boolean esBlanco(String cadena)
	{
		try
		{
			if(cadena.equals(""))
				return true;
			else
				return false;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
}
