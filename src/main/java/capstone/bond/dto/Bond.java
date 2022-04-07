package capstone.bond.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bond {

    @ApiModelProperty(value="날짜")
    String date;
    @ApiModelProperty(value="종가")
    double close;
    @ApiModelProperty(value="시가")
    double open;
    @ApiModelProperty(value="고가")
    double high;
    @ApiModelProperty(value="저가")
    double low;
    @ApiModelProperty(value="변동률")
    double change;

    @Override
    public String toString(){
        return date+","+close+","+open+","+high+","+low+","+change;
    }
}
