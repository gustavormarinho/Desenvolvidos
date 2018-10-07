create database BD_TCM
use BD_TCM


create table tb_fun(
nome_fun varchar(100) not null,
username varchar(20)not null,
senha varchar(30)not null,
nv_acesso int,   /* 1 adm, 2 funcionário, 3 convidado * - Usar Update para alterar o nível*/
rg_fun varchar(14),
cpf_fun varchar(14) primary key not null,
funcao_fun varchar(50)   
)

create table tb_forn(
nome_forn varchar(40)not null,
nomefantas_forn varchar(50) not null,
cnpj_forn varchar(19) primary key not null,
cep_forn varchar(9)not null,
cid_forn varchar (20),   
est_forn varchar (2),
num_forn int,
tel_forn varchar(18),
email_forn varchar(100),
logra_forn varchar(255)
)

create table tb_orc(
cod_orc int primary key identity(2000,1),
data_orc varchar(20),
cpf_orc varchar(14) references tb_fun(cpf_fun) not null,
cnpj_orc varchar(19) references tb_forn(cnpj_forn)not null,
valortotal_orc decimal(18,2)
)

create table tb_ped(
cod_ped int primary key identity(3000,1),
data_ped varchar (20),
cnpj_ped varchar(19) references tb_forn(cnpj_forn)not null,
cpf_ped varchar (14) references tb_fun(cpf_fun)not null,
valortotal_ped decimal(18,2),
datanf_ped varchar(20),  /*nf = Nota Fiscal */
numnf_ped varchar (30),  
dataent_ped varchar(20) /*Data de Entrega, que pode ser diferente da data da NF*/
)

create table tb_sd(		
cod_sd int primary key identity(4000,1),
cpf_sd varchar(14) references tb_fun(cpf_fun)not null,
solic_sd varchar (50)not null,		/*solicitante*/
data_sd varchar(20),
)

create table tb_prod(	
cod_prod int primary key identity(5000,1),
nome_prod varchar (30)not null,
tipo_prod varchar (15)not null,
qtde_prod int, /*qtde em estoque*/
desc_prod varchar (255), /*Descrição*/
local_prod varchar (50) /*Localização*/
)

create table tb_itemped( 
cod_itemped int primary key identity(6000,1),
codprod_itemped int references tb_prod(cod_prod)not null ,
codped_itemped int references tb_ped(cod_ped)not null,
qtde_itemped int,
preco_itemped decimal(18,2),
precototal_itemped decimal(18,2),  /*preço total */
)

create table tb_itemorc(  /*Item do orçamento*/
cod_itemorc int primary key identity(7000,1),
codprod_itemorc int references tb_prod(cod_prod)not null,
codorc_itemorc int references tb_orc(cod_orc)not null,  /*FK código do orçamento*/
qtde_itemorc int,
preco_itemorc decimal(18,2),
precototal_itemorc decimal(18,2) /*preço total*/
)

create table tb_itemsaida(   /*Item da saída*/
cod_itemsaida int primary key identity(8000,1),
codprod_itemsaida int references tb_prod(cod_prod)not null,
codsd_itemsaida int references tb_sd(cod_sd)not null,
qtde_itemsaida int 
)
