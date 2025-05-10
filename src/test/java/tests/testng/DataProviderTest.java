package tests.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {

    @Test(dataProvider = "userData")
    public void testDataProvider(User user) {
        System.out.println(user);
    }

    @DataProvider(name = "userData")
    public User[] getUser() {
        User ti = new User("Ti", 20);
        User teo = new User("Teo", 10);
        User tun = new User("Tun", 15);
        return new User[] { ti, teo, tun };
    }

    public static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "User [name=" + name + ", age=" + age + "]";
        }
        
    }
}
