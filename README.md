# Capstone (어쩔AI조)
# https://kr.investing.com/ 사이트를 크롤링해서 채권가격을 얻어옵니다. (2022-09-29기준 사이트의 api가 변경되어 사용불가)

# [API디자인]
``````
URL

GET /api/bond 
Host: https://bond-price-prediction.herokuapp.com/
``````

# [Request Parameter]
| Name | Type | Description | Required |
| --- | --- | --- | --- |
| st_date | String | 시작날짜 | O |
| end_date | String | 종료날짜 | O |
| symbol | String | 얻고싶은 채권의 심볼 | O |

# [Response]

## [Design]
```
{
  "apiError": {
    "message": "string",
    "status": 0
  },
  "response": [
    {
      "change": 0,
      "close": 0,
      "date": "string",
      "high": 0,
      "low": 0,
      "open": 0
    }
  ],
  "success": true
}
```

| Name | Type | Description |
| --- | --- | --- |
| apiError | Object{</br>message : String,</br>status : integer</br>} | 에러가 있을시 메세지와 상태값리턴</br>message : 에러메세지</br>status : 상태값 |
| response | Object{</br>change : number(double),</br>close : number(double),</br>open : number(double),</br>low : number(double),</br>high : number(double),</br>date : String </br>}|결과값</br>change : 변동률</br>close : 종가</br>open : 시가</br>low : 저가</br>high :  고가</br>date : 날짜 |
| success | Boolean | 성공여부 |

# [Symbols]

| Symbols | Description |
| --- | --- |
| KR1YT | 1년만기 한국국채 수익률 |
| KR2YT | 2년만기 한국국채 수익률 |
| KR3YT | 3년만기 한국국채 수익률 |
| KR4YT | 4년만기 한국국채 수익률 |
| KR5YT | 5년만기 한국국채 수익률 |
| KR10YT | 10년만기 한국국채 수익률 |
| KR20YT | 20년만기 한국국채 수익률 |
| KR30YT | 30년만기 한국국채 수익률 |
| KR50YT | 50년만기 한국국채 수익률 |
| US1MT | 1개월만기 미국국채 수익률 |
| US3MT | 3개월만기 미국국채 수익률 |
| US6MT | 1개월만기 미국국채 수익률 |
| US1YT | 1년만기 미국국채 수익률 |
| US2YT | 2년만기 미국국채 수익률 |
| US3YT | 3년만기 미국국채 수익률 |
| US7YT | 7년만기 미국국채 수익률 |
| US10YT | 10년만기 미국국채 수익률 |

# [Api Document](https://bond-price-prediction.herokuapp.com/swagger-ui/index.html)
