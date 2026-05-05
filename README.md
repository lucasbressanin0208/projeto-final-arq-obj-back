<div align="center">

<img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white"/>
<img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
<img src="https://img.shields.io/badge/Lombok-pink?style=for-the-badge&logoColor=white"/>

<br/><br/>

```
                                              ██████╗ ███████╗██████╗ ██╗   ██╗██╗     ██╗ █████╗
                                              ██╔════╝██╔════╝██╔══██╗██║   ██║██║     ██║██╔══██╗
                                              ███████╗█████╗  ██████╔╝██║   ██║██║     ██║███████║
                                              ╚════██║██╔══╝  ██╔══██╗╚██╗ ██╔╝██║██   ██║██╔══██║
                                              ███████║███████╗██║  ██║ ╚████╔╝ ██║╚█████╔╝██║  ██║
                                              ╚══════╝╚══════╝╚═╝  ╚═╝  ╚═══╝  ╚═╝ ╚════╝ ╚═╝  ╚═╝
```

### 🔧 Plataforma de conexão entre clientes e prestadores de serviços locais

*Projeto Final — Arquitetura de Objetos*

<br/>

> *"Acabei de chegar em São Paulo e preciso cortar o cabelo. Entro na plataforma,*
> *busco cabeleireiros em SP, comparo preço e nota, escolho um horário e faço o agendamento."*

<br/>

