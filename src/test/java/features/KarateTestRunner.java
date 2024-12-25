package features;

import com.intuit.karate.junit5.Karate;

public class KarateTestRunner {

    @Karate.Test
    Karate testAll() {
        // Assure-toi que "UserTests" correspond au nom exact de ton fichier .feature sans l'extension
        return Karate.run("UserTests").relativeTo(getClass());
    }
}
