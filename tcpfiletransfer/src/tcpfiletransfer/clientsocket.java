package tcpfiletransfer;

import java.io.*;
import java.net.*;

public class clientsocket {
	Socket suck;
	FileOutputStream fout;
	OutputStream out;
	InputStream in;
	FileInputStream fin;
	PrintWriter fnameout ;
	String dirName;
	
	clientsocket() throws UnknownHostException, IOException
	{
		suck = new Socket("localhost",9998);
		out=suck.getOutputStream();
		in=suck.getInputStream();
		fnameout = new PrintWriter(suck.getOutputStream(),true);
        dirName="C:\\Users\\vyshn\\Downloads\\Lab-2\\Lab-2\\SFTClient";
	}

	void send(String s) throws Exception
	{
		fnameout.println("send");
		fnameout.flush();
		fnameout.println(s);
		fnameout.flush();
		
		fin=new FileInputStream(dirName+"\\"+s);
		byte b[]=new byte[10000];
		fin.read(b,0,b.length);
		out.write(b,0,b.length);
		
	}
	void receive(String s) throws Exception
	{
		fnameout.println("receive");
		fnameout.flush();
		fnameout.println(s);
		fnameout.flush();
//		Thread.sleep(100);
		fout=new FileOutputStream(dirName+"\\"+s);
		byte b1[]=new byte[10000];
		in.read(b1,0,b1.length);
		fout.write(b1,0,b1.length);
	}
}
