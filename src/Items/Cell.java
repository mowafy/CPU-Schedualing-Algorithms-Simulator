package Items;

import java.awt.Color;
import javax.swing.JLabel;

/**
 * @author Ahmed Elmowafy
 * " Cell " is a class to represent CPU job graphically on the screen
 *  and some markers (black mark represented every 10 jobs).
 */
public class Cell extends JLabel{
    
    /**
     * Create a cell with a specific location, size and number
     * @param x x-coordinate location of the Cell
     * @param y y-coordinate location of the Cell
     * @param width width of the Cell
     * @param height height of the Cell
     * @param jobNumber number on the Cell
     */
    private Cell(int x , int y , int width , int height , int jobNumber)
    {
        setSize(width , height);
        chooseColor(jobNumber);
        setLocation(x, y);
        setOpaque(true);
    }
    
    /**
     * Create and return a job on Gantt Chart at a specific location 
     * with a specific job number 
     * @param x x-coordinate location of the Cell
     * @param y y-coordinate location of the Cell
     * @param jobNumber number on the Cell
     * @return 
     */
    public static Cell createGanttCell(int x , int y , int jobNumber) 
    {
        return new Cell(x, y, 10, 80, jobNumber);
    }
    
    /**
     * Create a white small square to represent an empty job
     * at a specific location for the Gantt Chart
     * @param x x-coordinate location of the Cell
     * @param y y-coordinate location of the Cell 
     */
    public static Cell createEmptyJobCell(int x , int y)
    {
        return new Cell(x,y,10,10,9);
    }
    
    /**
     * Create and return a job on Ready Queue at a specific location 
     * with a specific job number 
     * @param x x-coordinate location of the Cell
     * @param y y-coordinate location of the Cell
     * @param jobNumber number on the Cell
     * @return Cell
     */
    public static Cell createReadyQueueCell(int x , int y , int jobNumber)
    {
        return new Cell(x,y, 36 , 80 ,jobNumber);
    }
    
    /**
     * Create a black small mark at a specific location.
     * Used mainly to make a mark after every 10 jobs. 
     * @param x x-coordinate location of the Cell
     * @param y y-coordinate location of the Cell +70
     * @return Cell
     */
    public static Cell createMark(int x , int y)
    {
        return new Cell(x,y,1,10,10);
    }
    
    /**
     * used to select the color of the job based on
     * its number and also view job number.
     * note: case 9 means empty job which is represented
     * in small white square in Gantt Chart.
     * @param type  is the job number related to its color.
     */
    private void chooseColor(int type)
    {
        setForeground(Color.WHITE);   // view job number in white on the label
        setHorizontalAlignment(CENTER);  // view job number in the center of the label
        switch(type)
        {
            case 1: setBackground(new Color(0x9C0000)); setText("1"); break;
            case 2: setBackground(Color.DARK_GRAY);     setText("2"); break;
            case 3: setBackground(new Color(0x171515)); setText("3"); break;
            case 4: setBackground(new Color(0x0000E6)); setText("4"); break;
            case 5: setBackground(new Color(0x40A800)); setText("5"); break;
            case 6: setBackground(new Color(0x720E9E)); setText("6"); break;
            case 7: setBackground(new Color(0xef8236)); setText("7"); break;
            case 8: setBackground(new Color(0x634d40)); setText("8"); break;
            case 9: setBackground(Color.WHITE);  break; // empty job 
            case 10: setBackground(Color.BLACK); break;// 10 jobs mark
            default:  
        }
    }       
}
