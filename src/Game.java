
import javax.swing.JFrame;


public class Game 
{
    private TTTGameFrame tttGame;
    
    public Game()
    {
        tttGame = new TTTGameFrame();
    }
    
    private void Start()
    {
        tttGame.setSize(400, 500);
        tttGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tttGame.setLocationRelativeTo(null);
        tttGame.setResizable(false);
        tttGame.SetTTTGameFrameDisplay();
        
        tttGame.setVisible(true);
    }
    
    public static void main(String[] args) 
    {
        Game myTTTGame = new Game();
        
        myTTTGame.Start();
    }
}
