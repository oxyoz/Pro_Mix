package oz.code.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class SocketTest
{

	public static void main(String[] args)
	{
		Socket socket = null;

		PrintWriter pw = null;

		BufferedInputStream bis = null;

		try
		{
			socket = new Socket("192.168.1.118", 8920);

			pw = new PrintWriter(socket.getOutputStream(), true);

			bis = new BufferedInputStream(socket.getInputStream());

			while (true)
			{
				if (bis.available() != 0)
				{
					
					byte[] buff = new byte[bis.available()];
			
					bis.read(buff);
					
					System.out.printf("C:s%\n", new String(buff,"utf-8"));
				
				}

			}

		}
		catch(UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{

			try
			{

				if (pw != null) pw.close();
				
				if (bis != null) bis.close();
				
				if (socket != null) socket.close();

				pw = null;
				
				bis = null;
				
				socket = null;
				
			}
			catch(IOException e)
			{

				e.printStackTrace();
			}

		}

	}

}
