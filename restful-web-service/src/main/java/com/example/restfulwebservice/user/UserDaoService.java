package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;
    static {
        users.add(new User(1, "taemin", new Date(), "pass1", "940718-1111111"));
        usersCount++;

        users.add(new User(2, "jimin", new Date(), "pass2", "951003-1111111"));
        usersCount++;

        users.add(new User(3, "joomin", new Date(), "pass3", "020121-1111111"));
        usersCount++;
    }

    public List<User> findAll() {
        return this.users;
    }


    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }

        if (user.getJoinDate() == null) {
            user.setJoinDate(new Date());
        }

        users.add(user);
        return user;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }

    public User update(User user) {
        ListIterator iterator = users.listIterator();

        while (iterator.hasNext()) {
            User target = (User) iterator.next();
            if (target.getId() == user.getId()) {
                iterator.set(user);
                return user;
            }
        }

        return null;
    }
}
