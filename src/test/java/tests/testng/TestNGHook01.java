package tests.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGHook01 {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("beforeSuite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("\t---> beforeTest");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("\t\t---> beforeClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("\t\t\t---> beforeMethod");
    }

    @Test
    public void testSth1() {
        System.out.println("\t\t\t\t---> TEST 1");
    }

    @Test
    public void testSth2() {
        System.out.println("\t\t\t\t---> TEST 2");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("\t\t\t---> afterMethod");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("\t\t---> afterClass");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("\t---> afterTest");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("afterSuite");
    }

}
