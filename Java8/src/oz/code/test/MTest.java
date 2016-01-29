package oz.code.test;

public class MTest
{

	public static void main(String[] args)
	{

		String imei = "111111111111119";

		System.out.println(np(imei));

	}

	
	
	public static String np(String imei)
	{

		char[]charImei = imei.toCharArray();
		
		StringBuffer sb = new StringBuffer();

		int len = imei.length();

		int i = 0;

		while (i <= len / 2)
		{

			int sum = 0;
			
			for (int k = i; k <= len / 2 + i; k++)
			{		
				sum+=Integer.valueOf(String.valueOf(charImei[k]));						
			}
		
			sb.append(sum%10);
			
			i++;
		}
		
		return sb.toString();
		
	}	
	
}
