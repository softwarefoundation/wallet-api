```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.4.5.RELEASE)
```

# API RESTfull com Spring Boot utilizando TDD, CI e CD
[![Continuous Deployment Heroku](https://github.com/softwarefoundation/wallet-api/actions/workflows/continuos-deployment-heroku.yml/badge.svg?branch=main)](https://github.com/softwarefoundation/wallet-api/actions/workflows/continuos-deployment-heroku.yml)

Aprenda a criar APIs profissionais com testes utilizando Spring Boot e Java 11, do zero ao deploy em produção

## Acesso ao Swagger da aplicação
[https://wallet-api-softwarefoundation.herokuapp.com/swagger-ui.html](https://wallet-api-softwarefoundation.herokuapp.com/swagger-ui.html)


## Conteúdo
1. Como construir uma API RESTFull utilizando Spring Boot e Java 11 :heavy_check_mark:
2. Utilizar a técnica de Desenvolvimento Orientado a Testes (TDD) :heavy_check_mark:
3. Como fazer o deploy em um ambiente de produção :heavy_check_mark:
4. Utilizar o Travis CI para realizar integração contínua :heavy_check_mark:
5. Utilizar o Travis CD para realizar deploy automatizado :heavy_check_mark:
6. Autenticação via tokens JWT :heavy_check_mark:
7. Configurar Swagger para documentar os endpoints :heavy_check_mark:
8. Versionamento de Banco de Dados com Flyway :heavy_check_mark:
9. Utilização de cache com Ehcache :heavy_check_mark:
10. Como configurar e utilizar o Github :heavy_check_mark:
11. Configurar e utilizar a nuvem do Heroku :heavy_check_mark:

Palavras chave: Mockito, Junit, Tokens JWT, Spring Security, Flyway, H2, Integração Contínua e Deploy Automatizado com o Travis CI.

## Banco de dados
A aplicação utiliza o banco de dados H2 e como é um banco em memória ele é recriado a cada execução da aplicação.
  
### Acesso
  O banco de dados pode ser acesso pelo console WEB, que fica disponível na mesma url da aplicação + /h2-console/ Exe: http://localhost:8080/h2-console/ 

Configuração do console WEB: Para fazer login no console web utilize as configurações abaixo:
* JDBC URL: jdbc:h2:mem:wallet
* User Name: root
* Password:

Essas informações podem ser encontraradas no arquivoapplication.properties

## Referências:

Esse projeto foi desenvolvido durante o curso: [API RESTfull com Spring Boot utilizando TDD, CI e CD](https://www.udemy.com/course/api-restfull-com-spring-boot-utilizando-tdd-ci-e-cd/ ) /  [Repositório de Referência](https://github.com/vitoralves/walletAPI)


Made with :heart: by [Dherkyan Ribeiro](https://www.credly.com/users/dherkyan-ribeiro-da-silva/badges)


![alt Oracle Certified Professional, Java SE 7 Programmer](https://images.credly.com/size/110x110/images/3661e48f-ee1c-47fc-a474-b84fca370a19/Oracle-Certification-badge_OC-Professional600X600.png)


<p align="center">
	<a href="">
		<img src="https://i.imgur.com/BlmTWPV.png">
	</a>
</p>

