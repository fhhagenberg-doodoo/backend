import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.0.RC1"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("org.jmailen.kotlinter") version "2.3.2"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"

	jacoco
}

springBoot {
	buildInfo()
}

group = "org.fhooe.mcm.ci"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("http://oss.jfrog.org/artifactory/oss-snapshot-local/")}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.projectlombok:lombok:1.18.12")

	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	runtimeOnly("io.r2dbc:r2dbc-postgresql")
	runtimeOnly("org.postgresql:postgresql")

	// ---------------------- Testing ------------------------------
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}

	testImplementation("io.projectreactor:reactor-test")

	testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
	testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.3.1")


	// ---------------------- Documentation ------------------------------

	compile ("io.springfox:springfox-swagger2:3.0.0-SNAPSHOT")
	compile ("io.springfox:springfox-spring-webflux:3.0.0-SNAPSHOT")
	compile ("io.springfox:springfox-swagger-ui:3.0.0-SNAPSHOT")

}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<ProcessResources> {
	filesMatching("**/*.properties") {
		expand(project.properties)
	}
}

tasks.withType<JavaCompile> {
	options.encoding = "UTF-8"
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
	archiveFileName.set("doodoo-backend.jar")
}

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
	if (project.hasProperty("prod")) {
		setArgsString("--spring.profiles.active=prod")
	}
}

tasks.test {
	finalizedBy("jacocoTestReport")
	doLast {
		println("View code coverage at:")
		println("file://$buildDir/reports/jacoco/test/html/index.html")
	}
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
}



