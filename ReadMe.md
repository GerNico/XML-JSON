XML & JSON
----
You need to develop 3 simple classes that represent graphical shapes Triangle, Circle, Square and one Group class which
 will contains any number of graphical shapes including other groups.
Develop set of classes that is responsible for serialization your shapes-tree to XML and JSON format.

Solution
----
According to the task, it was created 3 classes implementing interface Shape. Also, class BoxOfShapes was created, 
this class contains List of Shapes. Class BoxOfShapes was used in order to clarify the difference between composition and aggregation during conversion to JSON/XML.
The conversion was made using java.lang.reflect and recursive visiting of all iterable fields. 
There are two methods, in the class Converter, they make the conversion: 
1 ) String convertToJSON(Object object) 
2 ) String convertToXML(Object object)
Also in this task, I have made big testing class ConverterTest. It helps to discover the behaviour of the converter.
