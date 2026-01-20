package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import entities.Account;
import java.util.Random;

public class RegistrationFrame extends JFrame implements ActionListener {

    private JTextField usernameFld, firstNameFld, emailFld, captchaFld;
    private JPasswordField passFld, confirmPassFld;
    private JButton registerBtn, backBtn, refreshCaptchaBtn;
    private JLabel background, captchaLabel;
    private int captchaAnswer;
    private Random random = new Random();

    public RegistrationFrame() {
        super("Game Shop - Sign Up");
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
            ImageIcon bgIcon = new ImageIcon("./images/regi_bg.jpg");
            Image bgImage = bgIcon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
            background = new JLabel(new ImageIcon(bgImage));
        } catch (Exception e) {
            background = new JLabel();
            background.setBackground(new Color(30, 30, 30));
            background.setOpaque(true);
        }

        background.setBounds(0, 0, 1920, 1080);
        background.setLayout(null);

        int startX = 1300;
        int startY = 300;

        Font labelFont = new Font("Arial", Font.BOLD, 30);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        JLabel userLabel = new JLabel("Username: ");
        userLabel.setForeground(new Color(89, 255, 249));
        userLabel.setFont(labelFont);
        userLabel.setBounds(startX, startY, 200, 30);
        background.add(userLabel);

        usernameFld = new JTextField();
        usernameFld.setBounds(startX + 168, startY, 250, 28);
        usernameFld.setFont(fieldFont);
        usernameFld.setBackground(new Color(139, 123, 143));
        usernameFld.setForeground(Color.WHITE);
        usernameFld.setCaretColor(Color.CYAN);
        background.add(usernameFld);

        startY += 50;
        JLabel nameLabel = new JLabel("Full Name: ");
        nameLabel.setForeground(new Color(89, 255, 249));
        nameLabel.setFont(labelFont);
        nameLabel.setBounds(startX, startY, 200, 30);
        background.add(nameLabel);

        firstNameFld = new JTextField();
        firstNameFld.setBounds(startX + 168, startY, 250, 28);
        firstNameFld.setFont(fieldFont);
        firstNameFld.setBackground(new Color(139, 123, 143));
        firstNameFld.setForeground(Color.WHITE);
        firstNameFld.setCaretColor(Color.CYAN);
        background.add(firstNameFld);

        startY += 50;
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setForeground(new Color(89, 255, 249));
        emailLabel.setFont(labelFont);
        emailLabel.setBounds(startX, startY, 200, 30);
        background.add(emailLabel);

        emailFld = new JTextField();
        emailFld.setBounds(startX + 168, startY, 250, 28);
        emailFld.setFont(fieldFont);
        emailFld.setBackground(new Color(139, 123, 143));
        emailFld.setForeground(Color.WHITE);
        emailFld.setCaretColor(Color.CYAN);
        background.add(emailFld);

        startY += 50;
        JLabel passLabel = new JLabel("Password: ");
        passLabel.setForeground(new Color(89, 255, 249));
        passLabel.setFont(labelFont);
        passLabel.setBounds(startX, startY, 200, 30);
        background.add(passLabel);

        passFld = new JPasswordField();
        passFld.setBounds(startX + 168, startY, 250, 28);
        passFld.setFont(fieldFont);
        passFld.setBackground(new Color(139, 123, 143));
        passFld.setForeground(Color.WHITE);
        passFld.setCaretColor(Color.CYAN);
        background.add(passFld);

        startY += 50;
        JLabel confirmLabel = new JLabel("Confirm: ");
        confirmLabel.setForeground(new Color(89, 255, 249));
        confirmLabel.setFont(labelFont);
        confirmLabel.setBounds(startX, startY, 200, 30);
        background.add(confirmLabel);

        confirmPassFld = new JPasswordField();
        confirmPassFld.setBounds(startX + 168, startY, 250, 28);
        confirmPassFld.setFont(fieldFont);
        confirmPassFld.setBackground(new Color(139, 123, 143));
        confirmPassFld.setForeground(Color.WHITE);
        confirmPassFld.setCaretColor(Color.CYAN);
        background.add(confirmPassFld);

