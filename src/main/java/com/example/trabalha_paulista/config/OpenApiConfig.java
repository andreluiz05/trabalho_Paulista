package com.example.trabalha_paulista.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        private static final String SECURITY_SCHEME_NAME = "bearerAuth";

        @Bean
        public OpenAPI customOpenAPI() {
                String descricaoCustomizada = """
                                <img src="/imagens/Logo_TP.png" width="800" alt="Logo Trabalha Paulista" />

                                # 🏢 TRABALHA PAULISTA | Painel Interativo da API

                                🌟 O Trabalha Paulista e o motor para conectar talentos, promover cursos,
                                gerenciar mentorias, divulgar vagas e impulsionar a empregabilidade em Paulista - PE.

                                🌐 Esta API serve como o back-end central do ecossistema. Ela pode alimentar
                                plataformas web, aplicativos mobile e paineis administrativos atraves destes endpoints.

                                ## 🔐 Autenticacao
                                1. 👤 Crie uma conta em `POST /auth/register` ou faca login em `POST /auth/login`.
                                2. 📋 Copie o token retornado.
                                3. 🔑 No Swagger, clique em `Authorize`.
                                4. 🪪 Cole somente o token, sem escrever `Bearer`.

                                ✅ Nesta versao local, os endpoints ficam liberados para facilitar os testes no Swagger.
                                O JWT continua disponivel em `/auth/register` e `/auth/login` para demonstrar autenticacao.

                                ## 🧭 Guia pratico para testar
                                Este painel organiza os recursos em blocos, como `usuario-controller`,
                                `vaga-controller`, `curso-controller`, `mentoria-controller` e `parceria-controller`.

                                1. 📂 Abra o bloco do recurso desejado.
                                2. 🎯 Clique na acao desejada, por exemplo `POST /cursos`.
                                3. 🧪 Clique em `Try it out`.
                                4. 📝 Preencha o JSON de exemplo com dados reais.
                                5. 🚀 Clique em `Execute`.

                                ## 🔄 Fluxo de uso
                                - 🟢 `POST`: cria um novo registro no banco.
                                - 🔵 `GET`: lista ou consulta registros existentes.
                                - 🟠 `PUT`: atualiza um registro existente.
                                - 🔴 `DELETE`: remove um registro do banco.

                                ## 📊 Como analisar as respostas
                                - ✅ `200` ou `201`: operacao concluida com sucesso.
                                - ⚠️ `400`: dados invalidos ou corpo da requisicao mal formado.
                                - 🔒 `401`: token ausente, invalido ou expirado.
                                - 🔎 `404`: recurso nao encontrado.
                                - ⚔️ `409`: conflito de integridade, como email duplicado.
                                - 💥 `500`: erro interno do servidor.

                                ## ⚠️ Regra de integridade importante
                                Para publicar uma vaga com publicador, primeiro crie um usuario e use o ID
                                retornado no campo `publicadorId`.

                                ## 🛡️ Observacoes sobre seguranca
                                As respostas de usuario nao expõem a senha. A senha e salva com BCrypt
                                e a autenticacao usa JWT no cabecalho `Authorization`.

                                ☕ Desenvolvido com Java 21, Spring Boot, Springdoc OpenAPI, JWT e MySQL.
                                """;

                return new OpenAPI()
                                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                                .components(new Components()
                                                .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                                                                .name(SECURITY_SCHEME_NAME)
                                                                .type(SecurityScheme.Type.HTTP)
                                                                .scheme("bearer")
                                                                .bearerFormat("JWT")))
                                .info(new Info()
                                                .title("🏢 TRABALHA PAULISTA | Central de Controle da API")
                                                .version("1.0.0")
                                                .description(descricaoCustomizada)
                                                .contact(new Contact()
                                                                .name("Equipe de Desenvolvimento Paulista")
                                                                .email("dev@trabalhapaulista.pe.gov.br")));
        }
}
