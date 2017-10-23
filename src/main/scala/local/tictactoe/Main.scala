package local.tictactoe

object Main extends App {

  def loop(state: State): Unit = {

    println(Render.board(state))

    (Game.winner(state), Game.full(state)) match {
      case (Some(p), _) => println(Render.winner(p))
      case (_, true) => println(Render.tie)
      case _ =>
        println(Render.nextToMove(state.nextToMove))
        Input.event(state) match {
          case Quit => println(Render.quit); sys.exit(0)
          case Action(a) => loop(Game.turn(state, a))
      }
    }
  }

  def entry(): Unit = {
    println("Choose board size:")
    val n = Input.size
    val idx = (1 to n).toList
    val init = State(
      idx = idx,
      board = (for { i <- idx; j <- idx } yield (i, j) -> None).toMap,
      nextToMove = X
    )
    loop(init)
  }

  entry()
}
