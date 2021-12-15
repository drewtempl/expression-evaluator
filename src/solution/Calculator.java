package solution;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import solution.Calculator.ButtonHelper;

public class Calculator
{
    private JFrame frame;
    private JTextField field1, field2;
    private JLabel resultLabel;
    private String result;
    private double entry1, entry2;
    private JPanel buttonPanel;
    private SimpleCalc calc;
    private BaseConverter converter;
    private EvalPanel eval;
    private MatrixPanel matrix;
    
    public Calculator()
    {
        frame = new JFrame();
        frame.setLocation(100, 100);
        frame.setSize(600, 400);
        frame.setTitle("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(50, 50));
        frame.setResizable(false);
        
        calc = new SimpleCalc();
        converter = new BaseConverter();
        eval = new EvalPanel();
        matrix = new MatrixPanel();
        
        initComponents();
        
        //frame.pack();
        frame.setVisible(true);
    }
    
    public void initComponents()
    {
        initButtons();  
    }
    
    public void initButtons()
    {
        buttonPanel = new JPanel();
        
        JButton button2 = new JButton("Expression Evaluator");
        buttonPanel.add(button2);
        button2.addActionListener(new ButtonHelper(2));
        
        JButton button1 = new JButton("Calculator");
        buttonPanel.add(button1); 
        button1.addActionListener(new ButtonHelper(1));
       
        JButton button3 = new JButton("Base Converter");
        buttonPanel.add(button3);
        button3.addActionListener(new ButtonHelper(3));
        
//        JButton button4 = new JButton("Matrix Calculator");
//        buttonPanel.add(button4);
//        button4.addActionListener(new ButtonHelper(4));

        frame.add(buttonPanel, BorderLayout.NORTH);
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
            newView(selection);
        }
    }
    
    
    
    public void newView(int i)
    {
        switch(i)
        {
            case 1:
                removeComps();
                frame.add(calc, BorderLayout.CENTER);   
                frame.repaint();
                frame.setVisible(true);
            break;
            
            case 2:
                removeComps();
                frame.add(eval, BorderLayout.CENTER);
                frame.repaint();
                frame.setVisible(true);
            break;
            
            case 3:
                removeComps();
                frame.add(converter, BorderLayout.CENTER);
                frame.repaint();
                frame.setVisible(true);
            break;
            
            case 4:

                removeComps();
                matrix = new MatrixPanel();
                frame.add(matrix, BorderLayout.CENTER);
                frame.repaint();
                frame.setVisible(true);
            break;
                
        }
    }

    private void removeComps()
    {
        frame.remove(eval);
        frame.remove(calc);
        frame.remove(converter);
        frame.remove(matrix);
    }

    public static void main(String[] args)
    {
        new Calculator();
    }
    
}


