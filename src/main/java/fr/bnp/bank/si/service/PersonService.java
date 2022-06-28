package fr.bnp.bank.si.service;

import static fr.bnp.bank.si.configuration.Constants.MESSAGE_NULL_REQUEST_PERSON_DTO_ERROR;
import static fr.bnp.bank.si.utility.Utils.pageByPagePersonDto;
import static fr.bnp.bank.si.utility.Utils.throwsOnCondition;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.bnp.bank.si.driver.FileDriver;
import fr.bnp.bank.si.dto.PersonDto;
import fr.bnp.bank.si.dto.request.RequestCreationPersonDto;
import fr.bnp.bank.si.dto.response.ResponsePagedPersonsDto;
import fr.bnp.bank.si.entity.Person;
import fr.bnp.bank.si.exception.InvalidRequestPersonException;
import fr.bnp.bank.si.mapper.DtoMapper;
import fr.bnp.bank.si.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	FileDriver fileDriver;



	public ResponsePagedPersonsDto createPersons(RequestCreationPersonDto requestCreationPersonDto) {
		log.debug("Creating a new account - {}", requestCreationPersonDto);
		throwsOnCondition(Objects.isNull(requestCreationPersonDto), InvalidRequestPersonException::new,
				MESSAGE_NULL_REQUEST_PERSON_DTO_ERROR);

		List<List<PersonDto>> PersonsPageByPage = pageByPagePersonDto(requestCreationPersonDto.getPersonDtoList(),
				requestCreationPersonDto.getSubListSize());

		List<Person> filtedPersonByAge = filterPersonByAge(PersonsPageByPage);

		personRepository.saveAll(filtedPersonByAge);

		fileDriver.writeToFile(sortByageAndFirstNameAndLastName(filtedPersonByAge));

		return ResponsePagedPersonsDto.builder().PersonDtoPageByPage(PersonsPageByPage).build();
	}

	private List<Person> filterPersonByAge(List<List<PersonDto>> pageByPage) {
		return pageByPage.stream().flatMap(e -> e.stream()).filter(e -> e.getAge() > 40)
				.map(e -> DtoMapper.toPersonEntity(e)).collect(Collectors.toList());

	}

	private List<Person> sortByageAndFirstNameAndLastName(List<Person> persons) {
		return persons.stream()
				.sorted(Comparator.comparing(Person::getAge).thenComparing(Comparator.comparing(Person::getFirstName))
						.thenComparing(Comparator.comparing(Person::getLastName)))
				.collect(Collectors.toList());
	}

}
