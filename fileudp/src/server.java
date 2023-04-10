
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;



class send 
{
	static String remove(String s)
	{
		String t="";
		for(int i=0;i<s.length();i++)
		{
			if((s.charAt(i)>'a')&&(s.charAt(i)<'z')||(s.charAt(i)>'A')&&(s.charAt(i)<'Z')||(s.charAt(i)>='.'))
			{
				t=t+s.charAt(i);
			}
			else break;
		}
		return t;
	}

	FileOutputStream fout;
	FileInputStream fin;
	String dirName = "C:\\Users\\vyshn\\Downloads\\Lab-2\\Lab-2\\SFTServer\\";
	InetAddress inet;
	DatagramSocket ds;
	
	send(DatagramSocket ds)throws Exception
	{
		
		this.ds=ds;
	}
	
	public void run() 
	{
		//fnameout.println(s);
		//fnameout.flush();
		try {
			byte[] b1=new byte[10000];
			DatagramPacket dp2=new DatagramPacket(b1,b1.length);
			ds.receive(dp2);
			String s=new String(dp2.getData());
			//System.out.println(s);
			s=remove(s);
			System.out.println(s);
			fin=new FileInputStream(dirName+s);
			byte b[]=new byte[10000];
			fin.read(b,0,b.length);
			inet=InetAddress.getByName("localhost");
			DatagramPacket dp1=new DatagramPacket(b,b.length,inet,8888);
			ds.send(dp1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
	
}



class receive 
{
	static String remove(String s)
	{
		String t="";
		for(int i=0;i<s.length();i++)
		{
			if((s.charAt(i)>'a')&&(s.charAt(i)<'z')||(s.charAt(i)>'A')&&(s.charAt(i)<'Z')||(s.charAt(i)>='.'))
			{
				t=t+s.charAt(i);
			}
			else break;
		}
		return t;
	}

	FileOutputStream fout;
	FileInputStream fin;
	String dirName = "C:\\Users\\vyshn\\Downloads\\Lab-2\\Lab-2\\SFTServer\\";
	InetAddress inet;
	DatagramSocket ds;
	
	receive(DatagramSocket ds)throws Exception
	{
		this.ds=ds;
	}
	public void run()
	{
		try {
			byte b2[]=new byte[10000];
			DatagramPacket dp3=new DatagramPacket(b2,b2.length);
			ds.receive(dp3);
			String s1=new String(dp3.getData());
			s1=remove(s1);
			fout=new FileOutputStream(dirName+s1);
			byte b1[]=new byte[10000];
			DatagramPacket dp2=new DatagramPacket(b1,b1.length);
			ds.receive(dp2);
			fout.write(b1,0,b1.length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		//System.out.println(s);
}
public class server {
	
	static String remove(String s)
	{
		String t="";
		for(int i=0;i<s.length();i++)
		{
			if((s.charAt(i)>'a')&&(s.charAt(i)<'z')||(s.charAt(i)>'A')&&(s.charAt(i)<'Z')||(s.charAt(i)>='.'))
			{
				t=t+s.charAt(i);
			}
			else break;
		}
		return t;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DatagramSocket ds=new DatagramSocket(9998);
		receive rec=new receive(ds);
		send sen=new send(ds);
		while(true)
		{
			byte b1[]=new byte[100];
			DatagramPacket dp2=new DatagramPacket(b1,b1.length);
			ds.receive(dp2);
			String t=new String(b1);
			t=remove(t);
			if(t.equals("send"))
			{
				rec.run();
			}
			else
			{
				sen.run();
			}
		}
		
	}

}
