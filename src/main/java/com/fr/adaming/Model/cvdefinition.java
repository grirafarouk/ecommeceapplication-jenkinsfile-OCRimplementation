package com.fr.adaming.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class cvdefinition {	
		
String region;
String technologie;
String competence;
String email;
String origine;
@Override
public String toString() {
	return "cvdefinition [region=" + region + ", technologie=" + technologie + ", competence=" + competence + ", email="
			+ email + ", origine=" + origine + "]";
}


}
