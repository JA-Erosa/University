// Abstract class cannot be instantiated (can't create a direct object)
abstract class first{
  // Class attributes
  var age: Int = 5
  // Class Methods
  final def detailsAbs(){
    println("These are the details for the abstract class")
  }
}

class second extends first{
  // Class attributes
  var name: String= "Second class"
  var number: Int= 2
  // Class methods
  def details(){
    println("This class' name is " + name)
    println("This class' number is " + number)
  }
}

class third{
  // Class methods
  def poly(a: String, b: String){
    println("Your name is "+a+ " and your last name is " +b)
  }
  def poly(c: String, d: Int){
    println("Your name is "+c+ " and your age is "+ d)
  }
  def poly(e: Int, f: Int){
    println("Kids who are "+ e + " usually are in grade "+ f)
  }
}

val friend = new second();
friend.detailsAbs();
println();
friend.details();
// You can modify the abstract class variable because it isn't encapsulated as private
friend.age = 10;
println(friend.age);
println()
var polimorfismo = new third();
polimorfismo.poly("Jorge", "Erosa");
polimorfismo.poly("Jorge", 23);
polimorfismo.poly(9, 3);

var input=scala.io.StdIn.readLine();
var parsed=input.split(" ");
println(parsed.length);
