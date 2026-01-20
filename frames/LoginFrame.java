package frames;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import entities.Account;
import java.util.Random;


public class LoginFrame extends JFrame implements ActionListener{
    private JTextField userFld, captchaFld;
    private JPasswordField passFld;
    private JButton loginBtn, signUpBtn, refreshCaptchaBtn;
    private JLabel background, captchaLabel;
    private int captchaAnswer;
    private Random random = new Random();

    public LoginFrame(){
        super("Game Shop - Login");
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        try{
            ImageIcon icon = new ImageIcon("./images/logo.png");
            Image image = icon.getImage();
            this.setIconImage(image);
        } catch (Exception e){
            System.out.println("Logo image not found: " + e.getMessage());
        }
        
        setSize(1920, 1080);

        try{
            ImageIcon bgIcon = new ImageIcon("./images/login_bg.jpg");
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

        int startX = 1300;
        int startY = 350;

        JLabel userLabel = new JLabel("Username: ");
        userLabel.setForeground(new Color(89, 255, 249));
        userLabel.setFont(new Font("Arial", Font.BOLD, 30));
        userLabel.setBounds(startX, startY, 200, 30);
        background.add(userLabel);

        userFld = new JTextField();
        userFld.setBounds(startX + 160, startY, 250, 35);
        userFld.setFont(new Font("Arial", Font.PLAIN, 16));
        userFld.setBackground(new Color(139, 123, 143));
        userFld.setForeground(Color.WHITE);
        userFld.setCaretColor(Color.CYAN);
        background.add(userFld);

        JLabel passLabel = new JLabel("Password: ");
        passLabel.setForeground(new Color(89, 255, 249));
        passLabel.setFont(new Font("Arial", Font.BOLD, 30));
        passLabel.setBounds(startX, startY + 50, 200, 30);
        background.add(passLabel);

        passFld = new JPasswordField();
        passFld.setBounds(startX + 160, startY + 50, 250, 35);
        passFld.setFont(new Font("Arial", Font.PLAIN, 16));
        passFld.setBackground(new Color(139, 123, 143));
        passFld.setForeground(Color.WHITE);
        passFld.setCaretColor(Color.CYAN);
        background.add(passFld);

        JLabel captchaTitle = new JLabel("Solve: ");
        captchaTitle.setForeground(new Color(89, 255, 249));
        captchaTitle.setFont(new Font("Arial", Font.BOLD, 30));
        captchaTitle.setBounds(startX, startY + 100, 200, 30);
        background.add(captchaTitle);

        String[] captchaData = generateMathCaptcha();
        captchaLabel = new JLabel(captchaData[0]);
        captchaLabel.setFont(new Font("Arial", Font.BOLD, 28));
        captchaLabel.setForeground(new Color(59, 247, 194));
        captchaLabel.setBounds(startX + 160, startY + 97, 120, 40);
        captchaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        background.add(captchaLabel);
        
        captchaAnswer = Integer.parseInt(captchaData[1]);

        refreshCaptchaBtn = new JButton("...");
        refreshCaptchaBtn.setBounds(startX + 290, startY + 100, 40, 40);
        refreshCaptchaBtn.setBackground(new Color(0, 255, 255));
        refreshCaptchaBtn.setForeground(Color.BLACK);
        refreshCaptchaBtn.setFont(new Font("Arial", Font.BOLD, 16));
        refreshCaptchaBtn.addActionListener(e -> refreshCaptcha());
        background.add(refreshCaptchaBtn);

        JLabel captchaInputLabel = new JLabel("Answer: ");
        captchaInputLabel.setForeground(new Color(89, 255, 249));
        captchaInputLabel.setFont(new Font("Arial", Font.BOLD, 30));
        captchaInputLabel.setBounds(startX, startY + 150, 200, 30);
        background.add(captchaInputLabel);

        captchaFld = new JTextField();
        captchaFld.setBounds(startX + 160, startY + 150, 120, 35);
        captchaFld.setFont(new Font("Arial", Font.PLAIN, 16));
        captchaFld.setBackground(new Color(139, 123, 143));
        captchaFld.setForeground(Color.WHITE);
        captchaFld.setCaretColor(Color.CYAN);
        background.add(captchaFld);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(startX + 80, startY + 200, 120, 45);
        loginBtn.setBackground(new Color(129, 66, 245));
        loginBtn.setForeground(Color.BLACK);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 18));
        loginBtn.addActionListener(this);
        background.add(loginBtn);

        signUpBtn = new JButton("Sign up");
        signUpBtn.setBounds(startX + 230, startY + 200, 120, 45);
        signUpBtn.setBackground(new Color(129, 66, 245));
        signUpBtn.setForeground(Color.BLACK);
        signUpBtn.setFont(new Font("Arial", Font.BOLD, 18));
        signUpBtn.addActionListener(this);
        background.add(signUpBtn);

        this.add(background);
    }

    private String[] generateMathCaptcha(){
        int num1 = random.nextInt(10) + 1;
        int num2 = random.nextInt(10) + 1;
        captchaAnswer = num1 + num2;
        return new String[]{num1 + " + " + num2 + " = ?", String.valueOf(captchaAnswer)};
    }

    private void refreshCaptcha(){
        String[] captchaData = generateMathCaptcha();
        captchaLabel.setText(captchaData[0]);
        captchaAnswer = Integer.parseInt(captchaData[1]);
        captchaFld.setText("");
    }

    private boolean validateCaptcha(String userInput){
        try{
            int userAnswer = Integer.parseInt(userInput.trim());
            return userAnswer == captchaAnswer;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == signUpBtn) {
            new RegistrationFrame().setVisible(true);
            this.dispose();
        }
        else if (ae.getSource() == loginBtn){
            String user = userFld.getText();
            String pass = new String(passFld.getPassword());
            String captchaInput = captchaFld.getText();
            
            if(user.isEmpty() || pass.isEmpty() || captchaInput.isEmpty()){
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }
            if (!validateCaptcha(captchaInput)){
                JOptionPane.showMessageDialog(this, "Wrong CAPTCHA answer!");
                refreshCaptcha();
                return;
            }
            
            if(new Account().validateLogin(user, pass)){
                JOptionPane.showMessageDialog(this, "Login Successful! Welcome " + user);
                new ShopFrame(user).setVisible(true);
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "Invalid Login! Check username/password");
            }
    }
}

}
