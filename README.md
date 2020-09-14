# Java8_Trainee

## Задание 1.
1. Описать класс из любой реальной предметной области (автомобиль, человек, дом и прочие), содержащий несколько полей примитивных и объектных типов.
2. Собрать объекты класса в коллекцию. Для избежания ручной работы при создании объектов (здесь и далее!) применить рандомайзеры.
3. С помощью лямбда-выражений создать два компаратора: простой, сортирующий по одному полю, и сложный, сортирующий по двум полям каскадно (отсортирвать по первому полю, внутри одинаковых категорий - по второму).
4. Вывести результаты в логгер.
5. Имплементировать не менее шести основных функциональных интерфейсов Java 8: Runnable, Consumer и прочие при помощи лямбда-выражений.
6. Создать свой функциональный интефейс и описать три его имплементации для независящих случаев (то есть ситуация, при которой просто изменяется какая-то деталь имплементации, не принимается).
7. Создать один метод по умолчанию и один статический метод в вышеописанном интерфейсе. Логика, описанная в них не должна носить демонстрационный характер (типа вывода в консоль "Default method!").
В ходе выполнения задания также применить ссылки на методы/конструкторы.

## Задание 2.
#### 1. Описать два класса:
"Автор"
- имя;
- возраст;
- список книг (объектов);

"Книга"
- название;
- количество страниц;
- список авторов (объектов);
#### 2. Создать массивы авторов и книг, реальность ситуации Автор-Книга не имеет значение (советую вынести в файл список авторов и список книг, читая который, рандомно создавать объекты).
#### 3. Создать stream'ы для книг и произвести следующие действия:
1) проверить есть ли книги с количеством страниц более 200;
2) проверить все ли книги имеют более 200 страниц;
3) найти книги с наибольшим и наименьшим числом страниц (это можеть быть одна и более книг);
4) найти книги с одним автором;
5) отсортировать книги по количеству страниц, а затем по названию;
6) получить список всех уникальных названий книг;
7) получить уникальный список авторов книг с количеством страниц менее 200.
#### 4. Каждый из пунктов выводить в логгер через forEach.

## Задание 3.
#### 1. Описать класс, позволяющих произвести какие-либо статистические вычисления для промежутка времени (например, измерение температуры, вычисление курса валюты и прочее, то есть одно из полей - дата, второе - целое/дробное числовое значение).
#### 2. Собрать коллекцию объектов класса для покрытия года.
#### 3. С помощью лямбда-выражений и возможностей Stream API описать логику для вычисления:
1) максимального и минимального значения за год + дата;
2) среднего значения за год;
3) максимального и минимального значения в каждом месяце, в логгер выводить в формате "имя месяца (порядок важен) - значение";
4) среднего значения в каждом месяце, в логгер выводить в формате "имя месяца (порядок важен) - значение";
#### 4. Создать коллектор, который соберет сортированную коллекцию дат с максимальным значением числового поля каждого месяца. Коллектор должен иметь возможность корректно работать на параллельном stream'е (также продемонстрировать).
Каждое вычисление выводить в логгер.
