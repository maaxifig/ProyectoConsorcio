package negocioPck;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modeloPck.Consorcio;
import persistenciaPck.AdministracionException;
import persistenciaPck.ConsorcioDAO;

public class ConsorcioBO {
	private ConsorcioDAO consorcioDAO;
	
	public void setConsorcioDAO(ConsorcioDAO consorcioDAO) 
	{
		this.consorcioDAO = consorcioDAO;
	}
	
	public void altaConsorcioBO(Consorcio cons) throws AdministracionException 
	{
			validarUnidades(cons);
			Consorcio consorcioValidado=consorcioDAO.validarConsorcio(cons);
			if (consorcioValidado.getDireccion()!=null){
				throw new AdministracionException("El consorcio ya existe");
			}
			else
			{
				consorcioDAO.crearConsorcio(cons);
				mostrarMensaje("Consorcio Agregado");
			}

	}
	
	public void eliminarConsorcioBO(Consorcio cons) throws AdministracionException
	{
			consorcioDAO.borrarConsorcio(cons);
	}

	public void modificarConsorcioBO(Consorcio consorcio) throws AdministracionException
	{
		Consorcio consorcioNuevoValidado = consorcioDAO.validarConsorcio(consorcio);
		if(consorcioNuevoValidado.getDireccion()== null)
			consorcioDAO.actualizarConsorcio(consorcio);
		else
			throw new AdministracionException("El nuevo consorcio ya esta en la base.");
	}
	
	public List<Consorcio> mostrarUnoBO(Consorcio consorcio) throws AdministracionException
	{
		List<Consorcio> ListaUnConsorcio = new ArrayList<Consorcio>();
		ListaUnConsorcio.add(consorcioDAO.buscarUnIdConsorcio(consorcio));
		return ListaUnConsorcio;
			
	}
	
	public List<Consorcio> mostrarConsorciosBO() throws AdministracionException
	{
		return consorcioDAO.mostrarConsorcios();
	}
	
	public void mostrarMensaje(String mensaje)
	{
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
	
	public void validarUnidades(Consorcio consorcio) throws AdministracionException
	{
		if (consorcio.getUnidades()<=0)
		{
			throw new AdministracionException("Las unidades tienen que ser mayor a cero");
		}
	}
	
}
