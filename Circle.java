import java.util.HashSet;
import java.util.Set;
import java.math.BigDecimal;

public class Circle{	private double radius;
	public Circle(double radius)
{
	this.radius = radius;
	}
	@Override
	public boolean equals(Object obj)
	{
		Circle other = (Circle) obj;
		return other.radius == this.radius;
	}
	@Override
	public int hashCode()
	{
		return new Double(radius).hashCode();
	}
	public static void main(String[] args)
	{
		Set<Circle> set = new HashSet<Circle>();
		Circle c1 = new Circle(5);
		Circle c2 = new Circle(5);
		System.out.println(c1.equals(c2));
		set.add(c1);
		set.add(c2);
		System.out.println(set.size());
		
		int dollar = 100;
		int numDimes = 9;
		int dime = 10;
		System.out.println(dollar - numDimes*dime);
		System.out.println("Cents");
	}
}