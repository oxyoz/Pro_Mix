package oz.code.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test
{

	public static void main(String[] args) throws IOException
	{

		RandomAccessFile aFile;
		try
		{
			aFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\新建文本文档.txt", "rw");

			FileChannel inChannel = aFile.getChannel();

			long size = inChannel.size();

			System.out.println("size = " + size);

			ByteBuffer buf = ByteBuffer.allocate(2048);

			int bytesRead = inChannel.read(buf);

			while (bytesRead != -1)
			{

				System.out.println("Read " + bytesRead);

				buf.flip();

				String data = new String(buf.array(), "utf-8");

				System.out.println(data);

				while (buf.hasRemaining())
				{

					// System.out.println((char) buf.get());

				}

				buf.clear();

				bytesRead = inChannel.read(buf);

			}

			aFile.close();

		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}

	}

}
