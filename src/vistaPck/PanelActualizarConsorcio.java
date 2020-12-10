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

import modeloPck.Consorcio;

public class PanelActualizarConsorcio extends PanelAbstracto 
{
	private Controlador controlador;
	private JTextField txtBuscarId;
	private JTextField txtActualizarDir;
	private JTextField txtUnidades;
	
	List<Consorcio> Listaconsorcio = new ArrayList<Consorcio>();
	
	public PanelActualizarConsorcio(Controlador controlador, List<Consorcio> Listaconsorcio) 
	{
		super();
		this.controlador = controlador;
		this.Listaconsorcio = Listaconsorcio;
		init();
	}
	
	
	private void init() 
	{
		setLayout(new BorderLayout());
		
		//************************* BOX DATO BUSCADO *****************************
		Box boxIDaModificar = Box.createHorizontalBox();//Creamos el contenedor para los datos del consorcio
		JLabel lblBuscarId = new JLabel("ID de consorcio a actualizar: ");//Label para definir el id a actualizar
		boxIDaModificar.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxIDaModificar.add(lblBuscarId);//Agregamos un label
		txtBuscarId = new JTextField(30);
		boxIDaModificar.add(txtBuscarId);//Agregamos un TextField y definimos el tamaño
		
		//************************* BOX DIRECCION NUEVA *****************************
		Box boxNuevaDireccion = Box.createHorizontalBox();//Creamos el contenedor para los datos del consorcio
		JLabel lblActualizarDir = new JLabel("Nueva direccion: ");//Label para definir el id a actualizar
		boxNuevaDireccion.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxNuevaDireccion.add(lblActualizarDir);//Agregamos un label
		txtActualizarDir = new JTextField(30);
		boxNuevaDireccion.add(txtActualizarDir);//Agregamos un TextField
		
		//************************* BOX UNIDADES *****************************
		Box boxNuevasUnidades = Box.createHorizontalBox();//Creamos el contenedor para los datos del consorcio
		JLabel lblUnidades = new JLabel("Unidades: ");//Label para definir el id a actualizar
		boxNuevasUnidades.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxNuevasUnidades.add(lblUnidades);//Agregamos un label
		txtUnidades = new JTextField(30);
		boxNuevasUnidades.add(txtUnidades);//Agregamos un TextField
		
		//************************* BOX TABLA *****************************
		Box boxTabla = Box.createHorizontalBox();
		boxTabla.add(crearTabla(new ConsorcioTableModel(Listaconsorcio)), BorderLayout.SOUTH);
		
		//************************* BOX GENERAL *****************************
		Box boxGral = Box.createVerticalBox();//Creamos el contenedor de contenedores dentro del panel
		boxGral.add(boxIDaModificar);//Agregamos el contenedor datos de consorcio dentro del contenedor general
		boxGral.add(Box.createVerticalStrut(10));
		boxGral.add(boxNuevaDireccion);//Agregamos el contenedor datos de consorcio dentro del contenedor general
		boxGral.add(Box.createVerticalStrut(10));
		boxGral.add(boxNuevasUnidades);//Agregamos el contenedor datos de consorcio dentro del contenedor general
		JButton buttonActualizar = new JButton("Actualizar");
		boxGral.add(buttonActualizar);
		boxGral.add(boxTabla);
		
		buttonActualizar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				actualizarConsorcio();
			}
		});
		add(boxGral, BorderLayout.CENTER);//Agregamos el contenedor gral dentro del panel y definimos su ubicacion
		
	}
	
	private void actualizarConsorcio()
	{
		Consorcio consorcio = new Consorcio(); 
		if(esBlanco(txtBuscarId.getText())==false) //Se evaluan campos obligatorios
		{
			consorcio.setID(Integer.parseInt(txtBuscarId.getText())); //Seteamos el ID obtenido por el usuario
			consorcio.setDireccion(txtActualizarDir.getText()); // Seteamos la direccion que el usuario desea actualizar
		
			if (esBlanco(txtActualizarDir.getText()) && esBlanco(txtUnidades.getText()))//se evalua que exista al menos un dato a modificar
			{
				JOptionPane.showMessageDialog(null, "No se ingresaron datos");
			}
			else
			{
				//Se valida si solamente el campo unidades esta en blanco
				if (esBlanco(txtUnidades.getText()))//Se evaluan campos con validaciones especiales
				{
					consorcio.setUnidades(0);
					controlador.actualizarConsorcio(consorcio);
				}
				else
				{
					//Se valida si se ingresan 0 unidades o negativo, en ese caso se dispara un cartel de advertencia de mal ingreso y se limpia el campo
					if (validarUnidades(txtUnidades.getText())==false)
					{
						JOptionPane.showMessageDialog(null, "Unidades invalidas");
					}
					else
					{
					//Se valida si no se ingreso unidades, en este caso se estima que no se desea actualizar las unidades y se quiere mantener
					//la anteriormente cargada.
						consorcio.setUnidades(Integer.parseInt(txtUnidades.getText())); // Seteamos las unidades que el usuario desea actualizar
						controlador.actualizarConsorcio(consorcio);
						limpiarCampo();
					}
				}
			}
		}
		else
			JOptionPane.showMessageDialog(null, "El campo ID de consorcio es obligatorio");
	}
	
	public void limpiarCampo() {
		txtUnidades.setText("");
		txtActualizarDir.setText("");
		txtBuscarId.setText("");
	}
	
}
