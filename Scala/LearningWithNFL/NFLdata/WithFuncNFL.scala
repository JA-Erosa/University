
//Abstract class can't be directly instantiated, so we need to call/modify it from a subclass
abstract class NFL{
  // Class attributes
  private val teams: Int = 32
  private val conferences: Int = 2
  private val divisions: Int = 4
  val sb: String= "New England Patriots vs San Francisco 49ers"
  // Class methods to showcase number of teams, conferences and divisions. This can't be modified even with override
  final def number_of_teams(){
    println(f"There are $teams teams in the NFL." )
  }
  final def number_of_conf(){
    println(f"There are $conferences conferences in the NFL." )
  }
  final def number_of_div(){
    println(f"There are $divisions divisions per conference in the NFL")
  }
  // method to showcase the teams who'll play the superbowl
  def SuperBowl(){
    println(f"The 2 teams who will play for the Super Bowl this year will be $sb")
  }
}
// Class that will inherit NFL's attributes and methods
class Team(a: String) extends NFL{
  // Class attributes that store team name and counters
  val team: String= a
  var wins: Int=0
  var loss: Int=0
  var ties: Int=0
  var games: Int=0
  var listapuntosf=Array[Int]()
  var listapuntosa=Array[Int]()

  //Adding functionality
  def roundAt(p: Double)(n: Double): Double = { val s = math pow (10, p); (math round n * s) / s }
  def roundAt2(p: Double) = roundAt(2)(p)

  def proy = (x: Double,b:Int) => roundAt2(x/b * 100)

  // lambda expression that will be used as a counter
  val plus = (x:Int) => x + 1

  // Class polymorphic methods, will apply depending on input
  // If only 1 int is typed we'll showcase the points the team scored
  def teamResult(p:Int){
    println(f"$team scored $p points today")
  }
  // If 2 ints and a string are typed, we'll consider it a win or a loss depending
  def teamResult(home:Int,away:Int,t:String){
    listapuntosf=listapuntosf:+home
    listapuntosa=listapuntosa:+away
    if (t==team){
        wins=plus(wins)
        games=plus(games)
        println(f"We won! With a score of $home - $away")
    } else{
        loss=plus(loss)
        games=plus(games)
        println(f"We lost :( Today's score was $home - $away in favor of $t")
        println("We'll come back stronger next week! RISE UP!")
    }
  }
  // If 2 ints are typed we'll check if it's indeed a tie or the user forgot to input the name of the winning team
  def teamResult(b:Int,c:Int){
    if (b==c){
        listapuntosf=listapuntosf:+b
        listapuntosa=listapuntosa:+c
        ties=plus(ties)
        games=plus(games)
        println(f"We tied! Today's score was $b - $c")
    }
    else{
      println("You didn't enter the correct score or you didn't type the winner!")
    }
  }
  // the record method shows the user its team statistics during the season making use of the variables stored
  def record(){
      val favour=listapuntosf.sum
      val against=listapuntosa.sum
      var winper=(wins.toDouble/games.toDouble)*100
      var ppg=(favour.toDouble/games.toDouble)
      var ppga=(against.toDouble/games.toDouble)
      var proyeccion1=listapuntosf.map(x => proy(x,favour))
      var proyeccion2=listapuntosa.map(x => proy(x,against))
      println(f"Your team's record is $wins - $loss - $ties");
      println(f"Your team has scored a total of $favour points for $games games");
      println(f"Your team has allowed a total of $against points for $games games");
      println(f"Your team has an average of $ppg%1.2f points per game");
      println(f"Your team allows an average of $ppga%1.2f points per game");
      println(f"Your team has a win percentage of $winper%1.2f"+ '%')
      //println(f"If your team played double the games, they would have $fav points in favour, $aga points against and $gms games in total")
      println("Percentage of points in favour registered per game")
      proyeccion1.foreach(println)
      println("Percentage of points against registered per game")
      proyeccion2.foreach(println)
    }
}
// We define a function playball to interact with the user while continue = y for a default yes, the con value automatically sets as val, so we make another variable var to stop the cycle
def playBall(con: String){
  var continue=con;
  while (continue== "y" ){
    println("Please enter the name of your team: " );
    // We read the team name from line and then pass it on as a string constructor for the new instance of Team
    var h = scala.io.StdIn.readLine();
    var season=0;
    var x=new Team(h.toString);
    // We quickly display the starting format for our abstract class which will set some basic data about the NFL
    x.number_of_teams();
    x.number_of_conf();
    x.number_of_div();
    println("This is your team: " +x.team);
    // We start a while cycle for the season, so as long as the user doesn't state that the season is over we'll continue collecting data
    while (season==0){
      println("Please enter today's score with the following format: \nHomeScore(Number) AwayScore(Number) WinningTeam(Name)");
      // The user needs to input 2 ints and a string in that order, yet we're prepared to parse the line on a string and then calling the polymorphic function depending on the length of our string
      var input=scala.io.StdIn.readLine();
      var parsed=input.split(" ");
      if (parsed.length==1){
          var a=parsed(0).toInt;
          x.teamResult(a);
      }
      else if (parsed.length==2){
          var a=parsed(0).toInt;
          var b=parsed(1).toInt;
          x.teamResult(a, b);
      }
      else if (parsed.length==3){
          var a=parsed(0).toInt;
          var b=parsed(1).toInt;
          var c=parsed(2).toString;
          x.teamResult(a, b, c);
      }
      else{
          println("Incorrect number of variables, you either didn't enter anything or you gave more than 3 variables")
      }
      // Asking the user if he/she wants to continue with the season
      println("Is the season over? Type 0 for no and 1 for yes");
      season=scala.io.StdIn.readInt();
    }
    // Once the season ends for a team we'll display the team's record and then ask if the user wants to do another team or season
    x.record();
    println()
    println("Do you wish to fill another season? Type y for Yes");
    continue=scala.io.StdIn.readLine();
    // Once it ends the program the user will see a message stating that the season has ended and then which teams made it to the super bowl that season
    if (continue != 0){
      println("Season has ended");
      x.SuperBowl();
    }
  }
}

// main free code stating a variable continue and applying the function playBall
playBall("y") ;
