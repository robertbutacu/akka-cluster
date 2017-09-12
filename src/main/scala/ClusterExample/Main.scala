package ClusterExample

import akka.actor.{ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import Roles._

/**
  * Created by Robert-PC on 9/12/2017.
  */
object Main extends App {
  val role = args.headOption.getOrElse(Seed)
  val config = ConfigFactory.parseString(s"""akka.cluster.roles = ["$role"]""")
    .withFallback(ConfigFactory.load())

  val app = config.getString("args.app-name")
  val system = ActorSystem(app, config)

  def startMessageGenerator(producer: ActorRef): Unit = {
    import scala.concurrent.duration.DurationInt
    import system.dispatcher
    system.scheduler.schedule(10 seconds, 2 seconds, producer, SimpleMessage)
  }

  role match {
    case Producer =>
      val producer = system.actorOf(Props[RoundRobinProducerActor], Producer)
      startMessageGenerator(producer)
    case Consumer =>
      system.actorOf(Props[ConsumerActor], Consumer)
    case Seed =>
  }
}
