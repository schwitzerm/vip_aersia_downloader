package ca.schwitzer.vip_aersia_downloader

import java.nio.file.Path

import akka.NotUsed
import akka.actor.ActorSystem
import akka.http.scaladsl._
import akka.http.scaladsl.model.{HttpRequest, StatusCodes}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import com.google.inject.Inject
import com.typesafe.config.Config

import scala.xml.{Elem, XML}

trait VIPDownloader {
  def downloadAll(savePath: Path): Unit //TODO: replace
}

class VIPDownloaderImpl @Inject()(implicit config: Config,
                                  system: ActorSystem,
                                  materializer: ActorMaterializer) extends VIPDownloader {
  val httpFlow = Http().outgoingConnection(host = config.getString("vip.http-addr"))

  def xmlSource: Source[Elem, NotUsed] = Source.single(HttpRequest(uri = s"/${config.getString("vip.xml-file")}"))
    .via(httpFlow)
    .flatMapConcat(xhr => xhr.status match {
      case StatusCodes.OK => xhr.entity.dataBytes.reduce(_ concat _)
      //TODO: specific errors?
      case _ => throw new Exception(xhr.status.reason())
    })
    .map(bs => XML.loadString(bs.utf8String))

  def filenameFlow = Flow[Elem].mapConcat { xml =>
    //drop the first 4 as they're "information" tracks
    (xml \ "trackList" \ "track" \ "location").drop(4).map(_.text.split("/").last)
  }

  def downloadTracksFlow = Flow[String].map(filename => HttpRequest(uri = "/mu/" + filename))
    .via(httpFlow)
    .flatMapConcat(_.entity.dataBytes.reduce(_ concat _))

  override def downloadAll(savePath: Path): Unit = {
    //limited at 5000, tracks shouldnt exceed this many unless the author of the site adds a TON more..
    xmlSource.via(filenameFlow).limit(5000).runWith(Sink.seq).map { filenames =>
      //group into a stream of streams 80 tracks long, as http flows will stop processing after ~100 elems
      val fileStreams = Source(filenames)
        .grouped(80)
        .map(xs => Source(xs))

      //map over each stream in the main stream. each stream will run in parallel
      fileStreams.map { stream =>
        stream.zip(stream.via(downloadTracksFlow))
      }.runWith(Sink.ignore)
    }
  }
}
