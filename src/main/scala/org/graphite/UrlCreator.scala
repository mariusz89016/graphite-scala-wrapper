package org.graphite

case class UrlCreator(host: String, target: String, from: GraphiteTime, until: GraphiteTime, queryParamsMaybe: Option[String] = None) {
  import UrlCreator._

  def requestString: String = UrlFormat.format(host, target, from.getFormatted, until.getFormatted) + getQueryParams
  private def getQueryParams = queryParamsMaybe.map(queryParams => s"&$queryParams").getOrElse("")
}

object UrlCreator {
  val UrlFormat = "http://%s/render?target=%s&format=json&from=%s&until=%s"
}
