package capstone.bond.service;

import capstone.bond.dto.Bond;

import java.io.IOException;
import java.util.List;

public interface CrawlingService {

    List<Bond> getStock(String st_date,String end_date,String symbol) throws IOException;
}
