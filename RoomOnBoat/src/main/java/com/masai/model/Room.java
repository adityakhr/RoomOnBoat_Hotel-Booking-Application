package com.masai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@Min(1)
	private double price;
	@NotNull(message = "Status can't be Null")
	@NotEmpty(message = "Status can't be Empty")
	@NotBlank(message = "Status can't be Blank")
	private String status;
	@JsonIgnore
	@ManyToOne
	private Property property;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = true)
	private Booking booking;
}
