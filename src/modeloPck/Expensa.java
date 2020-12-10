package modeloPck;

public class Expensa {

	private int id_edificio, mes, año, nUnidad;
	private float expensa_ext, expensa_ord;
	private double m2;
	
	public int getIdEdif() {
		return id_edificio;
	}
	public void setIdEdificio(int id_edificio) {
		this.id_edificio = id_edificio;
	}
	
	public int getMes()
	{
		return mes;
	}
	public void setMes(int mes)
	{
		this.mes = mes;
	}
	
	public int getAño()
	{
		return año;
	}
	public void setAño(int año)
	{
		this.año = año;
	}
	
	public float getExpensaOrd()
	{
		return expensa_ord;
	}
	public void setExpensaOrd(float expensa_ord)
	{
		this.expensa_ord = expensa_ord;
	}
	
	public float getExpensaExt()
	{
		return expensa_ext;
	}
	
	public void setExpensaExt(float expensa_ext)
	{
		this.expensa_ext = expensa_ext;
	}
	
	public double getm2()
	{
		return m2;
	}
	
	public void setM2(Double m2)
	{
		this.m2 = m2;
	}
	
	public int getnUnidad()
	{
		return nUnidad;
	}
	public void setnUnidad(int nUnidad)
	{
		this.nUnidad = nUnidad;
	}
	
	public String toString() 
	{
		return "ID EDIFICIO: "+ id_edificio + "\nUnidad: "+ nUnidad + "\nm2: "+ m2 + "\nAño:" + año + "\nMes:" + mes + "\nExp Ord: " + expensa_ord + "\nExp Extra: " + expensa_ext+"\n\n\n";                
	}
	
	
}
