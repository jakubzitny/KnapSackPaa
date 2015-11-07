package util

/**
 * Timing utility object
 *
 * @author Jakub Zitny <zitnyjak@fit.cvut.cz>
 * @since Sat Nov  7 12:29:07 CET 2015
 */
object Timer {

  val DEBUG_TIME = false

  private def normalize(num: Double): Double =
    BigDecimal(num).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble

  def meausreDuration[R](block: => R)
                        (blockTag: String = "block"): (R, Double) = {
    val t0 = System.currentTimeMillis()
    val result = block // call-by-name
    val t1 = System.currentTimeMillis()

    val diffMs = t1 - t0

    if (DEBUG_TIME) println(s"Elapsed time: ($blockTag): ${diffMs}ms")
    (result, diffMs)
  }

}
