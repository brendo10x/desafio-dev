# Desafio programação - para vaga desenvolvedor
 
### Para rodar a aplicação
Requisitos
* Git
* Docker e Docker Compose

Basta executar os seguintes comandos:
```bash
git clone git@github.com:brendo10x/desafio-dev.git
```
```bash
cd desafio-dev
```
```bash
docker-compose up
```
Após finalizado, acessar o frontend da aplicação:
* Obs: para importação, o arquivo CNAB.txt está na raiz do projeto.
```bash
http://localhost:5200/
```
Backend da aplicação
```bash
http://localhost:9090/swagger-ui.html
```

### Setup de desenvolvimento
Requisitos
* Git
* Docker e Docker Compose
* Java 11
* Maven 3.6.3
* NPM
* Node 14

## 1. Rodar o Docker
```bash
git clone git@github.com:brendo10x/desafio-dev.git
```
```bash
cd desafio-dev
```
```bash
docker-compose up
```
Após rodar, o docker subirá os seguintes containers:
1. Um app completo de produção:
* Frontend com Angular: http://localhost:5200/
* Backend com Spring Boot: http://localhost:9090/
* Documentação da API do Backend com Spring Boot: http://localhost:9090/swagger-ui.html
* Banco de dados PostgreSQL de produção (na porta 7000:5432)
2. Um banco de dados de teste:
* Banco de dados PostgreSQL de teste/desenvolvimento (na porta 9000:5432)

Conforme descritos em detalhes nos arquivos:
1. docker-compose.yml - na pasta raiz do projeto
2. backend/Dockerfile
3. frontend/Dockerfile

## Configurando o backend com Spring Boot
Requisitos:
- Java 11
- Maven 3.6.3

Rodar os seguintes comandos:
```bash
cd desafio-dev/backend
```
Por fim, importe na sua IDE de preferência.

Após executado, basta acessar o backend:
```bash
http://localhost:8080/
```
Documentação da API:
```bash
http://localhost:8080/swagger-ui.html
```
### Como rodar os testes?
Obs: para os testes, será utilizado o JUnit e DBUnit em conjunto. Os testes estão na pasta backend/src/test/java.

## Configurando o frontend com Angular
Requisitos:
- Node.js 14
- npm
- Angular CLI

Rodar os seguintes comandos:
```bash
cd desafio-dev/frontend
```
Para baixar as dependências
```bash
npm install
```
Rodar o app
```bash
ng serve
```
Se preferir, importe na sua IDE de preferência.

Mais informações na documentação oficial: https://angular.io/guide/setup-local

Após executado, basta acessar o app:
```bash
http://localhost:4200/
```
Obs: lembrando que o backend precisa ser executado antes, para disponibilizar a API para o front consumir.

## Overview das ferramentas utilizadas no desafio
* Java 11
* Spring Boot
* Spring Data JPA
* Maven
* PostgreSQL
* Docker
* Docker Compose
* Flyway
* ModelMapper
* JUnit
* DBUnit
* Hamcrest
* Lombok
* Slf4j
* Swagger2
* Postman
* Spring Tools Suite
* Git
* Angular
* Node js
* WebStorm

#### Padrões e boas práticas adotados no projeto
- Padrões de projeto: Strategy, Facade, Singleton, DTO, SOLID, Clean Code.
- Ágil: TDD, BDD, Kanban e Pomodoro
