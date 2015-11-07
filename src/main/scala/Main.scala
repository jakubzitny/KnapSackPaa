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

  val DebugInit = false
  val DebugOne = false

  /**
   * dispatch processing of given file
   * - load data
   * - solve by a solver of choice
   *
   * @param inputFile String of the path to input file.
   */
  def processFile(inputFile: String) = {
    var times = List[Double]()
    var errors = List[Double]()
    val solFile = inputFile.replace("inst", "sol")
    val solFileIterator = Source.fromFile(solFile).getLines()
    for (lineTuple <- Source.fromFile(inputFile).getLines() zip
      Source.fromFile(solFile).getLines()) {
      val lineData = lineTuple._1.split(" ")
      val lineSolData = lineTuple._2.split(" ")
      val instance = new KnapSackProblem(lineData(0).toInt, lineData(1).toInt,
        lineData(2).toInt, lineSolData(2).toInt)
      instance.loadThings(lineData.slice(3, lineData.length))

      val resultTuple = Timer.measureDuration {
        instance.findSolution(Solvers.greedySolver)
      }("findSolution")
      times = times :+ resultTuple._2
      errors = errors :+ instance.calcError()
      if (DebugOne) System.exit(0) // one is enough for now
    }

    println(f"($inputFile) Average time: ${times.sum / times.length}%2.2f")
    println(f"($inputFile) Average error: ${errors.sum / errors.length}%2.4f")
    println(f"($inputFile) Max error: ${errors.max}%2.4f")

  }

  def manualInit() = {
    if (DebugInit) {
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
      Timer.measureDuration{
        processFile(arg)
      }("processFile")
    }
  }
}
