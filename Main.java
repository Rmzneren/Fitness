import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.Map;

public class Egzersiz extends JPanel {

    private JComboBox<String> exerciseCombo;
    private JSlider minuteSlider;
    private JLabel calorieLabel;
    private JLabel descriptionLabel;

    private DefaultListModel<String> historyModel;
    private JList<String> historyList;

    private Map<String, Integer> calorieMap = new HashMap<>();
    private Map<String, String> descriptionMap = new HashMap<>();

    private Map<String, Integer> chartData = new HashMap<>();
    private ChartPanel chartPanel;

    private JTextField customNameField;
    private JTextField customMinField;
    private JTextField customCalField;

    public Egzersiz(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 247, 250));


        calorieMap.put("Ip Atlama", 12);
        calorieMap.put("Kosu", 10);
        calorieMap.put("Yuruyus", 5);
        calorieMap.put("Bisiklet", 8);
        calorieMap.put("Sinav", 9);

        descriptionMap.put("Ip Atlama", "Yuksek tempolu kardiyo");
        descriptionMap.put("Kosu", "Dayaniklilik ve yag yakimi");
        descriptionMap.put("Yuruyus", "Hafif tempo");
        descriptionMap.put("Bisiklet", "Bacak kaslari");
        descriptionMap.put("Sinav", "Ust vucut");


        JPanel exerciseSelectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        exerciseSelectPanel.setBackground(getBackground());
        exerciseSelectPanel.setBorder(BorderFactory.createTitledBorder("Egzersiz Secimi & Hizli Kayit"));

        exerciseCombo = new JComboBox<>();
        calorieMap.keySet().forEach(exerciseCombo::addItem);
        exerciseCombo.setPreferredSize(new Dimension(120, 25));
        exerciseCombo.addActionListener(e -> updateDescription());

        JButton backBtn = new JButton("Ana Ekran");
        backBtn.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "ANA"));

        JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
        sep.setPreferredSize(new Dimension(2, 25));

        customNameField = createPlaceholderField("Isim", 7);
        customMinField = createPlaceholderField("Dk", 3);
        customCalField = createPlaceholderField("Kcal", 4);

        JButton fastAddBtn = new JButton("Ekle");
        fastAddBtn.setFont(new Font("Segoe UI", Font.BOLD, 11));
        fastAddBtn.setBackground(new Color(0, 121, 107));
        fastAddBtn.setForeground(Color.WHITE);

        exerciseSelectPanel.add(new JLabel("Egzersiz:"));
        exerciseSelectPanel.add(exerciseCombo);
        exerciseSelectPanel.add(backBtn);
        exerciseSelectPanel.add(sep);
        exerciseSelectPanel.add(new JLabel("Hizli Kayit:"));
        exerciseSelectPanel.add(customNameField);
        exerciseSelectPanel.add(customMinField);
        exerciseSelectPanel.add(customCalField);
        exerciseSelectPanel.add(fastAddBtn);

        add(exerciseSelectPanel, BorderLayout.NORTH);


        JPanel centerPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        minuteSlider = new JSlider(5, 120, 5);
        minuteSlider.setMajorTickSpacing(15);
        minuteSlider.setPaintTicks(true);
        minuteSlider.setPaintLabels(true);

        JButton calculateBtn = new JButton("Kalori Hesapla");
        calculateBtn.setBackground(new Color(0, 150, 136));
        calculateBtn.setForeground(Color.WHITE);

        calorieLabel = new JLabel("Sonuc: -", JLabel.CENTER);
        calorieLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        descriptionLabel = new JLabel("-", JLabel.CENTER);
        descriptionLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));

        centerPanel.add(new JLabel("Sure (dk):"));
        centerPanel.add(minuteSlider);
        centerPanel.add(calculateBtn);
        centerPanel.add(calorieLabel);
        centerPanel.add(descriptionLabel);

        add(centerPanel, BorderLayout.CENTER);


        historyModel = new DefaultListModel<>();
        historyList = new JList<>(historyModel);
        historyList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JButton deleteSelectedBtn = new JButton("Secilenleri Sil");
        JButton deleteAllBtn = new JButton("Tumunu Sil");

        deleteSelectedBtn.addActionListener(e -> deleteSelected());
        deleteAllBtn.addActionListener(e -> deleteAll());

        JPanel historyBtnPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        historyBtnPanel.add(deleteSelectedBtn);
        historyBtnPanel.add(deleteAllBtn);

        chartPanel = new ChartPanel();
        chartPanel.setPreferredSize(new Dimension(300, 200));

        JPanel historyPanel = new JPanel(new BorderLayout(10, 10));
        historyPanel.setBorder(BorderFactory.createTitledBorder("Egzersiz Gecmisi & Grafik"));
        historyPanel.add(chartPanel, BorderLayout.NORTH);
        historyPanel.add(new JScrollPane(historyList), BorderLayout.CENTER);
        historyPanel.add(historyBtnPanel, BorderLayout.SOUTH);

        add(historyPanel, BorderLayout.EAST);


        calculateBtn.addActionListener(e -> calculateCalories());

        fastAddBtn.addActionListener(e -> {
            try {
                String name = customNameField.getText();
                int min = Integer.parseInt(customMinField.getText());
                int cal = Integer.parseInt(customCalField.getText());

                historyModel.addElement(name + " - " + min + " dk - " + cal + " kcal");

                // Grafik verisini güncelle
                chartData.put(name, chartData.getOrDefault(name, 0) + cal);
                chartPanel.repaint();

                resetField(customNameField, "Isim");
                resetField(customMinField, "Dk");
                resetField(customCalField, "Kcal");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Lutfen dakika ve kaloriyi sayi girin!");
            }
        });
    }


    private JTextField createPlaceholderField(String placeholder, int columns) {
        JTextField field = new JTextField(placeholder, columns);
        field.setForeground(Color.GRAY);
        field.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
        return field;
    }

    private void resetField(JTextField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
    }

    private void calculateCalories() {
        String selected = (String) exerciseCombo.getSelectedItem();
        if (selected == null) return;

        int minutes = minuteSlider.getValue();
        int calories = minutes * calorieMap.get(selected);

        calorieLabel.setText(selected + " - " + calories + " kcal");
        historyModel.addElement(selected + " - " + minutes + " dk - " + calories + " kcal");


        chartData.put(selected, chartData.getOrDefault(selected, 0) + calories);
        chartPanel.repaint();
    }

    private void deleteSelected() {
        int[] indexes = historyList.getSelectedIndices();
        for (int i = indexes.length - 1; i >= 0; i--) {
            String item = historyModel.get(indexes[i]);
            historyModel.remove(indexes[i]);


            String[] parts = item.split(" - ");
            if (parts.length == 3) {
                String name = parts[0];
                chartData.remove(name);
            }
        }
        chartPanel.repaint();
    }

    private void deleteAll() {
        historyModel.clear();
        chartData.clear();
        chartPanel.repaint();
    }

    private void updateDescription() {
        String selected = (String) exerciseCombo.getSelectedItem();
        descriptionLabel.setText(selected == null ? "-" : descriptionMap.get(selected));
    }


    private class ChartPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (chartData.isEmpty()) return;

            int width = getWidth();
            int height = getHeight();
            int barWidth = width / chartData.size();
            int max = chartData.values().stream().max(Integer::compare).orElse(1);

            int x = 10;
            for (Map.Entry<String, Integer> e : chartData.entrySet()) {
                int barHeight = (int) ((double) e.getValue() / max * (height - 40));
                g.setColor(new Color(0, 150, 136));
                g.fillRect(x, height - barHeight - 20, barWidth - 20, barHeight);
                g.setColor(Color.BLACK);
                g.drawString(e.getKey(), x, height - 5);
                x += barWidth;
            }
        }
    }
}
