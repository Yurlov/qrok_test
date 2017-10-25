package com.viktor.yurlov.util;


import com.viktor.yurlov.entities.Author;
import com.viktor.yurlov.entities.Book;
import com.viktor.yurlov.entities.Reward;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {

    private static SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
    public static int getAge(Date birthDate)
    {
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) <= birth.get(Calendar.DAY_OF_YEAR))
        {
            age--;
        }
        return age;
    }

    public static List<String> getBookTitle(List<Book> books){
        List<String> titles = new ArrayList<>();
        books.forEach(book -> titles.add(book.getTitle()));
        return titles;
    }



    public static List<Author> addAuthors() throws Exception {

        List<Author> authors = new ArrayList<>();
        Author a1 = new Author("Will","Smith", Author.Sex.MALE,sm.parse("01-02-1992"));
        Author a2 = new Author("Kent","Brock", Author.Sex.MALE,sm.parse("05-05-1977"));
        Author a3 = new Author("Anna","Korochkina", Author.Sex.FEMALE,sm.parse("03-03-1985"));
        Author a4 = new Author("Kate","Svinny", Author.Sex.FEMALE,sm.parse("09-09-1967"));
        Author a5 = new Author("Andrew","Curtovich", Author.Sex.MALE,sm.parse("04-04-1982"));
        authors.add(a1);
        authors.add(a2);
        authors.add(a3);
        authors.add(a4);
        authors.add(a5);
        return authors;
    }

    public static List<Book> fillDB() throws Exception {

       List<Book> books = new ArrayList<>();
       List<Author> authors = addAuthors();
        Book book = new Book("Witcher","5645434243", Book.Genre.FANTASY,authors.subList(1,2));
        Book book1 = new Book("The Lord of the Rings","945374534453", Book.Genre.NOVEL,authors.subList(2,3));
        Book book2 = new Book("Blue Flower","344324543324", Book.Genre.DETECTIVE,authors.subList(3,5));
        books.add(book);
        books.add(book1);
        books.add(book2);
        return books;
    }
    public static Author addReward(Author authors){
        List<Reward> rewards = new ArrayList<>();
        rewards.add(new Reward(2000,"The best author 2000"));
        rewards.add(new Reward(2014,"The best author 2014"));
        rewards.add(new Reward(2009,"The best author 2009"));
        rewards.add(new Reward(2017,"The best author 2017"));
        authors.setRewards(rewards);
        return authors;
    }

}
