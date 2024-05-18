import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


//extend means we are creating a new class named SnakeGame that will take on the properties of Jpanel
public class SnakeGame extends JPanel implements ActionListener,KeyListener {



    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1){
            velocityX = 0;
            velocityY = -1;
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1){
            velocityX = 0;
            velocityY = 1;
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1){
            velocityX = 1;
            velocityY = 0;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1){
            velocityX = -1;
            velocityY = 0;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

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
    int titleSize = 25; // 25px
    int velocityX =0;
    int velocityY= 0;

    boolean gameOver = false;

    //snakeHead
    Tile snakeHead;
    ArrayList<Tile>snakeBody;
    //Egg
    Tile egg;

    Random random;
    Timer gameloop;

    SnakeGame(int BoardWidth,int BoardHeight){
        this.BoardWidth =  BoardWidth;
        this.BoardHeight = BoardHeight;
        setPreferredSize(new Dimension(this.BoardWidth,this.BoardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);
        snakeHead = new Tile(5,5); //default starting point
        snakeBody = new ArrayList<Tile>(); //to store body parts
        egg = new Tile(15,15);
        random = new Random();
        placeEgg();

        gameloop = new Timer(100,this);
        gameloop.start();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        //grid
        for (int i =0 ; i < BoardWidth/titleSize ; i++){
//            g.setColor(Color.cyan);
//            g.drawLine(i*titleSize,0,i*titleSize,BoardHeight);
//            g.drawLine(0,i*titleSize,BoardWidth,i*titleSize);
//            g.drawLine(0, 25, 600, 25); 0 = starting point  600 = end point ,, y = height
//            g.drawLine(25,0,25,600);
        }

        //snake
        g.setColor(Color.green);
        //125 125px
        g.fill3DRect(snakeHead.x * titleSize,snakeHead.y * titleSize,titleSize,titleSize,true); //titleszie width & height

        g.setColor(Color.white);
        g.fillOval(egg.x*titleSize,egg.y*titleSize,titleSize,titleSize);

        //snake body
        for(int i =0 ; i < snakeBody.size() ; i++ ){
            Tile snakePart = snakeBody.get(i);
            g.setColor(Color.green);
            g.fill3DRect(snakePart.x*titleSize,snakePart.y*titleSize,titleSize,titleSize,true);
        }

        //score
        g.setFont(new Font("Arial",Font.BOLD,16));
        if(gameOver){
            g.setColor(Color.red);
            g.drawString("Game Over : "+ String.valueOf(snakeBody.size()),titleSize-16,titleSize);
        }else{
            g.drawString("Score "+ String.valueOf(snakeBody.size()),titleSize-16,titleSize );
        }
    }

    public void placeEgg(){
        egg.x = random.nextInt(BoardWidth/titleSize);
        egg.y = random.nextInt(BoardHeight/titleSize);

    }



    public boolean collision(Tile tile1,Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y;
     }

    public void move(){
        if(collision(snakeHead,egg)) {
            snakeBody.add(new Tile(egg.x,egg.y));
            placeEgg();
        }

        for(int i = snakeBody.size()-1; i >= 0 ; i-- ){
            Tile snakePart = snakeBody.get(i);
            if(i == 0){
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }else{
                Tile prevSnakePart = snakeBody.get(i-1);
                snakePart.x =prevSnakePart.x;
                snakePart.y =prevSnakePart.y;
            }
        }


        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        //game over
        for(int i =0 ;i <snakeBody.size() ; i++){
            Tile snakePart = snakeBody.get(i);
            if(collision(snakeHead,snakePart)){
                gameOver = true;
            }
        }

        if(snakeHead.x*titleSize < 0 || snakeHead.x*titleSize > BoardWidth ||
            snakeHead.y*titleSize <0 || snakeHead.y*titleSize > BoardHeight
        ){
            gameOver =true;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver){
            gameloop.stop();
            JOptionPane.showMessageDialog(null,"Game over");
        }


    }


}
