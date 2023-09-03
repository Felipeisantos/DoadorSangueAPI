<h1 align="center">DoadorSangueAPI</h1>


# Objetivo do projeto
<p>O objetivo deste projeto é implementar uma solução com o Spring Boot, onde os candidatos para doação de sangue são informados, passam por uma análise e os resultados são retornados em formato de dados.</p>
<p>Este repositório é a parte back-end, o repositório do front-end pode ser encontrado em: https://github.com/Felipeisantos/DoadorSangue-front</p>

## ✔️ Técnicas, ferramentas e tecnologias utilizadas:

- ``Java 17``
- ``InteliJ IDEA``
- ``Insomnia``
- ``Mysql``
- ``POO``
- ``Design Pattern``

## Frameworks utilizados

### Jpa: 
<p>Framework utilizado para persistência de objetos.</p>

### Hibernate:
<p>Framework utilizado para mapear os relacionamentos das entidades.</p>

### Org.json: 
<p>Framwork utilizado para trabalhar com json para serializar e deserializar objetos.</p>

### Swagger-ui:
<p>Framework de documentação onde ficam disponíveis todos os end-points da api.</p>

### Lombok:
<p>Framework de produtividade onde as anotações geram getter setter e construtores para entidades e objetos.</p>

### Spring-boot-devtools:
<p>Framework de produtividade onde ao salvar os arquivos do programa o spring faz uma recarregamento automática das classes, sem derrubar a aplicação.</p>

### Spring-security:
<p>Framework de segurança onde é feito um filtro de todas as requisições.</p>

### Jwt:
<p>Framework de segurança que atua junto com o spring-security, este é responsavel pela geração dos tokens jwt onde são armazenados o login do usuário e permissões do mesmo.</p>

<h1 align="center"></h1>


#### Configurando ambiente do projeto 

Instalar o maven e o java 17

https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

https://maven.apache.org/download.cgi

https://maven.apache.org/install.html

#### Instalar mysql V8

https://dev.mysql.com/downloads/mysql/



#### 1ª Faça um clone da aplicação

- `git clone https://github.com/Felipeisantos/DoadorSangueAPI.git`
- `cd DoadorSangueAPI`

  
#### 2ª Execute o script DDL no banco de dados que se encontra na pasta patches

- `cd patches`
- `cd notepad DDL-Create.sql`
- `cd..`
 
#### 3º No diretório da aplicação com o apache maven instalado, abra o terminal e execute o seguinte comando

` mvn spring-boot:run `


#### 4º Para visualizar a documentação do projeto acesse: 
http://localhost:8080/swagger-ui/index.html



