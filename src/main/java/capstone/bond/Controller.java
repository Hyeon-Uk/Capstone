package capstone.bond;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/bond")
    public String bond(){
        return "bond is predicted";
    }
}
