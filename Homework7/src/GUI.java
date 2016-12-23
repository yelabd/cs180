import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by youssefelabd on 10/23/16.
 */
public class GUI extends JFrame implements ActionListener{

    static final String[] SIZE = {"Small","Medium","Large"};
    static final String[] STYLE = {"Margherita","Proscuitto","Diavola","Verdure","Calzone"};
    static final String[] TOPPINGS = {"Garlic","Jalepenos","Extra Cheese","Bacon"};

    private JButton submit;
    private JRadioButton[] size;
    private JCheckBox[] toppings;
    private JComboBox style;
    private JTextArea order;
    private String userStyle = "";
    private String userSize = "";
    private String userT = "";
    private String finalOrder = "";


    public GUI(){
        this.setTitle("Pizza Shop");
        this.setMinimumSize(new Dimension(800,400));
        this.setResizable(false);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        order = new JTextArea();
        order.setEditable(false);
        JPanel orderPanel = new JPanel(new GridLayout());
        orderPanel.setBorder(BorderFactory.createTitledBorder("Output"));
        orderPanel.add(order);

        JPanel center = new JPanel(new GridLayout(0,2)); //center panel
        center.add(createOptionsPanel());
        center.add(orderPanel);

        contentPane.add(center,BorderLayout.CENTER);

        submit = new JButton("Submit"); //submit button
        JPanel buttonPanel = new JPanel(new FlowLayout()); //panel for button
        submit.addActionListener(this);
        buttonPanel.add(submit);
        contentPane.add(buttonPanel,BorderLayout.SOUTH);



    }
    public JPanel createOptionsPanel(){
        JPanel OptionsPanel = new JPanel(); //panel that would contain all the options
        OptionsPanel.setLayout(new GridLayout(2,0));
        OptionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        JPanel sizePanel = new JPanel(new FlowLayout()); //panel that contains size elements
        sizePanel.setBorder(BorderFactory.createTitledBorder("Select your pizza size"));
        size = new JRadioButton[SIZE.length];
        ButtonGroup sizeGroup = new ButtonGroup();
        for (int i = 0; i < SIZE.length; i++){
            size[i] = new JRadioButton();
            size[i].addActionListener(this);
            sizeGroup.add(size[i]);
            sizePanel.add(size[i]);
            sizePanel.add(new JLabel(SIZE[i]));
        }
        OptionsPanel.add(sizePanel);
        JPanel stylePanel = new JPanel(new FlowLayout()); //panel that would contain the style elements
        stylePanel.setBorder(BorderFactory.createTitledBorder("Select your pizza style"));
        style = new JComboBox(STYLE);
        style.addActionListener(this);
        stylePanel.add(style);

        OptionsPanel.add(stylePanel);

        return OptionsPanel;
    }
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if (source instanceof JRadioButton){
            JRadioButton selected = (JRadioButton) source;
            for (int i = 0; i < SIZE.length; i++){
                if (selected == size[i]){
                    userSize = SIZE[i];
                    System.out.println(userSize);
                }
            }
        }
        if (source instanceof JComboBox){
            userStyle = STYLE[style.getSelectedIndex()];
            System.out.println(userStyle);
        }
        if (source instanceof JCheckBox){
            JCheckBox selected = (JCheckBox) source;
            for (int i = 0; i < TOPPINGS.length; i++){
                if (selected == toppings[i]){
                    userT = userT +" "+ TOPPINGS[i];
                    System.out.println(userT);
                }
            }
        }
        if (source instanceof JButton){
            JButton selected = (JButton) source;
            if (source == submit) {
                System.out.println("Button");
                finalOrder = "Your custom pizza" + "\nSize: " + userSize + "\nStyle: " + userStyle + "\nToppings: " + userT;
                showOrder(finalOrder);
            }
        }
    }
    public void showOrder(String userOrder){
        order.setText(userOrder);
    }
    public static void main(String[] args){
        GUI pizzaGUI = new GUI();
        pizzaGUI.setVisible(true);
    }


}

