package vistaPck;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import modeloPck.Gasto;

public class PanelEliminarGasto extends PanelEliminarAbstracto {
	
	private List<Gasto> Listagastos;
	
	public PanelEliminarGasto(Controlador controlador,List<Gasto> Listagastos)
	{
		super(controlador);
		this.Listagastos = Listagastos;
		init();
	}
	
	
	public TableModel getTableModel() {
		 return new GastoTableModel(Listagastos);  // --> Listagastos sera atributo de PanelEliminarGasto pero NO del padre
		}
	
	public void accionEliminar()
	{
		if (esBlanco(txtEliminar.getText()))
		{
			JOptionPane.showMessageDialog(null, "El campo id es obligatorio");
			limpiarCampo();
		}
		else
		{
			Gasto gasto = new Gasto();
			gasto.setIdGasto(Integer.parseInt(txtEliminar.getText()));//Obtiene el string y lo convierte en entero
			controlador.eliminarGasto(gasto);
			limpiarCampo();
		}
	}
}
