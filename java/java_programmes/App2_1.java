import java.util.Scanner;//����Scanner��
public class App2_1{
	public static void main(String[] args){
		//����Scanner��Ķ���Scan
		Scanner scan=new Scanner(System.in);
		System.out.println("������һ����������");
		//scan�������nextInt�������뺯��
		int read=scan.nextInt();
		System.out.println("������������ǣ�"+read);
		System.out.println("������һ����������");
		//scan�������nextFloat�������븡����
		float f=scan.nextFloat();
		System.out.println("����ĸ�����Ϊ��"+f);
		System.out.println("������һ��Ӣ�ģ�");
		scan.nextLine();//�����س���
		String s=scan.nextLine();
		System.out.println("�����Ӣ���ǣ�"+s);
	}
}