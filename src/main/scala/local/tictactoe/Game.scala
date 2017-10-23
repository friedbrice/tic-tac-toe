package local.tictactoe

object Game {

  def turn(state: State, action: PlayerAction): State = action match {

    case Move(p, (i, j)) => state.board(i, j) match {
      case None => State(state.idx, state.board.updated((i,j), Some(p)), next(p))
      case Some(_) => state
    }
  }

  def next(p: Piece): Piece = p match { case X => O; case O => X }

  def winner(state: State): Option[Piece] = {

    val State(idx, board, next) = state

    def row(i: Int): List[Option[Piece]] =
      idx.map(j => board(i, j))

    def col(j: Int): List[Option[Piece]] =
      idx.map(i => board(i, j))

    val rows = idx.map(row)
    val cols = idx.map(col)

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

  def full(state: State): Boolean =
    state.board.count(_._2.isDefined) == state.idx.length * state.idx.length
}
