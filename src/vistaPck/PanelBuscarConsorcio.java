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

import modeloPck.Consorcio;

public class PanelBuscarConsorcio extends PanelAbstracto
{
	private Controlador controlador;
	private JTextField txtBuscar;

	List<Consorcio> Listaconsorcio = new ArrayList<Consorcio>();
	
	public PanelBuscarConsorcio(Controlador controlador, List<Consorcio> Listaconsorcio) 
    {
		super();
        this.controlador = controlador;
        this.Listaconsorcio = Listaconsorcio;
        initBuscar();
    }
	
	//*************************************************************************************************
    //Funcion que busca el consorcio y lo imprime en el JTextField
    private void initBuscar()
    {
    	setLayout(new BorderLayout());
       
      //************************* BOX BUSCAR DATO *****************************
  		Box boxBuscar = Box.createHorizontalBox();
  		JLabel lblBuscar = new JLabel("Id a buscar");
  		JButton buttonMostrar = new JButton("Buscar");
  		boxBuscar.add(Box.createHorizontalStrut(10));//Definimos la separacion
  		boxBuscar.add(lblBuscar);//Agregamos el label
  		boxBuscar.add(buttonMostrar);
  		txtBuscar = new JTextField(30);
  		boxBuscar.add(txtBuscar);//Agregamos el TextField y definimos el tamaño
  		
  		//************************* BOX MOSTRAR DATO *****************************
  		Box boxMostrar = Box.createHorizontalBox();
  		boxMostrar.add(Box.createHorizontalStrut(10));//Definimos la separacion
  		boxMostrar.add(crearTabla(new ConsorcioTableModel(Listaconsorcio)), BorderLayout.SOUTH);
        //************************* BOX GENERAL *****************************
  		Box boxGral = Box.createVerticalBox();//Creamos el contenedor de contenedores dentro del panel
  		boxGral.add(boxBuscar);//Agregamos el contenedor datos de consorcio dentro del contenedor general
  		boxGral.add(Box.createVerticalStrut(10));
        boxGral.add(boxMostrar);  	
        
        buttonMostrar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			buscarConsorcio();			
		}
		});
        
        add(boxGral, BorderLayout.CENTER);//Agregamos el contenedor gral dentro del panel y definimos su ubicacion
    }
    
    private void buscarConsorcio() {
    	if(esBlanco(txtBuscar.getText())){
			JOptionPane.showMessageDialog(null, "El id de consorcio es obligatorio");
		}
		else {
			Consorcio consorcio = new Consorcio();
			consorcio.setID(Integer.parseInt(txtBuscar.getText()));//Obtiene el string ingresado, lo convierte a entero y lo guarda en consorcio
			controlador.mostrarConsorcio(consorcio);
			limpiarCampo();
		}
    }
    
    public void limpiarCampo() {
    	txtBuscar.setText("");
    }
}
