package model

import scala.annotation.tailrec

case class KnapSack (stuff: List[Thing]) {

  def getTotalValue: Integer = {
    @tailrec
    def aggValue(stuff: List[Thing], acc: Integer = 0): Integer = stuff match {
      case Nil => acc
      case x :: tail => aggValue(tail, acc + x.value)
    }
    aggValue(stuff)
  }

  def getTotalWeight: Integer = {
    @tailrec
    def aggWeight(stuff: List[Thing], acc: Integer = 0): Integer = stuff match {
      case Nil => acc
      case x :: tail => aggWeight(tail, acc + x.weight)
    }
    aggWeight(stuff)
  }

}

object KnapSack {
  def createEmpty(): KnapSack = {
    new KnapSack(List())
  }

  def createFromBinary(x: Int, stuff: List[Thing]): KnapSack = {
    val binaryRep =  "0" * (stuff.length - x.toBinaryString.length) + x.toBinaryString
    new KnapSack(
      (stuff, binaryRep.toList)
      .zipped
      .map((item: Thing, cond) => if (cond == '1') item else null)
      .filter(_ != null)
    )
  }
}