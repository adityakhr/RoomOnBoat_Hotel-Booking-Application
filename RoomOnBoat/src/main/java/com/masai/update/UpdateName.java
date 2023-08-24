package com.masai.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateName {
	@NotNull(message = "Name can't be Null")
	@NotEmpty(message = "Name can't be Empty")
	@NotBlank(message = "Name can't be Blank")
	private String name;
	@NotNull(message = "LastName can't be Null")
	@NotEmpty(message = "LastName can't be Empty")
	@NotBlank(message = "LastName can't be Blank")
	private String lastName;
}
