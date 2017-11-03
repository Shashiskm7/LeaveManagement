package com.eg.pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Country2 {
	
	static HashMap<Integer,Country> countryIdMap = getCountryIdMap();
	
	public Country2(){
		super();
		
		if(countryIdMap==null)
		{
			countryIdMap = new HashMap<Integer,Country>();
			
			Country indiaCountry = new Country(1,"India",10000);
			Country chinaCountry = new Country(2,"China",100000);
			Country japanCountry = new Country(3,"Japan",210000);
			Country AusCountry = new Country(4,"Australia",10200);
			
			countryIdMap.put(1,indiaCountry);
			countryIdMap.put(2,chinaCountry);
			countryIdMap.put(3,japanCountry);
			countryIdMap.put(4,AusCountry);
		}
		
		
	}
	
	public List<Country> getAllCountries()
	{
		List<Country> countries = new ArrayList<Country>(countryIdMap.values());
		return countries;
	}
	
	public Country getCountry(int id)
	{
		Country country = countryIdMap.get(id);
		
		return country;
	}
	
	public Country addCountry(Country country)
	{
		country.setId(countryIdMap.size()+1);
		return countryIdMap.put(country.getId(),country);
	}
	
	public Country UpdateCountry(Country country)
	{
		if(country.getId()<=0)
			return null;
			countryIdMap.put(country.getId(),country);
			return country;
	}
	
	public void deleteCountry(int id)
	{
		countryIdMap.remove(id);
	}
	
	public static HashMap<Integer,Country> getCountryIdMap()
	{
		return countryIdMap;
	}
	
	
	
	
	

}
