//defining and overriding parametric fields

class Cat{
  val dangerous=false
}

class Tiger(
  override val dangerous : Boolean,
  private var age: Int
) extends Cat{
  def ages{
    println("Tiger's age is " ,age)
  }
}

var cat=new Cat();
println(cat.dangerous);

var tiger= new Tiger(true , 5);
println(tiger.dangerous);
tiger.ages;
