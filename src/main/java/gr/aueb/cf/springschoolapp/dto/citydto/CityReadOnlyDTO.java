package gr.aueb.cf.springschoolapp.dto.citydto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityReadOnlyDTO {
    private Long id;
    private String cityName;
}
