-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.17 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para sirius
CREATE DATABASE IF NOT EXISTS `sirius` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sirius`;

-- Volcando estructura para tabla sirius.administrador
CREATE TABLE IF NOT EXISTS `administrador` (
  `login` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `nombre_administrador` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `carrera_area` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `tipo_administrador` int(11) NOT NULL,
  `estado` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Alta',
  PRIMARY KEY (`login`),
  KEY `fk_administrador_carrera` (`carrera_area`),
  KEY `fk_administrador_tipo` (`tipo_administrador`),
  CONSTRAINT `fk_administrador_carrera` FOREIGN KEY (`carrera_area`) REFERENCES `carrera_area` (`clave`),
  CONSTRAINT `fk_administrador_tipo` FOREIGN KEY (`tipo_administrador`) REFERENCES `tipo_administrador` (`idtipo_administrador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla sirius.administrador: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
REPLACE INTO `administrador` (`login`, `nombre_administrador`, `password`, `carrera_area`, `tipo_administrador`, `estado`) VALUES
	('david', 'David Esquivel Mendoza', 'fa1af6355ba77083d352800dc91294ce31500f39b8c41db60b34657d3c4cd658', 'divinfo', 1, 'Alta'),
	('karina', 'Karina Esquivel Mendoza', 'fa1af6355ba77083d352800dc91294ce31500f39b8c41db60b34657d3c4cd658', 'divinfo', 1, 'Alta'),
	('lynn', 'Lynn G', 'a2761524101bcaa778bbe25ce62603b926ad1e7032cd01f1e628f1fbf31c1fc5', 'divinfo', 1, 'Baja');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;

