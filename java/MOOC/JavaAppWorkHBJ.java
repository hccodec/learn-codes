import java.util.Scanner;

public class JavaAppWorkHBJ
{
	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);//��ʼ��Scanner�����ڼ��̱�׼������
		System.out.println("JavaAppWorkHBJ=================\n");

		System.out.print("������������");
		int n=scanner.nextInt();//�Ӽ���ȡ������
		int[][] a=new int[n][n];//��ʼ��һ����ά����
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<=i;j++)
			{
				if(j==0) a[i][j]=1;
				else a[i][j]=a[i-1][j-1]+a[i-1][j];//��ֵ
				System.out.print(a[i][j]+"  ");
				if(a[i][j]<10) System.out.print(" ");//���ֵ
			}
			System.out.print("\n");//���Ƹ�ʽ
		}

		System.out.println("\nJavaAppWorkHBJ=================");
	}
}