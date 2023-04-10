

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

class fx extends JFrame implements ActionListener
{
	JLabel heading;
	JPanel panel;
	
	JComboBox view ;
	clientsocket c;
	
	JTextField text;
	
	JButton send;
	JButton get;
	String dirName;
	

	fx() throws Exception
	{
		c=new clientsocket();
		this.setLayout(null);
        this.setTitle("SHARE");
        this.setBounds(700,300,600,200);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        heading=new JLabel("SHARE");
        heading.setBounds(0,0,600,30);
        this.add(heading);
        heading.setFont(new Font(null,Font.BOLD,25));
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setBackground(new Color(120,100,50));
        heading.setOpaque(true);
        
        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(100,100,200));
        this.add(panel);
        panel.setBounds(0,30,600,140);
        
        dirName="C:\\Users\\vyshn\\Downloads\\Lab-2\\Lab-2\\SFTClient\\";
        File ff = new File(dirName);
        ArrayList<String> files=new ArrayList<String>(Arrays.asList(ff.list()));
        String names[]=new String[files.size()];
        
        int i=0;
        for(String file:files)
        {
        	names[i]=file;
        	i++;
        }
        view = new JComboBox(names);
        panel.add(view);
        view.setBounds(100,20,200,50);
        
        send = new JButton("SEND");
        send.setBounds(350,30,100,30);
        panel.add(send);
        send.addActionListener(this);
        
        text = new JTextField();
        text.setBounds(100,90,200,20);
        panel.add(text);
        
        get = new JButton("GET");
        get.setBounds(350,85,100,30);
        panel.add(get);
        get.addActionListener(this);
        get.setFocusable(false);  
        this.setVisible(true); 
        
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==send)
		{
			String s=(String)view.getSelectedItem();
			try {
				c.send(s);
				//System.out.println(dirName+"\\"+s);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==get)
		{
			String s1 = text.getText();
			try {
				c.receive(s1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			render();
		}
			
		
	}
	void render()
	{
		this.setVisible(false);
		panel.remove(view);
		dirName="C:\\Users\\vyshn\\Downloads\\Lab-2\\Lab-2\\SFTClient\\";
        File ff = new File(dirName);
        ArrayList<String> files=new ArrayList<String>(Arrays.asList(ff.list()));
        String names[]=new String[files.size()];
        
        int i=0;
        for(String file:files)
        {
        	names[i]=file;
        	i++;
        }
        view = new JComboBox(names);
        panel.add(view);
        view.setBounds(100,20,200,50);
        this.setVisible(true);
	}
}

public class client {

	public static void main(String[] args) {	
		try {
			new fx();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
