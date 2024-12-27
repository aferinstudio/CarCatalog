package MainClass;

import database.AracDAO;
import usinginterface.*;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        AracDAO aracDAO = new AracDAO();

        setTitle("Araç Galerisi");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Araç Listele", new ListelePaneli(aracDAO));
        tabbedPane.addTab("Araç Güncelle", new GuncellemePaneli(aracDAO));
        tabbedPane.addTab("Araç Sil", new SilmePaneli(aracDAO));
        tabbedPane.addTab("Araç Ekle", new EklePanel(aracDAO));

        add(tabbedPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