-- Volcando estructura para tabla sirius.carrera_area
CREATE TABLE IF NOT EXISTS `carrera_area` (
  `clave` varchar(8) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nombre_carrera` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `descripcion` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `jefe_director` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tipo_carrera` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `estado` varchar(4) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Alta',
  PRIMARY KEY (`clave`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla sirius.carrera_area: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `carrera_area` DISABLE KEYS */;
REPLACE INTO `carrera_area` (`clave`, `nombre_carrera`, `descripcion`, `jefe_director`, `tipo_carrera`, `estado`) VALUES
	('divaero', 'Ingeniería Aeronáutica', 'Formar profesionistas de la Ingeniería Aeronáutica, con una base científica, tecnológica y humanística, capaces de diseñar, evaluar, gestionar y desarrollar procesos de manufactura y componentes así como operar, mantener y administrar sistemas aeronáuticos, con los índices de seguridad y confiabilidad establecidos en las regulaciones aeronáuticas vigentes, bajo un enfoque sustentable.', 'Mtro. Ángel Martínez Cruz', 'Carrera', 'Alta'),
	('divgem', 'Ingeniería Gestión Empresarial', 'Formar integralmente profesionales que contribuyan a la gestión de empresas e innovación de procesos así como al diseño, implementación y desarrollo de sistemas estratégicos de negocios, optimizando recursos en un entorno global, con ética y responsabilidad social.', 'Mtra. Irisbeth Ramírez Díaz', 'Carrera', 'Alta'),
	('divinfo', 'Ingenieria Informática', 'Formar profesionales competentes en el diseño, desarrollo, implementación y administración de servicios informáticos y gestión de proyectos con una visión sistémica, tecnológica y estratégica, ofreciendo soluciones innovadoras e integrales a las organizaciones, de acuerdo con las necesidades globales, actuales y emergentes, comprometidos con su entorno, desempeñándose con actitud ética, emprendedora y de liderazgo.', 'Ing. Veronica Martinez Martinez', 'Carrera', 'Alta');
/*!40000 ALTER TABLE `carrera_area` ENABLE KEYS */;

-- Volcando estructura para tabla sirius.comunidad
CREATE TABLE IF NOT EXISTS `comunidad` (
  `matricula` int(11) NOT NULL,
  `nombre_comunidad` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `claverfid` int(11) NOT NULL,
  `carrera_area` varchar(8) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fotografia` varchar(14) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `saldo` decimal(7,2) NOT NULL DEFAULT '0.00',
  `tipo_comunidad` varchar(14) COLLATE utf8mb4_unicode_ci NOT NULL,
  `estado` varchar(4) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Alta',
  PRIMARY KEY (`matricula`),
  KEY `fk_comunidad_carrera` (`carrera_area`),
  CONSTRAINT `fk_comunidad_carrera` FOREIGN KEY (`carrera_area`) REFERENCES `carrera_area` (`clave`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla sirius.comunidad: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `comunidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `comunidad` ENABLE KEYS */;

-- Volcando estructura para tabla sirius.servicios
CREATE TABLE IF NOT EXISTS `servicios` (
  `idservicio` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_servicio` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `carrera_area` varchar(8) COLLATE utf8mb4_unicode_ci NOT NULL,
  `costo` decimal(7,2) NOT NULL DEFAULT '0.00',
  `descripcion` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `fecha_entrega` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `imagen` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `estado` varchar(4) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Alta',
  PRIMARY KEY (`idservicio`),
  KEY `fk_servicios_carrera` (`carrera_area`),
  CONSTRAINT `fk_servicios_carrera` FOREIGN KEY (`carrera_area`) REFERENCES `carrera_area` (`clave`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla sirius.servicios: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `servicios` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicios` ENABLE KEYS */;

-- Volcando estructura para tabla sirius.tipo_administrador
CREATE TABLE IF NOT EXISTS `tipo_administrador` (
  `idtipo_administrador` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `estado` varchar(4) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Alta',
  PRIMARY KEY (`idtipo_administrador`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla sirius.tipo_administrador: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tipo_administrador` DISABLE KEYS */;
REPLACE INTO `tipo_administrador` (`idtipo_administrador`, `descripcion`, `estado`) VALUES
	(1, 'Super Administrador', 'Alta');
/*!40000 ALTER TABLE `tipo_administrador` ENABLE KEYS */;

-- Volcando estructura para procedimiento sirius.administradorBuscar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `administradorBuscar`(
	IN `paramDato` VARCHAR(60)
)
BEGIN
   SELECT administrador.login,
   administrador.nombre_administrador,
   carrera_area.nombre_carrera,
   tipo_administrador.descripcion
   FROM administrador administrador
   INNER JOIN carrera_area carrera_area
   ON administrador.carrera_area = carrera_area.clave
   INNER JOIN tipo_administrador tipo_administrador
   ON administrador.tipo_administrador = tipo_administrador.idtipo_administrador
   WHERE administrador.login LIKE CONCAT('%', paramDato, '%')
   OR administrador.nombre_administrador LIKE CONCAT('%', paramDato, '%')
   AND administrador.estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.administradorConsultar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `administradorConsultar`()
BEGIN
   SELECT administrador.login,
   administrador.nombre_administrador,
   carrera_area.nombre_carrera,
   tipo_administrador.descripcion
   FROM administrador administrador
   INNER JOIN carrera_area carrera_area
   ON administrador.carrera_area = carrera_area.clave
   INNER JOIN tipo_administrador tipo_administrador
   ON administrador.tipo_administrador = tipo_administrador.idtipo_administrador
   WHERE administrador.estado = 'Alta'
   ORDER BY administrador.nombre_administrador ASC;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.administradorContar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `administradorContar`()
BEGIN
   SELECT COUNT(login) AS cantidad
   FROM administrador
   WHERE estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.administradorContarBuscados
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `administradorContarBuscados`(
	IN `paramDato` VARCHAR(60)
)
BEGIN
   SELECT COUNT(login) AS cantidad
   FROM administrador
   WHERE login LIKE CONCAT('%', paramDato, '%')
   OR nombre_administrador LIKE CONCAT('%', paramDato,'%')
   AND estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.administradorEliminar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `administradorEliminar`(
	IN `paramLogin` VARCHAR(15)
)
BEGIN
   UPDATE administrador
   SET estado = 'Baja'
   WHERE login = paramLogin;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.administradorExiste
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `administradorExiste`(
	IN `paramLogin` VARCHAR(15)
)
BEGIN
   SELECT *
   FROM administrador
   WHERE login = paramLogin;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.administradorInsertar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `administradorInsertar`(
	IN `paramLogin` VARCHAR(15),
	IN `paramNombre` VARCHAR(60),
	IN `paramPassword` VARCHAR(64),
	IN `paramCarrera` VARCHAR(8),
	IN `paramTipo` INT
)
BEGIN
   INSERT INTO administrador
   (
      login,
      nombre_administrador,
      password,
      carrera_area,
      tipo_administrador
   )
   VALUES
   (
      paramLogin,
      paramNombre,
      paramPassword,
      paramCarrera,
      paramTipo
   );
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.administradorModificar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `administradorModificar`(
	IN `paramLogin` VARCHAR(15),
	IN `paramNombre` VARCHAR(60),
	IN `paramPassword` VARCHAR(64),
	IN `paramCarrera` VARCHAR(8),
	IN `paramTipo` INT

)
BEGIN
   UPDATE administrador
   SET nombre_administrador = paramNombre,
   password = paramPassword,
   carrera_area = paramCarrera,
   tipo_administrador = paramTipo
   WHERE login = paramLogin;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.administradorSeleccionar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `administradorSeleccionar`(
	IN `paramLogin` VARCHAR(15)
)
BEGIN
   SELECT *
   FROM administrador
   WHERE login = paramLogin;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.administradorValidar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `administradorValidar`(
	IN `paramLogin` VARCHAR(15),
	IN `paramPassword` VARCHAR(64)
)
BEGIN
   SELECT administrador.nombre_administrador,
   tipo_administrador.descripcion
   FROM administrador administrador
   INNER JOIN tipo_administrador tipo_administrador
   ON administrador.tipo_administrador = tipo_administrador.idtipo_administrador
   WHERE administrador.login = paramLogin
   AND administrador.password = paramPassword
   AND administrador.estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.carreraAreaBuscar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `carreraAreaBuscar`(
	IN `paramDato` VARCHAR(60)
)
BEGIN
   SELECT clave,
   nombre_carrera,
   jefe_director,
   tipo_carrera
   FROM carrera_area
   WHERE clave LIKE CONCAT('%', paramDato, '%')
   OR nombre_carrera LIKE CONCAT('%', paramDato, '%')
   AND estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.carreraAreaBuscarFiltro
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `carreraAreaBuscarFiltro`(
	IN `paramDato` VARCHAR(60),
	IN `paramFiltro` VARCHAR(10)
)
BEGIN
   SELECT clave,
   nombre_carrera,
   jefe_director,
   tipo_carrera
   FROM carrera_area
   WHERE clave LIKE CONCAT('%', paramDato, '%')
   OR nombre_carrera LIKE CONCAT('%', paramDato, '%')
   AND tipo_carrera = paramFiltro
   AND estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.carreraAreaConsultar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `carreraAreaConsultar`()
BEGIN
   SELECT clave,
   nombre_carrera,
   jefe_director,
   tipo_carrera
   FROM carrera_area
   WHERE estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.carreraAreaConsultarFiltro
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `carreraAreaConsultarFiltro`(
	IN `paramFiltro` VARCHAR(10)
)
BEGIN
   SELECT clave,
   nombre_carrera,
   jefe_director,
   tipo_carrera
   FROM carrera_area
   WHERE tipo_carrera = paramFiltro
   AND estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.carreraAreaContar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `carreraAreaContar`()
BEGIN
   SELECT COUNT(clave) AS cantidad
   FROM carrera_area
   WHERE estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.carreraAreaContarBuscado
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `carreraAreaContarBuscado`(
	IN `paramDato` VARCHAR(60)
)
BEGIN
   SELECT COUNT(clave) AS cantidad
   FROM carrera_area
   WHERE clave LIKE CONCAT('%', paramDato, '%')
   OR nombre_carrera LIKE CONCAT('%', paramDato, '%')
   AND estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.carreraAreaContarBuscadoFiltro
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `carreraAreaContarBuscadoFiltro`(
	IN `paramDato` VARCHAR(60),
	IN `paramFiltro` VARCHAR(10)
)
BEGIN
   SELECT COUNT(clave) AS cantidad
   FROM carrera_area
   WHERE clave LIKE CONCAT('%', paramDato, '%')
   OR nombre_carrera LIKE CONCAT('%', paramDato, '%')
   AND tipo_carrera = paramFiltro
   AND estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.carreraAreaContarFiltro
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `carreraAreaContarFiltro`(
	IN `paramFiltro` VARCHAR(10)
)
BEGIN
   SELECT COUNT(clave) AS cantidad
   FROM carrera_area
   WHERE tipo_carrera = paramFiltro
   AND estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.carreraAreaEliminar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `carreraAreaEliminar`(
	IN `paramClave` VARCHAR(8)
)
BEGIN
   UPDATE carrera_area
   SET estado = 'Baja'
   WHERE clave = paramClave;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.carreraAreaExiste
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `carreraAreaExiste`(
	IN `paramClave` VARCHAR(8)
)
BEGIN
   SELECT *
   FROM carrera_area
   WHERE clave = paramClave;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.carreraAreaInsertar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `carreraAreaInsertar`(
	IN `paramClave` VARCHAR(8),
	IN `paramNombre` VARCHAR(60),
	IN `paramDescripcion` TEXT,
	IN `paramJefe` VARCHAR(60),
	IN `paramTipo` CHAR(10)
)
BEGIN
   INSERT INTO carrera_area
   (
      clave,
      nombre_carrera,
      descripcion,
      jefe_director,
      tipo_carrera
   )
   VALUES
   (
      paramClave,
      paramNombre,
      paramDescripcion,
      paramJefe,
      paramTipo
   );
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.carreraAreaModificar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `carreraAreaModificar`(
	IN `paramClave` VARCHAR(8),
	IN `paramNombre` VARCHAR(60),
	IN `paramDescripcion` TEXT,
	IN `paramJefe` VARCHAR(60),
	IN `paramTipo` VARCHAR(10)
)
BEGIN
   UPDATE carrera_area
   SET nombre_carrera = paramNombre,
   descripcion = paramDescripcion,
   jefe_director = paramJefe,
   tipo_carrera = paramTipo
   WHERE clave = paramClave;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.carreraAreaSeleccionar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `carreraAreaSeleccionar`(
	IN `paramClave` VARCHAR(8)
)
BEGIN
   SELECT clave,
   nombre_carrera,
   descripcion,
   jefe_director,
   tipo_carrera
   FROM carrera_area
   WHERE clave = paramClave;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.comunidadBuscar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `comunidadBuscar`(
	IN `paramDato` VARCHAR(60)
)
BEGIN
   SELECT comunidad.matricula,
   comunidad.nombre_comunidad,
   carrera_area.nombre_carrera,
   comunidad.fecha_registro,
   comunidad.tipo_comunidad,
   comunidad.saldo
   FROM comunidad comunidad
   INNER JOIN carrera_area carrera_area
   ON comunidad.carrera_area = carrera_area.clave
   WHERE comunidad.matricula LIKE CONCAT('%', paramDato, '%')
   OR comunidad.nombre_comunidad LIKE CONCAT('%', paramDato, '%')
   OR carrera_area.nombre_carrera LIKE CONCAT('%', paramDato, '%')
   OR comunidad.tipo_comunidad LIKE CONCAT('%', paramDato, '%')
   AND comunidad.estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.comunidadBuscarFiltro
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `comunidadBuscarFiltro`(
	IN `paramDato` VARCHAR(60),
	IN `paramFiltro` VARCHAR(14)
)
BEGIN
   SELECT comunidad.matricula,
   comunidad.nombre_comunidad,
   carrera_area.nombre_carrera,
   comunidad.fecha_registro,
   comunidad.tipo_comunidad,
   comunidad.saldo
   FROM comunidad comunidad
   INNER JOIN carrera_area carrera_area
   ON comunidad.carrera_area = carrera_area.clave
   WHERE comunidad.matricula LIKE CONCAT('%', paramDato, '%')
   OR comunidad.nombre_comunidad LIKE CONCAT('%', paramDato, '%')
   OR carrera_area.nombre_carrera LIKE CONCAT('%', paramDato, '%')
   OR comunidad.tipo_comunidad LIKE CONCAT('%', paramDato, '%')
   AND comunidad.tipo_comunidad = paramFiltro
   AND comunidad.estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.comunidadConsultar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `comunidadConsultar`()
BEGIN
   SELECT comunidad.matricula,
   comunidad.nombre_comunidad,
   carrera_area.nombre_carrera,
   comunidad.fecha_registro,
   comunidad.tipo_comunidad,
   comunidad.saldo
   FROM comunidad comunidad
   INNER JOIN carrera_area carrera_area
   ON comunidad.carrera_area = carrera_area.clave
   WHERE comunidad.estado = 'Alta'
   ORDER BY comunidad.nombre_comunidad ASC;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.comunidadConsultarFiltro
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `comunidadConsultarFiltro`(
	IN `paramFiltro` VARCHAR(14)
)
BEGIN
      SELECT comunidad.matricula,
   comunidad.nombre_comunidad,
   carrera_area.nombre_carrera,
   comunidad.fecha_registro,
   comunidad.tipo_comunidad,
   comunidad.saldo
   FROM comunidad comunidad
   INNER JOIN carrera_area carrera_area
   ON comunidad.carrera_area = carrera_area.clave
   WHERE comunidad.tipo_comunidad = paramFiltro
   AND comunidad.estado = 'Alta'
   ORDER BY comunidad.nombre_comunidad ASC;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.comunidadContar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `comunidadContar`()
BEGIN
   SELECT COUNT(comunidad.matricula) AS cantidad
   FROM comunidad comunidad
   INNER JOIN carrera_area carrera_area
   ON comunidad.carrera_area = carrera_area.clave
   WHERE comunidad.estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.comunidadContarBuscadaFiltro
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `comunidadContarBuscadaFiltro`(
	IN `paramDato` VARCHAR(60),
	IN `paramFiltro` VARCHAR(14)
)
BEGIN
   SELECT COUNT(comunidad.matricula) AS cantidad
   FROM comunidad comunidad
   INNER JOIN carrera_area carrera_area
   ON comunidad.carrera_area = carrera_area.clave
   WHERE comunidad.matricula LIKE CONCAT('%', paramDato, '%')
   OR comunidad.nombre_comunidad LIKE CONCAT('%', paramDato, '%')
   OR carrera_area.nombre_carrera LIKE CONCAT('%', paramDato, '%')
   OR comunidad.tipo_comunidad LIKE CONCAT('%', paramDato, '%')
   AND comunidad.tipo_comunidad = paramFiltro
   AND comunidad.estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.comunidadContarBuscado
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `comunidadContarBuscado`(
	IN `paramDato` VARCHAR(60)
)
BEGIN
   SELECT COUNT(comunidad.matricula) AS cantidad
   FROM comunidad comunidad
   INNER JOIN carrera_area carrera_area
   ON comunidad.carrera_area = carrera_area.clave
   WHERE comunidad.matricula LIKE CONCAT('%', paramDato, '%')
   OR comunidad.nombre_comunidad LIKE CONCAT('%', paramDato, '%')
   OR carrera_area.nombre_carrera LIKE CONCAT('%', paramDato, '%')
   OR comunidad.tipo_comunidad LIKE CONCAT('%', paramDato, '%')
   AND comunidad.estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.comunidadContarFiltro
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `comunidadContarFiltro`(
	IN `paramFiltro` VARCHAR(14)
)
BEGIN
   SELECT COUNT(comunidad.matricula) AS cantidad
   FROM comunidad comunidad
   INNER JOIN carrera_area carrera_area
   ON comunidad.carrera_area = carrera_area.clave
   WHERE comunidad.tipo_comunidad = paramFiltro
   AND comunidad.estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.comunidadEliminar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `comunidadEliminar`(
	IN `paramMatricula` INT
)
BEGIN
   UPDATE comunidad
   SET estado = 'Baja'
   WHERE matricula = paramMatricula;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.comunidadExiste
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `comunidadExiste`(
	IN `paramMatricula` INT
)
BEGIN
   SELECT *
   FROM comunidad
   WHERE matricula = paramMatricula;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.comunidadInsertar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `comunidadInsertar`(
	IN `paramMatricula` INT,
	IN `paramNombre` VARCHAR(60),
	IN `paramRFID` INT,
	IN `paramCarrera` VARCHAR(8),
	IN `paramFotografia` VARCHAR(14),
	IN `paramTipo` VARCHAR(14)
)
BEGIN
   INSERT INTO comunidad
   (
      matricula,
      nombre_comunidad,
      claverfid,
      carrera_area,
      fotografia,
      tipo_comunidad
   )
   VALUES
   (
      paramMatricula,
      paramNombre,
      paramRFID,
      paramCarrera,
      paramFotografia,
      paramTipo
   );
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.comunidadModificar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `comunidadModificar`(
	IN `paramMatricula` INT,
	IN `paramNombre` VARCHAR(60),
	IN `paramRFID` INT,
	IN `paramCarrera` VARCHAR(8),
	IN `paramFotografia` VARCHAR(14),
	IN `paramSaldo` DECIMAL(7,2),
	IN `paramTipo` VARCHAR(14)
)
BEGIN
   UPDATE comunidad
   SET nombre_comunidad = paramNombre,
   claverfid = paramRFID,
   carrera_area = paramCarrera,
   fotografia = paramFotografia,
   saldo = paramSaldo,
   tipo_comunidad = paramTipo
   WHERE matricula = paramMatricula;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.comunidadSeleccionar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `comunidadSeleccionar`(
	IN `paramMatricula` INT
)
BEGIN
   SELECT comunidad.*,
   carrera_area.nombre_carrera
   FROM comunidad comunidad
   INNER JOIN carrera_area carrera_area
   ON comunidad.carrera_area = carrera_area.clave
   WHERE comunidad.matricula = paramMatricula;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.comunidadValidar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `comunidadValidar`(
	IN `paramMatricula` INT,
	IN `paramRFID` INT
)
BEGIN
   SELECT nombre_comunidad
   FROM comunidad
   WHERE matricula = paramMatricula
   AND rfid = paramRFID;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.servicioBuscar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `servicioBuscar`(
	IN `paramDato` VARCHAR(60)
)
BEGIN
   SELECT servicios.idservicio,
   servicios.nombre_servicio,
   carrera_area.nombre_carrera,
   servicios.costo,
   servicios.descripcion,
   servicios.fecha_entrega
   FROM servicios servicios
   INNER JOIN carrera_area carrera_area
   ON servicios.carrera_area = carrera_area.clave
   WHERE servicios.idservicio LIKE CONCAT('%', paramDato, '%')
   OR servicios.nombre_servicio LIKE CONCAT('%', paramDato, '%')
   OR carrera_area.nombre_carrera LIKE CONCAT('%', paramDato, '%')
   OR servicios.descripcion LIKE CONCAT('%', paramDato, '%')
   AND servicios.estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.servicioConsecutivo
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `servicioConsecutivo`()
BEGIN
   SELECT MAX(idservicio) AS ultimo
   FROM servicios;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.servicioConsultar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `servicioConsultar`()
BEGIN
   SELECT servicios.idservicio,
   servicios.nombre_servicio,
   carrera_area.nombre_carrera,
   servicios.costo,
   servicios.descripcion,
   servicios.fecha_entrega
   FROM servicios servicios
   INNER JOIN carrera_area carrera_area
   ON servicios.carrera_area = carrera_area.clave
   WHERE servicios.estado = 'Alta'
   ORDER BY servicios.nombre_servicio ASC;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.servicioContar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `servicioContar`()
BEGIN
   SELECT COUNT(servicios.idservicio) AS cantidad
   FROM servicios servicios
   INNER JOIN carrera_area carrera_area
   ON servicios.carrera_area = carrera_area.clave
   WHERE servicios.estado = 'Alta';
   
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.servicioContarBuscado
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `servicioContarBuscado`(
	IN `paramDato` VARCHAR(60)
)
BEGIN
   SELECT COUNT(servicios.idservicio) AS cantidad
   FROM servicios servicios
   INNER JOIN carrera_area carrera_area
   ON servicios.carrera_area = carrera_area.clave
   WHERE servicios.idservicio LIKE CONCAT('%', paramDato, '%')
   OR servicios.nombre_servicio LIKE CONCAT('%', paramDato, '%')
   OR carrera_area.nombre_carrera LIKE CONCAT('%', paramDato, '%')
   OR servicios.descripcion LIKE CONCAT('%', paramDato, '%')
   AND servicios.estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.servicioEliminar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `servicioEliminar`(
	IN `paramID` INT
)
BEGIN
   UPDATE servicios
   SET estado = 'Baja'
   WHERE idservicio = paramID;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.servicioExiste
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `servicioExiste`(
	IN `paramID` INT
)
BEGIN
   SELECT *
   FROM servicios
   WHERE idservicio = paramID;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.servicioInsertar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `servicioInsertar`(
	IN `paramNombre` VARCHAR(60),
	IN `paramCarrera` VARCHAR(8),
	IN `paramCosto` DECIMAL(7,2),
	IN `paramDescripcion` TEXT,
	IN `paramFecha` TIMESTAMP,
	IN `paramImagen` VARCHAR(14)
)
BEGIN
   INSERT INTO servicios
   (
      nombre_servicio,
      carrera_area,
      costo,
      descripcion,
      fecha_entrega,
      imagen
   )
   VALUES
   (
      paramNombre,
      paramCarrera,
      paramCosto,
      paramDescripcion,
      paramFecha,
      paramImagen
   );
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.servicioModificar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `servicioModificar`(
	IN `paramID` INT,
	IN `paramNombre` VARCHAR(60),
	IN `paramCarrera` VARCHAR(8),
	IN `paramCosto` DECIMAL(7,2),
	IN `paramDescripcion` TEXT,
	IN `paramFecha` TIMESTAMP,
	IN `paramImagen` VARCHAR(14)
)
BEGIN
   UPDATE servicios
   SET nombre_servicio = paramNombre,
   carrera_area = paramCarrera,
   costo = paramCosto,
   descripcion = paramDescripcion,
   fecha_entrega = paramFecha,
   imagen = paramImagen
   WHERE idservicio = paramID;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.servicioSeleccionar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `servicioSeleccionar`(
	IN `paramID` INT
)
BEGIN
   SELECT servicios.*,
   carrera_area.nombre_carrera
   FROM servicios servicios
   INNER JOIN carrera_area carrera_area
   ON servicios.carrera_area = carrera_area.clave
   WHERE servicios.idservicio = paramID;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.tipoAdministradorBuscar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `tipoAdministradorBuscar`(
	IN `paramDato` VARCHAR(20)
)
BEGIN
   SELECT idtipo_administrador,
   descripcion
   FROM tipo_administrador
   WHERE idtipo_administrador LIKE CONCAT('%', paramDato, '%')
   OR descripcion LIKE CONCAT('%', paramDato, '%')
   AND estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.tipoAdministradorConsultar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `tipoAdministradorConsultar`()
BEGIN
   SELECT idtipo_administrador,
   descripcion
   FROM tipo_administrador
   WHERE estado = 'Alta'
   ORDER BY descripcion ASC;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.tipoAdministradorContar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `tipoAdministradorContar`()
BEGIN
   SELECT COUNT(idtipo_administrador) AS cantidad
   FROM tipo_administrador
   WHERE estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.tipoAdministradorContarBuscado
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `tipoAdministradorContarBuscado`(
	IN `paramDato` VARCHAR(20)
)
BEGIN
   SELECT COUNT(idtipo_administrador) AS cantidad
   FROM tipo_administrador
   WHERE idtipo_administrador LIKE CONCAT('%', paramDato, '%')
   OR descripcion LIKE CONCAT('%', paramDato, '%')
   AND estado = 'Alta';
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.tipoAdministradorEliminar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `tipoAdministradorEliminar`(
	IN `paramID` INT
)
BEGIN
   UPDATE tipo_administrador
   SET estado = 'Baja'
   WHERE idtipo_administrador = paramID;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.tipoAdministradorExiste
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `tipoAdministradorExiste`(
	IN `paramID` INT
)
BEGIN
   SELECT *
   FROM tipo_administrador
   WHERE idtipo_administrador = paramID;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.tipoAdministradorInsertar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `tipoAdministradorInsertar`(
	IN `paramDescripcion` VARCHAR(20)
)
BEGIN
   INSERT INTO tipo_administrador
   (
      descripcion
   )
   VALUES
   (
      paramDescripcion
   );
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.tipoAdministradorModificar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `tipoAdministradorModificar`(
	IN `paramID` INT,
	IN `paramDescripcion` VARCHAR(20)
)
BEGIN
   UPDATE tipo_administrador
   SET descripcion = paramDescripcion
   WHERE idtipo_administrador = paramID;
END//
DELIMITER ;

-- Volcando estructura para procedimiento sirius.tipoAdministradorSeleccionar
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `tipoAdministradorSeleccionar`(
	IN `paramID` INT
)
BEGIN
   SELECT *
   FROM tipo_administrador
   WHERE idtipo_administrador = paramID
   ORDER BY descripcion ASC;
END//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
