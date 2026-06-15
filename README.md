# 📌 Trabalha Paulista - Backend

<img src="banner%20trabalha%20paulista.png" alt="Banner Trabalha Paulista" width="700">
## 📖 Descrição

O **Trabalha Paulista** é uma API backend desenvolvida em Java com o objetivo de promover a inclusão produtiva e fortalecer o empreendedorismo local no município de Paulista.

A plataforma integra oportunidades de emprego, capacitações e apoio a microempreendedores, permitindo que usuários encontrem vagas, cursos e divulguem seus serviços.

---

## 🎯 Objetivo

Facilitar o acesso da população a:

- Oportunidades de emprego, estágio e jovem aprendiz  
- Cursos gratuitos e capacitações  
- Divulgação de serviços de microempreendedores (MEIs)  
- Mentorias para abertura e formalização de negócios  
- Parcerias com empresas locais  

---

## 🏗️ Arquitetura do Projeto

O projeto segue o padrão de **arquitetura em camadas**, organizado da seguinte forma:

Controller → Service → Repository → Database

- **Controller**: Recebe as requisições HTTP  
- **Service**: Contém as regras de negócio  
- **Repository**: Responsável pelo acesso ao banco de dados  
- **Database**: Armazenamento das informações  

---

## ⚙️ Tecnologias Utilizadas

- Java  
- Spring Boot  
- JPA / Hibernate  
- Banco de Dados (MySQL na nuvem da Aiven)
- Postman (testes de API)  
- GitHub (versionamento)  

---

## 🗄️ Modelo de Dados (Baseado no DER)

O sistema foi modelado com base em um DER (Diagrama Entidade-Relacionamento), garantindo consistência entre banco de dados e regras de negócio.

### Entidades principais:

- Usuario  
- Vaga  
- Curso  
- Servico  
- Mentoria  
- Parceria  
- InscricaoVaga  
- InscricaoCurso  
- InscricaoMentoria  

---

## 🚀 Funcionalidades

### 👤 Usuários
- Cadastro de usuário  
- Login/autenticação  

### 💼 Vagas
- Cadastro de vagas  
- Listagem de vagas  
- Atualização e remoção  

### 🎓 Cursos
- Cadastro e divulgação de cursos  
- Listagem de cursos disponíveis  

### 🏪 Serviços (Empreendedores)
- Cadastro de serviços e produtos  
- Divulgação local  

### 🤝 Mentoria
- Solicitação de mentoria  
- Apoio ao empreendedor iniciante  

### 🤝 Parcerias
- Cadastro de parcerias com empresas locais  
- Listagem de parcerias disponíveis  

### 🔍 Busca
- Filtro de vagas, cursos e serviços  

---

## 📡 Endpoints (Exemplo)

POST   /usuarios POST   /auth/login
GET    /vagas POST   /vagas PUT    /vagas/{id} DELETE /vagas/{id}
GET    /cursos POST   /cursos
GET    /servicos POST   /servicos
POST   /mentorias
GET    /parcerias POST   /parcerias

---

## 🧪 Testes

Os testes da API são realizados utilizando o Postman, validando:

- Status HTTP  
- Respostas da API  
- Funcionamento dos endpoints  

---

## 📊 Metodologia Ágil

O projeto é gerenciado utilizando **Kanban Scrum Ágil**, com organização de tarefas no Trello.

### c

- Backlog  
- Sprint  
- Em andamento  
- Em teste  
- Concluído  

---

## 👥 Equipe

Projeto desenvolvido por uma equipe de 7 integrantes:

- Líder: Fernando (Modelo Conceitual do banco de Dados - DER)
- Integrantes:
- André Luiz(Desenvolvimento)
-  Camila Vitória (Vídeo Pitch)
-  Ênio Enrique(Desenvolvimenti)
-  Aquiles Magalhães(Vídeo Pitch)
-  Rian Honorato (Apresentação do projeto - Slides)
- Emerson Marcos (Apresentação do projeto - Slides)

---

## 🛠️ Como Executar o Projeto

1. Clonar o repositório:
git clone https://github.com/seu-usuario/trabalha-paulista.git

2. Acessar o projeto:
cd trabalha-paulista

3. Executar a aplicação:
./mvnw spring-boot:run

4. Acessar a API:
http://localhost:8080/swagger-ui/index.html#/

---

## Autenticacao e Seguranca com JWT

A API possui autenticacao com JWT. Quando a protecao esta ligada, algumas rotas exigem o envio do token no cabecalho da requisicao.

### Tomada da protecao

A protecao das rotas pode ser ativada ou desativada pela propriedade:

```properties
security.jwt.protection-enabled=true
```

Tambem e possivel controlar pelo arquivo `.env`:

```env
JWT_PROTECTION_ENABLED=true
```

- `true`: exige token nas rotas privadas.
- `false`: libera todas as rotas, util para desenvolvimento ou demonstracao.

### Cadastrar usuario

Rota publica:

```http
POST /auth/register
```

Exemplo de corpo da requisicao:

```json
{
  "nome": "Maria Silva",
  "email": "maria@email.com",
  "senha": "123456",
  "telefone": "81999999999",
  "tipoUsuario": "candidato"
}
```

O campo `tipoUsuario` aceita `candidato` ou `empreendedor`.

### Fazer login

Rota publica:

```http
POST /auth/login
```

Exemplo de corpo da requisicao:

```json
{
  "email": "maria@email.com",
  "senha": "123456"
}
```

Exemplo de resposta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "tipo": "Bearer",
  "usuario": {
    "id": 1,
    "nome": "Maria Silva",
    "email": "maria@email.com",
    "telefone": "81999999999",
    "tipoUsuario": "candidato"
  }
}
```

### Usar o token JWT

Nas rotas privadas, envie o token no cabecalho:

```http
Authorization: Bearer TOKEN_AQUI
```

### Rotas publicas

- `POST /auth/register`
- `POST /auth/login`
- `GET /status`
- `GET /vagas`
- `GET /cursos`
- `GET /servicos`
- `GET /mentorias`
- `GET /parcerias`
- Rotas do Swagger: `/swagger-ui/**` e `/v3/api-docs/**`

### Rotas privadas

Exigem token quando `security.jwt.protection-enabled=true`:

- `GET /auth/usuarios`
- `GET /auth/usuarios/{id}`
- `POST /auth/usuarios`
- `PUT /auth/usuarios/{id}`
- `DELETE /auth/usuarios/{id}`
- `POST /vagas`
- `PUT /vagas/{id}`
- `DELETE /vagas/{id}`
- `POST /cursos`
- `PUT /cursos/{id}`
- `DELETE /cursos/{id}`
- `POST /servicos`
- `PUT /servicos/{id}`
- `DELETE /servicos/{id}`
- `POST /mentorias`
- `PUT /mentorias/{id}`
- `DELETE /mentorias/{id}`
- `POST /parcerias`
- `PUT /parcerias/{id}`
- `DELETE /parcerias/{id}`
- Todas as rotas de inscricoes: `/inscricoes-vagas/**`, `/inscricoes-cursos/**` e `/inscricoes-mentorias/**`

---

## 📄 Licença

Este projeto é acadêmico e desenvolvido para fins educacionais.

---

## 💡 Observações

Este sistema tem como foco o backend, sendo possível integração futura com aplicações frontend (web ou mobile).


