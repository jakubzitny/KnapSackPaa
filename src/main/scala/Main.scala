import java.nio.file.{Paths, Files}
import scala.io.Source

/**
 * KnapSack problem PAA
 *
 * @author Jakub Zitny <zitnyjak@fit.cvut.cz>
 * @since Fri Oct 30 05:03:43 CET 2015
 */
object Main {

  /**
   * dispatch processing of given file
   * - load data
   * - solve by a solver of choice
   *
   * @param inputFile String of the path to input file.
   */
  def processFile(inputFile: String) = {
    for (line <- Source.fromFile(inputFile).getLines()) {
      val lineData = line.split(" ")
      val instance = new KnapSackProblem(lineData(0).toInt, lineData(1).toInt, lineData(2).toInt)
      instance.loadThings(lineData.slice(3, lineData.length))
      //instance.findSolution(Solvers.sortedValueWeightsRatioGreedySolver)
      instance.findSolution(Solvers.greedySolver)
      //System.exit(0) // one is enough for now
    }
  }

  /**
   * main duh
   *
   * @param args Array of CLI args
   */
  def main(args: Array[String]): Unit = {
    if (args.length == 0) System.exit(1)

    for (arg <- args) {
      if (!Files.exists(Paths.get(arg))) System.exit(2)
      processFile(arg)
    }
  }
}