CREATE DATABASE bdPizzaHut
GO

USE bdPizzaHut
GO
-- tables
-- Table: CLIENTE
CREATE TABLE CLIENTE (
    IDCLI int  NOT NULL IDENTITY(1, 1),
    NOMCLI Varchar(30)  NOT NULL,
    APECLI Varchar(30)  NOT NULL,
    EMACLI Varchar(60)  NOT NULL,
    CELCLI char(9)  NOT NULL,
    DNICLI char(8)  NOT NULL,
    DIRCLI Varchar(60)  NOT NULL,
    ESTCLI char(1)  NOT NULL,
    FECNACCLI char(10)  NOT NULL,
    CODUBI char(6)  NOT NULL,
    IDLOG int  NOT NULL,
    CONSTRAINT CLIENTE_pk PRIMARY KEY  (IDCLI)
);
Go

INSERT INTO CLIENTE
(NOMCLI ,APECLI ,EMACLI ,CELCLI,DNICLI ,DIRCLI, ESTCLI,FECNACCLI,CODUBI,IDLOG)
VALUES
('Pepe Juan','Castro Sanchez','p.castrosanchez@gmail.com','975364937', '15347765','Av. 28 de Julio N° 100','A','13-03-1999','140406',6),
('Juana Yuliana','Tacza Saavedra','j.tacza@gmail.com','999364937', '15347766','Av. 2 de mayo','A','14-04-2000','140406',11),
('Jenny','Urdaneta Alfaro','j.urdaneta@gmail.com','975364938', '15347767','Av. ramos larrea N° 101','A','13-04-1999','140406',12),
('Josimar','Bernal Casas','j.bernal@gmail.com','975364939', '15347768','Av. sucre N° 90','A','13-03-1999','140406',13),
('Rocio','Malpartida Osorio','r.malpartida@gmail.com','975364939', '15347769','Av. ramos N° 800','A','13-03-1999','140406',14),
('Lady','Ramos Ramos','l.ramos@gmail.com','975364940', '15347770','Av. Ayacucho N° 190','A','13-03-1999','140410',15),
('Alexis Raul','Flores Sanchez','a.flores@gmail.com','975364941', '15347771','Av. la mar N° 200','A','13-03-1999','140410',16),
('Ronny Raul','Flores Sanchez','r.flores@gmail.com','975364942', '15347772','Av. Canada N° 100','A','13-03-1999','140409',17),
('Lisset Karina','Ciprian Sanchez','l.ciprian@gmail.com','975364900', '15347773','Av. ayacucho N° 140','A','13-03-1999','140409',18),
('Ignacio Ernesto','Sulca Pomalaya','i.sulca@gmail.com','975364901', '15347774','Av. principal N° 401','A','13-03-1999','140409',19)
Go

-- Table: LOGINX
CREATE TABLE LOGINX (
    IDLOG int  NOT NULL IDENTITY(1, 1),
    USUARIO Varchar(20)  NOT NULL,
    CLAVE Varchar(20)  NOT NULL,
    ROL char(1)  NOT NULL,
    CONSTRAINT LOGINX_pk PRIMARY KEY  (IDLOG)
);
GO

INSERT INTO LOGINX
(USUARIO,CLAVE,ROL)
VALUES
('USUARIO1','123456','V'),
('USUARIO2','123456','V'),
('USUARIO3','123456','V'),
('USUARIO4','123456','V'),
('USUARIO5','123456','V'),
('USUARIO6','123456','C'),
('USUARIO7','123456','V'),
('USUARIO8','123456','A'),
('USUARIO9','123456','J'),
('USUARIO10','123456','D'),
('USUARIO11','123456','C'),
('USUARIO12','123456','C'),
('USUARIO13','123456','C'),
('USUARIO14','123456','C'),
('USUARIO15','123456','C'),
('USUARIO16','123456','C'),
('USUARIO17','123456','C'),
('USUARIO18','123456','C'),
('USUARIO19','123456','C'),
('USUARIO20','123456','V'),
('USUARIO20','123456','V'),
('USUARIO21','123456','V'),
('USUARIO22','123456','V'),
('USUARIO23','123456','V'),
('USUARIO24','123456','V'),
('USUARIO25','123456','V'),
('USUARIO26','123456','V'),
('USUARIO27','123456','V'),
('USUARIO28','123456','V'),
('USUARIO29','123456','V'),
('USUARIO30','123456','V');
GO



