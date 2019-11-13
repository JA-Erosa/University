class Dog
{

    // Class variables
    var legs: Int = 4
    var cats: String = "enemies"

    // Class method
    def Display()
    {
        println("Dogs have : " + legs + " legs");
        println("A lot of people believe dogs and cats are: " + cats);
    }
}
class Huskey(var a:String) extends Dog
{
    // Class variables
    var breed: String = "Huskey"
    var name: String = a

    // Class methods
    def Breed_Disp()
    {
        println("Dog breed: " + breed);
        println("Dog's name: " + name);
    }
}

// Creating an object of the subclass and accessing methods from the parent and the subclass

val scooby = new Huskey("Scoob");
scooby.Display();
scooby.Breed_Disp();
