package ca.schwitzer.vip_aersia_downloader

import com.google.inject.Guice

object Runner extends App {
  val injector = Guice.createInjector(new GuiceModule)
  val vipDownloader = injector.getInstance(classOf[VIPDownloader])

  //TODO: write small dir detector/creator

  //TODO: run class
}
