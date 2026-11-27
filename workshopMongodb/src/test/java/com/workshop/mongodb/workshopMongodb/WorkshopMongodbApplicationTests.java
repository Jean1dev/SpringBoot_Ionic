package com.workshop.mongodb.workshopMongodb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(MongoTestcontainersConfig.class)
class WorkshopMongodbApplicationTests {

	@Test
	void contextLoads() {
	}

}
