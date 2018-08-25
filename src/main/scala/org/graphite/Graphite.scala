package org.graphite

import java.io.{BufferedReader, IOException, InputStreamReader}
import java.net.URL
import java.util.stream.Collectors

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.core.{JsonFactory, JsonParser}
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.graphite.GraphiteTime._

import scala.io.Source

class Graphite(host: String, port: Int, val prefix: String) {
  import Graphite._

  private val mapper = new ObjectMapper(new JsonFactory().enable(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS))
    .registerModule(DefaultScalaModule)

  @throws[IOException]
  def getData(seriesRequest: SeriesRequest): List[GraphiteSeriesResponse] = {
    val url = UrlCreator(host, seriesRequest.target, seriesRequest.from, seriesRequest.until, seriesRequest.getFormattedQueryParams)

    mapper.readValue(getRequest(url.requestString), new TypeReference[List[GraphiteSeriesResponse]] {})
  }

  object implicits {
    implicit def stringToSeriesRequest(target: String)(implicit graphite: Graphite): SeriesRequest = series(target)
    implicit def intToGraphiteTimeRelative(amount: Int): IntToGraphiteTimeRelative = IntToGraphiteTimeRelative(amount)
  }
}

object Graphite {
  @throws[IOException]
  private def getRequest(stringUrl: String): String = {
    val urlConnection = new URL(stringUrl).openConnection
    Source.fromInputStream(urlConnection.getInputStream).getLines().mkString
  }

  private def series(target: String,
             from: GraphiteTime = 15.minutesAgo,
             until: GraphiteTime = Now,
             queryParams: Map[String, String] = Map())(implicit graphite: Graphite) =
    SeriesRequest(graphite.prefix + "." + target, from, until, queryParams)
}
