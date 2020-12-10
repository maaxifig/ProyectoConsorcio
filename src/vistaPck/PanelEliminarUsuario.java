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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import modeloPck.Usuario;

public class PanelEliminarUsuario extends PanelEliminarAbstracto 
{
	private List<Usuario> Listausuario;
	
	public PanelEliminarUsuario(Controlador controlador, List<Usuario> Listausuario)
	{
		super(controlador);
		this.Listausuario = Listausuario;
		init();
	}
	
	public TableModel getTableModel() {
		 return new UsuarioTableModel(Listausuario);  
		 }
	
	public void accionEliminar() {
		if (esBlanco(txtEliminar.getText()))
		{
			JOptionPane.showMessageDialog(null, "El campo ID es obligatorio");
			limpiarCampo();
		}
		else {
			Usuario usuario = new Usuario();
			usuario.setUsuario(txtEliminar.getText());
			controlador.eliminarUsuario(usuario);
			limpiarCampo();
		}

	}
	
}
