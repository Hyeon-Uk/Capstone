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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class CrawlingServiceWithJsoup implements CrawlingService{

    //change input date format "yyyy-MM-dd"(input) to "MM/dd/yyyy"(Api Parameter Format)
    public String changeInputDateFormat(String str){
        StringTokenizer st=new StringTokenizer(str,"-");
        String year=st.nextToken();
        String month=st.nextToken();
        String date=st.nextToken();

        return month+"/"+date+"/"+year;
    }

    //change format "MM dd, yyyy" to "yyyy-MM-dd"
    public String changeDateFormat(String str){
        StringTokenizer st=new StringTokenizer(str," ");
        String month=st.nextToken();
        String date=st.nextToken().substring(0,2);
        String year=st.nextToken();

        switch(month){
            case "Jan":
                month="01";
                break;
            case "Feb":
                month="02";
                break;
            case "Mar":
                month="03";
                break;
            case "Apr":
                month="04";
                break;
            case "May":
                month="05";
                break;
            case "Jun":
                month="06";
                break;
            case "Jul":
                month="07";
                break;
            case "Aug":
                month="08";
                break;
            case "Sep":
                month="09";
                break;
            case "Oct":
                month="10";
                break;
            case "Nov":
                month="11";
                break;
            case "Dec":
                month="12";
                break;
            default:
                month=null;
                break;
        }
        return year+"-"+month+"-"+date;
    }

    public List<Bond> getStock(String st_date,String end_date,String symbol) throws IOException {
        final String URL="https://www.investing.com/instruments/HistoricalDataAjax";
        final int CURID=Symbol.valueOf(symbol).getCurrId();
        final String USER_AGENT="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36";

        log.info("before st_date = {}, after st_date = {}",st_date,changeInputDateFormat(st_date));
        //JSOUP을 이용해서 크롤링 시작
        Connection.Response response= Jsoup.connect(URL)
                .userAgent(USER_AGENT)
                .header("X-Requested-With","XMLHttpRequest")
                .data("curr_id",Integer.toString(CURID))
                .data("st_date",changeInputDateFormat(st_date))
                .data("end_date",changeInputDateFormat(end_date))
                .data("interval_sec","Daily")
                .data("action","historical_data")
                .method(Connection.Method.POST)
                .execute();

        Document doc=response.parse();
        Elements elements = doc.select("tr");


        //헤더와 푸터 제거
        elements.remove(elements.size()-1);
        elements.remove(0);

        //새로운 리스트에 원하는 정보 매핑
        List<Bond> ret=new ArrayList<>();
        elements.stream().forEach(e->{
            Elements tds=e.getElementsByTag("td");
            Bond bond=Bond.builder()
                    .date(changeDateFormat(tds.get(0).text()))
                    .close(Double.parseDouble(tds.get(1).text()))
                    .open(Double.parseDouble(tds.get(2).text()))
                    .high(Double.parseDouble(tds.get(3).text()))
                    .low(Double.parseDouble(tds.get(4).text()))
                    .change(Double.parseDouble(tds.get(5).text().substring(0,tds.get(5).text().length()-1)))
                    .build();
            ret.add(bond);
        });

        //리스트 정렬
        ret.sort(new Comparator<Bond>() {
            @Override
            public int compare(Bond o1, Bond o2) {
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                Date o1Date= null;
                Date o2Date=null;

                try {
                    o1Date = format.parse(o1.getDate());
                    o2Date=format.parse(o2.getDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return o1Date.compareTo(o2Date);
            }
        });

        return ret;
    }
}
