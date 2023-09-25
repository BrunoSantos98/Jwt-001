# jwt-001

Este é o back-end da aplicação myblog que estou construindo com a função de cadastrar novos usuarios, fazer controle de roles, efetuar login.

---

## Índice:
1. 🖥️ [Tecnologias utilizadas](#1--tecnologias-utilizadas--)
2. 🧑‍🔬 [Como testar](#2--como-testar--)
3. 🌐 [Endpoints](#3--endpoints-da-aplicação--)

---

## 1. 🖥️ Tecnologias utilizadas 🖥️:

- Java
- Maven
- Springboot
  - JPA
  - Security
  - Lombock
- Postgresql sendo a database
- Postman para testar endpoints

---

## 2. 🧑‍🔬 Como testar 🧑‍🔬:

1. Primeiro você deve instalar na sua maquina o Jre e Jdk do java, de preferência parra versões acima da 11.
2. Sua maquina deve ter o Poostgresql instalada. Caso você tenha outro banco SQL como preferencia, deverá atualizar o arquivo POM.XML com o driver e informações do banco de dados de sua preferência.
3. Lembrar de atualizar o arquivo do POM.XML com a senha que você definiu para seu banco local.
4. Para testar os endpoints você pode utilizar ferramentas como postman e insomnia, os endpoints serão listados abaixo.

---

## 3. 🌐 Endpoints da Aplicação 🌐:

**Observação: A aplicação por padrão irá rodar na porta 8080, caso você queira alterar a porta no arquivo application.properties coloque: server.port=[ numero da porta]**

1. Endpoint de cadastro localizado na url `localhoost:[porta]/api/v1/user/register`
    - Request: na request você envia os dados do usuario a ser cadastrado como no exemplo abaixo
  ```
  {
    "name":"Carlos Santos",
    "username":"Carlos002",
    "password":"senha1234",
    "email":"emaili@email.com"
  }
```

  - Response: se não tiver nenhum erro, a response vem com codigo 200 e um JSON com os dados do usuario similar ao da request.
<br/>

2. Endpoint de login localizado na url `localhoost:[porta]/api/v1/user/login`
    - Request: na request você envia o usuario e senha como no exemplo abaixo
  ```
{
  "username":"Carlos002",
  "password":"senha1234"
}
```   
  - Response: se não tiver nenhum erro, a response vem com codigo 200 e um JWT em forma se string através do body.
<br/>

3. Endpoint de busca de usuario por username localizado na url `localhoost:[porta]/api/v1/user/findUserByUsername`
    - Request: na request você envia o nome do usuario através do parametro `username`
    - Response: se não tiver nenhum erro, a response vem com codigo 200 e um JSON com os dados do usuario buscado através doo username.
<br/>

4. Endpoint de busca de usuario por email localizado na url `localhoost:[porta]/api/v1/user/findUserByEmail`
    - Request: na request você envia o nome do usuario através do parametro `email`
    - Response: se não tiver nenhum erro, a response vem com codigo 200 e um JWT em forma se string através do body.
