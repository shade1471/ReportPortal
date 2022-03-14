package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {


    public DataGenerator() {
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }

    public static String generateDate(int shift) {
        String date = LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity() {
        String[] cities = {"Москва", "Красноярск", "Новосибирск", "Барнаул", "Краснодар",
                "Великий Новгород", "Иваново", "Нижний Новгород", "Благовещенск"};
        String city = cities[new Random().nextInt(cities.length)];
        return city;
    }

    public static String generateDoubleCity() {
        String[] cities = {"Санкт-Петербург", "Йошкар-Ола", "Улан-Удэ", "Ростов-на-Дону", "Горно-Алтайск"};
        String city = cities[new Random().nextInt(cities.length)];
        return city;
    }

    public static String generateLocalCity() {
        String[] cities = {"Северск", "Барабинск", "Кожевниково", "Обь", "Асино"};
        String city = cities[new Random().nextInt(cities.length)];
        return city;
    }

    public static String generateDoubleSurname() {
        String[] names = {"Салтыков-Щедрин", "Шеллер-Михайлов", "Мамин-Сибиряк", "Новиков-Прибой",
                "Бонгард-Левин", "Гулак-Артемовский", "Жан-Жак", "Пьер-Анри", "Шарлъ-Мари-Рене"};
        String name = names[new Random().nextInt(names.length)];
        return name;
    }

    public static String generateName(String locale) {
        String name = new Faker(new Locale(locale)).name().fullName();
        return name;
    }

    public static String generatePhone(String locale) {
        String phone = new Faker(new Locale(locale)).phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            UserInfo user = new UserInfo(DataGenerator.generateCity(), DataGenerator.generateName(locale),
                    DataGenerator.generatePhone(locale));
            return user;
        }
    }

}
