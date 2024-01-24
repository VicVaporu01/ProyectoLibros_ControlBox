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
    ('Cien años de soledad', 'Gabriel García Márquez', 'Ficción', 'La novela narra la historia de la familia Buendía a lo largo de siete generaciones en el ficticio pueblo de Macondo. A través de realismo mágico, García Márquez teje una trama en la que lo extraordinario se mezcla con lo cotidiano.'),
    
    ('1984', 'George Orwell', 'Ciencia Ficción', 'En un futuro distópico, el protagonista Winston Smith lucha contra el régimen totalitario de Gran Hermano, que controla cada aspecto de la vida de las personas, incluso sus pensamientos más íntimos.'),
    
    ('Orgullo y prejuicio', 'Jane Austen', 'Clásico', 'La novela explora las complejidades de las relaciones sociales y románticas en la Inglaterra del siglo XIX. Elizabeth Bennet, la protagonista, se enfrenta a sus prejuicios mientras encuentra el amor en un mundo marcado por las convenciones sociales.'),
    
    ('El señor de los anillos', 'J.R.R. Tolkien', 'Fantasía', 'En la Tierra Media, Frodo Bolsón emprende una peligrosa misión para destruir el Anillo Único y detener al malvado Sauron. A lo largo de la epopeya, se forjan amistades y se libran batallas épicas que determinarán el destino del mundo.'),
    
    ('Don Quijote de la Mancha', 'Miguel de Cervantes', 'Clásico', 'La historia de un hidalgo enloquecido que se cree un caballero andante y su fiel escudero Sancho Panza. A través de las divertidas y a veces melancólicas aventuras, Cervantes satiriza la sociedad y la literatura de su tiempo.'),
    
    ('Matar a un ruiseñor', 'Harper Lee', 'Ficción', 'La novela aborda temas de racismo y justicia en el sur de Estados Unidos durante la década de 1930. La historia es narrada por Scout Finch, una niña que observa a su padre, el abogado Atticus Finch, defender a un hombre negro acusado injustamente.'),
    
    ('Crimen y castigo', 'Fiodor Dostoievski', 'Clásico', 'La obra sigue la vida de Rodion Raskólnikov, un estudiante que comete un asesinato por razones filosóficas. La novela explora temas de moralidad, culpa y redención en la sociedad rusa del siglo XIX.'),
    
    ('Drácula', 'Bram Stoker', 'Terror', 'La historia del Conde Drácula, un vampiro que busca trasladarse de Transilvania a Inglaterra para extender su dominio. El libro utiliza una variedad de formatos, como cartas y diarios, para contar la aterradora historia.'),
    
    ('Crimen en la calle Morgue', 'Edgar Allan Poe', 'Misterio', 'El detective C. Auguste Dupin investiga un brutal asesinato en París. La historia es considerada una de las primeras obras de detectives y estableció las bases para el género policiaco.'),
    
    ('Moby Dick', 'Herman Melville', 'Aventura', 'El capitán Ahab persigue obsesivamente a la ballena blanca Moby Dick después de haber perdido una pierna en un encuentro anterior. La novela explora temas de obsesión, venganza y la relación entre el hombre y la naturaleza.'),
    
    ('Los miserables', 'Victor Hugo', 'Clásico', 'Jean Valjean, un exconvicto, busca la redención mientras es perseguido por el implacable inspector Javert. La novela aborda temas de justicia, amor y redención en la Francia del siglo XIX.'),
    
    ('El gran Gatsby', 'F. Scott Fitzgerald', 'Ficción', 'En la década de 1920, Jay Gatsby organiza fiestas extravagantes en su mansión mientras busca reconquistar a su antiguo amor, Daisy Buchanan. La novela explora la decadencia del sueño americano durante la era del jazz.'),
    
    ('Anna Karenina', 'León Tolstói', 'Clásico', 'La historia de Anna Karenina, una mujer casada que se enamora del apuesto oficial Conde Vronsky. La novela aborda temas de amor, matrimonio, moralidad y la alta sociedad rusa del siglo XIX.'),
    
    ('El guardián entre el centeno', 'J.D. Salinger', 'Ficción', 'El adolescente Holden Caulfield narra sus experiencias en Nueva York mientras trata de encontrar su lugar en el mundo. La novela se ha convertido en un clásico de la literatura juvenil y aborda temas de alienación y la búsqueda de la autenticidad.'),
    
    ('Mujercitas', 'Louisa May Alcott', 'Clásico', 'La historia sigue las vidas de las hermanas March mientras enfrentan desafíos y crecen durante y después de la Guerra Civil estadounidense. La novela celebra los lazos familiares y la fortaleza de las mujeres.'),
    
    ('Frankenstein', 'Mary Shelley', 'Ciencia Ficción', 'El científico Victor Frankenstein crea un ser vivo a partir de partes de cadáveres. La criatura, abandonada por su creador, busca comprensión y venganza. La novela es considerada una obra maestra del horror gótico.'),
    
    ('En el camino', 'Jack Kerouac', 'Beat Generation', 'La novela sigue los viajes y experiencias de Sal Paradise y Dean Moriarty a través de Estados Unidos. Kerouac captura la esencia de la generación beat y la búsqueda de la libertad y la autenticidad.'),
    
    ('Crónica de una muerte anunciada', 'Gabriel García Márquez', 'Ficción', 'La historia de un asesinato anunciado en un pequeño pueblo latinoamericano. García Márquez juega con la estructura narrativa para explorar la inevitabilidad del destino y la naturaleza humana.'),
    
    ('El retrato de Dorian Gray', 'Oscar Wilde', 'Ficción', 'Dorian Gray, un joven apuesto, vende su alma para preservar su belleza mientras su retrato envejece. La novela explora la decadencia moral y la búsqueda obsesiva de la juventud eterna.'),
    
    ('El nombre de la rosa', 'Umberto Eco', 'Misterio', 'En una abadía medieval, el monje Guillermo de Baskerville investiga una serie de misteriosos asesinatos. La novela combina el thriller histórico con la reflexión sobre la naturaleza del conocimiento y la fe.');
