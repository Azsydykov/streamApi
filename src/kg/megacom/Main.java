package kg.megacom;

import kg.megacom.enums.Classification;

import javax.swing.event.ListDataEvent;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Animal> animals = getAnimals();

        // старый подход

//        List<Animal> predators = new ArrayList<>();
//
//        for (Animal animal : animals) {
//            if (animal.getClassification().equals(Classification.PREDATOR)) {
//                predators.add(animal);
//            }
//        }
        //   predators.forEach(System.out::println);

        // новый подход
        //filter
        List<Animal> predators = animals.stream().filter(animal -> animal.getClassification()
                .equals(Classification.PREDATOR)).collect(Collectors.toList());
        //  predators.forEach(System.out::println);

        //sort

        List<Animal> sorted = animals.stream().sorted(Comparator.comparing(Animal::getAge))
                .collect(Collectors.toList());
        //  sorted.forEach(System.out::println);

        // allMatch

        boolean allMatch = animals.stream().allMatch(animal -> animal.getAge() > 10);
        // System.out.println(allMatch);

        // anyMatch


        boolean anyMatch = animals.stream().anyMatch(animal -> animal.getName().equals("Медведь"));
        //   System.out.println(anyMatch);

        //None Match

        boolean noneMatch = animals.stream().noneMatch(animal -> animal.getName().equals("Слон"));
        //    System.out.println(noneMatch);

        // max

//        animals.stream()
//                .max(Comparator.comparing(Animal::getAge))
//                .ifPresent((System.out::println));

//        animals.stream()
//                .min(Comparator.comparing(Animal::getAge))
//                .ifPresent((System.out::println));

        // Group

        Map<Classification, List<Animal>> classificationListMap = animals.stream()
                .collect(Collectors.groupingBy(Animal::getClassification));
        classificationListMap.forEach((classification, animals1) -> {

//            System.out.println(classification);
//            animals1.forEach(System.out::println);
//            System.out.println();
        });

        // Находим самого старого хищника

        Optional<String> oldestAnimal =  animals.stream()
                .filter(animal -> animal.getClassification().equals(Classification.PREDATOR))
                .max(Comparator.comparing(Animal::getAge))
                .map(Animal::getName);
        oldestAnimal.ifPresent(System.out::println);



    }

    private static List<Animal> getAnimals() {
        return Arrays.asList(
                new Animal("Слон", 20, Classification.HERBIVORE),
                new Animal("Лев", 10, Classification.PREDATOR),
                new Animal("Гиена", 11, Classification.PREDATOR),
                new Animal("Жираф", 7, Classification.HERBIVORE),
                new Animal("Гибон", 35, Classification.OMNIVOROUS),
                new Animal("Лощадь", 36, Classification.HERBIVORE),
                new Animal("Рысь", 2, Classification.PREDATOR),
                new Animal("Динозавр", 200, Classification.PREDATOR));
    }
}
