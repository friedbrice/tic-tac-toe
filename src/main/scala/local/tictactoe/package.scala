package local

package object tictactoe {

  sealed abstract class Piece extends Product with Serializable
  case object X extends Piece
  case object O extends Piece

  case class State(board: Map[(Int, Int), Option[Piece]], nextToMove: Piece)

  sealed abstract class Event extends Product with Serializable
  case class Move(player: Piece, move: (Int, Int)) extends Event
}
