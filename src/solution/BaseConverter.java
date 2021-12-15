package solution;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BaseConverter extends JPanel
{
    private JLabel resultLabel;
    private JTextField field1;
    private double entry1;
    private String result;

    public BaseConverter()
    {
        setLayout(new BorderLayout());
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
        
        JButton button1 = new JButton("Binary");
        button1.setName("addButton");
        buttonPanel.add(button1); 
        button1.addActionListener(new ButtonHelper(1));
       
        JButton button2 = new JButton("Hexidecimal");
        button2.setName("subButton");
        buttonPanel.add(button2);
        button2.addActionListener(new ButtonHelper(2));
                
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
        JPanel singleText = new JPanel();
        
        field1 = new JTextField(10);
        singleText.add(field1);
        
        add(singleText, BorderLayout.NORTH);
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
        String s1;
        long answer;
        
        result = "";
        
        s1 = field1.getText();
        entry1 = converter(s1);
        
        if(entry1 < 0)
            resultLabel.setText("Result = 0");
        
        else if(selection == 1 && entry1 <= 524287)
        {
            answer = deciToBinary((long)entry1);
            resultLabel.setText("Result = " + answer);
        }
        
        else if(selection == 2)
        {
            resultLabel.setText("Result = " + deciToHex((long)entry1));
        }
  
    }
    
    public static long deciToBinary(long num)
    {
        if (num == 0)
            return 0;
        
        else
            return num % 2 + 10 * deciToBinary(num / 2);
    }
    
    public static String deciToHex(long num)
    {
        String nums = "0123456789ABCDEF";
        
        if (num == 0)
            return "";
        
        else
        {    
            return deciToHex(num / 16) + nums.charAt((int) (num % 16));
        }
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
