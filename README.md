<div align="center">
<img src="https://png.pngtree.com/png-vector/20220616/ourmid/pngtree-people-contact-logo-vector-icon-png-image_5111734.png"/>
</div>

###

<div align="center">
  <a href="https://www.linkedin.com/in/pedro-neves-867001258/" target="_blank">
    <img src="https://img.shields.io/static/v1?message=LinkedIn&logo=linkedin&label=&color=0077B5&logoColor=white&labelColor=&style=for-the-badge" height="25" alt="linkedin logo"  />
  </a>
</div>

###

# Controle de Contatos 

Esse projeto foi realizado visando apresentar habilidades técnicas relacionados a linguagem de programação Backend Java, utilizando como framework principal, o Spring, tendo o escopo como o cadastro de pessoas e seus respectivos meios de contato.
<div align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="40" alt="java logo"  />
  <img width="12" />
</div>

🛠 Tecnologias Utilizadas:

Java (Spring Boot)

Maven (Gerenciamento de dependências)

Banco de Dados (JPA / Hibernate)

Swagger (OpenAPI) (Documentação da API)
## Rodando localmente

### Pré requisitos 

 Recomendados:
- JDK 21 https://www.oracle.com/br/java/technologies/downloads/#java21
- Maven 3.9.9 https://maven.apache.org/download.cgi
- Eclipse ou Intellij community.
- Postman para realizar requisições.

### Variáveis de Ambiente

- Para rodar esse projeto, você vai precisar adicionar as seguintes variáveis de ambiente no seu Path:

`%JAVA_HOME%\bin`

`%M2_HOME%\bin`

Crie também nas variáveis do sistema:

`JAVA_HOME`
C:\Program Files\Java\jdk-21 (local da instalação do JDK)

`M2_HOME`
C:\Program Files\apache-maven-3.9.9

- Clone o projeto

```bash
  git clone https://github.com/PedroNeves-git/controle-contatos.git
```
- Entre no diretório do projeto

```bash
  cd controle-contatos
```
- Execute o projeto com o Maven ou no Spring Boot:

```bash
  mvn spring-boot:run
```
- Ou se preferir, execute via terminal, mvn clean install no diretório do projeto, será gerado um arquivo controle-contatos-0.0.1.jar no diretório: controle-contatos\target
```bash
 java -jar controle-contatos-0.0.1.jar
``` 

- Acesse a documentação Swagger:

```bash
  http://localhost:8080/swagger-ui/index.html
``` 

- Importe a collection dos endpoints para o Postman no diretório:

```bash
 cd controle-contatos\src\controle-contatos.postman_collection
``` 



## Instruções de uso

- Acesse:   http://localhost:8080/
- Banco:    http://localhost:8080/h2-console  username:sa/password:(em branco)
- Swagger:  http://localhost:8080/swagger-ui/index.html

