package capstone.bond.service;

import capstone.bond.dto.Bond;
import capstone.bond.dto.Symbol;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CrawlingService {

    public List<Bond> getStock(String st_date,String end_date,String symbol) throws IOException {
        final String URL="https://www.investing.com/instruments/HistoricalDataAjax";
        final int CURID=Symbol.valueOf(symbol).getCurrId();
        final String USER_AGENT="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36";

        Connection.Response response= Jsoup.connect(URL)
                .userAgent(USER_AGENT)
                .header("X-Requested-With","XMLHttpRequest")
                .data("curr_id",Integer.toString(CURID))
                .data("st_date",st_date)
                .data("end_date",end_date)
                .data("interval_sec","Daily")
                .data("action","historical_data")
                .method(Connection.Method.POST)
                .execute();

        Document doc=response.parse();
        Elements elements = doc.select("tr");


        //헤더와 푸터 제거
        elements.remove(elements.size()-1);
        elements.remove(0);

        List<Bond> ret=new ArrayList<>();
        elements.stream().forEach(e->{
            Elements tds=e.getElementsByTag("td");
            Bond bond=Bond.builder()
                    .date(tds.get(0).text())
                    .close(Double.parseDouble(tds.get(1).text()))
                    .open(Double.parseDouble(tds.get(2).text()))
                    .high(Double.parseDouble(tds.get(3).text()))
                    .low(Double.parseDouble(tds.get(4).text()))
                    .change(Double.parseDouble(tds.get(5).text().substring(0,tds.get(5).text().length()-1)))
                    .build();
            ret.add(bond);
        });


        return ret;
    }
}
