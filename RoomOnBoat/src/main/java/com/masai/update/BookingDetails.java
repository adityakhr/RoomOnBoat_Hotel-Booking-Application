package com.masai.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetails {
	@Min(1)
	@NotNull(message = "DurationInDays can't be Null")
	private int durationInDays;
	@Min(1)
	@NotNull(message = "numberOfGuest can't be Null")
	private int numberOfGuest;
}
