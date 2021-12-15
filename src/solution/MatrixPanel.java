package solution;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*
 * Read matrices into arrays, multiple arrays
 */
public class MatrixPanel extends JPanel
{
    public JPanel sizer, matrices;
    private JTextField field1, field2, field3, field4;
    private JLabel message;
    private double[][] matrix1, matrix2;
    private JTextField[][] text1, text2;
    private boolean dimSet;
    private int rows1, rows2, cols1, cols2;

    public MatrixPanel()
    {
        initComponents();
    }
    
    public void initComponents()
    {
        initSizes();
        //initMatrices();
        
    }
    
    public void initSizes()
    {
        sizer = new JPanel();
        JPanel m1 = new JPanel();
        JPanel m2 = new JPanel();
        
        sizer.setLayout(new BoxLayout(sizer, BoxLayout.Y_AXIS));
        
        field1 = new JTextField(5);
        field2 = new JTextField(5);
        field3 = new JTextField(5);
        field4 = new JTextField(5);
        
        m1.add(new JLabel("Matrix A Dimensions: "));
        m1.add(field1);
        m1.add(new JLabel("x"));
        m1.add(field2);
        
        m2.add(new JLabel("Matrix B Dimensions: "));
        m2.add(field3);
        m2.add(new JLabel("x"));
        m2.add(field4);
       
        JButton b1 = new JButton("Set Matrices");
        b1.addActionListener(new ButtonHelper(1));
        
        message = new JLabel();
        message.setText("");
        
        sizer.add(m1);
        sizer.add(m2);
        sizer.add(b1);
        sizer.add(message);
   
        add(sizer);
    }
    
    public void initMatrices()
    {
        matrices = new JPanel();
        
        JPanel matrix1 = new JPanel();
        matrix1.setLayout(new GridLayout(rows1, cols1));
        
        JPanel matrix2 = new JPanel();
        matrix2.setLayout(new GridLayout(rows2, cols2));
        
        text1 = new JTextField[rows1][cols1];
        text2 = new JTextField[rows2][cols2];
        
        for(int i = 0; i < rows1; i++)
        {
            for(int j = 0; j < cols1; j++)
            {
                JTextField temp = new JTextField(4);
                text1[i][j] = temp;
            
                matrix1.add(temp);
            }
            
        }
        
        for(int i = 0; i < rows2; i++)
        {
            for(int j = 0; j < cols2; j++)
            {
                JTextField temp = new JTextField(4);
                text2[i][j] = temp;
            
                matrix2.add(temp);
            }
            
        }
        
        matrices.add(matrix1);
        matrices.add(new JLabel(" X "));
        matrices.add(matrix2);
        
        add(matrices);
    }
    
    
    
    public void setMatrices()
    {
        String rows, cols;
        
        rows = field1.getText();
        cols = field2.getText();
        
        rows1 = converter(rows);
        cols1 = converter(cols);
        
        matrix1 = new double[rows1][cols1];
        
        rows = field3.getText();
        cols = field4.getText();
        
        rows2 = converter(rows);
        cols2 = converter(cols);
        
        
        
        if(rows1 == 0 || cols1 == 0 || rows2 == 0 || cols2 == 0)
        {
            message.setText("Error");
            dimSet = false;
        }
        
        else if(cols1 != rows2)
        {
            message.setText("Invalid Dimensions");
            dimSet = false;
        }
        
        else
        {
            dimSet = true;
            message.setText("");
        }
        
        matrix2 = new double[rows2][cols2];
        
    }
    
    public int converter(String s)
    {
        int num = 0;
        
        try
        {
            num = Integer.parseInt(s);

        }
        
        catch (Exception e)
        {
            message.setText("Error");
            dimSet = false;
        }
        
        return num;
    }
    
    public class ButtonHelper implements ActionListener
    {
        private int selection;
        
        public ButtonHelper(int x)
        {
            selection = x;
        }
        
        public void actionPerformed(ActionEvent e)
        {     
            buttonPress(selection);
        }
    }
    
    public void buttonPress(int selection)
    {
        switch(selection)
        {
            case 1: 
                setMatrices();
                
                if(dimSet)
                {
                    sizer.setVisible(false);
                    sizer.setFocusable(false);
                    initMatrices();
                    
                }
                    
                
            break;
        }
    }
        
        
}
