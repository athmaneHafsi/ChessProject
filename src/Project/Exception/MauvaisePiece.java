package Project.Exception;
public class MauvaisePiece extends Exception {
    
    public static final String message = new String("Attention la Piece que vous essayez de bouger ne vous appartiens pas !!");
     public MauvaisePiece() {
        super(message);
    }
}
