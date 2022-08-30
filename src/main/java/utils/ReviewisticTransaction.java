package utils;

public class ReviewisticTransaction {

    public String nomorTransaksi , review , ratingPrice, ratingService, ratingProduk ,id_toko , status , voucher_id;

    public ReviewisticTransaction(String nomorTransaksi, String review, String ratingPrice, String ratingService, String ratingProduk, String id_toko, String status, String voucher_id) {
        this.nomorTransaksi = nomorTransaksi;
        this.review = review;
        this.ratingPrice = ratingPrice;
        this.ratingService = ratingService;
        this.ratingProduk = ratingProduk;
        this.id_toko = id_toko;
        this.status = status;
        this.voucher_id = voucher_id;
    }

    @Override
    public String toString() {
        return "ReviewisticTransaction{" +
                "nomorTransaksi='" + nomorTransaksi + '\'' +
                ", review='" + review + '\'' +
                ", ratingPrice='" + ratingPrice + '\'' +
                ", ratingService='" + ratingService + '\'' +
                ", ratingProduk='" + ratingProduk + '\'' +
                ", id_toko='" + id_toko + '\'' +
                ", status='" + status + '\'' +
                ", voucher_id='" + voucher_id + '\'' +
                '}';
    }
}
