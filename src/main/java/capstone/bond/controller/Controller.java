package capstone.bond.controller;

import capstone.bond.dto.ApiResult;
import capstone.bond.dto.Bond;
import capstone.bond.service.CrawlingService;
import capstone.bond.utils.ApiUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags={"1. Bond Api"})
public class Controller {

    private final String SYMBOLS="KR1YT=1년만기 한국국채 수익률\n" +
            "KR2YT=2년만기 한국국채 수익률\n" +
            "KR3YT=3년만기 한국국채 수익률\n" +
            "KR4YT=4년만기 한국국채 수익률\n" +
            "KR5YT=5년만기 한국국채 수익률\n" +
            "KR10YT=10년만기 한국국채 수익률\n" +
            "KR20YT=20년만기 한국국채 수익률\n" +
            "KR30YT=30년만기 한국국채 수익률\n" +
            "KR50YT=50년만기 한국국채 수익률\n" +
            "\n" +
            "US1MT=1개월만기 미국국채 수익률\n" +
            "US3MT=3개월만기 미국국채 수익률\n" +
            "US6MT=6개월만기 미국국채 수익률\n" +
            "US1YT=1년만기 미국국채 수익률\n" +
            "US2YT=2년만기 미국국채 수익률\n" +
            "US3YT=3년만기 미국국채 수익률\n" +
            "US7YT=7년만기 미국국채 수익률\n" +
            "US10YT=10년만기 미국국채 수익률";

    @Autowired
    private CrawlingService crawlingService;

    @GetMapping("/bond")
    @ApiOperation(value="Get Bond Price",notes="채권가격을 얻어옵니다.")
    @ApiResponses({
            @ApiResponse(code=200,message="success"),
            @ApiResponse(code=403,message="파라미터 형식 확인해주세요")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "st_date", value = "시작날짜(MM/dd/yyyy 형식)", required = true, dataType = "String")
            , @ApiImplicitParam(name = "end_date", value = "종료날짜(MM/dd/yyyy 형식)", required = true, dataType = "String")
            , @ApiImplicitParam(name="symbol",value=SYMBOLS,required = true,dataType = "String")
    })
    public ApiResult<List<Bond>> bond(@RequestParam("st_date") String stDate,
                                      @RequestParam("end_date") String endDate,
                                      @RequestParam("symbol") String symbol) throws IOException {
        return ApiUtils.success(crawlingService.getStock(stDate,endDate,symbol));
    }

}
