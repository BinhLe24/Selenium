package support.verification;

public class Verifier {

    public static void verifyResult(String actualResult, String expectedResult) {
        if (!actualResult.equals(expectedResult)) {
            System.out.printf("Actual is: %s\nExpected is:%s\n", actualResult, expectedResult);
            throw new AssertionError("actualResult must be equal expectedResult");
        }
    }
}
