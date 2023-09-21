# banktest
Teste simples de API para banco

<h1>Tecnologias</h1><br>
Java 17<br>
Spring v3.0<br>
Spring Boot<br>
Spring Web<br>
Spring Data JPA<br>
Flyway<br>
MySQL<br>
Lombok<br><br>

<h1>Estrutura de tabelas</h1><br>
Cliente (id, nome, cpf)<br>
Conta (id, client_id, numero, limite, saldo);<br>
Movimentação (id, conta_id, operação, valor, data_hora)<br><br>

<h1>Regra de Negócio</h1><br>
Não cadastrar mais de um cliente com o mesmo CPF<br>
Não deletar cliente com conta vinculada;<br>
Não cadastrar mais de uma conta com mesmo número<br>
Não deletar conta com movimentação vinculada<br>
Toda conta deve ter cliente vinculado<br>
Conta deve ser criada com saldo zero;<br>
Saldo da conta não deve ser alterada diretamente, apenas mediante <br>
a movimentações (Depósitos e Saques)<br>
Toda movimentação deve ter conta vinculada<br>
Valor de saque não deve ultrapassar (Saldo + Limite);



