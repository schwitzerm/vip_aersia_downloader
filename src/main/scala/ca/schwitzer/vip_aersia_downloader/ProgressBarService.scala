package ca.schwitzer.vip_aersia_downloader

import ca.schwitzer.vip_aersia_downloader.progressbar.ProgressBar

trait ProgressBarService {
  def clearCount(): Unit
  def clearProgress(): Unit
  def draw(): Unit
  def increment(value: Int = 1): Unit
  def setCount(value: Int): Unit
}

class ProgressBarServiceImpl extends ProgressBarService {
  var progressBar = new ProgressBar(0, 0)

  def clearCount(): Unit = progressBar = new ProgressBar(0, 0)

  def clearProgress(): Unit = progressBar = progressBar.clearProgress

  def draw(): Unit = progressBar.draw()

  def increment(value: Int = 1): Unit = progressBar = progressBar.increment(value)

  def setCount(value: Int): Unit = progressBar = new ProgressBar(0, value)
}