-- Table: PRODUCTO
CREATE TABLE PRODUCTO (
    CODPRO char(5)  NOT NULL,
    TIPPRO char(1)  NOT NULL,
    NOMPRO Varchar(25)  NOT NULL,
    PREPRO decimal(8,2)  NOT NULL,
    TAMPRO Varchar(10)  NOT NULL,
    STOCKPRO int  NOT NULL,
    ESTPRO char(1)  NOT NULL,
    DETPRO Varchar(100)  NOT NULL,
    CONSTRAINT PRODUCTO_pk PRIMARY KEY  (CODPRO)
);
Go

INSERT INTO PRODUCTO
(CODPRO ,TIPPRO ,NOMPRO, PREPRO , TAMPRO , STOCKPRO ,ESTPRO,DETPRO)
VALUES
('P0001','P','PIZZA CARBONARA PASSIONE',40,'F',150,'A','La tradicional receta carbonara con jamón, tocino, champiñones y salsa alfredo.'),
('P0002','P','PIZZA CHICKEN AMORE',15,'I',150,'A','Una pizza con pollo a la parilla, tomates frescos y cebolla morada, con un toque de salsa alfredo.'),
('P0003','P','PIZZA PEPPERONI ROMANZZA',70,'X',150,'A','De pepperoni, acompañado con tomates frescos y champiñones .'),
('P0004','P','4 ESTACIONES',32,'F',150,'M','Pizza: súper suprema, canadiense, pepperoni y jamón.'),
('P0005','P','SUPER SUPREMA',45,'F',150,'F','Hecho con pepperoni, carne de res y cerdo.'),
('P0006','P','CANADIENSE',48,'F',150,'F','Elaborada con pepperoni, jamón, carne de res y cerdo, salchicha italiana y bacon bits..'),
('P0007','P','SUPREMA',51,'F',150,'F','Elaborada con pepperoni, carne de res y cerdo, champiñones, chile verde y cebolla.'),
('P0008','P','PEPPERONI O JAMÓN LOVERS',39,'F',150,'A','Pepperoni o jamón en abundancia, queso 100% mozzarella.');
Go



-- Table: SUCURSAL
CREATE TABLE SUCURSAL (
    IDSU int  NOT NULL IDENTITY(1, 1),
    NOMSU Varchar(60)  NOT NULL,
    DIRSU Varchar(60)  NOT NULL,
    CODUBI char(6)  NOT NULL,
    CONSTRAINT SUCURSAL_pk PRIMARY KEY  (IDSU)
);
GO

INSERT INTO SUCURSAL
(NOMSU,DIRSU,CODUBI)
VALUES
('Pizza Hut Mega plaza','Benavides n° 154','140410'),
('Pizza Hut Imperial','2 de mayo n° 754','140406'),
('Pizza Hut - Nuevo Imperial', 'Av. principal n° 474', '140409');
Go

-- Table: UBIGEO
CREATE TABLE UBIGEO (
    CODUBI char(6)  NOT NULL,
    DEPA Varchar(20)  NOT NULL,
    PROV Varchar(20)  NOT NULL,
    DIST Varchar(20)  NOT NULL,
    CONSTRAINT UBIGEO_pk PRIMARY KEY  (CODUBI)
);
Go

INSERT INTO UBIGEO
(CODUBI,DEPA,PROV,DIST)
VALUES
('140402','LIMA','CAÑETE','CALANGO'),
('140403','LIMA','CAÑETE','CERRO AZUL'),
('140404','LIMA','CAÑETE','CHILCA'),
('140405','LIMA','CAÑETE','COAYLLO'),
('140406','LIMA','CAÑETE','IMPERIAL'),
('140407','LIMA','CAÑETE','LUNAHUANA'),
('140408','LIMA','CAÑETE','MALA'),
('140409','LIMA','CAÑETE','NUEVO IMPERIAL'),
('140410','LIMA','CAÑETE','SAN VICENTE');
GO

-- Table: VENDEDOR
CREATE TABLE VENDEDOR (
    IDVEND int  NOT NULL IDENTITY(1, 1),
    NOMVEND Varchar(30)  NOT NULL,
    APEVEND Varchar(30)  NOT NULL,
    EMAVEND Varchar(60)  NOT NULL,
    CELVEND char(9)  NOT NULL,
    DNIVEND char(8)  NOT NULL,
    DIRVEND Varchar(60)  NOT NULL,
    ESTVEND char(1)  NOT NULL,
    FECNACVEND char(10)  NOT NULL,
    CODUBI char(6)  NOT NULL,
    IDLOG int  NOT NULL,
    CONSTRAINT VENDEDOR_pk PRIMARY KEY  (IDVEND)
);
Go

