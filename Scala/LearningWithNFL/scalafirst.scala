class Dog(val a:String, val b:Int)
{

    // Class variables
    var age: Int = b
    var name: String = a

    // Class method
    def Display()
    {
        println("Name of the dog is : " + name);
        println("The age of the dog is: " + age);
    }
}

// Creating object and then calling a method from the class

var philo = new Dog("Philo",5);
philo.Display();
