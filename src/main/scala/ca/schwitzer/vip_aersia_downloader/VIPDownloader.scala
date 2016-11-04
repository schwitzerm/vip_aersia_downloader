package ca.schwitzer.vip_aersia_downloader

import java.nio.file.Path

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.google.inject.Inject
import com.typesafe.config.Config

trait VIPDownloader {
  def downloadAll(savePath: Path): Unit //TODO: replace
}

class VIPDownloaderImpl @Inject()(implicit config: Config,
                                  system: ActorSystem,
                                  materializer: ActorMaterializer) extends VIPDownloader {
  override def downloadAll(savePath: Path): Unit = {

  }
}
