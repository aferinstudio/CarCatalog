package usinginterface;

import database.AracDAO;
import models.Arac;

import javax.swing.*;
import java.awt.*;

public class GuncellemePaneli extends JPanel {
    private final JTextField idField, markaField, modelField, yilField, kmField, aciklamaField;

    public GuncellemePaneli(AracDAO aracDAO) {
        setLayout(new GridLayout(7, 2));

        idField = new JTextField();
        markaField = new JTextField();
        modelField = new JTextField();
        yilField = new JTextField();
        kmField = new JTextField();
        aciklamaField = new JTextField();
        JButton guncelleButton = new JButton("Güncelle");
        JButton getirButton = new JButton("Araç Bilgilerini Getir");

        add(new JLabel("Araç ID:"));
        add(idField);
        add(new JLabel("Marka:"));
        add(markaField);
        add(new JLabel("Model:"));
        add(modelField);
        add(new JLabel("Yıl:"));
        add(yilField);
        add(new JLabel("Kilometre:"));
        add(kmField);
        add(new JLabel("Açıklama:"));
        add(aciklamaField);
        add(getirButton);
        add(guncelleButton);

        getirButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText().trim());
            Arac arac = aracDAO.araciGetir(id);
            if (arac != null) {
                markaField.setText(arac.getMarka());
                modelField.setText(arac.getModel());
                yilField.setText(String.valueOf(arac.getYil()));
                kmField.setText(String.valueOf(arac.getKm()));
                aciklamaField.setText(arac.getAciklama());
            } else {
                JOptionPane.showMessageDialog(this, "Araç bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        guncelleButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText().trim());
            String marka = markaField.getText().trim();
            String model = modelField.getText().trim();
            int yil = Integer.parseInt(yilField.getText().trim());
            int km = Integer.parseInt(kmField.getText().trim());
            String aciklama = aciklamaField.getText().trim();

            aracDAO.araciGuncelle(id, marka, model, yil, km, aciklama);
            JOptionPane.showMessageDialog(this, "Araç başarıyla güncellendi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
