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

import modeloPck.Usuario;
 
public class PanelAgregarUsuario extends PanelAbstracto 
{	
    private Controlador controlador;
    private JTextField txtUsuario;
    private JTextField txtPass;
    private JTextField txtPermiso;
    
    List<Usuario> Listausuario = new ArrayList<Usuario>();
	public PanelAgregarUsuario(Controlador controlador, List<Usuario> Listausuario) 
    {
		super();
		this.controlador = controlador;
		this.Listausuario = Listausuario;
        initAgregar(); 
    }
	
    private void initAgregar() 
    {
        setLayout(new BorderLayout());
        
      //************************* BOX USUARIO *****************************
		Box boxUsuario = Box.createHorizontalBox();
		JLabel lblUsuario = new JLabel("Ingresar Usuario: ");
		boxUsuario.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxUsuario.add(lblUsuario);//Agregamos el label
		txtUsuario = new JTextField(30);
		boxUsuario.add(txtUsuario);//Agregamos el TextField y definimos el tamaño
		
		//************************* BOX PASSWORD *****************************
		Box boxPass = Box.createHorizontalBox();
		JLabel lblPass = new JLabel("Ingresar Password: ");
		boxPass.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxPass.add(lblPass);//Agregamos el label
		txtPass = new JTextField(30);
		boxPass.add(txtPass);//Agregamos un TextField
		
		//************************* BOX PERMISO *****************************
		Box boxPermiso = Box.createHorizontalBox();
		JLabel lblPermiso = new JLabel("Ingresar Permiso: ");
		boxPermiso.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxPermiso.add(lblPermiso);//Agregamos el label
		txtPermiso = new JTextField(30);
		boxPermiso.add(txtPermiso);//Agregamos un TextField
		
		//**********************************BOX TABLA*************************
		Box boxTabla = Box.createHorizontalBox();
		boxTabla.add(crearTabla(new UsuarioTableModel(Listausuario)), BorderLayout.SOUTH);
		
		//************************* BOX GENERAL *****************************
		Box boxGral = Box.createVerticalBox();//Creamos el contenedor de contenedores dentro del panel
		boxGral.add(boxUsuario);//Agregamos el contenedor datos de consorcio dentro del contenedor general
		boxGral.add(Box.createVerticalStrut(10));
		boxGral.add(boxPass);//Agregamos el contenedor datos de Usuario dentro del contenedor general
		boxGral.add(Box.createVerticalStrut(10));
		boxGral.add(boxPermiso);
		JButton buttonAdd = new JButton("Agregar");
		boxGral.add(buttonAdd);
		boxGral.add(boxTabla);
		
        buttonAdd.addActionListener(new ActionListener() {
			
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			agregarUsuario();
		}
		});
        
    	add(boxGral, BorderLayout.CENTER);//Agregamos el contenedor gral dentro del panel y definimos su ubicacion
    }
    
    private void agregarUsuario()
    {
    	if (esBlanco(txtUsuario.getText()))
		{
			JOptionPane.showMessageDialog(null, "El campo usuario es obligatorio");
		}
		else {
			Usuario usuario = new Usuario();
			usuario.setUsuario(txtUsuario.getText());
			usuario.setPass(txtPass.getText());
			if(esBlanco(txtPermiso.getText()))
				usuario.setPermiso(1);
			else
				usuario.setPermiso(Integer.parseInt(txtPermiso.getText()));
			controlador.crearUsuario(usuario);
			limpiarCampo();
		}
    }
    
    public void limpiarCampo()
    {
    	txtUsuario.setText("");
    	txtPass.setText("");
    	txtPermiso.setText("");
    }
}