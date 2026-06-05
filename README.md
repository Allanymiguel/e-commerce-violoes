# E-commerce de Violões

Projeto da disciplina de Tópicos em Programação. Implementa uma API REST de e-commerce de violões e acessórios usando Quarkus, com autenticação via Keycloak e persistência em PostgreSQL.

## Tecnologias

- Java 17+
- Quarkus
- PostgreSQL
- Keycloak (autenticação OIDC)
- Hibernate ORM com Panache

## Pré-requisitos

Antes de subir a aplicação, é necessário ter rodando localmente:

1. **PostgreSQL** com um banco chamado `topicos1_ecommerce`
2. **Keycloak** em `http://localhost:8180` com um realm chamado `ecommerce` configurado:
   - Client `ecommerce-app` com **Direct access grants** habilitado e **Client authentication** ON
   - Roles `ADMIN` e `CLIENTE` no realm

## Configuração

Os arquivos de configuração com segredos não vêm versionados. Copie os templates e ajuste para seu ambiente:

```bash
# Configuração para rodar em dev (mvn quarkus:dev)
# Crie src/main/resources/application-dev.properties com base em application.properties,
# preenchendo:
#   quarkus.datasource.username, .password, .jdbc.url
#   quarkus.oidc.credentials.secret (Client Secret do ecommerce-app no Keycloak)
#   quarkus.oidc.auth-server-url, quarkus.rest-client.keycloak-*.url
#   keycloak.admin.* (credenciais do realm master para criar usuários)

# Configuração para rodar testes (mvn test)
cp src/main/resources/application-test.properties.example src/main/resources/application-test.properties
# edite com seus dados do PostgreSQL local
```

## Rodando em dev

```bash
./mvnw quarkus:dev
```

Aplicação em http://localhost:8080
- Swagger UI: http://localhost:8080/q/swagger-ui
- Dev UI: http://localhost:8080/q/dev

## Rodando os testes

```bash
./mvnw test
```

Os testes usam `@InjectMock` nos services, então não tocam o banco. Precisam apenas que o Postgres esteja **acessível** para o Quarkus subir.

## Fluxo de cadastro/login

1. `POST /usuarios/completo` — cria usuário com dados completos (login, senha, email, nomeCompleto, cpf, telefone, dataNascimento). O cadastro cria o usuário também no Keycloak e atribui a role CLIENTE.
2. `POST /auth/login` — retorna o `access_token` para usar em `Authorization: Bearer <token>` nos endpoints protegidos.
3. Endpoints com `@RolesAllowed("CLIENTE")` ou `@RolesAllowed("ADMIN")` exigem o token correspondente.
