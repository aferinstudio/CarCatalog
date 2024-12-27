package models;

public class Arac {
    private int id;
    private String marka;
    private String model;
    private int yil;
    private int km;
    private String aciklama;

    public Arac(int id, String marka, String model, int yil, int km, String aciklama) {
        this.id = id;
        this.marka = marka;
        this.model = model;
        this.yil = yil;
        this.km = km;
        this.aciklama = aciklama;
    }

    // Getter ve Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMarka() { return marka; }
    public void setMarka(String marka) { this.marka = marka; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYil() { return yil; }
    public void setYil(int yil) { this.yil = yil; }

    public int getKm() { return km; }
    public void setKm(int km) { this.km = km; }

    public String getAciklama() { return aciklama; }
    public void setAciklama(String aciklama) { this.aciklama = aciklama; }

    @Override
    public String toString() {
        return marka + " " + model + " (" + yil + ") - " + km + " km: " + aciklama;
    }
}
