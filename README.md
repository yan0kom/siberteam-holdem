# Ранжирование покерных рук

Определяет самую сильную комбинация в покерной руке. Сортирует руки по силе комбинаций.  
В реализации используется Stream API, Comparator, Optional.  

Build: `mvn clean package`  
Run: `java -jar target/sibertem-holdem-1.0.jar`  
Генерирует 100 случайных покерных рук и выводит их сортированными по убыванию силы комбинаций
