import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook extends AbstractPiece{
    private boolean hasMoved;
    public Rook(String color) {
        super(color);

    }

    public List<List<Integer>> getLegalMoves(BoardModel boardModel, List<Integer> currentCoordinates) {
        List<List<Integer>> legalMoves = new ArrayList<>();

        int rowIncrement = 1;
        while (currentCoordinates.get(0) + rowIncrement < 8) {
            List<Integer> legalMove = new ArrayList<>();
            legalMove.add(currentCoordinates.get(0) + rowIncrement);
            legalMove.add(currentCoordinates.get(1));
            if (boardModel.getPieceAt(legalMove) != null) {
                if (!boardModel.getPieceAt(legalMove).getColor().equals(this.getColor())) legalMoves.add(legalMove);
                break;
            }
            else legalMoves.add(legalMove);
            rowIncrement++;
        }

        rowIncrement = -1;
        while (currentCoordinates.get(0) + rowIncrement >= 0) {
            List<Integer> legalMove = new ArrayList<>();
            legalMove.add(currentCoordinates.get(0) + rowIncrement);
            legalMove.add(currentCoordinates.get(1));
            if (boardModel.getPieceAt(legalMove) != null) {
                if (!boardModel.getPieceAt(legalMove).getColor().equals(this.getColor())) legalMoves.add(legalMove);
                break;
            }
            else legalMoves.add(legalMove);
            rowIncrement--;
        }

        int columnIncrement = 1;
        while (currentCoordinates.get(1) + columnIncrement < 8) {
            List<Integer> legalMove = new ArrayList<>();
            legalMove.add(currentCoordinates.get(0));
            legalMove.add(currentCoordinates.get(1)+columnIncrement);
            if (boardModel.getPieceAt(legalMove) != null) {
                if (!boardModel.getPieceAt(legalMove).getColor().equals(this.getColor())) legalMoves.add(legalMove);
                break;
            }
            else legalMoves.add(legalMove);
            columnIncrement++;
        }

        columnIncrement = -1;
        while (currentCoordinates.get(1) + columnIncrement >= 0) {
            List<Integer> legalMove = new ArrayList<>();
            legalMove.add(currentCoordinates.get(0));
            legalMove.add(currentCoordinates.get(1)+columnIncrement);
            if (boardModel.getPieceAt(legalMove) != null) {
                if (!boardModel.getPieceAt(legalMove).getColor().equals(this.getColor())) legalMoves.add(legalMove);
                break;
            }
            else legalMoves.add(legalMove);
            columnIncrement--;
        }
        return legalMoves;

    }


    public void changeState() {
        if (!hasMoved) hasMoved = true;
    }


}
