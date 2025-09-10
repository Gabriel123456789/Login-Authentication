# Login Authentication API - Documentação OpenAPI

Este projeto foi aprimorado com anotações completas do OpenAPI/SpringDoc para documentação automática da API.

## 🚀 Funcionalidades Implementadas

### 1. Configuração OpenAPI
- **Arquivo:** `OpenApiConfig.java`
- **Funcionalidades:**
  - Configuração de metadados da API (título, versão, descrição)
  - Informações de contato e licença
  - Configuração de autenticação JWT Bearer Token
  - Esquema de segurança global

### 2. Controllers Documentados

#### AuthController
- **Tag:** "Authentication"
- **Endpoints documentados:**
  - `GET /ping` - Health Check
  - `POST /auth/register` - Registro de usuário
  - `POST /auth/login` - Login de usuário
- **Anotações utilizadas:**
  - `@Tag` - Agrupamento de endpoints
  - `@Operation` - Descrição detalhada dos endpoints
  - `@ApiResponses` - Documentação de respostas possíveis
  - `@ApiResponse` - Códigos de status e descrições

#### DemoController
- **Tag:** "Demo"
- **Endpoints documentados:**
  - `GET /api/v1/demo` - Endpoint protegido (requer JWT)
- **Segurança:** Configurado para exigir Bearer Authentication

### 3. DTOs Documentados

Todos os DTOs foram enriquecidos com anotações `@Schema`:

#### userRegister
- Descrição dos campos email e password
- Validações documentadas
- Exemplos de uso

#### userLoginRequest
- Campos de login documentados
- Validações adicionadas
- Exemplos práticos

#### userResponse
- Documentação dos dados retornados
- Exemplos de IDs e emails

#### LoginResponseDTO
- Documentação do token JWT
- Exemplo de token

## 🛠️ Configurações Adicionais

### application.properties
Configurações do SpringDoc adicionadas:
```properties
# Configurações do SpringDoc OpenAPI
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.tryItOutEnabled=true
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
springdoc.swagger-ui.filter=true
```

### Dependência Maven
O projeto já continha a dependência necessária:
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.0</version>
</dependency>
```

## 📋 URLs Importantes

- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **API Docs JSON:** http://localhost:8080/v3/api-docs
- **API Docs YAML:** http://localhost:8080/v3/api-docs.yaml

## 🔒 Autenticação

A API utiliza JWT Bearer Token. Para testar endpoints protegidos:

1. Faça login via `POST /auth/login`
2. Copie o token retornado
3. No Swagger UI, clique em "Authorize"
4. Digite: `Bearer {seu-token-aqui}`
5. Teste os endpoints protegidos

## 📝 Exemplos de Uso

### Registro de Usuário
```json
POST /auth/register
{
  "email": "usuario@exemplo.com",
  "password": "minhasenha123"
}
```

### Login
```json
POST /auth/login
{
  "email": "usuario@exemplo.com",
  "password": "minhasenha123"
}
```

### Resposta do Login
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

## ✅ Status da Implementação

- ✅ Configuração OpenAPI completa
- ✅ Controllers totalmente documentados
- ✅ DTOs com anotações @Schema
- ✅ Configuração de segurança JWT
- ✅ Validações documentadas
- ✅ Exemplos de uso incluídos
- ✅ Swagger UI funcional

## 🔧 Como Executar

1. Compile o projeto:
   ```bash
   ./mvnw clean compile
   ```

2. Execute a aplicação:
   ```bash
   java -jar target/Login-0.0.1-SNAPSHOT.jar
   ```

3. Acesse a documentação:
   - Navegue para http://localhost:8080/swagger-ui.html

A documentação OpenAPI está completamente funcional e pronta para uso!
