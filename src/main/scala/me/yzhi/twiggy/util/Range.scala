package me.yzhi.twiggy.util

import scala.collection.immutable.NumericRange
import scala.{specialized=>spec}

/**
 */


class Range[@spec(Int, Long) T](val range: NumericRange[T]) {
}

object Range {
  abstract class Limit[T] {
    val min: T
    val max: T
    val step: T
  }

  implicit object IntLimits extends Limit[Int] {
    val min = 0
    val max = Int.MaxValue
    val step = 1
  }

  implicit object LongLimits extends Limit[Long] {
    val min = 0L
    val max = Long.MaxValue
    val step = 1L
  }

  // FIXME
  def all[@spec(Int, Long) T] = {
    new Range[T](NumericRange[T](implicitly[Limit[T]].max, implicitly[Limit[T]].max, implicitly[Limit[T]].step))
  }
}

