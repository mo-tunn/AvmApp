package org.example;

import java.util.Date;

public class FinansalKayit {
    private String kayitId;
    private String magazaId;
    private double miktar;
    private String aciklama;
    private Date tarih;
    private KayitTipi tip;

    public enum KayitTipi {
        GELIR,
        GIDER
    }

    public FinansalKayit(String kayitId, String magazaId, double miktar, String aciklama, Date tarih, KayitTipi tip) {
        this.kayitId = kayitId;
        this.magazaId = magazaId;
        this.miktar = miktar;
        this.aciklama = aciklama;
        this.tarih = tarih;
        this.tip = tip;
    }

    public String getKayitId() {
        return kayitId;
    }

    public String getMagazaId() {
        return magazaId;
    }

    public double getMiktar() {
        return miktar;
    }

    public String getAciklama() {
        return aciklama;
    }

    public Date getTarih() {
        return tarih;
    }

    public KayitTipi getTip() {
        return tip;
    }

    public void detaylariYazdir() {
        System.out.printf("Kayit ID: %s\n", kayitId);
        System.out.printf("Magaza ID: %s\n", magazaId);
        System.out.printf("Miktar: %.2f TL\n", miktar);
        System.out.printf("Aciklama: %s\n", aciklama);
        System.out.printf("Tarih: %tF\n", tarih);
        System.out.printf("Tip: %s\n", tip);
    }
} 