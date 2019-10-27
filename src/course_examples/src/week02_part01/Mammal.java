package week02_part01;

public class Mammal extends Animal
{
	private int age;

	private int getAge() 
	{	return age; }

	protected void setAge( int newAge ) 
	{ 	age = newAge; }

	public Mammal()
	{	this.setAge(162); }

	public Mammal(int age)
	{
		// option 1
		//this.setAge(age);

		// option 2
		this.age = age;

		// option 3: NOT AN OPTION
		//age = age;
	}

	// mammal
	public String toString()
	{	return super.toString() + " age is "+ this.getAge(); }
}
