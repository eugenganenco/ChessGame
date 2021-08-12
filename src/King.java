import java.util.ArrayList;
import java.util.List;

public class King extends AbstractPiece{
    private boolean hasMoved;
    public King(String color) {
        super(color);
        hasMoved = false;
    }

    public List<List<Integer>> getLegalMoves(BoardModel boardModel, List<Integer> currentCoordinates) {

        List<List<Integer>> legalMoves = new ArrayList<>();
        if (currentCoordinates.get(0) + 1 < 8){
            List<Integer> move = new ArrayList<>();
            move.add(currentCoordinates.get(0)+1);
            move.add((currentCoordinates.get(1)));
            if (this.moveIsLegal(move,boardModel))  legalMoves.add(move);
        }
        if (currentCoordinates.get(0) - 1 >= 0){
            List<Integer> move = new ArrayList<>();
            move.add(currentCoordinates.get(0)-1);
            move.add((currentCoordinates.get(1)));
            if (this.moveIsLegal(move,boardModel))  legalMoves.add(move);
        }
        if (currentCoordinates.get(1) + 1 < 8){
            List<Integer> move = new ArrayList<>();
            move.add(currentCoordinates.get(0));
            move.add((currentCoordinates.get(1)+1));
            if (this.moveIsLegal(move,boardModel))  legalMoves.add(move);
        }
        if (currentCoordinates.get(1) - 1 >= 0){
            List<Integer> move = new ArrayList<>();
            move.add(currentCoordinates.get(0));
            move.add((currentCoordinates.get(1)-1));
            if (this.moveIsLegal(move,boardModel))  legalMoves.add(move);
        }
        if (currentCoordinates.get(0) + 1 < 8 && currentCoordinates.get(1) + 1 < 8){
            List<Integer> move = new ArrayList<>();
            move.add(currentCoordinates.get(0)+1);
            move.add((currentCoordinates.get(1)+1));
            if (this.moveIsLegal(move,boardModel))  legalMoves.add(move);
        }
        if (currentCoordinates.get(0) - 1 >= 0 && currentCoordinates.get(1) - 1 >= 0){
            List<Integer> move = new ArrayList<>();
            move.add(currentCoordinates.get(0)-1);
            move.add((currentCoordinates.get(1)-1));
            if (this.moveIsLegal(move,boardModel))  legalMoves.add(move);
        }
        if (currentCoordinates.get(0) + 1 < 8 && currentCoordinates.get(1) - 1 >= 0){
            List<Integer> move = new ArrayList<>();
            move.add(currentCoordinates.get(0)+1);
            move.add((currentCoordinates.get(1)-1));
            if (this.moveIsLegal(move,boardModel))  legalMoves.add(move);
        }
        if (currentCoordinates.get(0) - 1 >= 0 && currentCoordinates.get(1) + 1 < 8){
            List<Integer> move = new ArrayList<>();
            move.add(currentCoordinates.get(0)-1);
            move.add((currentCoordinates.get(1)+1));
            if (this.moveIsLegal(move,boardModel))  legalMoves.add(move);
        }

        return legalMoves;



    }
    private boolean moveIsLegal(List<Integer> move, BoardModel boardModel){
        boolean flag = false;
        if (boardModel.getPieceAt(move) != null) {
            if (!boardModel.getPieceAt(move).getColor().equals(this.getColor()))  flag = true;
        }
        else if (!boardModel.getCellAt(move).isAttackedByOppositeColor(this.getColor())) {
            flag = true;
        }

        return flag;
    }


    public void changeState(BoardModel boardModel,List<Integer> currentCoordinates) {
        if (!hasMoved) hasMoved = true;
    }


}
