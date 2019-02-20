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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jacquelyn Johnson
 */
public class Names extends JFrame{
    
    JButton btn;
    JLabel lbl;
    JPanel pan;
    ArrayList<String> ar = new ArrayList<>();
    String[] sa;
    InputStream is = null;
    DataInputStream dis = null;
    String k;
    /**
     * constructor for the class
     * GUI implementation
     */
    public Names(){
        btn = new JButton();
        btn.setText("Click to Sort");
        lbl = new JLabel("Sorting by Ascending Alpha: ");
        pan = new JPanel(new BorderLayout());
        
        pan.add(btn, BorderLayout.SOUTH);
        pan.add(lbl, BorderLayout.NORTH);
        add(pan);
        
        setTitle("QuickSort names");
        setSize(500, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        
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
    }
    /**
     * private method to handle the button click
     */
    private void btnMouseClicked(java.awt.event.MouseEvent ae) throws IOException{
        lbl.setText("Clicked");
        
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
        List<String> list = new ArrayList<>();
        for(String x : a){
            list.add(x);
        }
        String[] s = new String[list.size()];
        s = list.toArray(s);
        return sa = s;
    }
    
    /**
     * This method will accept a String array as an argument and sort the 
     * contents. It will call itself until all elements are sorted.
     * @param s the String array to be sorted
     */
    public void nameSort(String[] s){
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Names();
    }
    
}
