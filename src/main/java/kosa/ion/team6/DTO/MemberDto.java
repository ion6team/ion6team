package kosa.ion.team6.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
	private String name;
	private String email;
	private String password;
	private String address;
	private String address_detail;
}
