import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


//extend means we are creating a new class named SnakeGame that will take on the properties of Jpanel
public class SnakeGame extends JPanel {

    int BoardWidth; //member of snameGame class
    int BoardHeight;

    SnakeGame(int BoardWidth,int BoardHeight){
        this.BoardWidth =  BoardWidth;
        this.BoardHeight = BoardHeight;
        setPreferredSize(new Dimension(this.BoardWidth,this.BoardHeight));
        setBackground(Color.black);
    }

}
