package fr.bnp.bank.si.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonDto {
	 @NotNull
	private String firstName;
	 @NotNull
	private String lastName;
	 @NotNull
	private Integer age;
	

}
