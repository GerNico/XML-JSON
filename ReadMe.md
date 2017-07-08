XML & JSON
----
You need to develop 3 simple classes that represent graphical shapes Triangle, Circle, Square and one Group class which will contains any number of graphical shapes including other groups.
Develop set of classes that is responsible for serialization your shapes-tree to XML and JSON format.

Solution
----
According to the task, it was created 3 classes implementing interface Shape. Also was class BoxOfShapes was created, this class just includes the main method. This method converts to XML & JSON array of shapes.
The conversion was made using java.lang.reflect. It is realised in class Converter by methods: 
* ) String convertToJSON(Object object) 
* ) String convertToXML(Object object)