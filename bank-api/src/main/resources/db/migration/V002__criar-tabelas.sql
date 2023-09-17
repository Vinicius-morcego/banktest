create table cliente(
id bigint auto_increment,
nome varchar(100),
cpf varchar(20),
primary key(id)
)engine=InnoDB default charset=utf8;

create table conta(
id bigint auto_increment,
cliente_id bigint not null,
numero varchar(30) not null,
limite decimal(19,2) not null,
saldo decimal(19,2) not null,
primary key(id)
)engine=InnoDB default charset=utf8;