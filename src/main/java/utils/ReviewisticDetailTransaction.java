package utils;

public class ReviewisticDetailTransaction {
    public String  nomorTransaksi,id_produk,nama_produk,qty_produk,harga_produk;

    public ReviewisticDetailTransaction(String nomorTransaksi, String id_produk, String nama_produk, String qty_produk, String harga_produk) {
        this.nomorTransaksi = nomorTransaksi;
        this.id_produk = id_produk;
        this.nama_produk = nama_produk;
        this.qty_produk = qty_produk;
        this.harga_produk = harga_produk;
    }
}
