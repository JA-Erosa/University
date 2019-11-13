//Composition and inheritance
//extending classes


abstract class Element{
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height==0) 0 else contents(0).length
}
/* parentless vs empty-parent methods
arrays vs lists*/
/*class ArrayElement2(val contents: Array[String])extends Element*/


class ArrayElement(conts:Array[String]) extends Element{
  def contents: Array[String]= conts
}
val try1= new ArrayElement(Array("Hello","World"));
println(try1.width);
println(try1);
val try2 =Array("Hello","World");
println(try2);
println(try2(0));
try2.foreach(println);

/*val try3= new ArrayElement2(Array("Hello","World"));
println(try3.width);
println(try3);*/
