package com.test;

import java.util.Arrays;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		/*int a=10;
		int sum=0;
		
		// sum = sum + i
		// +=
		for(int i=1; i<=a;sum += i, ++i)
		;
		
		int i=0;
		
		while(i <=a )
		{ 
			 sum += i;
			 ++i;
		}
		
		System.out.println(sum);
	}
		
		int[] arr= new int[10];
		arr[0]=4;
		arr[1]=6;
		arr[2]=9;
		arr[3]=3;
		arr[4]=1;
		arr[5]=2;
		arr[6]=5;
		arr[7]=15;
		arr[8]=22;
		arr[9]=8;
		
		Arrays.sort(arr);
        
		for (int i : arr )
	
			System.out.println(i);
		
		*/
		
		
		/*int a = 50;
        
		for(int i=1; i<=a; ++i)
		{
			if( i%3 ==0 )
			System.out.println(i);
		}*/
		
		Scanner s= new Scanner( System.in);
	
		/*
		3
		0
		1
		4
		 */
        int t= s.nextInt();
        
        for(int i=0; i<t; ++i)
        {
        	int a =s.nextInt();
        	int height=1;
        	for (int j=1; j<=a; ++j)
        	{
        		if(j%2==0)
        			height+=1;
        		else
        			height*=2;
        	}
        	System.out.println(height);
        }
        
        
	}	 
}
	
