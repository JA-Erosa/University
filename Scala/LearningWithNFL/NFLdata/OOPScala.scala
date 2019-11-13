
//Abstract class can't be directly instantiated, so we need to call/modify it from a subclass
abstract class NFL{
  // Class attributes
  private val teams: Int = 32
  private val conferences: Int = 2
  private val divisions: Int = 4
  val sb: String= "Kansas City vs New Orleans"
  // Class methods to showcase number of teams, conferences and divisions. This can't be modified even with override
  final def number_of_teams(){
    println("There are " +teams+ " teams in the NFL." )
  }
  final def number_of_conf(){
    println("There are " +conferences+ " conferences in the NFL." )
  }
  final def number_of_div(){
    println("There are " +divisions+ " divisions per conference in the NFL")
  }
  // method to showcase the teams who'll play the superbowl
  def SuperBowl(){
    println("The 2 teams who will play for the Super Bowl this year will be " +sb)
  }
}
// Class that will inherit NFL's attributes and methods
class Team(a: String) extends NFL{
  // Class attributes that store team name and counters
  val team: String= a
  var wins: Int=0
  var loss: Int=0
  var ties: Int=0
  var pointsT: Int=0
  var games: Int=0

  // Class polymorphic methods, will apply depending on input
  // If only 1 int is typed we'll showcase the points the team scored
  def teamResult(p:Int){
    println(team+ " scored " +p+ " points today")
  }
  // If 2 ints and a string are typed, we'll consider it a win or a loss depending
  def teamResult(home:Int,away:Int,t:String){
    pointsT += home;
    if (t==team){
        wins +=1
        games +=1
        println("We won! With a score of " +home+ "-" +away)
    } else{
        loss +=1
        games +=1
        println("We lost :( Today's score was " +home+ "-" +away+ " in favor of "+t)
        println("We'll come back stronger next week! RISE UP!")
    }
  }
  // If 2 ints are typed we'll check if it's indeed a tie or the user forgot to input the name of the winning team
  def teamResult(b:Int,c:Int){
    if (b==c){
        pointsT += b;
        ties +=1
        games +=1
        println("We tied! Today's score was " +b+ "-" +c)
    }
    else{
      println("You didn't enter the correct score or you didn't type the winner!")
    }
  }
  // the record method shows the user its team statistics during the season making use of the variables stored
  def record(){
      println("Your team's record is " +wins+ " - "+loss+ " - " +ties);
      println("Your team has scored a total of "+pointsT+ " for " +games+ " games");
      println("Your team has an average of " +(pointsT.toDouble/games.toDouble)+ " points per game");
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
