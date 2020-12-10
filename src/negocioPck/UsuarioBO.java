package negocioPck;

import java.util.List;

import javax.swing.JOptionPane;

import modeloPck.Usuario;
import persistenciaPck.AdministracionException;
import persistenciaPck.UsuarioDAO;

public class UsuarioBO {

	private UsuarioDAO usuarioDAO;
	
	
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) 
	{
		this.usuarioDAO = usuarioDAO;
	}
	
	public void altaUsuarioBO(Usuario usuario) throws AdministracionException 
	{
			Usuario usuarioValidado = usuarioDAO.validarUsuario(usuario);
			if(usuarioValidado.getUsuario()==null)
			{
				usuarioDAO.crearUsuario(usuario);
				mostrarMensaje("Usuario Agregado");
			}
			else
				throw new AdministracionException("El usuario ya existe");
	}
	
	public void eliminarUsuarioBO(Usuario usuario) throws AdministracionException
	{
		usuarioDAO.borrarUsuario(usuario);
	}

	public void modificarUsuarioBO(Usuario usuario, Usuario usuarioAModif) throws AdministracionException
	{
		Usuario usuarioValidado = usuarioDAO.validarUsuario(usuario);
		if(usuarioValidado.getUsuario()==null)
			usuarioDAO.actualizarUsuario(usuario, usuarioAModif);
		else
			throw new AdministracionException("El usuario que quiere ingresar ya existe");
	}
	
	public List<Usuario> mostrarUsuariosBO() throws AdministracionException
	{
		return usuarioDAO.mostrarUsuarios();
	}
	
	public void buscarMatchBO(Usuario usuario)throws AdministracionException
	{
		Usuario usuarioValidado = usuarioDAO.buscarMatch(usuario);
		if(usuarioValidado.getUsuario()==null)
			throw new AdministracionException("Usuario o password incorrecto");
			
	}
	
	public void mostrarMensaje(String mensaje)
	{
		JOptionPane.showMessageDialog(null, mensaje);
	}
}
