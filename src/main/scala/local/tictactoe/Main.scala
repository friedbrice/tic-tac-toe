package local.tictactoe

object Main extends App {

  def step(state: State, event: Event): State = event match {

    case Move(p, (i, j)) => state.board(i, j) match {
      case None => State(state.board.updated((i,j), Some(p)), GameLogic.turn(p))
      case Some(_) => state
    }
  }

  def run(state: State): Unit = {

    println(Renderer.render(state))

    (GameLogic.winner(state), GameLogic.full(state)) match {
      case (Some(p), _) => println(Renderer.winner(p))
      case (None, true) => println(Renderer.tie)
      case _ => run(step(state, InputParser.getInput(state)))
    }
  }

  val initialState: State = State(
    board = (for {
      i <- List(1, 2, 3)
      j <- List(1, 2, 3)
    } yield (i, j) -> None).toMap,
    nextToMove = X
  )

  run(initialState)
}
