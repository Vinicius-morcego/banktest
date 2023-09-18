set foreign_key_checks = 0;

#lock tables cliente write, conta write, movimentacao write;

delete from cliente;
delete from conta;
delete from movimentacao;

set foreign_key_checks = 1;

alter table cliente auto_increment = 1;
alter table conta auto_increment = 1;
alter table movimentacao auto_increment = 1;

insert into cliente (nome, cpf) values('Vinicius Queiroz Souza', '64186012032');
insert into conta (cliente_id, numero, limite, saldo) values(1, '1234567', 100.0, 0.0);

