package io.github.navneetvishwakarma.governor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.navneetvishwakarma.governor.client.GeminiClient;

@SpringBootApplication
public class AgentGovernorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentGovernorApplication.class, args);
	}

	// Temporary — delete after confirming the call works
	@Bean
	CommandLineRunner smokeTest(GeminiClient client) {
		return args -> {
			String reply = client.generate("Reply with the single word: ready");
			System.out.println("Gemini replied: " + reply);
		};
	}

}
