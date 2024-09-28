# API de Cadastro de Clientes e Pedidos

Esta API RESTful realiza o cadastro de clientes, pedidos e aplica regras de desconto nas taxas de entrega. A API foi desenvolvida em Java com Spring Boot, empacotada em um container Docker e está disponível no Docker Hub.

## Requisitos de Ambiente

- **[Docker](https://www.docker.com/get-started)**: Para executar o container da API. 
- **[Postman](https://www.postman.com/downloads/)**: Para testar os endpoints da API. 
- **[Git](https://git-scm.com/book/en/v2/)**: Para clonar o repositório do código-fonte.

## Passo a Passo para Execução Local

### 1. Clonando o Repositório

Clone o repositório com o código-fonte da API:

```bash
git clone https://github.com/LDrawe/trabalho-extra.git
cd trabalho-extra
```
## 2. Construindo e Executando a Imagem Docker

Para construir a imagem Docker e executar a API, use os seguintes comandos:

```bash
docker build -t nome-da-imagem .
docker run -p 8080:8080 nome-da-imagem
```

## Consumo da API com Postman

### 1. Importando a Coleção Postman

Para facilitar o teste dos endpoints da API, uma coleção Postman foi fornecida. Siga os passos abaixo para importá-la:

1. Abra o **Postman**.
2. Clique em **File > Import** no canto superior esquerdo.
3. Escolha a opção **Upload Files** e selecione o arquivo da coleção Postman presente na pasta raíz deste projeto.

### 2. Cenários de Teste

#### Cenário 1: Criar Cliente

- **Método**: `POST`
- **Endpoint**: `/clientes`
- **Descrição**: Cria um novo cliente.
- **Corpo da Requisição** (exemplo):

```json
{
  "nome": "Caio",
  "tipo": "Prata",
  "fidelidade": 100.0,
  "logradouro": "Rua A",
  "bairro": "Centro",
  "cidade": "São Paulo"
}
```
- **Resposta Esperada** (exemplo):

```json

  {
    "id": 0,
    "nome": "Caio",
    "tipo": "Prata",
    "fidelidade": 100.0,
    "logradouro": "Rua A",
    "bairro": "Centro",
    "cidade": "São Paulo"
}
```
#### Cenário 2: Criar Pedido

- **Método**: `POST`
- **Endpoint**: `/pedidos`
- **Descrição:** Cria um novo pedido associado ao cliente criado.
- **Corpo da Requisição** (exemplo):

```json

{
  "clienteId": 1,
  "data": "2024-09-27",
  "itens": [
    {
      "nome": "Pizza",
      "quantidade": 2,
      "valorUnitario": 35.50,
      "tipo": "Alimentação"
    }
  ]
}
```
- **Resposta Esperada**: Status 201

#### Cenário 3: Consultar descontos sem aplicá-los primeiro

- **Método**: `GET`
- **Endpoint**: `/pedidos/{pedidoId}`
- **Descrição**: Retorna as informações do pedido a partir do ID.
- **Resposta Esperada** (exemplo):

```json
{
    "cliente": {
        "id": 0,
        "nome": "Caio",
        "tipo": "Prata",
        "fidelidade": 100.0,
        "logradouro": "Rua A",
        "bairro": "Centro",
        "cidade": "São Paulo"
    },
    "taxaEntrega": 0.0,
    "itens": [
        {
            "nome": "Pizza",
            "quantidade": 2,
            "valorUnitario": 35.5,
            "tipo": "Alimentação",
            "valorTotal": 71.0
        }
    ],
    "cuponsDescontoEntrega": [],
    "descontoConcedido": 0.0,
    "valorPedido": 71.0
}
```
#### Cenário 4: Processar Descontos

- **Método**: `POST`
- **Endpoint**: `/pedidos/{pedidoId}/processar-descontos`
- **Descrição**: Aplica descontos ao pedido.
- **Parâmetros**:
        pedidoId (Integer)
- **Resposta Esperada**: Status 201

#### Cenário 5: Consultar Pedido com descontos aplicados

- **Método**: `GET`
- **Endpoint**: `/pedidos/{pedidoId}`
- **Descrição**: Retorna as informações do pedido atualizado após a aplicação dos descontos.
- **Resposta** Esperada (exemplo):

```json

{
    "cliente": {
        "id": 0,
        "nome": "Caio",
        "tipo": "Prata",
        "fidelidade": 100.0,
        "logradouro": "Rua A",
        "bairro": "Centro",
        "cidade": "São Paulo"
    },
    "taxaEntrega": 0.0,
    "itens": [
        {
            "nome": "Pizza",
            "quantidade": 2,
            "valorUnitario": 35.5,
            "tipo": "Alimentação",
            "valorTotal": 71.0
        }
    ],
    "cuponsDescontoEntrega": [
        {
            "nomeMetodo": "Desconto por Bairro",
            "valorDesconto": 2.0
        },
        {
            "nomeMetodo": "Desconto por Tipo de Cliente",
            "valorDesconto": 2.0
        },
        {
            "nomeMetodo": "Parcial - Desconto por Tipo de Item",
            "valorDesconto": 6.0
        }
    ],
    "descontoConcedido": 10.0,
    "valorPedido": 71.0
}
```