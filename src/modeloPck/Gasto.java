package modeloPck;


public class Gasto {
	private int id_edificio, id_gasto, mes, a�o;
	private float gasto_ord, gasto_ext;
	
	public int getIdEdif() {
		return id_edificio;
	}
	public void setIdEdificio(int id_edificio) {
		this.id_edificio = id_edificio;
	}
	
	public int getIdGasto() {
		return id_gasto;
	}
	public void setIdGasto(int id_gasto) {
		this.id_gasto = id_gasto;
	}
	
	public int getMes()
	{
		return mes;
	}
	public void setMes(int mes)
	{
		this.mes = mes;
	}
	
	public int getA�o()
	{
		return a�o;
	}
	public void setA�o(int a�o)
	{
		this.a�o = a�o;
	}
	
	public float getGastoOrd()
	{
		return gasto_ord;
	}
	public void setGastoOrd(float gasto_ord)
	{
		this.gasto_ord = gasto_ord;
	}
	
	public float getGastoExt()
	{
		return gasto_ext;
	}
	
	public void setGastoExt(float gasto_ext)
	{
		this.gasto_ext = gasto_ext;
	}
	
	
	public String toString() 
	{
		return "ID EDIFICIO: "+ id_edificio + "\nID gasto:" + id_gasto + "\nA�o:" + a�o + "\nMes:" + mes + "\nGasto Ord: " + gasto_ord + "\nGasto Extra: " + gasto_ext;                
	}
}
