/**
  * Created by Robert-PC on 9/12/2017.
  */
object Roles {
  val Seed = "seed"
  val Consumer = "consumer"
  val Producer = "producer"
}

sealed trait Message
case class SimpleMessage() extends Message
