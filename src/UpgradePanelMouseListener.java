import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpgradePanelMouseListener extends MouseAdapter {
    BoardPanel boardPanel;
    String pieceString;
    PawnUpgradePanel pawnUpgradePanel;
    public UpgradePanelMouseListener(BoardPanel boardPanel,String pieceString,PawnUpgradePanel pawnUpgradePanel){
        this.boardPanel = boardPanel;
        this.pieceString = pieceString;
        this.pawnUpgradePanel = pawnUpgradePanel;
    }
    public void mouseClicked(MouseEvent e){
        boardPanel.upgradePawnTo(pieceString);
        if (pieceString.equals("b")) pawnUpgradePanel.updateMessage("bishop");
        if (pieceString.equals("h")) pawnUpgradePanel.updateMessage("horse");
        if (pieceString.equals("q")) pawnUpgradePanel.updateMessage("queen");
        if (pieceString.equals("r")) pawnUpgradePanel.updateMessage("rook");
    }

}
