import javax.swing.*;
import java.awt.*;

public class AnaEkran extends JPanel {

    public AnaEkran(CardLayout cardLayout, JPanel mainPanel) {

        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));


        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.setBackground(new Color(30, 30, 30));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(40, 10, 20, 10));

        JLabel title = new JLabel("FITNESS KALORI HESAPLAMA", JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Verdana", Font.BOLD, 26));

        JLabel subtitle = new JLabel(
                "Egzersiz seç, süreni belirle ve yaktığın kaloriyi gör",
                JLabel.CENTER
        );
        subtitle.setForeground(Color.LIGHT_GRAY);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));

        headerPanel.add(title);
        headerPanel.add(subtitle);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(30, 30, 30));

        JButton startButton = new JButton("EGZERSİZE BAŞLA");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setBackground(new Color(0, 150, 136));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setPreferredSize(new Dimension(220, 50));

        startButton.addActionListener(e ->
                cardLayout.show(mainPanel, "EGZERSIZ")
        );

        buttonPanel.add(startButton);


        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(30, 30, 30));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        JLabel info = new JLabel("© 2026 Fitness Takip Sistemi", JLabel.CENTER);
        info.setForeground(Color.GRAY);
        info.setFont(new Font("Arial", Font.ITALIC, 12));

        footerPanel.add(info);


        add(headerPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }
}
