package persistenciaPck;

import java.util.List;

import modeloPck.Consorcio;

public interface ConsorcioDAO 
{
	void crearConsorcio(Consorcio consorcio) throws AdministracionException;// CREA EL CONSORCIO
	
	void borrarConsorcio(Consorcio consorcio) throws AdministracionException;// ELIMINA UN CONSORCIO
	
	void actualizarConsorcio(Consorcio consorcio) throws AdministracionException;//ACTUALIZA UN CONSORCIO
	
	List<Consorcio> mostrarConsorcios() throws AdministracionException;//MUESTRA TODOS LOS CONSORCIOS
	
	Consorcio validarConsorcio(Consorcio consorcio) throws AdministracionException;
	
	Consorcio buscarUnIdConsorcio(Consorcio consorcio) throws AdministracionException;
}