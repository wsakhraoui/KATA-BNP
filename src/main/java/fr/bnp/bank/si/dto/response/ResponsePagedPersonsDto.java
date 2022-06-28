package fr.bnp.bank.si.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.bnp.bank.si.dto.PersonDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponsePagedPersonsDto {
	
	
	 @JsonProperty("Persons")
	 List<List<PersonDto>>  PersonDtoPageByPage;

}
