class Rectangle extends Shapes
{
	protected int height;
	protected int width;
	Rectangle(int h, int w)
	{
		height=h;
		width=w;
	}
	public void setCircumference()
	{
		circumference=2*(height+width);
	}
	public void setArea()
	{
		area=height*width;
	}
	public String toString()
	{
		return ""+area;
	}
}