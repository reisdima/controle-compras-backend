package vincenzo.caio.controle_compras_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ControleComprasBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleComprasBackendApplication.class, args);
	}

}
