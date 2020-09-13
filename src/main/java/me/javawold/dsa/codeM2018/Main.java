package me.javawold.dsa.codeM2018;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
        in.close();
    }
	
	 public static void main1(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        int n = sc.nextInt();
	        int ans = 0, x;
	        for(int i = 0; i < n; i++){
	            for(int j = 0; j < n; j++){
	                x = sc.nextInt();
	                ans += x;
	            }
	        } 
	        sc.close();
	        System.out.println(ans); 
	    }

}
