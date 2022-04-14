
public class Player 
{
    private String m_name;
    private TTTBoard m_board;
    
    public Player(String name)
    {
        m_name = name;
    }
    
    public void SetBoard(TTTBoard board)
    {
        m_board = board;
    }
    
    public boolean checkWin(int moveCount, int currentRow, int currentCol)
    {
        boolean playerWins = false;

        if(moveCount >= 5)
        {
            playerWins = isWin(currentRow, currentCol);
        }
        
        return playerWins;
    }
        
    // checks to see if there is a win state on the current board for the specified currentPlayer (X or O) This method in turn calls three additional methods that break down the 3 kinds of wins that are possible.
    public boolean isWin(int currentRow, int currentCol)
    {
        boolean win = false;

        win |= isRowWin(currentRow);
        win |= isColWin(currentCol);
        win |= isDiagonalWin(currentRow, currentCol);

        return win;
    }

    // checks for a col win for specified currentPlayer
    private boolean isColWin(int currentCol)
    {
        boolean colwin = false;

        if ((m_board.GetBoardState(0, currentCol).equals(m_name)) 
         && (m_board.GetBoardState(1, currentCol).equals(m_name)) 
         && (m_board.GetBoardState(2, currentCol).equals(m_name))) colwin = true;

        return colwin;
    }

    // checks for a row win for the specified currentPlayer
    private boolean isRowWin(int currentRow)
    {
        boolean rowwin = false;

        if ((m_board.GetBoardState(currentRow, 0).equals(m_name)) 
         && (m_board.GetBoardState(currentRow, 1).equals(m_name)) 
         && (m_board.GetBoardState(currentRow, 2).equals(m_name))) rowwin = true;

        return rowwin;
    }

    // checks for a diagonal win for the specified currentPlayer
    private boolean isDiagonalWin(int currentRow, int currentCol)
    {
        boolean diagonalwin = false;

        if(currentRow == currentCol)
        {
            if ((m_board.GetBoardState(0, 0).equals(m_name)) 
             && (m_board.GetBoardState(1, 1).equals(m_name)) 
             && (m_board.GetBoardState(2, 2).equals(m_name))) diagonalwin = true;
        }
        if((Math.abs(currentRow - currentCol) == 2) || ((currentRow == 1) && (currentCol == 1)))
        {
            if ((m_board.GetBoardState(0, 2).equals(m_name)) 
             && (m_board.GetBoardState(1, 1).equals(m_name)) 
             && (m_board.GetBoardState(2, 0).equals(m_name))) diagonalwin = true;
        }
        
        return diagonalwin;
    }
}
