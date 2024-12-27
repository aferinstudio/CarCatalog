package usinginterface;

import database.AracDAO;
import models.Arac;

import javax.swing.*;
import java.awt.*;

public class SilmePaneli extends JPanel {
    private final AracDAO aracDAO;

    public SilmePaneli(AracDAO aracDAO) {
        this.aracDAO = aracDAO;

        setLayout(new GridLayout(3, 2));

        JLabel markaLabel = new JLabel("Marka:");
        JLabel modelLabel = new JLabel("Model:");
        JTextField markaField = new JTextField();
        JTextField modelField = new JTextField();
        JButton silButton = new JButton("Sil");

        add(markaLabel);
        add(markaField);
        add(modelLabel);
        add(modelField);
        add(new JLabel()); // Boş yer tutucu
        add(silButton);

        silButton.addActionListener(e -> {
            String marka =markaField.getText().trim();
            String model = modelField.getText().trim();

            if (marka.isEmpty() || model.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Marka ve Model bilgilerini doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Eşleşen araç bilgilerini al
            Arac arac = aracDAO.aracGetirByMarkaModel(marka, model);
            if (arac != null) {
                int onay = JOptionPane.showConfirmDialog(
                        this,
                        "Araç bilgileri:\n" + arac.toString() + "\nBu aracı silmek istiyor musunuz?",
                        "Onay",
                        JOptionPane.YES_NO_OPTION
                );

                if (onay == JOptionPane.YES_OPTION) {
                    aracDAO.aracSil(arac.getId());
                    JOptionPane.showMessageDialog(this, "Araç başarıyla silindi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Eşleşen araç bulunamadı.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
