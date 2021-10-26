#Simple Exchange Api 
É uma api escrita em kotlin usando springboot, jpa, swagger,containers(docker), e outras tecnologias.
Para rodar a aplicação com o docker. É necessario criar o arquivo .jar da aplicação, usando o comando:
```
./gradlew assemble

ou 

./gradlew bootJar

```

Após é preciso usar o comando:
```
docker-compose up 
```
Obs: É preciso ter o docker instalado na maquina para o mesmo. 


Após levantar o container é possivel acessar a documentação e fazer teste de uso da api atraves do endereço:
http://{server}/swagger-ui/ 

Também é possivel usar a aplicação através do comando:
```
./gradlew clean bootrun

```

Para que os testes sejam executados podemos usar os comandos:
```
./gradlew check

Ou 

./gradlew test 

```
O ultimo roda os testes unitarios da aplicação, o primeiro roda todos os testes da aplicação. 
Criei a aplicação com CI no gitlab e a integração com o heroku. Porém falta o deploy da imagem docker no heroku. Fazendo a integração direto subi a aplicação no endereço:
```
https://simpleexchange-production.herokuapp.com/swagger-ui/
```



#Apresentação do projeto:

O projeto visa fazer conversões de moeda. Enviando uma moeda de origem em formato ISO 4217, e uma moeda de destino no mesmo padrão, e o valor que será convertido. 
Escolhi o spring boot por ser uma ferramenta, que já estou acostumado a usar. Se fosse escolher pelo projeto escolheria Quarkus e Vert.x. Porque o 
Quarkus facilitaria a criação do container que foi usado para deploy no heroku. 



