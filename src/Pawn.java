import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece{
    private boolean movedOnce;

    public Pawn(String color) {
        super(color);
        movedOnce = false;

    }


    public List<List<Integer>> getLegalMoves(BoardModel boardModel, List<Integer> currentCoordinates) {
        List<List<Integer>> legalMoves = new ArrayList<>();
        if (this.getColor().equals("b")){
            if (currentCoordinates.get(0)+1 < 8) {
                List<Integer> legalMove = new ArrayList<>();
                legalMove.add(currentCoordinates.get(0) + 1);
                legalMove.add(currentCoordinates.get(1));
                if (boardModel.getPieceAt(legalMove) == null) legalMoves.add(legalMove);

                if (!this.movedOnce) {
                    legalMove = new ArrayList<>();
                    legalMove.add(currentCoordinates.get(0) + 2);
                    legalMove.add(currentCoordinates.get(1));
                    if (boardModel.getPieceAt(legalMove) == null) legalMoves.add(legalMove);
                }

                if (currentCoordinates.get(1)+1 < 8) {
                    legalMove = new ArrayList<>();
                    legalMove.add(currentCoordinates.get(0) + 1);
                    legalMove.add(currentCoordinates.get(1) + 1);
                    if (boardModel.getPieceAt(legalMove) != null)
                        if (!boardModel.getPieceAt(legalMove).getColor().equals(this.getColor()))
                            legalMoves.add(legalMove);
                }

                if (currentCoordinates.get(1)-1 >= 0) {
                    legalMove = new ArrayList<>();
                    legalMove.add(currentCoordinates.get(0) + 1);
                    legalMove.add(currentCoordinates.get(1) - 1);
                    if (boardModel.getPieceAt(legalMove) != null)
                        if (!boardModel.getPieceAt(legalMove).getColor().equals(this.getColor()))
                            legalMoves.add(legalMove);
                }
            }
        }
        else {
            if (currentCoordinates.get(0)-1 >= 0) {
                List<Integer> legalMove = new ArrayList<>();
                legalMove.add(currentCoordinates.get(0) - 1);
                legalMove.add(currentCoordinates.get(1));
                if (boardModel.getPieceAt(legalMove) == null) legalMoves.add(legalMove);

                if (!this.movedOnce) {
                    legalMove = new ArrayList<>();
                    legalMove.add(currentCoordinates.get(0) - 2);
                    legalMove.add(currentCoordinates.get(1));
                    if (boardModel.getPieceAt(legalMove) == null) legalMoves.add(legalMove);
                }

                if (currentCoordinates.get(1)-1 >= 0) {
                    legalMove = new ArrayList<>();
                    legalMove.add(currentCoordinates.get(0) - 1);
                    legalMove.add(currentCoordinates.get(1) - 1);
                    if (boardModel.getPieceAt(legalMove) != null)
                        if (!boardModel.getPieceAt(legalMove).getColor().equals(this.getColor()))
                            legalMoves.add(legalMove);
                }

                if (currentCoordinates.get(1)+1 < 8) {
                    legalMove = new ArrayList<>();
                    legalMove.add(currentCoordinates.get(0) - 1);
                    legalMove.add(currentCoordinates.get(1) + 1);
                    if (boardModel.getPieceAt(legalMove) != null)
                        if (!boardModel.getPieceAt(legalMove).getColor().equals(this.getColor()))
                            legalMoves.add(legalMove);
                }
            }
        }
        return legalMoves;
    }
    public List<List<Integer>> getPossibleAttacks(BoardModel boardModel,List<Integer> currentCoordinates){
        List<List<Integer>> possibleAttacks = new ArrayList<>(this.getLegalMoves(boardModel, currentCoordinates));
        if (this.getColor().equals("b")){
            if (currentCoordinates.get(0)+1 < 8 && currentCoordinates.get(1)+1 < 8) {
                List<Integer> possibleAttack = new ArrayList<>();
                possibleAttack.add(currentCoordinates.get(0) + 1);
                possibleAttack.add(currentCoordinates.get(1) + 1);
                if (!possibleAttacks.contains(possibleAttack)) possibleAttacks.add(possibleAttack);
            }
            if (currentCoordinates.get(0)+1 < 8 && currentCoordinates.get(1)-1 >= 0) {
                List<Integer> possibleAttack = new ArrayList<>();
                possibleAttack.add(currentCoordinates.get(0) + 1);
                possibleAttack.add(currentCoordinates.get(1) - 1);
                if (!possibleAttacks.contains(possibleAttack)) possibleAttacks.add(possibleAttack);
            }
        }
        else {
            if (currentCoordinates.get(0)-1 >= 0 && currentCoordinates.get(1)+1 < 8) {
                List<Integer> possibleAttack = new ArrayList<>();
                possibleAttack.add(currentCoordinates.get(0) - 1);
                possibleAttack.add(currentCoordinates.get(1) + 1);
                if (!possibleAttacks.contains(possibleAttack)) possibleAttacks.add(possibleAttack);
            }
            if (currentCoordinates.get(0)-1 >= 0 && currentCoordinates.get(1)-1 >= 0) {
                List<Integer> possibleAttack = new ArrayList<>();
                possibleAttack.add(currentCoordinates.get(0) - 1);
                possibleAttack.add(currentCoordinates.get(1) - 1);
                if (!possibleAttacks.contains(possibleAttack)) possibleAttacks.add(possibleAttack);
            }

        }
        return possibleAttacks;

    }


    public void changeState(BoardModel boardModel,List<Integer> currentCoordinates) {
        if (!movedOnce) movedOnce = true;
        if (this.getColor().equals("w") && currentCoordinates.get(0)== 0 ||
        this.getColor().equals("b") && currentCoordinates.get(0)== 7){
            System.out.println("upgrade requested");
            boardModel.requestPawnUpdate(currentCoordinates,this.getColor());
        }
    }




}
