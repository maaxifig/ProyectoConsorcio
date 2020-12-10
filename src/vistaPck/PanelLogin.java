package vistaPck;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import modeloPck.Consorcio;
import modeloPck.Usuario;

 
public class PanelLogin extends PanelAbstracto 
{	
    private Controlador controlador;
    private JTextField txtUsuario;
    private JPasswordField txtPwd;

	public PanelLogin(Controlador controlador) 
    {
		super();
		this.controlador = controlador;
        init();
    }
	
    private void init() 
    {
        setLayout(new BorderLayout());
        
      //************************* BOX USUARIO *****************************
		Box boxUsuario = Box.createHorizontalBox();
		JLabel lblUsuario = new JLabel("Usuario: ");

		boxUsuario.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxUsuario.add(lblUsuario);//Agregamos el label
		txtUsuario = new JTextField(30);
		boxUsuario.add(txtUsuario);
		//************************* BOX PASSWORD *****************************
		Box boxPwd = Box.createHorizontalBox();
		JLabel lblPwd = new JLabel("Password: ");
		boxPwd.add(lblPwd);
		txtPwd = new JPasswordField(30);
		boxPwd.add(txtPwd);
		//************************* BOX BOTON *****************************
		Box boxBoton = Box.createHorizontalBox();
		JButton jbtnLogin = new JButton("Ingresar");
		boxBoton.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxBoton.add(jbtnLogin);//Agregamos el label
		
		//************************* BOX GENERAL *****************************
		Box boxGral = Box.createVerticalBox();//Creamos el contenedor de contenedores dentro del panel
		boxGral.add(boxUsuario);//Agregamos el contenedor datos de consorcio dentro del contenedor general
		boxGral.add(Box.createVerticalStrut(10));
		boxGral.add(boxPwd);//Agregamos el contenedor datos de consorcio dentro del contenedor general
		boxGral.add(Box.createVerticalStrut(10));
		boxGral.add(boxBoton);//Agregamos el contenedor datos de consorcio dentro del contenedor general
        
		
		
		jbtnLogin.addActionListener(new ActionListener() {
			
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			loguearse();
		}
		});
        
    	add(boxGral, BorderLayout.CENTER);//Agregamos el contenedor gral dentro del panel y definimos su ubicacion
    }
    
    private void loguearse()
    {
    	Usuario usuario = new Usuario();
		usuario.setUsuario(txtUsuario.getText());
		usuario.setPass(txtPwd.getText());
		if(controlador.buscarMatch(usuario))
		{
			controlador.MostrarFrame();
		}
		else
			limpiarCampo();
    }
    public void limpiarCampo() {
    	txtPwd.setText("");
    	txtUsuario.setText("");
    }
}