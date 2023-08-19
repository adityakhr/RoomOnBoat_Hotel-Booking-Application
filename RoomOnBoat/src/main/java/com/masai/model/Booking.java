package com.masai.model;


import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDate date = LocalDate.now();
	@Min(1)
	@NotNull(message = "DurationInDays can't be Null")
	@NotEmpty(message = "DurationInDays can't be Empty")
	@NotBlank(message = "DurationInDays can't be Blank")
	private int durationInDays;
	@Min(1)
	@NotNull(message = "numberOfGuest can't be Null")
	@NotEmpty(message = "numberOfGuest can't be Empty")
	@NotBlank(message = "numberOfGuest can't be Blank")
	private int numberOfGuest;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(nullable = true)
	private Users user;
	@OneToMany(mappedBy = "booking")
	private List<Room>rooms;
	@JsonIgnore
	private boolean active=true;
	public boolean getActive() {
		return this.active;
	}
	public void setActive(boolean active) {
		this.active=active;
	}
}
