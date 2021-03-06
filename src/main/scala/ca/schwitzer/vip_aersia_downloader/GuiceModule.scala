package ca.schwitzer.vip_aersia_downloader

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.google.inject.{AbstractModule, Inject, Provider}
import com.typesafe.config.{Config, ConfigFactory}

class GuiceModule extends AbstractModule {
  import GuiceModule._

  override def configure(): Unit = {
    bind(classOf[ProgressBarService]).to(classOf[ProgressBarServiceImpl])
    bind(classOf[VIPDownloaderService]).to(classOf[VIPDownloaderServiceImpl])

    bind(classOf[ActorSystem]).toProvider(classOf[ActorSystemProvider])
    bind(classOf[ActorMaterializer]).toProvider(classOf[ActorMaterializerProvider])
    bind(classOf[Config]).toProvider(classOf[ConfigProvider])
  }
}

object GuiceModule {
  class ActorSystemProvider extends Provider[ActorSystem] {
    val system = ActorSystem()

    override def get(): ActorSystem = system
  }

  class ActorMaterializerProvider @Inject()(implicit system: ActorSystem) extends Provider[ActorMaterializer] {
    val materializer = ActorMaterializer()

    override def get(): ActorMaterializer = materializer
  }

  class ConfigProvider extends Provider[Config] {
    val config = ConfigFactory.load()

    override def get(): Config = config
  }
}
