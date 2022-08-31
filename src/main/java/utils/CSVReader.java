package utils;


import PageObjects.Reviewstic.TransactionFragment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {


    public static List<ReviewisticTransaction> getReviewsticTransactionData(){
        List<ReviewisticTransaction> list = new ArrayList<>();
        //ReviewisticTransaction transaction = new ReviewisticTransaction()
        String line = "";
        String splitBy = ";";
        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/data/Transaksi-Grid view.csv"));
            while ((line = br.readLine()) != null)
            {
                String[] data = line.split(splitBy);
                //use comma as separator
                list.add(new ReviewisticTransaction(data[0] ,data[1],data[2],data[3],data[4] ,data[5],data[6],data[7] ));
           }
        }catch(IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<ReviewisticDetailTransaction> getReviewsticDetailTransactionData(){
        List<ReviewisticDetailTransaction> list = new ArrayList<>();
        //ReviewisticTransaction transaction = new ReviewisticTransaction()
        String line = "";
        String splitBy = ";";
        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/data/TransaksiDetail-Grid view.csv"));
            while ((line = br.readLine()) != null)
            {
                String[] data = line.split(splitBy);
                //use comma as separator
                list.add(new ReviewisticDetailTransaction(data[0] ,data[1],data[2],data[3],data[4]));
            }
        }catch(IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void getAllReview(){

    }
    public static int getReviewCount( String type , String rate){
        // contoh ada 5 untuk rating peniliaian service dengan nilai 2
        ArrayList<ReviewisticTransaction> list = (ArrayList<ReviewisticTransaction>) CSVReader.getReviewsticTransactionData();
        int angka =0;
        for(ReviewisticTransaction trans : list ){
            //System.out.println(trans.nomorTransaksi+"____"+trans.ratingService);
            if(trans.status.equalsIgnoreCase("2")){
                if(type.equalsIgnoreCase("Service") && rate.equalsIgnoreCase(trans.ratingService)){
                    angka++;
                }else if(type.equalsIgnoreCase("Price") && rate.equalsIgnoreCase(trans.ratingPrice)){
                    angka++;
                }else if(type.equalsIgnoreCase("Produk") && rate.equalsIgnoreCase(trans.ratingProduk)){
                    angka++;
                }else if (type.equalsIgnoreCase("All") && (  rate.equalsIgnoreCase(trans.ratingProduk) || rate.equalsIgnoreCase(trans.ratingPrice) || rate.equalsIgnoreCase(trans.ratingService)) ){
                    angka++;
                }
            }
        }

        return angka;
    }
    public static ReviewisticTransaction getTransactionByID(String id){
        List<ReviewisticTransaction> list = getReviewsticDetailTransactionData();
        ReviewisticTransaction result = null;
        for (ReviewisticTransaction  trans : list){
            if(trans.nomorTransaksi.equalsIgnoreCase(id)){
                result = trans;
                break;
            }
        }
        return result;
    }

}
