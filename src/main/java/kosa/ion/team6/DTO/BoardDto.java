package kosa.ion.team6.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private String title;
    private long category_id;
    private String contents;
    private String hopeaddress;
    private String price;
}
