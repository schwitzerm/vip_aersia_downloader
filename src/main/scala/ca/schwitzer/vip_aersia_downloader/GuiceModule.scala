package ca.schwitzer.vip_aersia_downloader

import com.google.inject.AbstractModule

class GuiceModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ProgressBarService]).to(classOf[ProgressBarServiceImpl])
    bind(classOf[VIPDownloaderService]).to(classOf[VIPNopImpl])
  }
}
