package local.tictactoe

object Game {

  def turn(state: State, action: PlayerAction): State = action match {

    case Move(piece, (i, j)) => state.board(i, j) match {
      case Some(_) => state
      case None =>
        val newBoard = state.board.updated((i, j), Some(piece))
        State(state.idx, newBoard, swap(piece))
    }
  }

  def swap(p: Piece): Piece = p match { case X => O; case O => X }

  def winner(state: State): Option[Piece] = {

    val State(idx, board, _) = state

    val rows = idx.map(i => idx.map(j => board(i, j)))
    val cols = idx.map(j => idx.map(i => board(i, j)))

    val diags = List(
      idx.map(i => board(i, i)),
      idx.map(i => board((idx.max + 1) - i, i))
    )

    (for {
      line <- rows ++ cols ++ diags
      if !line.contains(None)
      if line.toSet.size == 1
    } yield line) match {
      case Nil => None
      case (winner::_)::_ => winner
    }
  }

  def filled(state: State): Boolean =
    state.board.count(_._2.isDefined) == state.idx.length * state.idx.length
}
