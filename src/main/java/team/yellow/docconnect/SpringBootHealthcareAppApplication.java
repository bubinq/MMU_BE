package team.yellow.docconnect;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
		title = "Spring Boot DocConnect App REST APIs",
		description = "Spring Boot DocConnect App REST APIs Documentation",
		version = "v1.0",
		contact = @Contact(
				name = "Ruben, Alexandr, Daniel",
				email = "triple@gmail.com"
		)
),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot DocConnect App Documentation",
				url = "https://gitlab.mentormate.bg/base/mmu/foundation/yellow-squad-project/back-end"
		)
)
@SpringBootApplication
public class SpringBootHealthcareAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHealthcareAppApplication.class, args);
	}

}
