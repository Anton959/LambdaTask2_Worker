import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        System.out.println(persons.stream().filter(age -> age.getAge() < 18).count());

        List<String> familySoldier = persons.stream()
                .filter(sex -> sex.getSex() == Sex.MAN)
                .filter(age -> age.getAge() > 17 && age.getAge() < 28)
                .map(String::valueOf)
                .collect(Collectors.toList());
        System.out.println(familySoldier.toString());

        List<Person> peopleWorker = persons.stream()
                .filter(education -> education.getEducation() == Education.HIGHER)
                .filter(age -> age.getAge() > 18)
                .filter(person -> person.getSex() == Sex.MAN && person.getAge() < 65 ||
                        person.getSex() == Sex.WOMAN && person.getAge() < 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(peopleWorker.toString());
    }
}