        startY += 50;
        JLabel captchaTitle = new JLabel("Solve: ");
        captchaTitle.setForeground(new Color(89, 255, 249));
        captchaTitle.setFont(labelFont);
        captchaTitle.setBounds(startX, startY, 200, 30);
        background.add(captchaTitle);

        String[] captchaData = generateMathCaptcha();
        captchaLabel = new JLabel(captchaData[0]);
        captchaLabel.setFont(new Font("Arial", Font.BOLD, 28));
        captchaLabel.setForeground(new Color(59, 247, 194));
        captchaLabel.setBounds(startX + 168, startY - 3, 120, 40);
        captchaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        background.add(captchaLabel);

        refreshCaptchaBtn = new JButton("...");
        refreshCaptchaBtn.setBounds(startX + 290, startY, 40, 40);
        refreshCaptchaBtn.setBackground(new Color(0, 255, 255));
        refreshCaptchaBtn.setFont(new Font("Arial", Font.BOLD, 16));
        refreshCaptchaBtn.addActionListener(e -> refreshCaptcha());
        background.add(refreshCaptchaBtn);

        startY += 50;
        JLabel captchaInputLabel = new JLabel("Answer: ");
        captchaInputLabel.setForeground(new Color(89, 255, 249));
        captchaInputLabel.setFont(labelFont);
        captchaInputLabel.setBounds(startX, startY, 200, 30);
        background.add(captchaInputLabel);

        captchaFld = new JTextField();
        captchaFld.setBounds(startX + 168, startY, 120, 28);
        captchaFld.setFont(fieldFont);
        captchaFld.setBackground(new Color(139, 123, 143));
        captchaFld.setForeground(Color.WHITE);
        captchaFld.setCaretColor(Color.CYAN);
        background.add(captchaFld);

        startY += 50;
        registerBtn = new JButton("Register");
        registerBtn.setBounds(startX + 80, startY, 120, 45);
        registerBtn.setForeground(Color.BLACK);
        registerBtn.setFont(new Font("Arial", Font.BOLD, 18));
        registerBtn.setBackground(new Color(129, 66, 245));
        registerBtn.addActionListener(this);
        background.add(registerBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(startX + 230, startY, 120, 45);
        backBtn.setBackground(new Color(129, 66, 245));
        backBtn.setForeground(Color.BLACK);
        backBtn.setFont(new Font("Arial", Font.BOLD, 18));
        backBtn.addActionListener(this);
        background.add(backBtn);

        this.add(background);
    }

    private String[] generateMathCaptcha() {
        int a = random.nextInt(10) + 1;
        int b = random.nextInt(10) + 1;
        captchaAnswer = a + b;
        return new String[]{a + " + " + b + " = ?", String.valueOf(captchaAnswer)};
    }

    private void refreshCaptcha() {
        String[] data = generateMathCaptcha();
        captchaLabel.setText(data[0]);
        captchaFld.setText("");
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.indexOf("@") > 0 && email.contains(".");
    }

    private boolean validateCaptcha(String input) {
        try {
            return Integer.parseInt(input.trim()) == captchaAnswer;
        } catch (Exception e) {
            return false;
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backBtn) {
            new LoginFrame().setVisible(true);
            dispose();
        }

        if (ae.getSource() == registerBtn) {

            String username = usernameFld.getText().trim();
            String email = emailFld.getText().trim();
            String password = new String(passFld.getPassword());
            String confirm = new String(confirmPassFld.getPassword());

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(this, "Invalid email! Must contain @");
                return;
            }

            if (!password.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!");
                return;
            }

            if (!validateCaptcha(captchaFld.getText())) {
                JOptionPane.showMessageDialog(this, "Wrong CAPTCHA!");
                refreshCaptcha();
                return;
            }

            Account acc = new Account();
            if (acc.usernameExists(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists!");
                return;
            }

            new Account(username, password).addAccount();

            JOptionPane.showMessageDialog(this, "Account created successfully!");
            new LoginFrame().setVisible(true);
            dispose();
        }

    }
}
