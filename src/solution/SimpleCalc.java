package solution;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import solution.Calculator.ButtonHelper;


public class SimpleCalc extends JPanel
{
    private JLabel resultLabel;
    private JTextField field1, field2;
    private double entry1, entry2;
    private String result;

    public SimpleCalc()
    {
        setLayout(new BorderLayout(30, 30));
        initComponents();
    }
    
    public void initComponents()
    {
        initTextFields();
        initButtons();
        initResultPanel();
        
    }
    
    public void initButtons()
    {
        JPanel buttonPanel = new JPanel();
        
        JButton button1 = new JButton("ADD");
        button1.setName("addButton");
        buttonPanel.add(button1); 
        button1.addActionListener(new ButtonHelper(1));
       
        JButton button2 = new JButton("SUB");
        button2.setName("subButton");
        buttonPanel.add(button2);
        button2.addActionListener(new ButtonHelper(2));
        
        JButton button3 = new JButton("MULT");
        button3.setName("multButton");
        buttonPanel.add(button3);
        button3.addActionListener(new ButtonHelper(3));
        
        JButton button4 = new JButton("DIV");
        button4.setName("divButton");
        buttonPanel.add(button4);
        button4.addActionListener(new ButtonHelper(4));

        add(buttonPanel, BorderLayout.CENTER);
    }
    
    public void initResultPanel()
    {
        JPanel resultPanel = new JPanel();
        
        resultLabel = new JLabel("Result");
        resultLabel.setName("resultLabel");
        
        resultPanel.add(resultLabel);
        
        add(resultPanel, BorderLayout.SOUTH);
    }
    
    public void initTextFields()
    {
        JPanel textFieldPanel = new JPanel();
        
        field1 = new JTextField(10);
        textFieldPanel.add(field1);
        
        field2 = new JTextField(10);
        textFieldPanel.add(field2);
        
        add(textFieldPanel, BorderLayout.NORTH);
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
        String s1, s2;
        double answer;
        
        result = "";
        
        s1 = field1.getText();
        s2 = field2.getText();
        
        entry1 = converter(s1);
        entry2 = converter(s2);
        
        if(entry2 == 0 && selection == 4)
        {
            result = "Error";
        }
        
        if(result.equals("Error"))
        {
            resultLabel.setText("Result = Error");
        }

        else
        {
            answer = compute(selection);
            resultLabel.setText("Result = " + answer);
        }
    }
    
    public double compute(int i)
    {
        if(i == 1)
            return entry1 + entry2;
        
        else if(i == 2)
            return entry1 - entry2;
        
        else if(i == 3)
            return entry1 * entry2;
        
        else  
            return entry1 / entry2;
    }
    
    public double converter(String s)
    {
        double num = 0;
        
        try
        {
            num = Double.parseDouble(s);
        }
        
        catch (Exception e)
        {
            result = "Error";
        }
        
        return num;
    }
}
