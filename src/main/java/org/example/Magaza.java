package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Magaza {
    private final String magazaId;
    private String magazaAdi;
    private String adres;
    private double kira;
    private List<Gelir> gelirListesi = new ArrayList<Gelir>();
    private List<Gider> giderListesi = new ArrayList<Gider>();

    public Magaza(String magazaId, String magazaAdi, String adres, double kira) {
        if (magazaId == null || magazaId.trim().isEmpty()) {
            throw new MagazaYonetimiException(
                "Magaza ID'si bos olamaz",
                MagazaYonetimiException.ValidationCategory.STORE_INFO,
                "magazaId"
            );
        }
        if (magazaAdi == null || magazaAdi.trim().isEmpty()) {
            throw new MagazaYonetimiException(
                "Magaza adi bos olamaz",
                MagazaYonetimiException.ValidationCategory.STORE_INFO,
                "magazaAdi"
            );
        }
        if (adres == null || adres.trim().isEmpty()) {
            throw new MagazaYonetimiException(
                "Magaza adresi bos olamaz",
                MagazaYonetimiException.ValidationCategory.STORE_INFO,
                "adres"
            );
        }
        if (kira < 0) {
            throw new MagazaYonetimiException(
                "Kira tutari negatif olamaz: " + kira,
                MagazaYonetimiException.ValidationCategory.TENANT_VIOLATION,
                "kira"
            );
        }

        this.magazaId = magazaId;
        this.magazaAdi = magazaAdi;
        this.adres = adres;
        this.kira = kira;
    }

    public void gelirEkle(double miktar, String aciklama, Date tarih) {
        if (miktar <= 0) {
            throw new MuhasebeHatasiException(
                "Gelir tutari sifir veya negatif olamaz: " + miktar,
                MuhasebeHatasiException.ViolationType.NEGATIVE_AMOUNT
            );
        }
        if (tarih == null) {
            throw new MuhasebeHatasiException(
                "Gelir tarihi bos olamaz",
                MuhasebeHatasiException.ViolationType.MISSING_DATA
            );
        }
        if (tarih.after(new Date())) {
            throw new MuhasebeHatasiException(
                "Gelir tarihi gelecek bir tarih olamaz: " + tarih,
                MuhasebeHatasiException.ViolationType.FUTURE_DATE
            );
        }
        Gelir gelir = new Gelir(miktar, aciklama, tarih);
        gelirListesi.add(gelir);
    }

    public void giderEkle(double miktar, String aciklama, Date tarih) {
        if (miktar <= 0) {
            throw new MuhasebeHatasiException(
                "Gider tutari sifir veya negatif olamaz: " + miktar,
                MuhasebeHatasiException.ViolationType.NEGATIVE_AMOUNT
            );
        }
        if (tarih == null) {
            throw new MuhasebeHatasiException(
                "Gider tarihi bos olamaz",
                MuhasebeHatasiException.ViolationType.MISSING_DATA
            );
        }
        if (tarih.after(new Date())) {
            throw new MuhasebeHatasiException(
                "Gider tarihi gelecek bir tarih olamaz: " + tarih,
                MuhasebeHatasiException.ViolationType.FUTURE_DATE
            );
        }
        Gider gider = new Gider(miktar, aciklama, tarih);
        giderListesi.add(gider);
    }

    public double gelirHesapla() {
        return gelirListesi.stream().mapToDouble(gelir -> gelir.getMiktar()).sum();
    }

    public double gelirHesapla(Date baslangicTarihi, Date bitisTarihi) {
        tarihAraliginiDogrula(baslangicTarihi, bitisTarihi);
        return gelirListesi.stream()
            .filter(gelir -> !gelir.getTarih().before(baslangicTarihi) && !gelir.getTarih().after(bitisTarihi))
            .mapToDouble(gelir -> gelir.getMiktar())
            .sum();
    }

    public double giderHesapla() {
        return giderListesi.stream().mapToDouble(gider -> gider.getMiktar()).sum();
    }

    public double giderHesapla(Date baslangicTarihi, Date bitisTarihi) {
        tarihAraliginiDogrula(baslangicTarihi, bitisTarihi);
        return giderListesi.stream()
            .filter(gider -> !gider.getTarih().before(baslangicTarihi) && !gider.getTarih().after(bitisTarihi))
            .mapToDouble(gider -> gider.getMiktar())
            .sum();
    }

    public double netKarHesapla() {
        return gelirHesapla() - giderHesapla();
    }

    public double netKarHesapla(Date baslangicTarihi, Date bitisTarihi) {
        if (bitisTarihi.before(baslangicTarihi)) {
            throw new MuhasebeHatasiException(
                "Bitis tarihi baslangic tarihinden once olamaz",
                MuhasebeHatasiException.ViolationType.INVALID_DATE
            );
        }
        return gelirHesapla(baslangicTarihi, bitisTarihi) - giderHesapla(baslangicTarihi, bitisTarihi);
    }

    public void gelirleriYazdir() {
        if (gelirListesi.isEmpty()) {
            System.out.println("\nHenüz gelir kaydı bulunmamaktadır.");
            return;
        }

        System.out.println("\n=== Gelir Listesi ===");
        System.out.println("Magaza: " + magazaAdi + " (" + magazaId + ")");
        System.out.println("----------------------------------------");
        
        gelirListesi.forEach(gelir -> gelir.detaylariYazdir());
        
        System.out.println("----------------------------------------");
        System.out.printf("Toplam Gelir: %.2f TL\n", gelirHesapla());
    }

    public void gelirleriYazdir(Date baslangicTarihi, Date bitisTarihi) {
        tarihAraliginiDogrula(baslangicTarihi, bitisTarihi);
        if (gelirListesi.isEmpty()) {
            System.out.println("\nHenüz gelir kaydı bulunmamaktadır.");
            return;
        }

        System.out.println("\n=== Gelir Listesi (Tarih Aralıklı) ===");
        System.out.println("Magaza: " + magazaAdi + " (" + magazaId + ")");
        System.out.printf("Dönem: %tF - %tF\n", baslangicTarihi, bitisTarihi);
        System.out.println("----------------------------------------");
        
        List<Gelir> filtrelenmisGelirler = gelirListesi.stream()
            .filter(gelir -> !gelir.getTarih().before(baslangicTarihi) && !gelir.getTarih().after(bitisTarihi))
            .collect(java.util.stream.Collectors.toList());

        if (filtrelenmisGelirler.isEmpty()) {
            System.out.println("Bu tarih aralığında gelir kaydı bulunmamaktadır.");
            return;
        }

        filtrelenmisGelirler.forEach(gelir -> gelir.detaylariYazdir());
        
        System.out.println("----------------------------------------");
        System.out.printf("Dönem Toplam Gelir: %.2f TL\n", gelirHesapla(baslangicTarihi, bitisTarihi));
    }

    public void giderleriYazdir() {
        if (giderListesi.isEmpty()) {
            System.out.println("\nHenüz gider kaydı bulunmamaktadır.");
            return;
        }

        System.out.println("\n=== Gider Listesi ===");
        System.out.println("Magaza: " + magazaAdi + " (" + magazaId + ")");
        System.out.println("----------------------------------------");
        System.out.printf("Sabit Gider (Kira): %.2f TL\n", kira);
        System.out.println("----------------------------------------");
        
        giderListesi.forEach(gider -> gider.detaylariYazdir());
        
        System.out.println("----------------------------------------");
        System.out.printf("Toplam Gider: %.2f TL\n", giderHesapla());
    }

    public void giderleriYazdir(Date baslangicTarihi, Date bitisTarihi) {
        tarihAraliginiDogrula(baslangicTarihi, bitisTarihi);
        if (giderListesi.isEmpty()) {
            System.out.println("\nHenüz gider kaydı bulunmamaktadır.");
            return;
        }

        System.out.println("\n=== Gider Listesi (Tarih Aralıklı) ===");
        System.out.println("Magaza: " + magazaAdi + " (" + magazaId + ")");
        System.out.printf("Dönem: %tF - %tF\n", baslangicTarihi, bitisTarihi);
        System.out.println("----------------------------------------");
        System.out.printf("Sabit Gider (Kira): %.2f TL\n", kira);
        System.out.println("----------------------------------------");
        
        List<Gider> filtrelenmisGiderler = giderListesi.stream()
            .filter(gider -> !gider.getTarih().before(baslangicTarihi) && !gider.getTarih().after(bitisTarihi))
            .collect(java.util.stream.Collectors.toList());

        if (filtrelenmisGiderler.isEmpty()) {
            System.out.println("Bu tarih aralığında gider kaydı bulunmamaktadır.");
            return;
        }

        filtrelenmisGiderler.forEach(gider -> gider.detaylariYazdir());
        
        System.out.println("----------------------------------------");
        System.out.printf("Dönem Toplam Gider: %.2f TL\n", giderHesapla(baslangicTarihi, bitisTarihi));
    }

    public String getMagazaId() {
        return magazaId;
    }

    public String getMagazaAdi() {
        return magazaAdi;
    }

    public void setMagazaAdi(String ad) {
        if (ad == null || ad.trim().isEmpty()) {
            throw new MagazaYonetimiException(
                "Magaza adi bos olamaz",
                MagazaYonetimiException.ValidationCategory.STORE_INFO,
                "magazaAdi"
            );
        }
        this.magazaAdi = ad;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        if (adres == null || adres.trim().isEmpty()) {
            throw new MagazaYonetimiException(
                "Magaza adresi bos olamaz",
                MagazaYonetimiException.ValidationCategory.STORE_INFO,
                "adres"
            );
        }
        this.adres = adres;
    }

    public double getKira() {
        return kira;
    }

    public void setKira(double kira) {
        if (kira < 0) {
            throw new MagazaYonetimiException(
                "Kira tutari negatif olamaz: " + kira,
                MagazaYonetimiException.ValidationCategory.TENANT_VIOLATION,
                "kira"
            );
        }
        this.kira = kira;
    }

    private void tarihAraliginiDogrula(Date baslangicTarihi, Date bitisTarihi) {
        if (baslangicTarihi == null || bitisTarihi == null) {
            throw new MuhasebeHatasiException(
                "Tarih araligi bos olamaz",
                MuhasebeHatasiException.ViolationType.MISSING_DATA
            );
        }
        if (bitisTarihi.before(baslangicTarihi)) {
            throw new MuhasebeHatasiException(
                "Bitis tarihi baslangic tarihinden once olamaz",
                MuhasebeHatasiException.ViolationType.INVALID_DATE
            );
        }
    }
} 