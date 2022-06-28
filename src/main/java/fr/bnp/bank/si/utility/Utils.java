package fr.bnp.bank.si.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import fr.bnp.bank.si.dto.PersonDto;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@UtilityClass
@Log4j2
public class Utils {
    public static void throwsOnCondition(boolean conditionToThrowsException,
                                   Supplier<? extends RuntimeException> exceptionSupplier,
                                   String ... logMessages) {
        if (conditionToThrowsException) {
            Arrays.stream(logMessages).forEach(log::error);
            throw exceptionSupplier.get();
        }
    }
    
    
    public static List<List<PersonDto>> pageByPagePersonDto(List<PersonDto> personDtoList,Integer subListSize){
    	
    	List<List<PersonDto>> result=new ArrayList<>();
    	Integer start=0;
    	while (start<personDtoList.size()) {
    		
    		if(start+subListSize<=personDtoList.size()) {
    			result.add(personDtoList.subList(start, start+subListSize));
    		}else {
    			result.add(personDtoList.subList(start, personDtoList.size()));	
    		}
    		start+=subListSize;
    		
    	}
    	
    	return result;
    	
     	
    }
}
