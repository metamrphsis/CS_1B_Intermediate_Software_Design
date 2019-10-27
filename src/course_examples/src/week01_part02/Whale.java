package week01_part02;

public class Whale extends Mammal
{ 
    // ---------------------------------
	// attribute(s) of class
    // ---------------------------------
	private double speed;
	
    // ---------------------------------
    // constructors
    // ---------------------------------
	
    // explicit declaration of default constructor
	public Whale() 
	{ 
		// implicitly calls the default Mammal constructor
		//super();
		this.initialize(30.00);
		System.out.println ("Whale() constructor called");
	}
	
    // declaration of a constructor with two parameters
	public Whale(int a, double speed)
	{ 
		// explicitly calls the Mammal constructor with an int
		super(a);
		this.initialize(speed);
		System.out.println("Whale(int, double) constructor called");
	}
	
    // ---------------------------------
    // accessor and mutator methods
    // ---------------------------------
	private double getSpeed() 
	{ 
		return this.speed;
	}

	private void setSpeed(double newSpeed)
	{ 
		this.speed = newSpeed;
	}
	
    // ---------------------------------
    // private methods
    // ---------------------------------
	private void initialize(double speed)
	{ 
		this.setSpeed(speed);
	}

    // ---------------------------------
    // public methods
    // ---------------------------------
  
	public String toString()
	{ 
		return super.toString() + ", and speed is " + this.getSpeed();
	}

	public static void main(String[] args)
	{
		Mammal george = new Mammal(5);
		Whale blueWhale = new Whale(7, 77);

		// invokes setAge() of class Mammal
		blueWhale.setAge(5);

		System.out.println("Overriding:");
		// a)
		System.out.println(george.toString()); // Implicitly calls toString() method/function

		// b
		System.out.println("BlueWhale : " + blueWhale.toString()); // Implicitly calls toString() method/function

		// c
		System.out.println("BlueWhale : " + blueWhale); // Explicitly calls toString() method/function

		// d
		System.out.println(george); // Explicitly calls toString() method/function
	}
}