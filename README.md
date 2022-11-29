<h1 align="center">
    <img alt="Ecommerce" src="https://github.com/JeffersonLuizCruz/financial/blob/main/src/main/resources/tamplates/ecommerce.png" />
</h1>

<h3 align="center">
  REST API E-Commerce - BackEnd - Spring Boot
</h3>

<p align="center">Exemplo de um Sistema E-Commerce</p>

![GitHub repo size](https://img.shields.io/github/repo-size/JeffersonLuizCruz/financial)  ![Packagist License](https://img.shields.io/packagist/l/JeffersonLuizCruz/financial)  ![GitHub top language](https://img.shields.io/github/languages/top/JeffersonLuizCruz/financial)  ![GitHub language count](https://img.shields.io/github/languages/count/JeffersonLuizCruz/financial?label=Linguagem%20de%20Programa%C3%A7%C3%A3o)  ![GitHub followers](https://img.shields.io/github/followers/JeffersonLuizCruz?style=social)

<p align="center">
  <a href="#-sobre">Sobre o projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-diagrama">Diagrama de Classe</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
</p>

## :page_with_curl: Sobre o projeto <a name="-sobre"/></a>

> O objetivo dessa API REST é explorar as boas práticas de modelagem de entidades usando JPA.
- Foi aplicado o conceito de relacionamento de entidade unidirecional e bidirecional visando as vantagens e desvantagens de cada uma delas; 
- Entre o relacionamento do uso de cardinalidade foi abordado o conceito de Entidade Composta entre a entidade Product, ItemRequest e Request.; 
- E o uso das anotation do javax persistence: @ElementCollection, @CollectionTable ...


O sistema deve permitir o cadastro de pelo menos um administrador do sistema. Este terá a
responsabilidade sobre todas as inclusões e alterações que serão feitas na aplicação. O e-commerce deve possibilitar o cadastramento dos clientes além dos dados essenciais para efetuar uma compra. Ao efetuar o pedido, adicionando os itens e quantidade, o cliente poderá optar a forma de pagamento a ser utilizada(Mock de Boleto ou Cartão de Crédito e suas parcelas). A compra só será confirmada a partir do momento em que for validada a forma de pagamento.


## :page_with_curl: Diagrama de Classe <a name="-diagrama"/></a>
<h1 align="center">
    <img alt="Ecommerce" src="https://github.com/JeffersonLuizCruz/financial/blob/main/src/main/resources/tamplates/Classe%20UML3.png" />
</h1>

## Tecnologia:
- [x] Java 11<br>
- [x] Spring Boot 2.4.4<br>
- [x] Spring Data - JPA/Hibernate<br>
- [x] Banco de Dados PostgreSQL<br>
- [x] Spring Secutity - OAuth 2<br>
- [ ] Front-end Ionic<br>
- [x] Implementação no Heroku<br>
- [ ] Amazon S3<br>

## Construção do Projeto:
- [x] Criação de Interface Service (garantir baixo acoplamento)<br>
- [x] CRUD (ORM Hibernate - Ambiente de teste)<br>
- [x] Exception Personalizada
- [x] Consulta e Busca Paginada
- [ ] Autenticação e Autorização (JWT)
- [x] Serviço de Email

