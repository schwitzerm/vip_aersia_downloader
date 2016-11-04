package ca.schwitzer.vip_aersia_downloader

import ca.schwitzer.vip_aersia_downloader.progressbar.ProgressBar
import com.google.inject.Singleton
import org.joda.time.DateTime

trait ProgressBarService {
  def clearProgress(): Unit
  def draw(): Unit
  def increment(value: Int = 1): Unit
  def setStartTime(value: DateTime): Unit
  def setTotal(value: Int): Unit
}

@Singleton
class ProgressBarServiceImpl extends ProgressBarService {
  var progressBar = new ProgressBar(0, 0)

  def clearProgress(): Unit = progressBar = progressBar.clearProgress

  def draw(): Unit = progressBar.draw()

  def increment(value: Int = 1): Unit = progressBar = progressBar.increment(value)

  def setStartTime(value: DateTime): Unit = progressBar = progressBar.copy(startTime = Some(value))

  def setTotal(value: Int): Unit = progressBar = progressBar.copy(total = value)
}
