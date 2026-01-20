package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class PaymentFrame extends JFrame implements ActionListener {
    private JLabel background;
    private double totalAmount;
    private String username;
    
    private JTextField cardfld, namefld, cvvfld, bkashMobilefld, nagadMobilefld;
    private JPasswordField bkashPinfld, nagadPinfld;
    private JComboBox<String> monthbox, yearbox;
    private JButton paybtn, backbtn;
    private JRadioButton cardrb, bkashrb, nagadrb;
    private ButtonGroup paymentGroup;
    
    private JPanel cardPanel, bkashPanel, nagadPanel;
    
    public PaymentFrame(String username, double totalAmount) {
        super("Game Shop - Payment");
        this.username = username;
        this.totalAmount = totalAmount;
        
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        try {
            ImageIcon icon = new ImageIcon("./images/logo.png");
            Image image = icon.getImage();
            this.setIconImage(image);
        } catch (Exception e) {
            System.out.println("Logo image not found: " + e.getMessage());
        }
        
        setSize(1920, 1080);

        try {
            ImageIcon bgIcon = new ImageIcon("./images/payment.jpg");
            Image bgImage = bgIcon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
            background = new JLabel(new ImageIcon(bgImage));
            background.setBounds(0, 0, 1920, 1080);
        } catch (Exception e) {
            background = new JLabel();
            background.setBackground(new Color(30, 30, 30));
            background.setOpaque(true);
            background.setBounds(0, 0, 1920, 1080);
        }

        background.setLayout(null);

        JLabel userlbl = new JLabel("Customer: " + username);
        userlbl.setForeground(Color.BLACK);
        userlbl.setFont(new Font("Arial", Font.BOLD, 30));
        userlbl.setBounds(1500, 130, 500, 40);
        background.add(userlbl);

        DecimalFormat df = new DecimalFormat("#,##0.00");
        JLabel totallbl = new JLabel("Total Amount: $" + df.format(totalAmount));
        totallbl.setForeground(Color.BLACK);
        totallbl.setFont(new Font("Arial", Font.BOLD, 32));
        totallbl.setBounds(1500, 180, 500, 50);
        background.add(totallbl);

        JLabel methodlbl = new JLabel("Select Payment Method:");
        methodlbl.setForeground(new Color(89, 255, 249));
        methodlbl.setFont(new Font("Arial", Font.BOLD, 28));
        methodlbl.setBounds(700, 250, 400, 40);
        background.add(methodlbl);

        paymentGroup = new ButtonGroup();
        
        cardrb = new JRadioButton("Credit/Debit Card");
        cardrb.setBounds(700, 300, 250, 40);
        cardrb.setFont(new Font("Arial", Font.BOLD, 24));
        cardrb.setForeground(Color.WHITE);
        cardrb.setOpaque(false);
        cardrb.setSelected(true);
        cardrb.addActionListener(this);
        paymentGroup.add(cardrb);
        background.add(cardrb);
        
        bkashrb = new JRadioButton("bKash");
        bkashrb.setBounds(700, 350, 200, 40);
        bkashrb.setFont(new Font("Arial", Font.BOLD, 24));
        bkashrb.setForeground(Color.WHITE);
        bkashrb.setOpaque(false);
        bkashrb.addActionListener(this);
        paymentGroup.add(bkashrb);
        background.add(bkashrb);
        
        nagadrb = new JRadioButton("Nagad");
        nagadrb.setBounds(700, 400, 200, 40);
        nagadrb.setFont(new Font("Arial", Font.BOLD, 24));
        nagadrb.setForeground(Color.WHITE);
        nagadrb.setOpaque(false);
        nagadrb.addActionListener(this);
        paymentGroup.add(nagadrb);
        background.add(nagadrb);

        cardPanel = createCardPanel();
        cardPanel.setBounds(700, 450, 500, 300);
        background.add(cardPanel);

        bkashPanel = createBkashPanel();
        bkashPanel.setBounds(700, 450, 500, 200);
        bkashPanel.setVisible(false);
        background.add(bkashPanel);

        nagadPanel = createNagadPanel();
        nagadPanel.setBounds(700, 450, 500, 200);
        nagadPanel.setVisible(false);
        background.add(nagadPanel);

        paybtn = new JButton("CONFIRM PAYMENT");
        paybtn.setBounds(700, 800, 250, 60);
        paybtn.setBackground(new Color(15, 219, 255));
        paybtn.setForeground(Color.BLACK);
        paybtn.setFont(new Font("Arial", Font.BOLD, 22));
        paybtn.addActionListener(this);
        background.add(paybtn);

        backbtn = new JButton("BACK TO STORE");
        backbtn.setBounds(1000, 800, 250, 60);
        backbtn.setBackground(new Color(15, 219, 255));
        backbtn.setForeground(Color.BLACK);
        backbtn.setFont(new Font("Arial", Font.BOLD, 22));
        backbtn.addActionListener(this);
        background.add(backbtn);

        this.add(background);
    }

    private JPanel createCardPanel() {
        JPanel panel = new JPanel(null);
        panel.setOpaque(false);
        
        JLabel cardlbl = new JLabel("Card Number:");
        cardlbl.setForeground(new Color(89, 255, 249));
        cardlbl.setFont(new Font("Arial", Font.BOLD, 26));
        cardlbl.setBounds(0, 0, 200, 40);
        panel.add(cardlbl);

        cardfld = new JTextField();
        cardfld.setBounds(220, 0, 250, 40);
        cardfld.setFont(new Font("Arial", Font.PLAIN, 18));
        cardfld.setBackground(new Color(139, 123, 143));
        cardfld.setForeground(Color.WHITE);
        cardfld.setCaretColor(Color.CYAN);
        panel.add(cardfld);

        JLabel namelbl = new JLabel("Name on Card:");
        namelbl.setForeground(new Color(89, 255, 249));
        namelbl.setFont(new Font("Arial", Font.BOLD, 26));
        namelbl.setBounds(0, 60, 200, 40);
        panel.add(namelbl);

        namefld = new JTextField();
        namefld.setBounds(220, 60, 250, 40);
        namefld.setFont(new Font("Arial", Font.PLAIN, 18));
        namefld.setBackground(new Color(139, 123, 143));
        namefld.setForeground(Color.WHITE);
        namefld.setCaretColor(Color.CYAN);
        panel.add(namefld);

        JLabel expirylbl = new JLabel("Expiry Date:");
        expirylbl.setForeground(new Color(89, 255, 249));
        expirylbl.setFont(new Font("Arial", Font.BOLD, 26));
        expirylbl.setBounds(0, 120, 200, 40);
        panel.add(expirylbl);

        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        monthbox = new JComboBox<>(months);
        monthbox.setBounds(220, 120, 100, 40);
        monthbox.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(monthbox);

        JLabel slashlbl = new JLabel("/");
        slashlbl.setForeground(Color.WHITE);
        slashlbl.setFont(new Font("Arial", Font.BOLD, 24));
        slashlbl.setBounds(330, 120, 20, 40);
        panel.add(slashlbl);

        String[] years = {"2026", "2027", "2028", "2029", "2030", "2031", "2032"};
        yearbox = new JComboBox<>(years);
        yearbox.setBounds(350, 120, 120, 40);
        yearbox.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(yearbox);

        JLabel cvvlbl = new JLabel("CVV:");
        cvvlbl.setForeground(new Color(89, 255, 249));
        cvvlbl.setFont(new Font("Arial", Font.BOLD, 26));
        cvvlbl.setBounds(0, 180, 200, 40);
        panel.add(cvvlbl);

        cvvfld = new JTextField();
        cvvfld.setBounds(220, 180, 120, 40);
        cvvfld.setFont(new Font("Arial", Font.PLAIN, 18));
        cvvfld.setBackground(new Color(139, 123, 143));
        cvvfld.setForeground(Color.WHITE);
        cvvfld.setCaretColor(Color.CYAN);
        panel.add(cvvfld);

        return panel;
    }

    private JPanel createBkashPanel() {
        JPanel panel = new JPanel(null);
        panel.setOpaque(false);
        
        JLabel mobilelbl = new JLabel("bKash Mobile:");
        mobilelbl.setForeground(new Color(89, 255, 249));
        mobilelbl.setFont(new Font("Arial", Font.BOLD, 26));
        mobilelbl.setBounds(0, 0, 200, 40);
        panel.add(mobilelbl);

        bkashMobilefld = new JTextField();
        bkashMobilefld.setBounds(220, 0, 250, 40);
        bkashMobilefld.setFont(new Font("Arial", Font.PLAIN, 18));
        bkashMobilefld.setBackground(new Color(139, 123, 143));
        bkashMobilefld.setForeground(Color.WHITE);
        bkashMobilefld.setCaretColor(Color.CYAN);
        panel.add(bkashMobilefld);

        JLabel pinlbl = new JLabel("bKash PIN:");
        pinlbl.setForeground(new Color(89, 255, 249));
        pinlbl.setFont(new Font("Arial", Font.BOLD, 26));
        pinlbl.setBounds(0, 60, 200, 40);
        panel.add(pinlbl);

        bkashPinfld = new JPasswordField();
        bkashPinfld.setBounds(220, 60, 250, 40);
        bkashPinfld.setFont(new Font("Arial", Font.PLAIN, 18));
        bkashPinfld.setBackground(new Color(139, 123, 143));
        bkashPinfld.setForeground(Color.WHITE);
        bkashPinfld.setCaretColor(Color.CYAN);
        panel.add(bkashPinfld);

        return panel;
    }

    private JPanel createNagadPanel() {
        JPanel panel = new JPanel(null);
        panel.setOpaque(false);
        
        JLabel mobilelbl = new JLabel("Nagad Mobile:");
        mobilelbl.setForeground(new Color(89, 255, 249));
        mobilelbl.setFont(new Font("Arial", Font.BOLD, 26));
        mobilelbl.setBounds(0, 0, 200, 40);
        panel.add(mobilelbl);

        nagadMobilefld = new JTextField();
        nagadMobilefld.setBounds(220, 0, 250, 40);
        nagadMobilefld.setFont(new Font("Arial", Font.PLAIN, 18));
        nagadMobilefld.setBackground(new Color(139, 123, 143));
        nagadMobilefld.setForeground(Color.WHITE);
        nagadMobilefld.setCaretColor(Color.CYAN);
        panel.add(nagadMobilefld);

        JLabel pinlbl = new JLabel("Nagad PIN:");
        pinlbl.setForeground(new Color(89, 255, 249));
        pinlbl.setFont(new Font("Arial", Font.BOLD, 26));
        pinlbl.setBounds(0, 60, 200, 40);
        panel.add(pinlbl);

        nagadPinfld = new JPasswordField();
        nagadPinfld.setBounds(220, 60, 250, 40);
        nagadPinfld.setFont(new Font("Arial", Font.PLAIN, 18));
        nagadPinfld.setBackground(new Color(139, 123, 143));
        nagadPinfld.setForeground(Color.WHITE);
        nagadPinfld.setCaretColor(Color.CYAN);
        panel.add(nagadPinfld);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backbtn) {
            new ShopFrame(username).setVisible(true);
            this.dispose();
        }
        
        else if (ae.getSource() == paybtn) {
            boolean valid = false;
            String method = "";
            
            if (cardrb.isSelected()) {
                if (cardfld.getText().length() == 16 && 
                    cvvfld.getText().length() == 3 && 
                    !namefld.getText().isEmpty()) {
                    valid = true;
                    method = "Credit/Debit Card";
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Invalid card details!\n" +
                        "• Card must be 16 digits\n" +
                        "• CVV must be 3 digits\n" +
                        "• Name cannot be empty",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (bkashrb.isSelected()) {
                if (!bkashMobilefld.getText().isEmpty()) {
                    valid = true;
                    method = "bKash";
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Enter bKash mobile number!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (nagadrb.isSelected()) {
                if (!nagadMobilefld.getText().isEmpty()) {
                    valid = true;
                    method = "Nagad";
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Enter Nagad mobile number!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
            
            if (valid) {
                DecimalFormat df = new DecimalFormat("#,##0.00");
                JOptionPane.showMessageDialog(this,
                    "✅ PAYMENT SUCCESSFUL!\n\n" +
                    "Amount: $" + df.format(totalAmount) + "\n" +
                    "Method: " + method + "\n" +
                    "Customer: " + username + "\n\n" +
                    "Your games have been purchased!\n" +
                    "Game is already add to your game library.",
                    "Payment Confirmed",
                    JOptionPane.INFORMATION_MESSAGE);
                
                new ShopFrame(username).setVisible(true);
                this.dispose();
            }
        }
        
        else if (ae.getSource() == cardrb) {
            cardPanel.setVisible(true);
            bkashPanel.setVisible(false);
            nagadPanel.setVisible(false);
        }
        else if (ae.getSource() == bkashrb) {
            cardPanel.setVisible(false);
            bkashPanel.setVisible(true);
            nagadPanel.setVisible(false);
        }
        else if (ae.getSource() == nagadrb) {
            cardPanel.setVisible(false);
            bkashPanel.setVisible(false);
            nagadPanel.setVisible(true);
        }
    }
}