package org.graphite

abstract class GraphiteTimeUnit(val abbreviation: String)

object Seconds extends GraphiteTimeUnit("s")
object Minutes extends GraphiteTimeUnit("min")
object Hours extends GraphiteTimeUnit("h")
object Days extends GraphiteTimeUnit("d")
object Weeks extends GraphiteTimeUnit("w")
object Months extends GraphiteTimeUnit("mon")
object Years extends GraphiteTimeUnit("y")