</div>

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Stack Tecnológica](#-stack-tecnológica)
- [Arquitetura](#-arquitetura)
- [Estrutura de Pastas](#-estrutura-de-pastas)
- [As 10 Entidades do Domínio](#-as-10-entidades-do-domínio)
- [Diagrama de Relacionamentos](#-diagrama-de-relacionamentos)
- [Enums e Fluxo de Status](#-enums-e-fluxo-de-status)
- [Endpoints da API](#-endpoints-da-api)
- [Regras de Negócio](#-regras-de-negócio)
- [Constraints do Banco](#-constraints-do-banco)
- [Como Executar](#-como-executar)
- [Equipe](#-equipe)

---

## 💡 Sobre o Projeto

O **ServiJá** é uma plataforma estilo iFood, voltada para **serviços locais**. Conecta clientes que precisam de profissionais como cabeleireiros, manicures, marceneiros, eletricistas e encanadores, com prestadores autônomos que buscam visibilidade e organização de agenda.

### O problema que resolvemos

| 👤 Para o Cliente | 🔧 Para o Prestador |
|---|---|
| Dificuldade em encontrar serviços confiáveis em cidades novas ou bairros desconhecidos | Falta de um canal simples para divulgar serviços e organizar a agenda |
| Sem forma de comparar preços e avaliações em um só lugar | Sem controle centralizado de agendamentos recebidos |

### ✅ Escopo do MVP

| ✅ Incluído no MVP | ❌ Fora do MVP |
|---|---|
| Cadastro de cliente e prestador | Chat em tempo real |
| Cadastro de categorias e serviços | Pagamento real com gateway externo |
| Busca por cidade, bairro e categoria | Geolocalização com mapa avançado |
| Agendamento com status | Notificações push |
| Disponibilidade do prestador | Upload de imagens/portfólio |
| Avaliação após serviço concluído | Cupons e promoções |
| Favoritar prestadores | Assinatura premium para prestadores |

---

## 🛠 Stack Tecnológica

| Camada | Tecnologia | Versão |
|---|---|---|
| 🟠 Linguagem | Java | 17 |
| 🟢 Framework | Spring Boot | 3.x |
| 🐘 Banco de Dados | PostgreSQL | 15+ |
| 🔗 ORM | Spring Data JPA + Hibernate | — |
| 📦 Build | Maven | 3.x |
| ⚡ Utilitários | Lombok | — |
| 🌐 API | REST | — |

---

## 🏗 Arquitetura

O projeto segue uma **arquitetura em camadas** clara, com responsabilidades bem definidas:

```
┌─────────────────────────────────────────────────────────┐
│                     HTTP Request                        │
└─────────────────────────┬───────────────────────────────┘
                          ▼
┌─────────────────────────────────────────────────────────┐
│                    CONTROLLER                           │
│   Recebe requisições HTTP · Valida entrada via DTO      │
│              Retorna ResponseEntity                     │
└─────────────────────────┬───────────────────────────────┘
                          ▼
┌─────────────────────────────────────────────────────────┐
│                     SERVICE                             │
│     Regras de negócio · Validações de domínio           │
│           Orquestra operações entre entidades           │
└─────────────────────────┬───────────────────────────────┘
                          ▼
┌─────────────────────────────────────────────────────────┐
│                   REPOSITORY                            │
│        Acesso ao banco via Spring Data JPA              │
│              Queries customizadas                       │
└─────────────────────────┬───────────────────────────────┘
                          ▼
┌─────────────────────────────────────────────────────────┐
│                 MODEL / ENTITY                          │
│     Representação das tabelas do PostgreSQL             │
│          Métodos de domínio encapsulados                │
└─────────────────────────────────────────────────────────┘
```

> ⚠️ **Princípio fundamental:** Nenhuma regra de negócio fica no Controller.
> Todo o domínio vive nos Services e nos métodos das entidades.

---

## 📁 Estrutura de Pastas

```
src/
└── main/
    └── java/
        └── br/niaga/servija/
            │
            ├── 📂 controller/
            │   ├── AgendamentoController.java
            │   ├── AvaliacaoController.java
            │   ├── CategoriaServicoController.java
            │   ├── ClienteController.java
            │   ├── FavoritoController.java
            │   ├── PrestadorController.java
            │   └── ServicoController.java
            │
            ├── 📂 dto/
            │   ├── request/
            │   │   ├── CriarAgendamentoRequest.java
            │   │   ├── AvaliacaoRequest.java
            │   │   ├── DisponibilidadeRequest.java
            │   │   ├── CategoriaServicoDTO.java
            │   │   └── ServicoDTO.java
            │   └── response/
            │       ├── PrestadorResumoResponse.java
            │       └── ServicoResponse.java
            │
            ├── 📂 models/
            │   ├── Agendamento.java
            │   ├── Avaliacao.java
            │   ├── CategoriaServico.java
            │   ├── Cliente.java
            │   ├── Disponibilidade.java
            │   ├── Endereco.java
            │   ├── Favorito.java
            │   ├── Pagamento.java
            │   ├── Prestador.java
            │   └── Servico.java
            │
            ├── 📂 repository/
            │   ├── AgendamentoRepository.java
            │   ├── AvaliacaoRepository.java
            │   ├── CategoriaServicoRepository.java
            │   ├── ClienteRepository.java
            │   ├── DisponibilidadeRepository.java
            │   ├── FavoritoRepository.java
            │   ├── PagamentoRepository.java
            │   ├── PrestadorRepository.java
            │   └── ServicoRepository.java
            │
            ├── 📂 service/
            │   ├── AgendamentoService.java
            │   ├── AvaliacaoService.java
            │   ├── CategoriaServicoService.java
            │   ├── ClienteService.java
            │   ├── FavoritoService.java
            │   ├── PagamentoService.java
            │   ├── PrestadorService.java
            │   └── ServicoService.java
            │
            └── ServijaApplication.java
```

---

## 🗂 As 10 Entidades do Domínio

### 1. 👤 Cliente
> Pessoa que busca, agenda e avalia serviços.

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `UUID` | Identificador único |
| `nome` | `String` | Nome completo |
| `email` | `String` | Login e contato — **único** |
| `senha` | `String` | Senha criptografada |
| `telefone` | `String` | Contato do cliente |
| `cpf` | `String` | Documento — opcional no MVP |
| `enderecos` | `List<Endereco>` | Endereços cadastrados |

**Métodos de domínio:** `adicionarEndereco()` · `favoritarPrestador()` · `podeAvaliar()`

---

### 2. 🔧 Prestador
> Profissional que oferece serviços na plataforma.

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `UUID` | Identificador único |
| `nome` | `String` | Nome do profissional ou empresa |
| `email` | `String` | Login e contato — **único** |
| `senha` | `String` | Senha criptografada |
| `telefone` | `String` | Contato do prestador |
| `descricao` | `String` | Texto de apresentação |
| `notaMedia` | `Double` | Média das avaliações recebidas |
| `ativo` | `Boolean` | Aparece nas buscas apenas se ativo |
| `endereco` | `Endereco` | Local de atendimento principal |

**Métodos de domínio:** `recalcularNotaMedia()` · `estaDisponivelEm()` · `aceitarAgendamento()`

---

### 3. 📍 Endereco
> Informações de localização usadas em perfil e filtros de busca.

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `UUID` | Identificador único |
| `rua` | `String` | Logradouro |
| `numero` | `String` | Número |
| `bairro` | `String` | Bairro |
| `cidade` | `String` | Cidade — **obrigatório** |
| `estado` | `String` | UF — **obrigatório** |
| `cep` | `String` | CEP — opcional no MVP |
| `complemento` | `String` | Complemento |

**Métodos de domínio:** `pertenceACidade()` · `resumoLocalizacao()`

---

### 4. 🏷️ CategoriaServico
> Classifica os tipos de serviço oferecidos na plataforma.

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `UUID` | Identificador único |
| `nome` | `String` | Nome da categoria — **único** |
| `descricao` | `String` | Descrição da categoria |
| `ativa` | `Boolean` | Se está disponível |

**Métodos de domínio:** `ativar()` · `desativar()`

---

### 5. 🛎️ Servico
> Oferta específica cadastrada por um prestador.

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `UUID` | Identificador único |
| `nome` | `String` | Ex: Corte masculino |
| `descricao` | `String` | Detalhes do serviço |
| `preco` | `Double` | Valor cobrado — **deve ser > 0** |
| `duracaoMinutos` | `Integer` | Tempo estimado — **deve ser > 0** |
| `categoria` | `CategoriaServico` | Categoria do serviço |
| `prestador` | `Prestador` | Quem oferece |
| `ativo` | `Boolean` | Se está disponível para agendamento |

**Métodos de domínio:** `ativar()` · `desativar()` · `calcularHorarioFim()`

---

### 6. 📅 Disponibilidade
> Horários em que o prestador atende.

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `UUID` | Identificador único |
| `prestador` | `Prestador` | Dono da disponibilidade |
| `diaSemana` | `DayOfWeek` | Dia da semana |
| `horaInicio` | `LocalTime` | Início do atendimento |
| `horaFim` | `LocalTime` | Fim do atendimento |
| `ativa` | `Boolean` | Se o horário está em uso |

**Métodos de domínio:** `contemHorario()` · `conflitaCom()`

---

### 7. 📌 Agendamento *(entidade central)*
> Liga cliente, prestador, serviço, data/hora e status. É o coração do sistema.

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `UUID` | Identificador único |
| `cliente` | `Cliente` | Quem contratou |
| `prestador` | `Prestador` | Quem realizará o serviço |
| `servico` | `Servico` | Serviço escolhido |
| `dataHoraInicio` | `LocalDateTime` | Início do serviço |
| `dataHoraFim` | `LocalDateTime` | Fim calculado pela duração |
| `status` | `StatusAgendamento` | Estado atual |
| `observacaoCliente` | `String` | Observação opcional |

**Métodos de domínio:** `confirmar()` · `recusar()` · `cancelar()` · `concluir()` · `estaConcluido()`

---

### 8. 💳 Pagamento
> Representa o pagamento simulado de um agendamento.

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `UUID` | Identificador único |
| `agendamento` | `Agendamento` | Agendamento pago |
| `valor` | `Double` | Valor no momento da contratação |
| `metodo` | `MetodoPagamento` | PIX, Cartão ou Dinheiro |
| `status` | `StatusPagamento` | Estado do pagamento |
| `dataPagamento` | `LocalDateTime` | Quando foi pago |

**Métodos de domínio:** `marcarComoPago()` · `cancelar()` · `estaPago()`

---

### 9. ⭐ Avaliacao
> Nota e comentário dados pelo cliente após o serviço concluído.

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `UUID` | Identificador único |
| `cliente` | `Cliente` | Quem avaliou |
| `prestador` | `Prestador` | Quem foi avaliado |
| `agendamento` | `Agendamento` | Serviço realizado |
| `nota` | `Integer` | Nota de 1 a 5 |
| `comentario` | `String` | Comentário opcional |
| `dataCriacao` | `LocalDateTime` | Data da avaliação |

**Métodos de domínio:** `validarNota()` · `foiFeitaPeloClienteDoAgendamento()`

---

### 10. ❤️ Favorito
> Prestador salvo pelo cliente para acesso rápido.

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `UUID` | Identificador único |
| `cliente` | `Cliente` | Quem favoritou |
| `prestador` | `Prestador` | Prestador salvo |
| `dataCriacao` | `LocalDateTime` | Quando foi favoritado |

**Métodos de domínio:** `pertenceAoCliente()` · `referenciaPrestador()`

---

## 🔗 Diagrama de Relacionamentos

```
                    ┌──────────────────┐
                    │   CategoriaServico│
                    └────────┬─────────┘
                             │ 1:N
                             ▼
┌──────────┐  1:N   ┌──────────────┐  N:1  ┌────────────┐
│  Cliente │───────▶│    Servico   │◀──────│  Prestador │
└────┬─────┘        └──────┬───────┘       └─────┬──────┘
     │                     │ 1:N                 │
     │ 1:N                 ▼                     │ 1:N
     │            ┌─────────────────┐            │
     └───────────▶│   Agendamento   │◀───────────┘
     │  1:N        └────┬──────┬────┘
     │                  │      │
     │               1:1│      │1:1
     │                  ▼      ▼
     │           ┌──────────┐ ┌──────────┐
     │           │ Pagamento│ │ Avaliacao│
     │           └──────────┘ └──────────┘
     │
     │ 1:N          1:N
     ├──────────▶ Endereco
     ├──────────▶ Avaliacao
     └──────────▶ Favorito ◀──── Prestador
```

### Cardinalidades JPA

| Relação | Cardinalidade | Implementação |
|---|---|---|
| Cliente → Endereco | `1:N` | `@OneToMany` / `@ManyToOne` em Endereco |
| Prestador → Endereco | `1:1` | `@OneToOne` |
| Prestador → Servico | `1:N` | `@ManyToOne` em Servico |
| CategoriaServico → Servico | `1:N` | `@ManyToOne` em Servico |
| Prestador → Disponibilidade | `1:N` | `@ManyToOne` em Disponibilidade |
| Cliente → Agendamento | `1:N` | `@ManyToOne` em Agendamento |
| Prestador → Agendamento | `1:N` | `@ManyToOne` em Agendamento |
| Servico → Agendamento | `1:N` | `@ManyToOne` em Agendamento |
| Agendamento → Pagamento | `1:1` | `@OneToOne` em Pagamento |
| Agendamento → Avaliacao | `1:1` | `@OneToOne` em Avaliacao |
| Cliente ↔ Prestador (Favorito) | `N:N` resolvido por entidade | `@ManyToOne` em Favorito para ambos |

---

## 🔄 Enums e Fluxo de Status

### Fluxo do Agendamento

```
                    ┌─────────────┐
                    │   PENDENTE  │
                    └──────┬──────┘
                           │
              ┌────────────┴────────────┐
              ▼                         ▼
       ┌─────────────┐           ┌─────────────┐
       │ CONFIRMADO  │           │  RECUSADO   │
       └──────┬──────┘           └─────────────┘
              │
     ┌────────┴────────┐
     ▼                 ▼
┌──────────┐    ┌─────────────┐
│CONCLUIDO │    │  CANCELADO  │
└──────────┘    └─────────────┘
```

### Todos os Enums

| Enum | Valores |
|---|---|
| `StatusAgendamento` | `PENDENTE` · `CONFIRMADO` · `RECUSADO` · `CANCELADO` · `CONCLUIDO` |
| `StatusPagamento` | `PENDENTE` · `PAGO` · `CANCELADO` · `REEMBOLSADO` |
| `MetodoPagamento` | `PIX` · `CARTAO` · `DINHEIRO` |
| `DayOfWeek` | `java.time.DayOfWeek` nativo do Java |

---

## 🌐 Endpoints da API

### 👤 Clientes
| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/clientes` | Cadastrar cliente |
| `GET` | `/clientes/{id}` | Buscar cliente por ID |
| `PUT` | `/clientes/{id}` | Atualizar dados do cliente |

### 🔧 Prestadores
| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/prestadores` | Cadastrar prestador |
| `GET` | `/prestadores` | Buscar prestadores por cidade e categoria |
| `GET` | `/prestadores/{id}` | Detalhar prestador |
| `PUT` | `/prestadores/{id}` | Atualizar prestador |
| `POST` | `/prestadores/{id}/disponibilidades` | Cadastrar disponibilidade |

> 💡 Exemplo de busca: `GET /prestadores?cidade=São Paulo&categoria=Cabeleireiro`

### 🏷️ Categorias de Serviço
| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/categorias` | Criar nova categoria |
| `GET` | `/categorias` | Listar todas as categorias |
| `GET` | `/categorias/ativas` | Listar categorias ativas |
| `GET` | `/categorias/{id}` | Buscar categoria por ID |
| `PUT` | `/categorias/{id}` | Atualizar categoria |
| `PATCH` | `/categorias/{id}/ativar` | Ativar categoria |
| `PATCH` | `/categorias/{id}/desativar` | Desativar categoria |

### 🛎️ Serviços
| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/servicos` | Criar novo serviço |
| `GET` | `/servicos/{id}` | Buscar serviço por ID |
| `GET` | `/servicos/prestador/{prestadorId}` | Listar serviços de um prestador |
| `GET` | `/servicos/prestador/{prestadorId}/ativos` | Listar serviços ativos de um prestador |
| `GET` | `/servicos/categoria/{categoriaId}` | Listar serviços por categoria |
| `PUT` | `/servicos/{id}` | Atualizar serviço |
| `PATCH` | `/servicos/{id}/ativar` | Ativar serviço |
| `PATCH` | `/servicos/{id}/desativar` | Desativar serviço |

### 📌 Agendamentos
| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/agendamentos` | Criar agendamento |
| `GET` | `/agendamentos/{id}` | Buscar agendamento por ID |
| `GET` | `/agendamentos/cliente/{clienteId}` | Agendamentos de um cliente |
| `GET` | `/agendamentos/prestador/{prestadorId}` | Agendamentos de um prestador |
| `PATCH` | `/agendamentos/{id}/confirmar` | Prestador confirma agendamento |
| `PATCH` | `/agendamentos/{id}/recusar` | Prestador recusa agendamento |
| `PATCH` | `/agendamentos/{id}/concluir` | Concluir serviço realizado |
| `PATCH` | `/agendamentos/{id}/cancelar` | Cancelar agendamento |

### 💳 Pagamentos
| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/pagamentos/{id}` | Buscar pagamento |
| `PATCH` | `/pagamentos/{id}/pagar` | Marcar como pago |
| `PATCH` | `/pagamentos/{id}/cancelar` | Cancelar pagamento |

### ⭐ Avaliações
| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/avaliacoes` | Criar avaliação |
| `GET` | `/avaliacoes/prestador/{prestadorId}` | Listar avaliações de um prestador |

### ❤️ Favoritos
| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/favoritos` | Favoritar prestador |
| `GET` | `/favoritos/cliente/{clienteId}` | Listar favoritos do cliente |
| `DELETE` | `/favoritos/{id}` | Remover favorito |

---

## 📏 Regras de Negócio

| # | Regra | Descrição | Camada |
|---|---|---|---|
| 1 | 🔍 **Busca local** | Cliente busca prestadores por cidade, bairro e categoria | `PrestadorService` |
| 2 | ✅ **Serviço ativo** | Apenas serviços ativos podem ser agendados | `ServicoService` |
| 3 | 🏷️ **Categoria ativa** | Não permite criar serviço em categoria inativa | `CategoriaServicoService` |
| 4 | 📅 **Disponibilidade** | Agendamento deve estar dentro do horário disponível do prestador | `AgendamentoService` |
| 5 | 🚫 **Sem conflito** | Prestador não pode ter dois agendamentos confirmados no mesmo horário | `AgendamentoService` |
| 6 | ⭐ **Avaliação pós-serviço** | Avaliação só pode ser criada após status `CONCLUIDO` | `AvaliacaoService` |
| 7 | 📊 **Nota média** | Recalcular nota do prestador a cada nova avaliação criada | `PrestadorService` |
| 8 | ❤️ **Favorito único** | Mesmo cliente não pode favoritar o mesmo prestador duas vezes | `FavoritoService` |
| 9 | 💳 **Pagamento simulado** | Pagamento criado com valor do serviço, sem gateway externo | `PagamentoService` |
| 10 | 🔒 **Email único** | Email deve ser único para clientes e prestadores | `ClienteService` / `PrestadorService` |
| 11 | ⏰ **Sem passado** | Não é possível criar agendamento para data/hora no passado | `AgendamentoService` |
| 12 | 💰 **Preço válido** | Preço do serviço deve ser maior que zero | `ServicoService` |

---

## 🔒 Constraints do Banco

```sql
-- Evita cadastro duplicado
UNIQUE (email) ON clientes;
UNIQUE (email) ON prestadores;

-- Evita categoria duplicada
UNIQUE (nome) ON categorias_servico;

-- Evita favorito duplicado
UNIQUE (cliente_id, prestador_id) ON favoritos;

-- Evita duas avaliações para o mesmo serviço
UNIQUE (agendamento_id) ON avaliacoes;

-- Evita dois pagamentos para o mesmo agendamento
UNIQUE (agendamento_id) ON pagamentos;

-- Garante valores válidos
CHECK (preco > 0) ON servicos;
CHECK (duracao_minutos > 0) ON servicos;
CHECK (nota BETWEEN 1 AND 5) ON avaliacoes;
```

---

## 🚀 Como Executar

### Pré-requisitos

- ☕ Java 17+
- 🐘 PostgreSQL 15+
- 📦 Maven 3+

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/servija-backend.git
cd servija-backend
```

### 2. Crie o banco de dados

```sql
CREATE DATABASE servija;
```

### 3. Configure o `application.properties`

```properties
# Banco de dados
spring.datasource.url=jdbc:postgresql://localhost:5432/servija
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# Servidor
server.port=8080
```

### 4. Execute o projeto

```bash
./mvnw spring-boot:run
```

✅ API disponível em: `http://localhost:8080`

---

## 👥 Equipe

| Integrante | Responsabilidade |
|---|---|
| **Integrante 1** | 🗂️ Models, repositories e migrations — `Cliente`, `Prestador`, `Endereco`, `Favorito` |
| **Integrante 2** | ⚙️ Services e regras de negócio — `AgendamentoService`, `DisponibilidadeService`, `PagamentoService` |
| **Integrante 3** | 🌐 Controllers, DTOs e testes — `AgendamentoController`, `AvaliacaoController`, `ClienteController`, `PrestadorController` |
| **Integrante 4** | 🏷️ Models, repositories, services e controllers — `CategoriaServico`, `Servico` |
| **Integrante 5** | 🖥️ Front-end prestador — serviços, disponibilidade e gerenciamento de agendamentos |

---

<div align="center">

**ServiJá** — Projeto Final de Arquitetura de Objetos

*Feito com ☕ Java · 🍃 Spring Boot · 🐘 PostgreSQL*

</div>
