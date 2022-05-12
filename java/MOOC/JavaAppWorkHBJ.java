import java.util.Scanner;

public class JavaAppWorkHBJ
{
	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);//初始化Scanner类用于键盘标准输入流
		System.out.println("JavaAppWorkHBJ=================\n");

		System.out.print("请输入行数：");
		int n=scanner.nextInt();//从键盘取得行数
		int[][] a=new int[n][n];//初始化一个二维数组
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<=i;j++)
			{
				if(j==0) a[i][j]=1;
				else a[i][j]=a[i-1][j-1]+a[i-1][j];//赋值
				System.out.print(a[i][j]+"  ");
				if(a[i][j]<10) System.out.print(" ");//输出值
			}
			System.out.print("\n");//控制格式
		}

		System.out.println("\nJavaAppWorkHBJ=================");
	}
}