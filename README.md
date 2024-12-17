# Tech Challenge Reserva e Avaliação de Restaurantes

## Visão Geral

Solução criada para Reserva e Avaliação de Restaurantes. Este projeto está desenvolvido em Java 17 e segue a arquitetura Clean Architecture, utilizando Banco de Dados Relacional AWS RDS(PostgreSQL).

## Requisitos

Antes de iniciar, certifique-se de ter os seguintes requisitos atendidos:

- **Java 17**: O projeto utiliza o Java 17. Certifique-se de que sua IDE está configurada com o Java 17.
- **Maven**: Usado para gerenciar dependências e construir o projeto.

1. **Clone o Repositório:**

   Abra o terminal e clone o repositório usando o comando:

   ```bash
   git clone https://github.com/bpcavalcante/tech-challenge-reserva-restaurante.git

2. **Acesse a Branch main:**

   Depois de clonar o repositório, navegue até o diretório do projeto e altere para a branch main:

   ```bash
   git checkout main

3. **Importe o Projeto na IDE:**

- Abra sua IDE preferida (por exemplo, IntelliJ IDEA ou Eclipse).
- Certifique-se de que o **Java 17** está configurado como JDK.
- Importe o projeto como um projeto Maven existente.

4. **Construir o Projeto:**

   No terminal, dentro do diretório do projeto, execute o comando Maven para construir o projeto, ou em algumas IDE já constroem automaticamente:

   ```bash
   mvn clean install

5. **Executar o Projeto:**
   Depois de construir o projeto, você pode executá-lo diretamente na IDE ou via linha de comando:
   ```bash
   mvn spring-boot:run

O servidor será iniciado na porta **80**

6. **Subir o container Docker:**
   Você precisará subir o container com as configurações, que estão no arquivo docker-compose dentro do projeto:
   ```bash
   docker-compose up

O servidor PostgreSQL será iniciado na porta **5432**

7. **Testando o Sistema:**
   Use os comandos curl abaixo para testar as funcionalidades do sistema:
    - **Cadastrar Restaurante**
      ```bash
        curl --request POST \
        --url http://localhost:8080/restaurantes \
        --header 'Content-Type: application/json' \
        --header 'User-Agent: insomnia/10.2.0' \
        --data '{
        "nome": "Pizzaria Bella Napoli",
        "localizacao": "Avenida Paulista, 123 - São Paulo",
        "tipoCozinha": "Italiana",
        "capacidade": 50,
        "horarioAbertura": "2024-12-09T18:00:00",
        "horarioFechamento": "2024-12-09T23:00:00"
        }'
   - **Buscar Restaurantes**
     ```bash
        curl --request GET \
        --url 'http://localhost:8080/restaurantes?nome=&localizacao=&tipoCozinha=' \
        --header 'Content-Type: application/json'
   - **Cadastrar Avaliacao**
     ```bash
        curl --request POST \
        --url http://localhost:8080/avaliacoes \
        --header 'Content-Type: application/json' \
        --header 'User-Agent: insomnia/10.2.0' \
        --data '{
        "nota": 1,
        "comentario": "Pessima experiencia",
        "restauranteId": "1"
        }'
   - **Criar Reserva**
     ```bash
        curl --request POST \
        --url http://localhost:8080/reservas \
        --header 'Content-Type: application/json' \
        --header 'User-Agent: insomnia/10.2.0' \
        --data '{
        "nome": "Denis Silva",
        "email": "denis.silva@example.com",
        "quantidadePessoas": 4,
        "dataHoraReserva": "2024-12-21T11:30:00",
        "status": "CONFIRMADO",
        "restauranteId": 1
        }' 
   - **Visualizar Reserva**
     ```bash
        curl --request GET \
        --url http://localhost:8080/reservas/1 \
        --header 'User-Agent: insomnia/10.2.0'
   - **Atualizar Reserva**
     ```bash
        curl --request PUT \
        --url http://localhost:8080/reservas/4 \
        --header 'Content-Type: application/json' \
        --header 'User-Agent: insomnia/10.2.0' \
        --data '{
        "nome": "Cleyton Silva",
        "email": "cleyton.silva@example.com",
        "quantidadePessoas": 10,
        "dataHoraReserva": "2024-12-21T11:30:00",
        "status": "CANCELADA",
        "restauranteId": 1
        }'
8. **Verificando doc Swagger:**
   Acesse o link http://localhost:8080/swagger-ui/index.html com o projeto rodando
9. **Testando Deploy:**
   Para realizar o teste do deploy basta trocar http://localhost:8080 por https://tech-challenge-reserva-restaurante.onrender.com
   - Exemplo
     ```bash
        curl --request POST \
        --url https://tech-challenge-reserva-restaurante.onrender.com/reservas \
        --header 'Content-Type: application/json' \
        --header 'User-Agent: insomnia/10.2.0' \
        --data '{
        "nome": "Beto Silva",
        "email": "denis.silva@example.com",
        "quantidadePessoas": 4,
        "dataHoraReserva": "2024-12-21T23:30:00",
        "status": "CONFIRMADO",
        "restauranteId": 1
        }'
