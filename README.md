# Consumo CEP — Spring Boot + OpenFeign + H2 + Swagger

Projeto Spring Boot que consome a API pública **ViaCEP** via **OpenFeign**, persiste os endereços no banco **H2** em memória e exibe os dados em uma **página HTML** com Thymeleaf.

---

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

