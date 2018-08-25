package org.graphite

import com.fasterxml.jackson.annotation.JsonProperty

case class GraphiteSeriesResponse(@JsonProperty("target") target: String,
                                  @JsonProperty("datapoints") datapoints: List[Datapoint]) {
  override def toString: String =
    s"""-------------
       || target =>
       ||    $target
       || datapoints => ${datapoints.mkString("\n||\t", "\n||\t", "")}
       |--------------
    """.stripMargin
}
