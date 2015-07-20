package com.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.codeminders.hidapi.HIDDevice;
import com.codeminders.hidapi.HIDManager;
import com.hidapi.CallbackDeviceChange;
import com.hidapi.DeviceChange;
import com.hidapi.HidClassLoader;

public class main implements DeviceChange{
		
	static
	{
		if( !HidClassLoader.LoadLibrary())
		{
			JOptionPane.showMessageDialog(null,"This OS is not supported");
		}
	}
	
	private CallbackDeviceChange deviceChange =null;
	private HIDManager manager =null;
	public main()
	{
		 try {
			manager =HIDManager.getInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		 deviceChange =  CallbackDeviceChange.getInstance(manager,this);
		 deviceChange.setSerialNumber("MyPCR333333");
		 deviceChange.start();
	}
	
	private boolean statusFlag =true;

	@Override
	public void OnMessage(int MessageType, Object data, int firmwareVersion) {
		
		// TODO Auto-generated method stub
		String count = (String)data;
		
		switch(MessageType)
		{
			case CONNECTED:
				if(count.equals("1")){
				System.out.println("����");
				statusFlag= true;
				}
				break;
			case DISCONNECTED:
				if(statusFlag)
				{
				System.out.println("����ȵ�");
				statusFlag=! statusFlag;
				}
				break;
		}
		
	}	 
	
	
	
	
	public static void main(String[] args) {
		/*main m =new main();// �̷��� �ϸ� �����ڰ� ȣ���
		while(true);
        */
		Action[]action= new Action[20];
		int lines=0;
		String path="test.txt";
		BufferedReader in =null;//���� �о��ִ¿�
		
		try{
			in= new BufferedReader(new FileReader(path));// ������ �о��ִ���  >> ������ �������ִ� 
			//������ �о��ִ� �۾�
			//���پ� �о��ִ� ����
			String line= null;
			ArrayList<String> list =new ArrayList<String>(); // �ִµ��� �ϳ��� �����ϴ°�
			while((line=in.readLine())!=null)
			{
				
				list.add(line);
			}
			in.close();// �ݾ��ֱ�
			
			String first =list.get(0);// �迭 0����
			String last = list.get(list.size()-1);// �迭 �� 0 ���� �ϱ� �������� -1 �̴�.
			
			if(first.contains("%PCR%") && last.contains("%END%"))
			{
				for(int i=1;i<=list.size()-2;++i)
				{
					String[] datas =list.get(i).split("\t");
					action[lines]=new Action(datas[0],datas[1],datas[2]);
					lines++;
				}
					
					//System.out.println(String.format("Label:%s, temp::%s, time:%s",datas[0],datas[1],datas[2]));
				
				System.out.println("�ǹٸ���");
				
				for(int i=0; i< lines;i++)
				{
					System.out.println(String.format("Label:%s, temp::%s, time:%s",
							action[i].label,action[i].temp,action[i].time));
					
					//label�� GOTO���� Ȯ���ϴ� ���
					if(action[i].label.equals("GOTO"))
						System.out.println("GOTO��");
					//�ð����� �ٲٴ� ���
					System.out.println(Integer.parseInt(action[i].time));
				}
				System.out.println(String.format("%02d:%02d",10,10));//�ð��� 2�ڸ��� ����°�
				
				//1.action ������ �̿��Ͽ� ��ü �������� ������ �������� �ð���  ����Ͽ� ���ʷ� ����Ͻÿ�
				//2.action������ �̿��Ͽ� ��ü �������������� ���� ������ label���� �־� ArrayList<String>list2�� �����Ͽ�����ϼ���(GOTO�����ϰ�)
				}else
			{
				System.out.println("��� �ǹٸ��� ���� ������");
			}
			
		}catch(IOException e)
		{
			System.out.println("���� ���");
		}
		}
	}

	
