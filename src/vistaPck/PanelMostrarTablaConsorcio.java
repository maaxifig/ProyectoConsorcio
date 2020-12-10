package vistaPck;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modeloPck.Consorcio;

public class PanelMostrarTablaConsorcio extends JPanel
{
	 List<Consorcio> Listaconsorcio;

	 public PanelMostrarTablaConsorcio(List<Consorcio> Listaconsorcio) 
    {
        this.Listaconsorcio = Listaconsorcio;
		initUI();
    }
    private void initUI() 
    {
        setLayout(new BorderLayout());
        
        ConsorcioTableModel consTablaModel = new ConsorcioTableModel(Listaconsorcio);//Se instancia el AbstractTableModel
        JTable jTabla = new JTable(consTablaModel);//Se pasa el AbstractTableModel al nuevo objeto JTABLE
        JScrollPane scrollPane = new JScrollPane(jTabla);//Se agrega el JTABLE al SCROLLPANE
        jTabla.setFillsViewportHeight(true);//Para no crear ventana gráfica más grande que el tamaño preferido de la tabla.

        this.add(scrollPane, BorderLayout.CENTER);//Se le asigna una orientacion en el JPANEL
        this.add(scrollPane);//Se agrega el objeto al JPANEL
    }   
}
