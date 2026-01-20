package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class ShopFrame extends JFrame implements ActionListener {

    private JLabel background;
    private JLabel cartTotalLabel;
    private HashMap<String, Double> cart = new HashMap<>();
    private double cartTotal = 0.0;
    private String username;
    
    private String[][] games = {
        {"Stardew Valley", "14.99", "RPG", "./images/games/stardew_valley.png"},
        {"Call of Duty: Modern Warfare", "59.99", "First-person shooter", "./images/games/cod.png"},
        {"Terraria", "9.99", "RPG", "./images/games/terraria.jpg"},
        {"Minecraft", "29.99", "Sandbox adventure", "./images/games/minecraft.png"},
        {"Cyberpunk 2077", "39.99", "Futuristic RPG", "./images/games/cyberpunk.jpg"},
        {"Fortnite", "0.00", "Battle Royale (Free)", "./images/games/fortnite.jpg"},
        {"Elden Ring", "59.99", "Action RPG", "./images/games/eldenring.jpg"},
        {"Valorant", "0.00", "Tactical shooter (Free)", "./images/games/valorant.png"},
        {"Last of Us 2 Remastered", "49.99", "Action-Adventure", "./images/games/lastofus.png"},
        {"CS2", "0.00", "Competitive FPS", "./images/games/cs2.png"},
        {"Forza Horizon 6", "49.99", "Racing game", "./images/games/forza.jpg"},
        {"Red Dead Redemption 2", "39.99", "Open-world western", "./images/games/rdr.png"}
    };

    public ShopFrame(String username) {
        this.username = username;
        
        setTitle("Game Shop - Store");
        setSize(1920, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        try {
            ImageIcon icon = new ImageIcon("./images/logo.png");
            Image image = icon.getImage();
            this.setIconImage(image);
        } catch (Exception e) {
            System.out.println("Logo image not found: " + e.getMessage());
        }
        
        setSize(1920, 1080);

        Image bg = new ImageIcon("./images/shop_bg.jpg").getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        background = new JLabel(new ImageIcon(bg));
        background.setLayout(null);
        add(background);

        // ===== CART PANEL =====
        JPanel cartPanel = new JPanel(null);
        cartPanel.setBounds(1400, 40, 420, 130);
        cartPanel.setOpaque(false);
        background.add(cartPanel);

        JLabel userLabel = new JLabel("👤 " + username);
        userLabel.setBounds(10, 0, 300, 30);
        userLabel.setFont(new Font("Arial", Font.BOLD, 20));
        userLabel.setForeground(new Color(90, 255, 250));
        cartPanel.add(userLabel);

        cartTotalLabel = new JLabel("Total: $0.00");
        cartTotalLabel.setBounds(10, 40, 250, 35);
        cartTotalLabel.setFont(new Font("Arial", Font.BOLD, 24));
        cartTotalLabel.setForeground(Color.GREEN);
        cartPanel.add(cartTotalLabel);

        JButton viewCart = new JButton("View Cart");
        viewCart.setBounds(260, 35, 130, 45);
        viewCart.setBackground(new Color(140, 90, 255));
        viewCart.addActionListener(e -> showCart());
        cartPanel.add(viewCart);

        createGameCard(0,  60, 220, 250, 250);
        createGameCard(1, 350, 220, 250, 250);
        createGameCard(2, 640, 220, 250, 250);
        createGameCard(3, 930, 220, 250, 250);
        createGameCard(4, 1220, 220, 250, 250);
        createGameCard(5, 1510, 220, 250, 250);

        createGameCard(6,  60, 530, 250, 250);
        createGameCard(7, 350, 530, 250, 250);
        createGameCard(8, 640, 530, 250, 250);
        createGameCard(9, 930, 530, 250, 250);
        createGameCard(10,1220, 530, 250, 250);
        createGameCard(11,1510, 530, 250, 250);

        JPanel bottom = new JPanel(null);
        bottom.setBounds(0, 900, 1920, 100);
        bottom.setBackground(new Color(0, 0, 0, 210));
        background.add(bottom);

        JButton clear = new JButton("CLEAR CART");
        clear.setBounds(420, 25, 180, 50);
        clear.setBackground(Color.ORANGE);
        clear.setFont(new Font("Arial", Font.BOLD, 18));
        clear.addActionListener(e -> clearCart());
        bottom.add(clear);

        JButton checkout = new JButton("PROCEED TO CHECKOUT");
        checkout.setBounds(760, 25, 320, 50);
        checkout.setBackground(new Color(0, 190, 0));
        checkout.setFont(new Font("Arial", Font.BOLD, 18));
        checkout.addActionListener(e -> proceedToCheckout());
        bottom.add(checkout);

        JButton logout = new JButton("LOGOUT");
        logout.setBounds(1150, 25, 180, 50);
        logout.setBackground(new Color(255, 120, 120));
        logout.setFont(new Font("Arial", Font.BOLD, 18));
        logout.addActionListener(e -> logout());
        bottom.add(logout);
    }

    private void createGameCard(int i, int x, int y, int w, int h) {
        JPanel card = new JPanel(null);
        card.setBounds(x, y, w, h);
        card.setBackground(new Color(0, 0, 0, 190));

        JPanel topBar = new JPanel(null);
        topBar.setBounds(0, 0, w, 40);
        topBar.setBackground(new Color(20, 20, 20, 220));

        JLabel title = new JLabel(games[i][0]);
        title.setBounds(10, 5, 160, 30);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setForeground(new Color(90, 255, 250));
        topBar.add(title);

        JLabel price = new JLabel("$" + games[i][1]);
        price.setBounds(w - 80, 5, 70, 30);
        price.setFont(new Font("Arial", Font.BOLD, 16));
        price.setForeground(Color.YELLOW);
        price.setHorizontalAlignment(SwingConstants.RIGHT);
        topBar.add(price);

        card.add(topBar);

        JLabel img = new JLabel();
        img.setBounds(10, 50, w - 20, 120);
        ImageIcon icon = new ImageIcon(games[i][3]);
        Image scaled = icon.getImage()
                .getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
        img.setIcon(new ImageIcon(scaled));
        card.add(img);

        JLabel desc = new JLabel(games[i][2]);
        desc.setBounds(10, 175, w - 20, 20);
        desc.setFont(new Font("Arial", Font.PLAIN, 11));
        desc.setForeground(Color.LIGHT_GRAY);
        card.add(desc);

        JButton add = new JButton("ADD TO CART");
        add.setBounds(50, 205, 150, 35);
        add.setBackground(new Color(140, 90, 255));
        add.setFont(new Font("Arial", Font.BOLD, 13));
        add.setActionCommand(String.valueOf(i));
        add.addActionListener(this);
        card.add(add);

        background.add(card);
    }

    private void updateCartTotal() {
        cartTotalLabel.setText(String.format("Total: $%.2f", cartTotal));
    }

    private void showCart() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty!");
            return;
        }
        StringBuilder sb = new StringBuilder("🛒 YOUR CART\n\n");
        cart.forEach((k, v) -> sb.append("• ").append(k).append(" - $").append(v).append("\n"));
        sb.append("\nTOTAL: $").append(String.format("%.2f", cartTotal));
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void clearCart() {
        cart.clear();
        cartTotal = 0;
        updateCartTotal();
    }

    private void proceedToCheckout() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cart is empty!");
            return;
        }
        
        new PaymentFrame(username, cartTotal).setVisible(true);
        this.dispose();
    }

    private void logout() {
        dispose();
        new LoginFrame().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = Integer.parseInt(e.getActionCommand());
        String name = games[index][0];
        double price = Double.parseDouble(games[index][1]);
        cart.put(name, price);
        cartTotal += price;
        updateCartTotal();
        JOptionPane.showMessageDialog(this, name + " added to cart!");
    }
}