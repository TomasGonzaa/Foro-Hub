create table topicos(

            id bigint not null auto_increment,
            curso varchar(12) not null,
            titulo varchar(50) not null,
            mensaje varchar(200) not null,
            usuario_id bigint not null,
            fecha datetime not null,
            respuesta_id bigint,

            primary key(id),

            constraint fk_topicos_usuario_id foreign key(usuario_id) references usuarios(id),
            constraint fk_topicos_respuesta_id foreign key(respuesta_id) references respuestas(id)
            );