package local.tictactoe

import scala.io.StdIn.readLine
import scala.util.matching.Regex

object Input {

  def getInput(state: State): Event = parse(state, readLine("> "))

  val matchMove: Regex = "([123])([123])".r

  def parse(state: State, input: String): Event = input match {

    case matchMove(i, j) =>
      Move(state.nextToMove, (i.toInt, j.toInt))

    case invalid =>
      println(s"Invalid input, $invalid")
      getInput(state)
  }
}
