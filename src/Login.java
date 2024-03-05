import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener
{

    JButton login, singup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login(){
        setTitle("AUTOMATED TELLER MACHINE");

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("osward", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);

        JLabel cardno = new JLabel("Card No:");
        cardno.setFont(new Font("Osward", Font.BOLD, 38));
        cardno.setBounds(120, 150, 400, 40);
        add(cardno);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 40);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);

        JLabel pin = new JLabel("Pin:");
        pin.setFont(new Font("Osward", Font.BOLD, 38));
        pin.setBounds(120, 220, 400, 40);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 40);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);

        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

        singup = new JButton("SIGN UP");
        singup.setBounds(300, 350, 230, 30);
        singup.setBackground(Color.BLACK);
        singup.setForeground(Color.WHITE);
        singup.addActionListener(this);
        add(singup);


        getContentPane().setBackground(Color.WHITE);


        setSize(800,480);
        setVisible(true);
        setLocation(350, 200);

    }

    public void actionPerformed(ActionEvent ae){

        if (ae.getSource() == clear){
            cardTextField.setText("");
            pinTextField.setText("");

        }else if (ae.getSource() == login){
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            char[] pinChars = pinTextField.getPassword();
            String pinnumber = new String(pinChars);

            String query = "select * from login where cardnumber = '"+cardnumber+"' and pin = '"+pinnumber+"'";
            try{
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Card number or Pin");
                }


            }catch (Exception e){
                System.out.println(e);
            }
        }else if (ae.getSource() == singup){

            setVisible(false);
            new Signupone().setVisible(true);


        }

    }

    public static void main(String args[])
    {
        new Login();
    }
}