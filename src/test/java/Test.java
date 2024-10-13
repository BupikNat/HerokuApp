//Написать метод, который принимает число и возвращает строку.
//Условия:
//Если число кратно 3, то вернуть букву T
//Если число кратно 5, то вернуть букву M
//Если число кратно 5 и 3, то вернуть букву TMS


import org.testng.Assert;

public class Test {

    public static String checkNumber(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "TMS";
        } else if (number % 3 == 0) {
            return "T";
        } else if (number % 5 == 0) {
            return "M";
        } else {
            return "wrong number";
        }
    }

    @org.testng.annotations.Test
    public void test1() {

        String result = checkNumber(3);
        Assert.assertEquals(result, "T");
    }

    @org.testng.annotations.Test
    public void test2() {

        String result = checkNumber(5);
        Assert.assertEquals(result, "M");
    }

    @org.testng.annotations.Test
    public void test3() {

        String result = checkNumber(15);
        Assert.assertEquals(result, "TMS");
    }

    @org.testng.annotations.Test
    public void test4() {

        String result = checkNumber(0);
        Assert.assertEquals(result, "TMS");
    }

    @org.testng.annotations.Test
    public void test5() {

        String result = checkNumber(7);
        Assert.assertEquals(result, "wrong number");
    }
}
