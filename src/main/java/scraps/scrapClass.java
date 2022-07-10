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
