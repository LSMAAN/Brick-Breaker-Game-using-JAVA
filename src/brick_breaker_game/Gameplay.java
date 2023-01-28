package brick_breaker_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener,ActionListener {
    
    private boolean play=false;
    private int score=0;
    public String highscore="";
    private int totalBricks=21;
    
    private final Timer timer;
    private final int delay=10;
    
    private int playerX=310;
    
    private int ballposX=120;
    private int ballposY=350;
    
    private int ballXdir=-3;
    private int ballYdir=-3;
    
    private MapGenerator map;
    
    public Gameplay()
    {
        map=new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay, this);
        timer.start();
       
    }
    
    public void paint(Graphics g)
    {
        //background
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 692, 592);
        
        //draw map
        
     map.draw((Graphics2D)g);
        
        
        //border
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        
        
        //scores
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score, 590, 30);
        
        //highscores
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,20));
        g.drawString("Highscores :"+highscore, 80, 30);
        
        //paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);
        
        //ball
         g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);
       
 if(highscore.equals(""))
 {
  highscore=this.getHighscores();
 }
        if(totalBricks<=0)
        {
             play=false;    //game ends
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,40));
            g.drawString("YOU WON", 240, 300);
            
            
            g.setFont(new Font("serif",Font.BOLD,24));
            g.drawString("Press ENTER to Start", 220, 340);
            
        }
        
        
        if(ballposY > 570)
        {
           
            play=false;    //game ends
              //Checkscore();
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("GAME OVER Scores:"+score, 190, 300);
            
            
            g.setFont(new Font("serif",Font.BOLD,24));
            g.drawString("Press ENTER to Start", 220, 340);
                    
        }
        
        g.dispose();
        
        
    }
    
    public String getHighscores()
    {
        FileReader readFile=null;
        BufferedReader reader=null;
            
        try {
            readFile=new FileReader("highscore.dat");
            reader=new BufferedReader(readFile);
            return reader.readLine();
            
            
        } catch (IOException e) {
            return "Nobody:0";
        }
        finally
        {
            try {
                if(reader!=null)
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
              
        }    
    }
    public void Checkscore()
    {
        if(score>Integer.parseInt(highscore.split(":")[1]))
        {
            String name=JOptionPane.showInputDialog("You set a new highscore! What is your name? ");
            highscore=name+":"+score;
            
            File scoreFile=new File("highscore.dat"); 
            
            if(!scoreFile.exists())
            {
                try {
                    scoreFile.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            FileWriter writeFile=null;
            BufferedWriter writer=null;
            try {
                
                writeFile=new FileWriter(scoreFile);
                writer=new BufferedWriter(writeFile);
                writer.write(this.highscore);
                
            } catch (Exception e) {
            }
            finally
            {
                try {
                    
                    if(writer !=null)
                    {
                        writer.close();
                    }
                    
                    
                } catch (Exception e) {
                }
                   
            }
            
        }
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {  }

    @Override
    public void keyPressed(KeyEvent e) {
       
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            if(playerX>=600)
            {
                playerX=600;  //playerX should not move outside the border
            }
            else
            {
                moveRight();
            }
            
            
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            if(playerX<10)
            {
                playerX=10;  //playerX should not move outside the border
            }
            else
            {
                moveLeft();
            }
            
        }
        if(e.getKeyCode()== KeyEvent.VK_ENTER)
        { 
            Checkscore();
            if(!play)
                    {
                        // Checkscore();
                        play=true;
                        ballposX=120;
                        ballposY=350;
                        ballXdir=3;
                        ballYdir=3;
                        playerX=310;
                        score=0;
                        totalBricks=21;
                        map=new MapGenerator(3, 7);
                        repaint();
                    }
        }
     
    }
    
    public void moveRight()
    {
        play=true;     //game starts
        playerX+=20;
    }
    public void moveLeft()
    {
        play=true;     //game starts
        playerX-=20;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
       timer.start();
     //  Checkscore();
       if(play)
       {
           if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8)))
           {
               // Checkscore();
               ballYdir=-ballYdir;
           }
        
           A:   for(int i=0;i<map.map.length;i++)
           {
               for(int j=0;j<map.map[0].length;j++)
               {
                   if(map.map[i][j]>0)
                   {
                       // Checkscore();
                       int brickX=j*map.brickWidth+80;
                       int brickY=i*map.brickHeight+50;
                       int brickWidth=map.brickWidth;
                       int brickHeight=map.brickHeight;
                       
                       
                       Rectangle rect=new Rectangle(brickX, brickY, brickWidth, brickHeight);
                       Rectangle ballRect=new Rectangle(ballposX, ballposY, 20, 20);
                       Rectangle brickRect=rect;
                        if(ballRect.intersects(brickRect))
                       {
                           map.setBrickValue(0, i, j);
                           totalBricks--;
                           score+=5;
                          
                          //To resolve the problem of left and right intersection,following code is done
                           if(ballposX+19 <= brickRect.x   || ballposX+1 >=brickRect.x +brickRect.width)
                           {
                               ballXdir=-ballXdir;
                           }
                           else
                           {
                               ballYdir=-ballYdir;
                           }
                           
                           break A;// to exit the compiler from outer loop as well, we have used the BREAK LABEL
                            
                       }
                       
                   }
               }
           }
          //  Checkscore();
           ballposX+=ballXdir;
           ballposY+=ballYdir;
           if(ballposX<0)
           {
                
               ballXdir=-ballXdir;
           }
           if(ballposY<0)
           {
              
               ballYdir=-ballYdir;
           }
           if(ballposX>670)
           {
                
               ballXdir=-ballXdir;
           }
           
       }
       
       repaint();    //calling the paint method again
    }
    
}
