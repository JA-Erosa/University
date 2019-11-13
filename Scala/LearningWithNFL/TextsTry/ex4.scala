// polymorphism

class show{
  def a(x:Int){
    println("This is an Integer")
  }
  def a(y:String){
    println("This is a String")
  }
}

val b=new show() ;
b.a(1);
b.a("hola");


// heighten widen book
