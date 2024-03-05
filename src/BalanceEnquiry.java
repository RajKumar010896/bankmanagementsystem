import javax.swing.*;
import javax.swing.text.html.MinimalHTMLWriter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton back;
    String pinnumber;

    BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);


        ImageIcon i1 =  new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image =  new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

         back =  new JButton("back");
         back.setBounds(355,520,150,30);
         back.addActionListener(this);
         image.add(back);

        Conn c = new Conn();
        int depositedAmount = 0;
        int withdrawnAmount = 0;


        try{
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
            while (rs.next()) {
                if(rs.getString("type").equals("Deposit")) {

                depositedAmount = Integer.parseInt(rs.getString("amount"));

            } else {

                withdrawnAmount = Integer.parseInt(rs.getString("amount"));
            }
        }


        }catch (Exception e) {
            System.out.println(e);
        }
        int balance = depositedAmount - withdrawnAmount;

//        String balanceMessage = (balance >=0) ? "Your current balance is " + formattedBalance : "you have an overdraft of " + formattedBalance;
        JLabel text = new JLabel("Your Current balance is Rs  " + balance);
        text.setForeground(Color.WHITE);
        text.setBounds(170, 300, 400,30);
        image.add(text);


        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);


    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }
        public static void main (String args[]){
        new BalanceEnquiry("");
    }
}
