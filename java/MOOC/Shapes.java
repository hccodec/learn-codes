/* 例：一个表示图形的类 Shapes.java */
abstract class Shapes					//定义一个名为Shapes的抽象类
{
	//============================================================
	protected double circumference;		//图形周长
	protected double area;				//图形面积
	abstract void setCircumference();	//计算周长（抽象方法）
	abstract void setArea();			//计算面积（抽象方法）
	//===========================================================/
	public double getCircumference()	//获取周长
	{
		return circumference;
	}
	public double getArea()				//获取面积
	{
		return area;
	}
}