package fr.bnp.bank.si.controller;

  

 
import static fr.bnp.bank.si.controller.PersonController.PERSON_CREATION_END_POINT_V1;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import fr.bnp.bank.si.dto.PersonDto;
import fr.bnp.bank.si.dto.request.RequestCreationPersonDto;
import fr.bnp.bank.si.repository.PersonRepository;
import fr.bnp.bank.si.utility.Utils;
import lombok.extern.slf4j.Slf4j;
 


@ActiveProfiles("test")
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@Slf4j
public class PersonControllerTest {
	
	  @Autowired
	    private WebApplicationContext context;

	 
	
	@Test
	void contextLoads() {
	}

	@Autowired
	PersonController personController;

	@Autowired
	PersonRepository personRepository;
	  
	
	private RestTemplate  restTemplate;
	private String url;
	 private List<PersonDto> persons;
	@LocalServerPort
	private int randomServerPort = 0;
	 
	
	 private List<PersonDto> personDtoList;
	
	
	
	
	 


	@BeforeEach
	public void beforeTest() {
		url = "http://localhost:" + randomServerPort;
		restTemplate=new RestTemplate();
		 persons=new ArrayList<>();
		 persons.add(PersonDto.builder().firstName("Nicolas").lastName("Testla").age(78).build());
		 persons.add(PersonDto.builder().firstName("Nicolas").lastName("unknown").age(50).build());
		 persons.add(PersonDto.builder().firstName("Wahid").lastName("Sakhraoui").age(101).build());
		 persons.add(PersonDto.builder().firstName("Scott").lastName("unknown").age(50).build());
		 persons.add(PersonDto.builder().firstName("Paul").lastName("wellknown").age(101).build());
		 persons.add(PersonDto.builder().firstName("Alin").lastName("XXXX").age(41).build());
		 persons.add(PersonDto.builder().firstName("Karim").lastName("BBBB").age(50).build());
		 persons.add(PersonDto.builder().firstName("Mathieu").lastName("AAAA").age(60).build());
		 
		 
		 
	}
	 
	 
	 @Test
    void createNewPersonsListTest() {
        
		RequestCreationPersonDto requestPersonDTO =
        		RequestCreationPersonDto.builder().PersonDtoList(persons).subListSize(3).build();
        HttpEntity<RequestCreationPersonDto> request = new HttpEntity<>(requestPersonDTO);

        ResponseEntity<Void> response = restTemplate.postForEntity(url +  PERSON_CREATION_END_POINT_V1, request, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        
        
 }
	 
	 
	

	 
	 @Test
	public void should_return_onPage_when_size_1() {
		 
		 //GIVEN
		 personDtoList =persons.stream().map(p-> PersonDto.builder().
				 firstName(p.getFirstName()).lastName(p.getLastName()).age(p.getAge()).build()).
				  collect(Collectors.toList());
		 //WHEN
		 List<List<PersonDto>> result= Utils.pageByPagePersonDto(   personDtoList,  1) ;
		 //Then
		 assertThat(result.get(result.size()-1).size()).isEqualTo(1);
		 assertThat(result.size()).isEqualTo(personDtoList.size());
		  

	} 
	 
	 
	 
	 @Test
	public void should_return_list_of_equals_page_size_when_page_size_is_multiple_of_the_intial_List() {
		 
		 //GIVEN
		 personDtoList =persons.stream().map(p-> PersonDto.builder().
				 firstName(p.getFirstName()).lastName(p.getLastName()).age(p.getAge()).build()).
				  collect(Collectors.toList());
		 //WHEN
		 List<List<PersonDto>> result= Utils.pageByPagePersonDto(   personDtoList,  2) ;
		 //Then
		 assertThat(result.get(result.size()-1).size()).isEqualTo(2);
		  
	} 
	 
	 
	 @Test
	public void should_return_last_page_size_less_than_page_size_when_page_size_is_not_multiple_of_the_intial_List() {
		 
		 //GIVEN
		 personDtoList =persons.stream().map(p-> PersonDto.builder().
				 firstName(p.getFirstName()).lastName(p.getLastName()).age(p.getAge()).build()).
				  collect(Collectors.toList());
		 //WHEN
		 List<List<PersonDto>> result= Utils.pageByPagePersonDto(   personDtoList,  5) ;
		 //Then
		 assertThat(result.get(result.size()-1).size()).isEqualTo(personDtoList.size()%5);
		  
	} 
	 
	 
	 
	 
	
}
