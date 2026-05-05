# Consumo CEP — Spring Boot + OpenFeign + H2 + Swagger

Projeto Spring Boot que consome a API pública **ViaCEP** via **OpenFeign**, persiste os endereços no banco **H2** em memória e exibe os dados em uma **página HTML** com Thymeleaf.

---

## 🏗️ Estrutura MVC Correta

```
src/main/java/com/accenture/consumo/
│
├── ConsumoApplication.java              ← @SpringBootApplication + @EnableFeignClients
│
├── interfaces/
│   └── CepService.java                  ← @FeignClient (cliente ViaCEP declarativo)
│
├── model/
│   └── Endereco.java                    ← Model de resposta da API ViaCEP
│
├── entity/
│   └── EnderecoEntity.java              ← Entidade JPA persistida no H2
│
├── repository/
│   └── EnderecoRepository.java          ← Spring Data JPA
│
├── service/
│   └── CepAppService.java               ← Lógica: chama Feign + salva no H2
│
├── controller/
│   ├── CepRestController.java           ← API REST (JSON)
│   └── CepWebController.java            ← Página HTML (Thymeleaf)
│
└── config/
    └── SwaggerConfig.java               ← OpenAPI 3 / Swagger UI
```

### Fluxo de dados

```
Usuário (HTML ou REST)
    ↓
CepRestController / CepWebController
    ↓
CepAppService
    ├── CepService (Feign) ──► https://viacep.com.br/ws/{cep}/json/
    └── EnderecoRepository ──► H2 (em memória)
```

---

## ⚙️ Como Executar

```bash
# Pré-requisito: Java 21 + Maven

cd consumo-cep
mvn spring-boot:run
```

---

## 🌐 URLs Disponíveis

| URL | Descrição |
|-----|-----------|
| `http://localhost:8080/` | Página HTML de consulta de CEPs |
| `http://localhost:8080/cep/{cep}` | API REST — consulta CEP (JSON) |
| `http://localhost:8080/cep/historico` | API REST — histórico (JSON) |
| `http://localhost:8080/swagger-ui/index.html` | Swagger UI |
| `http://localhost:8080/h2-console` | Console do banco H2 |

### Console H2
- JDBC URL: `jdbc:h2:mem:cepdb`
- User: `sa` / Senha: *(vazia)*

---

## 🔌 Starters / Dependências

| Dependência | Finalidade |
|-------------|-----------|
| `spring-boot-starter-web` | API REST + MVC |
| `spring-boot-starter-thymeleaf` | Template HTML server-side |
| `spring-boot-starter-data-jpa` | JPA + Hibernate |
| `h2` | Banco em memória (sem instalação) |
| `spring-cloud-starter-openfeign` | Cliente declarativo para ViaCEP |
| `springdoc-openapi-starter-webmvc-ui` | Swagger UI |
| `spring-boot-starter-validation` | Validações |
| `spring-boot-devtools` | Reload automático em dev |

---

## 📡 Exemplos de Uso (curl)

```bash
# Consultar CEP
curl http://localhost:8080/cep/01523040

# Ver histórico
curl http://localhost:8080/cep/historico
```

---

## 📝 Sobre o OpenFeign

O **OpenFeign** é um cliente HTTP declarativo. Em vez de criar `RestTemplate` ou `HttpClient`, você declara uma interface Java com anotações:

```java
@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface CepService {
    @GetMapping("/{cep}/json/")
    Endereco buscarEnderecoPorCep(@PathVariable("cep") String cep);
}
```

O Spring gera a implementação automaticamente. Habilitado com `@EnableFeignClients` na classe principal.
