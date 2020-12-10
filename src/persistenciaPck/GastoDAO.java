package persistenciaPck;

import java.util.List;

import modeloPck.Expensa;
import modeloPck.Gasto;

public interface GastoDAO {
	
	void crearGasto(Gasto gasto) throws AdministracionException;// CREA EL GASTO
	void EliminarGasto(Gasto gasto) throws AdministracionException;
	void ModificarGasto(Gasto gasto) throws AdministracionException;
	List<Gasto> mostrarGastosFiltrados(Gasto gasto) throws AdministracionException;
	List<Gasto> mostrarGastos() throws AdministracionException;
	List<Expensa> calcularExpensas(Expensa expensa) throws AdministracionException;
	Gasto validarIdEdificio(Gasto gasto) throws AdministracionException;
	Gasto validarIdGasto(Gasto gasto) throws AdministracionException;
}
