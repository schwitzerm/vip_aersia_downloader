package ca.schwitzer.vip_aersia_downloader

trait ProgressBarService {
  def clearCount(): Unit
  def clearProgress(): Unit
  def increment(value: Int = 1): Unit
  def setCount(value: Int): Unit
  def setProgress(value: Int): Unit
}

class ProgressBarServiceImpl extends ProgressBarService {
  var progressBar = new pb.ProgressBar(0)

  def clearCount(): Unit = progressBar = new pb.ProgressBar(0)

  def clearProgress(): Unit = progressBar.current = 0

  def increment(value: Int = 1): Unit = progressBar += 1

  def setCount(value: Int): Unit = progressBar = new pb.ProgressBar(value)

  def setProgress(value: Int): Unit = progressBar.current = value
}