INSERT INTO VENDEDOR
(NOMVEND ,APEVEND ,EMAVEND ,CELVEND,DNIVEND,DIRVEND ,ESTVEND, FECNACVEND,CODUBI,IDLOG)
VALUES
('Juan Pepe','Sanchez Castro','juan.sanchez@gmail.com','991932288', '70782063','Av. 28 de Julio N° 150','A','13-03-1999','140406',20),
('Yuliana Juana','Saavedra Tacza','y.saavedra@gmail.com','991932289', '70782064','Av. 2 de mayo','A','14-04-2000','140406',21),
('Jenny','Alfaro Urdaneta','j.alfaro@gmail.com','991932290', '70782065','Av. ramos larrea N° 101','A','13-04-1999','140406',22),
('Josimar','Bernal Casas','j.bernal@gmail.com','991932291', '70782066','Av. sucre N° 90','A','13-03-1999','140406',23),
('Rocio','Osorio Malpartida','r.osorio@gmail.com','991932292', '70782067','Av. ramos N° 800','A','13-03-1999','140406',24),
('Marta','Ramos Ramos','m.ramos@gmail.com','991932293', '70782068','Av. Ayacucho N° 190','A','13-03-1999','140410',25),
('Raul Alexis','Sanchez Flores','r.sanchez@gmail.com','991932294', '70782069','Av. la mar N° 200','A','13-03-1999','140410',26),
('Raul Ronny','Sanchez Flores ','r.sanchez@gmail.com','991932295', '70782070','Av. Canada N° 100','A','13-03-1999','140409',27),
('Karina Lisset','Sanchez Ciprian','k.sanchez@gmail.com','991932296', '70782071','Av. ayacucho N° 140','A','13-03-1999','140409',28),
('Ernesto Ignacio','Pomalaya Sulca','e.pomalaya@gmail.com','991932297', '70782072','Av. principal N° 401','A','13-03-1999','140409',29);
Go

-- Table: VENTA
CREATE TABLE VENTA (
    IDVEN int  NOT NULL IDENTITY(1, 1),
    FECVEN char(10)  NOT NULL,
    IDCLI int  NOT NULL,
    IDSU int  NOT NULL,
    IDVEND int  NOT NULL,
    TIPVEN char(1)  NOT NULL,
    CONSTRAINT VENTA_pk PRIMARY KEY  (IDVEN)
);
Go

INSERT INTO VENTA
(FECVEN,IDCLI,IDSU,IDVEND,TIPVEN)
VALUES
('08-07-2022',1,1,10,'P'),
('08-07-2022',2,2,9,'P'),
('08-07-2022',3,3,8,'P'),
('08-07-2022',4,1,7,'P'),
('08-07-2022',5,2,6,'P'),
('08-07-2022',6,3,5,'D'),
('08-07-2022',7,1,4,'D'),
('08-07-2022',8,2,3,'D'),
('08-07-2022',9,3,2,'D'),
('08-07-2022',10,1,1,'D');
Go

-- Table: VENTA_DETALLE
CREATE TABLE VENTA_DETALLE (
    IDVENDET int  NOT NULL IDENTITY(1, 1),
    CANTPRO int  NOT NULL,
    CODPRO char(5)  NOT NULL,
    IDVEN int  NOT NULL,
    CONSTRAINT VENTA_DETALLE_pk PRIMARY KEY  (IDVENDET)
);
Go


INSERT INTO VENTA_DETALLE
(CANTPRO,CODPRO,IDVEN)
VALUES
(2,'P0001',1),
(4,'P0002',2),
(6,'P0003',3),
(8,'P0004',4),
(10,'P0005',5),
(8,'P0006',6),
(6,'P0007',7),
(4,'P0008',8);
Go



-- foreign keys
-- Reference: CLIENTE_LOGIN (table: CLIENTE)
ALTER TABLE CLIENTE ADD CONSTRAINT CLIENTE_LOGIN
    FOREIGN KEY (IDLOG)
    REFERENCES LOGINX (IDLOG);

-- Reference: CLIENTE_UBIGEO (table: CLIENTE)
ALTER TABLE CLIENTE ADD CONSTRAINT CLIENTE_UBIGEO
    FOREIGN KEY (CODUBI)
    REFERENCES UBIGEO (CODUBI);

-- Reference: SUCURSAL_UBIGEO (table: SUCURSAL)
ALTER TABLE SUCURSAL ADD CONSTRAINT SUCURSAL_UBIGEO
    FOREIGN KEY (CODUBI)
    REFERENCES UBIGEO (CODUBI);

