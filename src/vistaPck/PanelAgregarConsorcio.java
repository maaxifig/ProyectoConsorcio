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

import modeloPck.Consorcio;
 
public class PanelAgregarConsorcio extends PanelAbstracto 
{	
    private Controlador controlador;
    private JTextField txtDireccion;
    private JTextField txtUnidades;

    List<Consorcio> Listaconsorcio = new ArrayList<Consorcio>();
    
	public PanelAgregarConsorcio(Controlador controlador, List<Consorcio> Listaconsorcio) 
    {
		super();
		this.controlador = controlador;
		this.Listaconsorcio = Listaconsorcio;
        initAgregar();
        
    }
	
	//Funcion que agregar el consorcio especificado
    private void initAgregar() 
    {
        setLayout(new BorderLayout());
        
      //************************* BOX DIRECCION *****************************
		Box boxDireccion = Box.createHorizontalBox();
		JLabel lblDireccion = new JLabel("Ingresar Consorcio: ");
		boxDireccion.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxDireccion.add(lblDireccion);//Agregamos el label
		txtDireccion = new JTextField(30);
		boxDireccion.add(txtDireccion);//Agregamos el TextField y definimos el tamaño
		
		//************************* BOX UNIDADES *****************************
		Box boxUnidades = Box.createHorizontalBox();
		JLabel lblUnidades = new JLabel("Ingresar Unidades: ");
		boxUnidades.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxUnidades.add(lblUnidades);//Agregamos el label
		txtUnidades = new JTextField(30);
		boxUnidades.add(txtUnidades);//Agregamos un TextField
		
		//**********************************BOX TABLA*************************
		Box boxTabla = Box.createHorizontalBox();
		boxTabla.add(crearTabla(new ConsorcioTableModel(Listaconsorcio)), BorderLayout.SOUTH);
		
		//************************* BOX GENERAL *****************************
		Box boxGral = Box.createVerticalBox();//Creamos el contenedor de contenedores dentro del panel
		boxGral.add(boxDireccion);//Agregamos el contenedor datos de consorcio dentro del contenedor general
		boxGral.add(Box.createVerticalStrut(10));
		boxGral.add(boxUnidades);//Agregamos el contenedor datos de consorcio dentro del contenedor general
		JButton buttonAdd = new JButton("Ingresar");
		boxGral.add(buttonAdd);
		boxGral.add(boxTabla);
        
        buttonAdd.addActionListener(new ActionListener() {
			
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			agregarConsorcio();
		}
		});
        
    	add(boxGral, BorderLayout.CENTER);//Agregamos el contenedor gral dentro del panel y definimos su ubicacion
    }
    
    private void agregarConsorcio()
    {
    	if (esBlanco(txtUnidades.getText()) ||esBlanco(txtDireccion.getText()))
		{
			JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
		}
		else
		{
			Consorcio consorcio = new Consorcio();
			consorcio.setDireccion(txtDireccion.getText());
			consorcio.setUnidades(Integer.parseInt(txtUnidades.getText()));
			controlador.crearConsorcio(consorcio);
			limpiarCampo();
			
		}
    }
    
    public void limpiarCampo()
    {
    	txtUnidades.setText("");
    	txtDireccion.setText("");
    }
}