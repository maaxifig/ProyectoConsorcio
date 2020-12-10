package persistenciaPck;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modeloPck.Consorcio;
import modeloPck.Expensa;
import modeloPck.Gasto;
import modeloPck.Usuario;

public class GastoJDBCDAO implements GastoDAO 
{

	@Override
	public void crearGasto(Gasto gasto) throws AdministracionException {
		
		Connection c = DBManager.getInstance().conectarse();

		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO finanzas (id_edificio, gasto_ord, gasto_ext, año, mes) VALUES ("+gasto.getIdEdif()+","+gasto.getGastoOrd()+","+gasto.getGastoExt()+","+gasto.getAño()+","+gasto.getMes()+")";
			
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
			}
			throw new AdministracionException("Hubo un error inesperado", e);
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
			}
		}
		
	}

	@Override
	public void EliminarGasto(Gasto gasto) throws AdministracionException {
		String sql = "DELETE FROM finanzas WHERE id_gasto = " + gasto.getIdGasto();
		Connection c = DBManager.getInstance().conectarse();
		try {
			Statement s = c.createStatement();
			int deletedRows = s.executeUpdate(sql);
			if(deletedRows == 0) {
				throw new AdministracionException("El Gasto que se desea eliminar no existe");
			}
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
			}
			throw new AdministracionException("Hubo un error inesperado", e);
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
			}
		}		
	}
	
	@Override
	public void ModificarGasto(Gasto gasto) throws AdministracionException 
	{
		String sql = "UPDATE finanzas SET id_edificio= " + gasto.getIdEdif() + ", gasto_ord= "+gasto.getGastoOrd()+", gasto_ext="+gasto.getGastoExt()+", año="+gasto.getAño()+", mes="+gasto.getMes() +" WHERE id_gasto =" + gasto.getIdGasto();
		Connection c = DBManager.getInstance().conectarse();
		
		try {
			Statement s = c.createStatement();
			int updateRows=s.executeUpdate(sql);
			if (updateRows == 0)
			{
				throw new AdministracionException("El gasto que se desea actualizar no existe");
			}
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				//no hago nada
			}
			throw new AdministracionException("Hubo un error inesperado", e);
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				//no hago nada
			}
		}	
	}	
	

	@Override
	public List<Gasto> mostrarGastos() throws AdministracionException {
		
		ArrayList<Gasto> lista = new ArrayList<Gasto>();//Instanciamos la lista de tipo Consorcio
//		System.out.println("Estoy mostrar dao");
		String sql = "SELECT * FROM finanzas";
		Connection c = DBManager.getInstance().conectarse();// SINGLETON
		

		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) 
			{
				Gasto gasto = new Gasto();
				gasto.setIdEdificio(rs.getInt("id_edificio"));//Se carga el ID del objeto Consorcio
				gasto.setIdGasto(rs.getInt("id_gasto"));//Se carga la direccion del objeto Consorcio
				gasto.setAño(rs.getInt("año"));
				gasto.setMes(rs.getInt("mes"));
				gasto.setGastoOrd(rs.getFloat("gasto_ord"));
				gasto.setGastoExt(rs.getFloat("gasto_ext"));

				lista.add(gasto);//Se agrega el objeto Consorcio a la lista
			}

		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				//no hago nada
			}
			throw new AdministracionException("Hubo un error inesperado", e);
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				//no hago nada
			}
		}
		return lista;//Se devuelve la lista cargada
	}

	@Override
	public List<Gasto> mostrarGastosFiltrados(Gasto gasto) throws AdministracionException 
	{
		String sql = "SELECT * FROM finanzas WHERE id_edificio = "+gasto.getIdEdif()+" AND año = "+gasto.getAño()+" AND mes = "+gasto.getMes();
		Connection c = DBManager.getInstance().conectarse();// SINGLETON
		ArrayList<Gasto> lista = new ArrayList<Gasto>();//Instanciamos la lista de tipo Consorcio
		try 
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next())  // CUANDO TENGO MAS DE UN DATO EN BASE, PARA IMPRIMIR VA WHILE.
			{
				Gasto gastoEncontrado = new Gasto();
				
				gastoEncontrado.setIdEdificio(rs.getInt("id_edificio"));
				gastoEncontrado.setIdGasto(rs.getInt("id_gasto"));
				gastoEncontrado.setGastoOrd(rs.getFloat("gasto_ord"));
				gastoEncontrado.setGastoExt(rs.getFloat("gasto_ext"));
				gastoEncontrado.setAño(rs.getInt("año"));
				gastoEncontrado.setMes(rs.getInt("mes"));
				lista.add(gastoEncontrado);//Se agrega el objeto Consorcio a la lista
			}
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				//no hago nada
			}
			throw new AdministracionException("Hubo un error inesperado", e);
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				//no hago nada
			}
		}
		return lista;
	
	}
	
	public List<Expensa> calcularExpensas(Expensa expensa) throws AdministracionException
	{
		String sqlF = "SELECT SUM(gasto_ext), SUM(gasto_ord) FROM finanzas WHERE id_edificio = "+expensa.getIdEdif()+" AND año = "+expensa.getAño()+" AND mes = "+expensa.getMes();
		String sqlD = "SELECT nUnidad, m2 FROM departamento WHERE id_edificio="+expensa.getIdEdif();
		
		Connection c = DBManager.getInstance().conectarse();// SINGLETON
		ArrayList<Expensa> lista = new ArrayList<Expensa>();//Instanciamos la lista de tipo Consorcio
		try 
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sqlF);
			rs.next();
			
			int sumaExt = rs.getInt(1);
			int sumaOrd = rs.getInt(2);
			rs = s.executeQuery(sqlD);
			
			while(rs.next())
			{
				Expensa expensaObtenida = new Expensa();
				
				expensaObtenida.setIdEdificio(expensa.getIdEdif());
				expensaObtenida.setnUnidad(rs.getInt("nUnidad"));
				expensaObtenida.setExpensaExt(sumaExt);
				expensaObtenida.setExpensaOrd(sumaOrd);
				expensaObtenida.setM2(rs.getDouble("m2"));
				expensaObtenida.setAño(expensa.getAño());
				expensaObtenida.setMes(expensa.getMes());
				lista.add(expensaObtenida);//Se agrega el objeto Consorcio a la lista
			}
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				//no hago nada
			}
			throw new AdministracionException("Hubo un error inesperado", e);
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				//no hago nada
			}
		}
		return lista;
	}
	
	public Gasto validarIdEdificio(Gasto gasto) throws AdministracionException {
			
			Gasto gastoValidado = new Gasto();
			Connection c = DBManager.getInstance().conectarse();// SINGLETON
			String sql = "SELECT * FROM edificio WHERE id = '"+gasto.getIdEdif()+"'";
			try 
			{
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql);
				if(rs.next())
				{
					gastoValidado.setIdEdificio(rs.getInt("id"));
					
				}
				}catch (SQLException e) {
					try {
						c.rollback();
						e.printStackTrace();
					} catch (SQLException e1) {
					}
					throw new AdministracionException("Hubo un error inesperado", e);
				} finally {
					try {
						c.close();
					} catch (SQLException e1) {
					}
				}
			System.out.println(gastoValidado);
			return gastoValidado;
		}
	
	public Gasto validarIdGasto(Gasto gasto) throws AdministracionException {

		Gasto gastoValidado = new Gasto();
		Connection c = DBManager.getInstance().conectarse();// SINGLETON
		String sql = "SELECT * FROM finanzas WHERE id_gasto = '"+gasto.getIdGasto()+"'";
		try 
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next())
			{
				gastoValidado.setIdGasto(rs.getInt("id_gasto"));
			}
			}catch (SQLException e) {
				try {
					c.rollback();
					e.printStackTrace();
				} catch (SQLException e1) {
				}
				throw new AdministracionException("Hubo un error inesperado", e);
			} finally {
				try {
					c.close();
				} catch (SQLException e1) {
				}
			}
			
		return gastoValidado;
	}



}
