package vistaPck;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modeloPck.Consorcio;
import modeloPck.Expensa;
import modeloPck.Gasto;
import negocioPck.ConsorcioBO;
import negocioPck.GastoBO;
import negocioPck.UsuarioBO;
import persistenciaPck.AdministracionException;
import persistenciaPck.ConsorcioJDBCDAO;
import persistenciaPck.GastoJDBCDAO;
import modeloPck.Usuario;
import persistenciaPck.UsuarioJDBCDAO;

public class Controlador 
{
	private GralFrame frame;
	private LoginFrame LoginFrame;
	private ConsorcioBO consorcioBO;
	private UsuarioBO usuarioBO;
	private GastoBO gastoBO;
	
	public Controlador() 
	{
		consorcioBO = new ConsorcioBO();
		consorcioBO.setConsorcioDAO(new ConsorcioJDBCDAO());
		
		usuarioBO = new UsuarioBO();
		usuarioBO.setUsuarioDAO(new UsuarioJDBCDAO());
		
		gastoBO = new GastoBO();
		gastoBO.setGastoDAO(new GastoJDBCDAO());
	}
	
	public void MostrarFrame()
	{
		LoginFrame.dispose();
		frame = new GralFrame(this);
		frame.setVisible(true);
	}
	
	public void MostrarFrameLogin()
	{
		LoginFrame = new LoginFrame(this);
		LoginFrame.setVisible(true);
	}
	
	public void CerrarSesion()
	{
		frame.dispose();
		LoginFrame = new LoginFrame(this);
		LoginFrame.setVisible(true);
				
	}
	
	public PanelLogin llamarPanelLogin() 
	{
		PanelLogin panel = new PanelLogin(this);
		return panel;
	}
	//***********************************  FUNCIONES LLAMADAS DESDE MiFrame **********************************************
	//***********************************  CONSORCIO *********************************************************************

	public void llamarPanelAgregarConsorcio() 
	{
		List<Consorcio> Listaconsorcio = new ArrayList<Consorcio>();
		Listaconsorcio = mostrarConsorcios();
		frame.cambiarPanel(new PanelAgregarConsorcio(this, Listaconsorcio));
	}
	
	public void llamarPanelEliminarConsorcio() 
	{
		List<Consorcio> Listaconsorcio = new ArrayList<Consorcio>();
		Listaconsorcio = mostrarConsorcios();
		frame.cambiarPanel(new PanelEliminarConsorcio(this, Listaconsorcio));
	}
	
	public void llamarPanelActualizarConsorcio()
	{
		List<Consorcio> Listaconsorcio = new ArrayList<Consorcio>();
		Listaconsorcio=mostrarConsorcios();
		frame.cambiarPanel(new PanelActualizarConsorcio(this, Listaconsorcio));
	}
	
	public void llamarPanelBuscarConsorcio()
	{
		List<Consorcio> Listaconsorcio = new ArrayList<Consorcio>();
		Listaconsorcio=mostrarConsorcios();
		frame.cambiarPanel(new PanelBuscarConsorcio(this, Listaconsorcio));
	}
	
	public void mostrarTablaConsorcio()
	{
		List<Consorcio> Listaconsorcio = new ArrayList<Consorcio>();
		Listaconsorcio=mostrarConsorcios();
		frame.cambiarPanel(new PanelMostrarTablaConsorcio(Listaconsorcio));//Aca estamos pasando un controlador, y panelMostrarTabla recibe una lista.
	}
	
	//*******************************************   USUARIO   **********************************************************
	public void llamarPanelAgregarUsuario() 
	{
		List<Usuario> Listausuario = new ArrayList<Usuario>();
		Listausuario=mostrarUsuarios();
		frame.cambiarPanel(new PanelAgregarUsuario(this, Listausuario));
	}
	
	public void llamarPanelEliminarUsuario() 
	{
		List<Usuario> Listausuario = new ArrayList<Usuario>();
		Listausuario=mostrarUsuarios();
		frame.cambiarPanel(new PanelEliminarUsuario(this,Listausuario));
	}
	
	public void llamarPanelActualizarUsuario()
	{
		List<Usuario> Listausuario = new ArrayList<Usuario>();
		Listausuario=mostrarUsuarios();
		frame.cambiarPanel(new PanelActualizarUsuario(this, Listausuario));
	}
	
	public void mostrarTablaUsuarios()
	{
		List<Usuario> Listausuario = new ArrayList<Usuario>();
		frame.cambiarPanel(new PanelMostrarTablaUsuario(Listausuario));//Aca estamos pasando un controlador, y panelMostrarTabla recibe una lista.
	}
	
	
	//****************************FINANZAS***********************************
	public void llamarPanelCargarGasto() {
		frame.cambiarPanel(new PanelCargarGasto(this));
	}
	
