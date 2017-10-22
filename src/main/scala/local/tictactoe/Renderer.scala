package local.tictactoe

object Renderer {

  def render(state: State): String = {

    val glyphs: Map[(Int, Int), Char] =
      state.board.map { kv => (kv._1, renderPiece(kv._2)) }

    s"""
       % ${glyphs(1,1)} | ${glyphs(1,2)} | ${glyphs(1,3)}
       %---+---+---
       % ${glyphs(2,1)} | ${glyphs(2,2)} | ${glyphs(2,3)}
       %---+---+---
       % ${glyphs(3,1)} | ${glyphs(3,2)} | ${glyphs(3,3)}
       %
       %Next To Move: ${state.nextToMove}
       %""".stripMargin('%')
  }

  def renderPiece(piece: Option[Piece]): Char = piece match {
    case None => ' '
    case Some(X) => 'X'
    case Some(O) => 'O'
  }

  def winner(p: Piece): String = s"$p wins!"

  val tie: String = "Tie."
}
