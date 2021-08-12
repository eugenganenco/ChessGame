public class Cell {
    private boolean attackedByBlack;
    private boolean attackedByWhite;
    private AbstractPiece piece;
    public Cell(){
        attackedByBlack = false;
        attackedByWhite = false;
        this.piece = null;
    }

    public void setPiece(AbstractPiece piece){
        this.piece = piece;
    }
    public AbstractPiece getPiece(){
        return this.piece;
    }
    public void setIsAttackedBy(String color){
        //System.out.println("Cell is being attacked by "+color);
        if (color.equals("w")) attackedByWhite = true;
        if (color.equals("b")) attackedByBlack = true;

    }


    public void noLongerAttackedBy(String color){
        //System.out.println("Cell is no longer attacked by "+color);
        if (color.equals("w")) attackedByWhite = false;
        if (color.equals("b")) attackedByBlack = false;
    }

    public boolean isAttackedByWhite() {
        return attackedByWhite;
    }

    public boolean isAttackedByBlack(){ return  attackedByBlack;}
    public boolean isAttackedByOppositeColor(String color){
        if (isAttackedByWhite() && color.equals("b")) return true;
        return isAttackedByBlack() && color.equals("w");
    }
}
