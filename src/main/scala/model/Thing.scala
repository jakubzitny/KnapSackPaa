package model

/**
 * Representation of a thing to be loaded into a bag
 *
 * @param weight Weight of the thing
 * @param value Value of the thing
 */
case class Thing (weight: Integer, value: Integer) {

  def valueWeightRatio = if (weight > 0) value/weight else 0

}