package org.example;

public class MuhasebeHatasiException extends RuntimeException {
    private ViolationType hataTipi;

    public enum ViolationType {
        NEGATIVE_AMOUNT("Negatif Miktar"),
        MISSING_DATA("Eksik Veri"),
        FUTURE_DATE("Gelecek Tarih"),
        INVALID_DATE("Geçersiz Tarih"),
        INVALID_TAX_RATE("Geçersiz Vergi Oranı");

        private final String aciklama;

        ViolationType(String aciklama) {
            this.aciklama = aciklama;
        }

        public String getAciklama() {
            return aciklama;
        }
    }

    public MuhasebeHatasiException(String mesaj, ViolationType hataTipi) {
        super(mesaj);
        this.hataTipi = hataTipi;
    }

    public ViolationType getHataTipi() {
        return hataTipi;
    }

    @Override
    public String toString() {
        return String.format("Muhasebe Hatasi [%s]: %s", hataTipi.getAciklama(), getMessage());
    }
} 