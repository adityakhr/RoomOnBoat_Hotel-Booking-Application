package com.masai.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmail {

	@NotNull(message = "Email can't be Null")
	@NotEmpty(message = "Email can't be Empty")
	@NotBlank(message = "Email can't be Blank")
	@Email(message = "Email should be in proper formate")
	private String email;
}
