package vistaPck;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import persistenciaPck.AdministracionException;
import persistenciaPck.UsuarioJDBCDAO;

public class GralFrame extends JFrame 
{
	private Controlador controlador;
	
	public GralFrame(Controlador controlador) 
	{
		super();
		this.controlador = controlador;
		initUI();
	}
	public void initUI() 
	{
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuBar();

		
	}
	
	public void cambiarPanel(JPanel panel)
	{
		getContentPane().removeAll();
		getContentPane().add(panel);
		getContentPane().validate();
	}
	
	private void menuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		
		JMenu JmenuConsorcio = new JMenu("Administrar");
		JMenuItem JMIagregarConsorcio = new JMenuItem("Agregar Consorcio");//Item del menu "Agregar Consorcio"
		JMenuItem JMIeliminarConsorcio = new JMenuItem("Eliminar Consorcio");//Item eliminar
		JMenuItem JMIactualizarConsorcio = new JMenuItem("Actualizar un consorcio");//item actualizar
		JMenuItem JMImostrarConsorcio = new JMenuItem("Mostrar un consorcio");//item mostrar uno
		JMenuItem JMImostrarTodos = new JMenuItem("Mostrar todos los consorcios");//item mostrar uno

		JMIagregarConsorcio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae)
			{
				controlador.llamarPanelAgregarConsorcio();
			}	
		});
		
			JMIeliminarConsorcio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae)
				{
					
					controlador.llamarPanelEliminarConsorcio();
				}
			});
			
			JMIactualizarConsorcio.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					controlador.llamarPanelActualizarConsorcio();
				}
			});
			
			JMImostrarConsorcio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) 
				{
					
					controlador.llamarPanelBuscarConsorcio();
				}
			});
			
			JMImostrarTodos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) 
				{
					controlador.mostrarTablaConsorcio();
				}
			});
		
		JmenuConsorcio.add(JMIagregarConsorcio);
		JmenuConsorcio.add(JMIeliminarConsorcio);
		JmenuConsorcio.add(JMIactualizarConsorcio);
		JmenuConsorcio.add(JMImostrarConsorcio);
		JmenuConsorcio.add(JMImostrarTodos);
		menuBar.add(JmenuConsorcio);
		
		//*********************************USUARIO*****************************************
		
		JMenu JmenuUsuario = new JMenu("Usuarios");
		JMenuItem JMIagregarUsuario = new JMenuItem("Agregar Usuario");//Item del menu "Agregar Ususario"
		JMenuItem JMIEliminarUsuario = new JMenuItem("Eliminar Usuario");//Item del menu "Agregar Ususario"
		JMenuItem JMIActualizarUsuario = new JMenuItem("Actualizar Usuario");
		
		JMIagregarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae)
			{
				controlador.llamarPanelAgregarUsuario();
			}	
		});
		
		JMIEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae)
			{
				controlador.llamarPanelEliminarUsuario();
			}	
		});
		
		JMIActualizarUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				controlador.llamarPanelActualizarUsuario();
			}
		});
		
		
		JmenuUsuario.add(JMIagregarUsuario);
		JmenuUsuario.add(JMIEliminarUsuario);
		JmenuUsuario.add(JMIActualizarUsuario);

		menuBar.add(JmenuUsuario);
		setJMenuBar(menuBar);
//		****************************************************FINANZAS***************************************

		
		JMenu JmenuFinanzas = new JMenu("Gastos");
		JMenuItem JMIAgregarGasto = new JMenuItem("Cargar gasto");
		JMenuItem JMIEliminarGasto = new JMenuItem("Eliminar gasto");
		JMenuItem JMIModificarGasto = new JMenuItem("Modificar gasto");
		JMenuItem JMIMostrarGastosFiltrado = new JMenuItem("Mostrar gastos por edificio");
		JMenuItem JMIMostrarGastoPorDepartamento = new JMenuItem("Mostrar expensas de cada departamento");
		
		JMIAgregarGasto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae)
			{
				controlador.llamarPanelCargarGasto();
			}	
		});
		
		JMIEliminarGasto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae)
			{
				controlador.llamarPanelEliminarGasto();
			}
		});
		
		JMIModificarGasto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae)
			{
				controlador.llamarPanelModificarGasto();
			}	
		});
		
		JMIMostrarGastosFiltrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae)
			{
				controlador.llamarPanelBuscarGastos();
			}
		});
		
		
		JMIMostrarGastoPorDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae)
			{
				controlador.llamarPanelGastoDepto();
			}
		});
		
		JmenuFinanzas.add(JMIAgregarGasto);
		JmenuFinanzas.add(JMIEliminarGasto);
		JmenuFinanzas.add(JMIModificarGasto);
		JmenuFinanzas.add(JMIMostrarGastosFiltrado);
		JmenuFinanzas.add(JMIMostrarGastoPorDepartamento);
		menuBar.add(JmenuFinanzas);

		
		JMenu JmenuSalir = new JMenu("Salir");
		JMenuItem JMICerrar = new JMenuItem("Cerrar sesion");
		JmenuSalir.add(JMICerrar);
		menuBar.add(JmenuSalir);
		
		JMICerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae)
			{
				
				controlador.CerrarSesion();
			}
		});
	}
}