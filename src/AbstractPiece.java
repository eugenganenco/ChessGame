import java.util.List;

public abstract class AbstractPiece {
    private final String color;
    private List<Integer> coordinates;

    public AbstractPiece(String color){
        this.color = color;
    }
    public String getColor(){
        return this.color;
    }
    public List<List<Integer>> getPossibleAttacks(BoardModel boardModel,List<Integer> currentCoordinates){
        return getLegalMoves(boardModel,currentCoordinates);
    }
    public abstract List<List<Integer>> getLegalMoves(BoardModel boardModel,List<Integer> currentCoordinates);
    public void changeState(BoardModel boardModel,List<Integer> currentCoordinates){
    }
    public void updateCoordinates(List<Integer> currentCoordinates){
        coordinates = currentCoordinates;
    }
    public List<Integer> getCoordinates(){
        return coordinates;
    }






}
