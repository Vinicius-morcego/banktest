set foreign_key_checks = 0;

lock tables cliente write, conta write, movimentacao write;

delete from cliente;
delete from conta;
delete from movimentacao;

set foreign_key_checks = 1;

alter table cliente auto_increment = 1;
alter table conta auto_increment = 1;
alter table movimentacao auto_increment = 1;

insert into cliente (id, nome, cpf) values(1, 'Vinicius Queiroz Souza', '64186012032');
insert into conta (id, cliente_id, numero, limite, saldo) values(1, 1, '123456-7', 100.0, 0.0);
#insert into movimentacao (id, conta_id, operacao, valor, data_hora);


