package usinginterface;

import database.AracDAO;

import javax.swing.*;
import java.awt.*;

public class EklePanel extends JPanel {
    private final AracDAO aracDAO;

    public EklePanel(AracDAO aracDAO) {
        this.aracDAO = aracDAO;

        setLayout(new GridLayout(6, 2));

        JLabel markaLabel = new JLabel("Marka:");
        JLabel modelLabel = new JLabel("Model:");
        JLabel yilLabel = new JLabel("Yıl:");
        JLabel kmLabel = new JLabel("Kilometre:");
        JLabel aciklamaLabel = new JLabel("Açıklama:");
        JTextField markaField = new JTextField();
        JTextField modelField = new JTextField();
        JTextField yilField = new JTextField();
        JTextField kmField = new JTextField();
        JTextField aciklamaField = new JTextField();
        JButton ekleButton = new JButton("Ekle");

        add(markaLabel);
        add(markaField);
        add(modelLabel);
        add(modelField);
        add(yilLabel);
        add(yilField);
        add(kmLabel);
        add(kmField);
        add(aciklamaLabel);
        add(aciklamaField);
        add(new JLabel()); // Boş yer tutucu
        add(ekleButton);

        ekleButton.addActionListener(e -> {
            try {
                String marka = markaField.getText().trim();
                String model = modelField.getText().trim();
                int yil = Integer.parseInt(yilField.getText().trim());
                int km = Integer.parseInt(kmField.getText().trim());
                String aciklama = aciklamaField.getText().trim();

                aracDAO.aracEkle(marka, model, yil, km, aciklama);
                JOptionPane.showMessageDialog(this, "Araç başarıyla eklendi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);

                // Alanları temizle
                markaField.setText("");
                modelField.setText("");
                yilField.setText("");
                kmField.setText("");
                aciklamaField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Lütfen geçerli değerler girin!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
