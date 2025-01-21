package Programacion2.HoldingEmpresas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class HoldingEmpresasApplication {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Dotenv dotenv = Dotenv.configure().load();
		SpringApplication.run(HoldingEmpresasApplication.class, args);
	}

}