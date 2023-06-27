package com.masai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
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
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomId;
	@NotNull(message = "Name can't be Null")
	@NotEmpty(message = "Name can't be Empty")
	@NotBlank(message = "Name can't be Blank")
	private String name;
	@NotNull(message = "Price can't be Null")
	@NotEmpty(message = "Price can't be Empty")
	@NotBlank(message = "Price can't be Blank")
	@Min(1)
	private double price;
	@NotNull(message = "Status can't be Null")
	@NotEmpty(message = "Status can't be Empty")
	@NotBlank(message = "Status can't be Blank")
// 	Initially room is "available"
	private String status = "available";
	
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Property property;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Booking booking;
}
