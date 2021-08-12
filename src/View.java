import javax.swing.*;
import java.awt.*;

public class View extends JFrame{
    private BoardPanel boardPanel;
    public View(){
        this.addComponentListener(new CustomComponentListener());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1,1));
        this.setSize(500,500);
        boardPanel = new BoardPanel();
        this.add(boardPanel);
        this.setVisible(true);

    }



}
