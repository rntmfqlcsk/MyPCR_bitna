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
				System.out.println("연결");
				statusFlag= true;
				}
				break;
			case DISCONNECTED:
				if(statusFlag)
				{
				System.out.println("연결안됨");
				statusFlag=! statusFlag;
				}
				break;
		}
		
	}	 
	
	
	
	
	public static void main(String[] args) {
		/*main m =new main();// 이렇게 하면 생성자가 호출됨
		while(true);
        */
		Action[]action= new Action[20];
		int lines=0;
		String path="test.txt";
		BufferedReader in =null;//파일 읽어주는에
		
		try{
			in= new BufferedReader(new FileReader(path));// 무엇을 읽어주는지  >> 파일을 오픈해주는 
			//살제로 읽어주는 작업
			//한줄씩 읽어주는 거임
			String line= null;
			ArrayList<String> list =new ArrayList<String>(); // 넣는데로 하나씩 증가하는거
			while((line=in.readLine())!=null)
			{
				
				list.add(line);
			}
			in.close();// 닫아주기
			
			String first =list.get(0);// 배열 0부터
			String last = list.get(list.size()-1);// 배열 은 0 부터 니까 마지막은 -1 이다.
			
			if(first.contains("%PCR%") && last.contains("%END%"))
			{
				for(int i=1;i<=list.size()-2;++i)
				{
					String[] datas =list.get(i).split("\t");
					action[lines]=new Action(datas[0],datas[1],datas[2]);
					lines++;
				}
					
					//System.out.println(String.format("Label:%s, temp::%s, time:%s",datas[0],datas[1],datas[2]));
				
				System.out.println("옳바르다");
				
				for(int i=0; i< lines;i++)
				{
					System.out.println(String.format("Label:%s, temp::%s, time:%s",
							action[i].label,action[i].temp,action[i].time));
					
					//label이 GOTO인지 확인하는 방법
					if(action[i].label.equals("GOTO"))
						System.out.println("GOTO일");
					//시간으로 바꾸는 방법
					System.out.println(Integer.parseInt(action[i].time));
				}
				System.out.println(String.format("%02d:%02d",10,10));//시간을 2자리로 만드는거
				
				//1.action 변수를 이용하여 전체 프로토콜 파일의 프로토콜 시간을  계산하여 분초로 출력하시오
				//2.action변수를 이용하여 전체 프로토콜파일의 실행 순서를 label값을 넣어 ArrayList<String>list2에 저장하여출력하세요(GOTO제외하고)
				}else
			{
				System.out.println("노노 옳바르지 않은 파일임");
			}
			
		}catch(IOException e)
		{
			System.out.println("파일 노노");
		}
		}
	}

	
