package usinginterface;

import database.AracDAO;
import models.Arac;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListelePaneli extends JPanel {
    private final AracDAO aracDAO;
    private final JTable table;

    public ListelePaneli(AracDAO aracDAO) {
        this.aracDAO = aracDAO;
        setLayout(new BorderLayout());

        // Tablo başlıkları
        String[] columnNames = {"ID", "Marka", "Model", "Yıl"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        // Araçları listeleme
        JButton detayButton = new JButton("Seçili Aracın Detayları");
        detayButton.addActionListener(e -> detaylariGoster());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(detayButton, BorderLayout.SOUTH);

        araclariListele();
    }

    private void araclariListele() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Önce tabloyu temizle
        List<Arac> araclar = aracDAO.araclariGetir();
        for (Arac arac : araclar) {
            model.addRow(new Object[]{arac.getId(), arac.getMarka(), arac.getModel(), arac.getYil()});
        }
    }

    private void detaylariGoster() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) table.getValueAt(selectedRow, 0);
            Arac seciliArac = aracDAO.araciGetir(id);
            if (seciliArac != null) {
                String detaylar = "ID: " + seciliArac.getId() + "\n"
                        + "Marka: " + seciliArac.getMarka() + "\n"
                        + "Model: " + seciliArac.getModel() + "\n"
                        + "Yıl: " + seciliArac.getYil() + "\n"
                        + "Kilometre: " + seciliArac.getKm() + "\n"
                        + "Açıklama: " + seciliArac.getAciklama();
                JOptionPane.showMessageDialog(this, detaylar, "Araç Detayları", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Lütfen bir araç seçin!", "Uyarı", JOptionPane.WARNING_MESSAGE);
        }
    }
}
