package com.masai.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Host {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hostId;
	@NotNull(message = "Name can't be Null")
	@NotEmpty(message = "Name can't be Empty")
	@NotBlank(message = "Name can't be Blank")
	private String name;
	@NotNull(message = "Email can't be Null")
	@NotEmpty(message = "Email can't be Empty")
	@NotBlank(message = "Email can't be Blank")
	@Email(message = "Email should be in Proper Format")
	private String email;
	@JsonIgnore
	@OneToMany(mappedBy = "host",cascade =CascadeType.ALL)
	private List<Property> property;
}
