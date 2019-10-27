package week01_part02;

class Sheep extends Mammal 
{
    // ---------------------------------
	// attribute(s) of class
    // ---------------------------------
    private String name = "sheep";
    private int speed = 20;

    // ---------------------------------
    // constructors
    // ---------------------------------
   
    // declaration of a constructor with two parameters
    public Sheep(int age, String name)
    {
        super();
    	this.name = name;
    }
    
    // ---------------------------------
    // accessor and mutator methods
    // ---------------------------------
   
    public int getSpeed()
    {
        return this.speed;
    }

    // ---------------------------------
    // public methods
    // ---------------------------------
   
    public String toString() 
    { 
        return this.name + " can run at " + this.getSpeed();
    }
    
    
    public static void main(String[] args)
    {
        Mammal george = new Mammal(5);
        Sheep shaun = new Sheep(7, "Shaun");

        // invokes setAge() of class Mammal
        shaun.setAge(5);
        
        System.out.println("Overriding:");
		// a)
		System.out.println(george.toString()); // Implicitly calls toString() method/function

		// b
		System.out.println(shaun.toString()); // Implicitly calls toString() method/function

		// c
		System.out.println(shaun); // Explicitly calls toString() method/function

		// d
		System.out.println(george); // Explicitly calls toString() method/function
    }
}
