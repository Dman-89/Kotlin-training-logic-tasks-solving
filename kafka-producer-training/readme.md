https://www.conduktor.io/kafka/how-to-start-kafka-using-docker
1) git clone https://github.com/conduktor/kafka-stack-docker-compose.git

2) docker-compose -f zk-single-kafka-single.yml up -d


Stopping Kafka on Docker

Now that we've verified the installation, we can stop the services by stopping the respective containers:
$ docker-compose -f zk-single-kafka-single.yml stop
Stopping kafka1 ... done
Stopping zoo1   ... done

To remove all the resources including the containers altogether, use down instead of stop:
$ docker-compose -f zk-single-kafka-single.yml down
Removing kafka1 ... done
Removing zoo1   ... done
Removing network kafka-stack-docker-compose_default