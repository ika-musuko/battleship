// SelfSpace is an enum (like int) which represents a single space on attackBoard
// there are two possible values: 
//      AttackSpace.UNMARKED: the player has not tried to attack this square yet
//      AttackSpace.MARKED: the player has already tried attacking this square
public enum AttackSpace {
    UNMARKED,
    MARKED,
}