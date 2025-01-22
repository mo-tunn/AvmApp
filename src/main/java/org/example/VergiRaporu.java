package org.example;

import java.util.Date;

public class VergiRaporu implements Rapor {
    private Magaza magaza;
    private Date baslangicTarihi;
    private Date bitisTarihi;
    private double toplamGelir;
    private double toplamGider;
    private double vergiOrani = 0.18; // KDV oranı
    private double hesaplananVergi;

    public VergiRaporu(Magaza magaza, Date baslangicTarihi, Date bitisTarihi) {
        this.magaza = magaza;
        this.baslangicTarihi = baslangicTarihi;
        this.bitisTarihi = bitisTarihi;
    }

    @Override
    public void raporOlustur() {
        toplamGelir = magaza.gelirHesapla(baslangicTarihi, bitisTarihi);
        toplamGider = magaza.giderHesapla(baslangicTarihi, bitisTarihi);
        hesaplananVergi = (toplamGelir - toplamGider) * vergiOrani;
    }

    @Override
    public void raporuYazdir() {
        System.out.println("\n=== Vergi Raporu ===");
        System.out.println("Magaza: " + magaza.getMagazaAdi() + " (" + magaza.getMagazaId() + ")");
        System.out.printf("Dönem: %tF - %tF\n", baslangicTarihi, bitisTarihi);
        System.out.println("----------------------------------------");
        System.out.printf("Toplam Gelir: %.2f TL\n", toplamGelir);
        System.out.printf("Toplam Gider: %.2f TL\n", toplamGider);
        System.out.printf("Vergi Matrahı: %.2f TL\n", toplamGelir - toplamGider);
        System.out.printf("Vergi Oranı: %%%.1f\n", vergiOrani * 100);
        System.out.printf("Hesaplanan Vergi: %.2f TL\n", hesaplananVergi);
    }

    public void setVergiOrani(double oran) {
        if (oran < 0 || oran > 1) {
            throw new MuhasebeHatasiException(
                "Vergi orani 0 ile 1 arasinda olmalidir: " + oran,
                MuhasebeHatasiException.ViolationType.INVALID_TAX_RATE
            );
        }
        this.vergiOrani = oran;
    }

    public double getHesaplananVergi() {
        return hesaplananVergi;
    }
} 