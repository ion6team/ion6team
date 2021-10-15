package kosa.ion.team6.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardDto {
	private String title;
	private String category_id;
	private long member_id;
	private String contents;
	private String hope_address;
	private String price;
	
}
