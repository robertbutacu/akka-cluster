package ClusterExample

/**
  * Created by Robert-PC on 9/12/2017.
  */
import akka.actor.{Actor, ActorLogging}

class ConsumerActor extends Actor with ActorLogging {
  override def receive(): Receive = {
    case msg => log.info(s"""${sender.path.address} : $msg""")
  }
}

