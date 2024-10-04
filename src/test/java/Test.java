import ru.sarmosov.util.MappingUtils;
import java.util.HashMap;

public class Test {

    @org.junit.jupiter.api.Test
    void test() {
        String input = "PUT firstName=Иван, lastName=Иванов, middleName=Иванович, birthDate=1990-01-01";
        System.out.println(MappingUtils.parsePerson(MappingUtils.removeFirstWord(input)));
    }

}
