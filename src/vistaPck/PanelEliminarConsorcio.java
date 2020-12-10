package vistaPck;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import modeloPck.Consorcio;

public class PanelEliminarConsorcio extends PanelEliminarAbstracto 
{
	private List<Consorcio> Listaconsorcio;
	
	public PanelEliminarConsorcio(Controlador controlador, List<Consorcio> Listaconsorcio)
	{
		super(controlador);
		this.Listaconsorcio = Listaconsorcio;
		init();
	}
	
	public void accionEliminar() {
		if (esBlanco(txtEliminar.getText()))
		{
			JOptionPane.showMessageDialog(null, "El campo ID es obligatorio");
			limpiarCampo();
		}
		else
		{
			Consorcio consorcio = new Consorcio();
			consorcio.setID(Integer.parseInt(txtEliminar.getText()));//Obtiene el string y lo convierte en entero
			controlador.eliminarConsorcio(consorcio);
			limpiarCampo();
		}
	}
	
	public TableModel getTableModel() {
		 return new ConsorcioTableModel(Listaconsorcio);
	}
	
	public void limpiarCampo()
	{
		txtEliminar.setText("");
	}
}
