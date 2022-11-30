
Hello CodePaser

*********************************

Textoo

*********************************

docker run --rm -it -v $PWD:/scripts -w /scripts groovy bash

*********************************

println "------------------------------------------------------------------"
println "Hello"
System.getenv().each{
    println it
}
println "------------------------------------------------------------------"

*********************************

def x = 5

x += 5

println x

assert x == 10

*********************************

String name = "Santi"
int courseCount = 14
float salary = 99999.99
Boolean isProgrammer = true

println name + " has created " + courseCount + " courses."
println name + " is a programmer?" + isProgrammer
println name + " wishes his salary was " + salary

*********************************

println name + " wishes his salary was \$" + String.format("%.2f", salary)

*********************************

if (isProgrammer) {
  println "He's a programmer, alright"
} else {
  println "Not a programmer, tho"
}

*********************************

for (int i = 0; i < courseCount; i++) {
  println "Chris made course " + (i + 1) + "!!!"
}

*********************************

while (i < courseCount) {
  println "Chris made course " + (i + 1) + "!!!"
  i++;
}

*********************************

String[] singers = ["Bob", "George", "Jeff", "Roy", "Tom"];

for (String singer: singers) {
  println singer
}

*********************************

singers.each( singer -> println(singer) )

*********************************

singers.each({ println(it) })
singers.each { println(it) }

*********************************

String getUserName(String firstName, String lastName) {
  return firstName.substring(0, 1).toLowerCase() + lastName.toLowerCase();
}

assert getUserName("Santiago", "Camargo") == "scamargo" : "getUserName isn't working"

*********************************

void printCredentials(cred) {
  println "Username is ${cred}"
}
zº
assert getUserName("Santiago", "Camargo") == "scamargo" : "getUserName isn't working"

printCredentials(getUserName("Santiago", "Camargo"))

*********************************

String[] firstNames = ["Ferra", "Dani", "Jordi", "Joan", "Martin"]
String[] lastNames = ["Adriá", "Gracía", "Cruz", "Roca", "Berasategi"]

for (int i = 0; i < firstNames.size(); i++) {
  printCredentials(
    getUserName(firstNames[i], lastNames[i])
  )
}

*********************************
