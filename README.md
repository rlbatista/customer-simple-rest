# Teste Hexadata

## Observações

### Lombok
Este projeto usa o lombok para reduzir a verbosidade com get/set/toString/builder etc, mas exige
a instalação na IDE (eu uso o eclipse).
A instalação é bem simples, ela pode ser feita com o próprio jar que o baixado pelo maven bastando apenas:
* Executar o jar (ele possui uma interface gráfica)
* Apontar para o diretório onde a IDE está localizada e clicar em _Install_
* Reiniciar a IDE após a mensagem de sucesso

### MapStruct
Grosseiramente falando, o MapSctruct é um framework para "de/para" de objetos. Facilica muito a comunicação
entre a camada rest e os serviços atrelados pois crio objetos especificos para a camada rest ao invés de 
transportar diretamente as entidades.
O MapStruct não faz reflection para fazer a mágica dele, ele realmente implementa as classes que fazem isso
e mantém essas classes na pasta /target/generated-sources/annotations e, apesar de não necessitar de instalação
como o lombock, é necessário rodar pelo menos um 'mvn compile' para que ele gere essas implementações e as
mensagens de erro da IDE desapareçam.

## Documentação do serviço
Após subir o serviço, a documentação das chamadas podem ser acessadas através dos caminhos: /, /doc ou /docs
O teste foi implementado usando um simples banco em memória e ele sobe com 2 usuários:

|Usuário  |Senha   |Papeis     |
|---------|--------|-----------|
|test_user|changeMe|ADMIN, USER|
|guest    |123     |USER       |

## Docker
Para gerar uma imagem do projeto rode:
`mvn clean package docker:build`