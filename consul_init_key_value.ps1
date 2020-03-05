
docker exec consul-1 consul kv put config/Authorization/application.jwt.secretKey securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecure
docker exec consul-1 consul kv put config/Authorization/application.jwt.tokenExpirationAfterDays 10
docker exec consul-1 consul kv put config/Authorization/security.user.name admin
docker exec consul-1 consul kv put config/Authorization/security.user.password admin
docker exec consul-1 consul kv put config/Authorization/mxpipeline.ldap.url ldap://root-dc.murex.com:3268
docker exec consul-1 consul kv put config/Authorization/mxpipeline.ldap.domain murex.com
docker exec consul-1 consul kv put config/Authorization/spring.datasource.platform postgres
docker exec consul-1 consul kv put config/Authorization/spring.datasource.url jdbc:postgresql://localhost:5432/userDB
docker exec consul-1 consul kv put config/Authorization/spring.datasource.username postgres
docker exec consul-1 consul kv put config/Authorization/spring.datasource.password admin

docker exec consul-1 consul kv put config/Environments/spring.data.mongodb.port 27017
docker exec consul-1 consul kv put config/Environments/spring.data.mongodb.host localhost
docker exec consul-1 consul kv put config/Environments/spring.data.mongodb.database environments
docker exec consul-1 consul kv put config/Environments/application.jwt.secretKey securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecure
docker exec consul-1 consul kv put config/Environments/application.jwt.tokenExpirationAfterDays 10
docker exec consul-1 consul kv put config/Environments/security.user.name admin
docker exec consul-1 consul kv put config/Environments/security.user.password admin
docker exec consul-1 consul kv put config/Environments/spring.rabbitmq.host localhost
docker exec consul-1 consul kv put config/Environments/spring.rabbitmq.port 5672
docker exec consul-1 consul kv put config/Environments/spring.rabbitmq.username guest
docker exec consul-1 consul kv put config/Environments/spring.rabbitmq.password guest
docker exec consul-1 consul kv put config/Environments/javainuse.rabbitmq.exchange javainuse.exchange
docker exec consul-1 consul kv put config/Environments/javainuse.rabbitmq.queue javainuse.queue
docker exec consul-1 consul kv put config/Environments/javainuse.rabbitmq.routingkey javainuse.routingkey

docker exec consul-1 consul kv put config/Gateway/security.user.name admin
docker exec consul-1 consul kv put config/Gateway/security.user.password admin

docker exec consul-1 consul kv put config/Scheduler/security.user.name admin
docker exec consul-1 consul kv put config/Scheduler/security.user.password admin
docker exec consul-1 consul kv put config/Scheduler/spring.rabbitmq.host localhost
docker exec consul-1 consul kv put config/Scheduler/spring.rabbitmq.port 5672
docker exec consul-1 consul kv put config/Scheduler/spring.rabbitmq.username guest
docker exec consul-1 consul kv put config/Scheduler/spring.rabbitmq.password guest
docker exec consul-1 consul kv put config/Scheduler/javainuse.rabbitmq.exchange javainuse.exchange
docker exec consul-1 consul kv put config/Scheduler/javainuse.rabbitmq.queue javainuse.queue
docker exec consul-1 consul kv put config/Scheduler/javainuse.rabbitmq.routingkey javainuse.routingkey
docker exec consul-1 consul kv put config/Scheduler/spring.data.mongodb.port 27017
docker exec consul-1 consul kv put config/Scheduler/spring.data.mongodb.host localhost
docker exec consul-1 consul kv put config/Scheduler/spring.data.mongodb.database scheduler
docker exec consul-1 consul kv put config/Scheduler/application.jwt.secretKey securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecure
docker exec consul-1 consul kv put config/Scheduler/application.jwt.tokenExpirationAfterDays 10


docker exec consul-1 consul kv put config/Users/spring.datasource.platform postgres
docker exec consul-1 consul kv put config/Users/spring.datasource.url jdbc:postgresql://localhost:5432/userDB
docker exec consul-1 consul kv put config/Users/spring.datasource.username postgres
docker exec consul-1 consul kv put config/Users/spring.datasource.password admin
docker exec consul-1 consul kv put config/Users/application.jwt.tokenExpirationAfterDays 10
docker exec consul-1 consul kv put config/Users/application.jwt.secretKey securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecure
docker exec consul-1 consul kv put config/Users/security.user.name admin
docker exec consul-1 consul kv put config/Users/security.user.password admin