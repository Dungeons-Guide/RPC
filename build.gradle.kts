var protobufVersion = "3.14.0"
var grpcVersion = "1.35.0"


plugins {
    id("java")
    id("idea")
    id("com.google.protobuf") version "0.9.4"
}

group = "kr.syeyoung.dungeonsguide"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}



dependencies {
    runtimeOnly("io.grpc:grpc-netty-shaded:1.67.1")
    implementation("io.grpc:grpc-protobuf:1.67.1")
    implementation("io.grpc:grpc-stub:1.67.1")

    compileOnly("jakarta.annotation:jakarta.annotation-api:1.3.5")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.25.3"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.67.1"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                create("grpc")
            }
        }
    }
}