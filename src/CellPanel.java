import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CellPanel extends JPanel {
    private final BoardPanel boardPanel;
    private final Integer row;
    private final Integer column;
    private JLabel currentLabel;
    private final Color defaultColor;



    public CellPanel(Integer row, Integer col, BoardPanel boardPanel){
        this.boardPanel = boardPanel;
        this.row = row;
        this.column = col;
        this.setLayout(new BorderLayout());
        this.addMouseListener(new CustomMouseListener(this,this.boardPanel));
        if ((col+row) % 2 == 0) defaultColor = Color.white;
        else  defaultColor = Color.LIGHT_GRAY;
        this.setBackground(defaultColor);
    }

    public void deleteCurrentPieceLabel(){
        this.remove(currentLabel);
        currentLabel = null;
        revalidate();
        repaint();
    }
    public void setCurrentPieceLabel(JLabel  pieceLabel){
        if (currentLabel!=null) this.deleteCurrentPieceLabel();
        currentLabel = pieceLabel;
        this.add(currentLabel,BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    public JLabel getCurrentPieceLabel(){
        return this.currentLabel;
    }
    public List<Integer> getCoordinates(){List<Integer> coordinates = new ArrayList<>();
        coordinates.add(row);
        coordinates.add(column);
        return coordinates;
    }

    public void highlight(){
        this.setBackground(Color.BLUE);
    }
    public void unhighlight(){
        if (!this.getBackground().equals(Color.RED))
        this.setBackground(defaultColor);
    }
    public void setKingIsCheckedBackground(){
        System.out.println("King is checked");
        this.setBackground(Color.RED);

    }


}
