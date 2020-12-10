package negocioPck;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import modeloPck.Expensa;
import modeloPck.Gasto;
import persistenciaPck.AdministracionException;
import persistenciaPck.GastoDAO;

public class GastoBO {

	private GastoDAO gastoDAO;

	public void setGastoDAO(GastoDAO gastoDAO) 
	{
		this.gastoDAO = gastoDAO;
	}
	
	public void altaGastoBO(Gasto gasto) throws AdministracionException 
	{		
			validarFecha(gasto.getMes(),gasto.getAño());
			validarMonto(gasto);
			Gasto gastoValidado = gastoDAO.validarIdEdificio(gasto);
			if(gastoValidado.getIdEdif()!= 0)
			{
				gastoDAO.crearGasto(gasto);
				mostrarMensaje("Gasto Agregado");
			}
			else
				throw new AdministracionException("El edificio ingresado no existe");
			
	}
	
	public void eliminarGastoBO(Gasto gasto) throws AdministracionException
	{
		Gasto gastoValidado=gastoDAO.validarIdGasto(gasto);
		if(gastoValidado.getIdGasto()!=0 && gastoValidado.getIdGasto()>0)
		{
			gastoDAO.EliminarGasto(gasto);
			mostrarMensaje("Gasto Eliminado");
		}
		else
			throw new AdministracionException("El id ingresado es incorrecto");
	}
	
	public void modificarGastoBO(Gasto gasto) throws AdministracionException
	{
		validarFecha(gasto.getMes(),gasto.getAño());
		gastoDAO.ModificarGasto(gasto);
	}
	
	public List<Gasto> mostrarGastosFiltradosBO(Gasto gasto) throws AdministracionException
	{
		validarFecha(gasto.getMes(),gasto.getAño());
		return gastoDAO.mostrarGastosFiltrados(gasto);
	}
	
	public List<Gasto> mostrarGastosBO() throws AdministracionException
	{
		return gastoDAO.mostrarGastos();
	}
	
	public List<Expensa> mostrarExpensasBO(Expensa expensa) throws AdministracionException
	{
		List<Expensa> ExpensaList = new ArrayList<Expensa>();
		validarFecha(expensa.getMes(),expensa.getAño());
		ExpensaList = gastoDAO.calcularExpensas(expensa);
		ExpensaList = trabajarLista(ExpensaList);
		return ExpensaList;
	}
	
	public List<Expensa> trabajarLista(List<Expensa> ExpensaList)
	{
		Expensa ex = ExpensaList.get(0);// Se obtiene cualquier fila para guardar en variable local el total de expensas ord y ext.
		
		float totalOrd = ex.getExpensaOrd();
		float totalExt = ex.getExpensaExt();
		double expO, expE;
		double mTotales = 0, m2Actual,porcM2;
		
		for(Expensa exp : ExpensaList)
		{
			mTotales += exp.getm2();
		}
		List<Expensa> listaTrabajada = new ArrayList<Expensa>();
		
		for(Expensa registroActual:ExpensaList)
		{
			m2Actual = registroActual.getm2();
			porcM2=(m2Actual*100)/mTotales;
			
			expO = (porcM2*totalOrd) / 100;
			expE = (porcM2*totalExt)/100;
			
			Expensa expCalc = new Expensa();
			expCalc.setAño(ex.getAño());
			expCalc.setExpensaExt((float) expE);
			expCalc.setExpensaOrd((float) expO);
			expCalc.setIdEdificio(ex.getIdEdif());
			expCalc.setM2(m2Actual);
			expCalc.setMes(ex.getMes());
			expCalc.setnUnidad(registroActual.getnUnidad());

			listaTrabajada.add(expCalc);			
		}
		return listaTrabajada;
	}
	
//	***********************VALIDACIONES***************************
	
	public void validarFecha(int mes, int anio) throws AdministracionException
	{
		Calendar calendario = Calendar.getInstance();
		int anioActual = calendario.get(Calendar.YEAR);
		if((mes>12 || mes< 1)||anio>anioActual)
			throw new AdministracionException("Fecha invalida");
	}
	
	
	public void validarMonto(Gasto gasto) throws AdministracionException
	{
		if (gasto.getGastoExt()<=0 && gasto.getGastoOrd()<= 0)
		{
			throw new AdministracionException("El monto debe ser mayor a 0");
		}
	}
	
	
	public void mostrarMensaje(String mensaje)
	{
		JOptionPane.showMessageDialog(null, mensaje);
	}
}
