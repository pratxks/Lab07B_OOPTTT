
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TTTBoard extends JPanel implements ActionListener
{
    private static final int ROW = 3;
    private static final int COL = 3;
    
    private static TTTTileButton board[][];
    private static String state[][];
    
    private TTTTileButton currentClickedButton;
    private static int iMoveCount;
    
    private static Player playerX;
    private static Player playerO;
    
    private static int [] rowPredictIndexes;
    private static int [] colPredictIndexes;
    
    public TTTBoard()
    {
    }

    public void SetTTTBoardDisplay()
    {
        SetBoard();
        createTTTBoardPanel();
    }
        
    private void createTTTBoardPanel()
    {
        GridLayout buttonPanelLayout = new GridLayout(3, 3);
        
        setBounds(0, 0, 400, 400);
        setLayout(buttonPanelLayout);
        
        for(int row=0; row<3;  row++)
        {
            for(int col=0; col<3; col++)
            {
                add(board[row][col]);
            }
        }
    }
        
    public void SetBoard()
    {
        createBoard();
        createBoardState();
        
        iMoveCount = 0;
                
        playerX = new Player("X");
        playerO = new Player("O");
        
        playerX.SetBoard(this);
        playerO.SetBoard(this);
        
        SetBoardEmptyState();
    }
    
    private void createBoard()
    {
        board = new TTTTileButton[ROW][COL];
                
        for(int row=0; row<3;  row++)
        {
            for(int col=0; col<3; col++)
            {
                board[row][col] = new TTTTileButton(row, col);
                board[row][col].addActionListener(this);
            }
        }
    }
    
    private static void ResetBoard()
    {
        for(int row=0; row<3;  row++)
        {
            for(int col=0; col<3; col++)
            {
                board[row][col].SetEmptyState();
            }
        }
        
        iMoveCount = 0;
    }

    private void createBoardState()
    {
        state = new String[ROW][COL];
         
        for(int row=0; row<3;  row++)
        {
            for(int col=0; col<3; col++)
            {
                state[row][col] = new String("");
            }
        }
    }
    
    private static void SetBoardEmptyState()
    {
        for(int row=0; row<3;  row++)
        {
            for(int col=0; col<3; col++)
            {
                SetBoardState(row, col, "");
            }
        }
    }
    
    private static void SetBoardState(int row, int col, String player_name)
    {
        state[row][col] = player_name;
    }
    
    public static String GetBoardState(int row, int col)
    {
        return state[row][col];
    }
    
    private static void displayMessageDialog(String dlgMessage)
    {
        JOptionPane.showMessageDialog(null, dlgMessage);
        int iGameReplay = JOptionPane.showConfirmDialog(null, "Do you Want to Play again", "Restart Game", JOptionPane.YES_NO_OPTION);

        if(iGameReplay == JOptionPane.YES_OPTION)
        {
            ResetBoard();
            SetBoardEmptyState();
        }
        else
        {
            System.exit(0);
        }
    }
        
    private void checkTie()
    {
        if(iMoveCount == 7)
        {
            boolean isPredictTie = predictTie();

            if(isPredictTie) displayMessageDialog("Game Will Tie Anyway");
        }
        if(iMoveCount == 9)
        {
            displayMessageDialog("Full Board Tie");
        }
    }
        
    private static void checkEmptyState()
    {
        rowPredictIndexes = new int[2];
        colPredictIndexes = new int[2];
        int index = 0;
        
        for(int row=0; row<3;  row++)
        {
            for(int col=0; col<3; col++)
            {
                if(state[row][col].isEmpty())
                {
                    rowPredictIndexes[index] = row;
                    colPredictIndexes[index] = col;
                    index++;
                }
            }
        }
    }
        
    private static boolean doPredictionMove(Player predictedPlayer, String playerState, int currentRow, int currentCol)
    {
        boolean playerWins = false;
        
        if(state[currentRow][currentCol].isEmpty())
        {
            SetBoardState(currentRow, currentCol, playerState);
            playerWins = predictedPlayer.isWin(currentRow, currentCol);
        }
        
        return playerWins;
    }
        
    private static boolean predictTie()
    {
        boolean isPredictTie = false;
        
        checkEmptyState();
        
        isPredictTie = doPredictionMove(playerO, "O", rowPredictIndexes[0], colPredictIndexes[0]);
        isPredictTie |= doPredictionMove(playerX, "X", rowPredictIndexes[1], colPredictIndexes[1]);
        
        SetBoardState(rowPredictIndexes[0], colPredictIndexes[0], "");
        SetBoardState(rowPredictIndexes[1], colPredictIndexes[1], "");
        
        isPredictTie |= doPredictionMove(playerX, "X", rowPredictIndexes[0], colPredictIndexes[0]);
        isPredictTie |= doPredictionMove(playerO, "O", rowPredictIndexes[1], colPredictIndexes[1]);

        SetBoardState(rowPredictIndexes[0], colPredictIndexes[0], "");
        SetBoardState(rowPredictIndexes[1], colPredictIndexes[1], "");
        
        return !isPredictTie;
    }
        
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        currentClickedButton = (TTTTileButton)e.getSource();
        
        int currentRow = currentClickedButton.getRowIndex();
        int currentCol = currentClickedButton.getColIndex();
        
        boolean bIsWin = false;
                    
        if((iMoveCount == 0) || (iMoveCount == 2) || (iMoveCount == 4) || (iMoveCount == 6) || (iMoveCount == 8))
        {
            if(currentClickedButton.EmptyState())
            {
                currentClickedButton.SetXState();
                SetBoardState(currentRow, currentCol, "X");
                iMoveCount++;
                bIsWin = playerX.checkWin(iMoveCount, currentRow, currentCol);
                if(bIsWin)
                {
                    displayMessageDialog("X Wins");
                }
                else
                {
                    checkTie();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Position Already Selected by Player " + currentClickedButton.getText() + "\nPlease Select Other Position");
            }
        }
        else if((iMoveCount == 1) || (iMoveCount == 3) || (iMoveCount == 5) || (iMoveCount == 7))
        {
            if(currentClickedButton.EmptyState())
            {
                currentClickedButton.SetOState();
                SetBoardState(currentRow, currentCol, "O");
                iMoveCount++;
                bIsWin = playerO.checkWin(iMoveCount, currentRow, currentCol);
                if(bIsWin)
                {
                    displayMessageDialog("O Wins");
                }
                else
                {
                    checkTie();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Position Already Selected by Player " + currentClickedButton.getText() + "\nPlease Select Other Position");
            }
        }        
    }
}