-- Reference: VENDEDOR_LOGIN (table: VENDEDOR)
ALTER TABLE VENDEDOR ADD CONSTRAINT VENDEDOR_LOGIN
    FOREIGN KEY (IDLOG)
    REFERENCES LOGINX (IDLOG);

-- Reference: VENDEDOR_UBIGEO (table: VENDEDOR)
ALTER TABLE VENDEDOR ADD CONSTRAINT VENDEDOR_UBIGEO
    FOREIGN KEY (CODUBI)
    REFERENCES UBIGEO (CODUBI);

-- Reference: VENTA_CLIENTE (table: VENTA)
ALTER TABLE VENTA ADD CONSTRAINT VENTA_CLIENTE
    FOREIGN KEY (IDCLI)
    REFERENCES CLIENTE (IDCLI);

-- Reference: VENTA_DETALLE_PRODUCTO (table: VENTA_DETALLE)
ALTER TABLE VENTA_DETALLE ADD CONSTRAINT VENTA_DETALLE_PRODUCTO
    FOREIGN KEY (CODPRO)
    REFERENCES PRODUCTO (CODPRO);

-- Reference: VENTA_DETALLE_VENTA (table: VENTA_DETALLE)
ALTER TABLE VENTA_DETALLE ADD CONSTRAINT VENTA_DETALLE_VENTA
    FOREIGN KEY (IDVEN)
    REFERENCES VENTA (IDVEN);

-- Reference: VENTA_SUCURSAL (table: VENTA)
ALTER TABLE VENTA ADD CONSTRAINT VENTA_SUCURSAL
    FOREIGN KEY (IDSU)
    REFERENCES SUCURSAL (IDSU);

-- Reference: VENTA_VENDEDOR (table: VENTA)
ALTER TABLE VENTA ADD CONSTRAINT VENTA_VENDEDOR
    FOREIGN KEY (IDVEND)
    REFERENCES VENDEDOR (IDVEND);
Go


create view vproductos as 
select p.NOMPRO as Pizzas , s.NOMSU as Sucursal from PRODUCTO p
inner join VENTA_DETALLE vd on vd.CODPRO = p.CODPRO
inner join venta v on v.IDVEN = vd.IDVEN
inner join SUCURSAL s on s.IDSU = v.IDSU;

Go
Create view vDetalle as
select 
v.IDVEN,NOMSU as SUCURSAL, concat(c.NOMCLI,' , ',c.APECLI) as Cliente,
concat(vend.NOMVEND,' , ',vend.APEVEND) as Vendedor,
p.NOMPRO as Pizza , p.PREPRO as precio ,vd.CANTPRO as cantidad , total=p.PREPRO*vd.CANTPRO
from PRODUCTO p
inner join VENTA_DETALLE vd on vd.CODPRO = p.CODPRO
inner join venta v on v.IDVEN = vd.IDVEN
inner join SUCURSAL s on s.IDSU = v.IDSU
inner join UBIGEO u on u.CODUBI = s.CODUBI
inner join Cliente c on c.IDCLI = v.IDCLI
inner join VENDEDOR vend on vend.IDVEND = v.IDVEND
Go

-- PROCEDURE ACTUALIZAR PRODUCTO
CREATE PROCEDURE spUpdateProducto
        (
              @NUEVACANT int,
              @CODPRO char(5)
        )
AS
  BEGIN
         SET NOCOUNT ON
        BEGIN TRAN /* TRANSACCIONES */
        BEGIN TRY
		
               IF(@NUEVACANT=0) BEGIN 
                       UPDATE PRODUCTO SET STOCKPRO=@NUEVACANT , ESTPRO='I' WHERE CODPRO=@CODPRO
                       COMMIT TRAN
                   END
                   --END  --<-- This End


                ELSE IF (@NUEVACANT <0) 
                   BEGIN
                    ROLLBACK TRAN;
                   END


                ELSE BEGIN   
                    IF (SELECT ESTPRO FROM PRODUCTO WHERE CODPRO=@CODPRO)='I'
                       BEGIN
                       UPDATE PRODUCTO SET STOCKPRO=@NUEVACANT, ESTPRO='A' WHERE CODPRO=@CODPRO
                       END

                    ELSE BEGIN
                       UPDATE PRODUCTO SET STOCKPRO=@NUEVACANT WHERE CODPRO=@CODPRO
                       END
                    COMMIT TRAN
                 END

         END TRY
         BEGIN CATCH
              SELECT 'Ingreso los datos incorrectamentes' AS ERROR
              IF @@TRANCOUNT > 0 ROLLBACK TRAN;
         END CATCH
    END
GO
