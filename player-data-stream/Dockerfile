FROM maven:3.6.3-jdk-11

# create a folder in the container.
RUN mkdir -p /player-stream

# make a copy all the contents in the current directory (host) into the newly created folder in the container.
COPY  . /player-stream

# make the active directory the container's arbitrator dir. (how commands run in this context)
WORKDIR /player-stream

RUN mvn clean
RUN mvn install

# Container stores m2 content in /root/.m2/repository/

# copy jar (stored in a dir in container) to the workdir
RUN cp /root/.m2/repository/org/example/player-data-stream/1.0-SNAPSHOT/player-data-stream-1.0-SNAPSHOT-jar-with-dependencies.jar .

CMD ["java", "-jar", "player-data-stream-1.0-SNAPSHOT-jar-with-dependencies.jar"]


