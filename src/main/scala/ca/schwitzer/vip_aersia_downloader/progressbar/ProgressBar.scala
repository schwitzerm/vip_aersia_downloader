package ca.schwitzer.vip_aersia_downloader.progressbar

import com.github.nscala_time.time.Imports._
import jline.TerminalFactory

case class ProgressBar(progress: Int, total: Int, startTime: Option[DateTime] = None) {
  def clearProgress: ProgressBar = this.copy(progress = 0)
  def draw(): Unit = {
    val width: Int = TerminalFactory.get.getWidth

    def barBuilder(prefix: String, suffix: String) = {
      val barElems = ProgressBar.format.split("")
      val size = width - prefix.length - suffix.length - 3
      if (size > 0) {
        val curCount = Math.ceil((progress.toFloat / total) * size).toInt
        val remCount = size - curCount

        var bar = barElems(0)
        bar += barElems(1) * (curCount - 1) + barElems(2)
        bar += barElems(3) * remCount + barElems(4)

        prefix + bar + suffix
      }
      else { "" }
    }

    def prefixBuilder: String = "%d / %d ".format(progress, total)

    def suffixBuilder: String = {
      val pct: String = " %.2f %%".format(progress.toFloat / (total.toFloat / 100))
      val timeLeft: Option[Long] = startTime.map { start =>
        val left = ((start to DateTime.now).millis / progress) * (total - progress)
        Duration.millis(Math.ceil(left).toLong).seconds
      }

      timeLeft match {
        case Some(tl) => tl.toString + " " + pct
        case None => pct
      }
    }

    var bar = barBuilder(prefixBuilder, suffixBuilder)
    if(bar.length < width) {
      bar += " " * (width - bar.length)
    }

    print("\r" + bar)
  }

  def increment(v: Int = 1): ProgressBar = this.copy(progress = this.progress + v)
}

object ProgressBar {
  private val format: String = "[=:-]"
}
