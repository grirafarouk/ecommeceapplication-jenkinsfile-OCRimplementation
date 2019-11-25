package com.fr.adaming;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.fr.adaming.Model.Category;
import com.fr.adaming.Model.Product;
import com.fr.adaming.dao.categoryRepository;
import com.fr.adaming.dao.productRepository;

import net.bytebuddy.utility.RandomString;

@SpringBootApplication
public class EcommerceapplicationApplication implements CommandLineRunner {
	@Autowired
	private productRepository repoproduit;
	@Autowired
	private categoryRepository repocategorie;
	@Autowired
	private RepositoryRestConfiguration configuration;
	public static void main(String[] args) {
		SpringApplication.run(EcommerceapplicationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		configuration.exposeIdsFor(Product.class,Category.class);
		repocategorie.save(new Category(null, "ordinateur", null, null, null));
		repocategorie.save(new Category(null, "clavier", null, null, null));
		repocategorie.save(new Category(null, "sourie", null, null, null));
		Random random = new Random();
		repocategorie.findAll().forEach(c -> {
			for (int i = 0; i < 10; i++) {
				Product p = new Product();
				p.setSelected(random.nextBoolean());
				p.setName(RandomString.make(118));
				p.setCurrentprice(100 + random.nextInt(1000));
				p.setAvailable(random.nextBoolean());
				p.setPromotion(random.nextBoolean());
				p.setPhotoName("unknown.png");
				p.setCategory(c);
				repoproduit.save(p);

			}
		});

	}

}
