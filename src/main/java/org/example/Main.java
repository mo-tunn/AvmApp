package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final MagazaYonetici magazaYonetici = new MagazaYonetici();
    private static final MagazaYonetici.MagazaIstatistikleri istatistikler = magazaYonetici.new MagazaIstatistikleri();

    public static void main(String[] args) {
        while (true) {
            try {
                anaMenuyuYazdir();
                int secim = intGirisiAl("Seciminiz: ");

                switch (secim) {
                    case 1:
                        magazaIslemleriniYonet();
                        break;
                    case 2:
                        finansalIslemleriYonet();
                        break;
                    case 3:
                        raporIslemleriniYonet();
                        break;
                    case 4:
                        istatistikIslemleriniYonet();
                        break;
                    case 0:
                        System.out.println("Program sonlandiriliyor...");
                        return;
                    default:
                        System.out.println("Gecersiz secim!");
                }
            } catch (Exception e) {
                System.out.println("Hata: " + e.getMessage());
                System.out.println("Ana menuye donuluyor...\n");
            }
        }
    }

    private static void anaMenuyuYazdir() {
        System.out.println("\n=== AVM Yonetim Sistemi ===");
        System.out.println("1. Magaza Islemleri");
        System.out.println("2. Finansal Islemler");
        System.out.println("3. Raporlama Islemleri");
        System.out.println("4. Istatistik Islemleri");
        System.out.println("0. Cikis");
        System.out.println("========================");
    }

    private static void magazaIslemleriniYonet() {
        while (true) {
            System.out.println("\n=== Magaza Islemleri ===");
            System.out.println("1. Yeni Magaza Ekle");
            System.out.println("2. Magaza Sil");
            System.out.println("3. Magaza Bilgilerini Guncelle");
            System.out.println("4. Magazalari Listele");
            System.out.println("0. Ana Menuye Don");
            System.out.println("=====================");

            int secim = intGirisiAl("Seciminiz: ");

            switch (secim) {
                case 1:
                    yeniMagazaEkle();
                    break;
                case 2:
                    magazaSil();
                    break;
                case 3:
                    magazaGuncelle();
                    break;
                case 4:
                    magazaYonetici.magazalariListele();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Gecersiz secim!");
            }
        }
    }

    private static void finansalIslemleriYonet() {
        while (true) {
            System.out.println("\n=== Finansal Islemler ===");
            System.out.println("1. Gelir Ekle");
            System.out.println("2. Gider Ekle");
            System.out.println("3. Gelirleri Goruntule");
            System.out.println("4. Giderleri Goruntule");
            System.out.println("5. Net Kari Goruntule");
            System.out.println("0. Ana Menuye Don");
            System.out.println("=====================");

            int secim = intGirisiAl("Seciminiz: ");

            switch (secim) {
                case 1:
                    gelirEkle();
                    break;
                case 2:
                    giderEkle();
                    break;
                case 3:
                    gelirleriGoruntule();
                    break;
                case 4:
                    giderleriGoruntule();
                    break;
                case 5:
                    netKariGoruntule();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Gecersiz secim!");
            }
        }
    }

    private static void raporIslemleriniYonet() {
        while (true) {
            System.out.println("\n=== Raporlama Islemleri ===");
            System.out.println("1. Kar-Zarar Raporu");
            System.out.println("2. Vergi Raporu");
            System.out.println("3. Tarih Aralikli Kar-Zarar Raporu");
            System.out.println("4. Tarih Aralikli Vergi Raporu");
            System.out.println("0. Ana Menuye Don");
            System.out.println("=========================");

            int secim = intGirisiAl("Seciminiz: ");

            switch (secim) {
                case 1:
                    karZararRaporuOlustur();
                    break;
                case 2:
                    vergiRaporuOlustur();
                    break;
                case 3:
                    tarihAralikliKarZararRaporuOlustur();
                    break;
                case 4:
                    tarihAralikliVergiRaporuOlustur();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Gecersiz secim!");
            }
        }
    }

    private static void istatistikIslemleriniYonet() {
        while (true) {
            System.out.println("\n=== Istatistik Islemleri ===");
            System.out.println("1. Toplam Magaza Sayisi");
            System.out.println("2. Ortalama Kar Hesapla");
            System.out.println("3. Diger Istatistikler");
            System.out.println("0. Ana Menuye Don");
            System.out.println("========================");

            int secim = intGirisiAl("Seciminiz: ");

            switch (secim) {
                case 1:
                    istatistikler.magazaSayisiniYazdir();
                    break;
                case 2:
                    istatistikler.ortalamaKarHesapla();
                    break;
                case 3:
                    istatistikler.digerIstatistikler();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Gecersiz secim!");
            }
        }
    }

    private static void yeniMagazaEkle() {
        System.out.println("\n=== Yeni Magaza Ekleme ===");
        String magazaId = stringGirisiAl("Magaza ID: ");
        String magazaAdi = stringGirisiAl("Magaza Adi: ");
        String adres = stringGirisiAl("Adres: ");
        double kira = doubleGirisiAl("Kira Tutari: ");

        Magaza magaza = new Magaza(magazaId, magazaAdi, adres, kira);
        magazaYonetici.magazaEkle(magaza);
        System.out.println("Magaza basariyla eklendi.");
    }

    private static void magazaSil() {
        System.out.println("\n=== Magaza Silme ===");
        String magazaId = stringGirisiAl("Silinecek Magaza ID: ");
        magazaYonetici.magazaSil(magazaId);
        System.out.println("Magaza basariyla silindi.");
    }

    private static void magazaGuncelle() {
        System.out.println("\n=== Magaza Guncelleme ===");
        String magazaId = stringGirisiAl("Guncellenecek Magaza ID: ");
        Magaza magaza = magazaYonetici.magazaBul(magazaId);
        
        if (magaza != null) {
            System.out.println("1. Magaza Adini Guncelle");
            System.out.println("2. Adresi Guncelle");
            System.out.println("3. Kira Tutarini Guncelle");
            int secim = intGirisiAl("Seciminiz: ");

            switch (secim) {
                case 1:
                    String yeniAd = stringGirisiAl("Yeni Magaza Adi: ");
                    magaza.setMagazaAdi(yeniAd);
                    break;
                case 2:
                    String yeniAdres = stringGirisiAl("Yeni Adres: ");
                    magaza.setAdres(yeniAdres);
                    break;
                case 3:
                    double yeniKira = doubleGirisiAl("Yeni Kira Tutari: ");
                    magaza.setKira(yeniKira);
                    break;
                default:
                    System.out.println("Gecersiz secim!");
                    return;
            }
            System.out.println("Magaza bilgileri guncellendi.");
        } else {
            System.out.println("Magaza bulunamadi!");
        }
    }

    private static void gelirEkle() {
        try {
            System.out.println("\n=== Gelir Ekleme ===");
            String magazaId = stringGirisiAl("Magaza ID: ");
            Magaza magaza = magazaYonetici.magazaBul(magazaId);
            
            if (magaza != null) {
                double miktar = doubleGirisiAl("Gelir Tutari: ");
                String aciklama = stringGirisiAl("Aciklama: ");
                Date tarih = tarihGirisiAl("Tarih (GG/AA/YYYY): ");
                
                magaza.gelirEkle(miktar, aciklama, tarih);
                System.out.println("Gelir basariyla eklendi.");
            } else {
                System.out.println("Magaza bulunamadi!");
            }
        } catch (Exception e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }

    private static void giderEkle() {
        try {
            System.out.println("\n=== Gider Ekleme ===");
            String magazaId = stringGirisiAl("Magaza ID: ");
            Magaza magaza = magazaYonetici.magazaBul(magazaId);
            
            if (magaza != null) {
                double miktar = doubleGirisiAl("Gider Tutari: ");
                String aciklama = stringGirisiAl("Aciklama: ");
                Date tarih = tarihGirisiAl("Tarih (GG/AA/YYYY): ");
                
                magaza.giderEkle(miktar, aciklama, tarih);
                System.out.println("Gider basariyla eklendi.");
            } else {
                System.out.println("Magaza bulunamadi!");
            }
        } catch (Exception e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }

    private static void gelirleriGoruntule() {
        System.out.println("\n=== Gelirleri Goruntule ===");
        String magazaId = stringGirisiAl("Magaza ID: ");
        Magaza magaza = magazaYonetici.magazaBul(magazaId);
        
        if (magaza != null) {
            System.out.println("1. Tum Gelirler");
            System.out.println("2. Tarih Aralikli Gelirler");
            int secim = intGirisiAl("Seciminiz: ");

            switch (secim) {
                case 1:
                    magaza.gelirleriYazdir();
                    break;
                case 2:
                    Date baslangicTarihi = tarihGirisiAl("Baslangic Tarihi (GG/AA/YYYY): ");
                    Date bitisTarihi = tarihGirisiAl("Bitis Tarihi (GG/AA/YYYY): ");
                    magaza.gelirleriYazdir(baslangicTarihi, bitisTarihi);
                    break;
                default:
                    System.out.println("Gecersiz secim!");
            }
        } else {
            System.out.println("Magaza bulunamadi!");
        }
    }

    private static void giderleriGoruntule() {
        System.out.println("\n=== Giderleri Goruntule ===");
        String magazaId = stringGirisiAl("Magaza ID: ");
        Magaza magaza = magazaYonetici.magazaBul(magazaId);
        
        if (magaza != null) {
            System.out.println("1. Tum Giderler");
            System.out.println("2. Tarih Aralikli Giderler");
            int secim = intGirisiAl("Seciminiz: ");

            switch (secim) {
                case 1:
                    magaza.giderleriYazdir();
                    break;
                case 2:
                    Date baslangicTarihi = tarihGirisiAl("Baslangic Tarihi (GG/AA/YYYY): ");
                    Date bitisTarihi = tarihGirisiAl("Bitis Tarihi (GG/AA/YYYY): ");
                    magaza.giderleriYazdir(baslangicTarihi, bitisTarihi);
                    break;
                default:
                    System.out.println("Gecersiz secim!");
            }
        } else {
            System.out.println("Magaza bulunamadi!");
        }
    }

    private static void karZararRaporuOlustur() {
        System.out.println("\n=== Kar-Zarar Raporu Olustur ===");
        String magazaId = stringGirisiAl("Magaza ID: ");
        Magaza magaza = magazaYonetici.magazaBul(magazaId);
        
        if (magaza != null) {
            Date baslangicTarihi = tarihGirisiAl("Baslangic Tarihi (GG/AA/YYYY): ");
            Date bitisTarihi = tarihGirisiAl("Bitis Tarihi (GG/AA/YYYY): ");
            
            KarZararRaporu rapor = new KarZararRaporu(magaza, baslangicTarihi, bitisTarihi);
            rapor.raporOlustur();
            rapor.raporuYazdir();
        } else {
            System.out.println("Magaza bulunamadi!");
        }
    }

    private static void vergiRaporuOlustur() {
        System.out.println("\n=== Vergi Raporu Olustur ===");
        String magazaId = stringGirisiAl("Magaza ID: ");
        Magaza magaza = magazaYonetici.magazaBul(magazaId);
        
        if (magaza != null) {
            Date baslangicTarihi = tarihGirisiAl("Baslangic Tarihi (GG/AA/YYYY): ");
            Date bitisTarihi = tarihGirisiAl("Bitis Tarihi (GG/AA/YYYY): ");
            
            VergiRaporu rapor = new VergiRaporu(magaza, baslangicTarihi, bitisTarihi);
            rapor.raporOlustur();
            rapor.raporuYazdir();
        } else {
            System.out.println("Magaza bulunamadi!");
        }
    }

    private static void tarihAralikliKarZararRaporuOlustur() {
        System.out.println("\n=== Tarih Aralikli Kar-Zarar Raporu ===");
        String magazaId = stringGirisiAl("Magaza ID: ");
        Magaza magaza = magazaYonetici.magazaBul(magazaId);
        
        if (magaza != null) {
            Date baslangicTarihi = tarihGirisiAl("Baslangic Tarihi (GG/AA/YYYY): ");
            Date bitisTarihi = tarihGirisiAl("Bitis Tarihi (GG/AA/YYYY): ");
            
            KarZararRaporu rapor = new KarZararRaporu(magaza, baslangicTarihi, bitisTarihi);
            rapor.raporOlustur();
            rapor.raporuYazdir();
        } else {
            System.out.println("Magaza bulunamadi!");
        }
    }

    private static void tarihAralikliVergiRaporuOlustur() {
        System.out.println("\n=== Tarih Aralikli Vergi Raporu ===");
        String magazaId = stringGirisiAl("Magaza ID: ");
        Magaza magaza = magazaYonetici.magazaBul(magazaId);
        
        if (magaza != null) {
            Date baslangicTarihi = tarihGirisiAl("Baslangic Tarihi (GG/AA/YYYY): ");
            Date bitisTarihi = tarihGirisiAl("Bitis Tarihi (GG/AA/YYYY): ");
            
            VergiRaporu rapor = new VergiRaporu(magaza, baslangicTarihi, bitisTarihi);
            rapor.raporOlustur();
            rapor.raporuYazdir();
        } else {
            System.out.println("Magaza bulunamadi!");
        }
    }

    private static void netKariGoruntule() {
        System.out.println("\n=== Net Kar Goruntuleme ===");
        String magazaId = stringGirisiAl("Magaza ID: ");
        Magaza magaza = magazaYonetici.magazaBul(magazaId);
        
        if (magaza != null) {
            System.out.println("1. Toplam Net Kar");
            System.out.println("2. Tarih Aralikli Net Kar");
            int secim = intGirisiAl("Seciminiz: ");

            switch (secim) {
                case 1:
                    double netKar = magaza.netKarHesapla();
                    System.out.printf("\nToplam Net Kar: %.2f TL\n", netKar);
                    break;
                case 2:
                    Date baslangicTarihi = tarihGirisiAl("Baslangic Tarihi (GG/AA/YYYY): ");
                    Date bitisTarihi = tarihGirisiAl("Bitis Tarihi (GG/AA/YYYY): ");
                    double donemNetKar = magaza.netKarHesapla(baslangicTarihi, bitisTarihi);
                    System.out.printf("\nDonem Net Kar: %.2f TL\n", donemNetKar);
                    break;
                default:
                    System.out.println("Gecersiz secim!");
            }
        } else {
            System.out.println("Magaza bulunamadi!");
        }
    }

    private static String stringGirisiAl(String mesaj) {
        System.out.print(mesaj);
        return scanner.nextLine().trim();
    }

    private static int intGirisiAl(String mesaj) {
        while (true) {
            try {
                System.out.print(mesaj);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lutfen gecerli bir sayi giriniz!");
            }
        }
    }

    private static double doubleGirisiAl(String mesaj) {
        while (true) {
            try {
                System.out.print(mesaj);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lutfen gecerli bir sayi giriniz!");
            }
        }
    }

    private static Date tarihGirisiAl(String mesaj) {
        while (true) {
            try {
                System.out.print(mesaj);
                return dateFormat.parse(scanner.nextLine().trim());
            } catch (ParseException e) {
                System.out.println("Lutfen GG/AA/YYYY formatinda bir tarih giriniz!");
            }
        }
    }
}