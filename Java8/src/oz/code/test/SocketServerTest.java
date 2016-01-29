package oz.code.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class SocketServerTest
{

	public static void main(String[] args)
	{
		ServerSocket ss = null;

		PrintWriter pw = null;

		try
		{
			ss = new ServerSocket(8920);

			pw = new PrintWriter(ss.accept().getOutputStream(), true);

			while(true)
			{
		
				pw.println("服务器正在开启……");
				
				System.out.println("服务器正在开启……");
				
			}		
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{

			try
			{
				if (ss != null) ss.close();

				if (pw != null) pw.close();

				ss = null;
				
				pw = null;			
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}

		}

	}

}
