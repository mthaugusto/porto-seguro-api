<html>
<body>
    <h2>Porto Seguro API</h2>
    <p>API de gerenciamento dos dados cadastrados no banco de dados de bicicletas da Porto Seguro.</p>
    <a href="webapi/clientes">Clientes resource</a> - <a href="webapi/bicicletas">Bicicletas resource</a> - <a href="webapi/modelos">Modelos resource</a> - <a href="webapi/marcas">Marcas resource</a> - <a href="webapi/vistorias">Vistorias resource</a> - <a href="webapi/acessorios">Acessorios resource</a> - <a href="webapi/fotos-bicicleta">Fotos bicicletas resource</a>

    
    <h2>Documentação da API - Gerenciamento Porto Seguro</h2>
    <h3>Visão Geral</h3>
    <p>Esta API oferece operações de gerenciamento de clientes, bicicletas e seus dados, permitindo a criação, recuperação, atualização e exclusão de registros dos mesmos. É uma API RESTful que opera no banco de dados da Porto Seguro.</p>
    <p>É importante observar que o banco de dados da Porto se trata de um banco com tabelas relacionadas entre si, portanto a exclusão de algum item que possua chave estrangeira apenas é permitida após a exclusão do item filho, assim como o limite hierárquico deve ser respeitado na hora de cadastrar novos dados.
	
	<h3>Métodos</h3>
	
	<h3>1. Listar Todos</h3>
		<p>Método: GET</p>
		<p>BASE URL: http://localhost:8080/portoapi/webapi
		<p>URL: /clientes | /bicicletas | /modelos | /marcas | /acessorios | /vistorias | /fotos-bicicleta </p>
		<p>Descrição: Obtém uma lista completa dos dados cadastrados no banco de dados.</p>
		<p>Resposta de Sucesso: Código 200 (OK)</p>
		<p>Exemplo de Resposta:</p>
		<pre>
		    <p>
	[
		{
			"dtCadastro": "2020-01-01",
			"dtNascimento": "1980-05-20",
			"emailCliente": "joao@email.com",
			"enderecoCliente": "Rua Dante Ribeiro, 123",
			"estadoCivil": "Solteiro",
			"generoCliente": "M",
			"nomeCliente": "João Silva",
			"nrCpf": "12345678901",
			"nrTelefone": "11111111111",
			"renda": 50000.0,
			"tipoCliente": "PF"
		 },
            // Outros clientes...
     ]
		    </p>
		  </pre>
	
	<h3>2. Obter cliente, bicicleta ou atributos por CPF, ID ou número de série</h3>
		<p>Método: GET</p>
		<p>URL: /acessorios/{id} | /bicicletas/{nrSerie} | /clientes/{cpf} | /fotos-bicicleta/{id} | /marcas/{id} | /modelos/{id} | /vistorias{nrSerie}</p></p>
		<p>Descrição: Obtém os detalhes dos acessórios, bicicletas, clientes, fotos-bicicleta, marcas, modelos ou vistorias com base no id, número de série ou CPF fornecidos.</p>
		<p>Resposta de Sucesso: Código 200 (OK)</p>
		<p>Exemplo de Resposta:</p>
		<pre>
		    <p>
	{
		"dtCadastro": "2021-02-28",
		"dtNascimento": "1992-12-10",
		"emailCliente": "carlos@email.com",
		"enderecoCliente": "Rua Crepúsculo, 789",
		"estadoCivil": "Solteiro",
		"generoCliente": "M",
		"nomeCliente": "Carlos Santos",
		"nrCpf": "11111111111",
		"nrTelefone": "999999999",
		"renda": 55000.0,
		"tipoCliente": "PF"
	}
		    </p>
		  </pre>
	
	<h3>3. Cadastrar um novo cliente, bicicleta, marcas, modelos, vistorias, acessórios ou fotos das bicicletas.</h3>
		<p>Método: POST</p>
		<p>URL: /acessorios/{id} | /bicicletas/{nrSerie} | /clientes/{cpf} | /fotos-bicicleta/{id} | /marcas/{id} | /modelos/{id} | /vistorias{nrSerie}</p></p>
		<p>Descrição: Cadastrar com os detalhes fornecidos.</p>
		<p>Corpo da Requisição:</p>
		<pre>
			<p>
 	{
	    "dtCadastro": "2016-09-15",
	    "dtNascimento": "1982-09-30",
	    "emailCliente": "pedro@email.com",
	    "enderecoCliente": "Av. Paulista, 123",
	    "estadoCivil": "Casado",
	    "generoCliente": "M",
	    "nomeCliente": "Ana Paula",
	    "nrCpf": "12345678910",
	    "nrTelefone": "11777777777",
	    "renda": 75000.0,
	    "tipoCliente": "PF"
 	}
			</p>
		</pre>
		<p>Resposta de Sucesso: Código 201 (Created)</p>
	
	<h3>4. Atualizar um cliente, bicicleta, marca, modelo, vistoria, acessório ou foto das bicicletas.</h3>
		<p>Método: PUT</p>
		<p>URL: /acessorios/{id} | /bicicletas/{nrSerie} | /clientes/{cpf} | /fotos-bicicleta/{id} | /marcas/{id} | /modelos/{id} | /vistorias{nrSerie}</p></p>
		<p>Descrição: Atualiza os detalhes com base no ID, número de série ou CPF fornecido.</p>
		<p>Corpo da Requisição (campos a serem atualizados):</p>
		<pre>
		<p>
	{
	    "dtCadastro": "2016-09-15",
	    "dtNascimento": "1982-09-30",
	    "emailCliente": "pedro@email.com",
	    "enderecoCliente": "Av. Paulista, 123",
	    "estadoCivil": "Casado",
	    "generoCliente": "M",
	    "nomeCliente": "Ana Paula",
	    "nrCpf": "12345678910",
	    "nrTelefone": "11777777777",
	    "renda": 75000.0,
	    "tipoCliente": "PF"
	}
		</p>
		</pre>
		<p>Resposta de Sucesso: Código 200 (OK)</p>
	
	<h3>5. Excluir um um cliente, bicicleta, marca, modelo, vistoria, acessório ou foto das bicicletas.</h3>
		<p>Método: DELETE</p>
		<p>URL: /acessorios/{id} | /bicicletas/{nrSerie} | /clientes/{cpf} | /fotos-bicicleta/{id} | /marcas/{id} | /modelos/{id} | /vistorias{nrSerie}</p></p>
		<p>Parâmetros de URL: {id}: O ID do personagem que deseja excluir.</p>
		<p>Descrição: Exclui um cliente, bicicleta, marca, modelo, vistoria, acessório ou foto das bicicletas com base no ID, número de série ou CPF fornecido.</p>
		<p>Resposta de Sucesso: Código 204 (No Content)</p>
</body>
</html>
