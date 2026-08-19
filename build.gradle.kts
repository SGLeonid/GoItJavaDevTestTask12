plugins {
    id("java")
}

group = "org.forestwizard"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("com.h2database:h2:2.3.232")
    implementation("org.hibernate.orm:hibernate-core:7.4.5.Final")
    implementation("org.flywaydb:flyway-core:13.2.0")
    compileOnly("org.projectlombok:lombok:1.18.46")
    annotationProcessor("org.projectlombok:lombok:1.18.46")
    implementation("com.google.code.gson:gson:2.14.0")
}

tasks.test {
    useJUnitPlatform()
}