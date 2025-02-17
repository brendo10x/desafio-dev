create table transactions
(
   id int8 generated by default as identity,
   amount numeric (19,2) not null,
   beneficiarys_cpf varchar (255) not null,
   card_number varchar (255) not null,
   transaction_at timestamp not null,
   store_id int8 not null,
   transaction_category_id int8 not null,
   primary key (id)
);

alter table if exists transactions add constraint fk_store_id foreign key (store_id) references stores; 

alter table if exists transactions add constraint fk_transaction_category_id foreign key (transaction_category_id) references transaction_categories;