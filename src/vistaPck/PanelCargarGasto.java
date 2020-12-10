package vistaPck;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modeloPck.Gasto;
 
public class PanelCargarGasto extends PanelAbstracto 
{	
    private Controlador controlador;
    private JTextField txtEdificio;
    private JTextField txtGasto;
    private JComboBox opcionGasto;
    private JTextField txtMes;
    private JTextField txtAño;
    
	public PanelCargarGasto(Controlador controlador) 
    {
		super();
		this.controlador = controlador;
        initAgregar(); 
    }
	
    private void initAgregar() 
    {
        setLayout(new BorderLayout());
        
      //************************* BOX Edificio *****************************
		Box boxEdificio = Box.createHorizontalBox();
		JLabel lblEdificio = new JLabel("Ingresar ID Edificio: ");
		boxEdificio.add(Box.createHorizontalStrut(10));//Definimos la separacion
		boxEdificio.add(lblEdificio);//Agregamos el label
		txtEdificio= new JTextField(30);
		boxEdificio.add(txtEdificio);//Agregamos el TextField y definimos el tamaÃ±o
		
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

		//************************* BOX GENERAL *****************************
		Box boxGral = Box.createVerticalBox();//Creamos el contenedor de contenedores dentro del panel
		boxGral.add(boxEdificio);//Agregamos el contenedor datos de consorcio dentro del contenedor general
		boxGral.add(Box.createVerticalStrut(10));
		boxGral.add(boxGasto);//Agregamos el contenedor datos de Usuario dentro del contenedor general
		boxGral.add(Box.createVerticalStrut(10));
		boxGral.add(boxFecha);
		JButton buttonAdd = new JButton("Agregar");
		boxGral.add(buttonAdd);
		
		
        buttonAdd.addActionListener(new ActionListener() {
			
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			crearGasto();
		}
		});
        
        
    	add(boxGral, BorderLayout.CENTER);
    }
    
    private void crearGasto()
    {
    	if (!esBlanco(txtAño.getText())&&!esBlanco(txtEdificio.getText())&&!esBlanco(txtGasto.getText())&&!esBlanco(txtMes.getText()))
		{
			Gasto gasto = new Gasto();
			gasto.setIdEdificio(Integer.parseInt(txtEdificio.getText()));
			gasto.setAño(Integer.parseInt(txtAño.getText()));
			gasto.setMes(Integer.parseInt(txtMes.getText()));
			
			
			String itemSeleccionado = (String)opcionGasto.getSelectedItem();
			if ("Expensa ordinaria".equals(itemSeleccionado)) {
				gasto.setGastoOrd(Float.parseFloat(txtGasto.getText()));

			}
			
			else if ("Expensa Extraordinaria".equals(itemSeleccionado)) {
				gasto.setGastoExt(Float.parseFloat(txtGasto.getText()));
				}
			controlador.crearGasto(gasto);
			limpiarCampo();
		}
		else
			JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
		
		

    }
    public void limpiarCampo() {
    	txtEdificio.setText("");
    	txtAño.setText("");
    	txtGasto.setText("");
    	txtMes.setText("");
    }
}