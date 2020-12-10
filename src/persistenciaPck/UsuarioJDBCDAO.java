package persistenciaPck;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modeloPck.Consorcio;
import modeloPck.Usuario;

public class UsuarioJDBCDAO implements UsuarioDAO 
{
	public void crearUsuario(Usuario usuario) throws AdministracionException 
	{
		Connection c = DBManager.getInstance().conectarse();

		try {
			
			Statement s = c.createStatement();
			String sql = "INSERT INTO usuarios (usuario, pass, permiso) VALUES ('" + usuario.getUsuario()+ "','" + usuario.getPass()+"', "+ usuario.getPermiso()+")";
			
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
	
	public void borrarUsuario(Usuario usuario) throws AdministracionException {
		String sql = "DELETE FROM usuarios WHERE usuario = '" + usuario.getUsuario()+"'";
		Connection c = DBManager.getInstance().conectarse();
		try {
			Statement s = c.createStatement();
			int deletedRows = s.executeUpdate(sql);
			if(deletedRows == 0) {
				throw new AdministracionException("El usuario que se desea eliminar no existe");
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

	public void actualizarUsuario(Usuario usuario, Usuario usuarioAModif) throws AdministracionException 
	{
		
		StringBuffer buffer = new StringBuffer("UPDATE usuarios SET ");
		
		boolean separator = false;
		
		if (!usuario.getUsuario().equals(""))
		{
			buffer.append("usuario= '" + usuario.getUsuario() + "'");
			separator = true; //si hay mas filtros
		}
		
		if (!usuario.getPass().equals("")) 
		{
			if (separator)
				buffer.append(", ");
			buffer.append("pass = '" + usuario.getPass()+"'");
			separator = true;
		}
		
		if (usuario.getPermiso() > 0) 
		{
			if (separator)
				buffer.append(", ");
			buffer.append("permiso = " + usuario.getPermiso());
			separator = true;
		}
		buffer.append(" WHERE usuario ='" + usuarioAModif.getUsuario()+"'");
		String sql = buffer.toString();
		System.out.println(sql);
		
		Connection c = DBManager.getInstance().conectarse();
		
		System.out.println(usuario.getUsuario());
		try {
			Statement s = c.createStatement();
			int updateRows=s.executeUpdate(sql);
			if (updateRows == 0)
			{
				throw new AdministracionException("El usuario que se desea actualizar no existe");
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
	
	
	public List<Usuario> mostrarUsuarios() throws AdministracionException //imprime todos los usuarios
	{
	   ArrayList<Usuario> lista = new ArrayList<Usuario>();//Instanciamos la lista de tipo Usuario
		String sql = "SELECT * FROM usuarios";
		Connection c = DBManager.getInstance().conectarse();// SINGLETON
		
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) 
			{
				Usuario usuario = new Usuario();
				usuario.setUsuario(rs.getString("usuario"));//Se carga el ID del objeto usuario
				usuario.setPass(rs.getString("pass"));//Se carga el nombre de usuario del objeto usuario
				usuario.setPermiso(rs.getInt("permiso"));
				lista.add(usuario);//Se agrega el objeto usuario a la lista
			}
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
		return lista;//Se devuelve la lista cargada
	}
	
	public Usuario buscarMatch(Usuario usuario) throws AdministracionException //Imprime un consorcio en particular
	{
		Usuario usuarioValidado = new Usuario();
		String sql = "SELECT * FROM usuarios WHERE usuario = '"+usuario.getUsuario()+"' AND pass = '"+usuario.getPass()+"'";
		Connection c = DBManager.getInstance().conectarse();// SINGLETON
		
		try 
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			if(rs.next())
			{
				usuarioValidado.setUsuario(rs.getString("usuario"));
				usuarioValidado.setPass(rs.getString("pass"));
			}
			
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
		
		return usuarioValidado;
}

	@Override
	public Usuario validarUsuario(Usuario usuario) throws AdministracionException {
		
		Usuario usuarioValidado = new Usuario();
		Connection c = DBManager.getInstance().conectarse();// SINGLETON
		String sql = "SELECT * FROM usuarios WHERE usuario = '"+usuario.getUsuario()+"'";
		try 
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next())
			{
				usuarioValidado.setUsuario(rs.getString("usuario"));
				
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
		
		return usuarioValidado;
	}
}
