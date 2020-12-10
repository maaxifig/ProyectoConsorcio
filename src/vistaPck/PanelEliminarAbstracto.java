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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import modeloPck.Gasto;

public abstract class PanelEliminarAbstracto extends PanelAbstracto {

	protected Controlador controlador;
	protected JTextField txtEliminar;
	
	public PanelEliminarAbstracto(Controlador controlador)
	{
		super();
		this.controlador = controlador;
	}
	
	public abstract TableModel getTableModel();
	public abstract void accionEliminar();
	
	public void init() 
	{
		setLayout(new BorderLayout());
        
		//************************* BOX ELIMINAR *****************************
        Box boxEliminar = Box.createHorizontalBox();
        boxEliminar.add(Box.createHorizontalStrut(10));
        JLabel labelEliminar = new JLabel("ID a eliminar: ");
        txtEliminar = new JTextField();
        JButton buttonEliminar = new JButton("Eliminar");
        boxEliminar.add(labelEliminar);
        boxEliminar.add(txtEliminar);
        boxEliminar.add(buttonEliminar);
      //************************* BOX TABLA *****************************
        Box boxTabla = Box.createHorizontalBox();
        boxTabla.add(Box.createHorizontalStrut(10));
        boxTabla.add(crearTabla(getTableModel()), BorderLayout.SOUTH); // <---SE ROMPE PORQUE ESPERA RECIBIR UN AbstractTableModel
	  //************************* BOX GRAL *****************************
        Box boxGral = Box.createVerticalBox();
        boxGral.add(Box.createVerticalStrut(10));
        boxGral.add(boxEliminar);
        boxGral.add(boxTabla);
        
        buttonEliminar.addActionListener(new ActionListener() 
        {
			public void actionPerformed(ActionEvent e) 
			{
				accionEliminar();
			}
		});
       
        add(boxGral, BorderLayout.CENTER);

	}
	
	public void limpiarCampo() {
		txtEliminar.setText("");
	}
	
}
