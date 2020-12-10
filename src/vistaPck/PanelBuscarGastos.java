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
import javax.swing.JTextField;

import modeloPck.Gasto;

public class PanelBuscarGastos extends PanelAbstracto
{
	private Controlador controlador;
	private JTextField txtBuscarID;
	private JTextField txtBuscarAnio;
	private JTextField txtBuscarMes;

	List<Gasto> Listagastos = new ArrayList<Gasto>();
	public PanelBuscarGastos(Controlador controlador, List<Gasto> Listagastos) 
    {
		super();
        this.controlador = controlador;
        this.Listagastos = Listagastos;
        initBuscar();
    }
	
	//*************************************************************************************************
    //Funcion que busca el consorcio y lo imprime en el JTextField
	
	
	
    private void initBuscar()
    {
    	setLayout(new BorderLayout());
       
      //************************* BOX BUSCAR ID *****************************
  		Box boxBuscarID = Box.createHorizontalBox();
  		JLabel lblBuscarID = new JLabel("ID de edificio:");
  		JButton buttonMostrar = new JButton("Buscar");
  		boxBuscarID.add(Box.createHorizontalStrut(10));//Definimos la separacion
  		boxBuscarID.add(lblBuscarID);//Agregamos el label
  		boxBuscarID.add(buttonMostrar);
  		txtBuscarID = new JTextField(30);
  		boxBuscarID.add(txtBuscarID);//Agregamos el TextField y definimos el tama√±o
  		
  		//************************* BOX BUSCAR A—O *****************************
  		Box boxBuscarAnio = Box.createHorizontalBox();
  		JLabel lblBuscarAnio = new JLabel("Anio:");
  		boxBuscarAnio.add(Box.createHorizontalStrut(10));//Definimos la separacion
  		boxBuscarAnio.add(lblBuscarAnio);//Agregamos el label
  		txtBuscarAnio =  new JTextField(30);
  		boxBuscarAnio.add(txtBuscarAnio);//Agregamos el TextField y definimos el tama√±o
  		
  		
//  	************************* BOX BUSCAR MES *****************************
  		Box boxBuscarMes = Box.createHorizontalBox();
  		JLabel lblBuscarMes = new JLabel("Mes:");
  		boxBuscarMes.add(Box.createHorizontalStrut(10));//Definimos la separacion
  		boxBuscarMes.add(lblBuscarMes);//Agregamos el label
  		txtBuscarMes =  new JTextField(30);
  		boxBuscarMes.add(txtBuscarMes);//Agregamos el TextField y definimos el tama√±o
  		
  		//************************* BOX MOSTRAR DATO *****************************
  		Box boxMostrar = Box.createHorizontalBox();
  		boxMostrar.add(Box.createHorizontalStrut(10));//Definimos la separacion
  		boxMostrar.add(crearTabla(new GastoTableModel(Listagastos)), BorderLayout.SOUTH);
        //************************* BOX GENERAL *****************************
  		Box boxGral = Box.createVerticalBox();//Creamos el contenedor de contenedores dentro del panel
  		boxGral.add(boxBuscarID);//Agregamos el contenedor datos de consorcio dentro del contenedor general
  		boxGral.add(boxBuscarAnio);
  		boxGral.add(boxBuscarMes);
  		boxGral.add(Box.createVerticalStrut(10));
        boxGral.add(boxMostrar);  	
        
        buttonMostrar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			buscarGasto();
			
		}
		});
        
        add(boxGral, BorderLayout.CENTER);//Agregamos el contenedor gral dentro del panel y definimos su ubicacion
    }
    
    private void buscarGasto()
    {
    	if(esBlanco(txtBuscarAnio.getText())||esBlanco(txtBuscarID.getText())||esBlanco(txtBuscarMes.getText()))
		{
			JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
		}
		else {
			Gasto gasto = new Gasto();
			gasto.setIdEdificio(Integer.parseInt(txtBuscarID.getText()));//Obtiene el string ingresado, lo convierte a entero y lo guarda en consorcio
			gasto.setAÒo(Integer.parseInt(txtBuscarAnio.getText()));
			gasto.setMes(Integer.parseInt(txtBuscarMes.getText()));
			controlador.mostrarGastosFiltrado(gasto);
			limpiarCampo();
		}
    }
    
    public void limpiarCampo() {
    	txtBuscarAnio.setText("");
    	txtBuscarID.setText("");
    	txtBuscarMes.setText("");
    }

}
