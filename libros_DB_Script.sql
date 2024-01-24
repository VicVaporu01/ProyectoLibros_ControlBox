-- Create database libros_DB;
use libros_DB
GO

Create Table Usuarios(
	usuario_Id int Primary Key Identity(1,1) not null,
	correo nvarchar(100) not null unique,
	clave nvarchar(100) not null,
	nombre_Usuario nvarchar(100) not null
);
-- drop Table Usuarios;

Create Table Libros(
	libro_Id int Primary Key Identity(1,1) not null,
	titulo nvarchar(100) not null,
	autor nvarchar(100) not null,
	categoria nvarchar(100) not null,
	resumen text not null
);
-- drop Table Libros;

Create Table Opiniones(
	opinion_Id int Primary Key Identity(1,1) not null,
	usuario_Id int not null,
	libro_Id int not null,
	calificacion int check(calificacion >=1 AND calificacion <=5) not null,
	comentario text not null,
	fecha_Opinion date not null
	Foreign Key (usuario_Id) References Usuarios(usuario_Id),
    Foreign Key (libro_Id) References Libros(libro_Id)
);
-- drop Table Opiniones;


Select * from Usuarios;

Select * from Libros;

Select * from Opiniones Order By Opiniones.libro_Id;


-- Datos ejemplo para la tabla Libros
INSERT INTO Libros (titulo, autor, categoria, resumen)
VALUES
    ('Cien a�os de soledad', 'Gabriel Garc�a M�rquez', 'Ficci�n', 'La novela narra la historia de la familia Buend�a a lo largo de siete generaciones en el ficticio pueblo de Macondo. A trav�s de realismo m�gico, Garc�a M�rquez teje una trama en la que lo extraordinario se mezcla con lo cotidiano.'),
    
    ('1984', 'George Orwell', 'Ciencia Ficci�n', 'En un futuro dist�pico, el protagonista Winston Smith lucha contra el r�gimen totalitario de Gran Hermano, que controla cada aspecto de la vida de las personas, incluso sus pensamientos m�s �ntimos.'),
    
    ('Orgullo y prejuicio', 'Jane Austen', 'Cl�sico', 'La novela explora las complejidades de las relaciones sociales y rom�nticas en la Inglaterra del siglo XIX. Elizabeth Bennet, la protagonista, se enfrenta a sus prejuicios mientras encuentra el amor en un mundo marcado por las convenciones sociales.'),
    
    ('El se�or de los anillos', 'J.R.R. Tolkien', 'Fantas�a', 'En la Tierra Media, Frodo Bols�n emprende una peligrosa misi�n para destruir el Anillo �nico y detener al malvado Sauron. A lo largo de la epopeya, se forjan amistades y se libran batallas �picas que determinar�n el destino del mundo.'),
    
    ('Don Quijote de la Mancha', 'Miguel de Cervantes', 'Cl�sico', 'La historia de un hidalgo enloquecido que se cree un caballero andante y su fiel escudero Sancho Panza. A trav�s de las divertidas y a veces melanc�licas aventuras, Cervantes satiriza la sociedad y la literatura de su tiempo.'),
    
    ('Matar a un ruise�or', 'Harper Lee', 'Ficci�n', 'La novela aborda temas de racismo y justicia en el sur de Estados Unidos durante la d�cada de 1930. La historia es narrada por Scout Finch, una ni�a que observa a su padre, el abogado Atticus Finch, defender a un hombre negro acusado injustamente.'),
    
    ('Crimen y castigo', 'Fiodor Dostoievski', 'Cl�sico', 'La obra sigue la vida de Rodion Rask�lnikov, un estudiante que comete un asesinato por razones filos�ficas. La novela explora temas de moralidad, culpa y redenci�n en la sociedad rusa del siglo XIX.'),
    
    ('Dr�cula', 'Bram Stoker', 'Terror', 'La historia del Conde Dr�cula, un vampiro que busca trasladarse de Transilvania a Inglaterra para extender su dominio. El libro utiliza una variedad de formatos, como cartas y diarios, para contar la aterradora historia.'),
    
    ('Crimen en la calle Morgue', 'Edgar Allan Poe', 'Misterio', 'El detective C. Auguste Dupin investiga un brutal asesinato en Par�s. La historia es considerada una de las primeras obras de detectives y estableci� las bases para el g�nero policiaco.'),
    
    ('Moby Dick', 'Herman Melville', 'Aventura', 'El capit�n Ahab persigue obsesivamente a la ballena blanca Moby Dick despu�s de haber perdido una pierna en un encuentro anterior. La novela explora temas de obsesi�n, venganza y la relaci�n entre el hombre y la naturaleza.'),
    
    ('Los miserables', 'Victor Hugo', 'Cl�sico', 'Jean Valjean, un exconvicto, busca la redenci�n mientras es perseguido por el implacable inspector Javert. La novela aborda temas de justicia, amor y redenci�n en la Francia del siglo XIX.'),
    
    ('El gran Gatsby', 'F. Scott Fitzgerald', 'Ficci�n', 'En la d�cada de 1920, Jay Gatsby organiza fiestas extravagantes en su mansi�n mientras busca reconquistar a su antiguo amor, Daisy Buchanan. La novela explora la decadencia del sue�o americano durante la era del jazz.'),
    
    ('Anna Karenina', 'Le�n Tolst�i', 'Cl�sico', 'La historia de Anna Karenina, una mujer casada que se enamora del apuesto oficial Conde Vronsky. La novela aborda temas de amor, matrimonio, moralidad y la alta sociedad rusa del siglo XIX.'),
    
    ('El guardi�n entre el centeno', 'J.D. Salinger', 'Ficci�n', 'El adolescente Holden Caulfield narra sus experiencias en Nueva York mientras trata de encontrar su lugar en el mundo. La novela se ha convertido en un cl�sico de la literatura juvenil y aborda temas de alienaci�n y la b�squeda de la autenticidad.'),
    
    ('Mujercitas', 'Louisa May Alcott', 'Cl�sico', 'La historia sigue las vidas de las hermanas March mientras enfrentan desaf�os y crecen durante y despu�s de la Guerra Civil estadounidense. La novela celebra los lazos familiares y la fortaleza de las mujeres.'),
    
    ('Frankenstein', 'Mary Shelley', 'Ciencia Ficci�n', 'El cient�fico Victor Frankenstein crea un ser vivo a partir de partes de cad�veres. La criatura, abandonada por su creador, busca comprensi�n y venganza. La novela es considerada una obra maestra del horror g�tico.'),
    
    ('En el camino', 'Jack Kerouac', 'Beat Generation', 'La novela sigue los viajes y experiencias de Sal Paradise y Dean Moriarty a trav�s de Estados Unidos. Kerouac captura la esencia de la generaci�n beat y la b�squeda de la libertad y la autenticidad.'),
    
    ('Cr�nica de una muerte anunciada', 'Gabriel Garc�a M�rquez', 'Ficci�n', 'La historia de un asesinato anunciado en un peque�o pueblo latinoamericano. Garc�a M�rquez juega con la estructura narrativa para explorar la inevitabilidad del destino y la naturaleza humana.'),
    
    ('El retrato de Dorian Gray', 'Oscar Wilde', 'Ficci�n', 'Dorian Gray, un joven apuesto, vende su alma para preservar su belleza mientras su retrato envejece. La novela explora la decadencia moral y la b�squeda obsesiva de la juventud eterna.'),
    
    ('El nombre de la rosa', 'Umberto Eco', 'Misterio', 'En una abad�a medieval, el monje Guillermo de Baskerville investiga una serie de misteriosos asesinatos. La novela combina el thriller hist�rico con la reflexi�n sobre la naturaleza del conocimiento y la fe.');
