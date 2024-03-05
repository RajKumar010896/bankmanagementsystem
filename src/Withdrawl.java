import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener
{
    JTextField amount;
    JButton withdraw, back;
    String pinnumber;
    Withdrawl(String pinnumber) {

        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel Image = new JLabel(i3);
        Image.setBounds(0, 0, 900, 900);
        add(Image);

        JLabel text = new JLabel("Enter the amount you want to withdraw");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(170, 300, 400, 20);
        Image.add(text);

        amount =  new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD,22));
        amount.setBounds(170, 350, 320, 20);
        Image.add(amount);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(355, 485,150,30);
        withdraw.addActionListener(this);
        Image.add(withdraw);

        back = new JButton("Back");
        back.setBounds(355, 520,150,30);
        back.addActionListener(this);
        Image.add(back);


        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);


    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == withdraw){
            String withdrawlAmount =  amount.getText();
            Date date = new Date();
            if (withdrawlAmount.equals("")) {
                JOptionPane.showMessageDialog(null,"please enter teh amount you want to withdraw");

            }else {
                try {
                    Conn conn = new Conn();
                    String query = "INSERT INTO bank VALUES(?,?,?,? )";
                    try(PreparedStatement preparedStatement = conn.conn.prepareStatement(query)){
                        preparedStatement.setString(1,pinnumber);
                        preparedStatement.setString(2,date.toString());
                        preparedStatement.setString(3,"withdraw");
                        preparedStatement.setString(4,withdrawlAmount);

                        preparedStatement.executeUpdate();

                    }
//                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs " + withdrawlAmount + " Withdraw Successfully");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }catch(Exception e){
                    System.out.println(e);

                }

            }


        }else if (ae.getSource() == back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);

        }
    }
    public static void main (String args[]){
        new Withdrawl("");


    }

}
