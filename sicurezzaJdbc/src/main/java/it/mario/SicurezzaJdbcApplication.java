package it.mario;


import it.mario.model.Impiegati;
import it.mario.repository.ImpiegatiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SicurezzaJdbcApplication implements CommandLineRunner  {
 @Autowired
 ImpiegatiRepository impiegatiRepository;

	public static void main(String[] args) {
		SpringApplication.run(SicurezzaJdbcApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		// gistiamo i dati sul secondo database [ in alternativa all'endpoint]
		System.out.println("Inserisco un impiegato sul DB");
		Impiegati impiegato = new Impiegati(1,"Rossi", 45,1000);

		int x = impiegatiRepository.salvaImpSuDb(impiegato);
		if (x==1)
			System.out.println("Salvato impiegato");
		else
			System.out.println("Errore nel salvataggio");

	}
}
