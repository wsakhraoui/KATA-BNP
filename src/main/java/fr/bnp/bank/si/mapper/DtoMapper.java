package fr.bnp.bank.si.mapper;

import java.util.List;

import fr.bnp.bank.si.dto.PersonDto;
import fr.bnp.bank.si.dto.response.ResponsePagedPersonsDto;
import fr.bnp.bank.si.entity.Person;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DtoMapper {
	
	
	
	
	public PersonDto  toPersonDto(Person p) {
		return PersonDto.builder().firstName(p.getFirstName()).lastName(p.getLastName()).age(p.getAge()).build() ;
	}

	
	 
	 
	 public static  Person toPersonEntity(PersonDto personDto) {
		 return Person.builder().firstName(personDto.getFirstName()).
				 lastName(personDto.getLastName()).age(personDto.getAge()).build();
 	 }
	 
	 public static  ResponsePagedPersonsDto toResponsePagedPersonsDto( List<List<PersonDto>>  pageByPage) {
		 return  ResponsePagedPersonsDto.builder().PersonDtoPageByPage(pageByPage).build();
 	 }
	 
	 
	
}
