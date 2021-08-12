import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class BoardPanel extends JPanel {
    private final CellPanel[][] cellPanelList;
    private CellPanel selectedCellPanel;
    private String upgrade;

    public BoardPanel() {

        upgrade = null;
        this.setLayout(new GridLayout(8,8));
        selectedCellPanel = null;
        cellPanelList = new CellPanel[8][8];

        for (int i = 0; i<8; i++)
            for (int j = 0; j<8; j++){
                CellPanel cellPanel = new CellPanel(i,j,this);
                this.add(cellPanel);
                cellPanelList[i][j] = cellPanel;
        }

        cellPanelList[0][0].setCurrentPieceLabel(new JLabel(new ImageIcon("b"+"r.png")));
        cellPanelList[0][1].setCurrentPieceLabel(new JLabel(new ImageIcon("b"+"h.png")));
        cellPanelList[0][2].setCurrentPieceLabel(new JLabel(new ImageIcon("b"+"b.png")));
        cellPanelList[0][3].setCurrentPieceLabel(new JLabel(new ImageIcon("b"+"q.png")));
        cellPanelList[0][4].setCurrentPieceLabel(new JLabel(new ImageIcon("b"+"k.png")));
        cellPanelList[0][5].setCurrentPieceLabel(new JLabel(new ImageIcon("b"+"b.png")));
        cellPanelList[0][6].setCurrentPieceLabel(new JLabel(new ImageIcon("b"+"h.png")));
        cellPanelList[0][7].setCurrentPieceLabel(new JLabel(new ImageIcon("b"+"r.png")));



        for (int i = 0; i < 8; i++){
            cellPanelList[1][i].setCurrentPieceLabel(new JLabel(new ImageIcon("b"+"p.png")));
        }
        for (int i = 0; i < 8; i++){
            cellPanelList[6][i].setCurrentPieceLabel(new JLabel(new ImageIcon("w"+"p.png")));
        }
        cellPanelList[7][0].setCurrentPieceLabel(new JLabel(new ImageIcon("w"+"r.png")));
        cellPanelList[7][1].setCurrentPieceLabel(new JLabel(new ImageIcon("w"+"h.png")));
        cellPanelList[7][2].setCurrentPieceLabel(new JLabel(new ImageIcon("w"+"b.png")));
        cellPanelList[7][3].setCurrentPieceLabel(new JLabel(new ImageIcon("w"+"q.png")));
        cellPanelList[7][4].setCurrentPieceLabel(new JLabel(new ImageIcon("w"+"k.png")));
        cellPanelList[7][5].setCurrentPieceLabel(new JLabel(new ImageIcon("w"+"b.png")));
        cellPanelList[7][6].setCurrentPieceLabel(new JLabel(new ImageIcon("w"+"h.png")));
        cellPanelList[7][7].setCurrentPieceLabel(new JLabel(new ImageIcon("w"+"r.png")));
    }

    public void selectCellPanel(CellPanel cellPanel){
        this.selectedCellPanel = cellPanel;
    }
    public CellPanel getSelectedCellPanel(){
        return this.selectedCellPanel;
    }
    public void unselectCellPanel(){
        this.selectedCellPanel = null;
    }
    public void highlightLegalMoves(List<List<Integer>> legalMoves){
        for (List<Integer> legalMove : legalMoves) {
            cellPanelList[legalMove.get(0)][legalMove.get(1)].highlight();
        }
    }
    public void unhighlight(){
        for (int i = 0; i<8; i++)
            for(int j = 0; j<8; j++)
                cellPanelList[i][j].unhighlight();
    }
    public void askPlayerToUpgradePawn(String pawnColor){
        JOptionPane.showMessageDialog(null,new PawnUpgradePanel(pawnColor,this));
    }
    public void upgradePawnTo(String upgrade){
        this.upgrade = upgrade;
    }
    public String getPawnUpgrade(){
        return upgrade;
    }
    public void setLabel(String labelString,String colorString, List<Integer> labelCoordinates){
        cellPanelList[labelCoordinates.get(0)][labelCoordinates.get(1)].setCurrentPieceLabel(new JLabel(new ImageIcon(colorString+labelString+".png")));
    }
    public CellPanel getCellPanelAt(List<Integer> coordinates){
        return cellPanelList[coordinates.get(0)][coordinates.get(1)];
    }


}
