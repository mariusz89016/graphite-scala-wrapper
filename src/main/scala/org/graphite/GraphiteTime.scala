package org.graphite

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object GraphiteTime {
  val Now: GraphiteTime = new GraphiteTime() {
    def getFormatted: String = "now"
  }

  implicit class IntToGraphiteTimeRelative(amount: Int) {
    def secondsAgo = GraphiteTimeRelative(amount, Seconds)
    def minutesAgo = GraphiteTimeRelative(amount, Minutes)
    def hoursAgo = GraphiteTimeRelative(amount, Hours)
    def daysAgo = GraphiteTimeRelative(amount, Days)
    def weeksAgo = GraphiteTimeRelative(amount, Weeks)
    def monthsAgo = GraphiteTimeRelative(amount, Months)
    def yearsAgo = GraphiteTimeRelative(amount, Years)
  }
}

abstract class GraphiteTime {
  def getFormatted: String
}

case class GraphiteTimeRelative(amount: Int, graphiteTimeUnit: GraphiteTimeUnit) extends GraphiteTime {
  def getFormatted: String = -amount + graphiteTimeUnit.abbreviation
}

case class GraphiteTimeAbsolute(localDateTime: LocalDateTime) extends GraphiteTime {
  def getFormatted: String = localDateTime.format(DateTimeFormatter.ofPattern("HH:mm_uuuuMMdd"))
}

object GraphiteTimeAbsolute {
  def apply(year: Int, month: Int, dayOfMonth: Int, hour: Int, minute: Int): GraphiteTimeAbsolute = {
    GraphiteTimeAbsolute(LocalDateTime.of(year, month, dayOfMonth, hour, minute))
  }
}
