# 📌 Trabalha Paulista - Backend

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
- Banco de Dados (MySQL ou H2)  
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

O projeto é gerenciado utilizando **Scrum**, com organização de tarefas no Trello.

### Fluxo de trabalho:

- Backlog  
- Sprint  
- Em andamento  
- Em teste  
- Concluído  

---

## 👥 Equipe

Projeto desenvolvido por uma equipe de 6 integrantes:

- Líder: *(Seu nome aqui)*  
- Desenvolvedores: *(nomes da equipe)*  

---

## 🛠️ Como Executar o Projeto

1. Clonar o repositório:
git clone https://github.com/seu-usuario/trabalha-paulista.git

2. Acessar o projeto:
cd trabalha-paulista

3. Executar a aplicação:
./mvnw spring-boot:run

4. Acessar a API:
http://localhost:8080

---

## 📄 Licença

Este projeto é acadêmico e desenvolvido para fins educacionais.

---

## 💡 Observações

Este sistema tem como foco o backend, sendo possível integração futura com aplicações frontend (web ou mobile).


