create table tb02_wallet (
  id bigint generated by default as identity,
  nome varchar(255) not null,
  valor decimal(19, 2) not null,
  primary key (id)
)