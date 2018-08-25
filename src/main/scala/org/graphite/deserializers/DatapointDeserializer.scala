package org.graphite.deserializers

import java.io.IOException

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.{DeserializationContext, JsonNode}
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.graphite.Datapoint

class DatapointDeserializer extends StdDeserializer[Datapoint](null: Class[_]) {
  @throws[IOException]
  def deserialize(jsonParser: JsonParser, deserializationContext: DeserializationContext): Datapoint = {
    val node: JsonNode = jsonParser.getCodec.readTree(jsonParser)
    val value: Double = node.get(0).asDouble
    val timestamp: Long = node.get(1).asLong
    new Datapoint(timestamp, value)
  }
}
