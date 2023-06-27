package com.masai.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@NotNull(message = "Name can't be Null")
	@NotEmpty(message = "Name can't be Empty")
	@NotBlank(message = "Name can't be Blank")
	private String name;
	@NotNull(message = "Email can't be Null")
	@NotEmpty(message = "Email can't be Empty")
	@NotBlank(message = "Email can't be Blank")
	@Email(message = "Email should be in Proper Format")
	private String email;
	@NotNull(message = "Password can't be Null")
	@NotEmpty(message = "Password can't be Empty")
	@NotBlank(message = "Password can't be Blank")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@NotNull(message = "Role can't be Null")
	@NotEmpty(message = "Role can't be Empty")
	@NotBlank(message = "Role can't be Blank")
	private String role;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "user",cascade =CascadeType.ALL)
	private List<Booking> bookings;
}
