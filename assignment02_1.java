package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class assignment02_1 {

	private static int n=0;
	private static int []index;
	private static int [][] data;
	public static double min =  Double.MAX_VALUE;
	private static int []resultindex;


	public static void main(String[] args) {
		Scanner sc;
		try {
			sc = new Scanner(new File("input6.txt"));
			n = sc.nextInt();
			data= new int [n][2];
			index = new int[n];
			resultindex=new int [n];
			for(int j=0;j<n;j++) {
				index[j]=j;
			}

			int i=0;
			while(i<n) {
				data[i][0]= sc.nextInt();
				data[i][1]= sc.nextInt();
				i++;
			}		

		} catch (FileNotFoundException e) {
			System.out.println("no file");
			e.printStackTrace();
		}

		index[0]=0;
		tsp(1,0);

		System.out.print("[");
		for(int i=0;i<n;i++)
			System.out.print(resultindex[i]+" ");
		System.out.println("]");
		System.out.println("answer : " + min);

	}

	public static void tsp(int k ,double result) {

		if(min < result ) return;
		if(k==n) {			
			result += lengthwork(0,index[n-1]);
			if(result < min) {				
				min=result;	
				for(int i=0;i<n;i++)
					resultindex[i] = index[i];
			}
			
		}

		else {
		for(int i=k;i<n;i++) {				
			swapindex(k,i);
			tsp(k+1, result+lengthwork(index[k-1],index[k]));
			swapindex(k,i);
		}
		}
	}
	public static double lengthwork(int x,int y) {
		return Math.sqrt((data[x][0]-data[y][0])*(data[x][0]-data[y][0]) + 
				(data[x][1]-data[y][1])*(data[x][1]-data[y][1]));
	}

	public static void swapindex(int x,int y) {
		int tmp = index[x];
		index[x]=index[y];
		index[y]= tmp;
	}
}
