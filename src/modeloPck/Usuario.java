package modeloPck;

public class Usuario {

	private String usuario;
	private String pass;
	private int permiso;
	
	public String getUsuario() 
	{
		return usuario;
	}
	public void setUsuario(String usuario) 
	{
		this.usuario = usuario;
	}
	
	public String getPass()
	{
		return pass;
	}
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	
	public int getPermiso()
	{
		return permiso;
	}
	
	public void setPermiso(int permiso)
	{
		this.permiso=permiso;
	}
	
	public String toString() 
	{
		return "\nUsuario: "+ usuario + "\nPass:" + pass + "\nPermiso: "+ permiso;
	}


}
