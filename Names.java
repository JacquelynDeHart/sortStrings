/**
 * This program will quickSort names from an input file, output them to another 
 * file after sorting and allow for a binary search of the created file for a 
 * given name.
 */
package names;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author owner
 */
public class Names extends JFrame{
    
    JButton btn, quit,search;
    JLabel nameSorted;
    JPanel pan, btnPan, searchPan;
    ArrayList<String> ar = new ArrayList<>();
    String[] sa;
    InputStream is = null;
    DataInputStream dis = null;
    String k,ls, input;
    private JList list;
    int result;
    /**
     * constructor for the class
     * GUI implementation
     */
    public Names(){
        btn = new JButton();
        quit = new JButton();
        btn.setText("Click to Sort");
        quit.setText("Exit");
        
        nameSorted = new JLabel();
        nameSorted.setText("Sorted names will go here: ");
        pan = new JPanel(new BorderLayout());
        
        pan.add(btn, BorderLayout.WEST);
        pan.add(quit, BorderLayout.SOUTH);
        pan.add(nameSorted, BorderLayout.NORTH);
        add(pan);
        
        setTitle("QuickSort names");
        setSize(620, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        btn.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent ae){
                try{
                    btnMouseClicked(ae);
                }catch (IOException ex){
                    Logger.getLogger(Names.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        quit.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent ae){
                try {
                    quitMouseClicked(ae);
                } catch (IOException ex) {
                    Logger.getLogger(Names.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }
    /**
     * private method to handle the quit button click
     */
    private void quitMouseClicked(java.awt.event.MouseEvent ae) throws IOException{
        System.exit(0);
    }
                
    /**
     * private method to handle the button click
     */
    private void btnMouseClicked(java.awt.event.MouseEvent ae) throws IOException{
        nameSorted.setText("Here are the sorted names: ");
        readIn();
        convert(ar);

        doQuickSort(sa, 0, sa.length-1);

        list = new JList(sa);
        list.setLayoutOrientation(JList.VERTICAL_WRAP);
        list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        listScroller.setAlignmentX(LEFT_ALIGNMENT);
        pan.add(listScroller, BorderLayout.CENTER);
        
        search = new JButton("Click to search");
        search.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent ae){
                try{
                    searchMouseClicked(ae);
                }catch (IOException ex){
                    Logger.getLogger(Names.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        pan.add(search, BorderLayout.EAST);

    }
    /**
     * private method to handle the mouse click of the search button.
     * allows the user to input a name to search for using a binary search.
     */
    private void searchMouseClicked(java.awt.event.MouseEvent ae) throws IOException
    {
       
        input = JOptionPane.showInputDialog("Enter a name to search for: ");
        displayResult(result= doBinSearch(sa, input));    
    }
    
    /**
     * This method reads a file into an ArrayList
     */
    public ArrayList<String> readIn() throws IOException{
        try{
            is = new FileInputStream("/home/owner/NetBeansProjects/Names/src/names/names.dat");
            dis = new DataInputStream(is);
            while(dis.available()>0) {
                k = dis.readUTF();
                ar.add(k);
                
            }
        }
        catch (IOException e){
            
        }finally {
            if(is != null)
                is.close();
            if(dis != null)
                dis.close();
        } 
        return ar;
    }
    
    /**
     * This method converts an ArrayList to a String array for sorting
     * @param a the ArrayList to convert
     * @return the String array
     */
    public String[] convert(ArrayList<String> a){
        List<String> l = new ArrayList<>();
        for(String x : a){
            l.add(x);
        }
        String[] s = new String[l.size()];
        s = l.toArray(s);
        
        return sa = s;
    }
    

    /**
     * This method uses the Quick Sort algorithm to sort 
     * a String array
     * @param s The String array to sort
     * @param start the beginning subscript in the array
     * @param end the ending subscript of the list
     */
    private void doQuickSort(String[] s, int start, int end){
        int pivotPoint;               
        
        if(start < end){
            //get pivot point: returns an int
            pivotPoint = partition(s, start, end);
            
            //Sort the first sublist
            doQuickSort(s, start, pivotPoint-1);
            
            //Sort second half of the list
            doQuickSort(s, pivotPoint+1, end);
        }
    }
    /**
     * This method selects a pivot value in an array and arranges
     * the array into two sublists.
     * @param s the array to partition
     * @param start the beginning subscript of the array to partition
     * @param end the ending subscript of the array to partition
     */
    private int partition(String[] s, int start, int end){
        int endOfLeftList, mid;
        String pivotVal;
        //Find the subscript of the middle element.
        mid = start +(end - start)/2;
        
        //swap the middle element with the first. this moves the pivot
        // to the start of the list.
        swap(s, start, mid);
        
        //save the pivot
        pivotVal = s[start];
        
        endOfLeftList = start;
        
        for(int scan = start+1; scan<= end; scan++){
            if(s[scan].compareTo(pivotVal)<0){
                endOfLeftList++;
                swap(s, endOfLeftList, scan);
            }
        }
        return endOfLeftList;
    }
    /**
     * This method swaps the contents of two elements
     * @param s the array 
     * @param a the subscript of the first element
     * @param b the subscript of the second element
     */
    private void swap(String[] s, int a, int b){
        String temp;
        temp = s[a];
        s[a] = s[b];
        s[b] = temp;
    }
    
    /**
     * This method implements a binary search of the sorted array
     * @param s the array to search through
     * @param i the input string to search for
     */
    public int doBinSearch(String[] s, String i){
        //declare and initialize variables
        int low = 0;
        int right = s.length - 1;
        //use while loop to search for the element in the array
        while (low<= right){
            int mid = low+(right - low)/2;
            //compare the values 
            int res = i.compareToIgnoreCase(s[mid]);System.out.println(res);
            if(res == 0){
                return mid;
            }
            if(res >0){
                low = mid+1;
                //System.out.println(mid);
            }       
            else 
                right = mid-1;       
        }
        return -1;
    }
    
    /**
     * interprets result of binary search
     * @param f the result of the binary search method
     */
    public void displayResult(int f){
        if(f == -1)
            JOptionPane.showMessageDialog(null, "The name was not found.");
        else{
            JOptionPane.showMessageDialog(null, "The name "+input+" was found"+
                    " at index "+f+".");
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Names();
    }
    
}
