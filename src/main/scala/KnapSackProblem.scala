import model.{Thing, KnapSack}

case class KnapSackProblem (instanceId: Integer, thingCount: Integer, capacity: Integer) {

  var stuff = List[Thing]()

  def loadThings(thingData: Array[String]) = {
    for (i <- 0 to thingCount * 2 - 1 by 2) {
      stuff = stuff :+ new Thing(thingData(i).toInt, thingData(i + 1).toInt)
    }
  }

  def printStuff(stuff: List[Thing]) = {
    for (thing <- stuff) {
      println(thing.value, thing.weight)
    }
    println("...")
  }

  /**
   * Dispatcher for finding solutions with given solver
   *
   * @param solver Function taking parsed input and returning a solution
   */
  def findSolution(solver: (List[Thing], Integer) => KnapSack) = {
    val foundSolution = solver(stuff, capacity)
    println(instanceId + " " + thingCount + " " + foundSolution.getTotalValue
      + "  " + stuff.map(x => if (foundSolution.stuff.contains(x)) 1 else 0).mkString(" "))
  }

}

