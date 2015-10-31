import model.{Thing, KnapSack}

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
   * The greedy solver with presorted things by values.
   *
   * @see greedySolver
   */
  def sortedValuesGreedySolver(stuff: List[Thing], capacity: Integer): KnapSack = {
    greedySolver(stuff.sortBy(thing => thing.value), capacity)
  }

  /**
   * The greedy solver with presorted things by weights.s
   *
   * @see greedySolver
   */
  def sortedWeightsGreedySolver(stuff: List[Thing], capacity: Integer): KnapSack = {
    greedySolver(stuff.sortBy(thing => thing.weight), capacity)
  }

  /**
   * The greedy solver with presorted things by the ratio of values to weights.
   *
   * @see greedySolver
   */
  def sortedValueWeightsRatioGreedySolver(stuff: List[Thing], capacity: Integer): KnapSack = {
    greedySolver(stuff.sortBy(thing => thing.valueWeightRatio), capacity)
  }

}
