public class BattleShipContext {
    // state handler for the different game states
    public void setState(BattleState newState) {
        currentState = newState;
    }
    
    // call this method when a grid click is detected and pass in the grid coordinates
    public void gridAction(int x, int y) {
        activePlayer = currentState.handleGrid(activePlayer, x, y);
    }

    // call this method when a click on the next button is detected
    public void nextAction() {
        currentState = currentState.handleNext();
        activePlayer = currentState.getActive(); // if there we go to the next player's turn, reflect this change in the context
    }


    private Player activePlayer; // the player with the current turn
    private Player waitingPlayer; // the other player who is waiting for his turn
    private BattleState currentState;
}
