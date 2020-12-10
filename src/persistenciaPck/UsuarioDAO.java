package persistenciaPck;

import java.util.List;

import modeloPck.Usuario;

public interface UsuarioDAO 
{
	void crearUsuario(Usuario usuario) throws AdministracionException;// CREA EL USUARIO
	
	void borrarUsuario(Usuario usuario) throws AdministracionException;// ELIMINA UN USUARIO
	
	void actualizarUsuario(Usuario usuario, Usuario usuarioAModif) throws AdministracionException;//ACTUALIZA UN USUARIO
	
	List<Usuario> mostrarUsuarios() throws AdministracionException;//MUESTRA TODOS LOS USUARIOS
	
	Usuario buscarMatch(Usuario usuario) throws AdministracionException;
	
	Usuario validarUsuario(Usuario usuario) throws AdministracionException;
}
