// Invoking superclass constructors

class name(a:String){
  private var fullname: String = a
  def names(){
    println("My name is ", fullname)
  }
}

class son(x:String) extends name(x){
  val b = "Hello"
}

val my=new son("Jorge Agustin");
my.names();
print(my.b);
