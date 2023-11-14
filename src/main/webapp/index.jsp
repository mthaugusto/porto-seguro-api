<html>
<body>
    <h2>Porto Seguro API</h2>
    <p>API de gerenciamento dos dados cadastrados no banco de dados de bicicletas da Porto Seguro.</p>
    <a href="webapi/clientes">Clientes resource</a> - <a href="webapi/bicicletas">Bicicletas resource</a> - <a href="webapi/modelos">Modelos resource</a> - <a href="webapi/marcas">Marcas resource</a> - <a href="webapi/vistorias">Vistorias resource</a> - <a href="webapi/acessorios">Acessorios resource</a> - <a href="webapi/fotos-bicicleta">Fotos bicicletas resource</a>

    
    <h2>Documenta��o da API - Gerenciamento Porto Seguro</h2>
    <h3>Vis�o Geral</h3>
    <p>Esta API oferece opera��es de gerenciamento de clientes, bicicletas e seus dados, permitindo a cria��o, recupera��o, atualiza��o e exclus�o de registros dos mesmos. � uma API RESTful que opera no banco de dados da Porto Seguro.</p>
    <p>� importante observar que o banco de dados da Porto se trata de um banco com tabelas relacionadas entre si, portanto a exclus�o de algum item que possua chave estrangeira apenas � permitida ap�s a exclus�o do item filho, assim como o limite hier�rquico deve ser respeitado na hora de cadastrar novos dados.
	
	<h3>M�todos</h3>
	
	<h3>1. Listar Todos</h3>
		<p>M�todo: GET</p>
		<p>BASE URL: http://localhost:8080/portoapi/webapi
		<p>URL: /clientes | /bicicletas | /modelos | /marcas | /acessorios | /vistorias | /fotos-bicicleta </p>
		<p>Descri��o: Obt�m uma lista completa dos dados cadastrados no banco de dados.</p>
		<p>Resposta de Sucesso: C�digo 200 (OK)</p>
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
			"nomeCliente": "Jo�o Silva",
			"nrCpf": "12345678901",
			"nrTelefone": "11111111111",
			"renda": 50000.0,
			"tipoCliente": "PF"
		 },
            // Outros clientes...
     ]
		    </p>
		  </pre>
	
	<h3>2. Obter cliente, bicicleta ou atributos por CPF, ID ou n�mero de s�rie</h3>
		<p>M�todo: GET</p>
		<p>URL: /acessorios/{id} | /bicicletas/{nrSerie} | /clientes/{cpf} | /fotos-bicicleta/{id} | /marcas/{id} | /modelos/{id} | /vistorias{nrSerie}</p></p>
		<p>Descri��o: Obt�m os detalhes dos acess�rios, bicicletas, clientes, fotos-bicicleta, marcas, modelos ou vistorias com base no id, n�mero de s�rie ou CPF fornecidos.</p>
		<p>Resposta de Sucesso: C�digo 200 (OK)</p>
		<p>Exemplo de Resposta:</p>
		<pre>
		    <p>
	{
		"dtCadastro": "2021-02-28",
		"dtNascimento": "1992-12-10",
		"emailCliente": "carlos@email.com",
		"enderecoCliente": "Rua Crep�sculo, 789",
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
	
	<h3>3. Cadastrar um novo cliente, bicicleta, marcas, modelos, vistorias, acess�rios ou fotos das bicicletas.</h3>
		<p>M�todo: POST</p>
		<p>URL: /acessorios/{id} | /bicicletas/{nrSerie} | /clientes/{cpf} | /fotos-bicicleta/{id} | /marcas/{id} | /modelos/{id} | /vistorias{nrSerie}</p></p>
		<p>Descri��o: Cadastrar com os detalhes fornecidos.</p>
		<p>Corpo da Requisi��o:</p>
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
		<p>Resposta de Sucesso: C�digo 201 (Created)</p>
	
	<h3>4. Atualizar um cliente, bicicleta, marca, modelo, vistoria, acess�rio ou foto das bicicletas.</h3>
		<p>M�todo: PUT</p>
		<p>URL: /acessorios/{id} | /bicicletas/{nrSerie} | /clientes/{cpf} | /fotos-bicicleta/{id} | /marcas/{id} | /modelos/{id} | /vistorias{nrSerie}</p></p>
		<p>Descri��o: Atualiza os detalhes com base no ID, n�mero de s�rie ou CPF fornecido.</p>
		<p>Corpo da Requisi��o (campos a serem atualizados):</p>
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
		<p>Resposta de Sucesso: C�digo 200 (OK)</p>
	
	<h3>5. Excluir um um cliente, bicicleta, marca, modelo, vistoria, acess�rio ou foto das bicicletas.</h3>
		<p>M�todo: DELETE</p>
		<p>URL: /acessorios/{id} | /bicicletas/{nrSerie} | /clientes/{cpf} | /fotos-bicicleta/{id} | /marcas/{id} | /modelos/{id} | /vistorias{nrSerie}</p></p>
		<p>Par�metros de URL: {id}: O ID do personagem que deseja excluir.</p>
		<p>Descri��o: Exclui um cliente, bicicleta, marca, modelo, vistoria, acess�rio ou foto das bicicletas com base no ID, n�mero de s�rie ou CPF fornecido.</p>
		<p>Resposta de Sucesso: C�digo 204 (No Content)</p>
</body>
</html>
