import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Horse extends AbstractPiece{
    private final List<List<Integer>> movePatterns;
    public Horse(String color) {
        super(color);
        this.movePatterns = new ArrayList<>();
        movePatterns.add(new ArrayList<>(Arrays.asList(2,1)));
        movePatterns.add(new ArrayList<>(Arrays.asList(2,-1)));
        movePatterns.add(new ArrayList<>(Arrays.asList(-2,1)));
        movePatterns.add(new ArrayList<>(Arrays.asList(-2,-1)));
        movePatterns.add(new ArrayList<>(Arrays.asList(1,2)));
        movePatterns.add(new ArrayList<>(Arrays.asList(1,-2)));
        movePatterns.add(new ArrayList<>(Arrays.asList(-1,2)));
        movePatterns.add(new ArrayList<>(Arrays.asList(-1,-2)));



    }


    public List<List<Integer>> getLegalMoves(BoardModel boardModel, List<Integer> currentCoordinates) {
        List<List<Integer>> legalMoves = new ArrayList<>();
        for (List<Integer> movePattern : movePatterns){
            if (currentCoordinates.get(0) + movePattern.get(0) < 8 && currentCoordinates.get(0) + movePattern.get(0) >= 0
                && currentCoordinates.get(1) + movePattern.get(1) < 8 && currentCoordinates.get(1) + movePattern.get(1) >= 0){
                List<Integer> newCoordinate = new ArrayList<>();
                newCoordinate.add(currentCoordinates.get(0) + movePattern.get(0));
                newCoordinate.add(currentCoordinates.get(1) + movePattern.get(1));
                if (boardModel.getPieceAt(newCoordinate) == null)
                    legalMoves.add(newCoordinate);
                else if (!boardModel.getPieceAt(newCoordinate).getColor().equals(this.getColor()))
                    legalMoves.add(newCoordinate);
            }

        }
        return legalMoves;
    }





}
