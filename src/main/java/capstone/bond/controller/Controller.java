package capstone.bond.controller;

import capstone.bond.service.CrawlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Controller {

    @Autowired
    private CrawlingService crawlingService;

    @GetMapping("/bond")
    public String bond() throws IOException {
        crawlingService.getStock();
        return "bond is predicted";
    }

}
