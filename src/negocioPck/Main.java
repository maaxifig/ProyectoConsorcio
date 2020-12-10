package negocioPck;


import persistenciaPck.*;
import  vistaPck.*;


public class Main 
{
	public static void main(String [] args) throws AdministracionException 
	{
		
//		TableManager tab = new TableManager();
//		tab.borrarTabla();//Borra cualquier tabla creada
//		tab.crearTabla();//Crea la tabla sino existe
			
		Controlador controlador = new Controlador();
		controlador.MostrarFrameLogin();
	}
	
}