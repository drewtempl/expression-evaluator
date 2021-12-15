package solution;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import solution.BaseConverter.ButtonHelper;

public class EvalPanel extends JPanel
{
    private JLabel resultLabel;
    private JTextField field1;
    private double entry1;
    private String result;
    private Evaluator eval;

    public EvalPanel()
    {
        setLayout(new BorderLayout());
        initComponents();
    }
    
    public void initComponents()
    {
        initTextFields();
        initButtons();
        initResultPanel();
        
        eval = new Evaluator();
    }
    
    public void initButtons()
    {
        JPanel buttonPanel = new JPanel();
        
        JButton button1 = new JButton("Calculate");
        buttonPanel.add(button1); 
        button1.addActionListener(new ButtonHelper(1));
       
        JButton button2 = new JButton("Clear");
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
        
        field1 = new JTextField(15);
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
        
        if(selection == 2)
        {
            resultLabel.setText("Result");
            field1.setText("");
        }

        else
        {
            try
            {
                resultLabel.setText("Result = " + eval.calculate(field1.getText()));
            }
            
            catch(Exception e)
            {
                resultLabel.setText("Result = Error");
            }
        }
    }
    
    
}
