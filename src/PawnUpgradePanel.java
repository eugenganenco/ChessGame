import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PawnUpgradePanel extends JPanel {
    private final BoardPanel boardPanel;
    private final JLabel message;
    public PawnUpgradePanel(String pawnColor,BoardPanel boardPanel){

        this.boardPanel = boardPanel;
        this.setLayout(new BorderLayout());
        message = new JLabel("Select the promotion for the pawn");
        this.add(message, BorderLayout.SOUTH);
        JPanel upgradesPanel = this.getUpgradesPanel(pawnColor);
        upgradesPanel.revalidate();
        upgradesPanel.repaint();
        upgradesPanel.setVisible(true);
        this.add(upgradesPanel,BorderLayout.NORTH);
        this.setVisible(true);
        this.revalidate();
        this.repaint();


    }
    private JPanel getUpgradesPanel(String color){
        JPanel upgradesPanel = new JPanel();
        upgradesPanel.setLayout(new FlowLayout());

        JLabel horseLabel = new JLabel();
        horseLabel.addMouseListener(new UpgradePanelMouseListener(boardPanel,"h",this));
        horseLabel.setIcon(new ImageIcon(color+"h"+".png"));
        upgradesPanel.add(horseLabel);
        horseLabel.setVisible(true);
        horseLabel.revalidate();

        JLabel bishopLabel = new JLabel();
        bishopLabel.addMouseListener(new UpgradePanelMouseListener(boardPanel,"b",this));
        bishopLabel.setIcon(new ImageIcon(color+"b"+".png"));
        upgradesPanel.add(bishopLabel);
        bishopLabel.setVisible(true);
        bishopLabel.revalidate();


        JLabel rookLabel = new JLabel();
        rookLabel.addMouseListener(new UpgradePanelMouseListener(boardPanel,"r",this));
        rookLabel.setIcon(new ImageIcon(color+"r"+".png"));
        upgradesPanel.add(rookLabel);
        rookLabel.setVisible(true);
        rookLabel.revalidate();

        JLabel queenLabel = new JLabel();
        queenLabel.addMouseListener(new UpgradePanelMouseListener(boardPanel,"q",this));
        queenLabel.setIcon(new ImageIcon(color+"q"+".png"));
        upgradesPanel.add(queenLabel);
        queenLabel.setVisible(true);
        queenLabel.revalidate();

        upgradesPanel.setBorder(new LineBorder(Color.BLACK,1));
        return upgradesPanel;

    }
    public void updateMessage(String pieceString){
        message.setText("The pawn will be upgraded to "+ pieceString);
    }
}
