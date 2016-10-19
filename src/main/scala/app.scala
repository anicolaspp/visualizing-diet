package dogs

import algebra.Order
import cats.Show
import dogs.Diet.{DietNode, EmptyDiet}
import reftree._
import java.nio.file.Paths

import dogs.Range._
import cats.implicits._
import Range._


import scala.concurrent.duration._

object app extends App {

  val diagram = Diagram(
    defaultOptions = Diagram.Options(density = 75),
    defaultDirectory = Paths.get("/Users/nperez/diet", "")
  )

  val d: Diet[Int] = Diet.empty

  val d2 = d.add(12)

  val d3 = d2.remove(12)


  val trees = Utils.iterate(Diet.empty[Int])(_ + 12, _ + Range(14, 20), _ - Range(10, 12))

  implicit val show = new ToRefTree[Diet[Int]] {
    override def refTree(value: Diet[Int]): RefTree = toRef(value)
  }

  diagram.renderSequence("diet-seq1")(trees)

//  diagram.renderAnimation("aaaa", tweakOptions = _.copy(onionSkinLayers = 2, keyFrameDuration = 3.second, diffAccent = true))(trees)


//  def toTree[A](d: Diet[A])(implicit sr: Show[Range[A]]) = toRef(d)

  var i = 0

  def toRef[A](d: Diet[A])(implicit sr: Show[Range[A]]): RefTree = d match {

    case EmptyDiet              =>  RefTree.Null(false)
    case x @ DietNode(a, b, l, r)   => RefTree.Ref(x, Seq(toRef(l), toRef(r))).copy(name=sr.show(Range(a,b))) //RefTree.Ref(sr.show(Range(a, b)), {i+=1;i.toString}, Seq(toRef(l), toRef(r)) , false)

  }
}