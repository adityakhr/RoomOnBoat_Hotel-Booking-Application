package com.masai.update;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePassword {
	@NotNull(message = "Password can't be Null")
	@NotEmpty(message = "Password can't be Empty")
	@NotBlank(message = "Password can't be Blank")
	@Size(min = 8,message = "size must be greater than or equal to 8")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$",message = "Should match the pattern like 'Aa...@1' kind of")
	private String password; 
}
