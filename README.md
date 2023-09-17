# banktest
Teste simples de API para banco

Tecnologias
Java 17
Spring v3.0
Spring Boot
Spring Web
Spring Data JPA
Flyway
MySQL

Estrutura de tabelas
Cliente (id, nome, cpf)
Conta (id, client_id, numero, limite, saldo);
Movimentação (id, conta_id, operação, valor, data_hora)

Regra de Negócio
Não cadastrar mais de um cliente com o mesmo CPF
Não deletar cliente com conta vinculada;
Não cadastrar mais de uma conta com mesmo número
Não deletar conta com movimentação vinculada
Toda conta deve ter cliente vinculado
Conta de ser criada com saldo zero;
Saldo da conta não deve ser alterada diretamente, apenas mediante 
a movimentações (Depósitos e Saques)
Toda movimentação deve ter conta vinculada
Valor de saque não deve ultrapassar (Saldo + Limite);



