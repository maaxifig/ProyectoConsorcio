package vistaPck;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modeloPck.Usuario;

public class PanelMostrarTablaUsuario extends JPanel
{
	 List<Usuario> Listausuario;

	public PanelMostrarTablaUsuario(List<Usuario> Listausuario) 
    {
        this.Listausuario = Listausuario;
		initUI();
    }
    private void initUI() 
    {
        setLayout(new BorderLayout());
        
        UsuarioTableModel userTablaModel = new UsuarioTableModel(Listausuario);//Se instancia el AbstractTableModel
        JTable jTabla = new JTable(userTablaModel);//Se pasa el AbstractTableModel al nuevo objeto JTABLE
        JScrollPane scrollPane = new JScrollPane(jTabla);//Se agrega el JTABLE al SCROLLPANE
        jTabla.setFillsViewportHeight(true);//Para no crear ventana gráfica más grande que el tamaño preferido de la tabla.

        this.add(scrollPane, BorderLayout.CENTER);//Se le asigna una orientacion en el JPANEL
        this.add(scrollPane);//Se agrega el objeto al JPANEL
    }   
}
