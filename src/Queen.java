import java.util.ArrayList;
import java.util.List;

public class Queen extends AbstractPiece{
    public Queen(String color) {
        super(color);
    }

    public List<List<Integer>> getLegalMoves(BoardModel boardModel, List<Integer> currentCoordinates) {
        List<List<Integer>> legalMoves = new ArrayList<>(new Rook(this.getColor()).getLegalMoves(boardModel, currentCoordinates));
        legalMoves.addAll(new Bishop(this.getColor()).getLegalMoves(boardModel,currentCoordinates));
        return legalMoves;
    }


    public void changeState() {

    }


}
