
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class TTTGameFrame extends JFrame
{
    private JPanel mainPanel;
    private TTTBoard boardPanel;
    private JPanel controlPanel;
    
    private JButton quitButton;
    
    public TTTGameFrame()
    {
        setTitle("Tic Tac Toe Game");
    }
    
    public void SetTTTGameFrameDisplay()
    {
        mainPanel = new JPanel();
        
        createBoardPanel();
        createControlPanel();
        
        mainPanel.setLayout(new BorderLayout());
        
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void createBoardPanel()
    {
        boardPanel = new TTTBoard();
        
        boardPanel.SetTTTBoardDisplay();
    }
    
    private void createControlPanel()
    {
        controlPanel = new JPanel();
        
        quitButton = new JButton("Quit");
        quitButton.setFont(new Font(Font.DIALOG, Font.BOLD, 48));
        
        quitButton.addActionListener(e -> System.exit(0));
        controlPanel.add(quitButton);
    }
}
