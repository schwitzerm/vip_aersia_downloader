package ca.schwitzer.vip_aersia_downloader

import java.io.File
import java.nio.file.{Files, Path, Paths}

import com.google.inject.Guice

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Runner extends App {
  //TODO: drop blind exception throwing for Try, or combine w/ the future from VIPDownloader
  def defaultDownload(): Unit = {
    val savePath = Paths.get(new File("./downloaded").getCanonicalPath)

    if(!Files.isDirectory(savePath)) {
      if(!new File(savePath.toString).mkdirs()) { throw new Exception(s"Could not create default dir: $savePath") }
    }
    vipDownloader.downloadAll(savePath) onComplete {
      case Success(_) => println("Done downloading!"); System.exit(0)
      case Failure(e) => throw e
    }
  }

  val injector = Guice.createInjector(new GuiceModule)
  val vipDownloader = injector.getInstance(classOf[VIPDownloaderService])

  //TODO: write small dir detector/creator

  args.length match {
    case 0 => defaultDownload()
    case _ =>
      val savePath: Path = Paths.get(new File(args(0)).getCanonicalPath)
      if(Files.isDirectory(savePath)) {
        vipDownloader.downloadAll(savePath) onComplete {
          case Success(_) => println("Done downloading!"); System.exit(0)
          case Failure(e) => throw e
        }
      }
      else {
        println(s"!! ${args(0)} is not a valid path! Attempting default path...")
        defaultDownload()
      }
  }
}
