package local.tictactoe

object Render {

  def board(state: State): String = {

    val glyphs: Map[(Int, Int), String] =
      state.board.map { case (k, v) => (k, glyph(v)) }

    val hrule: String =
      state.idx.map(_ => "---").mkString("", "+", "\n")

    def row(i: Int): String =
      state.idx.map(j => glyphs(i, j)).mkString(" ", " | ", " \n")

    state.idx.map(row).mkString("\n", hrule, "")
  }

  def glyph(piece: Option[Piece]): String = piece match {
    case None => " "
    case Some(X) => "X"
    case Some(O) => "O"
  }

  def next(p: Piece): String = s"Next to move: $p"

  def winner(p: Piece): String = s"$p wins!"

  val tie: String = "Tie."

  val quit: String = "Thanks for playing!"

  val choose: String = "Choose board size:"
}
