package vistaPck;
import javax.swing.*;

public class LoginFrame extends JFrame 
{
	private Controlador controlador;
	
	public LoginFrame(Controlador controlador) 
	{
		super();
		this.controlador = controlador;
		initUI();
	}
	public void initUI() 
	{
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(controlador.llamarPanelLogin());
		getContentPane().validate();
		
	}

}