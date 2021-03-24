package com.financial.repository;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.financial.entity.City;
import com.financial.entity.State;

@SpringBootTest
public class StateTest {
	
	@Autowired private StateRepository stateRepository;
	@Autowired private CityRepository cityRepository;
	
	@Test
	public void saveStateCityTest() {
		State st1 = new State(null, "Pernambuco");
		State st2 = new State(null, "Paraíba");
		
		City ci1 = new City(null, "Recife", st1);
		City ci2 = new City(null, "João Pessoa", st2);
		City ci3 = new City(null, "Ipojuca", st1);
		
		st1.getCities().addAll(Arrays.asList(ci1, ci3));
		st2.getCities().addAll(Arrays.asList(ci2));
		
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(ci1, ci2, ci3));
	}

}
