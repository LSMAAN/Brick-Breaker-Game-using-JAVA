
package brick_breaker_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Brick_Breaker_Game extends JFrame implements MouseListener{
    
    JFrame obj,obj2;
    //JButton startPlay;
    JPanel startPage;
    JLabel brickGame,highscores,startPlay;
    
    
    public Brick_Breaker_Game()
    { 
       obj=new JFrame();
       Gameplay gamePlay=new Gameplay();
       obj.setBounds(10,10,700,600);
       obj.setTitle("Breakout Brick");
       obj.setResizable(false);
       obj.setVisible(true);
       obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       obj.add(gamePlay);
       
       
       //-------Start Panel--------
       
       startPage=new JPanel();      
       startPage.setBounds(10,10,700,600);
       startPage.setBackground(Color.darkGray);
       startPage.setVisible(true);
       startPage.setLayout(null);
       obj.add(startPage);
       
        brickGame=new JLabel("BRICK BREAKER");  //Brick Game Label
        brickGame.setBounds(50, 50, 700,400);
       
       
        brickGame.setForeground(Color.CYAN);
        brickGame.setFont(new Font("Courier New", Font.BOLD, 80));
      startPage.add(brickGame);
      
       startPlay=new JLabel("Start");         // start game button
      startPlay.setBounds(290, 300, 190, 60);
      startPlay.setForeground(Color.orange);
     startPlay.setFont(new Font("Times New Roman",Font.BOLD, 50));
     startPage.add(startPlay);
     startPlay.addMouseListener(this);
      
    /*  highscores=new JLabel("Highscores");
        highscores.setBounds(270,280, 700,100);
        highscores.setForeground(Color.orange);
       highscores.setFont(new Font("Times New Roman", Font.BOLD, 30));
      startPage.add(highscores);
      highscores.addMouseListener(this);*/
      
      
        obj2=new JFrame();
         gamePlay=new Gameplay(); 
        obj2.setBounds(10,10,700,600);
        obj2.setBackground(Color.DARK_GRAY);
        obj2.setResizable(false);
        obj2.setVisible(false);
        obj2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj2.add(gamePlay);
        
    }
    
    
    

    public static void main(String[] args) {
      
        
        Brick_Breaker_Game obj=new Brick_Breaker_Game();
        
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       if(e.getSource()== startPlay)
        {
            startPage.setVisible(false);
            obj.setVisible(false);
            obj2.setVisible(true);
            //gamePlay.setVisible(true);
        }
        if(e.getSource()==highscores)
        {
            //getHighscores();
            JOptionPane.showMessageDialog(null, "Highscores are", "Highscores", JOptionPane.PLAIN_MESSAGE);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
