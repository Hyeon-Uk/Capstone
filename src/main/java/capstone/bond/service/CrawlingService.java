package capstone.bond.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class CrawlingService {

    public void getStock() throws IOException {
        final String URL="https://www.investing.com/instruments/HistoricalDataAjax";
        final String CURID="23701";

        Connection.Response response= Jsoup.connect(URL)
                .header("User-Agent","Mozilla")
                .header("X-Requested-With","XMLHttpRequest")
                .data("curr_id",CURID)
                .data("st_date","04/05/2022")
                .data("end_date","04/06/2022")
                .data("interval_sec","Daily")
                .data("action","historical_data")
                .method(Connection.Method.POST)
                .execute();
        System.out.println(response.body().toString());

    }
}
