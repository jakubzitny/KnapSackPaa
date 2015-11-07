import model.{Thing, KnapSack}

import scala.annotation.tailrec

/**
 * available solver functions taking parsed input data and returning best solution found
 *
 * @author Jakub Zitny <zitnyjak@fit.cvut.cz>
 * @since Fri Oct 30 05:03:43 CET 2015
 */
object Solvers {

  /**
   * The greediest of the greedy solvers.
   *
   * @param stuff List of things to be loaded into the bag
   * @param capacity Integer max capacity of the bag
   * @return KnapSack best solution found by the solver
   */
  def greedySolver(stuff: List[Thing], capacity: Integer): KnapSack = {
    var idealSolution = KnapSack.createEmpty()
    for (i <- 0 to 2 << stuff.size - 1) {
      val attempt = KnapSack.createFromBinary(i, stuff)
      if (attempt.getTotalWeight <= capacity &&
        attempt.getTotalValue > idealSolution.getTotalValue) {
        //println("(" + i + ") " + i.toBinaryString + ": got " + attempt.getTotalValue)
        idealSolution = attempt
      }
    }
    idealSolution
  }

  /**
   * TODO
   *
   * @param stuff List of things to be loaded into the bag, already sorted
   * @param capacity Integer max capacity of the bag
   * @return KnapSack found solution
   */
  def dummyHeuristicSolver(stuff: List[Thing], capacity: Integer): KnapSack = {
    @tailrec
    def fillKnapSack(knapSack: KnapSack, stuff: List[Thing]): KnapSack =
      knapSack.getTotalWeight match {
        case empty if stuff.isEmpty => knapSack
        case totalWeight if totalWeight + stuff.head.weight >= capacity => knapSack
        case _ => fillKnapSack(knapSack.addThing(stuff.head), stuff.tail)
      }
    fillKnapSack(KnapSack.createEmpty(), stuff)
  }

  /**
   * The dummy heuristic with presorted things by value, descending.
   *
   * @see dummyHeuristic
   */
  def sortedValuesDummyHeuristicSolver(stuff: List[Thing], capacity: Integer): KnapSack =
    dummyHeuristicSolver(stuff.sortBy(-_.value), capacity)

  /**
   * The dummy heuristic with presorted things by weights, ascending.
   *
   * @see dummyHeuristic
   */
  def sortedWeightsDummyHeuristicSolver(stuff: List[Thing], capacity: Integer): KnapSack =
    dummyHeuristicSolver(stuff.sortBy(_.weight), capacity)

  /**
   * The dummy heuristic with presorted things by the ratio of values to weights, descending.
   *
   * @see dummyHeuristic
   */
  def sortedValueWeightsDummyHeuristicSolver(stuff: List[Thing], capacity: Integer): KnapSack =
    dummyHeuristicSolver(stuff.sortBy(-_.valueWeightRatio), capacity)

}
