import model.{Thing, KnapSack}
import org.scalatest._

class SolversSpec extends FlatSpec with Matchers {

  "The solver" should "solve a problem correctly" in {
    val thingArray =
      Array(27, 38, 2, 86, 41, 112, 1, 0, 25, 66, 1, 97, 34, 195, 3, 85, 50, 42, 12, 223)
    val instance = new KnapSackProblem(thingCount = 10, capacity = 100, idealSolutionValue = 798)
    instance.loadThings(thingArray.map(_.toString))

    val desiredSolution =
      KnapSack.createFromBinary(Integer.parseInt("0110011101", 2), instance.stuff)

    def testSolver(solver: (List[Thing], Integer) => KnapSack) =
      solver(instance.stuff, instance.capacity).stuff.sortBy(_.value) should
        be (desiredSolution.stuff.sortBy(_.value))

    // choose a solver to test
    testSolver(Solvers.sortedValueWeightsDummyHeuristicSolver)
    //testSolver(Solvers.sortedWeightsGreedySolver)
  }

}
