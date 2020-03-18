package murex.dev.mxem.Scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import static java.lang.Thread.sleep;

@EnableAutoConfiguration
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@Slf4j
@EnableMongoRepositories(basePackages = "murex.dev.mxem.Scheduler")
public class SchedulerApplication {



	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
	}

}
