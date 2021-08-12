import java.util.ArrayList;
import java.util.List;

public class BoardModel {
    private final Cell[][] cellList;
    private boolean pawnUpgradeRequested;
    private List<Integer> pawnUpgradeCoordinates;
    private String pawnUpgradeColor;
    private AbstractPiece whiteKing;
    private AbstractPiece blackKing;
    private String playerToMoveColor;


    public BoardModel(){
        playerToMoveColor = "w";
        pawnUpgradeRequested = false;
        cellList = new Cell[8][8];
        for (int i = 0; i<8; i++)
            for (int j = 0; j<8; j++){
                cellList[i][j] = new Cell();
            }

        cellList[0][0].setPiece(new Rook("b"));
        cellList[0][1].setPiece(new Horse("b"));
        cellList[0][2].setPiece(new Bishop("b"));
        cellList[0][3].setPiece(new Queen("b"));
        // black king initialization
        blackKing = new King("b");
        List<Integer> coordinates = new ArrayList<>();
        coordinates.add(0);
        coordinates.add(4);
        blackKing.updateCoordinates(coordinates);
        cellList[0][4].setPiece(blackKing);
        cellList[0][5].setPiece(new Bishop("b"));
        cellList[0][6].setPiece(new Horse("b"));
        cellList[0][7].setPiece(new Rook("b"));


        for (int i = 0; i < 8; i++){
            cellList[1][i].setPiece(new Pawn("b"));
        }
        for (int i = 0; i < 8; i++){
            cellList[6][i].setPiece(new Pawn("w"));
        }
        cellList[7][0].setPiece(new Rook("w"));
        cellList[7][1].setPiece(new Horse("w"));
        cellList[7][2].setPiece(new Bishop("w"));
        cellList[7][3].setPiece(new Queen("w"));
        // white king initialization
        whiteKing = new King("w");
        coordinates = new ArrayList<>();
        coordinates.add(7);
        coordinates.add(4);
        whiteKing.updateCoordinates(coordinates);
        cellList[7][4].setPiece(whiteKing);
        cellList[7][5].setPiece(new Bishop("w"));
        cellList[7][6].setPiece(new Horse("w"));
        cellList[7][7].setPiece(new Rook("w"));




    }
    public AbstractPiece getPieceAt(List<Integer> coordinates){
        return cellList[coordinates.get(0)][coordinates.get(1)].getPiece();
    }
    public Cell getCellAt(List<Integer> coordinates){
        return cellList[coordinates.get(0)][coordinates.get(1)];
    }
    public void setPiece(List<Integer> coordinates,AbstractPiece piece){
        cellList[coordinates.get(0)][coordinates.get(1)].setPiece(piece);
    }
    public void deletePieceAt(List<Integer> coordinates){
        cellList[coordinates.get(0)][coordinates.get(1)].setPiece(null);
    }
    public void requestPawnUpdate(List<Integer> currentCoordinates,String color){
        pawnUpgradeRequested = true;
        pawnUpgradeCoordinates = currentCoordinates;
        pawnUpgradeColor = color;
    }
    public boolean pawnUpdateRequested(){
        return pawnUpgradeRequested;
    }
    public List<Integer> getPawnUpgradeCoordinates(){
        return pawnUpgradeCoordinates;
    }
    public String getPawnUpgradeColor(){
        return pawnUpgradeColor;
    }
    public void upgradePawn(String upgradedPieceString){
        pawnUpgradeRequested = false;
        AbstractPiece upgradedPiece;
        upgradedPiece = new Queen(pawnUpgradeColor);
        if (upgradedPieceString.equals("b")) upgradedPiece = new Bishop(pawnUpgradeColor);
        if (upgradedPieceString.equals("h")) upgradedPiece = new Horse(pawnUpgradeColor);
        if (upgradedPieceString.equals("r")) upgradedPiece = new Rook(pawnUpgradeColor);
        cellList[pawnUpgradeCoordinates.get(0)][pawnUpgradeCoordinates.get(1)].setPiece(upgradedPiece);
    }

    public boolean actualizeMove(List<Integer> initialCoordinates, List<Integer> newCoordinates){
        AbstractPiece pieceMoved = this.getPieceAt(initialCoordinates);
        if (pieceMoved.getLegalMoves(this,initialCoordinates).contains(newCoordinates)){
            this.setPiece(newCoordinates,pieceMoved);
            this.deletePieceAt(initialCoordinates);
            pieceMoved.changeState(this,newCoordinates);
            pieceMoved.updateCoordinates(newCoordinates);
            this.setCellsAttackStatusDefault();
            this.notifyAttackedCells();
            return true;
        }
        else return false;

    }
    public void notifyAttackedCells(){
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (cellList[i][j].getPiece() != null){
                    List<Integer> pieceCoordinates = new ArrayList<>();
                    pieceCoordinates.add(i);
                    pieceCoordinates.add(j);
                    this.notifyAttackedCells(pieceCoordinates);
                }

    }
    private void notifyAttackedCells(List<Integer> pieceCoordinates){
        List<List<Integer>> possibleAttacks = cellList[pieceCoordinates.get(0)][pieceCoordinates.get(1)]
                .getPiece().getPossibleAttacks(this, pieceCoordinates);

        for (List<Integer> possibleAttack : possibleAttacks) {
            if (cellList[possibleAttack.get(0)][possibleAttack.get(1)].getPiece() != null) {
                if (!cellList[pieceCoordinates.get(0)][pieceCoordinates.get(1)].getPiece().getColor()
                        .equals(cellList[possibleAttack.get(0)][possibleAttack.get(1)].getPiece().getColor())) {
                    cellList[possibleAttack.get(0)][possibleAttack.get(1)].setIsAttackedBy
                            (cellList[pieceCoordinates.get(0)][pieceCoordinates.get(1)].getPiece().getColor());
                }
            }
            else
                cellList[possibleAttack.get(0)][possibleAttack.get(1)].setIsAttackedBy(cellList[pieceCoordinates.get(0)][pieceCoordinates.get(1)].getPiece().getColor());
        }
    }


    private void setCellsAttackStatusDefault(){
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++){
                cellList[i][j].noLongerAttackedBy("w");
                cellList[i][j].noLongerAttackedBy("b");
            }
    }
    private boolean whiteKingIsChecked(){
        return this.getCellAt(whiteKing.getCoordinates()).isAttackedByBlack();
    }
    private boolean blackKingIsChecked(){
        return this.getCellAt(blackKing.getCoordinates()).isAttackedByWhite();
    }
    public boolean oneKingIsChecked(){
        return this.blackKingIsChecked() || this.whiteKingIsChecked();
    }
    private List<Integer> getWhiteKingCoordinates() {
        return whiteKing.getCoordinates();
    }
    private List<Integer> getBlackKingCoordinates(){
        return blackKing.getCoordinates();
    }
    public  List<Integer> getCheckedKingCoordinates(){
        if (this.blackKingIsChecked()) return this.getBlackKingCoordinates();
        return this.getWhiteKingCoordinates();
    }


    public String getPlayerToMoveColor() {
        return playerToMoveColor;
    }

    public void switchPlayerToMoveColor() {
        if (this.getPlayerToMoveColor().equals("w")) this.playerToMoveColor = "b";
        else if (this.getPlayerToMoveColor().equals("b")) this.playerToMoveColor = "w";
    }


}
