import java.util.Scanner;//导入Scanner类
public class App2_1{
	public static void main(String[] args){
		//创建Scanner类的对象Scan
		Scanner scan=new Scanner(System.in);
		System.out.println("请输入一个正整数：");
		//scan对象调用nextInt方法输入函数
		int read=scan.nextInt();
		System.out.println("输入的正整数是："+read);
		System.out.println("请输入一个浮点数：");
		//scan对象调用nextFloat方法输入浮点数
		float f=scan.nextFloat();
		System.out.println("输入的浮点数为："+f);
		System.out.println("请输入一行英文：");
		scan.nextLine();//抵消回车符
		String s=scan.nextLine();
		System.out.println("输入的英文是："+s);
	}
}