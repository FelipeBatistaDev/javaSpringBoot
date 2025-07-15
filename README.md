# 🔐 Spring Boot - API REST com Autenticação JWT

Este projeto é uma **API RESTful** desenvolvida com **Spring Boot**, que implementa um sistema de **autenticação segura via JWT (JSON Web Token)**. Ideal como base para aplicações que necessitam de controle de acesso e autenticação moderna e robusta.

---

## 🚀 Tecnologias Utilizadas

- ✅ Java + Spring Boot
- ✅ Spring Security
- ✅ JWT (JSON Web Token)
- ✅ MySQL
- ✅ Maven
- ✅ JPA + Hibernate

---

## 📚 Funcionalidades

- 🔐 Cadastro e login de usuários
- 🔒 Proteção de rotas com autenticação JWT
- 🧾 Retorno de token válido no login
- ✅ Validação de token em requisições autenticadas
- 🚫 Acesso negado para tokens inválidos ou expirados

---

## ⚙️ Como rodar o projeto

### ✅ Pré-requisitos

- Java 21+
- MySQL rodando
- Maven instalado

### 🧪 Passos para execução

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio

# Configure seu application.properties ou application.yml com as credenciais do MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
spring.datasource.username=root
spring.datasource.password=senha

# Rode o projeto
./mvnw spring-boot:run
🔐 Autenticação JWT
Após o login bem-sucedido (via /login), será retornado um token JWT no corpo da resposta. Este token deverá ser enviado nas próximas requisições como header:

Authorization: Bearer <seu_token>
As rotas protegidas só funcionarão com um token válido.

🛠 Estrutura de diretórios (sugestão)
src/
├── main/
│   ├── java/
│   │   └── medi/
│   │       └── voli/
│   │           ├── controller/
│   │           ├── domain/
│   │           ├── infra/
│   └── resources/
│       ├── application.yml
│       └── db/
│           └── migration/
🧪 Testando com cURL ou Postman
Login

POST /login
Content-Type: application/json

{
  "login": "usuario@email.com",
  "password": "123456"
}

Requisição protegida

GET /medicos
Authorization: Bearer SEU_TOKEN_JWT