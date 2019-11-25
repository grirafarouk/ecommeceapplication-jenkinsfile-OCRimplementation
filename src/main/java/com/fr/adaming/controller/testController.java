package com.fr.adaming.controller;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fr.adaming.Model.Tester;
import com.fr.adaming.dao.testRepositor;


@CrossOrigin("*")
@RestController
public class testController {
	@Autowired
	public testRepositor tester;
	@PostMapping(value = "users-img")
	public void save(@RequestParam("file") MultipartFile file,@RequestParam("nom") String nom, @RequestParam("prenom") String prenom) throws Exception {
			Tester test =new Tester();
			test.setNom(nom);
			test.setPrenom(prenom);
			test.setFilebyte(file.getBytes());
	     	tester.save(test);
	
	}
@GetMapping(path="getalluser")
public List<Tester> getalltesters (){
	return tester.findAll();	
}
	
}
