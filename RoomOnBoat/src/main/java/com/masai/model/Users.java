package com.masai.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@NotNull(message = "Name can't be Null")
	@NotEmpty(message = "Name can't be Empty")
	@NotBlank(message = "Name can't be Blank")
	private String name;
	@NotNull(message = "LastName can't be Null")
	@NotEmpty(message = "LastName can't be Empty")
	@NotBlank(message = "LastName can't be Blank")
	private String lastName;
	@NotNull(message = "Email can't be Null")
	@NotEmpty(message = "Email can't be Empty")
	@NotBlank(message = "Email can't be Blank")
	@Email(message = "Email should be in Proper Format")
	@Column(unique = true)
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@JsonIgnore
	private final String role="ROLE_USER";
	@OneToMany(mappedBy = "user")
	private List<Booking> bookings;
}
