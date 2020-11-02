# Kafka Rest Consumer

Spring boot based application to consume data from REST api with custom reader and produce the data into kafka topic. 


Application Properties
----------------------
Basic starting application properties to start.

any of the sensitive values can be upload into SSM and refer with `secret.` where application try to retrieve from amazon systems manager parameter store.

```properties
rest.endpoint.url=https://jsonplaceholder.typicode.com/posts
rest.tag.list=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100
rest.tag.name=id
spring.kafka.producer.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.topic=restrepository
spring.kafka.producer.errortopic=errorrecords
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.retries=3
spring.kafka.producer.acks=1
spring.main.web-application-type=none
```

## Usage

```bash
#Linux/Unix based systems
bin/kafkarest

#Windows based systems

bin\kafkarest.bat

```

Where can I get the latest release?
-----------------------------------
You can get source from [SCM](https://github.com/bkosaraju/kafkarestconsumer).


## Build Instructions 

```bash
./gradlew clean build

#Linux/Windows binaries can be found at build/distribution directory 
```

## Contributing
Please feel free to raise a pull request in case if you feel like something can be updated or contributed

## License
[Apache](http://www.apache.org/licenses/LICENSE-2.0.txt)