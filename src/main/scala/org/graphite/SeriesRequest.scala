package org.graphite

import java.time.LocalDateTime

case class SeriesRequest(target: String, from: GraphiteTime, until: GraphiteTime, queryParams: Map[String, String] = Map()) {
  def getFormattedQueryParams: Option[String] = {
    val formattedQueryParams = queryParams.map { case (key, value) => s"$key=$value" }.mkString("&") //todo escaping (?)

    if (formattedQueryParams.isEmpty)
      None
    else
      Some(formattedQueryParams)
  }

  def from(graphiteTime: GraphiteTime): SeriesRequest = copy(from = graphiteTime)
  def from(localDateTime: LocalDateTime): SeriesRequest = copy(from = GraphiteTimeAbsolute(localDateTime))
  def from(year: Int, month: Int, dayOfMonth: Int, hour: Int, minute: Int): SeriesRequest =
    copy(from = GraphiteTimeAbsolute(year, month, dayOfMonth, hour, minute))

  def until(graphiteTime: GraphiteTime): SeriesRequest = copy(until = graphiteTime)
  def until(localDateTime: LocalDateTime): SeriesRequest = copy(until = GraphiteTimeAbsolute(localDateTime))
  def until(year: Int, month: Int, dayOfMonth: Int, hour: Int, minute: Int): SeriesRequest =
    copy(until = GraphiteTimeAbsolute(year, month, dayOfMonth, hour, minute))

  def putQueryParam(pair: (String, String)): SeriesRequest = copy(queryParams = queryParams + pair)

  def sumSeries: SeriesRequest = copy(target = s"sumSeries($target)")
}
