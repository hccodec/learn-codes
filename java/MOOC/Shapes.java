/* ����һ����ʾͼ�ε��� Shapes.java */
abstract class Shapes					//����һ����ΪShapes�ĳ�����
{
	//============================================================
	protected double circumference;		//ͼ���ܳ�
	protected double area;				//ͼ�����
	abstract void setCircumference();	//�����ܳ������󷽷���
	abstract void setArea();			//������������󷽷���
	//===========================================================/
	public double getCircumference()	//��ȡ�ܳ�
	{
		return circumference;
	}
	public double getArea()				//��ȡ���
	{
		return area;
	}
}