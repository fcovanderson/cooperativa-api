## Sicredi Cooperativa

### Descrição

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação.
Este projeto disponibiliza uma solução back-end para gerenciar essas sessões de votação.

### Configurações locais 

- **Java 17**

- **Spring boot 2.6.5**

- **Banco de dados Oracle SQL 21c**

### Build 

- Para executar a aplicação e necessário que exista um banco de dados Oracle configurado no host. 
- Necessário a configuração das seguintes variáveis de ambiente: **ORACLE_DB_HOST**, **ORACLE_DB_PORT**, **ORACLE_DB_SID**, **ORACLE_DB_USER**, **ORACLE_DB_USER_PASSWORD**.
- Caso seja necessário utilizar outro SGBD é necessário alterar as configurações especificadas no arquivo **resources/application.properties**

### Acesso à documentação Swagger UI (Após realizar o start da aplicação)
- http://localhost:8080/swagger-ui/index.html