	public void llamarPanelEliminarGasto() {
		List<Gasto> Listagastos = new ArrayList<Gasto>();
		Listagastos=mostrarGastos();
		frame.cambiarPanel(new PanelEliminarGasto(this,Listagastos));//ACA PASARLE LA LISTA DE GASTOS PARA VER EN TIEMPO REAL CUAL QUIERO ELIMINAR.
	}
	
	public void llamarPanelModificarGasto() 
	{
		List<Gasto> Listagastos = new ArrayList<Gasto>();
		Listagastos=mostrarGastos();
		frame.cambiarPanel(new PanelModificarGasto(this,Listagastos));
	}
	
	public void llamarPanelBuscarGastos()
	{
		List<Gasto> Listagastos = new ArrayList<Gasto>();
		Listagastos=mostrarGastos();
		frame.cambiarPanel(new PanelBuscarGastos(this, Listagastos));
	}
	
	public void mostrarTablaGasto() 
	{
		List<Gasto> Listagastos = new ArrayList<Gasto>();
		Listagastos=mostrarGastos();
		frame.cambiarPanel(new PanelMostrarTablaGasto(Listagastos));
	}
	
	public void llamarPanelGastoDepto()
	{
		frame.cambiarPanel(new PanelGastosDepto(this));
		
	}
	
	
	//*******************  FUNCIONES LLAMADAS DESDE LA INTERFACE CONSORCIODAO *****************************************
	
