# Documentação da API

## Mapper
Usado para transformar objetos entre camadas (e.g., de Entity para DTO e vice-versa).

---

## Exceções Personalizadas

- **NegativeNumberException**: Lançada quando coordenadas ou distâncias fornecidas são negativas.

---

## Endpoints

### **GET /pois**
Retorna todos os POIs.

#### Resposta:
- **200 OK**: Lista de POIs em formato JSON.

---

### **POST /pois**
Cria um novo POI.

#### Request Body:
```json
{
  "nome": "string",
  "coordenadaX": "integer",
  "coordenadaY": "integer"
}
```

#### Resposta:
- **201 Created**: Detalhes do POI criado em formato JSON.

---

### **GET /pois/{coordenadaX}/{coordenadaY}/{dmax}**
Retorna os POIs dentro da distância máxima especificada.

#### Parâmetros:
- `coordenadaX`: Coordenada X do ponto de origem.
- `coordenadaY`: Coordenada Y do ponto de origem.
- `dmax`: Distância máxima para busca.

#### Resposta:
- **200 OK**: Lista de POIs em formato JSON.

---

## Configuração e Build

Este projeto utiliza o Maven como ferramenta de build. A configuração principal está no arquivo `pom.xml`, que inclui as seguintes dependências:

### Dependências Principais:
- **spring-boot-starter-data-jpa**: Persistência de dados.
- **spring-boot-starter-web**: Criação de APIs REST.
- **spring-boot-starter-validation**: Validação de dados.
- **spring-boot-starter-hateoas**: Suporte a hypermedia.
- **mysql-connector-j**: Driver para integração com MySQL.
- **springdoc-openapi-starter-webmvc-ui**: Documentação interativa com Swagger/OpenAPI.
- **mapstruct**: Mapeamento de objetos entre camadas.

### Dependências para Testes:
- **spring-boot-starter-test**: Conjunto de ferramentas de teste.
- **JUnit 5**: Framework para testes unitários.
- **Mockito**: Mocking em testes.

### Ferramentas de Build:
- **spring-boot-maven-plugin**: Facilita o empacotamento da aplicação como um jar executável.
- **maven-compiler-plugin**: Configurado para Java 21 e suporte a processadores de anotações, como o MapStruct.

---

## Pré-requisitos

- **Java 21**: Linguagem principal para desenvolvimento.
- **Maven 3.8+**: Gerenciador de dependências e build.
- **MySQL**: Banco de dados relacional para persistência.

---

## Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```

2. Configure o banco de dados MySQL com as credenciais definidas no arquivo `application.yml`.

3. Compile e execute o projeto com Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Acesse a API no navegador ou em ferramentas como Postman:  
   `http://localhost:8080`.

---

## Tecnologias Utilizadas

- **Java 21**: Linguagem principal.
- **Spring Boot 3.4.0**: Framework para construção da API.
- **Hibernate**: ORM para acesso ao banco de dados.
- **MySQL**: Banco de dados relacional.
- **MapStruct**: Mapeamento de objetos.
- **Swagger/OpenAPI**: Documentação interativa.
- **JUnit 5** e **Mockito**: Ferramentas de testes.
- **H2 Database**: Banco em memória para testes.

---

## Autor

Desenvolvido por [Camila Kfouri].
