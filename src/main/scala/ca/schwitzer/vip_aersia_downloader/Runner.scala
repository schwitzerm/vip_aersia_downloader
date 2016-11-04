package ca.schwitzer.vip_aersia_downloader

import java.io.File
import java.nio.file.Paths

import com.google.inject.Guice

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Runner extends App {
  val injector = Guice.createInjector(new GuiceModule)
  val vipDownloader = injector.getInstance(classOf[VIPDownloader])

  //TODO: write small dir detector/creator

  vipDownloader.downloadAll(Paths.get(new File("./downloaded").getCanonicalPath)) onComplete {
    case Success(_) => println("Done downloading!")
    case Failure(e) => throw e
  }
}
