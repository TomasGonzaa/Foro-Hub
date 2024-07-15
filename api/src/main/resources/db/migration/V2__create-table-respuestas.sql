create table respuestas(

            id bigint not null auto_increment,
            topico_id bigint not null,
            mensaje varchar(200) not null,
            usuario_id bigint not null,
            fecha datetime not null,

            primary key(id),

            constraint fk_respuestas_usuario_id foreign key(usuario_id) references usuarios(id)
            );