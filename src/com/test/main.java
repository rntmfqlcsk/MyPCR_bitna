package com.test;

import java.io.IOException;
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
				if(count.equals("0"))
				System.out.println("����ȵ�");
				statusFlag=! statusFlag;
				break;
		}
		
	}	 
	
	
	
	
	public static void main(String[] args) {
		main m =new main();// �̷��� �ϸ� �����ڰ� ȣ���
		while(true);
        
	}
}
	
