package wp.lab.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddOrEditCourse extends AbstractPage {

    private WebElement name;
    private WebElement description;
    private WebElement students;
    private WebElement teachers;
    private WebElement submit;

    public AddOrEditCourse(WebDriver driver) {
        super(driver);
    }

    public static CoursesPage addCourse(WebDriver driver, String name, String description, String students, String teachers) {
        get(driver, "/courses/add-form");
        AddOrEditCourse addOrEditCourse = PageFactory.initElements(driver, AddOrEditCourse.class);
        addOrEditCourse.name.sendKeys(name);
        addOrEditCourse.description.sendKeys(description);
        addOrEditCourse.teachers.click();
        addOrEditCourse.teachers.findElement(By.xpath("//option[. = '" + teachers + "']")).click();
        addOrEditCourse.students.click();
        addOrEditCourse.students.findElement(By.xpath("//option[. = '" + students + "']")).click();

        addOrEditCourse.submit.click();
        return PageFactory.initElements(driver, CoursesPage.class);
    }

    public static CoursesPage editCourses(WebDriver driver,WebElement editButton, String name, String description, String students,String teachers) {

        editButton.click();
        System.out.println(driver.getCurrentUrl());
        AddOrEditCourse addOrEditCourse = PageFactory.initElements(driver, AddOrEditCourse.class);
        addOrEditCourse.name.sendKeys(name);
        addOrEditCourse.description.sendKeys(description);
        addOrEditCourse.teachers.click();
        addOrEditCourse.teachers.findElement(By.xpath("//option[. = '" + teachers + "']")).click();
        addOrEditCourse.students.click();
        addOrEditCourse.students.findElement(By.xpath("//option[. = '" + students + "']")).click();

        addOrEditCourse.submit.click();
        return PageFactory.initElements(driver, CoursesPage.class);
    }


}