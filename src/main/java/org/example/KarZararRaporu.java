package org.example;

import java.util.Date;

public class KarZararRaporu implements Rapor {
    private Magaza magaza;
    private Date baslangicTarihi;
    private Date bitisTarihi;
    private double toplamGelir;
    private double toplamGider;
    private double netKar;

    public KarZararRaporu(Magaza magaza, Date baslangicTarihi, Date bitisTarihi) {
        this.magaza = magaza;
        this.baslangicTarihi = baslangicTarihi;
        this.bitisTarihi = bitisTarihi;
    }

    @Override
    public void raporOlustur() {
        toplamGelir = magaza.gelirHesapla(baslangicTarihi, bitisTarihi);
        toplamGider = magaza.giderHesapla(baslangicTarihi, bitisTarihi);
        netKar = toplamGelir - toplamGider;
    }

    @Override
    public void raporuYazdir() {
        System.out.println("\n=== Kar/Zarar Raporu ===");
        System.out.println("Magaza: " + magaza.getMagazaAdi() + " (" + magaza.getMagazaId() + ")");
        System.out.printf("Dönem: %tF - %tF\n", baslangicTarihi, bitisTarihi);
        System.out.println("----------------------------------------");
        System.out.printf("Toplam Gelir: %.2f TL\n", toplamGelir);
        System.out.printf("Toplam Gider: %.2f TL\n", toplamGider);
        System.out.printf("Net Kar/Zarar: %.2f TL\n", netKar);
        System.out.println("Durum: " + (netKar >= 0 ? "KAR" : "ZARAR"));
    }

    public double getNetKar() {
        return netKar;
    }
} 