CREATE SCHEMA IF NOT EXISTS gestion_citas;

create table if not exists gestion_citas.tipo_documento(
	id SERIAL PRIMARY KEY,
	abreviacion VARCHAR(4) unique,
	descripcion VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS gestion_citas.consultorio (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    ubicacion VARCHAR(255),
    fechaCreacion DATE,
    fechaModificacion DATE
);
CREATE TABLE IF NOT EXISTS gestion_citas.doctor (
	tipoDocumentoId INTEGER,
    numDocumento VARCHAR(15),
    idConsultorio INTEGER,
    nombreCompleto VARCHAR(200),
    especialidad VARCHAR(255),
    fechaCreacion DATE,
    fechaModificacion DATE,
    primary key (tipoDocumentoId, numDocumento),
    FOREIGN KEY (idConsultorio) REFERENCES gestion_citas.consultorio(id),
    FOREIGN KEY (tipoDocumentoId) REFERENCES gestion_citas.tipo_documento(id)
);

CREATE TABLE IF NOT EXISTS gestion_citas.usuario (
    nombreCompleto VARCHAR(255),
    tipoDocumentoId INTEGER,
    numDocumento VARCHAR(15),
    fechaCreacion DATE,
    fechaModificacion DATE,
    primary key (tipoDocumentoId, numDocumento)
);
 
 
CREATE TABLE IF NOT EXISTS gestion_citas.cita (
    id SERIAL PRIMARY KEY,
    fecha DATE,
    hora VARCHAR(255),
    estado VARCHAR(255),
    tipoDocumentoIdDoctor INTEGER,
    numDocumentoDoctor VARCHAR(15),
    tipoDocumentoIdUsuario INTEGER,
    numDocumentoUsuario VARCHAR(15),
    fechaCreacion DATE,
    fechaModificacion DATE,
    FOREIGN KEY (tipoDocumentoIdDoctor, numDocumentoDoctor) REFERENCES gestion_citas.doctor(tipoDocumentoId, numDocumento),
    FOREIGN KEY (tipoDocumentoIdUsuario, numDocumentoUsuario) REFERENCES gestion_citas.usuario(tipoDocumentoId, numDocumento)
);


CREATE TABLE IF NOT EXISTS gestion_citas.diagnostico (
    id SERIAL PRIMARY KEY,
    descripcion VARCHAR(255),
    formulaMedica VARCHAR(1000),
    idCita INTEGER,
    fechaCreacion DATE,
    fechaModificacion DATE,
    FOREIGN KEY (idCita) REFERENCES gestion_citas.cita(id)
);

insert into gestion_citas.tipo_documento (abreviacion, descripcion)
VALUES 
('C.C', 'CEDULA'),
('C.E', 'CEDULA EXTRANJERIA'),
('T.I', 'TARJETA DE IDENTIDAD'),
('R.C', 'REGISTRO CIVIL');