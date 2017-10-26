package local.tictactoe

import scala.io.StdIn.readLine
import scala.util.matching.Regex

object Input {

  def size: Int = {

    val matchDigit = "(\\d)".r

    readLine("> ") match {
      case matchDigit(n) => n.toInt
      case _ => size
    }
  }

  def event(state: State): Event = parse(state, readLine("> ")) match {
    case Right(event) => event
    case Left(invalid) => println(s"Invalid input, $invalid"); event(state)
  }

  def parse(state: State, input: String): Either[String, Event] = {

    val digits = state.idx.mkString

    val matchQuit: Regex = "q".r
    val matchMove: Regex = s"([$digits])([$digits])".r

    input match {
      case matchQuit() => Right(Quit)
      case matchMove(i, j) => Right(Action(Move(state.next, (i.toInt, j.toInt))))
      case invalid => Left(invalid)
    }
  }
}
