package kosa.ion.team6.DTO;

import lombok.*;

//Token정보 응답시 사용
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String token;
}
