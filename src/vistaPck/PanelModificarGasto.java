package vistaPck;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modeloPck.Gasto;


public class PanelModificarGasto extends PanelAbstracto 
{
		private Controlador controlador;
		private JTextField txtIdGasto;
		private JComboBox opcionGasto;
		private JTextField txtGasto;
		private JTextField txtMes;
		private JTextField txtAño;
		
		List<Gasto> Listagastos = new ArrayList<Gasto>();
		
	public PanelModificarGasto(Controlador controlador, List<Gasto> Listagastos) 
	{
		super();
		this.controlador = controlador;
		this.Listagastos = Listagastos;
		init();
	}

	private void init() 
	{
		setLayout(new BorderLayout());
		
		//************************* BOX ID gasto *****************************
		Box boxIdGasto = Box.createHorizontalBox();//Creamos el contenedor para los datos del consorcio
		JLabel lblIdGasto = new JLabel("Id gasto a modificar: ");//Label para definir el id a actualizar
		boxIdGasto.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxIdGasto.add(lblIdGasto);//Agregamos un label
		txtIdGasto = new JTextField(30);
		boxIdGasto.add(txtIdGasto);//Agregamos un TextField y definimos el tamaÃ±o
		
		//************************* BOX Gasto *****************************
		String[] opcionGastoStr = { "Expensa ordinaria", "Expensa Extraordinaria"};
		Box boxGasto = Box.createHorizontalBox();
		opcionGasto = new JComboBox(opcionGastoStr);		
		
		JLabel lblGasto = new JLabel("Ingrese gasto, y tipo ");
		boxGasto.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxGasto.add(lblGasto);//Agregamos el label
		txtGasto = new JTextField(30);
		boxGasto.add(txtGasto);//Agregamos un TextField
		boxGasto.add(opcionGasto);
		
		//************************* BOX Fecha *****************************
		Box boxFecha = Box.createHorizontalBox();
		JLabel lblMes = new JLabel("Ingresar mes (1-12): ");
		boxFecha.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxFecha.add(lblMes);//Agregamos el label
		txtMes = new JTextField(30);
		boxFecha.add(txtMes);//Agregamos un TextField
		
		JLabel lblAño = new JLabel("Ingresar año(yyyy): ");
		boxFecha.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxFecha.add(lblAño);//Agregamos el label
		txtAño = new JTextField(30);
		boxFecha.add(txtAño);//Agregamos un TextField

		//************************* BOX TABLA *****************************
		Box boxTabla = Box.createHorizontalBox();
		boxTabla.add(crearTabla(new GastoTableModel(Listagastos)), BorderLayout.SOUTH);
		
		//************************* BOX GENERAL *****************************
		Box boxGral = Box.createVerticalBox();//Creamos el contenedor de contenedores dentro del panel
		boxGral.add(boxIdGasto);//Agregamos el contenedor datos de consorcio dentro del contenedor general
		boxGral.add(Box.createVerticalStrut(10));
		boxGral.add(Box.createVerticalStrut(10));
		boxGral.add(boxGasto);//Agregamos el contenedor datos de Usuario dentro del contenedor general
		boxGral.add(Box.createVerticalStrut(10));
		boxGral.add(boxFecha);
		JButton buttonModificar = new JButton("Modificar gasto");
		boxGral.add(buttonModificar);
		boxGral.add(boxTabla);
		
		buttonModificar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				modificarGasto();
			}
		});
		add(boxGral, BorderLayout.CENTER);//Agregamos el contenedor gral dentro del panel y definimos su ubicacion
	}
	
	private void modificarGasto()
	{
		if(esBlanco(txtAño.getText())||esBlanco(txtGasto.getText())||esBlanco(txtIdGasto.getText())||esBlanco(txtMes.getText())) {
			JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
			
		}
		else {
			Gasto gasto = new Gasto(); 
			gasto.setIdGasto(Integer.parseInt(txtIdGasto.getText())); // Seteamos la direccion que el usuario desea actualizar

			String itemSeleccionado = (String)opcionGasto.getSelectedItem();
			if ("Expensa ordinaria".equals(itemSeleccionado)) {
				gasto.setGastoOrd(Float.parseFloat(txtGasto.getText()));

			}
			
			else if ("Expensa Extraordinaria".equals(itemSeleccionado)) {
				gasto.setGastoExt(Float.parseFloat(txtGasto.getText()));
				}
			
			gasto.setMes(Integer.parseInt(txtMes.getText()));
			gasto.setAño(Integer.parseInt(txtAño.getText()));
			
			controlador.modificarGasto(gasto);
			limpiarCampo();
		}
	}
	
	public void limpiarCampo() {
		txtAño.setText("");
		txtGasto.setText("");
		txtIdGasto.setText("");
		txtMes.setText("");
	}
}
