
package brick_breaker_game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;


public class MapGenerator {
    
    public int map[][];
    public int brickWidth;
    public int brickHeight;
    
    public MapGenerator(int row,int col)
    {
        map=new int[row][col];
        for(int i=0;i<map.length;i++)
        {
            for(int j=0;j<map[0].length;j++)
            {
                map[i][j]=1;     /*the value of is 1 because i want to make brick visible to panel, i.e, ball is not intersecting 
                                  with brick right now. When ball intersects...the i will change the value of map to 0 to disappear
                                 the brick*/
                
            }
            
        
        }
            brickWidth=540/col;
            brickHeight=150/row; 
            
            
            
            
    }
    public void draw(Graphics2D g)
            {
                for(int i=0;i<map.length;i++)
                {
                    for(int j=0;j<map[0].length;j++)
                    {
                        if(map[i][j]>0)
                        {
                            g.setColor(Color.white);
                            g.fillRect(j*brickWidth +80, i*brickHeight +50, brickWidth, brickHeight);
                            
                            g.setStroke(new BasicStroke(3));
                            g.setColor(Color.black);
                            g.drawRect(j*brickWidth +80, i*brickHeight +50, brickWidth, brickHeight);
                        }
                        
                
                
                    }
                } 
            }  
    
    public void setBrickValue(int value,int row,int col)
    {
        map[row][col]=value;
    }
    
}