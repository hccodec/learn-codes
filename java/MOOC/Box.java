class Box extends Rectangle
{
	private int width;
	Box(int h, int w)
	{
		super(h, w);
	}
	Box(int h, int w, int innerw)
	{
		this(h, w);
		width=innerw;
	}
	public void setWidth(int innerw)
	{
		width=innerw;
	}
	public int getWidth()
	{
		return width;
	}
	public void setCircumference()
	{
		super.setCircumference();
		circumference+=4*width;
	}
	public int setArea(int innerw)
	{
		return width*width;
	}
	public void setArea()
	{
		area=height*super.width-setAera(width);
	}
}