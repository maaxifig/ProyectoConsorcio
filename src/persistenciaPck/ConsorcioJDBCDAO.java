package persistenciaPck;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modeloPck.Consorcio;


public class ConsorcioJDBCDAO implements ConsorcioDAO 
{
	public void crearConsorcio(Consorcio consorcio) throws AdministracionException 
	{
		Connection c = DBManager.getInstance().conectarse();

		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO edificio (direccion, unidades) VALUES ('" + consorcio.getDireccion()+ "'," + consorcio.getUnidades()+")";
			s.executeUpdate(sql);
			c.commit();
			String sqlSelect = "SELECT max(id) FROM edificio";
			ResultSet rs = s.executeQuery(sqlSelect);

			
			int nEdificio=0;
			
			if(rs.next())
			{
				 nEdificio = rs.getInt(1);
			}
			System.out.println("MAX ID: "+nEdificio);
			
			double m2=0;
			int maxUnidades = consorcio.getUnidades()+1;
			System.out.println("CANT UNIDADES A INSERTAR: "+consorcio.getUnidades());
			
			for(int i=1;i<maxUnidades;i++) 
			{
				m2=Math.random()*140+10;
				String sqlDepto = "INSERT INTO departamento(id_edificio, nUnidad, m2) VALUES("+nEdificio+","+i+","+m2+")";//MIN 10 MAX 150
				s.executeUpdate(sqlDepto);
				c.commit();
			}
			
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
	
	public void borrarConsorcio(Consorcio consorcio) throws AdministracionException {
		String sql = "DELETE FROM edificio WHERE id = " + consorcio.getID();
		String sqlDept = "DELETE FROM departamento WHERE id_edificio="+consorcio.getID();
		String sqlGastos = "DELETE FROM finanzas WHERE id_edificio = "+consorcio.getID();
		Connection c = DBManager.getInstance().conectarse();
		try {
			Statement s = c.createStatement();
			s.executeQuery(sqlGastos);
			s.executeQuery(sqlDept);
			int deletedRows = s.executeUpdate(sql);
			if(deletedRows == 0) {
				throw new AdministracionException("El consorcio que se desea eliminar no existe");
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

	public void actualizarConsorcio(Consorcio consorcio) throws AdministracionException 
	{
		int flagUnidades = 0;
		StringBuffer buffer = new StringBuffer("UPDATE edificio SET ");
		
		boolean separator = false;
		
		if (!consorcio.getDireccion().equals(""))
		{
			buffer.append("direccion= '" + consorcio.getDireccion() + "'");
			separator = true; //si hay mas filtros
		}
		
		if (consorcio.getUnidades() > 0) 
		{
			if (separator)
				buffer.append(", ");
			buffer.append("unidades = " + consorcio.getUnidades());
			flagUnidades = 1;
			separator = true;
		}
		
		buffer.append(" WHERE id =" + consorcio.getID());
		String sql = buffer.toString();
		System.out.println(sql);
		
		Connection c = DBManager.getInstance().conectarse();
		try {
			Statement s = c.createStatement();
			int updateRows=s.executeUpdate(sql);
			
			if (flagUnidades == 1)//SI SE MODIFICA LA CANTIDAD DE UNIDADES, SE BORRAN TODOS LAS UNIDADES DE ESE EDIFICIO, Y SE VUELVEN A CREAR CON 
								  //LA NUEVA CANTIDAD DE UNIDADES.
			{
				String sqlDU="DELETE FROM DEPARTAMENTO WHERE id_edificio = "+consorcio.getID();
				s.executeUpdate(sqlDU);
				for(int i=1;i<consorcio.getUnidades()+1;i++)
				{
					double m2=Math.random()*140+10;
					String sqlIU = "INSERT INTO departamento(id_edificio, nUnidad, m2) VALUES("+consorcio.getID()+","+i+","+m2+")";//MIN 10 MAX 150
					s.executeUpdate(sqlIU);
				}
			}
			if (updateRows == 0)
			{
				throw new AdministracionException("El consorcio que se desea actualizar no existe");
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
	
//	public List<Consorcio> mostrarUnConsorcio(Consorcio consorcio) throws AdministracionException //Imprime un consorcio en particular
//	{
//		String sql = "SELECT * FROM edificio WHERE id = "+consorcio.getID();
//		Connection c = DBManager.getInstance().conectarse();// SINGLETON
//		ArrayList<Consorcio> lista = new ArrayList<Consorcio>();//Instanciamos la lista de tipo Consorcio
//		try 
//		{
//			Statement s = c.createStatement();
//			ResultSet rs = s.executeQuery(sql);
//			if(rs.next())  // CUANDO TENGO MAS DE UN DATO EN BASE, PARA IMPRIMIR VA WHILE.
//			{
//				consorcio.setDireccion(rs.getString("direccion"));//Se carga la direccion del objeto Consorcio
//				consorcio.setUnidades(rs.getInt("unidades"));
//				lista.add(consorcio);//Se agrega el objeto Consorcio a la lista
//			}
//			else 
//			{
//				throw new AdministracionException("El consorcio no existe");
//			}
//		} catch (SQLException e) {
//			try {
//				c.rollback();
//				e.printStackTrace();
//			} catch (SQLException e1) {
//				//no hago nada
//			}
//			throw new AdministracionException("Hubo un error inesperado", e);
//		} finally {
//			try {
//				c.close();
//			} catch (SQLException e1) {
//				//no hago nada
//			}
//		}
//		return lista;
//	}
	
	public List<Consorcio> mostrarConsorcios() throws AdministracionException //imprime todos los consorcios
	{
	    ArrayList<Consorcio> lista = new ArrayList<Consorcio>();//Instanciamos la lista de tipo Consorcio
		String sql = "SELECT * FROM edificio";
		Connection c = DBManager.getInstance().conectarse();// SINGLETON
		
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) 
			{
				Consorcio consorcio = new Consorcio();
				consorcio.setID(rs.getInt("id"));//Se carga el ID del objeto Consorcio
				consorcio.setDireccion(rs.getString("direccion"));//Se carga la direccion del objeto Consorcio
				consorcio.setUnidades(rs.getInt("unidades"));
				lista.add(consorcio);//Se agrega el objeto Consorcio a la lista
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

	public Consorcio validarConsorcio(Consorcio consorcio) throws AdministracionException {

		Consorcio consorcioValidado = new Consorcio();
		Connection c = DBManager.getInstance().conectarse();// SINGLETON
		String sql = "SELECT * FROM edificio WHERE direccion = '"+consorcio.getDireccion()+"'";
		try 
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next())
			{
				consorcioValidado.setID(rs.getInt("id"));
				consorcioValidado.setDireccion(rs.getString("direccion"));
				consorcioValidado.setUnidades(rs.getInt("unidades"));
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
			
		return consorcioValidado;
	}
	
	public Consorcio buscarUnIdConsorcio(Consorcio consorcio) throws AdministracionException {

		Consorcio consorcioValidado = new Consorcio();
		Connection c = DBManager.getInstance().conectarse();// SINGLETON
		String sql = "SELECT * FROM edificio WHERE id = '"+consorcio.getID()+"'";
		try 
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next())
			{
				consorcioValidado.setID(rs.getInt("id"));
				consorcioValidado.setDireccion(rs.getString("direccion"));
				consorcioValidado.setUnidades(rs.getInt("unidades"));
			}
			else
				throw new AdministracionException("El consorcio no existe");
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
			
		return consorcioValidado;
	}
}

