package org.example;

import java.util.ArrayList;
import java.util.List;

public class MagazaYonetici {
    private List<Magaza> magazalar = new ArrayList<Magaza>();

    public void magazaEkle(Magaza magaza) {
        if (magaza == null) {
            throw new MagazaYonetimiException(
                "Magaza bilgisi bos olamaz",
                MagazaYonetimiException.ValidationCategory.STORE_INFO,
                "magaza"
            );
        }
        if (magazaBul(magaza.getMagazaId()) != null) {
            throw new MagazaYonetimiException(
                "Bu ID'ye sahip bir magaza zaten mevcut: " + magaza.getMagazaId(),
                MagazaYonetimiException.ValidationCategory.STORE_INFO,
                "magazaId"
            );
        }
        magazalar.add(magaza);
    }

    public void magazaSil(String magazaId) {
        Magaza magaza = magazaBul(magazaId);
        if (magaza == null) {
            throw new MagazaYonetimiException(
                "Magaza bulunamadi: " + magazaId,
                MagazaYonetimiException.ValidationCategory.STORE_INFO,
                "magazaId"
            );
        }
        magazalar.remove(magaza);
    }

    public Magaza magazaBul(String magazaId) {
        if (magazaId == null || magazaId.trim().isEmpty()) {
            throw new MagazaYonetimiException(
                "Aranacak magaza ID'si bos olamaz",
                MagazaYonetimiException.ValidationCategory.STORE_INFO,
                "magazaId"
            );
        }
        return magazalar.stream()
            .filter(magaza -> magaza.getMagazaId().equals(magazaId))
            .findFirst()
            .orElse(null);
    }

    public void magazalariListele() {
        if (magazalar.isEmpty()) {
            System.out.println("\nHenuz magaza kaydi bulunmamaktadir.");
            return;
        }

        System.out.println("\n=== Magaza Listesi ===");
        System.out.println("----------------------------------------");
        magazalar.forEach(magaza -> {
            System.out.println("Magaza ID: " + magaza.getMagazaId());
            System.out.println("Magaza Adi: " + magaza.getMagazaAdi());
            System.out.println("Adres: " + magaza.getAdres());
            System.out.printf("Kira Tutari: %.2f TL\n", magaza.getKira());
            System.out.println("----------------------------------------");
        });
    }

    public class MagazaIstatistikleri {
        public void magazaSayisiniYazdir() {
            System.out.println("\n=== Magaza Sayisi ===");
            System.out.printf("Toplam Magaza Sayisi: %d\n", magazalar.size());
        }

        public void ortalamaKarHesapla() {
            if (magazalar.isEmpty()) {
                System.out.println("\nHenuz magaza kaydi bulunmamaktadir.");
                return;
            }

            double toplamKar = magazalar.stream()
                .mapToDouble(magaza -> magaza.netKarHesapla())
                .sum();
            double ortalamaKar = toplamKar / magazalar.size();

            System.out.println("\n=== Ortalama Kar Raporu ===");
            System.out.printf("Toplam Net Kar: %.2f TL\n", toplamKar);
            System.out.printf("Magaza Sayisi: %d\n", magazalar.size());
            System.out.printf("Magaza Basina Ortalama Kar: %.2f TL\n", ortalamaKar);
        }

        public void digerIstatistikler() {
            if (magazalar.isEmpty()) {
                System.out.println("\nHenuz magaza kaydi bulunmamaktadir.");
                return;
            }

            Magaza enYuksekKarliMagaza = magazalar.stream()
                .max((m1, m2) -> Double.compare(m1.netKarHesapla(), m2.netKarHesapla()))
                .get();

            Magaza enYuksekGiderliMagaza = magazalar.stream()
                .max((m1, m2) -> Double.compare(m1.giderHesapla(), m2.giderHesapla()))
                .get();

            System.out.println("\n=== Diger Istatistikler ===");
            System.out.println("En Yuksek Kar:");
            System.out.printf("Magaza: %s (ID: %s)\n", enYuksekKarliMagaza.getMagazaAdi(), enYuksekKarliMagaza.getMagazaId());
            System.out.printf("Net Kar: %.2f TL\n", enYuksekKarliMagaza.netKarHesapla());
            System.out.println("----------------------------------------");
            System.out.println("En Yuksek Gider:");
            System.out.printf("Magaza: %s (ID: %s)\n", enYuksekGiderliMagaza.getMagazaAdi(), enYuksekGiderliMagaza.getMagazaId());
            System.out.printf("Toplam Gider: %.2f TL\n", enYuksekGiderliMagaza.giderHesapla());
        }
    }
} 