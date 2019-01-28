package fr.univtours.polytech.dii.assproject;

import fr.univtours.polytech.dii.assproject.enumeration.Rank;
import fr.univtours.polytech.dii.assproject.models.StoreUser;
import fr.univtours.polytech.dii.assproject.repositories.StoreUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AssProjectApplication implements CommandLineRunner {

	@Autowired
	private StoreUserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(AssProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		repository.deleteAll();

		repository.save(new StoreUser("admin", "admin@admincorp.fr",
				bCryptPasswordEncoder.encode("admin"), null, null, "12 Rue de la rivièere - Tours", Rank.ADMIN));
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
