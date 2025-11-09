# Gerenciador de Tarefas - Spring Boot

Aplicação web simples para gerenciar tarefas, construída com Spring Boot e H2 Database (em memória). Permite criar, listar, atualizar e excluir tarefas.

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Maven
- Git/GitHub
- Render (deploy gratuito na nuvem)

## Funcionalidades

- Criar nova tarefa
- Listar tarefas
- Atualizar status da tarefa
- Excluir tarefas

## Pré-requisitos

- Java 17 ou superior
- Maven
- Git

## Configuração local

1. Clone o repositório:
```bash
git clone https://github.com/alicezorzan/gerenciador-tarefas.git
cd gerenciador-tarefas

Comando para compilar:
./mvnw clean install
./mvnw spring-boot:run

http://localhost:8080
