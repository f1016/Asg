/*
 * CSCI1130 Assignment 6
 * 
 * I declare that the assignment here submitted is original
 * except for source material explicitly acknowledged,
 * and that the same or closely related material has not been
 * previously submitted for another course.
 * I also acknowledge that I am aware of University policy and
 * guidelines and procedures applicable to breaches of such
 * policy and regulations, as contained in the website.
 * 
 * University Guideline on Academic Honesty:
 *  http://www.cuhk.edu.hk/policy/academichonesty
 * Faculty of Engineering Guidelines to Academic Honesty:
 *  https://www.erg.cuhk.edu.hk/erg/AcademicHonesty
 * 
 * Student Name: Cheung Kam Fung
 * Student ID  : 1155110263
 * Date        : 1/12/2018
 */

package boardgame;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Fung
 */
public class Connect4 extends TurnBasedGame{
    
    String winner ;
    int[] numInCol = new int[7];
    
    public Connect4() 
    {
        super(7, 6, "RED", "BLUE");
        this.setTitle("Connect 4");
    }
    
    @Override
    protected  void initGame() //set the button text
    {
        for (int y = 0; y < yCount; y++)
            for (int x = 0; x < xCount; x++)
                pieces[x][y].setText(" ");
    }
    
    @Override
    protected void gameAction(JButton triggeredButton)
    {
        String[] coord = triggeredButton.getActionCommand().split(","); // split the x,y
        int x = Integer.parseInt(coord[0]);
        int y = Integer.parseInt(coord[1]);
   
        if(numInCol[x] == 6) // if there are full Column
        {
            addLineToOutput("Invalid move!"); //output a INVALID move
        }
        else 
        {
            addLineToOutput(currentPlayer + " piece at column " + x);
            pieces[x][5-numInCol[x]].setText(currentPlayer); //change the bottom button to RED / BLUE
            pieces[x][5-numInCol[x]].setOpaque(true); 
            if(currentPlayer.equals("RED"))  pieces[x][5-numInCol[x]].setBackground(Color.RED); // change button to red if the player is red
            if(currentPlayer.equals("BLUE")) pieces[x][5-numInCol[x]].setBackground(Color.BLUE); // change button to blue if the player is blue

            numInCol[x]++; // the number of occupied button increase one 
        
            checkEndGame(x,y); // check if the game is ended
            if(gameEnded) // if the game is ended
            {
                addLineToOutput("Game ended!"); // output game ended
                JOptionPane.showMessageDialog(null, "Game ended!"); // show the confirm message
                System.exit(0); // if the game is ended then close it
            }
            changeTurn(); // next turn
        }
    }
    
    @Override
    protected boolean checkEndGame(int buttonX, int buttonY) 
    {
        if(checkConnect4()) // if there are a conseuctive 4 button
        {
            gameEnded = true; // the game is ended
            addLineToOutput("Winner is " + winner + "!"); // output winner 
        }
        else if (turn == xCount * yCount) // if fill all the button 
            {
                gameEnded = true; // the game is ended
                addLineToOutput("Draw game!"); // output draw
            }
        return gameEnded;
    }
    
    protected boolean checkConnect4()
    {
        for(int i = 0 ; i< 7 ; i++) // checking the col (vertical)
        {
            for(int j = 0 ; j < 3 ; j++)
            {
                if(pieces[i][j].getText().equals(pieces[i][j+1].getText()) && // if the next one
                   pieces[i][j].getText().equals(pieces[i][j+2].getText()) && //             two
                   pieces[i][j].getText().equals(pieces[i][j+3].getText()) && //             three is the same as the first one
                   (pieces[i][j].getText().equals("RED") || pieces[i][j].getText().equals("BLUE")))
                {                    
                    for(int b=j ;b <= j+3 ;b++) // change the button to YELLOW
                    {
                        pieces[i][b].setOpaque(true);
                        pieces[i][b].setBackground(Color.YELLOW);
                    }
                    
                    winner = currentPlayer; 
                    return true;
                }
            }
        }
        
        for(int j = 0 ; j< 6 ; j++) // checking the row (horizontal)
        {
            for(int i = 0 ; i < 4 ; i++)
            {
                if(pieces[i][j].getText().equals(pieces[i+1][j].getText()) && // if the next one 
                   pieces[i][j].getText().equals(pieces[i+2][j].getText()) && //             two
                   pieces[i][j].getText().equals(pieces[i+3][j].getText()) && //             three are the same as the first one
                   (pieces[i][j].getText().equals("RED") || pieces[i][j].getText().equals("BLUE")))
                {
                    for(int b=i ; b <=i+3  ;b++) // change the color to yellow
                    {
                        pieces[b][j].setOpaque(true);
                        pieces[b][j].setBackground(Color.YELLOW);
                    }
                    
                    winner = currentPlayer;
                    return true;
                }
            }
        }
        
        for(int i = 0 ; i < 4 ; i ++) // checking the diagonal from left to right
        {
            for(int j = 0 ; j< 3; j++)
            {
                if(pieces[i][j].getText().equals(pieces[i+1][j+1].getText()) && // if the next one
                   pieces[i][j].getText().equals(pieces[i+2][j+2].getText()) && //             two
                   pieces[i][j].getText().equals(pieces[i+3][j+3].getText()) && //              three are the same as the first one
                   (pieces[i][j].getText().equals("RED") || pieces[i][j].getText().equals("BLUE")))
                {                    
                    for(int b = 0 ; b<4 ; b++) // change the color
                    {
                        pieces[i+b][j+b].setOpaque(true);
                        pieces[i+b][j+b].setBackground(Color.YELLOW);
                    }
                    
                    winner = currentPlayer;
                    return true;
                }  
            }
        }
        
        for(int i = 6 ; i >2 ; i --) // checking the diagonal from right to left
        {
            for(int j = 0 ; j< 3; j++)
            {
                if(pieces[i][j].getText().equals(pieces[i-1][j+1].getText()) && //if the next one
                   pieces[i][j].getText().equals(pieces[i-2][j+2].getText()) && //            two
                   pieces[i][j].getText().equals(pieces[i-3][j+3].getText()) && //            three are the same as the first one
                   (pieces[i][j].getText().equals("RED") || pieces[i][j].getText().equals("BLUE")))
                {           
                    for(int b = 3 ; b>=0 ; b--) // change the color
                    {
                        pieces[i-b][j+b].setOpaque(true);
                        pieces[i-b][j+b].setBackground(Color.YELLOW);
                    }
                    
                    winner = currentPlayer;
                    return true;
                }  
            }    
        }        
        return false;        // return false
    }
    public static void main(String[] args)
    {
        Connect4 connect4;
        connect4 = new Connect4(); // constructe 
        connect4.setLocation(400, 200);
        connect4.verbose = false;
    }
}