import java.nio.file.{Paths, Files}
import scala.io.Source
import scala.io.StdIn._
import util.Timer

/**
 * KnapSack problem PAA
 *
 * @author Jakub Zitny <zitnyjak@fit.cvut.cz>
 * @since Fri Oct 30 05:03:43 CET 2015
 */
object Main {

  val DEBUG_INIT = false
  val DEBUG_ONE = false

  /**
   * dispatch processing of given file
   * - load data
   * - solve by a solver of choice
   *
   * @param inputFile String of the path to input file.
   */
  def processFile(inputFile: String) = {
    var times = List[Double]()
    for (line <- Source.fromFile(inputFile).getLines()) {
      val lineData = line.split(" ")
      val instance = new KnapSackProblem(lineData(0).toInt, lineData(1).toInt, lineData(2).toInt)
      instance.loadThings(lineData.slice(3, lineData.length))

      val resultTuple = Timer.meausreDuration {
        instance.findSolution(Solvers.greedySolver)
      }("findSolution")
      times = times :+ resultTuple._2
      if (DEBUG_ONE) System.exit(0) // one is enough for now
    }
    println("Average time for instance (" + inputFile + "): " + times.sum / times.length)
  }

  def manualInit() = {
    if (DEBUG_INIT) {
      print("press ENTER to start")
      readLine()
      println("started..")
    }
  }

  /**
   * main duh
   *
   * @param args Array of CLI args
   */
  def main(args: Array[String]): Unit = {
    manualInit()
    if (args.length == 0) System.exit(1)

    for (arg <- args) {
      if (!Files.exists(Paths.get(arg))) System.exit(2)
      Timer.meausreDuration{
        processFile(arg)
      }("processFile")
    }
  }
}