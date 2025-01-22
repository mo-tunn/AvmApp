package org.example;

public class MagazaYonetimiException extends RuntimeException {
    private ValidationCategory dogrulamaKategorisi;
    private String alanAdi;

    public enum ValidationCategory {
        STORE_INFO("Magaza Bilgisi"),
        TENANT_VIOLATION("Kiracı İhlali"),
        OPERATION_FAILED("İşlem Başarısız");

        private final String aciklama;

        ValidationCategory(String aciklama) {
            this.aciklama = aciklama;
        }

        public String getAciklama() {
            return aciklama;
        }
    }

    public MagazaYonetimiException(String mesaj, ValidationCategory dogrulamaKategorisi, String alanAdi) {
        super(mesaj);
        this.dogrulamaKategorisi = dogrulamaKategorisi;
        this.alanAdi = alanAdi;
    }

    public ValidationCategory getDogrulamaKategorisi() {
        return dogrulamaKategorisi;
    }

    public String getAlanAdi() {
        return alanAdi;
    }

    @Override
    public String toString() {
        return String.format("Magaza Yonetimi Hatasi [%s - Alan: %s]: %s",
            dogrulamaKategorisi.getAciklama(), alanAdi, getMessage());
    }
} 