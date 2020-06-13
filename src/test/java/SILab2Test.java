import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    private final SILab2 lab2 = new SILab2();
    private final User nullUser = null;
    private final User withNullUsername = new User(null, "password", "asdasdf@gmail.com");
    private final User withNullEmail = new User("user", "password", null);
    private final User withInvalidEmail = new User("username", "password", "email");
    private final User emailWithoutDot = new User("username", "password", "email@");
    private final User validUser1 = new User("user1", "password123", "myemail1@gmail.com");
    private final User validUser2 = new User("user2", "password123", "myemail2@gmail.com");
    private final User validUser3 = new User("user3", "password123", "myemail3@gmail.com");
    private final User validUserNonExisting = new User("user4", "password123", "myemail4@gmail.com");
    private final User withEmptyEmail = new User("user4", "password123", "");
    private final List<String> allUsers = List.of("user1", "user2", "user3");

    @Test
    void everyPathTest() {
        // 1, 3, 12 -> user = null, allUsers = anything (return false)
        assertFalse(lab2.function(nullUser, allUsers));

        // 1, 2, 3, 12 -> koga user != null, no negoviot username ili email se null ili ako veke toj username se sodzri vo nizata allUsers (return false)
        assertFalse(lab2.function(withNullUsername, allUsers));
        assertFalse(lab2.function(withNullEmail, allUsers));
        assertFalse(lab2.function(validUser1, allUsers));

        // 1, 2, 4, 5.1, 5.2, 10, 3, 12 -> ni ednas ne vlagame vo for loop i user nema validen email, odnosno email e prazen string (return false)
        assertFalse(lab2.function(withEmptyEmail, allUsers));

        // 1, 2, 4, 5.1, 5.2, 10, 11, 12 -> ne moze da se sluci. Ako ne vlezeme vo for ciklusot znace nemame validen email i ne moze da vratime true

        // 1, 2, 4, 5.1, 5.2, (6, 8, 5.3, 5.2), 10, 11, 12 -> ne moze da se sluci, bidejki ne vlagame vo if uslovite nemoze funkcijata da vrati true

        // 1, 2, 4, 5.1, 5.2, (6, 8, 5.3, 5.2), 10, 3, 12 -> go izvrsuvame ciklusot nekolku pati, no ne vlagame vo if uslovite (nevaliden email)
        assertFalse(lab2.function(withInvalidEmail, allUsers));

        // 1, 2, 4, 5.1, 5.2, (6, 7, 8, 5.3, 5.2), 10, 3, 12 -> vleguvame vo prviot if uslov, ama ne vo vtoriot (email ima @, no nema tochka)
        assertFalse(lab2.function(emailWithoutDot, allUsers));

        // 1, 2, 4, 5.1, 5.2, (6, 7, 8, 5.3, 5.2), 10, 11, 12 -> nemoze da se sluci, nemoze da vratime true so invalid email (bez tochka)

        // 1, 2, 4, 5.1, 5.2, (6, 8, 9, 5.3, 5.2), 10, 11, 12 -> ne moze da se sluci, bidejki email ne e validen (nema @)

        // 1, 2, 4, 5.1, 5.2, (6, 8, 9, 5.3, 5.2), 10, 3, 12 -> ne moze da se sluci, ne moze da vlezeme vo vtoriot ciklus ako prethodno ne sme vlegle vo prviot

        // 1, 2, 4, 5.1, 5.2, (6, 7, 8, 9, 5.3, 5.2), 10, 3, 12 -> ne moze da se sluci, ne moze da vrati false ako se e vo red (email i username se validni)

        // 1, 2, 4, 5.1, 5.2, (6, 7, 8, 9, 5.3, 5.2), 10, 11, 12 -> email i username se validni (return true)
        assertTrue(lab2.function(validUserNonExisting, allUsers));
    }

    @Test
    void everyBranchTest() {
        // test case 1: user = null, allUsers = anything
        assertFalse(lab2.function(nullUser, allUsers));

        // test case 2: user ima validni email i password, no username mu e null, allUsers = anything
        assertFalse(lab2.function(withNullUsername, allUsers));

        // test case 3: user ima validni username, email i password i username ne postoi vo listata allUsers
        assertTrue(lab2.function(validUserNonExisting, allUsers));

        //test case 4: user ima nevalidna email adresa
        assertFalse(lab2.function(withInvalidEmail, allUsers));
    }
}