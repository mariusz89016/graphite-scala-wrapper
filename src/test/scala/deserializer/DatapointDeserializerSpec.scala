package deserializer

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.core.{JsonFactory, JsonParser}
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.graphite.Datapoint
import org.graphite.deserializers.DatapointDeserializer
import org.scalatest._

class DatapointDeserializerSpec extends FlatSpec with Matchers {
  val module = new SimpleModule()
  module.addDeserializer(classOf[Datapoint], new DatapointDeserializer)
  private val objectMapper = new ObjectMapper(new JsonFactory().enable(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS))
    .registerModules(DefaultScalaModule, module)

  it should "parse response into list of datapoints" in {
    val datapoints: List[Datapoint] = objectMapper.readValue(
      """
        | [
        |   [42.5, 123456],
        |   [22.5, 234567],
        |   [12.5, 345678],
        |   [32.5, 456789]
        | ]
      """.stripMargin, new TypeReference[List[Datapoint]] {})

    datapoints should === (List(Datapoint(123456, 42.5), Datapoint(234567, 22.5),
                                Datapoint(345678, 12.5), Datapoint(456789, 32.5)))
  }
}