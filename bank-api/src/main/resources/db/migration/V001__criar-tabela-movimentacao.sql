create table movimentacao(
id bigint not null auto_increment,
conta_id bigint not null,
operacao bigint not null,
valor decimal(19,2) not null,
data_hora datetime not null,
primary key(id)
) engine=InnoDB default charset=utf8;   