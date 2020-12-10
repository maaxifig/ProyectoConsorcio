package persistenciaPck;

public class AdministracionException extends Exception 
{

	public AdministracionException() 
	{
	}

	public AdministracionException(String message) 
	{//Recibe un string
		super(message);
	}

	public AdministracionException(Throwable cause) 
	{
		super(cause);
	}

	public AdministracionException(String message, Throwable cause) 
	{
		super(message, cause);
	}

	public AdministracionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) 
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
