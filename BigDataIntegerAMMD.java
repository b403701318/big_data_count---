package com.structure.interestmath;

import java.util.Scanner;

/**
 * 大数（整数）：加减乘除
 * 
 * @author Administrator
 *
 */
public class BigDataIntegerAMMD {

	public static void add(String s1, String s2) {
		int[] x = new int[Math.max(s1.length(), s2.length())];
		int i = 0, j = Math.max(s1.length(), s2.length()) - Math.min(s1.length(), s2.length());
		if (s1.length() > s2.length()) {
			for (; i < j; i++) {
				int t = Integer.parseInt(String.valueOf(s1.charAt(i)));
				x[i] = t;
			}
			for (j = 0; i < s1.length() & j < s2.length(); i++, j++) {
				int t1 = Integer.parseInt(String.valueOf(s1.charAt(i)));
				int t2 = Integer.parseInt(String.valueOf(s2.charAt(j)));
				x[i] = t1 + t2;
			}
		} else {
			for (; i < j; i++) {
				int t = Integer.parseInt(String.valueOf(s2.charAt(i)));
				x[i] = t;
			}
			for (j = 0; i < s2.length() & j < s1.length(); i++, j++) {
				int t1 = Integer.parseInt(String.valueOf(s2.charAt(i)));
				int t2 = Integer.parseInt(String.valueOf(s1.charAt(j)));
				x[i] = t1 + t2;
			}
		}

		// 处理进位
		for (i = x.length - 1; i > 0; i--) {
			x[i - 1] = x[i - 1] + x[i] / 10;
			x[i] = x[i] % 10;
		}
		String res = new String();
		for (i = 0; i < x.length; i++) {
			res += x[i];
		}
		x = null;
		System.out.println("加法结果：" + res);
	}

	public static void minus(String s1, String s2) {
		boolean flag = true;// true:有负号，false:无负号（表示结果）
		if (s1.length() > s2.length()) {// s1>s2
			flag = false;
		}
		if (s1.length() == s2.length()) {//
			for (int i = 0; i < s1.length(); i++) {
				if (s1.charAt(i) > s2.charAt(i)) {
					flag = false;
					break;
				} else if (s1.charAt(i) < s2.charAt(i)) {
					break;
				}
			}
		}
		int[] x;
		int i = 0, j = 0;
		if (flag) {// s1<s2
			j = s2.length() - s1.length();
			x = new int[s2.length()];
			i = 0;
			while (i < j) {
				x[i] = Integer.parseInt(String.valueOf(s2.charAt(i)));
				i++;
			}
			int ii = s2.length() - 1, jj = s1.length() - 1;
			for (; jj >= 0; ii--, jj--) {
				int t1 = Integer.parseInt(String.valueOf(s2.charAt(ii)));
				int t2 = Integer.parseInt(String.valueOf(s1.charAt(jj)));
				x[ii] = t1 - t2;
			}

		} else {
			j = s1.length() - s2.length();
			i = 0;
			x = new int[s1.length()];
			while (i < j) {
				x[i++] = Integer.parseInt(String.valueOf(s1.charAt(i)));
			}
			int ii = s1.length() - 1, jj = s2.length() - 1;
			for (; jj >= 0; ii--, jj--) {
				int t1 = Integer.parseInt(String.valueOf(s1.charAt(ii)));
				int t2 = Integer.parseInt(String.valueOf(s2.charAt(jj)));
				x[ii] = t1 - t2;
			}
		}
		// 处理由于不够减产生负数的情况
		// System.out.println(Arrays.toString(x));
		for (int ii = x.length - 1; ii > 0; ii--) {
			if (x[ii] < 0) {
				x[ii - 1] = x[ii - 1] - 1;
				x[ii] += 10;
			}
		}

		String res = new String();
		if (flag) {
			res += '-';
		}
		for (i = 0; i < x.length; i++) {// 去掉计算中产生的0（最前面）
			if (x[i] != 0) {
				break;
			}
		}
		for (; i < x.length; i++) {
			res += x[i];
		}
		System.out.println("减法结果：" + res);

	}

	public static void multiply(String s1, String s2) {
		int[][] a = new int[s1.length()][s2.length()];
		for (int i = 0; i < s1.length(); i++) {
			int t1 = Integer.parseInt(String.valueOf(s1.charAt(i)));
			for (int j = 0; j < s2.length(); j++) {
				int t2 = Integer.parseInt(String.valueOf(s2.charAt(j)));
				a[i][j] = t1 * t2;
			}
		}

		// 对角线相加
		int[] x = new int[s1.length() + s2.length() - 1];
		x[0] = a[0][0];
		x[x.length - 1] = a[s1.length() - 1][s2.length() - 1];
		int k=1;
		while (k<= s1.length() + s2.length() - 3) {// 对角线元素相加
			for (int i = 0; i <= Math.min(k, s1.length() - 1); i++) {
				for (int j = 0; j <= Math.min(k, s2.length() - 1); j++) {
					if (i + j == k) {
						x[k] += a[i][j];
					}
				}
			}
			k++;
		}
		int c = 0;
//		System.out.println(Arrays.toString(x));
		// 处理进位问题
		for (int i = x.length - 1; i > 0; i--) {
			c = x[i] / 10;
			x[i - 1] += c;
			x[i] = x[i] % 10;
		}
		String res = new String();
		for (int i = 0; i < x.length; i++) {
			res = res + x[i];
		}
		System.out.println("乘法结果：" + res);
	}

	public static void divide(String s1, String s2) {
		int []c=new int[s1.length()];
		int t1=0;//余数
		for(int i=0;i<s1.length();i++) {
			c[i]=(t1*10+Integer.parseInt(String.valueOf(s1.charAt(i))))/Integer.parseInt(s2);
			t1=(t1*10+Integer.parseInt(String.valueOf(s1.charAt(i))))%Integer.parseInt(s2);
		}
		//去掉最前面的零，比如s1=234,s2=156,得到c=[0,0,1]
		String res =new String();
		int i=0;
		for(;i<c.length;i++) {
			if(c[i]!=0) {
				break;
			}
		}
		while(i<c.length) {
			res+=c[i];
			i++;
		}
		System.out.println("相除的结果(整数部分)："+res);
		System.out.println("相除的结果(余数部分)："+t1);
		
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			String s1 = new String();
			s1 = sc.next();
			String s2 = new String();
			s2 = sc.next();

			add(s1, s2);
			minus(s1, s2);
			multiply(s1, s2);
			divide(s1, s2);
		}
	}

}
