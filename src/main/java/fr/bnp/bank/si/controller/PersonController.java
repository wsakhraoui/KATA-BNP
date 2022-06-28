package fr.bnp.bank.si.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.bnp.bank.si.dto.request.RequestCreationPersonDto;
import fr.bnp.bank.si.dto.response.ResponsePagedPersonsDto;
import fr.bnp.bank.si.service.PersonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PersonController {
	
	 public static final String PERSON_CREATION_END_POINT_V1 = "/v1/persons";
	 
	 @Autowired
	 private final PersonService personService;
	 
    @PostMapping(
            path     = PERSON_CREATION_END_POINT_V1,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Create list of perons",
            notes = "Create a new list persons .\n" +
                    "Add persons list to bucket ")
   
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Persons created."),
          
            @ApiResponse(code = 400, message = "Invalid request  incorrect persons data values")
    })
    public ResponseEntity<ResponsePagedPersonsDto> postPersons(@Valid @RequestBody RequestCreationPersonDto requestCreationPersonDto) {
    	
    	return new ResponseEntity<ResponsePagedPersonsDto>(personService.createPersons(requestCreationPersonDto), HttpStatus.CREATED);
        
    }
	

}
