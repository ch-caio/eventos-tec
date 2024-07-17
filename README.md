# Amazon S3 Access Project with Spring Boot

This project demonstrates how to access and manipulate files stored in Amazon S3 using Spring Boot. To run the project locally, you need to configure your AWS access credentials using the AWS CLI.

## Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- Valid AWS account
- AWS CLI installed and configured

## AWS CLI Configuration

For Spring Boot to access Amazon S3, you need to configure your AWS credentials using the AWS CLI. Follow the steps below to configure it:

1. **Install the AWS CLI**:
    - Follow the installation instructions on the official AWS CLI website: [AWS CLI Installation](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2.html)

2. **Configure your AWS credentials**:
    - Open your terminal and run the command:
      ```sh
      aws configure
      ```
    - Enter your AWS credentials when prompted:
      ```sh
      AWS Access Key ID [None]: <your-access-key-id>
      AWS Secret Access Key [None]: <your-secret-access-key>
      Default region name [None]: <your-default-region>
      Default output format [None]: <your-default-output-format (e.g., json)>
      ```

## Spring Boot Project Configuration

Spring Boot is automatically configured to use the credentials provided by the AWS CLI to access Amazon S3. No additional configurations are needed in `application.properties` or `application.yml`.

### Maven Dependencies

Add the following dependencies to your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-aws</artifactId>
    <version>2.2.6.RELEASE</version>
</dependency>
<dependency>
    <groupId>com.amazonaws</groupId>
    <artifactId>aws-java-sdk-s3</artifactId>
    <version>1.11.1000</version>
</dependency>
