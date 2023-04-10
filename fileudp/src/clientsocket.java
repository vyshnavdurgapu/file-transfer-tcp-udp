

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;

public class clientsocket {
	String dirName;
	DatagramSocket ds;
	FileOutputStream fout;
	FileInputStream fin;
	InetAddress inet=InetAddress.getByName("localhost");
	clientsocket() throws UnknownHostException, IOException
	{
		ds=new DatagramSocket(8888);
        dirName="C:\\Users\\vyshn\\Downloads\\Lab-2\\Lab-2\\SFTClient";
	}
	void send(String s) throws Exception
	{
		String s1="send";
		byte b1[]=new byte[10000];
		b1=s1.getBytes();
		DatagramPacket dp2=new DatagramPacket(b1,b1.length,inet,9998);
		ds.send(dp2);
		
		byte b2[]=new byte[10000];
		b2=s.getBytes();
		DatagramPacket dp3=new DatagramPacket(b2,b2.length,inet,9998);
		ds.send(dp3);
		
		fin=new FileInputStream(dirName+"\\"+s);
		byte b[]=new byte[10000];
		fin.read(b,0,b.length);
		DatagramPacket dp1=new DatagramPacket(b,b.length,inet,9998);
		ds.send(dp1);
		
	}
	void receive(String s) throws Exception
	{
		String s1="receive";
		byte b1[]=new byte[10000];
		b1=s1.getBytes();
		DatagramPacket dp2=new DatagramPacket(b1,b1.length,inet,9998);
		ds.send(dp2);
		
		byte [] b=new byte[10000];
		b=s.getBytes();
		DatagramPacket dp1=new DatagramPacket(b,b.length,inet,9998);
		ds.send(dp1);
		fout=new FileOutputStream(dirName+"\\"+s);
		//System.out.println(s);
		byte b2[]=new byte[10000];
		DatagramPacket dp3=new DatagramPacket(b2,b2.length);
		ds.receive(dp3);
		fout.write(b2,0,b2.length);
	}
}
