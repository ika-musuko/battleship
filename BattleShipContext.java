public class BattleShipContext {
    /// STATE HANDLER FOR THE GAME
    // this class should seamlessly interface with any type of front end
    // call gridAction() when you want to handle any events on a player's grid
    // call nextAction() when you want to handle going to the next course of the game (when the player is done deciding what to do) 
    // hopefully changing the States has been correctly handled (ie: no erroneous changing of state)
    //////////////////////////////////////////////////////////////////////////

    // some class constants to make sure SetupState chooses its next state properly and we don't end up in an infinite setup loop...
    final boolean NEXT_SETUP = true;
    final boolean NEXT_PLAY = false;

    // constructor, initial setup should be something like:
    // context = new BattleShipContext(player1, player2);
    // depending on how the players are named...
    // automatically sets currentState to P1SetupState
    public BattleShipContext(Player activePlayer, Player waitingPlayer) {
        this.activePlayer = activePlayer;
        this.waitingPlayer = waitingPlayer;
        this.currentState = SetupState(this.activePlayer, this.waitingPlayer, NEXT_SETUP);
    }

    // call this method when a grid click is detected and pass in the grid coordinates
    public void gridAction(int x, int y) {
        this.activePlayer = this.currentState.handleGrid(x, y);
    }

    // call this method when a click on the next button is detected
    public void nextAction() {
        // process player actions and get the updated state
        this.currentState = this.currentState.handleNext();

        // update the current active and waiting Players
        this.activePlayer = this.currentState.getActive();
        this.waitingPlayer = this.currentState.getWaiting();
    }

    private Player activePlayer; // the player with the current turn
    private Player waitingPlayer; // the other player who is waiting for his turn
    private BattleState currentState;
    
    /// STATE CLASSES
    // I wrote these inside this context class because they're not going to be used outside of this handler 
    // "interface" for a BattleState. this is an abstract class because java doesn't have an elegant way to swap anything via a simple function...(therefore I decided to store the Players as attributes) (educational/funny: https://stackoverflow.com/questions/3624525/how-to-write-a-basic-swap-function-in-java)
    /////////////////////////////////////////////////////////////////////
    abstract class BattleState {
        public BattleState(Player activePlayer, Player waitingPlayer) {
            this.activePlayer = activePlayer;
            this.waitingPlayer = waitingPlayer;
        }

        // handle a grid event
        public abstract Player handleGrid(int x, int y);
        // handle the next button
        public abstract BattleState handleNext();
        // output the state to a String
        public String toString() {
            return this.battleString;
        }

        // getters
        public Player getActive() {
            return this.activePlayer;
        }

        public Player getWaiting() {
            return this.waitingPlayer;
        }
        
        protected Player activePlayer;
        protected Player waitingPlayer;
        protected String battleString;
    }

    class SetupState extends BattleState {
        // constructor written with two parameters to stay consistent with rest of hierarchy
        public SetupState(Player activePlayer, Player waitingPlayer) {
            SetupState(activePlayer, waitingPlayer, true);
        }
        
        // more usable constructor...
        public SetupState(Player activePlayer, Player waitingPlayer, boolean gotoSetup) {
            super();
            this.gotoSetup = gotoSetup;
            this.battleString = activePlayer.name() + " setup";
        }
        
        // place or rotate a ship on activePlayer's board
        public Player handleGrid(int x, int y) {
            // make sure the below function also doesn't add any ships if there are more than 5 ships
            // note that placeShip will rotate a ship if it already exists instead of creating a new one (extra credit points!!!)
            this.activePlayer.placeShip(x, y);
            return this.activePlayer; // return activePlayer to update in context
        }

        // go to the next player's setup state or if all players have finished setting up, begin game with the first player 
        public BattleState handleNext() {
            if(this.activePlayer.totalShips() < Player.MAX_SHIPS) {
                return this.gotoSetup ? new SetupState(this.waitingPlayer, this.activePlayer, false) 
                                      : new AttackState(this.waitingPlayer, this.activePlayer);
            }
            // if the player has not finished setting up ships, do not change the state
            this.battleString = activePlayer.name() + "setup /// please place " + Player.MAX_SHIPS + " on the board."; // notify the player that they have not finished setup
            return this; 
        }

        private boolean gotoSetup; // boolean flag to decide where to go upon successful completion of setup
    }

    // the attacker is activePlayer and the one waiting his turn is waitingPlayer
    class AttackState extends BattleState {
        public AttackState(Player activePlayer, Player waitingPlayer) {
            super();
            this.battleString = activePlayer.name() + " attack";
            this.markSet = false;
        }

        public Player handleGrid(int x, int y) {
            this.markSet = this.activePlayer.setMark(x, y); // handle already marked squares in setMark() method for Player (return a boolean)
            return this.activePlayer;
        }

        public BattleState handleNext() {
            // if a valid mark has been set...
            if(this.markSet) {
                this.waitingPlayer = this.activePlayer.attack(this.waitingPlayer); // takes the currently marked square from activePlayer and attacks the waiting player on the corresponding square and updates the waitingPlayer
                return (this.waitingPlayer.noShips()) ? new WinState(this.activePlayer, this.waitingPlayer); // win if all the other ships on the waitingPlayer's board are gone
                                                      : new AttackState(this.waitingPlayer, this.activePlayer); // next turn
            } 
            
            // else do not change the state
            else {
                this.battleString = activePlayer.name() + " attack // please mark a valid square to attack";
                return this;
            }
            
        private boolean markSet; // has activePlayer set a mark yet?
    }
    
    // the winning player is activePlayer and the losing player is waitingPlayer
    // for now, we will let the game be nonfunctional upon win...
    class WinState extends BattleState {
        public WinState(Player activePlayer, Player waitingPlayer) {
            super();
            this.battleString = activePlayer.name() + " win!";
        }

        public Player handleGrid(int x, int y) {
            return this.activePlayer;
        }

        public BattleState handleNext() {
            return this;
        }
    } 
}
