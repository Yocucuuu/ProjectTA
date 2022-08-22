package scraps;

import org.json.simple.parser.ParseException;
import utils.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class scrapClass {


    public static Object[][] passData() throws IOException, ParseException {
        return JsonReader.getJSONdata(System.getProperty("user.dir") + "/data/kunciSejarahkita.json"
                        , "kunci", new String[]{"pertanyaan_kalimat", "kunci_jawaban"});
    }

    public static void main(String[] args) throws IOException, ParseException {
        String lat = "-7.2912683°";
        String lon ="112.75883°";
        String reverse ="ID Gedung B iSTTS, Jalan Ngagel Madya III, RW 01, Baratajaya, Gubeng, Surabaya, East Java, 60284, Indonesia";
        System.out.println(lat);
        System.out.println(lon);
        System.out.println(reverse);
        lat = lat.substring(0,lat.length()-1);
        lon = lon.substring(0,lon.length()-1);
        System.out.println(Double.parseDouble(lat));
        System.out.println(Double.parseDouble(lon));
        System.out.println(reverse.contains("iSTTS"));
//        URL url = new URL("https://www.sejarahkita.my.id/resetLogins");
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setConnectTimeout(100000);
//        con.setReadTimeout(100000);
//        con.setInstanceFollowRedirects(true);
//        System.out.println(con.getResponseCode());

//        InputStream response = con.getInputStream();
//        try (Scanner scanner = new Scanner(response)) {
//            String responseBody = scanner.useDelimiter("\\A").next();
//            System.out.println(responseBody);
//        }
//        System.out.println(passData().length);
//        String soal = "Siapakah tokoh dalam sidang Politbiro PKI pada 13-14 Agustus 1948 yang menawarkan resolusi dengan sebutan “Jalan Baru untuk Republik Indonesia”?";
//        for(int i=0;i<passData().length;i++){
//            if(passData()[i][0].equals(soal)){
//                System.out.printf("Pertanyaan : %s  \n",passData()[i][0]);
//                System.out.printf("Jawaban    : %s  \n\n",passData()[i][1]);
//            }
//
//        }
    }
}
