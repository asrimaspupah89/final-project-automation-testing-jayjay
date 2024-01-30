package stepdefinitions;

import helper.SetUpEndPoint;
import helper.Utility;
import io.cucumber.java.*;

import java.util.Objects;

public class Hooks {
    String tagsRunning = null;

    @BeforeAll
    public static void setUp(){
        System.out.println("Before all test");
        SetUpEndPoint.setUpApi(); //run api in the beginning testing
    }

    @AfterAll
    public static void tearDown(){
        System.out.println("After all test");
    }

    @Before
    public void berforeTest(Scenario scenario){
        System.out.println("Before test each scenario");
        String[] tags = scenario.getSourceTagNames().toArray(new String[0]);
        tagsRunning = tags[0];
        if (Objects.equals(tagsRunning, "@web")) {
            Utility.startDriver();
        }
    }

    @After
    public void afterTest(Scenario scenario) throws InterruptedException{
        System.out.println("After test each scenario");
        if (Objects.equals(tagsRunning, "@web")) {
            Utility.quitDriver();
        }
    }
}
