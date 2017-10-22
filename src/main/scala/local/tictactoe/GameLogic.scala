package local.tictactoe

object GameLogic {

  def winner(state: State): Option[Piece] = {

    def row(i: Int): List[Option[Piece]] =
      List(1, 2, 3).map(j => state.board(i, j))

    def col(j: Int): List[Option[Piece]] =
      List(1, 2, 3).map(i => state.board(i, j))

    val rows = List(1, 2, 3).map(row)
    val cols = List(1, 2, 3).map(col)

    val diags = List(
      List(1, 2, 3).map(i => state.board(i, i)),
      List(1, 2, 3).map(i => state.board(4-i, i))
    )

    (for {
      line <- rows ++ cols ++ diags
      if !line.contains(None)
      if line.toSet.size == 1
    } yield line) match {
      case Nil => None
      case List(winner,_,_)::_ => winner
    }
  }

  def turn(p: Piece): Piece = p match { case X => O; case O => X }

  def full(state: State): Boolean =
    state.board.count(kv => kv._2.isDefined) == 9
}
