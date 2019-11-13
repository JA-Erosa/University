// Creating the class nfl as the parent Class
class NFL
{
  // Defining the class variables or attributes
  private var num_teams: Int=32
  private var season: Int=100
  private var conferences_num: Int=2
  private var division_num: Int=4
  private var conferences_names: String = "American and National"
  private var division_names: String = "North, South, East and West"

  // Defining the class methods
  def Teams_Conf()
  {
    println("There are "+num_teams+ " teams in the NFL.");
    println("There are "+conferences_num+ " conferences and their names are "+conferences_names);
  }
}
// creating the class Chiefs for the Kansas City Chiefs
class Chiefs(var a:String,var b:String) extends NFL
{
  // Defining the class variables or attributes //
  var place: String="Kansas City"
  var coach: String="Andy Reid"
  var name: String=a
  var position: String=b

  // Defining the class methods
  def Player()
  {
    println(name+ " plays as a " +position+ " for the " +place+ " Chiefs");
  }
  def Coach()
  {
    println(name+ "'s head coach is " +coach);
  }
}
//creating the class Ravens for the Baltimore Ravens
class Ravens(var a:String,var b:String) extends NFL
{
  // Defining the class variables or attributes
  var place: String="Baltimore"
  var coach: String="John Harbaugh"
  var name: String=a
  var position: String=b

  // Defining the class methods
  def Player()
  {
    println(name+ " plays as a " +position+ " for the " +place+ " Ravens");
  }
  def Coach()
  {
    println(name+ "'s head coach is " +coach);
  }
}
//creating the class Falcons for the Atlanta Falcons
class Falcons(var a:String,var b:String) extends NFL
{
  // Defining the class variables or attributes
  var place: String="Atlanta"
  var coach: String="Dan Quinn"
  var name: String=a
  var position: String=b

  // Defining the class methods
  def Player()
  {
    println(name+ " plays as a " +position+ " for the " +place+ " Falcons");
  }
  def Coach()
  {
    println(name+ "'s head coach is " +coach);
  }
}
class Ryan(a:String="Matt Ryan",b:String="Quarterback") extends Falcons(a,b)
{
  // Defining class variables
  var age:Int=34
  var number:Int=2
  var nickname: String="Matty Ice"

  // Defining class methods
  def Ice()
  {
    println(name+ "'s number is the number "+ number + " and his nickname is "+nickname);
    println("He's " +age+ " years old and he plays as a " + position + " for the "+ place+ " Falcons");
  }
}

val julio= new Falcons("Julio Jones","Wide Receiver");
println();
julio.Teams_Conf();
println(julio.name);
julio.Player();
println();

val mahomes= new Chiefs("Patrick Mahomes", "Quarterback");
println(mahomes.position);
mahomes.Coach();
println();

val ingram= new Ravens("Mark Ingram", "Running Back");
ingram.Player();
ingram.Coach();
println();

val matty= new Ryan();
matty.Ice();
matty.Teams_Conf();
println();
