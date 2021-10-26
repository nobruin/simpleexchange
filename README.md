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
https://simpleexchange-production.herokuapp.com/swagger-ui/




#Apresentação do projeto:

O projeto visa fazer conversões de moeda. Enviando uma moeda de origem em formato ISO 4217, e uma moeda de destino no mesmo padrão, e o valor que será convertido.

##features
Seguiram mais ou menos a ordem do pedido do PDF. Porém parti da arquitetura basica para as featuraes. Não criei uma branch pra cada feature por estar trabalhando sozinho. Porém os commits estão em ordem que apresenta mais ou menos a construção das features

### Spring boot: 
Escolhi o  por ser uma ferramenta, simples de usar e dentro do escopo da aplicação ser possível. Se fosse uma escolha puramente tecnica escolheria Quarkus e Vert.x. Porque o
Quarkus facilitaria a criação do container que foi usado para deploy no heroku. 
### JPA/Hibernate:
É sem sombra de duvidas padrão de mercado e provavelmente a ORM mais usada no mundo java.
### Swagger:
Também é um dos padrões de mercado para documentação de apis, além de ser bastante simples criar exemplos de uso e etc.
### khttp
Usei por ser simples de usar e também por possuir tratamento de excessões proprias já havia usado em outro projeto com kotlin e havia gostado da experiencia. 
Segue caso tenham curiosidade (https://github.com/nobruin/planetsapi)


