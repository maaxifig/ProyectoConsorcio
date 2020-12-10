package modeloPck;

public class Departamento {
	private int id_edificio;
	private int nUnidad;
	private double m2;
	
	public int getID() {
		return id_edificio;
	}
	public void setID(int id_edificio) {
		this.id_edificio = id_edificio;
	}
	
	public int getnUnidad()
	{
		return nUnidad;
	}
	public void setnUnidad(int nUnidad)
	{
		this.nUnidad = nUnidad;
	}
	
	public double getm2()
	{
		return m2;
	}
	public void setM2(int m2)
	{
		this.m2 = m2;
	}
	public String toString() 
	{
		return "idEdificio: "+ id_edificio + "\n nUnidades:" + nUnidad + "\nm2: " + m2;
	}
}
