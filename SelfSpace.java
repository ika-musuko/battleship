// SelfSpace is an enum (like int) which represents a single space on selfBoard
// there are three possible values:
//      SelfSpace.EMPTY = nothing here
//      SelfSpace.SHIP  = a ship occupies this space (element in Player.selfGrid)
//      SelfSpace.DESTROYED = part of ship which has been destroyed is in this space 
//
//      be sure to only reduce the total number of ships if and only if ALL spaces in a ship have been set to SelfSpace.DESTROYED!

// set spaces with ships to SelfSpace.SHIP during setup mode
// set to SelfSpace.DESTROYED if opponent attacks successfully 
public enum SelfSpace {
    EMPTY,
    SHIP,
    DESTROYED
}
