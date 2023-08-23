package com.masai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	@NotNull(message = "Name can't be Null")
	@NotEmpty(message = "Name can't be Empty")
	@NotBlank(message = "Name can't be Blank")
	private String name;
	@NotNull(message = "LastName can't be Null")
	@NotEmpty(message = "LastName can't be Empty")
	@NotBlank(message = "LastName can't be Blank")
	private String lastName;
	@Column(unique = true)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String email;
	@NotNull(message = "Password can't be Null")
	@NotEmpty(message = "Password can't be Empty")
	@NotBlank(message = "Password can't be Blank")
	@Size(min = 8)
//	@Pattern(regexp = "^[A-Z][a-z]*[!@#$&*~`%^:?/|]{1,}[0-9]*",message = "Should match the pattern like 'Aa...@1' kind of")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@JsonIgnore
	private final String role="ROLE_ADMIN";
	public void setEmail(String str){
		this.email=str;
	}
}
