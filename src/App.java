import javax.swing.*;
public class App {
    public static void main(String[] args) throws Exception{

        int BoardWidth = 580;
        int BoardHeight = BoardWidth;

        JFrame jFrame = new JFrame("Snake Game");
        jFrame.setVisible(true);
        jFrame.setSize(BoardWidth,BoardHeight); //set width & heigth
        jFrame.setLocationRelativeTo(null); // window is centered
        jFrame.setResizable(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when user click exit button program terminated.

        SnakeGame snakeGame = new SnakeGame(BoardWidth,BoardHeight);
        jFrame.add(snakeGame);
        jFrame.pack(); //means height eka dunnata title bar eken e kotasa adu wenwa eka nati kirimata pack() use karanwa
        snakeGame.requestFocus();


    }
}