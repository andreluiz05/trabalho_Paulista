package com.example.trabalha_paulista.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        String descricaoCustomizada = """
            <img src="/imagens/Logo_TP.png" width="800" alt="Logo Trabalha Paulista" />
            
            # 🏢 TRABALHA PAULISTA | Painel Interativo da API
            
            O **Trabalha Paulista** é o motor definitivo para conectar talentos, promover cursos, gerenciar mentorias e impulsionar a empregabilidade em **Paulista - PE**. 🌟
            
            🌐 _**Nota de Arquitetura:** Esta API serve como o cérebro/back-end central do ecossistema. No futuro, ela alimentará diretamente plataformas web e aplicativos mobile através desses mesmos pontos de acesso (endpoints)._
            
            ---
            
            ## 🗺️ GUIA PRÁTICO: Como usar este Painel de Testes
            
            Este painel organiza os recursos em blocos sanfonados (ex: `usuario-controller`, `vaga-controller`). Clique neles para abrir as opções.
            
            ### 🎨 Significado das Cores (Ações do Banco)
            * 🟢 **POST (Verde):** Cria um registro novo (Envia dados para o banco).
            * 🔵 **GET (Azul):** Lista ou busca os dados que já estão guardados.
            * 🟠 **PUT (Laranja):** Atualiza/Edita as informações de um registro existente.
            * 🔴 **DELETE (Vermelho):** Apaga permanentemente um registro do banco.
            
            ### 👣 Passo a Passo para Executar qualquer Teste (Apenas 4 Cliques)
            1. **Clique 1:** Clique na linha da ação desejada para expandir o formulário (ex: 🟢 `POST /cursos`).
            2. **Clique 2:** No canto direito, clique no botão cinza **`Try it out`** (Testar) para liberar a edição.
            3. **Clique 3:** Na caixa de texto que abrir, altere o formato do texto (JSON) preenchendo os dados reais entre as aspas.
            4. **Clique 4:** Clique no botão azul grande **`Execute`** (Executar).
            
            ### 📊 Como analisar a Resposta (Responses)
            Logo abaixo do botão de executar, o sistema retornará o resultado:
            * **Código 200 ou 201 (Verde):** Sucesso absoluto! O banco processou e salvou as informações.
            * **Código 400 ou 500 (Vermelho):** Ocorreu um erro. Verifique se o formato dos dados está correto ou se alguma regra de validação foi quebrada.
            
            ---
            ⚠️ **REGRA DE INTEGRIDADE CRUCIAL:** Por conta das Chaves Estrangeiras (FK), lembre-se de **criar um usuário primeiro** na rota `POST /usuarios` e anotar o ID gerado antes de tentar publicar uma vaga ou realizar inscrições!
            
            _Desenvolvido com ☕, Java 21, Spring Boot, Springdoc OpenAPI e MySQL._
            """;

        return new OpenAPI()
                .info(new Info()
                        .title("🏢 TRABALHA PAULISTA | Central de Controle da API")
                        .version("1.0.0")
                        .description(descricaoCustomizada)
                        .contact(new Contact()
                                .name("Equipe de Desenvolvimento Paulista")
                                .email("dev@trabalhapaulista.pe.gov.br")));
    }
}