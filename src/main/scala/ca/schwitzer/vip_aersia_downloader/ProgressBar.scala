package ca.schwitzer.vip_aersia_downloader

class ProgressBar(var count: Int = 0) {
  var progressBar = new pb.ProgressBar(count)

  def clear(): Unit = {
    count = 0
    progressBar = new pb.ProgressBar(0)
  }

  def increment(value: Int = 1): Unit = progressBar += 1

  def setCount(value: Int): Unit = {
    count = value
    progressBar = new pb.ProgressBar(count)
  }

  def setProgress(value: Int): Unit = progressBar.current = value
}
