class Parent{
  // Class variables
    private var age: Int = 40
    private var name: String = "Jorge"

    // Class method
    def DisplayParent()
    {
        println("Name of the parent is : " + name);
        println("Age of the parent is: " + age);
    }
}

class Son extends Parent{
  // Class variables
  //var age: Int = a
//  var name: String = b
  // Class method
//  def DisplaySon()
  //{
    //  println("Name of the parent is : " + name);
    //  println("Age of the parent is: " + age);
//  }
}

val julio= new Son();
julio.DisplayParent();
println();
julio.age=20;
julio.DisplayParent();