	//	CREA EL CONSORCIO ESPECIFICADO--------------------------------------------------------------------------
	public void crearConsorcio(Consorcio consorcio) 
	{
		List<Consorcio> Listaconsorcio = new ArrayList<Consorcio>();// LOCAL
		
		try 
		{
			consorcioBO.altaConsorcioBO(consorcio);
			Listaconsorcio = mostrarConsorcios();
			frame.cambiarPanel(new PanelAgregarConsorcio(this, Listaconsorcio));//Se vuelve a llamar al panel agregar con la lista cargada.
		} catch (AdministracionException e) {
			mostrarFail(e);
		}
	} 
	//	ELIMINA EL CONSORCIO  ESPECIFICADO--------------------------------------------------------------------------
	public void eliminarConsorcio(Consorcio consorcio)
	{
		List<Consorcio> Listaconsorcio = new ArrayList<Consorcio>();
		try 
		{
			consorcioBO.eliminarConsorcioBO(consorcio);
			Listaconsorcio= mostrarConsorcios();
			mostrarSucces("Consorcio eliminado");
			frame.cambiarPanel(new PanelEliminarConsorcio(this, Listaconsorcio));			
		}catch (AdministracionException e) {
			mostrarFail(e);
		}
	}
	//	ACTUALIZA EL CONSORCIO ESPECIFICADO--------------------------------------------------------------------------
	public void actualizarConsorcio(Consorcio consorcio)
	{
		List<Consorcio> Listaconsorcio = new ArrayList<Consorcio>();
		try 
		{
			if (consorcio.getUnidades() >= 0)
			{
				consorcioBO.modificarConsorcioBO(consorcio);
				Listaconsorcio=mostrarConsorcios();
				mostrarSucces("Consorcio actualizado");
				frame.cambiarPanel(new PanelActualizarConsorcio(this, Listaconsorcio));
			}
		}catch(AdministracionException e)
		{
			mostrarFail(e);
		}
	}
	//	MUESTRA EL CONSORCIO BUSCADO--------------------------------------------------------------------------
	public void mostrarConsorcio(Consorcio consorcio)
	{
		List<Consorcio> Listaconsorcio = new ArrayList<Consorcio>();
		try 
		{
			Listaconsorcio=consorcioBO.mostrarUnoBO(consorcio);//Aca tengo el consorcio consultado
			frame.cambiarPanel(new PanelBuscarConsorcio(this, Listaconsorcio));
		} catch (AdministracionException e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	//   MUESTRA TODOS LOS CONSORCIOS--------------------------------------------------------------------------
	public List<Consorcio> mostrarConsorcios()
	{	 
		List<Consorcio> Listaconsorcio = new ArrayList<Consorcio>();
		try 
		{
			Listaconsorcio = consorcioBO.mostrarConsorciosBO();
			
		}catch(AdministracionException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return Listaconsorcio;
	}
	
	//***********************************  USUARIO *********************************************************************
//	CREA EL USUARIO ESPECIFICADO--------------------------------------------------------------------------
	public void crearUsuario(Usuario usuario) 
	{
		List<Usuario> Listausuario = new ArrayList<Usuario>();
		try 
		{
			usuarioBO.altaUsuarioBO(usuario);
			Listausuario=mostrarUsuarios();
			frame.cambiarPanel(new PanelAgregarUsuario(this, Listausuario));//Se vuelve a llamar al panel agregar con la lista cargada.
		} catch (AdministracionException e) {
			mostrarFail(e);
		}
	}
		
	public void eliminarUsuario(Usuario usuario)
	{
		List<Usuario> Listausuario = new ArrayList<Usuario>();
		try 
		{
			usuarioBO.eliminarUsuarioBO(usuario);
			Listausuario=mostrarUsuarios();
			frame.cambiarPanel(new PanelEliminarUsuario(this, Listausuario));
			mostrarSucces("Usuario eliminado");
		}catch (AdministracionException e) {
			mostrarFail(e);
		}
	}

//	ACTUALIZA EL USUARIO ESPECIFICADO--------------------------------------------------------------------------
	public void actualizarUsuario(Usuario usuario, Usuario usuarioAModif)
	{
		List<Usuario> Listausuario = new ArrayList<Usuario>();
		try 
		{
			usuarioBO.modificarUsuarioBO(usuario, usuarioAModif);
			Listausuario=mostrarUsuarios();//Refresca la lista una vez modificado el dato
			frame.cambiarPanel(new PanelActualizarUsuario(this, Listausuario));
			mostrarSucces("Usuario actualizado");
		
		}catch(AdministracionException e)
		{
			mostrarFail(e);
		}
	}
	
	public List<Usuario> mostrarUsuarios()
	{	
		List<Usuario> Listausuario = new ArrayList<Usuario>();
		try 
		{
			Listausuario = usuarioBO.mostrarUsuariosBO();
		}catch(AdministracionException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return Listausuario;
	}
	

	public boolean buscarMatch(Usuario usuario)
	{
		boolean autenticacion = false;
		try 
		{
//			autenticacion = usuarioBO.buscarMatchBO(usuario);
			usuarioBO.buscarMatchBO(usuario);
			autenticacion = true;
		}catch(AdministracionException e)
		{
			mostrarFail(e);
		}
		return autenticacion;
	}

//	************************************FINANZAS****************************************
	public void crearGasto(Gasto gasto)
	{
		try 
		{
			gastoBO.altaGastoBO(gasto);
			
		} catch (AdministracionException e) {
			mostrarFail(e);
		}
	}
	
	public void eliminarGasto(Gasto gasto)
	{
		List<Gasto> Listagastos = new ArrayList<Gasto>();
		try 
		{
			gastoBO.eliminarGastoBO(gasto);
			Listagastos=mostrarGastos();//Refresca la lista una vez modificado el dato
			frame.cambiarPanel(new PanelEliminarGasto(this,Listagastos));
			
		}catch (AdministracionException e) {
			mostrarFail(e);
		}
	}
	
	public void modificarGasto(Gasto gasto)
	{
		List<Gasto> Listagastos = new ArrayList<Gasto>();
		
		try 
		{
			gastoBO.modificarGastoBO(gasto);
			Listagastos=mostrarGastos();//Refresca la lista una vez modificado el dato
			frame.cambiarPanel(new PanelModificarGasto(this, Listagastos));
			mostrarSucces("Gasto modificado");
			
		}catch (AdministracionException e) {
			mostrarFail(e);
		}
	}
	
	public void mostrarGastosFiltrado(Gasto gasto)
	{
		List<Gasto> Listagastos = new ArrayList<Gasto>();
		try 
		{
			Listagastos=gastoBO.mostrarGastosFiltradosBO(gasto);//Aca tengo el consorcio consultado
			frame.cambiarPanel(new PanelBuscarGastos(this, Listagastos));
		} catch (AdministracionException e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public List<Gasto> mostrarGastos()
	{	List<Gasto> Listagastos = new ArrayList<Gasto>();
		try 
		{
			Listagastos = gastoBO.mostrarGastosBO();
			
		}catch(AdministracionException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return Listagastos;
	}
	
	public void mostrarExpensas(Expensa expensa)
	{
		List<Expensa> Listaexpensa = new ArrayList<Expensa>();
		try
		{
			Listaexpensa = gastoBO.mostrarExpensasBO(expensa);
			frame.cambiarPanel(new PanelMostrarTablaExpensa(Listaexpensa));
		}catch(AdministracionException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	
	public void mostrarSucces(String mensaje)
	{
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
	public void mostrarFail(AdministracionException e)
	{
		JOptionPane.showMessageDialog(null, e.getMessage());
		e.printStackTrace();
	}



}
