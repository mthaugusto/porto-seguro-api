

<h1>Documentação da API - Gerenciamento Porto Seguro</h1>


<h1>Visão Geral</h1>
<p>Esta API oferece operações de gerenciamento de clientes, bicicletas e seus dados, permitindo a criação, recuperação, atualização e exclusão de registros dos mesmos. É uma API RESTful que opera no banco de dados da Porto Seguro.</p>
<p>É importante observar que o banco de dados da Porto se trata de um banco com tabelas relacionadas entre si, portanto a exclusão de algum item que possua chave estrangeira apenas é permitida após a exclusão do item filho, assim como o limite hierárquico deve ser respeitado na hora de cadastrar novos dados.</p>

<h1>Métodos</h1>

<h3>1. Listar Todos</h3>
<p><strong>Método:</strong> GET</p>
<p><strong>BASE URL:</strong> http://localhost:8080/portoapi/webapi</p>
<p><strong>URL:</strong> /clientes | /bicicletas | /modelos | /marcas | /acessorios | /vistorias | /fotos-bicicleta</p>
<p><strong>Descrição:</strong> Obtém uma lista completa dos dados cadastrados no banco de dados.</p>
<p><strong>Resposta de Sucesso:</strong> Código 200 (OK)</p>
<p><strong>Exemplo de Resposta:</strong></p>
<pre>
    [
        {
            "dtCadastro": "2020-01-01",
            "dtNascimento": "1980-05-20",
            // Outros campos...
        },
        // Outros clientes...
    ]
</pre>

<h3>2. Obter cliente, bicicleta ou atributos por CPF, ID ou número de série</h3>
<p><strong>Método:</strong> GET</p>
<p><strong>URL:</strong> /acessorios/{id} | /bicicletas/{nrSerie} | /clientes/{cpf} | /fotos-bicicleta/{id} | /marcas/{id} | /modelos/{id} | /vistorias{nrSerie}</p>
<p><strong>Descrição:</strong> Obtém os detalhes dos acessórios, bicicletas, clientes, fotos-bicicleta, marcas, modelos ou vistorias com base no id, número de série ou CPF fornecidos.</p>
<p><strong>Resposta de Sucesso:</strong> Código 200 (OK)</p>
<p><strong>Exemplo de Resposta:</strong></p>
<pre>
    {
        "dtCadastro": "2021-02-28",
        "dtNascimento": "1992-12-10",
        // Outros campos...
    }
</pre>

<h3>3. Cadastrar um novo cliente, bicicleta, marcas, modelos, vistorias, acessórios ou fotos das bicicletas.</h3>
<p><strong>Método:</strong> POST</p>
<p><strong>URL:</strong> /acessorios/{id} | /bicicletas/{nrSerie} | /clientes/{cpf} | /fotos-bicicleta/{id} | /marcas/{id} | /modelos/{id} | /vistorias{nrSerie}</p>
<p><strong>Descrição:</strong> Cadastrar com os detalhes fornecidos.</p>
<p><strong>Corpo da Requisição:</strong></p>
<pre>
    {
        "dtCadastro": "2016-09-15",
        "dtNascimento": "1982-09-30",
        // Outros campos...
    }
</pre>
<p><strong>Resposta de Sucesso:</strong> Código 201 (Created)</p>

<h3>4. Atualizar um cliente, bicicleta, marca, modelo, vistoria, acessório ou foto das bicicletas.</h3>
<p><strong>Método:</strong> PUT</p>
<p><strong>URL:</strong> /acessorios/{id} | /bicicletas/{nrSerie} | /clientes/{cpf} | /fotos-bicicleta/{id} | /marcas/{id} | /modelos/{id} | /vistorias{nrSerie}</p>
<p><strong>Descrição:</strong> Atualiza os detalhes com base no ID, número de série ou CPF fornecido.</p>
<p><strong>Corpo da Requisição (campos a serem atualizados):</strong></p>
<pre>
    {
        "dtCadastro": "2016-09-15",
        "dtNascimento": "1982-09-30",
        // Outros campos...
    }
</pre>
<p><strong>Resposta de Sucesso:</strong> Código 200 (OK)</p>

<h3>5. Excluir um cliente, bicicleta, marca, modelo, vistoria, acessório ou foto das bicicletas.</h3>
<p><strong>Método:</strong> DELETE</p>
<p><strong>URL:</strong> /acessorios/{id} | /bicicletas/{nrSerie} | /clientes/{cpf} | /fotos-bicicleta/{id} | /marcas/{id} | /modelos/{id} | /vistorias{nrSerie}</p>
<p><strong>Parâmetros de URL:</strong> {id}: O ID do personagem que deseja excluir.</p>
<p><strong>Descrição:</strong> Exclui um cliente, bicicleta, marca, modelo, vistoria, acessório ou foto das bicicletas com base no ID, número de série ou CPF fornecido.</p>
<p><strong>Resposta de Sucesso:</strong> Código 204 (No Content)</p>
