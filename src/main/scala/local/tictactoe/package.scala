package local

package object tictactoe {

  sealed trait Piece
  case object X extends Piece
  case object O extends Piece

  type Board = Map[(Int, Int), Option[Piece]]

  case class State(idx: List[Int], board: Board, next: Piece)

  sealed trait PlayerAction
  case class Move(player: Piece, move: (Int, Int)) extends PlayerAction

  sealed trait Event
  case object Quit extends Event
  case class Action(action: PlayerAction) extends Event
}
