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

## Dados de teste

O arquivo `src/main/resources/import.sql` já popula o banco com 2 madeiras, 2 marcas, 2 perfis de braço, 2 acessórios e 2 violões (1 aço + 1 nylon). Isso é suficiente para testar todos os endpoints **públicos** de catálogo (`/produtos`, `/violoes/...`, `/acessorios/listar`, `/madeiras/listar`, `/marcas/listar`, `/perfis-braco/listar`).

Usuários NÃO podem ser inseridos via `import.sql` porque a autenticação fica no Keycloak, não no banco da aplicação. Para testar endpoints autenticados, siga o roteiro abaixo.

## Roteiro de teste no Swagger

Abra http://localhost:8080/q/swagger-ui e execute na ordem:

1. **Criar um cliente**: `POST /usuarios/completo` com um body como:
   ```json
   {
     "login": "Teste01",
     "senha": "teste123",
     "email": "teste01@example.com",
     "nomeCompleto": "Teste Sobrenome",
     "cpf": "09669090114",
     "telefone": "63912345678",
     "dataNascimento": "2000-01-01"
   }
   ```
   Isso cria o usuário no banco e no Keycloak já com a role `CLIENTE`.

2. **Logar**: `POST /auth/login` com `{ "login": "Teste01", "senha": "teste123" }`. Copie o `accessToken`.

3. No Swagger, clique em **Authorize** (canto superior direito) e cole `Bearer <accessToken>`. A partir daí, todos os endpoints autenticados respondem.

4. Endpoints disponíveis pro cliente: `PUT /clientes/me`, `POST/GET /clientes/enderecos`, `POST/GET /clientes/pedidos`, `POST/GET/DELETE /clientes/lista-desejos`.

5. Para testar endpoints **ADMIN** (cadastrar/atualizar/deletar do catálogo), promova o usuário no Keycloak:
   - Admin Console → realm `ecommerce` → Users → seu usuário → **Role mapping** → Assign role → marque `ADMIN`.
   - Faça `POST /auth/login` de novo para receber um token novo já com a role `ADMIN`.
