package org.graphite

import java.time.{Instant, ZoneId}

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.graphite.deserializers.DatapointDeserializer

@JsonDeserialize(using = classOf[DatapointDeserializer])
case class Datapoint(timestamp: Long, value: Double) {
  import Datapoint._

  override def toString = s"(${humanReadableTime(timestamp)}, $value)"
}

object Datapoint {
  def humanReadableTime(timestamp: Long): String = {
    val date = Instant.ofEpochMilli(timestamp * 1000)
      .atZone(ZoneId.systemDefault())
      .toLocalDateTime

    date.format(Formatter)
  }
}
