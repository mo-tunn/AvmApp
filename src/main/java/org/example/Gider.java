package org.example;

import java.util.Date;

public class Gider {
    private double miktar;
    private String aciklama;
    private Date tarih;

    public Gider(double miktar, String aciklama, Date tarih) {
        this.miktar = miktar;
        this.aciklama = aciklama;
        this.tarih = tarih;
    }

    public void detaylariYazdir() {
        System.out.printf("%tF: %.2f TL - %s\n", tarih, miktar, aciklama);
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
} 