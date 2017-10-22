package local.tictactoe

import scala.util.matching.Regex

object InputParser {

  def getInput(state: State): Event =
    parse(state, scala.io.StdIn.readLine("> "))

  val matchMove: Regex = "([123])([123])".r

  def parse(state: State, input: String): Event = input match {

    case matchMove(i, j) =>
      Move(state.nextToMove, (i.toInt, j.toInt))

    case invalid =>
      println(s"Invalid input, $invalid")
      getInput(state)
  }
}
