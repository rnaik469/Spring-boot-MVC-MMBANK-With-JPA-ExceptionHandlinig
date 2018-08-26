package in.co.cg.spring.boot.mmbank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import in.co.cg.spring.boot.mmbank.pojo.BankAccount;
import in.co.cg.spring.boot.mmbank.service.BankAccountService;

@SpringBootApplication
public class MmbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MmbankApplication.class, args);
	}

	
	//Populating data in the H2 database
	@Bean
	public CommandLineRunner addCusstomer(BankAccountService service) {
		return arg -> {
			service.AddNewAccount(new BankAccount("Naik", 1000, "Savings"));
			service.AddNewAccount(new BankAccount("Emmi", "Savings"));
			System.out.println("added");
		};

	}
}
