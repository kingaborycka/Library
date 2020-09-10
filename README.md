pomysł projektu - https://4programmers.net/Forum/Java/245524-program_bibliotekaproblem

1.Podstawowe założenia
Napisać program (fragment systemu) do obsługi biblioteki. Program powinien umoż-
liwić przechowywanie i zarządzanie danymi o książkach należących do biblioteki, w tym
również informacjami o wypożyczeniach książek.
Dane o książce przechowywane powinny być w klasie MyBook. Klasa ta powinna zawierać
następujące składowe (w ostatnim nawiasie przy każdym polu podano typ danych
dla pola):

• id – unikalny identyfikator egzemplarza książki (int);

• tytul – tytuł książki, nie może być pusty (String);

• nazwiskoAutora – nie może być puste (String);

• imionaAutora – imiona autora oddzielone znakiem spacji (String);

• rok – rok wydania książki

• kategorie – lista kategorii tematycznych, do których przynależy książka, np. ”powieść
historyczna;dramat;literatura piękna”. Kategorie powinny być oddzielone średnikami.
Każda książka powinna należeć do co najmniej jednej kategorii (String);

• czyWypozyczona – określa, czy książka jest obecnie wypożyczona: true – tak, false
– nie (boolean);

• liczbaWypozyczen – zlicza ile razy książka została wypożyczona i zwrócona (!) do
biblioteki (int).
Listą książek należących do biblioteki zarządzać powinna klasa MyLibrary zawierająca
następujące składowe:

• ksiazki – tablica obiektów typu MyBook reprezentujących książki w bibliotece. Moż-
na założyć, że maksymalna liczba książek w bibliotece nie przekroczy 1000 sztuk;

• ileKsiazek – ile książek jest obecnie w bibliotece (int).

1.1 Funkcjonalność programu
Funkcje programu powinny obejmować:

Wyświetlanie listy książek w formacie skróconym składającym się z identyfikatora,
tytułu, inicjałów autora i jego nazwiska oraz informacji, czy książka jest obecnie
wypożyczona (tak/nie), np.:
123 H.A. Sienkiewicz ,,W pustyni i w puszczy’’ tak
Wyświetlanie listy książek w formacie pełnym zawierającym wszystkie dane o książ-
kach ;

Dodawanie książek do biblioteki – użytkownik proszony jest o podanie tytułu, imion
i nazwiska autora, roku wydania oraz kategorii tematycznych, do których należy
książka, pozostałe pola ustalane są automatycznie;

Edycja danych o książce (na wypadek błędów w opisie) – możliwość zmiany warto-
ści wybranej przez użytkownika składowej opisu spośród: imion i nazwiska autora,
tytułu książki, roku wydania oraz kategorii tematycznych. Książka do edycji wybierana
jest na podstawie podanego identyfikatora. Przed rozpoczęciem edycji powinny
zostać wyświetlone informacje o edytowanej książce.

Wypożyczanie książek – po podaniu identyfikatora książka zostaje oznaczona jako
wypożyczona;

Zwrot książki – po podaniu identyfikator książka zostaje oznaczona jako oddana
(licznik wypożyczeń powinien zostać uaktualniony);

Wyszukiwanie książek na podstawie wybranego kryterium:

• nazwiska autora,

• tytułu,

• kategorii tematycznej.

Dla dwóch pierwszych wyszukiwanie powinno uwzględniać częściowe dopasowanie,
gdy użytkownik poda jedynie fragment nazwiska autora lub tytułu. Dopasowanie
po kategorii powinno być dokładne.
Funkcje dodatkowe z osobnym podmenu:

(a) Wyświetlanie liczby wszystkich książek, liczby obecnie wypożyczonych oraz
całkowitej liczby wypożyczeń.

(b) Wyświetlanie 5 najczęściej wypożyczanych egzemplarzy książek w formacie
skróconym (tylko, gdy liczba wypożyczeń > 0). Jeżeli więcej niż 5 egzemplarzy
ma tę samą liczbę wypożyczeń, to również powinny zostać wyświetlone.

(c) Wyświetlanie 5 najbardziej poczytnych książek z każdej kategorii tematycznej
(tylko, gdy liczba wypożyczeń > 0). Lista ta powinna sumować wypożyczenia
dla wszystkich egzemplarzy danej pozycji wydawniczej, tj. o tym samym tytule
oraz o tym samym autorze, ponieważ w bibliotece może znajdować się więcej,
niż jeden egzemplarz tej samej książki.

(d) Wyświetlanie 5 najbardziej poczytnych autorów – liczba wypożyczeń książek
autora powinna być > 0. Dla każdego autora podać tytuł jego najbardziej
popularnej książki (kryteria takie jak w poprzednim punkcie).
*Import danych z pliku tekstowego – umożliwia dodanie do biblioteki nowych egzemplarzyksiążek na podstawie opisów (po jednym w wierszu) w pliku tekstowym
w formacie, jak w przykładzie:
Henryk Adam, Sienkiewicz; Quo vadis; 1995; powieść historyczna, literatura
piękna
*Wyświetlanie listy książek posortowanej wg wybranego kryterium spośród: nazwiska
autora, roku wydania, liczby wypożyczeń, tytułu.
2Dostęp do poszczególnych funkcji programu powinien być realizowany przez menu
wyświetlane po uruchomieniu programu oraz po każdorazowym zakończeniu wykonywania
wybranej wcześniej funkcji.

Każda z funkcjonalności programu powinna być implementowana przez odrębną metodę
z klas Ksiazka oraz Biblioteka. Dane przechowywane powinny być w pliku o nazwie
„biblioteka.dat” odczytywanym automatycznie po uruchomieniu programu. Jeżeli plik z
danymi nie istnieje, to powinien zostać utworzony automatycznie (pusty). Po każdej modyfikacji
danych należy je zapisać do wspomnianego pliku, tak aby były widoczne po
ponownym uruchomieniu programu.
Wprowadzane przez użytkownika dane powinny być weryfikowane i jeżeli będą błędne,
to program powinien prosić o ich ponowne podanie.