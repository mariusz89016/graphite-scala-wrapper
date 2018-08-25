package org

import java.time.format.DateTimeFormatter

package object graphite {
  val Formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
}
