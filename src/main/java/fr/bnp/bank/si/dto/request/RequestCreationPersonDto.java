package fr.bnp.bank.si.dto.request;

 

import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

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
public class RequestCreationPersonDto {

    @JsonProperty("Persons")
    @NotNull
    List<PersonDto>   PersonDtoList;

    @JsonProperty("SubListSize")
    @NotNull
    @DecimalMin(value = "0", inclusive = false,  message = "INVALID_SUB_LIST_SIZE")
   
    private Integer subListSize;

}
