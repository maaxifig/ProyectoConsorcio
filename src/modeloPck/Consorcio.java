package modeloPck;

public class Consorcio {
	private String direccion;
	private int id, unidades;
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public int getID()
	{
		return id;
	}
	public void setID(int id)
	{
		this.id = id;
	}
	
	public int getUnidades()
	{
		return unidades;
	}
	public void setUnidades(int unidades)
	{
		this.unidades = unidades;
	}
	public String toString() 
	{
		return "Direccion: "+ direccion + "\nID:" + id + "\nUnidades: " + unidades;
	}
}
