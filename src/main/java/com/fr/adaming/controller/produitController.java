package com.fr.adaming.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fr.adaming.Model.Product;
import com.fr.adaming.Model.cvdefinition;
import com.fr.adaming.dao.productRepository;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@RestController
@CrossOrigin("*")
public class produitController {
	@Autowired
	public productRepository productrepository;

	@GetMapping(path = "/photoProduct/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] getphoto(@PathVariable("id") Long id) throws Exception {
		Product p = productrepository.findById(id).get();
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/ecomm/products/" + id+".jpg"));
	}

	@PostMapping(path = "/upload/{id}")
	public void upload(MultipartFile file, @PathVariable Long id) throws Exception {
		Product p = productrepository.findById(id).get();
		p.setPhotoName(id + ".jpg");
		Files.write(Paths.get(System.getProperty("user.home") + "/ecomm/products/" + p.getPhotoName()),
				file.getBytes());
		productrepository.save(p);
  
	}

	@GetMapping(path = "/filetest")
	public List<cvdefinition> recherchedansunefichierPdf() throws InterruptedException {
		List<cvdefinition> listesDesCv = new ArrayList<>();
		String reg = null;
		String tech = null;
		String comp = null;

		ArrayList<String> technologie = new ArrayList<String>();
		technologie.add("MAINFRAME");
		technologie.add("NTIC");
		technologie.add("MAINFRAME ++");
		technologie.add("SERVICE NOW");
		technologie.add("PROGRAMMING");
		technologie.add("commercial");
		technologie.add("Bac+6");
		technologie.add("SOPHIA DEVOPS");
		technologie.add("INGENIEUR PRODUCTION");

		technologie.add("java");
		technologie.add("INFOTEL");
		technologie.add("SALESFORCE");
		technologie.add("SAP");

		ArrayList<String> competence = new ArrayList<String>();
		competence.add("JAVA");
		competence.add("C++");
		competence.add("PHP");
		competence.add("VBA");

		competence.add(".NET");
		competence.add("BAC+");
		competence.add("HTML");
		competence.add("SHELL");

		competence.add("FORTAIN");
		competence.add("Amelioration");
		competence.add("MATLAB");
		competence.add("PYTHON");

		ArrayList<String> region = new ArrayList<String>();
		region.add("PICARDIE");
		region.add("AUVERGNE");
		region.add("Monaco");
		region.add("fdf");
		region.add("LORRAINE");
		region.add("fdfd");

		try {
			String sourceDir = "C:/Users\\fgrira/Desktop/cv/sarah dion.pdf";
			String destinationDir = "C:/OCR/tesseract/";
			File sourceFile = new File(sourceDir);
			File destinationFile = new File(destinationDir);
			if (!destinationFile.exists()) {
				destinationFile.mkdir();
				System.out.println("create the document -> " + destinationFile.getAbsolutePath());
			}
			if (sourceFile.exists()) {
				PDDocument document = PDDocument.load(sourceDir);
				@SuppressWarnings("unchecked")
				List<PDPage> list = document.getDocumentCatalog().getAllPages();

				String fileName = sourceFile.getName().replace(".pdf", "");
				int pageNumber = 1;
				for (PDPage page : list) {
					BufferedImage image = page.convertToImage();
					File outputfile = new File(destinationDir + fileName + "_" + pageNumber + ".PNG");
					ImageIO.write(image, "PNG", outputfile);
					pageNumber++;
				}
				document.close();
				System.out.println("register file in the right destination -> " + destinationFile.getAbsolutePath());
			} else {
				System.err.println(sourceFile.getName() + " file does not existe");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Tesseract tesseract = new Tesseract();
		try {
			tesseract.setDatapath("C:/Tess4J/tessdata");
			System.out.println("Still waiting for searching using tessearct ..................................");
			String text = tesseract.doOCR(new File("C:\\OCR\\tesseract\\cv1_1.jpg"));
			System.out.print(text);
			for (String obj : technologie) {
				if (text.contains(obj)) {
					tech = obj;
				}

			}
			for (String obj : competence) {
				if (text.contains(obj)) {
					comp = obj;
				}

			}

			for (String obj : region) {
				if (text.contains(obj)) {
					reg = obj;
				}

			}

			String[] splited = text.split("\\s+");
			for (int i = 0; i < splited.length; i++) {
				System.out.println(splited[i]);
			}

			ArrayList<String> hs = new ArrayList<String>();

			System.out.println("----------------------------------------------------------------------------");

			String validateemail = null;
			for (int i = 0; i < splited.length; i++) {
				if (isValid(splited[i])) {
					validateemail = splited[i];
					System.out.println(splited[i]);

				}
			}
			cvdefinition value = new cvdefinition(reg, tech, comp, validateemail, null);
			System.out.println("\n");
			System.out.println("\n");
			System.out.println("\n");

			listesDesCv.add(value);
			System.out.println("verified : searching done   .......................................................");
			System.out.println("\n");
			System.out.println(value);

		} catch (TesseractException e) {
			e.printStackTrace();
		}
		return listesDesCv;
	}

	static boolean isValidName(int email, String name) {
		return false;
	}

	static boolean isValid(String email) {
		String regex = "^[\\w-_\\.+:]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);

	}

	static boolean isValidPrename(String prename) {

		return true;

	}
}
