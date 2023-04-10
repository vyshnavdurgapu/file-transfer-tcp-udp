package tcpfiletransfer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class runner implements Runnable
{
	FileOutputStream fout;
	FileInputStream fin;
	InputStream in;
	OutputStream out;
	BufferedReader fnamein;
	String serverdir;
	
	runner(Socket s) throws Exception
	{
		out=s.getOutputStream();
		in=s.getInputStream();
		fnamein  = new BufferedReader(new InputStreamReader(s.getInputStream()));
		serverdir = "C:\\Users\\vyshn\\Downloads\\Lab-2\\Lab-2\\SFTServer\\";
	}
	@Override
	public void run() {
		while(true)
		{
			try {
					String t = fnamein.readLine();
					t.trim();
					System.out.println(t);
					if(t.equals("send"))
					{
						byte b[]=new byte[10000];
						String fname=fnamein.readLine();
						fout=new FileOutputStream(serverdir+fname);
						in.read(b,0,b.length);
						fout.write(b,0,b.length);
						fout.close(); 
					}
					if(t.equals("receive"))
					{	
						byte b[]=new byte[10000];
						String fname=fnamein.readLine();
						System.out.println(fname);
						fin=new FileInputStream(serverdir+fname);
						fin.read(b,0,b.length);
						out.write(b,0,b.length);
					}
				} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

public class server {

	public static void main(String[] args) throws Exception {
		ServerSocket ss=new ServerSocket(9998);
		Socket s;
		s=ss.accept();
		Thread t1=new Thread(new runner(s));
		t1.start();
	}

}
