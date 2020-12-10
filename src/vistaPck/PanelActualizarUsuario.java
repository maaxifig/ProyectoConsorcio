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
import modeloPck.Usuario;

public class PanelActualizarUsuario extends PanelAbstracto 
{
		private Controlador controlador;
		private JTextField txtUsuario;
		private JTextField txtNuevoUsuario;
		private JTextField txtPaswd;
		private JTextField txtPermiso;

		List<Usuario> Listausuario = new ArrayList<Usuario>();
		
	public PanelActualizarUsuario(Controlador controlador, List<Usuario> Listausuario) 
	{
		super();
		this.controlador = controlador;
		this.Listausuario = Listausuario;
		init();
	}

	private void init() 
	{
		setLayout(new BorderLayout());
		
		//************************* BOX DATO BUSCADO *****************************
		Box boxUsuarioBuscado = Box.createHorizontalBox();//Creamos el contenedor para los datos del consorcio
		JLabel lblUsuario = new JLabel("Usuario a modificar: ");//Label para definir el id a actualizar
		boxUsuarioBuscado.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxUsuarioBuscado.add(lblUsuario);//Agregamos un label
		txtUsuario = new JTextField(30);
		boxUsuarioBuscado.add(txtUsuario);//Agregamos un TextField y definimos el tamaño
		
		//************************* BOX DATO BUSCADO *****************************
		Box boxNuevoUsuario = Box.createHorizontalBox();//Creamos el contenedor para los datos del consorcio
		JLabel lblNuevoUsuario = new JLabel("Nuevo usuario: ");//Label para definir el id a actualizar
		boxNuevoUsuario.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxNuevoUsuario.add(lblNuevoUsuario);//Agregamos un label
		txtNuevoUsuario = new JTextField(30);
		boxNuevoUsuario.add(txtNuevoUsuario);//Agregamos un TextField y definimos el tamaño
		
		//************************* BOX PASWD NUEVO *****************************
		Box boxPaswd = Box.createHorizontalBox();//Creamos el contenedor para los datos del consorcio
		JLabel lblPaswd = new JLabel("Nueva passwd: ");//Label para definir el id a actualizar
		boxPaswd.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxPaswd.add(lblPaswd);//Agregamos un label
		txtPaswd = new JTextField(30);
		boxPaswd.add(txtPaswd);//Agregamos un TextField
		
		//************************* BOX PERMISO NUEVO *****************************
		Box boxPermiso = Box.createHorizontalBox();//Creamos el contenedor para los datos del consorcio
		JLabel lblPermiso = new JLabel("Nuevo Permiso: ");//Label para definir el id a actualizar
		boxPermiso.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxPermiso.add(lblPermiso);//Agregamos un label
		txtPermiso = new JTextField(30);
		boxPermiso.add(txtPermiso);//Agregamos un TextField
		
		//************************* BOX TABLA *****************************
		Box boxTabla = Box.createHorizontalBox();
		boxTabla.add(crearTabla(new UsuarioTableModel(Listausuario)), BorderLayout.SOUTH);
		
		//************************* BOX GENERAL *****************************
		Box boxGral = Box.createVerticalBox();//Creamos el contenedor de contenedores dentro del panel
		boxGral.add(boxUsuarioBuscado);//Agregamos el contenedor datos de consorcio dentro del contenedor general
		boxGral.add(Box.createVerticalStrut(10));
		boxGral.add(boxNuevoUsuario);//Agregamos el contenedor datos de consorcio dentro del contenedor general
		boxGral.add(Box.createVerticalStrut(10));
		boxGral.add(boxPaswd);//Agregamos el contenedor datos de consorcio dentro del contenedor general
		boxGral.add(Box.createVerticalStrut(10));
		boxGral.add(boxPermiso);//Agregamos el contenedor datos de consorcio dentro del contenedor general
		JButton buttonActualizar = new JButton("Actualizar");
		boxGral.add(buttonActualizar);
		boxGral.add(boxTabla);
		
		buttonActualizar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				actualizarUsuario();
			}
		});
		add(boxGral, BorderLayout.CENTER);//Agregamos el contenedor gral dentro del panel y definimos su ubicacion
	}
	
	private void actualizarUsuario()
	{
		Usuario usuarioNuevo = new Usuario();
		Usuario usuarioBuscado = new Usuario();
		
		if(esBlanco(txtUsuario.getText())==false) //Se evaluan campos obligatorios
		{
			usuarioBuscado.setUsuario(txtUsuario.getText());
			usuarioNuevo.setUsuario(txtNuevoUsuario.getText()); //Seteamos el ID obtenido por el usuario
			usuarioNuevo.setPass(txtPaswd.getText()); // Seteamos la direccion que el usuario desea actualizar
			
		
			if (esBlanco(txtNuevoUsuario.getText()) && esBlanco(txtPaswd.getText()) && esBlanco(txtPermiso.getText()))//se evalua que exista al menos un dato a modificar
			{
				JOptionPane.showMessageDialog(null, "No se ingresaron datos");
			}
			else
			{
				if (!esBlanco(txtPermiso.getText()))//Se evaluan campos con validaciones especiales
					usuarioNuevo.setPermiso(Integer.parseInt(txtPermiso.getText()));
				
				controlador.actualizarUsuario(usuarioNuevo, usuarioBuscado);
				limpiarCampo();
			}
		}
		else
			JOptionPane.showMessageDialog(null, "El campo usuario es obligatorio");
		
		
	}
	public void limpiarCampo() {
		txtUsuario.setText("");
		txtNuevoUsuario.setText("");
		txtPaswd.setText("");
		txtPermiso.setText("");
	}
	
}
