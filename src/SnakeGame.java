import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


//extend means we are creating a new class named SnakeGame that will take on the properties of Jpanel
public class SnakeGame extends JPanel {

    private class Tile{
        int x;
        int y;

        Tile(int x,int y){
            this.x = x;
            this.y = y;
        }


    }

    int BoardWidth; //member of snameGame class
    int BoardHeight;
    int titleSize = 10; // 25px
    Tile snakeHead;
    SnakeGame(int BoardWidth,int BoardHeight){
        this.BoardWidth =  BoardWidth;
        this.BoardHeight = BoardHeight;
        setPreferredSize(new Dimension(this.BoardWidth,this.BoardHeight));
        setBackground(Color.black);
        snakeHead = new Tile(100,100); //default starting point
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        //snake
        g.setColor(Color.green);
        g.fillOval(snakeHead.x,snakeHead.y,titleSize,titleSize); //titleszie width & height
    }

}
