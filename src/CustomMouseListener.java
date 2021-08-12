import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CustomMouseListener extends MouseAdapter{
    private final CellPanel cellPanel;
    private final BoardPanel boardPanel;
    private final static BoardModel boardModel = new BoardModel();
    public CustomMouseListener(CellPanel cellPanel,BoardPanel boardPanel){
        this.cellPanel = cellPanel;
        this.boardPanel = boardPanel;
    }
    public void mouseClicked(MouseEvent e) {
        System.out.println(boardModel.getPlayerToMoveColor() + " has to make a move");
        System.out.println("Mouse clicked");
        if (boardPanel.getSelectedCellPanel() != null) this.moveClickedHelper();
        else if (boardModel.getPlayerToMoveColor().equals(boardModel.getPieceAt(cellPanel.getCoordinates()).getColor())) {
            this.moveClickedHelper();
        }
    }

    private void moveClickedHelper(){
            if (boardPanel.getSelectedCellPanel() == null) {
                if (cellPanel.getCurrentPieceLabel() != null) {
                    boardPanel.selectCellPanel(cellPanel);
                    System.out.println("Selected");
                    List<List<Integer>> legalMoves = boardModel.getPieceAt(cellPanel.getCoordinates()).getLegalMoves(boardModel,
                            boardPanel.getSelectedCellPanel().getCoordinates());
                    boardPanel.highlightLegalMoves(legalMoves);
                }
            }
            else {
                if (boardPanel.getSelectedCellPanel() != cellPanel) {
                    if (boardModel.actualizeMove(boardPanel.getSelectedCellPanel().getCoordinates(), cellPanel.getCoordinates())) {
                        cellPanel.setCurrentPieceLabel(boardPanel.getSelectedCellPanel().getCurrentPieceLabel());
                        boardPanel.getSelectedCellPanel().deleteCurrentPieceLabel();
                        boardPanel.unselectCellPanel();
                        if (boardModel.pawnUpdateRequested()) {
                            boardPanel.askPlayerToUpgradePawn(boardModel.getPawnUpgradeColor());
                            boardModel.upgradePawn(boardPanel.getPawnUpgrade());
                            boardPanel.setLabel(boardPanel.getPawnUpgrade(), boardModel.getPawnUpgradeColor(), boardModel.getPawnUpgradeCoordinates());
                            boardModel.notifyAttackedCells();
                        }
                        if (boardModel.oneKingIsChecked()){
                            System.out.println("King is checked");
                            boardPanel.getCellPanelAt(boardModel.getCheckedKingCoordinates()).setKingIsCheckedBackground();
                        }
                        boardModel.switchPlayerToMoveColor();
                    }
                }
                boardPanel.unhighlight();
                boardPanel.unselectCellPanel();
                System.out.println("Unselected");

            }

        }




}
