<h1 align="center">DoadorSangueAPI</h1>


# Objetivo do projeto
<p>O projeto tem como objetivo implementar uma solução com Spring Boot onde serão informados candidados para doação de sangue, será feito uma análise desses doadores e retornado em forma de dados.</p> 

## ✔️ Técnicas ferramentas e tecnologias utilizadas 

- ``Java 17``
- ``InteliJ IDEA``
- ``Insomnia``
- ``Mysql``
- ``POO``
- ``Design Pattern``

<p>Frameworks utilizados:</p>

### jpa: 
<p>framework utilizado para persistencia de objetos</p>

### hibernate:
<p>framework utilizado para mapear os relacionamentos das entidades.</p>

### org.json: 
<p>framwork utilizado para trabalhar com json para serializar e deserializar objetos</p>

### swagger-ui:
<p>framework de documentação onde ficam disponiveis todos os end-points da api</p>

### lombok:
<p>framework de produtividade onde as anotações geram getter setter e construtores para as entidades e objetos</p>

### spring-boot-devtools:
<p>framework de produtividade ao salvar os arquivos do programa o spring faz uma recarga automática das classes sem derrubar a aplicação</p>

### spring-security:
<p>framework de segurança onde é feito um filtro de todas as requisições</p>

### jwt:
<p>framework de segurança que atua junto com o spring-security, ele é responsavel pela geração dos tokens jwt onde são armazenados login do usuario e permissões do mesmo</p>

<h1 align="center"></h1>


http://localhost:8080/swagger-ui/index.html


### Configurando ambiente do projeto 

Instalar o maven e o java 17
https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
https://maven.apache.org/download.cgi
https://maven.apache.org/install.html


#### 1ª Faça um clone da aplicação

- `git clone https://github.com/Felipeisantos/DoadorSangueAPI.git`
- `cd DoadorSangueAPI`
 
### Usando o maven para executar a aplicação

#### 2º no diretório da aplicação com o apache maven instalado, abra o terminal e execute o seguinte comando:

` mvn spring-boot:run `




