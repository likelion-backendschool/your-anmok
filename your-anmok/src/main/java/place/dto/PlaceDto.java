package place.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDto {
    private int id;
    private String address;
    private String name;
    private int rateCnt;
    private int star;
    private double lat;
    private double lon;
}
