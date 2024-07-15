alter table respuestas
add constraint fk_respuestas_topico_id
foreign key(topico_id) references topicos(id);